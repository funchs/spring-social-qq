package org.springframework.social.qq.connect;

import com.alibaba.fastjson.JSONObject;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;

/**
 * @author renq
 */
public class QQOAuth2Template extends OAuth2Template {

    private static final String URI_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    private static final String URI_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQOAuth2Template(String appId, String appSecret) {
        super(appId, appSecret, URI_AUTHORIZE, URI_ACCESS_TOKEN);
        setUseParametersForClientAuthentication(true);
    }
}
