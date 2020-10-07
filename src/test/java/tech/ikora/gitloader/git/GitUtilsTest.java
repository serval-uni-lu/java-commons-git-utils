package tech.ikora.gitloader.git;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tech.ikora.gitloader.Helpers;
import tech.ikora.gitloader.exception.InvalidGitRepositoryException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class GitUtilsTest {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
    private static Git git2;

    @BeforeAll
    static void setup() {
        git2 = Helpers.setRepository("git-repos/git-2.zip");
    }

    @Test
    void testCreateLocalRepository() throws GitAPIException, IOException, ParseException {
        final LocalRepository localRepository = GitUtils.createLocalRepository(git2);

        assertEquals(git2.getRepository().getDirectory().getParentFile(), localRepository.getLocation());
        assertEquals(git2, localRepository.getGit());

        final GitCommit commit = localRepository.getGitCommit();

        assertEquals(formatter.parse("2016-04-04 13:21:25"), commit.getDate());
    }

    @Test
    void testCreateLocalRepositoryGetHeadCommit() throws GitAPIException, IOException, ParseException {
        final GitCommit commit = GitUtils.createLocalRepository(git2).getGitCommit();

        assertEquals(formatter.parse("2016-04-04 13:21:25"), commit.getDate());
        assertEquals("29e929fbc5dc6a2e9c620069b24e2a143af4285f", commit.getId());
        assertEquals(1, commit.getDiffEntries().size());
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

    @AfterAll
    static void teardown(){
        Helpers.deleteRepository(git2);
    }
}