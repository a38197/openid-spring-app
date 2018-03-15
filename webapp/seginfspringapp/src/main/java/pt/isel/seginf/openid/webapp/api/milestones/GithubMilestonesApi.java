package pt.isel.seginf.openid.webapp.api.milestones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import pt.isel.seginf.openid.webapp.model.github.GithubAccessToken;
import pt.isel.seginf.openid.webapp.model.github.MilestoneList;
import pt.isel.seginf.openid.webapp.url.builder.GithubMilestonesUrlBuilder;
import reactor.core.publisher.Mono;

import java.util.Map;

public class GithubMilestonesApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(GithubMilestonesApi.class);

    private final GithubAccessToken githubAccessToken;
    private final String actionUrl;

    public GithubMilestonesApi(GithubAccessToken githubAccessToken, String actionUrl) {
        this.githubAccessToken = githubAccessToken;
        this.actionUrl = actionUrl;
    }

    public Mono<ModelAndView> handleRequest(String user, String repository) {
        final String uri = new GithubMilestonesUrlBuilder(user, repository)
                .accessToken(githubAccessToken).toString();
//todo must me possible to private repositories (require scopes on login)
        return WebClient.create()
                .get()
                .uri(uri)
                .exchange()
                .log()
                .flatMap(clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .map(milestoneList -> {
                                            LOGGER.info("Received from github {}", milestoneList);
                                            final Map<String, Object> modelMap = Map.of(
                                                    "model", MilestoneList.fromJson(user, repository, milestoneList),
                                                    "actionUrl", actionUrl);
                                            return new ModelAndView("milestone_list", modelMap);
                                        }
                                )
                );
    }

}
