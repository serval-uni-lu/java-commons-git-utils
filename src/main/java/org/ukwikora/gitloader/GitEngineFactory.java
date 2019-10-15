package org.ukwikora.gitloader;

import org.ukwikora.gitloader.gitlab.Gitlab;

public class GitEngineFactory {
    public static GitEngine create(Api api){
        GitEngine gitEngine;

        switch (api){
            case Gitlab: gitEngine = new Gitlab(); break;
            case Git:
            case Github:
            default: gitEngine = null; break;
        }

        return gitEngine;
    }
}
