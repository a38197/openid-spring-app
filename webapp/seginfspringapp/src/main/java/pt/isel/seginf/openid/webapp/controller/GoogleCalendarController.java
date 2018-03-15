package pt.isel.seginf.openid.webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pt.isel.seginf.openid.webapp.api.calendar.GoogleCalendarApi;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@SessionAttributes("oidToken")
public class GoogleCalendarController {
    public static final String GOOGLE_CALENDAR_LIST_MAPPING = "/google/calendar/list";
    private static final String BASE_GOOGLE_CALENDAR_ID_MAPPING = "/google/calendar/";
    private static final String FULL_GOOGLE_CALENDAR_ID_MAPPING = BASE_GOOGLE_CALENDAR_ID_MAPPING + "{calendarId}";
    private final OidStateToken token;
    public static final String CALENDAR_ID_SESSION = "session.google.calendarId";

    public GoogleCalendarController(OidStateToken token) {
        this.token = token;
    }

    @GetMapping(GOOGLE_CALENDAR_LIST_MAPPING)
    public Mono<ModelAndView> getCalendarList() {
        return new GoogleCalendarApi(token.getGoogleAuthCode().getToken_type(), token.getGoogleAuthCode().getAccess_token())
                .getAvailableCalendars()
                .map(calendars -> {
                    if(calendars.containsKey("error")){
                        return Controllers.error(HttpStatus.INTERNAL_SERVER_ERROR, calendars.get("error").toString());
                    }

                    final Map<String, Object> modelMap = Map.of(
                            "model", calendars.getItems(),
                            "selectUrl", BASE_GOOGLE_CALENDAR_ID_MAPPING,
                            "user", token.getDecodedIdToken().getEmail());

                    return new ModelAndView("calendar_list", modelMap);
                });
    }

    @GetMapping(FULL_GOOGLE_CALENDAR_ID_MAPPING)
    public RedirectView getCalendarId(@PathVariable String calendarId, HttpSession session) {
        //Spring removes ".com" from path variables
        session.setAttribute(CALENDAR_ID_SESSION, calendarId + ".com");
        return new RedirectView(LoginController.INDEX_MAPPING);
    }

}
