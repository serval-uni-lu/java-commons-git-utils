package org.ukwikora.gitloader.gitlab;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.ukwikora.gitloader.git.LocalRepo;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GitlabIntegrationTest {
    Gitlab createEngine(){
        Gitlab gitlab = new Gitlab();
        gitlab.setToken("oTh9EGjp7UJTBew-aiMg");
        gitlab.setUrl("https://gitlab.com");

        return gitlab;
    }

    @Test
    @Ignore
    void testCloningFromName(){
        try {
            Gitlab gitlab = createEngine();
            Set<String> names = new HashSet<>(3);
            names.add("ukwikora/robot-framework-project-a");
            names.add("ukwikora/robot-framework-project-b");
            names.add("ukwikora/robot-framework-project-c");

            gitlab.cloneProjectsFromNames(names);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    @Ignore
    void testCloningFromUsername(){
        try{
            Gitlab gitlab = createEngine();
            gitlab.cloneProjectsFromUser("renaudrwemalika");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testCloningFromGroup(){
        try{
            Gitlab gitlab = createEngine();
            final Set<LocalRepo> localRepos = gitlab.cloneProjectsFromGroup("ukwikora");
            assertEquals(3, localRepos.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}