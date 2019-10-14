package org.ukwikora.gitlabloader;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;
import java.util.Map;

public class Project {
    private int id;
    private String description;
    private String name;
    private String name_with_namespace;
    private String path;
    private String path_with_namespace;
    private String created_at;
    private String default_branch;
    private List<String> tag_list;
    private String ssh_url_to_repo;
    private String http_url_to_repo;
    private String web_url;
    private String readme_url;
    private String avatar_url;
    private int star_count;
    private int forks_count;
    private String last_activity_at;
    private Map<String, String> namespace;
    private Map<String, String> _links;
    private boolean empty_repo;
    private boolean archived;
    private String visibility;
    private boolean resolve_outdated_diff_discussions;
    private boolean container_registry_enabled;
    private boolean issues_enabled;
    private String issues_access_level;
    private String repository_access_level;
    private String merge_requests_access_level;
    private String builds_access_level;
    private String wiki_access_level;
    private String snippets_access_level;
    private int build_timeout;
    private boolean wiki_enabled;
    private boolean jobs_enabled;
    private boolean snippets_enabled;
    private boolean shared_runners_enabled;
    private boolean lfs_enabled;
    private int creator_id;
    private String import_status;
    private int open_issues_count;
    private int ci_default_git_depth;
    private boolean public_jobs;
    private String ci_config_path;
    private List<String> shared_with_groups;
    private boolean only_allow_merge_if_pipeline_succeeds;
    private boolean request_access_enabled;
    private boolean only_allow_merge_if_all_discussions_are_resolved;
    private boolean printing_merge_request_link_enabled;
    private String merge_method;
    private int approvals_before_merge;
    private boolean mirror;
    private boolean merge_requests_enabled;
    private String external_authorization_classification_label;

    @JsonGetter("id")
    public int getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonGetter("description")
    public String getDescription() {
        return description;
    }

    @JsonSetter("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("name_with_namespace")
    public String getNameWithNamespace() {
        return name_with_namespace;
    }

    @JsonSetter("name_with_namespace")
    public void setNameWithNamespace(String name_with_namespace) {
        this.name_with_namespace = name_with_namespace;
    }

    @JsonGetter("path")
    public String getPath() {
        return path;
    }

    @JsonSetter("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonGetter("path_with_namespace")
    public String getPathWithNamespace() {
        return path_with_namespace;
    }

    public void setPath_with_namespace(String path_with_namespace) {
        this.path_with_namespace = path_with_namespace;
    }

    @JsonGetter("created_at")
    public String getCreatedAt() {
        return created_at;
    }

    @JsonSetter("created_at")
    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    @JsonGetter("default_branch")
    public String getDefaultBranch() {
        return default_branch;
    }

    public void setDefault_branch(String default_branch) {
        this.default_branch = default_branch;
    }

    @JsonGetter("tag_list")
    public List<String> getTagList() {
        return tag_list;
    }

    public void setTag_list(List<String> tag_list) {
        this.tag_list = tag_list;
    }

    @JsonGetter("ssh_url_to_repo")
    public String getSshUrlToRepo() {
        return ssh_url_to_repo;
    }

    public void setSsh_url_to_repo(String ssh_url_to_repo) {
        this.ssh_url_to_repo = ssh_url_to_repo;
    }

    @JsonGetter("http_url_to_repo")
    public String getHttpUrlToRepo() {
        return http_url_to_repo;
    }

    @JsonSetter("http_url_to_repo")
    public void setHttpUrlToRepo(String http_url_to_repo) {
        this.http_url_to_repo = http_url_to_repo;
    }

    @JsonGetter("web_url")
    public String getWebUrl() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    @JsonGetter("readme_url")
    public String getReadmeUrl() {
        return readme_url;
    }

    @JsonSetter("readme_url")
    public void setReadmeUrl(String readme_url) {
        this.readme_url = readme_url;
    }

    @JsonGetter("avatar_url")
    public String getAvatarUrl() {
        return avatar_url;
    }

    public void setAvatarUrl(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @JsonGetter("star_count")
    public int getStarCount() {
        return star_count;
    }

    public void setStarCount(int star_count) {
        this.star_count = star_count;
    }

    @JsonGetter("forks_count")
    public int getForksCount() {
        return forks_count;
    }

    @JsonSetter("forks_count")
    public void setForksCount(int forks_count) {
        this.forks_count = forks_count;
    }

    @JsonGetter("last_activity_at")
    public String getLastActivityAt() {
        return last_activity_at;
    }

    @JsonSetter("last_activity_at")
    public void setLastActivityAt(String last_activity_at) {
        this.last_activity_at = last_activity_at;
    }

    @JsonGetter("empty_repo")
    public boolean isEmptyRepo() {
        return empty_repo;
    }

    @JsonSetter("empty_repo")
    public void setEmptyRepo(boolean empty_repo) {
        this.empty_repo = empty_repo;
    }

    @JsonGetter("archived")
    public boolean isArchived() {
        return archived;
    }

    @JsonSetter("archived")
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @JsonGetter("visibility")
    public String getVisibility() {
        return visibility;
    }

    @JsonSetter("visibility")
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @JsonGetter("resolve_outdated_diff_discussions")
    public boolean isResolveOutdatedDiffDiscussions() {
        return resolve_outdated_diff_discussions;
    }

    @JsonSetter("resolve_outdated_diff_discussions")
    public void setResolveOutdatedDiffDiscussions(boolean resolve_outdated_diff_discussions) {
        this.resolve_outdated_diff_discussions = resolve_outdated_diff_discussions;
    }

    @JsonGetter("container_registry_enabled")
    public boolean isContainerRegistryEnabled() {
        return container_registry_enabled;
    }

    @JsonSetter("container_registry_enabled")
    public void setContainerRegistryEnabled(boolean container_registry_enabled) {
        this.container_registry_enabled = container_registry_enabled;
    }

    @JsonGetter("issues_enabled")
    public boolean isIssuesEnabled() {
        return issues_enabled;
    }

    @JsonSetter("issues_enabled")
    public void setIssuesEnabled(boolean issues_enabled) {
        this.issues_enabled = issues_enabled;
    }

    @JsonGetter("wiki_enabled")
    public boolean isWikiEnabled() {
        return wiki_enabled;
    }

    @JsonSetter("wiki_enabled")
    public void setWikiEnabled(boolean wiki_enabled) {
        this.wiki_enabled = wiki_enabled;
    }

    @JsonGetter("jobs_enabled")
    public boolean isJobsEnabled() {
        return jobs_enabled;
    }

    public void setJobs_enabled(boolean jobs_enabled) {
        this.jobs_enabled = jobs_enabled;
    }

    @JsonGetter("snippets_enabled")
    public boolean isSnippetsEnabled() {
        return snippets_enabled;
    }

    @JsonSetter("snippets_enabled")
    public void setSnippetsEnabled(boolean snippets_enabled) {
        this.snippets_enabled = snippets_enabled;
    }

    @JsonGetter("shared_runners_enabled")
    public boolean isSharedRunnersEnabled() {
        return shared_runners_enabled;
    }

    @JsonSetter("shared_runners_enabled")
    public void setSharedRunnersEnabled(boolean shared_runners_enabled) {
        this.shared_runners_enabled = shared_runners_enabled;
    }

    @JsonGetter("lfs_enabled")
    public boolean isLfsEnabled() {
        return lfs_enabled;
    }

    public void setLfs_enabled(boolean lfs_enabled) {
        this.lfs_enabled = lfs_enabled;
    }

    @JsonGetter("creator_id")
    public int getCreatorId() {
        return creator_id;
    }

    @JsonSetter("creator_id")
    public void setCreatorId(int creator_id) {
        this.creator_id = creator_id;
    }

    @JsonGetter("import_status")
    public String getImportStatus() {
        return import_status;
    }

    @JsonSetter("import_status")
    public void setImportStatus(String import_status) {
        this.import_status = import_status;
    }

    @JsonSetter("issues_access_level")
    public void setIssuesAccessLevel(String issuesAccessLevel){
        this.issues_access_level = issuesAccessLevel;
    }

    @JsonGetter("issues_access_level")
    public String getIssuesAccessLevel() {
        return issues_access_level;
    }

    @JsonSetter("repository_access_level")
    public void setRepositoryAccessLevel(String repositoryAccessLevel){
        this.repository_access_level = repositoryAccessLevel;
    }

    @JsonGetter("repository_access_level")
    public String getRepositoryAccessLevel() {
        return repository_access_level;
    }

    @JsonSetter("merge_requests_access_level")
    public void setMergeRequestsAccessLevel(String mergeRequestsAccessLevel){
        this.merge_requests_access_level = mergeRequestsAccessLevel;
    }

    @JsonGetter("merge_requests_access_level")
    public String getMergeRequestsAccessLevel() {
        return merge_requests_access_level;
    }

    @JsonSetter("builds_access_level")
    public void setBuildsAccessLevel(String buildsAccessLevel){
        this.builds_access_level = buildsAccessLevel;
    }

    @JsonGetter("builds_access_level")
    public String getBuildsAccessLevel() {
        return builds_access_level;
    }

    @JsonSetter("wiki_access_level")
    public void setWikiAccessLevel(String wikiAccessLevel){
        this.wiki_access_level = wikiAccessLevel;
    }

    @JsonGetter("wiki_access_level")
    public String getWikiAccessLevel() {
        return wiki_access_level;
    }

    @JsonSetter("snippets_access_level")
    public void setSnippetsAccessLevel(String snippetsAccessLevel){
        this.snippets_access_level = snippetsAccessLevel;
    }

    @JsonGetter("snippets_access_level")
    public String getSnippetsAccessLevel() {
        return snippets_access_level;
    }

    @JsonSetter("build_timeout")
    public void setBuildTimeout(int buildTimeout){
        this.build_timeout = buildTimeout;
    }

    @JsonGetter("build_timeout")
    public int getBuildTimeout() {
        return build_timeout;
    }

    @JsonGetter("open_issues_count")
    public int getOpenIssuesCount() {
        return open_issues_count;
    }

    @JsonSetter("open_issues_count")
    public void setOpenIssuesCount(int open_issues_count) {
        this.open_issues_count = open_issues_count;
    }

    @JsonGetter("ci_default_git_depth")
    public int getCiDefaultGitDepth() {
        return ci_default_git_depth;
    }

    @JsonSetter("ci_default_git_depth")
    public void setCiDefaultGitDepth(int ci_default_git_depth) {
        this.ci_default_git_depth = ci_default_git_depth;
    }

    @JsonGetter("public_jobs")
    public boolean isPublicJobs() {
        return public_jobs;
    }

    @JsonSetter("public_jobs")
    public void setPublicJobs(boolean public_jobs) {
        this.public_jobs = public_jobs;
    }

    @JsonGetter("ci_config_path")
    public String getCiConfigPath() {
        return ci_config_path;
    }

    @JsonSetter("ci_config_path")
    public void setCiConfigPath(String ci_config_path) {
        this.ci_config_path = ci_config_path;
    }

    @JsonGetter("shared_with_groups")
    public List<String> getSharedWithGroups() {
        return shared_with_groups;
    }

    @JsonSetter("shared_with_groups")
    public void setSharedWithGroups(List<String> shared_with_groups) {
        this.shared_with_groups = shared_with_groups;
    }

    @JsonGetter("only_allow_merge_if_pipeline_succeeds")
    public boolean isOnlyAllowMergeIfPipelineSucceeds() {
        return only_allow_merge_if_pipeline_succeeds;
    }

    public void setOnly_allow_merge_if_pipeline_succeeds(boolean only_allow_merge_if_pipeline_succeeds) {
        this.only_allow_merge_if_pipeline_succeeds = only_allow_merge_if_pipeline_succeeds;
    }

    @JsonGetter("request_access_enabled")
    public boolean isRequestAccessEnabled() {
        return request_access_enabled;
    }

    @JsonSetter("request_access_enabled")
    public void setRequestAccessEnabled(boolean request_access_enabled) {
        this.request_access_enabled = request_access_enabled;
    }

    @JsonGetter("only_allow_merge_if_all_discussions_are_resolved")
    public boolean isOnlyAllowMergeIfAllDiscussionsAreResolved() {
        return only_allow_merge_if_all_discussions_are_resolved;
    }

    public void setOnly_allow_merge_if_all_discussions_are_resolved(boolean only_allow_merge_if_all_discussions_are_resolved) {
        this.only_allow_merge_if_all_discussions_are_resolved = only_allow_merge_if_all_discussions_are_resolved;
    }

    @JsonGetter("printing_merge_request_link_enabled")
    public boolean isPrintingMergeRequestLinkEnabled() {
        return printing_merge_request_link_enabled;
    }

    @JsonSetter("printing_merge_request_link_enabled")
    public void setPrintingMergeRequestLinkEnabled(boolean printing_merge_request_link_enabled) {
        this.printing_merge_request_link_enabled = printing_merge_request_link_enabled;
    }

    @JsonGetter("merge_method")
    public String getMergeMethod() {
        return merge_method;
    }

    @JsonSetter("merge_method")
    public void setMergeMethod(String merge_method) {
        this.merge_method = merge_method;
    }

    @JsonGetter("approvals_before_merge")
    public int getApprovalsBeforeMerge() {
        return approvals_before_merge;
    }

    @JsonSetter("approvals_before_merge")
    public void setApprovalsBeforeMerge(int approvals_before_merge) {
        this.approvals_before_merge = approvals_before_merge;
    }

    @JsonGetter("mirror")
    public boolean isMirror() {
        return mirror;
    }

    @JsonSetter("mirror")
    public void setMirror(boolean mirror) {
        this.mirror = mirror;
    }

    @JsonGetter("_links")
    public Map<String, String> getLinks() {
        return _links;
    }

    @JsonSetter("_links")
    public void setLinks(Map<String, String> _links) {
        this._links = _links;
    }

    @JsonGetter("namespace")
    public Map<String, String> getNamespace() {
        return namespace;
    }

    @JsonSetter("namespace")
    public void setNamespace(Map<String, String> namespace) {
        this.namespace = namespace;
    }

    @JsonGetter("merge_requests_enabled")
    public boolean isMergeRequestsEnabled() {
        return merge_requests_enabled;
    }

    @JsonSetter("merge_requests_enabled")
    public void setMergeRequestsEnabled(boolean merge_requests_enabled) {
        this.merge_requests_enabled = merge_requests_enabled;
    }

    @JsonGetter("external_authorization_classification_label")
    public String getExternalAuthorizationClassificationLabel() {
        return external_authorization_classification_label;
    }

    @JsonSetter("external_authorization_classification_label")
    public void setExternalAuthorizationClassificationLabel(String label) {
        this.external_authorization_classification_label = label;
    }
}
