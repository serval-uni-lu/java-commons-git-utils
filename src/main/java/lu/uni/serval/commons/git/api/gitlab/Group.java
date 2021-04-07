package lu.uni.serval.commons.git.api.gitlab;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Group {
    private int id;
    private String name;
    private String path;
    private String description;
    private String visibility;
    private boolean shareWithGroupLock;
    private boolean requireTwoFactorAuthentication;
    private int twoFactorGracePeriod;
    private String projectCreationLevel;
    private String autoDevopsEnabled;
    private String subgroupCreationLevel;
    private String emailsDisabled;
    private String mentionsDisabled;
    private boolean lfsEnabled;
    private String avatarUrl;
    private String webUrl;
    private boolean requestAccessEnabled;
    private String fullName;
    private String fullPath;
    private int parentId;
    private int defaultBranchProtection;
    private String createdAt;

    private String ldapCn;
    private String ldapAccess;

    @JsonGetter("id")
    public int getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonSetter("web_url")
    public void setWebUrl(String web_url) {
        this.webUrl = web_url;
    }

    @JsonGetter("path")
    public String getPath() {
        return path;
    }

    @JsonSetter("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonGetter("description")
    public String getDescription() {
        return description;
    }

    @JsonSetter("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonGetter("share_with_group_lock")
    public boolean isShareWithGroupLock(){
        return this.shareWithGroupLock;
    }

    @JsonSetter("share_with_group_lock")
    public void setShareWithGroupLock(boolean shareWithGroupLock){
        this.shareWithGroupLock = shareWithGroupLock;
    }

    @JsonGetter("require_two_factor_authentication")
    public boolean isRequireTwoFactorAuthentication(){
        return this.requireTwoFactorAuthentication;
    }

    @JsonSetter("require_two_factor_authentication")
    public void setRequireTwoFactorAuthentication(boolean requireTwoFactorAuthentication){
        this.requireTwoFactorAuthentication = requireTwoFactorAuthentication;
    }

    @JsonGetter("two_factor_grace_period")
    public int getTwoFactorGracePeriod(){
        return this.twoFactorGracePeriod;
    }

    @JsonSetter("two_factor_grace_period")
    public void setTwoFactorGracePeriod(int twoFactorGracePeriod){
        this.twoFactorGracePeriod = twoFactorGracePeriod;
    }

    @JsonGetter("project_creation_level")
    public String getProjectCreationLevel(){
        return this.projectCreationLevel;
    }

    @JsonSetter("project_creation_level")
    public void setProjectCreationLevel(String projectCreationLevel){
        this.projectCreationLevel = projectCreationLevel;
    }

    @JsonGetter("auto_devops_enabled")
    public String getAutoDevopsEnabled(){
        return this.autoDevopsEnabled;
    }

    @JsonSetter("auto_devops_enabled")
    public void setAutoDevopsEnabled(String autoDevopsEnabled){
        this.autoDevopsEnabled = autoDevopsEnabled;
    }

    @JsonGetter("subgroup_creation_level")
    public String getSubgroupCreationLevel(){
        return this.subgroupCreationLevel;
    }

    @JsonSetter("subgroup_creation_level")
    public void setSubgroupCreationLevel(String subgroupCreationLevel){
        this.subgroupCreationLevel = subgroupCreationLevel;
    }

    @JsonGetter("emails_disabled")
    public String getEmailsDisabled(){
        return this.emailsDisabled;
    }

    @JsonSetter("emails_disabled")
    public void setEmailsDisabled(String emailsDisabled){
        this.emailsDisabled = emailsDisabled;
    }

    @JsonGetter("mentions_disabled")
    public String getMentionsDisabled(){
        return this.mentionsDisabled;
    }

    @JsonSetter("mentions_disabled")
    public void setMentionsDisabled(String mentionsDisabled){
        this.mentionsDisabled = mentionsDisabled;
    }

    @JsonGetter("visibility")
    public String getVisibility() {
        return visibility;
    }

    @JsonSetter("visibility")
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @JsonGetter("lfs_enabled")
    public boolean isLfsEnabled() {
        return lfsEnabled;
    }

    @JsonSetter("lfs_enabled")
    public void setLfsEnabled(boolean lfsEnabled) {
        this.lfsEnabled = lfsEnabled;
    }

    @JsonGetter("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @JsonSetter("avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonGetter("request_access_enabled")
    public boolean isRequestAccessEnabled() {
        return requestAccessEnabled;
    }

    @JsonSetter("request_access_enabled")
    public void setRequestAccessEnabled(boolean requestAccessEnabled) {
        this.requestAccessEnabled = requestAccessEnabled;
    }

    @JsonGetter("full_name")
    public String getFullName() {
        return fullName;
    }

    @JsonSetter("full_name")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonGetter("full_path")
    public String getFullPath() {
        return fullPath;
    }

    @JsonSetter("full_path")
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    @JsonGetter("parent_id")
    public int getParentId() {
        return parentId;
    }

    @JsonSetter("parent_id")
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @JsonGetter("ldap_cn")
    public String getLdapCn() {
        return ldapCn;
    }

    @JsonSetter("ldap_cn")
    public void setLdapCn(String ldapCn) {
        this.ldapCn = ldapCn;
    }

    @JsonGetter("ldap_access")
    public String getLdapAccess() {
        return ldapAccess;
    }

    @JsonSetter("ldap_access")
    public void setLdapAccess(String ldapAccess) {
        this.ldapAccess = ldapAccess;
    }

    @JsonGetter("default_branch_protection")
    public int getDefaultBranchProtection(){
        return defaultBranchProtection;
    }

    @JsonSetter("default_branch_protection")
    public void setDefaultBranchProtection(int defaultBranchProtection){
        this.defaultBranchProtection = defaultBranchProtection;
    }

    @JsonGetter("created_at")
    public String getCreatedAt(){
        return createdAt;
    }

    @JsonSetter("created_at")
    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }
}
