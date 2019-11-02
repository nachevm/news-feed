package com.scopisto.newsfeed.domain;

import com.scopisto.newsfeed.api.NewsItemData;
import com.scopisto.newsfeed.api.NewsItemPage;
import com.scopisto.newsfeed.api.NewsItemService;
import com.scopisto.newsfeed.api.PageInput;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class NewsItemDomainService implements NewsItemService {

  private final NewsItemRepo newsItemRepo;
  private final NewsItemMapper newsItemMapper;

  @Override
  @Transactional(readOnly = true)
  public NewsItemPage findAll(PageInput input) {
    return newsItemMapper.toPage(newsItemRepo.findAll(newsItemMapper.toPageable(input)));
  }

  @Override
  @Transactional
  public Iterable<NewsItemData> saveAll(@NonNull Iterable<NewsItemData> data) {
    return newsItemMapper.toData(newsItemRepo.saveAll(newsItemMapper.toDomain(data)));
  }
}
