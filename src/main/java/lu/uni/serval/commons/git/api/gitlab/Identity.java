package lu.uni.serval.commons.git.api.gitlab;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Identity {
    private String provider;
    private String externUid;

    @JsonGetter("provider")
    public String getProvider() {
        return provider;
    }

    @JsonSetter("provider")
    public void setProvider(String provider) {
        this.provider = provider;
    }

    @JsonGetter("extern_uid")
    public String getExternUid() {
        return externUid;
    }

    @JsonSetter("extern_uid")
    public void setExternUid(String externUid) {
        this.externUid = externUid;
    }
}
