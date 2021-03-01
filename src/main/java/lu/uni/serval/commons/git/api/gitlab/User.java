package lu.uni.serval.commons.git.api.gitlab;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lu.uni.serval.commons.git.utils.TimeUtils;

import java.time.Instant;
import java.util.Set;

public class User {
    private int id;
    private String username;
    private String email;
    private String name;
    private String state;
    private String avatarUrl;
    private String webUrl;
    private Instant creationDate;
    private boolean admin;
    private String bio;
    private String location;
    private String skype;
    private String linkedin;
    private String twitter;
    private String websiteUrl;
    private String organization;
    private Instant lastSignInDate;
    private Instant confirmedDate;
    private int themeId;
    private Instant lastActivityDate;
    private int colorSchemeId;
    private int projectsLimit;
    private Instant currentSignInDate;
    private Set<Identity> identities;
    private boolean canCreateGroup;
    private boolean canCreateProject;
    private boolean twoFactorEnabled;
    private boolean external;
    private boolean privateProfile;

    @JsonGetter("id")
    public int getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(int id) {
        this.id = id;
    }

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

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("state")
    public String getState() {
        return state;
    }

    @JsonSetter("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonGetter("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @JsonSetter("avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonGetter("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonSetter("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @JsonGetter("created_at")
    public String getCreationDateAsString(){
        return TimeUtils.toDateTimeString(creationDate);
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    @JsonSetter("created_at")
    public void setCreationDate(String creationDate) {
        setCreationDate(TimeUtils.fromDateTimeString(creationDate));
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
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

    @JsonGetter("last_sign_in_at")
    public String getLastSignInDateAsString(){
        return TimeUtils.toDateTimeString(lastSignInDate);
    }

    public Instant getLastSignInDate() {
        return lastSignInDate;
    }

    @JsonSetter("last_sign_in_at")
    public void setLastSignInDate(String lastSignInDate) {
        setLastSignInDate(TimeUtils.fromDateTimeString(lastSignInDate));
    }

    public void setLastSignInDate(Instant lastSignInDate) {
        this.lastSignInDate = lastSignInDate;
    }

    @JsonGetter("confirmed_at")
    public String getConfirmedDateAsString(){
        return TimeUtils.toDateString(confirmedDate);
    }

    public Instant getConfirmedDate() {
        return confirmedDate;
    }

    @JsonSetter("confirmed_at")
    public void setConfirmedDate(String confirmedDate) {
        setConfirmedDate(TimeUtils.fromDateTimeString(confirmedDate));
    }

    public void setConfirmedDate(Instant confirmedDate) {
        this.confirmedDate = confirmedDate;
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
    public String getLastActivityDateAsString(){
        return TimeUtils.toDateString(lastActivityDate);
    }
    public Instant getLastActivityDate() {
        return lastActivityDate;
    }

    @JsonSetter("last_activity_on")
    public void setLastActivityDate(String lastActivityDate) {
        System.out.println(lastActivityDate);
        setLastActivityDate(TimeUtils.fromDateString(lastActivityDate));
    }

    public void setLastActivityDate(Instant lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
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
    public String getCurrentSignInDateAsString(){
        return TimeUtils.toDateTimeString(currentSignInDate);
    }

    public Instant getCurrentSignInDate() {
        return currentSignInDate;
    }

    @JsonSetter("current_sign_in_at")
    public void setCurrentSignInDate(String currentSignInDate)  {
        setCurrentSignInDate(TimeUtils.fromDateTimeString(currentSignInDate));
    }

    public void setCurrentSignInDate(Instant currentSignInDate) {
        this.currentSignInDate = currentSignInDate;
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
}
