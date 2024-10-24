package com.space.server.chat.service.implementation;

import com.space.server.ai.service.dto.response.AiResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class AiResponseJsonParsing {

    public AiResponse jsonCreator(String contents, Map<String, String> mapObject) {
        String result = contents.substring(7, contents.length() - 3);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        Long[] score = null;
        String[] move = null;

        try {
            jsonObject = (JSONObject) parser.parse(result);
            score = successCheckListIntegerCreator(jsonObject.get("score").toString());
            move = moveStringCreator(jsonObject.get("move").toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        log.info("json : " + jsonObject.toJSONString());
        return new AiResponse(
                (Boolean) jsonObject.get("isSuccess"),
                score,
                (String) jsonObject.get("feedback"),
                move
        );
    }

    public String[] moveStringCreator(String move) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(move);
        String[] stringArray = new String[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonArray.get(i) instanceof Long) {
                stringArray[i] = ((Long) jsonArray.get(i)).toString();
            } else {
                stringArray[i] = (String) jsonArray.get(i);
            }
        }

        return stringArray;
    }

    public Long[] successCheckListIntegerCreator(String score) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(score);
        Long[] longArray = new Long[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            longArray[i] = ((Long) jsonArray.get(i));
        }

        return longArray;
    }

}
