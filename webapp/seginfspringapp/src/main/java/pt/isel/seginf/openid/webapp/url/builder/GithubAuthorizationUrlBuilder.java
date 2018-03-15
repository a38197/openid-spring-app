package pt.isel.seginf.openid.webapp.url.builder;

/**
 * Github has 2 methods for authentication
 * <ol>
 *     <li>
 *         Using client_id + secret in the query string. This is advised only on server-server scenarios so the secret does not leak
 *         This method is mandatory for the second one, in order to create an access token, much like google api.
 *     </li>
 *     <li>
 *         Using the token header with a previously create access token.
 *     </li>
 * </ol>
 */
public class GithubAuthorizationUrlBuilder extends BaseUrlBuilder<GithubAuthorizationUrlBuilder> {

    private static final String BASE = "https://github.com/login/oauth/authorize?";

    public GithubAuthorizationUrlBuilder() {
        super(BASE);
    }

    @Override
    protected GithubAuthorizationUrlBuilder self() {
        return this;
    }

    public GithubAuthorizationUrlBuilder allowSignup(boolean value) {
        return addRawParam("allow_signup", String.valueOf(value));
    }

    public GithubAuthorizationUrlBuilder state(String value) {
        return addParam("state", value);
    }

    public GithubAuthorizationUrlBuilder responseType(String value) {
        return addParam("response_type", value);
    }

}
