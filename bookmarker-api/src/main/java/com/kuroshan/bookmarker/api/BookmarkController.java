package com.kuroshan.bookmarker.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuroshan.bookmarker.domain.BookmarkService;
import com.kuroshan.bookmarker.domain.BookmarksDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

  private final BookmarkService bookmarkService;

  @GetMapping
  public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page, 
                                  @RequestParam(name = "query", defaultValue = "") String query) {
    if(query == null && query.isEmpty()) {
      return bookmarkService.getBookmarks(page);
    }
    return bookmarkService.searchBookmarks(query, page);
  }

}
