package pt.isel.seginf.openid.webapp.model.github;

public class GithubAccessToken {

    private static final String paramSep = "&";
    private static final String valueSep = "=";

    private String access_token;
    private String tokenType;
    private String error;

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    private String scopes;

    public String getTokenType() {
        return tokenType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }


    @Override
    public String toString() {
        return "GithubAccessToken{" +
                "access_token='" + access_token + '\'' +
                '}';
    }

    public static GithubAccessToken parse(String rawResponse) {
        final GithubAccessToken token = new GithubAccessToken();
        for (String paramAndVal : rawResponse.split(paramSep)) {
            final String[] split = paramAndVal.split(valueSep);
            switch (split[0]) {
                case "error":
                    token.setError(split[1]);
                    break;
                case "access_token":
                    token.setAccess_token(split[1]);
                    break;
                case "token_type":
                    token.setTokenType(split[1]);
                    break;
                case "scopes":
                    token.setScopes(split[1]);
                    break;
            }
        }
        return token;
    }
}
