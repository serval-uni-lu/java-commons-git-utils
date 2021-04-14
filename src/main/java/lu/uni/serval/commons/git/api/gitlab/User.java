package lu.uni.serval.commons.git.api.gitlab;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

public class User extends GitlabEntity {
    private String username;
    private String email;
    private String state;
    private boolean admin;
    private String bio;
    private String bioHtml;
    private String location;
    private String skype;
    private String linkedin;
    private String twitter;
    private String websiteUrl;
    private String organization;
    private String jobTitle;
    private Instant lastSignInAt;
    private Instant confirmedAt;
    private int themeId;
    private LocalDate lastActivityOn;
    private int colorSchemeId;
    private int projectsLimit;
    private Instant currentSignInAt;
    private String note;
    private Set<Identity> identities;
    private boolean canCreateGroup;
    private boolean canCreateProject;
    private boolean twoFactorEnabled;
    private boolean external;
    private boolean privateProfile;
    private String currentSignInIp;
    private String lastSignInIp;

    @JsonGetter("username")
    public String getUsername() {
        return username;
    }

    @JsonSetter("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonGetter("email")
    public String getEmail() {
        return email;
    }

    @JsonSetter("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonGetter("state")
    public String getState() {
        return state;
    }

    @JsonSetter("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonGetter("is_admin")
    public boolean isAdmin() {
        return admin;
    }

    @JsonSetter("is_admin")
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @JsonGetter("bio")
    public String getBio() {
        return bio;
    }

    @JsonSetter("bio")
    public void setBio(String bio) {
        this.bio = bio;
    }

    @JsonGetter("bio_html")
    public String getBioHtml() {
        return bioHtml;
    }

    @JsonSetter("bio_html")
    public void setBioHtml(String bioHtml) {
        this.bioHtml = bioHtml;
    }

    @JsonGetter("location")
    public String getLocation() {
        return location;
    }

    @JsonSetter("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonGetter("skype")
    public String getSkype() {
        return skype;
    }

    @JsonSetter("skype")
    public void setSkype(String skype) {
        this.skype = skype;
    }

    @JsonGetter("linkedin")
    public String getLinkedin() {
        return linkedin;
    }

    @JsonSetter("linkedin")
    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    @JsonSetter("twitter")
    public String getTwitter() {
        return twitter;
    }

    @JsonSetter("twitter")
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @JsonGetter("website_url")
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    @JsonSetter("website_url")
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    @JsonGetter("organization")
    public String getOrganization() {
        return organization;
    }

    @JsonSetter("organization")
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @JsonGetter("job_title")
    public String getJobTitle() {
        return jobTitle;
    }

    @JsonSetter("job_title")
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @JsonGetter("last_sign_in_at")
    public Instant getLastSignInAt(){
        return lastSignInAt;
    }

    @JsonSetter("last_sign_in_at")
    public void setLastSignInAt(Instant lastSignInAt) {
        this.lastSignInAt = lastSignInAt;
    }

    @JsonGetter("confirmed_at")
    public Instant getConfirmedAt(){
        return confirmedAt;
    }

    @JsonSetter("confirmed_at")
    public void setConfirmedAt(Instant confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    @JsonGetter("theme_id")
    public int getThemeId() {
        return themeId;
    }

    @JsonSetter("theme_id")
    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    @JsonSetter("last_activity_on")
    public LocalDate getLastActivityOn(){
        return lastActivityOn;
    }

    @JsonSetter("last_activity_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    public void setLastActivityOn(LocalDate lastActivityOn) {
        this.lastActivityOn = lastActivityOn;
    }

    @JsonGetter("color_scheme_id")
    public int getColorSchemeId() {
        return colorSchemeId;
    }

    @JsonSetter("color_scheme_id")
    public void setColorSchemeId(int colorSchemeId) {
        this.colorSchemeId = colorSchemeId;
    }

    @JsonGetter("projects_limit")
    public int getProjectsLimit() {
        return projectsLimit;
    }

    @JsonSetter("projects_limit")
    public void setProjectsLimit(int projectsLimit) {
        this.projectsLimit = projectsLimit;
    }

    @JsonGetter("current_sign_in_at")
    public Instant getCurrentSignInAt(){
        return currentSignInAt;
    }

    @JsonSetter("current_sign_in_at")
    public void setCurrentSignInAt(Instant currentSignInAt)  {
        this.currentSignInAt = currentSignInAt;
    }

    @JsonGetter("note")
    public String getNote() {
        return note;
    }

    @JsonSetter("note")
    public void setNote(String note) {
        this.note = note;
    }

    @JsonGetter("identities")
    public Set<Identity> getIdentities() {
        return identities;
    }

    @JsonSetter("identities")
    public void setIdentities(Set<Identity> identities) {
        this.identities = identities;
    }

    @JsonGetter("can_create_group")
    public boolean isCanCreateGroup() {
        return canCreateGroup;
    }

    @JsonSetter("can_create_group")
    public void setCanCreateGroup(boolean canCreateGroup) {
        this.canCreateGroup = canCreateGroup;
    }

    @JsonGetter("can_create_project")
    public boolean isCanCreateProject() {
        return canCreateProject;
    }

    @JsonSetter("can_create_project")
    public void setCanCreateProject(boolean canCreateProject) {
        this.canCreateProject = canCreateProject;
    }

    @JsonGetter("two_factor_enabled")
    public boolean isTwoFactorEnabled() {
        return twoFactorEnabled;
    }

    @JsonSetter("two_factor_enabled")
    public void setTwoFactorEnabled(boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
    }

    @JsonGetter("external")
    public boolean isExternal() {
        return external;
    }

    @JsonSetter("external")
    public void setExternal(boolean external) {
        this.external = external;
    }

    @JsonGetter("private_profile")
    public boolean isPrivateProfile() {
        return privateProfile;
    }

    @JsonSetter("private_profile")
    public void setPrivateProfile(boolean privateProfile) {
        this.privateProfile = privateProfile;
    }

    @JsonGetter("current_sign_in_ip")
    public String getCurrentSignInIp() {
        return currentSignInIp;
    }

    @JsonSetter("current_sign_in_ip")
    public void setCurrentSignInIp(String currentSignInIp) {
        this.currentSignInIp = currentSignInIp;
    }

    @JsonGetter("last_sign_in_ip")
    public String getLastSignInIp() {
        return lastSignInIp;
    }

    @JsonSetter("last_sign_in_ip")
    public void setLastSignInIp(String lastSignInIp) {
        this.lastSignInIp = lastSignInIp;
    }
}
