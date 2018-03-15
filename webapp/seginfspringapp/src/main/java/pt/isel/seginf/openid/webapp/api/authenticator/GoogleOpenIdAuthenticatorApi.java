package pt.isel.seginf.openid.webapp.api.authenticator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pt.isel.seginf.openid.webapp.controller.OidStateToken;
import pt.isel.seginf.openid.webapp.controller.OpenIdProperties;
import pt.isel.seginf.openid.webapp.url.builder.GoogleAuthorizationUrlBuilder;
import pt.isel.seginf.openid.webapp.url.builder.GoogleExchangeTokenUrlBuilder;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

public class GoogleOpenIdAuthenticatorApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleOpenIdAuthenticatorApi.class);

    private OpenIdProperties openIdProperties;
    private final OidStateToken token;

    public GoogleOpenIdAuthenticatorApi(OpenIdProperties openIdProperties, OidStateToken token) {
        this.openIdProperties = openIdProperties;
        this.token = token;
    }

    public RedirectView request(String requestRedirectUri, GoogleAuthorizationUrlBuilder.Scope... scopes) {
        String openIdUrl = getOpenIdUrl(requestRedirectUri, scopes);
        RedirectView redirectView = new RedirectView(openIdUrl);
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);
        return redirectView;
    }

    public Mono<ModelAndView> handleReply(
            String code,
            String googleRequestRedirect,
            String redirectViewUri,
            Supplier<ModelAndView> error) {

        String googleCode = new GoogleExchangeTokenUrlBuilder()
                .clientId(openIdProperties.getGoogleClientId())
                .clientSecret(openIdProperties.getGoogleSecret())
                .code(code)
                .redirectUri(googleRequestRedirect)
                .grantType("authorization_code")
                .toString();

        return WebClient.create()
                .post()
                .uri(googleCode)
                .exchange()
                .flatMap(response ->
                        response.bodyToMono(String.class)
                                .map(gResponse -> {
                                    LOGGER.info("Received from google: {}", gResponse);
                                    token.setGoogleJsonResponse(gResponse);
                                    if(token.getGoogleAuthCode().hasError()){
                                        return error.get();
                                    }
                                    LOGGER.info("OidToken state: {}", token);
                                    return new ModelAndView(new RedirectView(redirectViewUri));
                                }));
    }

    private String getOpenIdUrl(String requestRedirectUri, GoogleAuthorizationUrlBuilder.Scope[] scopes) {
        return new GoogleAuthorizationUrlBuilder()
                .clientId(openIdProperties.getGoogleClientId())
                .scope(scopes)
                .state(token.getAntiForgeryToken())
                .redirectUri(requestRedirectUri)
                .responseType("code")
                .toString();
    }

}
