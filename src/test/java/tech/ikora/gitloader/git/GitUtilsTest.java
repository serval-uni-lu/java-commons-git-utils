package tech.ikora.gitloader.git;

import org.junit.jupiter.api.Test;
import tech.ikora.gitloader.exception.InvalidGitRepositoryException;

import static org.junit.jupiter.api.Assertions.*;

class GitUtilsTest {
    @Test
    void testNameExtractionFromGithubUrl() throws InvalidGitRepositoryException {
        final String name = GitUtils.extractProjectName("https://github.com/kabinja/git-loader.git");
        assertEquals("kabinja-git-loader", name);
    }
}