package org.springframework.social.qq.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.qq.api.QQ;
import org.springframework.social.qq.connect.QQConnectionFactory;

/**
 * @author renq
 */
@Configuration
@ConditionalOnClass({ SocialConfigurerAdapter.class, QQConnectionFactory.class })
@ConditionalOnProperty(prefix = "spring.social.qq", name = "app-id")
@AutoConfigureBefore(SecurityAutoConfiguration.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class QQAutoConfiguration {

	@Configuration
	@EnableSocial
	@EnableConfigurationProperties(QQProperties.class)
	@ConditionalOnWebApplication
	protected static class QQConfigurerAdapter extends SocialConfigurerAdapter {

		private final QQProperties properties;

		protected QQConfigurerAdapter(QQProperties properties) {
			this.properties = properties;
		}

		@Bean
		@ConditionalOnMissingBean(QQ.class)
		@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
		public QQ qq(ConnectionRepository repository) {
			Connection<QQ> connection = repository
					.findPrimaryConnection(QQ.class);
			return connection != null ? connection.getApi() : null;
		}

		@Override
		public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
			configurer.addConnectionFactory(this.createConnectionFactory());
		}

		private ConnectionFactory<QQ> createConnectionFactory() {
			return new QQConnectionFactory(this.properties.getAppId(),
					this.properties.getAppSecret());
		}

	}

}