package pt.isel.seginf.openid.webapp.model.google;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class DecodedIdToken {

    private String iss,
            at_hash,
            email_verified,
            sub,
            azp,
            email,
            aud,
            iat,
            exp,
            nonce,
            hd;

    @JsonGetter
    public String getIss() {
        return iss;
    }

    @JsonGetter
    public String getAt_hash() {
        return at_hash;
    }

    @JsonGetter
    public String getEmail_verified() {
        return email_verified;
    }

    @JsonGetter
    public String getSub() {
        return sub;
    }

    @JsonGetter
    public String getAzp() {
        return azp;
    }

    @JsonGetter
    public String getEmail() {
        return email;
    }

    @JsonGetter
    public String getAud() {
        return aud;
    }

    @JsonGetter
    public String getIat() {
        return iat;
    }

    @JsonGetter
    public String getExp() {
        return exp;
    }

    @JsonGetter
    public String getNonce() {
        return nonce;
    }

    @JsonGetter
    public String getHd() {
        return hd;
    }

    @JsonSetter
    public void setIss(String iss) {
        this.iss = iss;
    }

    @JsonSetter
    public void setAt_hash(String at_hash) {
        this.at_hash = at_hash;
    }

    @JsonSetter
    public void setEmail_verified(String email_verified) {
        this.email_verified = email_verified;
    }

    @JsonSetter
    public void setSub(String sub) {
        this.sub = sub;
    }

    @JsonSetter
    public void setAzp(String azp) {
        this.azp = azp;
    }

    @JsonSetter
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonSetter
    public void setAud(String aud) {
        this.aud = aud;
    }

    @JsonSetter
    public void setIat(String iat) {
        this.iat = iat;
    }

    @JsonSetter
    public void setExp(String exp) {
        this.exp = exp;
    }

    @JsonSetter
    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    @JsonSetter
    public void setHd(String hd) {
        this.hd = hd;
    }

    @Override
    public String toString() {
        return "DecodedIdToken{" +
                "iss='" + iss + '\'' +
                ", at_hash='" + at_hash + '\'' +
                ", email_verified='" + email_verified + '\'' +
                ", sub='" + sub + '\'' +
                ", azp='" + azp + '\'' +
                ", email='" + email + '\'' +
                ", aud='" + aud + '\'' +
                ", iat='" + iat + '\'' +
                ", exp='" + exp + '\'' +
                ", nonce='" + nonce + '\'' +
                ", hd='" + hd + '\'' +
                '}';
    }
}
