package com.scopisto.newsfeed.rss.consumer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "rss-consumer")
class RssConsumerProperties {

  private String feedUrl;
}
