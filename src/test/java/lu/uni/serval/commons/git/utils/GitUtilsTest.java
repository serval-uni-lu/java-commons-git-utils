package lu.uni.serval.commons.git.utils;

/*-
 * #%L
 * GIT Utils
 * %%
 * Copyright (C) 2020 - 2021 University of Luxembourg, Renaud RWEMALIKA
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import lu.uni.serval.commons.git.exception.CommitNotFoundException;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import lu.uni.serval.commons.git.Helpers;
import lu.uni.serval.commons.git.exception.InvalidGitRepositoryException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GitUtilsTest {
    private static Git git2;
    private static Git git3;
    private static Git git4;

    @BeforeAll
    static void setup() {
        git2 = Helpers.setRepository("git-repos/git-2.zip");
        git3 = Helpers.setRepository("git-repos/git-3.zip");
        git4 = Helpers.setRepository("git-repos/git-4.zip");
    }

    @Test
    void testCreateLocalRepository() throws GitAPIException, IOException {
        final LocalRepository localRepository = GitUtils.createLocalRepository(git2);

        assertEquals(git2.getRepository().getDirectory().getParentFile(), localRepository.getLocation());
        assertEquals(git2, localRepository.getGit());

        final GitCommit commit = localRepository.getGitCommit();

        assertEquals(Instant.parse("2016-04-04T11:21:25Z"), commit.getDate());
    }

    @Test
    void testCreateLocalRepositoryGetHeadCommit() throws GitAPIException, IOException {
        final GitCommit commit = GitUtils.createLocalRepository(git2).getGitCommit();

        assertEquals(Instant.parse("2016-04-04T11:21:25Z"), commit.getDate());
        assertEquals("29e929fbc5dc6a2e9c620069b24e2a143af4285f", commit.getId());
        assertEquals(1, commit.getDifference().getEntries().size());
        assertFalse(commit.getDifference().getFormatted().isEmpty());
    }

    @Test
    void testNameExtractionFromGithubUrl() throws InvalidGitRepositoryException {
        final String name = GitUtils.extractProjectName("https://github.com/kabinja/git-loader.git");
        assertEquals("kabinja-git-loader", name);
    }

    @Test
    void testCheckoutWithInvalidCommit() throws GitAPIException, IOException {
        final LocalRepository localRepository = GitUtils.createLocalRepository(git2);

        final File directory = localRepository.getLocation();
        final String[] extensions = new String[]{"java"};

        GitUtils.checkout(localRepository.getGit(), "");
        assertEquals(0, FileUtils.listFiles(directory, extensions, true).size());

        GitUtils.checkout(localRepository.getGit(), localRepository.getGitCommit().getId());
        assertEquals(1, FileUtils.listFiles(directory, extensions, true).size());

        GitUtils.checkout(localRepository.getGit(), "");
        assertEquals(0, FileUtils.listFiles(directory, extensions, true).size());
    }

    @Test
    void testCheckoutByDate() throws IOException, GitAPIException, CommitNotFoundException {
        final Ref ref = GitUtils.checkout(git2, Instant.parse("2016-04-04T11:21:10Z"), "b1");

        try(RevWalk revWalk = new RevWalk(git2.getRepository())){
            final RevCommit commit = revWalk.parseCommit(ref.getObjectId());
            assertEquals("8169f76a3d7add54b4fc7bca7160d1f1eede6eda", commit.getName());
        }
    }

    @Test
    void testCheckoutByDateTooEarly() throws IOException, GitAPIException {
        try{
            GitUtils.checkout(git2, Instant.parse("2016-03-04T11:21:10Z"), "master");
            fail("Should raise a CommitNotFoundException");
        }
        catch (CommitNotFoundException e){
            assertTrue(e.getMessage().contains("016-03-04T11:21:10Z"));
        }
    }

    @Test
    void testCreateLocalRepositoryAfterInvalidCommit() throws GitAPIException, IOException {
        final LocalRepository localRepository1 = GitUtils.createLocalRepository(git2);
        GitUtils.checkout(localRepository1.getGit(), "");
        GitUtils.createLocalRepository(git2);

        //make sure no exception is thrown
        assertTrue(true);
    }

    @Test
    void testInvalidCommitTwice() throws GitAPIException, IOException {
        final LocalRepository localRepository1 = GitUtils.createLocalRepository(git2);
        GitUtils.checkout(localRepository1.getGit(), "");
        GitUtils.checkout(localRepository1.getGit(), "");

        //make sure no exception is thrown
        assertTrue(true);
    }

    @Test
    void testGetVersion() throws GitAPIException, IOException {
        final LocalRepository localRepository = GitUtils.createLocalRepository(git3);
        final List<GitCommit> versions = GitUtils.getTags(localRepository.getGit(), null, null);
        assertEquals(3, versions.size());
    }

    @Test
    void testGetCommitDate() throws IOException, GitAPIException {
        final Instant date = GitUtils.getCommitDate(git2, "29e929fbc5dc6a2e9c620069b24e2a143af4285f");
        assertEquals(Instant.parse("2016-04-04T11:21:25Z"), date);
    }

    @Test
    void testDifference() throws IOException, GitAPIException {
        final String expectedFormatted = "diff --git a/ATest.java b/ATest.java\n" +
                "index c2787f9..9f9f890 100644\n" +
                "--- a/ATest.java\n" +
                "+++ b/ATest.java\n" +
                "@@ -1,3 +1,4 @@\n" +
                " class ATest{\n" +
                "     \\\\modify\n" +
                "+    \\\\added a line\n" +
                " }\n" +
                "\\ No newline at end of file";

        final Difference difference = GitUtils.getDifference(git3, "f1a90b8d7b151ceefd3e3dfc0dc1d0e12b5f48d0", "4638730126d40716e230c2040751a13153fb1556");
        final List<DiffEntry> entries = difference.getEntries();
        final String formatted = difference.getFormatted();

        assertEquals(1, entries.size());
        assertEquals(expectedFormatted, formatted);
    }

    @Test
    void testCheckoutAfterChanges() throws GitAPIException, IOException {
        final LocalRepository localRepository = GitUtils.createLocalRepository(git3);
        GitUtils.checkout(localRepository.getGit(), "f1a90b8d7b151ceefd3e3dfc0dc1d0e12b5f48d0");

        File file = new File(localRepository.getLocation(), "ATest.java");

        try(FileOutputStream fos = new FileOutputStream(file, true)){
            fos.write("Spain\r\n".getBytes());
        }

        GitUtils.checkout(localRepository.getGit(), "4638730126d40716e230c2040751a13153fb1556");
    }

    @Test
    void testCherryPickWhenPresent() throws IOException {
        final Optional<GitCommit> commit = GitUtils.getCommitById(git3, "4638730126d40716e230c2040751a13153fb1556");
        assertTrue(commit.isPresent());
        assertEquals("4638730126d40716e230c2040751a13153fb1556", commit.get().getId());
    }

    @Test
    void testCherryPickWhenAbsent() throws IOException {
        final Optional<GitCommit> commit = GitUtils.getCommitById(git3, "12345678912345679");
        assertFalse(commit.isPresent());
    }

    @Test
    void testCherryPickWithTag() throws IOException {
        final Optional<GitCommit> commit = GitUtils.getCommitById(git3, "tag1");
        assertTrue(commit.isPresent());
    }

    @Test
    void testDifferentBranch() throws InvalidGitRepositoryException, IOException, GitAPIException {
        final File folder = new File(FileUtils.getTempDirectory(), "test-git-utils");
        folder.deleteOnExit();

        final LocalRepository repo = GitUtils.loadCurrentRepository(
                git4.getRepository().getDirectory().getAbsolutePath(),
                "",
                folder,
                "main"
        );

        final List<Ref> branches = repo.getGit().branchList().call();
        assertEquals(1, branches.size());
        assertTrue(GitUtils.isBranchExist(repo.getGit(), "main"));
    }

    @AfterAll
    static void teardown(){
        Helpers.deleteRepository(git2);
        Helpers.deleteRepository(git3);
        Helpers.deleteRepository(git4);
    }
}
