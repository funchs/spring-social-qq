package org.springframework.social.qq.config.xml;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.social.config.xml.AbstractProviderConfigBeanDefinitionParser;
import org.springframework.social.qq.config.support.QQApiHelper;
import org.springframework.social.qq.connect.QQConnectionFactory;
import org.springframework.social.qq.security.QQAuthenticationService;
import org.springframework.social.security.provider.SocialAuthenticationService;

import java.util.Map;

/**
 * @author renq
 */
public class QQConfigBeanDefinitionParser extends AbstractProviderConfigBeanDefinitionParser {

    protected QQConfigBeanDefinitionParser() {
        super(QQConnectionFactory.class, QQApiHelper.class);
    }

    @Override
    protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
        return QQAuthenticationService.class;
    }

    @Override
    protected BeanDefinition getConnectionFactoryBeanDefinition(String appId, String appSecret, Map<String, Object> allAttributes) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(QQConnectionFactory.class).addConstructorArgValue(appId).addConstructorArgValue(appSecret);
        return builder.getBeanDefinition();
    }

}
