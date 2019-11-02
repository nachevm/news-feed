package com.scopisto.newsfeed.api.graphql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestAutoConfiguration;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.oembedler.moon.graphql.boot.GraphQLInstrumentationAutoConfiguration;
import com.oembedler.moon.graphql.boot.GraphQLWebAutoConfiguration;
import com.scopisto.newsfeed.api.NewsItemData;
import com.scopisto.newsfeed.api.NewsItemPage;
import com.scopisto.newsfeed.api.NewsItemService;
import com.scopisto.newsfeed.api.PageInput;
import com.zhokhov.graphql.datetime.boot.GraphQLDateTimeAutoConfiguration;
import graphql.kickstart.tools.boot.GraphQLJavaToolsAutoConfiguration;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.export.simple.SimpleMetricsExportAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@GraphQLTest(classes = {GraphQLInstrumentationAutoConfiguration.class,
    ServletWebServerFactoryAutoConfiguration.class,
    GraphQLJavaToolsAutoConfiguration.class,
    GraphQLWebAutoConfiguration.class,
    GraphQLTestAutoConfiguration.class,
    PropertySourcesPlaceholderConfigurer.class,
    WebSocketServletAutoConfiguration.class,
    MetricsAutoConfiguration.class,
    SimpleMetricsExportAutoConfiguration.class,
    JacksonAutoConfiguration.class,
    GraphQLDateTimeAutoConfiguration.class})
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE, onConstructor_ = @Autowired)
class NewsItemQueryResolverTest {

  private final GraphQLTestTemplate graphQLTestTemplate;

  @MockBean
  private NewsItemService newsItemService;

  @Test
  void newsItems() throws IOException {
    doReturn(NewsItemPage.builder()
        .content(List.of(NewsItemData.builder().id("1").title("T").description("D").publishedDate(LocalDateTime.now()).build()))
        .totalPages(3)
        .totalElements(3)
        .build()
    ).when(newsItemService).findAll(PageInput.builder().pageNumber(2).size(1).build());

    assertThat(graphQLTestTemplate.postForResource("newsItems.graphql").context().jsonString())
        .isEqualTo("{\"data\":{\"newsItems\":{\"content\":[{\"id\":\"1\",\"title\":\"T\"}],\"totalElements\":3}}}");
  }
}
