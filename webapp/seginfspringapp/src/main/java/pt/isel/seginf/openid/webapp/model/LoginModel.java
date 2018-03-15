package pt.isel.seginf.openid.webapp.model;

import pt.isel.seginf.openid.webapp.controller.OidStateToken;
import pt.isel.seginf.openid.webapp.model.github.GithubAccessToken;
import pt.isel.seginf.openid.webapp.model.google.DecodedIdToken;

public class LoginModel {

    private boolean googleLoggedIn, githubLoggedIn;
    private String googleUser, googleUrl, githubUrl, milestonesUrl, calendarSelectUrl;

    public LoginModel(OidStateToken token) {
        final DecodedIdToken decodedIdToken = token.getDecodedIdToken();
        if(decodedIdToken != null){
            googleLoggedIn = true;
            googleUser = decodedIdToken.getEmail();
        }

        final GithubAccessToken githubAccessToken = token.getGithubAccessToken();
        if(githubAccessToken != null) {
            githubLoggedIn = true;
        }
    }

    public boolean isGoogleLoggedIn() {
        return googleLoggedIn;
    }

    public LoginModel setGoogleLoggedIn(boolean googleLoggedIn) {
        this.googleLoggedIn = googleLoggedIn;
        return this;
    }

    public boolean isGithubLoggedIn() {
        return githubLoggedIn;
    }

    public LoginModel setGithubLoggedIn(boolean githubLoggedIn) {
        this.githubLoggedIn = githubLoggedIn;
        return this;
    }

    public String getGoogleUser() {
        return googleUser;
    }

    public LoginModel setGoogleUser(String googleUser) {
        this.googleUser = googleUser;
        return this;
    }

    public String getGoogleUrl() {
        return googleUrl;
    }

    public LoginModel setGoogleUrl(String googleUrl) {
        this.googleUrl = googleUrl;
        return this;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public LoginModel setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
        return this;
    }

    public String getMilestonesUrl() {
        return milestonesUrl;
    }

    public LoginModel setMilestonesUrl(String milestonesUrl) {
        this.milestonesUrl = milestonesUrl;
        return this;
    }

    public String getCalendarSelectUrl() {
        return calendarSelectUrl;
    }

    public LoginModel setCalendarSelectUrl(String calendarSelectUrl) {
        this.calendarSelectUrl = calendarSelectUrl;
        return this;
    }
}
