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

import org.apache.commons.io.FilenameUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class CommitCollector {
    private Git git;
    private Instant start;
    private Instant end;
    private String branch = "master";
    private Set<String> ignored = Collections.emptySet();
    private Frequency frequency = Frequency.UNIQUE;
    private int limit = 0;
    private Set<String> filterNoChangeIn = Collections.emptySet();
    private Set<String> extensions = Collections.emptySet();
    private boolean withDifference = true;

    public CommitCollector forGit(Git git){
        this.git = git;
        return this;
    }

    public CommitCollector onBranch(String branch){
        this.branch = branch;
        return this;
    }

    public CommitCollector from(Instant start){
        this.start = start;
        return this;
    }

    public CommitCollector to(Instant end){
        this.end = end;
        return this;
    }

    public CommitCollector ignoring(Set<String> commitIds){
        this.ignored = commitIds;
        return this;
    }

    public CommitCollector filterNoChangeIn(Set<String> foldersToWatch){
        this.filterNoChangeIn = foldersToWatch;
        return this;
    }

    public CommitCollector forExtensions(Set<String> extensions){
        this.extensions = extensions.stream().map(String::toLowerCase).collect(Collectors.toSet());
        return this;
    }

    public CommitCollector every(Frequency frequency){
        this.frequency = frequency;
        return this;
    }

    public CommitCollector limit(int limit){
        this.limit = limit;
        return this;
    }

    public CommitCollector withDifference(boolean withDifference){
        this.withDifference = withDifference;
        return this;
    }

    public List<GitCommit> collect() throws IOException, GitAPIException {
        List<GitCommit> commits;

        if(frequency == Frequency.RELEASE){
            commits = GitUtils.getTags(
                    this.git,
                    this.start,
                    this.end
            );
        }
        else{
            commits = GitUtils.getCommits(
                    this.git,
                    this.start,
                    this.end,
                    this.branch
            );
        }

        if(ignored != null && !ignored.isEmpty()){
            commits = commits.stream()
                    .filter(commit -> !ignored.contains(commit.getId()))
                    .collect(Collectors.toList());
        }

        if(!extensions.isEmpty() || !filterNoChangeIn.isEmpty()){
            GitUtils.setDifferences(git, commits);
            commits = commits.stream()
                    .filter(c -> isContainExtension(c, extensions))
                    .filter(c -> isSubfolderChanged(c, filterNoChangeIn, extensions))
                    .collect(Collectors.toList());
        }

        if(commits.isEmpty()){
            return commits;
        }

        commits = processFrequency(commits);
        finalizeCollection(commits);

        return commits;
    }

    private List<GitCommit> processFrequency(List<GitCommit> commits){
        commits = GitUtils.filterCommitsByFrequency(commits, frequency);

        if(frequency == Frequency.LATEST){
            commits = commits.subList(commits.size() - 1, commits.size());
        }
        else{
            commits = limit > 0 ? commits.subList(0, Math.min(commits.size(), limit)) : commits;
        }

        return commits;
    }

    private static boolean hasExtension(String path, Set<String> extensions){
        if(extensions == null || extensions.isEmpty()){
            return true;
        }

        return extensions.contains(FilenameUtils.getExtension(path));
    }

    private static boolean isContainExtension(GitCommit commit, Set<String> extensions) {
        if(extensions == null || extensions.isEmpty()){
            return true;
        }

        for(DiffEntry diffEntry: commit.getDifference().getEntries()){
            if(hasExtension(diffEntry.getOldPath(), extensions)){
                return true;
            }

            if(hasExtension(diffEntry.getNewPath(), extensions)){
                return true;
            }
        }

        return false;
    }

    private static boolean isSubfolderChanged(GitCommit commit, Set<String> subFolders, Set<String> extensions) {
        if((subFolders == null || subFolders.isEmpty())){
            return true;
        }

        for(DiffEntry diffEntry: commit.getDifference().getEntries()){
            if(isEntryInSubFolder(diffEntry, subFolders, extensions)){
                return true;
            }
        }

        return false;
    }

    private static boolean isEntryInSubFolder(DiffEntry diffEntry, Set<String> subFolders, Set<String> extensions){
        for(String subFolder: subFolders){
            if(FilenameUtils.directoryContains(subFolder, diffEntry.getOldPath())
                    && hasExtension(diffEntry.getOldPath(), extensions)){
                return true;
            }

            if(FilenameUtils.directoryContains(subFolder, diffEntry.getNewPath())
                    && hasExtension(diffEntry.getNewPath(), extensions)){
                return true;
            }
        }

        return false;
    }

    public List<GitCommit> cherryPick(String... commitIds) throws IOException, GitAPIException {
        List<GitCommit> commits = new ArrayList<>(commitIds.length);

        for(String commitId: commitIds){
            GitUtils.getCommitById(git, commitId).ifPresent(commits::add);
        }

        finalizeCollection(commits);
        return commits;
    }

    private void finalizeCollection(List<GitCommit> commits) throws IOException, GitAPIException {
        if(withDifference){
            GitUtils.setDifferences(git, commits);
        }
    }
}
