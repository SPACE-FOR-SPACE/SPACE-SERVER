package com.space.server.core.chapter.service.implementation;

import com.space.server.common.exception.ErrorCode;
import com.space.server.common.exception.SpaceException;
import com.space.server.core.chapter.domain.Chapter;
import com.space.server.core.chapter.domain.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterReader {

  private final ChapterRepository chapterRepository;

  public Chapter findById(Long id) {
    return chapterRepository.findById(id)
        .orElseThrow(() -> new SpaceException(ErrorCode.CHAPTER_NOT_FOUND));
  }

  public List<Chapter> findAll() {
    return chapterRepository.findAllByOrderById();
  }
}
