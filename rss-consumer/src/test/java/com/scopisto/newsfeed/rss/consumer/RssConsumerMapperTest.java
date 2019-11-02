package com.scopisto.newsfeed.rss.consumer;

import static java.time.ZoneOffset.UTC;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEnclosureImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.scopisto.newsfeed.api.NewsItemData;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class RssConsumerMapperTest {

  private final RssConsumerMapper mapper = Mappers.getMapper(RssConsumerMapper.class);

  @Test
  void toData() {
    List<SyndEntry> entries = List.of(createSyndEntry("0"), createSyndEntry("1"));

    List<NewsItemData> items = mapper.toData(entries);

    assertEquals(2, items.size());
    for (int i = 0; i < items.size(); i++) {
      assertEquals(i + "", items.get(i).getId());
      assertEquals("t", items.get(i).getTitle());
      assertEquals("d", items.get(i).getDescription());
      assertEquals("url", items.get(i).getImagePath());
      assertEquals(LocalDateTime.ofInstant(entries.get(i).getPublishedDate().toInstant(), UTC), items.get(i).getPublishedDate());
    }
  }

  private SyndEntry createSyndEntry(String uri) {
    SyndContent syndContent = new SyndContentImpl();
    syndContent.setValue("d");
    SyndEntry syndEntry = new SyndEntryImpl();
    syndEntry.setUri(uri);
    syndEntry.setDescription(syndContent);
    syndEntry.setTitle("t");
    syndEntry.setPublishedDate(new Date());
    SyndEnclosure syndEnclosure = new SyndEnclosureImpl();
    syndEnclosure.setUrl("url");
    syndEntry.setEnclosures(List.of(syndEnclosure));
    return syndEntry;
  }
}
