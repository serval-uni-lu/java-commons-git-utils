package lu.uni.serval.commons.git.utils;

/*-
 * #%L
 * GIT Utils
 * %%
 * Copyright (C) 2020 - 2021 University of Luxembourg, Renaud RWEMALIKA
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
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

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import lu.uni.serval.commons.git.Helpers;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommitCollectorTest {
    private static Git git1;
    private static Git git3;

    @BeforeAll
    static void setup() {
        git1 = Helpers.setRepository("git-repos/git-1.zip");
        git3 = Helpers.setRepository("git-repos/git-3.zip");
    }

    @Test
    void testCollectWithoutFilter() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .collect();

        assertEquals(13, commits.size());
    }

    @Test
    void testCollectWithBranch() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .onBranch("b2")
                .collect();

        assertEquals(1, commits.size());
    }

    @Test
    void testCollectWithLimit() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .limit(4)
                .collect();

        assertEquals(4, commits.size());
    }

    @Test
    void testCollectWithLimitTooBig() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .limit(100)
                .collect();

        assertEquals(13, commits.size());
    }

    @Test
    void testCollectWithLimitTooSmall() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .limit(0)
                .collect();

        assertEquals(13, commits.size());
    }

    @Test
    void testCollectWithStartDate() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .from(Instant.parse("2014-11-17T00:00:00.000Z"))
                .collect();

        assertEquals(4, commits.size());
    }

    @Test
    void testCollectWithEndDate() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .to(Instant.parse("2014-11-17T00:00:00.000Z"))
                .collect();

        assertEquals(9, commits.size());
    }

    @Test
    void testCollectWithExtension() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .forExtensions(Collections.singleton("javax"))
                .collect();

        assertEquals(1, commits.size());
    }

    @Test
    void testCollectWithSubFolder() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .filterNoChangeIn(Collections.singleton("pasta"))
                .collect();

        assertEquals(1, commits.size());
    }

    @Test
    void testCollectWithFrequencyDaily() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .every(Frequency.DAILY)
                .collect();

        assertEquals(5, commits.size());
    }

    @Test
    void testCollectWithFrequencyYearly() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .every(Frequency.YEARLY)
                .collect();

        assertEquals(2, commits.size());
    }

    @Test
    void testCollectWithFrequencyUnique() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .every(Frequency.UNIQUE)
                .collect();

        assertEquals(13, commits.size());
    }

    @Test
    void testCollectWithFrequencyLatest() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .onBranch(null)
                .every(Frequency.LATEST)
                .collect();

        assertEquals(1, commits.size());
        assertEquals("e7d13b0511f8a176284ce4f92ed8c6e8d09c77f2", commits.get(0).getId());
    }

    @Test
    void testCollectWithFrequencyVersion() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git3)
                .every(Frequency.RELEASE)
                .collect();

        assertEquals(3, commits.size());
        assertEquals("tag1", commits.get(0).getTag());
        assertEquals("tag2", commits.get(1).getTag());
        assertEquals("tag3", commits.get(2).getTag());
    }

    @Test
    void testCollectWithIgnore() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .ignoring(Collections.singleton("71535a31f0b598a5d5fcebda7146ebc01def783a"))
                .collect();

        assertEquals(12, commits.size());
    }

    @Test
    void testCherryPick() throws IOException, GitAPIException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .cherryPick(
                        "71535a31f0b598a5d5fcebda7146ebc01def783a",
                        "e7d13b0511f8a176284ce4f92ed8c6e8d09c77f2"
                );

        assertEquals(2, commits.size());

        assertEquals("71535a31f0b598a5d5fcebda7146ebc01def783a", commits.get(0).getId());
        assertEquals("e7d13b0511f8a176284ce4f92ed8c6e8d09c77f2", commits.get(1).getId());

        assertTrue(commits.get(0).getDifference().getFormatted().isEmpty());
        assertFalse(commits.get(1).getDifference().getFormatted().isEmpty());
    }

    @Test
    void testCherryPickWithShortTag() throws GitAPIException, IOException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git3)
                .cherryPick("tag1");

        assertEquals(1, commits.size());
    }

    @Test
    void testCherryPickWithMediumTag() throws GitAPIException, IOException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git3)
                .cherryPick("tags/tag1");

        assertEquals(1, commits.size());
    }

    @Test
    void testCherryPickWithLongTag() throws GitAPIException, IOException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git3)
                .cherryPick("refs/tags/tag1");

        assertEquals(1, commits.size());
    }

    @AfterAll
    static void teardown(){
        Helpers.deleteRepository(git1);
        Helpers.deleteRepository(git3);
    }
}
