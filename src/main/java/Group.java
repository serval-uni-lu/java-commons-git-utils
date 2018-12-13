import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Group {
    private int id;
    private String name;
    private String web_url;
    private String path;
    private String description;
    private String visibility;
    private boolean lfs_enabled;
    private String avatar_url;
    private boolean request_access_enabled;
    private String full_name;
    private String full_path;
    private int parent_id;
    private String ldap_cn;
    private String ldap_access;

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
        return web_url;
    }

    @JsonSetter("web_url")
    public void setWebUrl(String web_url) {
        this.web_url = web_url;
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
        return lfs_enabled;
    }

    @JsonSetter("lfs_enabled")
    public void setLfsEnabled(boolean lfs_enabled) {
        this.lfs_enabled = lfs_enabled;
    }

    @JsonGetter("avatar_url")
    public String getAvatarUrl() {
        return avatar_url;
    }

    @JsonSetter("avatar_url")
    public void setAvatarUrl(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @JsonGetter("request_access_enabled")
    public boolean isRequestAccessEnabled() {
        return request_access_enabled;
    }

    @JsonSetter("request_access_enabled")
    public void setRequestAccessEnabled(boolean request_access_enabled) {
        this.request_access_enabled = request_access_enabled;
    }

    @JsonGetter("full_name")
    public String getFullName() {
        return full_name;
    }

    @JsonSetter("full_name")
    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    @JsonGetter("full_path")
    public String getFull_path() {
        return full_path;
    }

    @JsonSetter("full_path")
    public void setFullPath(String full_path) {
        this.full_path = full_path;
    }

    @JsonGetter("parent_id")
    public int getParentId() {
        return parent_id;
    }

    @JsonSetter("parent_id")
    public void setParentId(int parent_id) {
        this.parent_id = parent_id;
    }

    @JsonGetter("ldap_cn")
    public String getLdapCn() {
        return ldap_cn;
    }

    @JsonSetter("ldap_cn")
    public void setLdapCn(String ldap_cn) {
        this.ldap_cn = ldap_cn;
    }

    @JsonGetter("ldap_access")
    public String getLdapAccess() {
        return ldap_access;
    }

    @JsonSetter("ldap_access")
    public void setLdapAccess(String ldap_access) {
        this.ldap_access = ldap_access;
    }
}
