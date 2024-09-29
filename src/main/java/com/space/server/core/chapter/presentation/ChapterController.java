package com.space.server.core.chapter.presentation;

import com.space.server.core.chapter.presentation.dto.request.ChapterRequest;
import com.space.server.core.chapter.presentation.dto.response.ChapterResponse;
import com.space.server.core.chapter.service.CommandChapterService;
import com.space.server.core.chapter.service.QueryChapterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
  @Operation(summary = "챕터 생성", description = "챕터를 생성합니다.")
  public void createChapter(@RequestBody ChapterRequest request) {
    commandChapterService.createChapter(request.toEntity());
  }

  @GetMapping("{chapter-id}")
  @Operation(summary = "챕터 조회", description = "해당 챕터를 조회합니다.")
  public ChapterResponse readOne(
      @Parameter(description = "챕터 ID", required = true) @PathVariable(name = "chapter-id") Long chapterId) {
    return ChapterResponse.from(queryChapterService.readOne(chapterId));
  }

  @GetMapping
  @Operation(summary = "모든 챕터 조회", description = "모든 챕터를 조회합니다.")
  public List<ChapterResponse> readAll() {
    return queryChapterService.readAll().stream()
        .map(ChapterResponse::from)
        .toList();
  }

  @PutMapping("/{chapter-id}")
  @Operation(summary = "챕터 수정", description = "해당 챕터를 업데이트합니다.")
  public void updateChapter(
      @Parameter(description = "챕터 ID", required = true) @PathVariable(name = "chapter-id") Long chapterId,
      @RequestBody ChapterRequest request) {
    commandChapterService.updateChapter(chapterId, request.toEntity());
  }

  @DeleteMapping("/{chapter-id}")
  @Operation(summary = "챕터 삭제", description = "해당 챕터를 삭제합니다.")
  public void deleteChapter(@Parameter(description = "챕터 ID", required = true) @PathVariable(name = "chapter-id") Long chapterId) {
    commandChapterService.deleteChapter(chapterId);
  }
}
