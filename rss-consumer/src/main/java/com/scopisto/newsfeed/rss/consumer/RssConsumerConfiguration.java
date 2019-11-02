package com.scopisto.newsfeed.rss.consumer;

import java.net.MalformedURLException;
import java.net.URL;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableConfigurationProperties(RssConsumerProperties.class)
@EnableScheduling
@PropertySource("classpath:/rss-consumer.properties")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class RssConsumerConfiguration {

  private final RssConsumerProperties properties;

  @Bean
  URL feedUrl() throws MalformedURLException {
    return new URL(properties.getFeedUrl());
  }
}
