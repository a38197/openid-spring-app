package pt.isel.seginf.openid.webapp.api.authenticator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pt.isel.seginf.openid.webapp.controller.OidStateToken;
import pt.isel.seginf.openid.webapp.controller.OpenIdProperties;
import pt.isel.seginf.openid.webapp.url.builder.GithubAuthorizationUrlBuilder;
import pt.isel.seginf.openid.webapp.url.builder.GithubExchangeTokenUrlBuilder;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

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
 *
 */
public class GithubOpenIdAuthenticatorApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(GithubOpenIdAuthenticatorApi.class);

    private OpenIdProperties openIdProperties;
    private final OidStateToken token;

    public GithubOpenIdAuthenticatorApi(OpenIdProperties openIdProperties, OidStateToken token) {
        this.openIdProperties = openIdProperties;
        this.token = token;
    }

    public RedirectView request(String requestRedirectUri) {
        String openIdUrl = getOpenIdUrl(requestRedirectUri);
        RedirectView redirectView = new RedirectView(openIdUrl);
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);
        return redirectView;
    }

    private String getOpenIdUrl(String requestRedirectUri) {
        return new GithubAuthorizationUrlBuilder()
                .clientId(openIdProperties.getGithubClientId())
                .state(token.getAntiForgeryToken())
                .redirectUri(requestRedirectUri)
                .responseType("code")
                .allowSignup(false)
                .toString();
    }

    public Mono<ModelAndView> handleReply(
            String code,
            String githubRequestRedirect,
            String redirectViewUri,
            Supplier<ModelAndView> error) {

        String githubCode = new GithubExchangeTokenUrlBuilder()
                .clientId(openIdProperties.getGithubClientId())
                .clientSecret(openIdProperties.getGithubSecret())
                .code(code)
                .redirectUri(githubRequestRedirect)
                .grantType("authorization_code")
                .toString();

        return WebClient.create()
                .post()
                .uri(githubCode)
                .exchange()
                .flatMap(response ->
                        response.bodyToMono(String.class)
                                .map(gResponse -> {
                                    LOGGER.info("Received from github: {}", gResponse);
                                    token.setGithubResponse(gResponse);
                                    if(!token.isGithubValid()){
                                        return error.get();
                                    }
                                    LOGGER.info("OidToken state: {}", token);
                                    return new ModelAndView(new RedirectView(redirectViewUri));
                                }));
    }

}
