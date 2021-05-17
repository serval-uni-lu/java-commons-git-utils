package lu.uni.serval.commons.git.api.gitlab;

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

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class Group extends GitlabEntity {
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
    private boolean requestAccessEnabled;
    private String fullName;
    private String fullPath;
    private String runnersToken;
    private int fileTemplateProjectId;
    private int parentId;
    private int defaultBranchProtection;
    private List<GroupRef> sharedWithGroups;
    private List<Project> projects;
    private List<Project> sharedProjects;
    private String ldapCn;
    private String ldapAccess;
    private Statistics statistics;

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

    @JsonGetter("runners_token")
    public String getRunnersToken() {
        return runnersToken;
    }

    @JsonSetter("runners_token")
    public void setRunnersToken(String runnersToken) {
        this.runnersToken = runnersToken;
    }

    @JsonGetter("file_template_project_id")
    public int getFileTemplateProjectId() {
        return fileTemplateProjectId;
    }

    @JsonSetter("file_template_project_id")
    public void setFileTemplateProjectId(int fileTemplateProjectId) {
        this.fileTemplateProjectId = fileTemplateProjectId;
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

    @JsonGetter("shared_with_groups")
    public List<GroupRef> getSharedWithGroups() {
        return sharedWithGroups;
    }

    @JsonSetter("shared_with_groups")
    public void setSharedWithGroups(List<GroupRef> sharedWithGroups) {
        this.sharedWithGroups = sharedWithGroups;
    }

    @JsonGetter("projects")
    public List<Project> getProjects() {
        return projects;
    }

    @JsonSetter("projects")
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @JsonGetter("shared_projects")
    public List<Project> getSharedProjects() {
        return sharedProjects;
    }

    @JsonSetter("shared_projects")
    public void setSharedProjects(List<Project> sharedProjects) {
        this.sharedProjects = sharedProjects;
    }

    @JsonGetter("statistics")
    public Statistics getStatistics() {
        return statistics;
    }

    @JsonSetter("statistics")
    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
}
