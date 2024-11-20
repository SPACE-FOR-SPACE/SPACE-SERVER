package com.space.server.domain.chapter.adapter.in.web;

import com.space.server.domain.chapter.adapter.in.web.dto.response.ChapterResponse;
import com.space.server.domain.chapter.application.port.in.GetChapterQuery;
import com.space.server.domain.chapter.application.port.in.GetChaptersQuery;
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

  private final GetChaptersQuery getChaptersQuery;
  private final GetChapterQuery getChapterQuery;

  @GetMapping("{chapter-id}")
  @Operation(summary = "챕터 조회", description = "해당 챕터를 조회합니다.")
  public ChapterResponse readOne(@Parameter(description = "챕터 ID", required = true) @PathVariable(name = "chapter-id") Long chapterId) {
    return ChapterResponse.from(getChapterQuery.getChapter(chapterId));
  }

  @GetMapping
  @Operation(summary = "모든 챕터 조회", description = "모든 챕터를 조회합니다.")
  public List<ChapterResponse> readAll() {
    return getChaptersQuery.getChapters().stream()
        .map(ChapterResponse::from)
        .toList();
  }
}
