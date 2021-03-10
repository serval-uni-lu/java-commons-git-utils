package lu.uni.serval.commons.git.api;

import lu.uni.serval.commons.git.api.gitlab.Gitlab;

public class GitEngineFactory {
    private GitEngineFactory() {}

    public static GitEngine create(Api api){
        GitEngine gitEngine;

        switch (api){
            case GITLAB: gitEngine = new Gitlab(); break;
            case GIT:
            case GITHUB:
            default: gitEngine = null; break;
        }

        return gitEngine;
    }
}
