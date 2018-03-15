package pt.isel.seginf.openid.webapp.url.builder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.function.Supplier;

public abstract class BaseUrlBuilder<EXTENDED> {

    private final StringBuilder sb;
    private Supplier<String> paramSep = () -> {
        paramSep = () -> "&";
        return "";
    };

    protected BaseUrlBuilder(String base) {
        sb = new StringBuilder(base);
    }

    protected abstract EXTENDED self();

    protected final EXTENDED addParam(String name, String param) {
        sb.append(paramSep.get()).append(name).append("=").append(sanitize(param));
        return self();
    }

    protected final EXTENDED addRawParam(String name, String param) {
        sb.append(paramSep.get()).append(name).append("=").append(param);
        return self();
    }

    private String sanitize(String param) {
        try {
            return URLEncoder.encode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new Error("UTF-8 should be supported!!!");
        }
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    public final EXTENDED clientId(String value) {
        return addParam("client_id", value);
    }

    public final EXTENDED redirectUri(String value) {
        return addRawParam("redirect_uri", value);
    }
}
