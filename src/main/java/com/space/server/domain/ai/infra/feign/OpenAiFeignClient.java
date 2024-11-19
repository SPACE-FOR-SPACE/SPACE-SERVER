package com.space.server.domain.ai.infra.feign;

import com.space.server.common.config.OpenAiFeignConfig;
import com.space.server.domain.ai.service.dto.request.internal.AiChat;
import com.space.server.domain.ai.service.dto.request.internal.AiRunsRequest;
import com.space.server.domain.ai.service.dto.response.internal.AiAllMessagesResponse;
import com.space.server.domain.ai.service.dto.response.internal.AiMessagesResponse;
import com.space.server.domain.ai.service.dto.response.internal.AiRunsResponse;
import com.space.server.domain.ai.service.dto.response.internal.AiThreadResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "OpenAiFeign",
        url = "https://api.openai.com/v1/threads",
        configuration = OpenAiFeignConfig.class
)
public interface OpenAiFeignClient {

    @PostMapping
    AiThreadResponse createThread();

    @PostMapping("/{threadId}/messages")
    AiMessagesResponse createMessage(@PathVariable("threadId") String threadId,
                                     @RequestBody AiChat aiChat);

    @GetMapping("/{threadId}/messages")
    AiAllMessagesResponse getAllMessages(@PathVariable("threadId") String threadId);

    @GetMapping("/{threadId}/messages/{messageId}")
    AiMessagesResponse getMessage(@PathVariable("threadId") String threadId,
                                  @PathVariable("messageId") String messageId);

    @PostMapping("/{threadId}/runs")
    AiRunsResponse createRun(@PathVariable("threadId") String threadId,
                             @RequestBody AiRunsRequest request);

    @GetMapping("/{threadId}/runs/{runId}")
    AiRunsResponse getRun(@PathVariable("threadId") String threadId,
                          @PathVariable("runId") String runId);
}