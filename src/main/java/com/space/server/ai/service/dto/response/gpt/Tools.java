package com.space.server.ai.service.dto.response.gpt;

import com.space.server.ai.service.dto.response.gpt.tools.*;

import java.util.Map;

public record Tools (
    String type,
    FileSearchTool file_search,
    FunctionTool function
){
}
//[
//  { type: "" },
//  { type: "", file_search: {} },
//  { type: "", function: {} },
//]
