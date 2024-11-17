package com.space.server.common.infra.presentation;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

@Setter
@RestController
@ConfigurationProperties(prefix = "server")
public class HealthCheckController {

    private String env;

    private String port;

    private String serverAddress;

    private String serverName;

    @GetMapping("/hc")
    public ResponseEntity<Map> healthCheck() {
        Map<String, String> responseData = new TreeMap<>();
        responseData.put("env", env);
        responseData.put("port", port);
        responseData.put("serverAddress", serverAddress);
        responseData.put("serverName", serverName);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/env")
    public ResponseEntity<String> getEnv() {
        return ResponseEntity.ok(env);
    }

}
