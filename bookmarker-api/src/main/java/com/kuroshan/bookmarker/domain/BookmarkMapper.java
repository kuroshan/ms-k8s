package com.kuroshan.bookmarker.domain;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookmarkMapper {

  BookmarkDTO toDTO(Bookmark bookmark);

  Bookmark toEntity(BookmarkDTO bookmarkDTO);

}
