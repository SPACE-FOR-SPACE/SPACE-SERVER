package com.space.server.domain.state.presentation;

import com.space.server.domain.state.presentation.dto.request.CheckStateRequest;
import com.space.server.domain.state.presentation.dto.response.CheckStateResponse;
import com.space.server.domain.state.presentation.dto.response.StateResponse;
import com.space.server.domain.state.service.CommandStateService;
import com.space.server.domain.state.service.QueryStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/states")
public class StateController {

    private final CommandStateService commandStateService;
    private final QueryStateService queryStateService;

    @GetMapping("/{state-id}")
    public StateResponse readOne(
            @PathVariable("state-id") Long stateId
    ) {
        return StateResponse.from(queryStateService.readOne(stateId));
    }

    @DeleteMapping("/{state-id}")
    public void deleteState(
            @PathVariable("state-id") Long stateId
    ) {
        commandStateService.delete(stateId);
    }

    @GetMapping("/checkStates")
    public List<CheckStateResponse> findByChapterUserId(CheckStateRequest request) {
        return queryStateService.findByChapterUserId(request.chapterId(), request.userId()).stream()
            .map(CheckStateResponse::from)
            .toList();
    }
}
