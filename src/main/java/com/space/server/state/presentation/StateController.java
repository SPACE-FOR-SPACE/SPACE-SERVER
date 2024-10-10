package com.space.server.state.presentation;

import com.space.server.state.presentation.dto.response.StateResponse;
import com.space.server.state.service.CommandStateService;
import com.space.server.state.service.QueryStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StateController {

    private final CommandStateService commandStateService;
    private final QueryStateService queryStateService;


    @GetMapping("/states/{state-id}")
    public StateResponse readOne(
            @PathVariable("state-id") Long stateId
    ) {
        return StateResponse.from(queryStateService.readOne(stateId));
    }

    @DeleteMapping("/states/{state-id}")
    public void deleteState(
            @PathVariable("state-id") Long stateId
    ) {
        commandStateService.delete(stateId);
    }


}
