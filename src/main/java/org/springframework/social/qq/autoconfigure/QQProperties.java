package org.springframework.social.qq.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author renq
 */
@ConfigurationProperties(prefix = "spring.social.qq")
public class QQProperties {

    private String appId;
    private String appSecret;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
