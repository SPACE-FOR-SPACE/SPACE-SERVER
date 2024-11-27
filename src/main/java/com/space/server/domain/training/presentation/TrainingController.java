package com.space.server.domain.training.presentation;

import com.space.server.domain.training.domain.Training;
import com.space.server.domain.training.presentation.dto.request.CreateTrainingRequest;
import com.space.server.domain.training.service.CommandTrainingService;
import com.space.server.domain.training.service.QueryTrainingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.space.server.common.jwt.util.AuthenticationUtil.getMemberId;

@RestController
@RequiredArgsConstructor
@Tag(name = "Training", description = "클래스룸 API")
public class TrainingController {

    private final CommandTrainingService commandTrainingService;
    private final QueryTrainingService queryTrainingService;

    @PostMapping("/trainings")
    @Operation(summary = "클래스룸 생성", description = "클래스룸을 생성합니다.")
    public void createTraining(@RequestBody CreateTrainingRequest request) {

        commandTrainingService.create(getMemberId(), request.toEntity(request));

    }

    @GetMapping("/trainings")
    @Operation(summary = "사용자의 클래스룸 조회", description = "특정 사용자에 대한 모든 클래스룸을 조회합니다.")
    public List<Training> findTrainingsByUserId() {
        return queryTrainingService.findAllTrainingsByUserId(getMemberId());
    }


}
