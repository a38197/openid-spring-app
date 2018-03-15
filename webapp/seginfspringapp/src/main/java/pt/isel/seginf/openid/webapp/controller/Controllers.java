package pt.isel.seginf.openid.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Supplier;

class Controllers {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controllers.class);

    Controllers() {
        //util
    }

    static Mono<ModelAndView> withAuthorization(boolean isValid, OidStateToken token, Supplier<Mono<ModelAndView>> other) {
        if (!isValid) {
            LOGGER.error("Not valid session token {}", token);
            return Mono.just(error(HttpStatus.UNAUTHORIZED, "Authorization token not found or invalid"));
        }
        return other.get();
    }

    static ModelAndView error(HttpStatus status, String msg, Object... args) {
        String errMsg = String.format(msg, args);
        ModelAndView modelAndView = new ModelAndView("customError", Map.of("errorMessage", errMsg));
        modelAndView.setStatus(status);
        return modelAndView;
    }

}
