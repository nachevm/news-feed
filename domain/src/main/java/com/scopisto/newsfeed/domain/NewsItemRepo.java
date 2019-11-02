package com.scopisto.newsfeed.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface NewsItemRepo extends JpaRepository<NewsItem, String> {

}
