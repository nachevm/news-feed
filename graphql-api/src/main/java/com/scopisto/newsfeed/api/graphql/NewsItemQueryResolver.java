package com.scopisto.newsfeed.api.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.scopisto.newsfeed.api.NewsItemPage;
import com.scopisto.newsfeed.api.NewsItemService;
import com.scopisto.newsfeed.api.PageInput;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
final class NewsItemQueryResolver implements GraphQLQueryResolver {

  private final NewsItemService newsItemService;

  NewsItemPage newsItems(PageInput input) {
    return newsItemService.findAll(input);
  }
}
