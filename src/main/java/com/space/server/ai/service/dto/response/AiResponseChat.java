package com.space.server.ai.service.dto.response;

import java.util.List;

public record AiResponseChat(
    boolean isSuccess,
    Integer accuracy,
    String feedback,
    List<Integer> move,
    Integer[][] map
) {
}
//isSuccess, accuracy, feedback, move, map