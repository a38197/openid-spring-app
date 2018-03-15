package pt.isel.seginf.openid.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pt.isel.seginf.openid.webapp.api.calendar.GoogleCalendarApi;
import pt.isel.seginf.openid.webapp.api.milestones.GithubMilestonesApi;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Map;

import static pt.isel.seginf.openid.webapp.controller.Controllers.error;
import static pt.isel.seginf.openid.webapp.controller.Controllers.withAuthorization;

@Controller
@SessionAttributes("oidToken")
public class MilestoneController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MilestoneController.class);
    private static final String GITHUB_MILESTONES_SHOW_MAPPING = "/github/milestones/list/";
    static final String GITHUB_MILESTONES_QUERY_MAPPING = "/github/milestones/query";
    private static final String GITHUB_SET_EVENT = "/github/milestones/setevent";

    private final OidStateToken token;


    public MilestoneController(OidStateToken token) {
        this.token = token;
    }

    @GetMapping(GITHUB_MILESTONES_QUERY_MAPPING)
    public ModelAndView githubMilestones() {

        //Both monos are instant. No block happens
        return withAuthorization(token.isGithubValid(), token, () -> Mono.just(new ModelAndView("milestone_query", Map.of("account", token.getDecodedIdToken().getEmail(), "milestoneAction", GITHUB_MILESTONES_SHOW_MAPPING)))).block();
    }

    @PostMapping(GITHUB_MILESTONES_SHOW_MAPPING)
    public Mono<ModelAndView> showGithubMilestones(
            @RequestParam String user,
            @RequestParam String repository) {

        LOGGER.info("Getting milestones for user {} and repo {}", user, repository);
        if (user.isEmpty() || repository.isEmpty()) {
            return Mono.just(error(HttpStatus.BAD_REQUEST, "User or Repository is empty!"));
        }

        return new GithubMilestonesApi(token.getGithubAccessToken(), GITHUB_SET_EVENT)
                .handleRequest(user, repository);

    }

    @PostMapping(GITHUB_SET_EVENT)
    public Mono<ModelAndView> insertCalendarEvent(@RequestParam String title,
                                    @RequestParam String description,
                                    @RequestParam String date,
                                    @RequestParam String url,
                                    @SessionAttribute(GoogleCalendarController.CALENDAR_ID_SESSION) String calendarId) {

        String calendarDesc = String.format("(%s) %s", url, description);
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        final LocalDate calendarDate = LocalDate.of(year, month, day);

        return new GoogleCalendarApi(token.getGoogleAuthCode().getToken_type(), token.getGoogleAuthCode().getAccess_token())
                .insertAllDayEvent(calendarId, calendarDate, title, calendarDesc)
                .map(httpStatus -> {
                    if(httpStatus.isError()){
                        return error(httpStatus, "Error inserting event: %s", httpStatus.getReasonPhrase());
                    }
                    return new ModelAndView("message", Map.of("message","Event inserted with success!"));
                });

    }
}
