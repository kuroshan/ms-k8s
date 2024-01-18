package com.kuroshan.bookmarker.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.kuroshan.bookmarker.domain.Bookmark;
import com.kuroshan.bookmarker.domain.BookmarkRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
  "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"
})
class BookmarkControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private BookmarkRepository bookmarkRepository;

  private List<Bookmark> bookmarks;

  @BeforeEach
  void setUp() {
    bookmarkRepository.deleteAllInBatch();
    bookmarks = new ArrayList<>();

    bookmarks.add(Bookmark.builder().title("title1").url("url1").build());
    bookmarks.add(Bookmark.builder().title("title2").url("url2").build());
    bookmarks.add(Bookmark.builder().title("title3").url("url3").build());
    bookmarks.add(Bookmark.builder().title("title4").url("url4").build());
    bookmarks.add(Bookmark.builder().title("title5").url("url5").build());
    bookmarks.add(Bookmark.builder().title("title6").url("url6").build());
    bookmarks.add(Bookmark.builder().title("title7").url("url7").build());
    bookmarks.add(Bookmark.builder().title("title8").url("url8").build());
    bookmarks.add(Bookmark.builder().title("title9").url("url9").build());
    bookmarks.add(Bookmark.builder().title("title10").url("url10").build());
    bookmarks.add(Bookmark.builder().title("title11").url("url11").build());
    bookmarks.add(Bookmark.builder().title("title12").url("url12").build());
    bookmarks.add(Bookmark.builder().title("title13").url("url13").build());
    bookmarks.add(Bookmark.builder().title("title14").url("url14").build());
    bookmarks.add(Bookmark.builder().title("title15").url("url15").build());

    bookmarkRepository.saveAll(bookmarks);
    
  }

  @ParameterizedTest
  @CsvSource({
    "1,15,2,1,true,false,true,false",
    "2,15,2,2,false,true,false,true",
  })
  void shoulGetBookmarks(int pageNo, int totalElements, int totalPages, int currentPage, 
                        boolean isFirst, boolean isLast,
                        boolean hasNext, boolean hasPrevious) throws Exception {
    mvc.perform(get("/api/bookmarks").param("page", String.valueOf(pageNo)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
      .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
      .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
      .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
      .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(isLast)))
      .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
      .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)));

  }
}
