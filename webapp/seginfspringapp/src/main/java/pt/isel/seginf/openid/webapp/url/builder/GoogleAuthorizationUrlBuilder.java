package pt.isel.seginf.openid.webapp.url.builder;

public class GoogleAuthorizationUrlBuilder extends BaseUrlBuilder<GoogleAuthorizationUrlBuilder> {

    private static final String BASE = "https://accounts.google.com/o/oauth2/v2/auth?";

    public GoogleAuthorizationUrlBuilder() {
        super(BASE);
    }

    @Override
    protected GoogleAuthorizationUrlBuilder self() {
        return this;
    }

    public GoogleAuthorizationUrlBuilder responseType(String value) {
        return addParam("response_type", value);
    }

    public GoogleAuthorizationUrlBuilder scope(Scope... values) {
        StringBuilder scopes = new StringBuilder();
        for (Scope scope : values) {
            scopes.append(scope.scope).append(" ");
        }
        return addParam("scope", scopes.toString());
    }

    public GoogleAuthorizationUrlBuilder state(String value) {
        return addParam("state", value);
    }

    public enum Scope {
        Openid("openid"), Email("email"), Calendar("https://www.googleapis.com/auth/calendar");
        private final String scope;

        Scope(String scope) {
            this.scope = scope;
        }
    }
}
