package com.space.server.ai.presentation.dto.response;

public record AiResponse (
    boolean isSuccess,
    Long consistency,
    String feedback,
    Integer[][] map
){ }

//{
//         "isSuccess" : false,
//         "consistency" : 0,
//         "feedback" : "오른쪽으로 몇 번, 아래로 몇 번, 왼쪽으로 몇 번 가면 될 것 같아요. 여유가 된다면 보물 상자도 여는 것이 좋을 것 같아요.",
//         "map" : [[3, 3, 3, 3, 3, 3, 3],
//            [3, 0, 2, 2, 2, 2, 3],
//            [3, 3, 3, 3, 3, 2, 3],
//            [3, 3, 3, 3, 3, 7, 3],
//            [3, 3, 3, 3, 3, 2, 3],
//            [3, 1, 2, 2, 2, 8, 3],
//            [3, 3, 3, 3, 3, 3, 3]]
//}
