package com.scopisto.newsfeed.domain;

import com.scopisto.newsfeed.api.NewsItemData;
import com.scopisto.newsfeed.api.NewsItemPage;
import com.scopisto.newsfeed.api.PageInput;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Mapper(componentModel = "spring")
interface NewsItemMapper {

  Set<NewsItem> toDomain(Iterable<NewsItemData> data);

  List<NewsItemData> toData(Iterable<NewsItem> newsItems);

  default NewsItemPage toPage(Page<NewsItem> page) {
    return NewsItemPage.builder()
        .content(toData(page.getContent()))
        .totalPages(page.getTotalPages())
        .totalElements(page.getTotalElements())
        .build();
  }

  default Pageable toPageable(PageInput input) {
    return input == null ? Pageable.unpaged() : PageRequest.of(input.getPageNumber(), input.getSize());
  }
}
