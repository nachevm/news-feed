package com.scopisto.newsfeed.rss.consumer;

import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.scopisto.newsfeed.api.NewsItemService;
import java.io.IOException;
import java.net.URL;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
final class RssConsumerService {

  private final URL feedUrl;
  private final NewsItemService newsItemService;
  private final RssConsumerMapper rssConsumerMapper;

  @Scheduled(fixedRateString = "${rss-consumer.schedule.milliseconds}")
  void save() throws IOException, FeedException {
    log.info("Consuming rss feed from {}", feedUrl);
    try (var reader = new XmlReader(feedUrl)) {
      newsItemService.saveAll(rssConsumerMapper.toData(new SyndFeedInput().build(reader).getEntries()));
    }
  }
}
