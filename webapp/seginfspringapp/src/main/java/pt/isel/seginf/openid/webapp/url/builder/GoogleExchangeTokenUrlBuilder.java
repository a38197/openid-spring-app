package pt.isel.seginf.openid.webapp.url.builder;

public class GoogleExchangeTokenUrlBuilder extends BaseUrlBuilder<GoogleExchangeTokenUrlBuilder> {

    private static final String BASE = "https://www.googleapis.com/oauth2/v4/token?";

    public GoogleExchangeTokenUrlBuilder() {
        super(BASE);
    }

    @Override
    protected GoogleExchangeTokenUrlBuilder self() {
        return this;
    }

    public GoogleExchangeTokenUrlBuilder code(String value) {
        return addRawParam("code", value);
    }

    public GoogleExchangeTokenUrlBuilder clientSecret(String value) {
        return addRawParam("client_secret", value);
    }

    public GoogleExchangeTokenUrlBuilder grantType(String value) {
        return addParam("grant_type", value);
    }

}
