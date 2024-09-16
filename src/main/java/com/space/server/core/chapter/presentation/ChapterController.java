package com.space.server.core.chapter.presentation;

import com.space.server.core.chapter.presentation.dto.request.ChapterRequest;
import com.space.server.core.chapter.presentation.dto.response.ChapterResponse;
import com.space.server.core.chapter.service.CommandChapterService;
import com.space.server.core.chapter.service.QueryChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chapters")
public class ChapterController {

  private final CommandChapterService commandChapterService;
  private final QueryChapterService queryChapterService;

  @PostMapping("/chapter")
  public void createChapter(@RequestBody ChapterRequest request) {
    commandChapterService.createChapter(request.toEntity());
  }

  @GetMapping("{chapter-id}")
  public ChapterResponse readOne(@PathVariable(name = "chapter-id") Long chapterId) {
    return ChapterResponse.from(queryChapterService.readOne(chapterId));
  }

  @GetMapping("chapter")
  public List<ChapterResponse> readAll() {
    return queryChapterService.readAll().stream()
        .map(ChapterResponse::from)
        .toList();
  }

  @PutMapping("/{chapter-id}")
  public void updateChapter(
      @PathVariable(name = "chapter-id") Long chapterId,
      @RequestBody ChapterRequest request
  ) {
    commandChapterService.updateChapter(chapterId, request.toEntity());
  }

  @DeleteMapping("/{chapter-id}")
  public void deleteChapter(@PathVariable(name = "chapter-id") Long chapterId) {
    commandChapterService.deleteChapter(chapterId);
  }
}
