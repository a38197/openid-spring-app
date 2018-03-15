package pt.isel.seginf.openid.webapp.model.github;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Milestone {

    private String url;
    private String html_url;
    private String labels_url;
    private int id;
    private int number;
    private String state;
    private String title;
    private String description;
    private Creator creator;
    private int open_issues;
    private int closed_issues;
    private String created_at;
    private String updated_at;
    private String closed_at;
    private String due_on;

    @JsonGetter
    public String getUrl() {
        return url;
    }

    @JsonGetter
    public String getHtml_url() {
        return html_url;
    }

    @JsonGetter
    public String getLabels_url() {
        return labels_url;
    }

    @JsonGetter
    public int getId() {
        return id;
    }

    @JsonGetter
    public int getNumber() {
        return number;
    }

    @JsonGetter
    public String getState() {
        return state;
    }

    @JsonGetter
    public String getTitle() {
        return title;
    }

    @JsonGetter
    public String getDescription() {
        return description;
    }

    @JsonGetter
    public Creator getCreator() {
        return creator;
    }

    @JsonGetter
    public int getOpen_issues() {
        return open_issues;
    }

    @JsonGetter
    public int getClosed_issues() {
        return closed_issues;
    }

    @JsonGetter
    public String getCreated_at() {
        return created_at;
    }

    @JsonGetter
    public String getUpdated_at() {
        return updated_at;
    }

    @JsonGetter
    public String getClosed_at() {
        return closed_at;
    }

    @JsonGetter
    public String getDue_on() {
        return due_on;
    }

    @JsonSetter
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonSetter
    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    @JsonSetter
    public void setLabels_url(String labels_url) {
        this.labels_url = labels_url;
    }

    @JsonSetter
    public void setId(int id) {
        this.id = id;
    }

    @JsonSetter
    public void setNumber(int number) {
        this.number = number;
    }

    @JsonSetter
    public void setState(String state) {
        this.state = state;
    }

    @JsonSetter
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonSetter
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonSetter
    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    @JsonSetter
    public void setOpen_issues(int open_issues) {
        this.open_issues = open_issues;
    }

    @JsonSetter
    public void setClosed_issues(int closed_issues) {
        this.closed_issues = closed_issues;
    }

    @JsonSetter
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @JsonSetter
    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @JsonSetter
    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    @JsonSetter
    public void setDue_on(String due_on) {
        this.due_on = due_on;
    }

    @Override
    public String toString() {
        return "Milestone{" +
                "url='" + url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", labels_url='" + labels_url + '\'' +
                ", id=" + id +
                ", number=" + number +
                ", state='" + state + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creator=" + creator +
                ", open_issues=" + open_issues +
                ", closed_issues=" + closed_issues +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", closed_at='" + closed_at + '\'' +
                ", due_on='" + due_on + '\'' +
                '}';
    }
}
