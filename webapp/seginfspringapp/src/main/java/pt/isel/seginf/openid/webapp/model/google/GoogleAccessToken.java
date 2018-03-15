package pt.isel.seginf.openid.webapp.model.google;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class GoogleAccessToken {

    private String access_token,
            id_token,
            expires_in,
            token_type,
            refresh_token,
            error,
            error_description;

    public GoogleAccessToken() {
    }

    public GoogleAccessToken(String access_token, String id_token, String expires_in, String token_type, String refresh_token) {
        this.access_token = access_token;
        this.id_token = id_token;
        this.expires_in = expires_in;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
    }

    @JsonGetter("access_token")
    public String getAccess_token() {
        return access_token;
    }

    @JsonSetter("access_token")
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @JsonGetter("id_token")
    public String getId_token() {
        return id_token;
    }

    @JsonSetter("id_token")
    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    @JsonGetter("expires_in")
    public String getExpires_in() {
        return expires_in;
    }

    @JsonSetter("expires_in")
    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    @JsonGetter("token_type")
    public String getToken_type() {
        return token_type;
    }

    @JsonSetter("token_type")
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    @JsonGetter("refresh_token")
    public String getRefresh_token() {
        return refresh_token;
    }

    @JsonSetter("refresh_token")
    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    @JsonGetter("error")
    public String getError() {
        return error;
    }

    @JsonSetter("error")
    public void setError(String error) {
        this.error = error;
    }

    @JsonGetter("error_description")
    public String getError_description() {
        return error_description;
    }

    @JsonSetter("error_description")
    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public boolean hasError() {
        return error != null;
    }

    @Override
    public String toString() {
        return "GoogleAccessToken{" +
                "access_token='" + access_token + '\'' +
                ", id_token='" + id_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", token_type='" + token_type + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", error='" + error + '\'' +
                ", error_description='" + error_description + '\'' +
                '}';
    }

    public boolean isValid() {
        return !hasError();
        //todo check expiration
    }
}
