package com.scopisto.newsfeed.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "news_item")
class NewsItem {

  @Id
  @GeneratedValue(generator = "null-only-uuid")
  @GenericGenerator(name = "null-only-uuid", strategy = "com.scopisto.newsfeed.domain.NullOnlyUUIDGenerator")
  private String id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private LocalDateTime publishedDate;

  private String imagePath;

  @Override
  public boolean equals(Object o) {
    return this == o || (o instanceof NewsItem && id != null && id.equals(((NewsItem) o).getId()));
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
