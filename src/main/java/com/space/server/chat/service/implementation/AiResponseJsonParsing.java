package com.space.server.chat.service.implementation;

import com.space.server.ai.service.dto.response.AiResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Parser;
import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Service
public class AiResponseJsonParsing {

    public AiResponse jsonCreator(String contents, Map<String, String> mapObject) {
        String result = contents.substring(7, contents.length() - 3);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        Integer[][] map = null;
        String[] move = null;

        try {
            jsonObject = (JSONObject) parser.parse(result);
            map = mapIntegerCreator(jsonObject.get("map").toString());
            move = moveStringCreator(jsonObject.get("move").toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        log.info("json : " + jsonObject.toJSONString());
        return new AiResponse(
                (Boolean) jsonObject.get("isSuccess"),
                (Long) jsonObject.get("accuracy"),
                (String) jsonObject.get("feedback"),
                map,
                move,
                mapObject
        );
    }

    public Integer[][] mapIntegerCreator(String map) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(map);
        Integer[][] integerArray = new Integer[jsonArray.size()][];

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONArray innerArray = (JSONArray) jsonArray.get(i);
            integerArray[i] = new Integer[innerArray.size()];
            for (int j = 0; j < innerArray.size(); j++) {
                integerArray[i][j] = ((Long) innerArray.get(j)).intValue();
            }
        }

        return integerArray;
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
}
