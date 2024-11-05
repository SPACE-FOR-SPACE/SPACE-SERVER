package com.space.server.domain.chapter.service.implementation;

import com.space.server.domain.chapter.domain.Chapter;
import com.space.server.domain.chapter.domain.repository.ChapterRepository;
import com.space.server.domain.chapter.exception.ChapterNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterReader {

  private final ChapterRepository chapterRepository;

  public Chapter findById(Long id) {
    return chapterRepository.findById(id)
        .orElseThrow(ChapterNotFoundException::new);
  }

  public List<Chapter> findAll() {
    return chapterRepository.findAllByOrderById();
  }
}
