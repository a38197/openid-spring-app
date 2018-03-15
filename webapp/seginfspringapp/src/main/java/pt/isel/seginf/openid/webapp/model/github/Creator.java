package pt.isel.seginf.openid.webapp.model.github;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Creator {
    private String login;
    private int id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private boolean site_admin;

    @JsonGetter
    public String getLogin() {
        return login;
    }

    @JsonSetter
    public void setLogin(String login) {
        this.login = login;
    }

    @JsonGetter
    public int getId() {
        return id;
    }

    @JsonSetter
    public void setId(int id) {
        this.id = id;
    }

    @JsonGetter
    public String getAvatar_url() {
        return avatar_url;
    }

    @JsonSetter
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @JsonGetter
    public String getGravatar_id() {
        return gravatar_id;
    }

    @JsonSetter
    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    @JsonGetter
    public String getUrl() {
        return url;
    }

    @JsonSetter
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonGetter
    public String getHtml_url() {
        return html_url;
    }

    @JsonSetter
    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    @JsonGetter
    public String getFollowers_url() {
        return followers_url;
    }

    @JsonSetter
    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    @JsonGetter
    public String getFollowing_url() {
        return following_url;
    }

    @JsonSetter
    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    @JsonGetter
    public String getGists_url() {
        return gists_url;
    }

    @JsonSetter
    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    @JsonGetter
    public String getStarred_url() {
        return starred_url;
    }

    @JsonSetter
    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    @JsonGetter
    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    @JsonSetter
    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    @JsonGetter
    public String getOrganizations_url() {
        return organizations_url;
    }

    @JsonSetter
    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    @JsonGetter
    public String getRepos_url() {
        return repos_url;
    }

    @JsonSetter
    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    @JsonGetter
    public String getEvents_url() {
        return events_url;
    }

    @JsonSetter
    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    @JsonGetter
    public String getReceived_events_url() {
        return received_events_url;
    }

    @JsonSetter
    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    @JsonGetter
    public String getType() {
        return type;
    }

    @JsonSetter
    public void setType(String type) {
        this.type = type;
    }

    @JsonGetter
    public boolean isSite_admin() {
        return site_admin;
    }

    @JsonSetter
    public void setSite_admin(boolean site_admin) {
        this.site_admin = site_admin;
    }

    @Override
    public String toString() {
        return "Creator{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", avatar_url='" + avatar_url + '\'' +
                ", gravatar_id='" + gravatar_id + '\'' +
                ", url='" + url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", followers_url='" + followers_url + '\'' +
                ", following_url='" + following_url + '\'' +
                ", gists_url='" + gists_url + '\'' +
                ", starred_url='" + starred_url + '\'' +
                ", subscriptions_url='" + subscriptions_url + '\'' +
                ", organizations_url='" + organizations_url + '\'' +
                ", repos_url='" + repos_url + '\'' +
                ", events_url='" + events_url + '\'' +
                ", received_events_url='" + received_events_url + '\'' +
                ", type='" + type + '\'' +
                ", site_admin=" + site_admin +
                '}';
    }
}
