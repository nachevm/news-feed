package com.scopisto.newsfeed.api;

import lombok.NonNull;

public interface NewsItemService {

  NewsItemPage findAll(PageInput input);

  Iterable<NewsItemData> saveAll(@NonNull Iterable<NewsItemData> data);
}
