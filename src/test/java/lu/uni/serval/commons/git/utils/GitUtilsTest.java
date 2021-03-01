package lu.uni.serval.commons.git.utils;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.diff.DiffEntry;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import lu.uni.serval.commons.git.Helpers;
import lu.uni.serval.commons.git.exception.InvalidGitRepositoryException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GitUtilsTest {
    private static Git git2;
    private static Git git3;

    @BeforeAll
    static void setup() {
        git2 = Helpers.setRepository("git-repos/git-2.zip");
        git3 = Helpers.setRepository("git-repos/git-3.zip");
    }

    @Test
    void testCreateLocalRepository() throws GitAPIException, IOException {
        final LocalRepository localRepository = GitUtils.createLocalRepository(git2);

        assertEquals(git2.getRepository().getDirectory().getParentFile(), localRepository.getLocation());
        assertEquals(git2, localRepository.getGit());

        final GitCommit commit = localRepository.getGitCommit();

        assertEquals(GitUtils.toInstant("2016-04-04 11:21:25"), commit.getDate());
    }

    @Test
    void testCreateLocalRepositoryGetHeadCommit() throws GitAPIException, IOException {
        final GitCommit commit = GitUtils.createLocalRepository(git2).getGitCommit();

        assertEquals(GitUtils.toInstant("2016-04-04 11:21:25"), commit.getDate());
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
        final List<GitCommit> versions = GitUtils.getVersions(localRepository.getGit(), null, null);
        assertEquals(3, versions.size());
    }

    @Test
    void testGetCommitDate() throws IOException, InvalidRefNameException, ParseException {
        final Instant date = GitUtils.getCommitDate(git2, "29e929fbc5dc6a2e9c620069b24e2a143af4285f");
        assertEquals(GitUtils.toInstant("2016-04-04 11:21:25"), date);
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
    void testCherryPickWheAbsent() throws IOException {
        final Optional<GitCommit> commit = GitUtils.getCommitById(git3, "12345678912345679");
        assertFalse(commit.isPresent());
    }

    @AfterAll
    static void teardown(){
        Helpers.deleteRepository(git2);
        Helpers.deleteRepository(git3);
    }
}