package pt.isel.seginf.openid.webapp.controller;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.util.Properties;

@Configuration
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class OpenIdProperties {

    private final String googleClientId, googleSecret,
            githubClientId, githubSecret;

    private OpenIdProperties() throws IOException {
        Properties prop = new Properties();
        prop.load(OpenIdProperties.class.getClassLoader().getResourceAsStream("openid.properties"));
        this.googleClientId = prop.getProperty("google.clientid");
        this.googleSecret = prop.getProperty("google.secret");
        this.githubClientId = prop.getProperty("github.clientid");
        this.githubSecret = prop.getProperty("github.secret");
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static OpenIdProperties openIdProperties() throws IOException {
        return new OpenIdProperties();
    }

    public String getGoogleClientId() {
        return googleClientId;
    }

    public String getGoogleSecret() {
        return googleSecret;
    }

    public String getGithubClientId() {
        return githubClientId;
    }

    public String getGithubSecret() {
        return githubSecret;
    }
}
