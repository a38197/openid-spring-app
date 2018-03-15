package pt.isel.seginf.openid.webapp.url.builder;

import pt.isel.seginf.openid.webapp.model.github.GithubAccessToken;

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
public class GithubMilestonesUrlBuilder extends BaseUrlBuilder<GithubMilestonesUrlBuilder> {

    private static final String BASE = "https://api.github.com/repos/%s/%s/milestones?";

    public GithubMilestonesUrlBuilder(String owner, String repo) {
        super(String.format(BASE, owner, repo));
    }

    @Override
    protected GithubMilestonesUrlBuilder self() {
        return this;
    }

    public GithubMilestonesUrlBuilder accessToken(GithubAccessToken token) {
        return addParam("access_token", token.getAccess_token());
    }

}
