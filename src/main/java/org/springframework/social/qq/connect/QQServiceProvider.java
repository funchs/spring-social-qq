package org.springframework.social.qq.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.qq.api.QQ;
import org.springframework.social.qq.api.impl.QQTemplate;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;
    private static final String URI_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    private static final String URI_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(OAuth2Operations oauth2Operations) {
        super(oauth2Operations);
    }

    public QQServiceProvider(String appId, String appSecret) {
        super(getOAuth2Template(appId, appSecret));
        this.appId = appId;
    }

    private static OAuth2Template getOAuth2Template(String appId, String appSecret) {
        OAuth2Template oAuth2Template = new OAuth2Template(appId, appSecret, URI_AUTHORIZE, URI_ACCESS_TOKEN);
        oAuth2Template.setUseParametersForClientAuthentication(true);
        return oAuth2Template;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQTemplate(appId, accessToken);
    }
}
