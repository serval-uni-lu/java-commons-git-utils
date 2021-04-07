package lu.uni.serval.commons.git.api.gitlab;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class Project {
    private int id;
    private String description;
    private String name;
    private String nameWithNamespace;
    private String path;
    private String pathWithNamespace;
    private String createdAt;
    private String defaultBranch;
    private List<String> tagList;
    private String sshUrlToRepo;
    private String httpUrlToRepo;
    private String webUrl;
    private String readmeUrl;
    private String avatarUrl;
    private int starCount;
    private int forksCount;
    private String lastActivityAt;
    private Namespace namespace;
    private String containerRegistryImagePrefix;
    private Links links;
    private boolean emptyRepo;
    private boolean archived;
    private String visibility;
    private Owner owner;
    private boolean resolveOutdatedDiffDiscussions;
    private boolean containerRegistryEnabled;
    private boolean issuesEnabled;
    private boolean mergeRequestsEnabled;
    private boolean wikiEnabled;
    private boolean jobsEnabled;
    private boolean snippetsEnabled;
    private boolean canCreateMergeRequestIn;
    private String pagesAccessLevel;
    private boolean emailsDisabled;
    private String issuesAccessLevel;
    private String repositoryAccessLevel;
    private String mergeRequestsAccessLevel;
    private String forkingAccessLevel;
    private String wikiAccessLevel;
    private String buildsAccessLevel;
    private String snippetsAccessLevel;
    private boolean sharedRunnersEnabled;
    private boolean lfsEnabled;
    private int creatorId;
    private String importStatus;
    private int openIssuesCount;
    private int ciDefaultGitDepth;
    private boolean publicJobs;
    private int buildTimeout;
    private String autoCancelPendingPipelines;
    private String buildCoverageRegex;
    private String ciConfigPath;
    private List<GroupRef> sharedWithGroups;
    private boolean onlyAllowMergeIfPipelineSucceeds;
    private boolean allowMergeOnSkippedPipeline;
    private boolean requestAccessEnabled;
    private boolean onlyAllowMergeIfAllDiscussionsAreResolved;
    private boolean removeSourceBranchAfterMerge;
    private boolean printingMergeRequestLinkEnabled;
    private String mergeMethod;
    private boolean autocloseReferencedIssues;
    private String suggestionCommitMessage;
    private String markedForDeletionOn;
    private List<String> complianceFrameworks;
    private boolean autoDevopsEnabled;
    private String autoDevopsDeployStrategy;
    private int approvalsBeforeMerge;
    private boolean mirror;
    private int mirrorUserId;
    private boolean mirrorTriggerBuilds;
    private boolean onlyMirrorProtectedBranches;
    private boolean mirrorOverwritesDivergedBranches;
    private String externalAuthorizationClassificationLabel;
    private boolean packagesEnabled;
    private boolean serviceDeskEnabled;
    private String serviceDeskAddress;
    private String operationsAccessLevel;
    private String analyticsAccessLevel;
    private boolean ciForwardDeploymentEnabled;
    private boolean restrictUserDefinedVariables;
    private boolean requirementsEnabled;
    private boolean securityAndComplianceEnabled;

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
        return nameWithNamespace;
    }

    @JsonSetter("name_with_namespace")
    public void setNameWithNamespace(String nameWithNamespace) {
        this.nameWithNamespace = nameWithNamespace;
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
        return pathWithNamespace;
    }

    public void setPathWithNamespace(String pathWithNamespace) {
        this.pathWithNamespace = pathWithNamespace;
    }

    @JsonGetter("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonSetter("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonGetter("default_branch")
    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    @JsonGetter("tag_list")
    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    @JsonGetter("ssh_url_to_repo")
    public String getSshUrlToRepo() {
        return sshUrlToRepo;
    }

    public void setSshUrlToRepo(String sshUrlToRepo) {
        this.sshUrlToRepo = sshUrlToRepo;
    }

    @JsonGetter("http_url_to_repo")
    public String getHttpUrlToRepo() {
        return httpUrlToRepo;
    }

    @JsonSetter("http_url_to_repo")
    public void setHttpUrlToRepo(String httpUrlToRepo) {
        this.httpUrlToRepo = httpUrlToRepo;
    }

    @JsonGetter("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @JsonGetter("readme_url")
    public String getReadmeUrl() {
        return readmeUrl;
    }

    @JsonSetter("readme_url")
    public void setReadmeUrl(String readmeUrl) {
        this.readmeUrl = readmeUrl;
    }

    @JsonGetter("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonGetter("star_count")
    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    @JsonGetter("forks_count")
    public int getForksCount() {
        return forksCount;
    }

    @JsonSetter("forks_count")
    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    @JsonGetter("last_activity_at")
    public String getLastActivityAt() {
        return lastActivityAt;
    }

    @JsonSetter("last_activity_at")
    public void setLastActivityAt(String lastActivityAt) {
        this.lastActivityAt = lastActivityAt;
    }

    @JsonGetter("empty_repo")
    public boolean isEmptyRepo() {
        return emptyRepo;
    }

    @JsonSetter("empty_repo")
    public void setEmptyRepo(boolean emptyRepo) {
        this.emptyRepo = emptyRepo;
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

    @JsonGetter("owner")
    public Owner getOwner() {
        return owner;
    }

    @JsonSetter("owner")
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @JsonGetter("resolve_outdated_diff_discussions")
    public boolean isResolveOutdatedDiffDiscussions() {
        return resolveOutdatedDiffDiscussions;
    }

    @JsonSetter("resolve_outdated_diff_discussions")
    public void setResolveOutdatedDiffDiscussions(boolean resolveOutdatedDiffDiscussions) {
        this.resolveOutdatedDiffDiscussions = resolveOutdatedDiffDiscussions;
    }

    @JsonGetter("container_registry_enabled")
    public boolean isContainerRegistryEnabled() {
        return containerRegistryEnabled;
    }

    @JsonSetter("container_registry_enabled")
    public void setContainerRegistryEnabled(boolean containerRegistryEnabled) {
        this.containerRegistryEnabled = containerRegistryEnabled;
    }

    @JsonGetter("issues_enabled")
    public boolean isIssuesEnabled() {
        return issuesEnabled;
    }

    @JsonSetter("issues_enabled")
    public void setIssuesEnabled(boolean issuesEnabled) {
        this.issuesEnabled = issuesEnabled;
    }

    @JsonGetter("wiki_enabled")
    public boolean isWikiEnabled() {
        return wikiEnabled;
    }

    @JsonSetter("wiki_enabled")
    public void setWikiEnabled(boolean wikiEnabled) {
        this.wikiEnabled = wikiEnabled;
    }

    @JsonGetter("jobs_enabled")
    public boolean isJobsEnabled() {
        return jobsEnabled;
    }

    public void setJobsEnabled(boolean jobsEnabled) {
        this.jobsEnabled = jobsEnabled;
    }

    @JsonGetter("snippets_enabled")
    public boolean isSnippetsEnabled() {
        return snippetsEnabled;
    }

    @JsonSetter("snippets_enabled")
    public void setSnippetsEnabled(boolean snippetsEnabled) {
        this.snippetsEnabled = snippetsEnabled;
    }

    @JsonGetter("can_create_merge_request_in")
    public boolean isCanCreateMergeRequestIn() {
        return canCreateMergeRequestIn;
    }

    @JsonSetter("can_create_merge_request_in")
    public void setCanCreateMergeRequestIn(boolean canCreateMergeRequestIn) {
        this.canCreateMergeRequestIn = canCreateMergeRequestIn;
    }

    @JsonGetter("pages_access_level")
    public String getPagesAccessLevel() {
        return pagesAccessLevel;
    }

    @JsonSetter("pages_access_level")
    public void setPagesAccessLevel(String pagesAccessLevel) {
        this.pagesAccessLevel = pagesAccessLevel;
    }

    @JsonGetter("emails_disabled")
    public boolean isEmailsDisabled() {
        return emailsDisabled;
    }

    @JsonSetter("emails_disabled")
    public void setEmailsDisabled(boolean emailsDisabled) {
        this.emailsDisabled = emailsDisabled;
    }

    @JsonGetter("shared_runners_enabled")
    public boolean isSharedRunnersEnabled() {
        return sharedRunnersEnabled;
    }

    @JsonSetter("shared_runners_enabled")
    public void setSharedRunnersEnabled(boolean sharedRunnersEnabled) {
        this.sharedRunnersEnabled = sharedRunnersEnabled;
    }

    @JsonGetter("lfs_enabled")
    public boolean isLfsEnabled() {
        return lfsEnabled;
    }

    public void setLfsEnabled(boolean lfsEnabled) {
        this.lfsEnabled = lfsEnabled;
    }

    @JsonGetter("creator_id")
    public int getCreatorId() {
        return creatorId;
    }

    @JsonSetter("creator_id")
    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    @JsonGetter("import_status")
    public String getImportStatus() {
        return importStatus;
    }

    @JsonSetter("import_status")
    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }

    @JsonSetter("issues_access_level")
    public void setIssuesAccessLevel(String issuesAccessLevel){
        this.issuesAccessLevel = issuesAccessLevel;
    }

    @JsonGetter("issues_access_level")
    public String getIssuesAccessLevel() {
        return issuesAccessLevel;
    }

    @JsonSetter("repository_access_level")
    public void setRepositoryAccessLevel(String repositoryAccessLevel){
        this.repositoryAccessLevel = repositoryAccessLevel;
    }

    @JsonGetter("repository_access_level")
    public String getRepositoryAccessLevel() {
        return repositoryAccessLevel;
    }

    @JsonSetter("merge_requests_access_level")
    public void setMergeRequestsAccessLevel(String mergeRequestsAccessLevel){
        this.mergeRequestsAccessLevel = mergeRequestsAccessLevel;
    }

    @JsonGetter("merge_requests_access_level")
    public String getMergeRequestsAccessLevel() {
        return mergeRequestsAccessLevel;
    }

    @JsonSetter("forking_access_level")
    public void setForkingAccessLevel(String forkingAccessLevel){
        this.forkingAccessLevel = forkingAccessLevel;
    }

    @JsonGetter("forking_access_level")
    public String getForkingAccessLevel() {
        return forkingAccessLevel;
    }

    @JsonSetter("builds_access_level")
    public void setBuildsAccessLevel(String buildsAccessLevel){
        this.buildsAccessLevel = buildsAccessLevel;
    }

    @JsonGetter("builds_access_level")
    public String getBuildsAccessLevel() {
        return buildsAccessLevel;
    }

    @JsonSetter("wiki_access_level")
    public void setWikiAccessLevel(String wikiAccessLevel){
        this.wikiAccessLevel = wikiAccessLevel;
    }

    @JsonGetter("wiki_access_level")
    public String getWikiAccessLevel() {
        return wikiAccessLevel;
    }

    @JsonSetter("snippets_access_level")
    public void setSnippetsAccessLevel(String snippetsAccessLevel){
        this.snippetsAccessLevel = snippetsAccessLevel;
    }

    @JsonGetter("snippets_access_level")
    public String getSnippetsAccessLevel() {
        return snippetsAccessLevel;
    }

    @JsonSetter("build_timeout")
    public void setBuildTimeout(int buildTimeout){
        this.buildTimeout = buildTimeout;
    }

    @JsonGetter("build_timeout")
    public int getBuildTimeout() {
        return buildTimeout;
    }

    @JsonSetter("build_coverage_regex")
    public void setBuildCoverageRegex(String buildCoverageRegex){
        this.buildCoverageRegex = buildCoverageRegex;
    }

    @JsonGetter("build_coverage_regex")
    public String getBuildCoverageRegex() {
        return buildCoverageRegex;
    }

    @JsonGetter("open_issues_count")
    public int getOpenIssuesCount() {
        return openIssuesCount;
    }

    @JsonSetter("open_issues_count")
    public void setOpenIssuesCount(int openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    @JsonGetter("ci_default_git_depth")
    public int getCiDefaultGitDepth() {
        return ciDefaultGitDepth;
    }

    @JsonSetter("ci_default_git_depth")
    public void setCiDefaultGitDepth(int ciDefaultGitDepth) {
        this.ciDefaultGitDepth = ciDefaultGitDepth;
    }

    @JsonGetter("public_jobs")
    public boolean isPublicJobs() {
        return publicJobs;
    }

    @JsonSetter("public_jobs")
    public void setPublicJobs(boolean publicJobs) {
        this.publicJobs = publicJobs;
    }

    @JsonGetter("ci_config_path")
    public String getCiConfigPath() {
        return ciConfigPath;
    }

    @JsonSetter("ci_config_path")
    public void setCiConfigPath(String ciConfigPath) {
        this.ciConfigPath = ciConfigPath;
    }

    @JsonGetter("shared_with_groups")
    public List<GroupRef> getSharedWithGroups() {
        return sharedWithGroups;
    }

    @JsonSetter("shared_with_groups")
    public void setSharedWithGroups(List<GroupRef> sharedWithGroups) {
        this.sharedWithGroups = sharedWithGroups;
    }

    @JsonGetter("only_allow_merge_if_pipeline_succeeds")
    public boolean isOnlyAllowMergeIfPipelineSucceeds() {
        return onlyAllowMergeIfPipelineSucceeds;
    }

    @JsonSetter("only_allow_merge_if_pipeline_succeeds")
    public void setOnlyAllowMergeIfPipelineSucceeds(boolean onlyAllowMergeIfPipelineSucceeds) {
        this.onlyAllowMergeIfPipelineSucceeds = onlyAllowMergeIfPipelineSucceeds;
    }

    @JsonGetter("allow_merge_on_skipped_pipeline")
    public boolean getAllowMergeOnSkippedPipeline() {
        return allowMergeOnSkippedPipeline;
    }

    @JsonSetter("allow_merge_on_skipped_pipeline")
    public void setAllowMergeOnSkippedPipeline(boolean allowMergeOnSkippedPipeline) {
        this.allowMergeOnSkippedPipeline = allowMergeOnSkippedPipeline;
    }

    @JsonGetter("request_access_enabled")
    public boolean isRequestAccessEnabled() {
        return requestAccessEnabled;
    }

    @JsonSetter("request_access_enabled")
    public void setRequestAccessEnabled(boolean requestAccessEnabled) {
        this.requestAccessEnabled = requestAccessEnabled;
    }

    @JsonGetter("only_allow_merge_if_all_discussions_are_resolved")
    public boolean isOnlyAllowMergeIfAllDiscussionsAreResolved() {
        return onlyAllowMergeIfAllDiscussionsAreResolved;
    }

    @JsonSetter("only_allow_merge_if_all_discussions_are_resolved")
    public void setOnlyAllowMergeIfAllDiscussionsAreResolved(boolean onlyAllowMergeIfAllDiscussionsAreResolved) {
        this.onlyAllowMergeIfAllDiscussionsAreResolved = onlyAllowMergeIfAllDiscussionsAreResolved;
    }

    @JsonGetter("remove_source_branch_after_merge")
    public boolean isRemoveSourceBranchAfterMerge(){
        return removeSourceBranchAfterMerge;
    }

    @JsonSetter("remove_source_branch_after_merge")
    public void setRemoveSourceBranchAfterMerge(boolean removeSourceBranchAfterMerge){
        this.removeSourceBranchAfterMerge = removeSourceBranchAfterMerge;
    }

    @JsonGetter("printing_merge_request_link_enabled")
    public boolean isPrintingMergeRequestLinkEnabled() {
        return printingMergeRequestLinkEnabled;
    }

    @JsonSetter("printing_merge_request_link_enabled")
    public void setPrintingMergeRequestLinkEnabled(boolean printingMergeRequestLinkEnabled) {
        this.printingMergeRequestLinkEnabled = printingMergeRequestLinkEnabled;
    }

    @JsonGetter("merge_method")
    public String getMergeMethod() {
        return mergeMethod;
    }

    @JsonSetter("merge_method")
    public void setMergeMethod(String merge_method) {
        this.mergeMethod = merge_method;
    }

    @JsonGetter("autoclose_referenced_issues")
    public boolean isAutocloseReferencedIssues() {
        return autocloseReferencedIssues;
    }

    @JsonSetter("autoclose_referenced_issues")
    public void setAutocloseReferencedIssues(boolean autocloseReferencedIssues) {
        this.autocloseReferencedIssues = autocloseReferencedIssues;
    }

    @JsonGetter("suggestion_commit_message")
    public String getSuggestionCommitMessage() {
        return suggestionCommitMessage;
    }

    @JsonSetter("suggestion_commit_message")
    public void isSuggestionCommitMessage(String suggestionCommitMessage) {
        this.suggestionCommitMessage = suggestionCommitMessage;
    }

    @JsonGetter("marked_for_deletion_on")
    public String getMarkedForDeletionOn() {
        return markedForDeletionOn;
    }

    @JsonSetter("marked_for_deletion_on")
    public void setMarkedForDeletionOn(String markedForDeletionOn) {
        this.markedForDeletionOn = markedForDeletionOn;
    }

    @JsonGetter("compliance_frameworks")
    public List<String> getComplianceFrameworks() {
        return complianceFrameworks;
    }

    @JsonSetter("compliance_frameworks")
    public void setComplianceFrameworks(List<String> complianceFrameworks) {
        this.complianceFrameworks = complianceFrameworks;
    }

    @JsonGetter("approvals_before_merge")
    public int getApprovalsBeforeMerge() {
        return approvalsBeforeMerge;
    }

    @JsonSetter("approvals_before_merge")
    public void setApprovalsBeforeMerge(int approvalsBeforeMerge) {
        this.approvalsBeforeMerge = approvalsBeforeMerge;
    }

    @JsonGetter("mirror")
    public boolean isMirror() {
        return mirror;
    }

    @JsonSetter("mirror")
    public void setMirror(boolean mirror) {
        this.mirror = mirror;
    }

    @JsonGetter("mirror_user_id")
    public int getMirrorUserId(){
        return mirrorUserId;
    }

    @JsonSetter("mirror_user_id")
    public void setMirrorUserId(int mirrorUserId){
        this.mirrorUserId = mirrorUserId;
    }

    @JsonGetter("mirror_trigger_builds")
    public boolean getMirrorTriggerBuilds(){
        return mirrorTriggerBuilds;
    }

    @JsonSetter("mirror_trigger_builds")
    public void setMirrorTriggerBuilds(boolean mirrorTriggerBuilds){
        this.mirrorTriggerBuilds = mirrorTriggerBuilds;
    }

    @JsonGetter("only_mirror_protected_branches")
    public boolean getOnlyMirrorProtectedBranches(){
        return onlyMirrorProtectedBranches;
    }

    @JsonSetter("only_mirror_protected_branches")
    public void setOnlyMirrorProtectedBranches(boolean onlyMirrorProtectedBranches){
        this.onlyMirrorProtectedBranches = onlyMirrorProtectedBranches;
    }

    @JsonGetter("mirror_overwrites_diverged_branches")
    public boolean getMirrorOverwritesDivergedBranches(){
        return mirrorOverwritesDivergedBranches;
    }

    @JsonSetter("mirror_overwrites_diverged_branches")
    public void setMirrorOverwritesDivergedBranches(boolean mirrorOverwritesDivergedBranches){
        this.mirrorOverwritesDivergedBranches = mirrorOverwritesDivergedBranches;
    }

    @JsonGetter("container_registry_image_prefix")
    public String getContainerRegistryImagePrefix() {
        return containerRegistryImagePrefix;
    }

    @JsonSetter("container_registry_image_prefix")
    public void setContainerRegistryImagePrefix(String containerRegistryImagePrefix) {
        this.containerRegistryImagePrefix = containerRegistryImagePrefix;
    }

    @JsonGetter("_links")
    public Links getLinks() {
        return links;
    }

    @JsonSetter("_links")
    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonGetter("namespace")
    public Namespace getNamespace() {
        return namespace;
    }

    @JsonSetter("namespace")
    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
    }

    @JsonGetter("merge_requests_enabled")
    public boolean isMergeRequestsEnabled() {
        return mergeRequestsEnabled;
    }

    @JsonSetter("merge_requests_enabled")
    public void setMergeRequestsEnabled(boolean mergeRequestsEnabled) {
        this.mergeRequestsEnabled = mergeRequestsEnabled;
    }

    @JsonGetter("external_authorization_classification_label")
    public String getExternalAuthorizationClassificationLabel() {
        return externalAuthorizationClassificationLabel;
    }

    @JsonSetter("external_authorization_classification_label")
    public void setExternalAuthorizationClassificationLabel(String label) {
        this.externalAuthorizationClassificationLabel = label;
    }

    @JsonGetter("auto_cancel_pending_pipelines")
    public String getAutoCancelPendingPipelines() {
        return autoCancelPendingPipelines;
    }

    @JsonSetter("auto_cancel_pending_pipelines")
    public void setAutoCancelPendingPipelines(String autoCancelPendingPipelines) {
        this.autoCancelPendingPipelines = autoCancelPendingPipelines;
    }

    @JsonGetter("auto_devops_enabled")
    public boolean getAutoDevopsEnabled(){
        return autoDevopsEnabled;
    }

    @JsonSetter("auto_devops_enabled")
    public void setAutoDevopsEnabled(boolean autoDevopsEnabled){
        this.autoDevopsEnabled = autoDevopsEnabled;
    }

    @JsonGetter("auto_devops_deploy_strategy")
    public String getAutoDevopsDeployStrategy(){
        return autoDevopsDeployStrategy;
    }

    @JsonSetter("auto_devops_deploy_strategy")
    public void setAutoDevopsDeployStrategy(String autoDevopsDeployStrategy){
        this.autoDevopsDeployStrategy = autoDevopsDeployStrategy;
    }

    @JsonGetter("packages_enabled")
    public boolean isPackagesEnabled(){
        return packagesEnabled;
    }

    @JsonSetter("packages_enabled")
    public void setPackagesEnabled(boolean packageEnabled){
        this.packagesEnabled = packageEnabled;
    }

    @JsonGetter("service_desk_enabled")
    public boolean isServiceDeskEnabled(){
        return serviceDeskEnabled;
    }

    @JsonSetter("service_desk_enabled")
    public void setServiceDeskEnabled(boolean serviceDeskEnabled){
        this.serviceDeskEnabled = serviceDeskEnabled;
    }

    @JsonGetter("service_desk_address")
    public String setServiceDeskAddress(){
        return serviceDeskAddress;
    }

    @JsonSetter("service_desk_address")
    public void setServiceDeskAddress(String serviceDeskAddress){
        this.serviceDeskAddress = serviceDeskAddress;
    }

    @JsonGetter("operations_access_level")
    public String getOperationsAccessLevel(){
        return operationsAccessLevel;
    }

    @JsonSetter("operations_access_level")
    public void setOperationsAccessLevel(String operationsAccessLevel){
        this.operationsAccessLevel = operationsAccessLevel;
    }

    @JsonGetter("analytics_access_level")
    public String getAnalyticsAccessLevel(){
        return analyticsAccessLevel;
    }

    @JsonSetter("analytics_access_level")
    public void setAnalyticsAccessLevel(String analyticsAccessLevel){
        this.analyticsAccessLevel = analyticsAccessLevel;
    }

    @JsonGetter("ci_forward_deployment_enabled")
    public boolean isCiForwardDeploymentEnabled(){
        return ciForwardDeploymentEnabled;
    }

    @JsonSetter("ci_forward_deployment_enabled")
    public void setCiForwardDeploymentEnabled(boolean ciForwardDeploymentEnabled){
        this.ciForwardDeploymentEnabled = ciForwardDeploymentEnabled;
    }

    @JsonGetter("restrict_user_defined_variables")
    public boolean isRestrictUserDefinedVariables(){
        return restrictUserDefinedVariables;
    }

    @JsonSetter("restrict_user_defined_variables")
    public void setRestrictUserDefinedVariables(boolean restrictUserDefinedVariables){
        this.restrictUserDefinedVariables = restrictUserDefinedVariables;
    }

    @JsonGetter("requirements_enabled")
    public boolean isRequirementsEnabled(){
        return requirementsEnabled;
    }

    @JsonSetter("requirements_enabled")
    public void setRequirementsEnabled(boolean requirementsEnabled){
        this.requirementsEnabled = requirementsEnabled;
    }

    @JsonGetter("security_and_compliance_enabled")
    public boolean isSecurityAndComplianceEnabled(){
        return securityAndComplianceEnabled;
    }

    @JsonSetter("security_and_compliance_enabled")
    public void setSecurityAndComplianceEnabled(boolean securityAndComplianceEnabled){
        this.securityAndComplianceEnabled = securityAndComplianceEnabled;
    }
}
