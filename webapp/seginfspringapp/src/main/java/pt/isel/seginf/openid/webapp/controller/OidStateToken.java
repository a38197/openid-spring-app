package pt.isel.seginf.openid.webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pt.isel.seginf.openid.webapp.model.github.GithubAccessToken;
import pt.isel.seginf.openid.webapp.model.google.DecodedIdToken;
import pt.isel.seginf.openid.webapp.model.google.GoogleAccessToken;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Component
public class OidStateToken {

    private static final Logger LOGGER = LoggerFactory.getLogger(OidStateToken.class);

    private final String antiForgeryToken = UUID.randomUUID().toString();
    private final LocalDateTime creationDate = LocalDateTime.now();
    private GoogleAccessToken googleAuthCode;
    private DecodedIdToken decodedIdToken;
    private GithubAccessToken githubAccessToken;
    private String googleJsonResponse, githubResponse;

    public String getAntiForgeryToken() {
        return antiForgeryToken;
    }

    public GoogleAccessToken getGoogleAuthCode() {
        return googleAuthCode;
    }

    public void setGoogleAuthCode(GoogleAccessToken googleAuthCode) {
        this.googleAuthCode = googleAuthCode;
    }

    public String getGoogleJsonResponse() {
        return googleJsonResponse;
    }

    public void setGoogleJsonResponse(String googleJsonResponse) {
        this.googleJsonResponse = googleJsonResponse;
        ObjectMapper mapper = new ObjectMapper();
        try {
            googleAuthCode = mapper.readValue(googleJsonResponse, GoogleAccessToken.class);
            if(!googleAuthCode.hasError()){
                /*
                * Index 0 - header
                * Index 1 - payload
                * Index 2 - signature (google says its not needed to validate signature because we do not share the token)
                * */
                String[] id_token = googleAuthCode.getId_token().split("\\.");

                String decoded = new String(Base64.getDecoder().decode(id_token[1]));
                LOGGER.info("Decoded id token {}", decoded);
                decodedIdToken = mapper.readValue(decoded, DecodedIdToken.class);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public DecodedIdToken getDecodedIdToken() {
        return decodedIdToken;
    }

    public String getGithubResponse() {
        return githubResponse;
    }

    public void setGithubResponse(String githubResponse) {
        //NOT JSON!!! separated with "&". damn you github and non standard api!
        this.githubResponse = githubResponse;
        this.githubAccessToken = GithubAccessToken.parse(githubResponse);
    }

    public GithubAccessToken getGithubAccessToken() {
        return githubAccessToken;
    }

    public boolean isGoogleValid() {
        return googleAuthCode != null && googleAuthCode.isValid();
    }

    public boolean isGithubValid() {
        return githubAccessToken != null;
    }

    @Override
    public String toString() {
        return "OidStateToken{" +
                "antiForgeryToken='" + antiForgeryToken + '\'' +
                ", creationDate=" + creationDate +
                ", googleAuthCode=" + googleAuthCode +
                ", decodedIdToken=" + decodedIdToken +
                ", githubAccessToken=" + githubAccessToken +
                ", googleJsonResponse='" + googleJsonResponse + '\'' +
                ", githubResponse='" + githubResponse + '\'' +
                '}';
    }
}
