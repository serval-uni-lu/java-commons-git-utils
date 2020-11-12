package tech.ikora.gitloader.git;

import org.eclipse.jgit.api.Git;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tech.ikora.gitloader.Helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class CommitCollectorTest {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private static Git git1;
    private static Git git3;

    @BeforeAll
    static void setup() {
        git1 = Helpers.setRepository("git-repos/git-1.zip");
        git3 = Helpers.setRepository("git-repos/git-3.zip");
    }

    @Test
    void testCollectWithoutFilter(){
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .collect();

        assertEquals(13, commits.size());
    }

    @Test
    void testCollectWithBranch(){
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .onBranch("b2")
                .collect();

        assertEquals(1, commits.size());
    }

    @Test
    void testCollectWithLimit(){
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .limit(4)
                .collect();

        assertEquals(4, commits.size());
    }

    @Test
    void testCollectWithLimitTooBig(){
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .limit(100)
                .collect();

        assertEquals(13, commits.size());
    }

    @Test
    void testCollectWithLimitTooSmall(){
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .limit(0)
                .collect();

        assertEquals(13, commits.size());
    }

    @Test
    void testCollectWithStartDate() throws ParseException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .from(formatter.parse("2014-11-17"))
                .collect();

        assertEquals(4, commits.size());
    }

    @Test
    void testCollectWithEndDate() throws ParseException {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .to(formatter.parse("2014-11-17"))
                .collect();

        assertEquals(9, commits.size());
    }

    @Test
    void testCollectWithExtension() {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .forExtensions(Collections.singleton("javax"))
                .collect();

        assertEquals(2, commits.size());
    }

    @Test
    void testCollectWithSubFolder() {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .filterNoChangeIn(Collections.singleton("pasta"))
                .collect();

        assertEquals(2, commits.size());
    }

    @Test
    void testCollectWithFrequencyDaily() {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .every(Frequency.DAILY)
                .collect();

        assertEquals(5, commits.size());
    }

    @Test
    void testCollectWithFrequencyYearly() {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .every(Frequency.YEARLY)
                .collect();

        assertEquals(2, commits.size());
    }

    @Test
    void testCollectWithFrequencyUnique() {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .every(Frequency.UNIQUE)
                .collect();

        assertEquals(13, commits.size());
    }

    @Test
    void testCollectWithFrequencyVersion(){
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
    void testCollectWithIgnore() {
        final List<GitCommit> commits = new CommitCollector()
                .forGit(git1)
                .ignoring(Collections.singleton("71535a31f0b598a5d5fcebda7146ebc01def783a"))
                .collect();

        assertEquals(12, commits.size());
    }

    @AfterAll
    static void teardown(){
        Helpers.deleteRepository(git1);
    }
}