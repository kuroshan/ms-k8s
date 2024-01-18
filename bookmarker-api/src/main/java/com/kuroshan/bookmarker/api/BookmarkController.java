package com.kuroshan.bookmarker.api;

import java.util.List;

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
  public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page) {

    return bookmarkService.getBookmarks(page);
  }

}
