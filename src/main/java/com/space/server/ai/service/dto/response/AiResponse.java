package com.space.server.ai.service.dto.response;

import java.util.Map;

public record AiResponse (
    Boolean isSuccess,
    Long accuracy,
    String feedback,
    Integer[][] map,
    String[] move,
    Map<String, String> mapObject
) {}
