package org.springframework.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import org.springframework.social.qq.api.QQ;

/**
 * @author renq
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String appId, String appSecret) {
        super("qq", new QQServiceProvider(appId, appSecret), new QQAdapter());
    }

    public QQConnectionFactory(String providerId, OAuth2ServiceProvider<QQ> serviceProvider, ApiAdapter<QQ> apiAdapter) {
        super(providerId, serviceProvider, apiAdapter);
    }
}
