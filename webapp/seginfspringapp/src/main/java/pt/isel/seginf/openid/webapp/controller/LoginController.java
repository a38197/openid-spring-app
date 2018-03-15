package pt.isel.seginf.openid.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pt.isel.seginf.openid.webapp.api.authenticator.GithubOpenIdAuthenticatorApi;
import pt.isel.seginf.openid.webapp.api.authenticator.GoogleOpenIdAuthenticatorApi;
import pt.isel.seginf.openid.webapp.model.LoginModel;
import pt.isel.seginf.openid.webapp.url.builder.GoogleAuthorizationUrlBuilder;
import reactor.core.publisher.Mono;

import static pt.isel.seginf.openid.webapp.controller.Controllers.error;
import static pt.isel.seginf.openid.webapp.controller.MilestoneController.GITHUB_MILESTONES_QUERY_MAPPING;

@Controller
@SessionAttributes("oidToken")
public class LoginController {

    /*
     * Should not use security module because authentication is automatic
     * It means every method should authenticate the client
     */

    private static final String MY_BASE_URL = "http://localhost:8080";
    public static final String INDEX_MAPPING = "/";
    private static final String GOOGLE_LOGIN_MAPPING = "/openid/google/request";
    private static final String GITHUB_LOGIN_MAPPING = "/openid/github/request";
    private static final String GOOGLE_AUTH_REPLY_MAPPING = "/openid/google/reply";
    private static final String GITHUB_AUTH_REPLY_MAPPING = "/openid/github/reply";



    private final OidStateToken token;

    public LoginController(OidStateToken token) {
        this.token = token;
    }

    @GetMapping(value = {INDEX_MAPPING, "/index"})
    public ModelAndView index() {
        final LoginModel model = new LoginModel(token)
                .setGoogleUrl(GOOGLE_LOGIN_MAPPING)
                .setGithubUrl(GITHUB_LOGIN_MAPPING)
                .setMilestonesUrl(GITHUB_MILESTONES_QUERY_MAPPING)
                .setCalendarSelectUrl(GoogleCalendarController.GOOGLE_CALENDAR_LIST_MAPPING);

        return new ModelAndView("login", "model", model);
    }

    /*
    * Both logins are mandatory for simplicity and chained together!
    * For debug change the google and login redirects to index page.
    * */
    @GetMapping(GOOGLE_LOGIN_MAPPING)
    public RedirectView redirectToGoogleLogin(
            @Autowired OpenIdProperties openIdProperties) {
        return new GoogleOpenIdAuthenticatorApi(openIdProperties, token)
                .request(MY_BASE_URL + GOOGLE_AUTH_REPLY_MAPPING,
                        GoogleAuthorizationUrlBuilder.Scope.Openid,
                        GoogleAuthorizationUrlBuilder.Scope.Email,
                        GoogleAuthorizationUrlBuilder.Scope.Calendar);

    }

    @GetMapping(GOOGLE_AUTH_REPLY_MAPPING)
    public Mono<ModelAndView> googleAuthResponse(
            @RequestParam String state,
            @RequestParam String code,
            @Autowired OpenIdProperties properties) {
        if (!token.getAntiForgeryToken().equals(state)) {
            return Mono.just(error(HttpStatus.UNAUTHORIZED, "Session token %s and retrieved %s are not equal", token.getAntiForgeryToken(), state));
        }
        return new GoogleOpenIdAuthenticatorApi(properties, token)
                .handleReply(code, MY_BASE_URL + GOOGLE_AUTH_REPLY_MAPPING,
                        GoogleCalendarController.GOOGLE_CALENDAR_LIST_MAPPING,
                        () -> error(HttpStatus.INTERNAL_SERVER_ERROR, token.getGoogleJsonResponse()));
    }

    @GetMapping(GITHUB_LOGIN_MAPPING)
    public RedirectView redirectToGithubLogin(
            @Autowired OpenIdProperties openIdProperties) {

        return new GithubOpenIdAuthenticatorApi(openIdProperties, token)
                .request(MY_BASE_URL + GITHUB_AUTH_REPLY_MAPPING);

    }

    @GetMapping(GITHUB_AUTH_REPLY_MAPPING)
    public Mono<ModelAndView> githubAuthResponse(
            @RequestParam String state,
            @RequestParam String code,
            @Autowired OpenIdProperties properties) {
        if (!token.getAntiForgeryToken().equals(state)) {
            return Mono.just(error(HttpStatus.UNAUTHORIZED, "Session token %s and retrieved %s are not equal", token.getAntiForgeryToken(), state));
        }
        return new GithubOpenIdAuthenticatorApi(properties, token)
                .handleReply(code, MY_BASE_URL + GITHUB_AUTH_REPLY_MAPPING,
                        INDEX_MAPPING,
                        () -> error(HttpStatus.INTERNAL_SERVER_ERROR, token.getGithubResponse()));
    }

}
