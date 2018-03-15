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
public class GithubExchangeTokenUrlBuilder extends BaseUrlBuilder<GithubExchangeTokenUrlBuilder> {

    private static final String BASE = "https://github.com/login/oauth/access_token?";

    public GithubExchangeTokenUrlBuilder() {
        super(BASE);
    }

    @Override
    protected GithubExchangeTokenUrlBuilder self() {
        return this;
    }


    public GithubExchangeTokenUrlBuilder clientSecret(String value) {
        return addParam("client_secret", value);
    }

    public GithubExchangeTokenUrlBuilder code(String value) {
        return addParam("code", value);
    }

    public GithubExchangeTokenUrlBuilder grantType(String value) {
        return addParam("gran_type", value);
    }
}
