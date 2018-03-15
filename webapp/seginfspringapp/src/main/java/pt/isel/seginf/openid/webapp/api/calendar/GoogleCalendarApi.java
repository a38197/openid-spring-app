package pt.isel.seginf.openid.webapp.api.calendar;

import com.google.api.services.calendar.model.CalendarList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public class GoogleCalendarApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleCalendarApi.class);
    private static final String BASE = "https://www.googleapis.com/calendar/v3";
    private static final String AUTHORIZATION_HEADER = "authorization";
    public static final String CONTENT_TYPE_HEADER = "Content-type";
    public static final String APPLICATION_JSON = "application/json";
    private final String tokenType;
    private final String googleToken;

    public GoogleCalendarApi(String tokenType, String googleToken) {
        this.tokenType = tokenType;
        this.googleToken = googleToken;
    }

    public Mono<CalendarList> getAvailableCalendars() {
        return WebClient.create()
                .get()
                .uri(BASE + "/users/me/calendarList")
                .header(AUTHORIZATION_HEADER, authHeaderValue())
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(CalendarList.class));
    }

    private String authHeaderValue() {
        return tokenType + " " + googleToken;
    }


    public Mono<org.springframework.http.HttpStatus> insertAllDayEvent(String calendarId, LocalDate start, String summary, String description) {

        final LocalDate end = start.plusDays(1);

        final String eventBody = String.format("{\"end\":{\"date\":\"%s\"}," +
                "\"start\":{\"date\":\"%s\"}, " +
                "\"description\":\"%s\"," +
                "\"summary\": \"%s\"}", end.toString(), start.toString(), description.trim(), summary.trim());

        final String eventUri = insertEventUri(calendarId);

        LOGGER.info("Sent to google calendar {} data {}", eventUri, eventBody);

        return WebClient.create()
                .post()
                .uri(eventUri)
                .header(AUTHORIZATION_HEADER, authHeaderValue())
                .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .syncBody(eventBody)
                .exchange()
                .map(clientResponse -> {
                    LOGGER.info("Received from google status {} {}", clientResponse.statusCode().value(), clientResponse.statusCode().getReasonPhrase());
                    clientResponse.bodyToMono(String.class).subscribe(LOGGER::info);
                    return clientResponse.statusCode();
                });
    }

    private String insertEventUri(String calendarId) {
        return BASE + "/calendars/" + calendarId + "/events";
    }
}
