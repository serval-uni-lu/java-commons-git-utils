import java.time.LocalDateTime;
import java.util.List;

public class Project {
    private int id;
    private String description;
    private String name;
    private String name_with_namespace;
    private String path;
    private String path_with_namespace;
    private LocalDateTime created_at;
    private String default_branch;
    private String tag_list;
    private String ssh_url_to_repo;
    private String http_url_to_repo;
    private String web_url;
    private String readme_url;
    private String avatar_url;
    private String star_count;
    private String forks_count;
    private LocalDateTime last_activity_at;
    private boolean archived;
    private String visibility;
    private boolean resolve_outdated_diff_discussions;
    private boolean container_registry_enabled;
    private boolean issues_enabled;
    private boolean wiki_enabled;
    private boolean jobs_enabled;
    private boolean snippets_enabled;
    private boolean shared_runners_enabled;
    private boolean lfs_enabled;
    private int creator_id;
    private String import_status;
    private int open_issues_count;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_with_namespace() {
        return name_with_namespace;
    }

    public void setName_with_namespace(String name_with_namespace) {
        this.name_with_namespace = name_with_namespace;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath_with_namespace() {
        return path_with_namespace;
    }

    public void setPath_with_namespace(String path_with_namespace) {
        this.path_with_namespace = path_with_namespace;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getDefault_branch() {
        return default_branch;
    }

    public void setDefault_branch(String default_branch) {
        this.default_branch = default_branch;
    }

    public String getTag_list() {
        return tag_list;
    }

    public void setTag_list(String tag_list) {
        this.tag_list = tag_list;
    }

    public String getSsh_url_to_repo() {
        return ssh_url_to_repo;
    }

    public void setSsh_url_to_repo(String ssh_url_to_repo) {
        this.ssh_url_to_repo = ssh_url_to_repo;
    }

    public String getHttp_url_to_repo() {
        return http_url_to_repo;
    }

    public void setHttp_url_to_repo(String http_url_to_repo) {
        this.http_url_to_repo = http_url_to_repo;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getReadme_url() {
        return readme_url;
    }

    public void setReadme_url(String readme_url) {
        this.readme_url = readme_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getStar_count() {
        return star_count;
    }

    public void setStar_count(String star_count) {
        this.star_count = star_count;
    }

    public String getForks_count() {
        return forks_count;
    }

    public void setForks_count(String forks_count) {
        this.forks_count = forks_count;
    }

    public LocalDateTime getLast_activity_at() {
        return last_activity_at;
    }

    public void setLast_activity_at(LocalDateTime last_activity_at) {
        this.last_activity_at = last_activity_at;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public boolean isResolve_outdated_diff_discussions() {
        return resolve_outdated_diff_discussions;
    }

    public void setResolve_outdated_diff_discussions(boolean resolve_outdated_diff_discussions) {
        this.resolve_outdated_diff_discussions = resolve_outdated_diff_discussions;
    }

    public boolean isContainer_registry_enabled() {
        return container_registry_enabled;
    }

    public void setContainer_registry_enabled(boolean container_registery_enabled) {
        this.container_registry_enabled = container_registery_enabled;
    }

    public boolean isIssues_enabled() {
        return issues_enabled;
    }

    public void setIssues_enabled(boolean issues_enabled) {
        this.issues_enabled = issues_enabled;
    }

    public boolean isWiki_enabled() {
        return wiki_enabled;
    }

    public void setWiki_enabled(boolean wiki_enabled) {
        this.wiki_enabled = wiki_enabled;
    }

    public boolean isJobs_enabled() {
        return jobs_enabled;
    }

    public void setJobs_enabled(boolean jobs_enabled) {
        this.jobs_enabled = jobs_enabled;
    }

    public boolean isSnippets_enabled() {
        return snippets_enabled;
    }

    public void setSnippets_enabled(boolean snippets_enabled) {
        this.snippets_enabled = snippets_enabled;
    }

    public boolean isShared_runners_enabled() {
        return shared_runners_enabled;
    }

    public void setShared_runners_enabled(boolean shared_runners_enabled) {
        this.shared_runners_enabled = shared_runners_enabled;
    }

    public boolean isLfs_enabled() {
        return lfs_enabled;
    }

    public void setLfs_enabled(boolean lfs_enabled) {
        this.lfs_enabled = lfs_enabled;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public String getImport_status() {
        return import_status;
    }

    public void setImport_status(String import_status) {
        this.import_status = import_status;
    }

    public int getOpen_issues_count() {
        return open_issues_count;
    }

    public void setOpen_issues_count(int open_issues_count) {
        this.open_issues_count = open_issues_count;
    }

    public boolean isPublic_jobs() {
        return public_jobs;
    }

    public void setPublic_jobs(boolean public_jobs) {
        this.public_jobs = public_jobs;
    }

    public String getCi_config_path() {
        return ci_config_path;
    }

    public void setCi_config_path(String ci_config_path) {
        this.ci_config_path = ci_config_path;
    }

    public List<String> getShared_with_groups() {
        return shared_with_groups;
    }

    public void setShared_with_groups(List<String> shared_with_groups) {
        this.shared_with_groups = shared_with_groups;
    }

    public boolean isOnly_allow_merge_if_pipeline_succeeds() {
        return only_allow_merge_if_pipeline_succeeds;
    }

    public void setOnly_allow_merge_if_pipeline_succeeds(boolean only_allow_merge_if_pipeline_succeeds) {
        this.only_allow_merge_if_pipeline_succeeds = only_allow_merge_if_pipeline_succeeds;
    }

    public boolean isRequest_access_enabled() {
        return request_access_enabled;
    }

    public void setRequest_access_enabled(boolean request_access_enabled) {
        this.request_access_enabled = request_access_enabled;
    }

    public boolean isOnly_allow_merge_if_all_discussions_are_resolved() {
        return only_allow_merge_if_all_discussions_are_resolved;
    }

    public void setOnly_allow_merge_if_all_discussions_are_resolved(boolean only_allow_merge_if_all_discussions_are_resolved) {
        this.only_allow_merge_if_all_discussions_are_resolved = only_allow_merge_if_all_discussions_are_resolved;
    }

    public boolean isPrinting_merge_request_link_enabled() {
        return printing_merge_request_link_enabled;
    }

    public void setPrinting_merge_request_link_enabled(boolean printing_merge_request_link_enabled) {
        this.printing_merge_request_link_enabled = printing_merge_request_link_enabled;
    }

    public String getMerge_method() {
        return merge_method;
    }

    public void setMerge_method(String merge_method) {
        this.merge_method = merge_method;
    }

    public int getApprovals_before_merge() {
        return approvals_before_merge;
    }

    public void setApprovals_before_merge(int approvals_before_merge) {
        this.approvals_before_merge = approvals_before_merge;
    }

    public boolean isMirror() {
        return mirror;
    }

    public void setMirror(boolean mirror) {
        this.mirror = mirror;
    }
}
