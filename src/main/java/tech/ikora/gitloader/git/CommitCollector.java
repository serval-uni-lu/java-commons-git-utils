package tech.ikora.gitloader.git;

import org.eclipse.jgit.api.Git;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommitCollector {
    private Git git;
    private Date start;
    private Date end;
    private String branch;
    private Set<String> ignored = Collections.emptySet();
    private Frequency frequency = Frequency.UNIQUE;
    private int limit = 0;

    public CommitCollector forGit(Git git){
        this.git = git;
        return this;
    }

    public CommitCollector onBranch(String branch){
        this.branch = branch;
        return this;
    }

    public CommitCollector from(Date start){
        this.start = start;
        return this;
    }

    public CommitCollector to(Date end){
        this.end = end;
        return this;
    }

    public CommitCollector ignoring(Set<String> commitIds){
        this.ignored = commitIds;
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

    public List<GitCommit> collect(){
        List<GitCommit> commits = GitUtils.getCommits(
                this.git,
                this.start,
                this.end,
                this.branch
        );

        if(ignored != null && !ignored.isEmpty()){
            commits = commits.stream()
                    .filter(commit -> ignored.contains(commit.getId()))
                    .collect(Collectors.toList());
        }

        commits = GitUtils.filterCommitsByFrequency(commits, frequency);

        return limit > 0 ? commits.subList(0, Math.min(commits.size(), limit)) : commits;
    }
}
