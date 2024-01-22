package com.kuroshan.bookmarker.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

  @Query("SELECT new com.kuroshan.bookmarker.domain.BookmarkDTO(b.id, b.title, b.url, b.createdAt) FROM Bookmark b")
  Page<BookmarkDTO> findBookmarks(Pageable pageable);

  @Query("""
  SELECT new com.kuroshan.bookmarker.domain.BookmarkDTO(b.id, b.title, b.url, b.createdAt) FROM Bookmark b 
  WHERE lower(b.title) LIKE lower(concat('%', :query, '%'))
  """)
  Page<BookmarkDTO> searchBookmarks(String query, Pageable pageable);

}
