package com.space.server.domin.chapter.presentation;

import com.space.server.domin.chapter.presentation.dto.response.ChapterResponse;
import com.space.server.domin.chapter.service.QueryChapterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chapters")
@Tag(name = "Chapter", description = "챕터 API")
public class ChapterController {

  private final QueryChapterService queryChapterService;

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
}
