package com.scopisto.newsfeed.rss.consumer;

import com.rometools.rome.feed.synd.SyndEntry;
import com.scopisto.newsfeed.api.NewsItemData;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface RssConsumerMapper {

  @Mapping(target = "id", source = "uri")
  @Mapping(target = "description", source = "entry.description.value")
  @Mapping(target = "imagePath", expression = "java(org.springframework.util.CollectionUtils.isEmpty(entry.getEnclosures()) ? "
      + "null : entry.getEnclosures().get(0).getUrl())")
  NewsItemData toData(SyndEntry entry);

  List<NewsItemData> toData(Iterable<SyndEntry> entries);
}
