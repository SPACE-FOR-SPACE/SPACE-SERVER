package com.space.server.domin.chat.service.implementation;

import com.space.server.ai.service.dto.response.AiResponse;
import com.space.server.domin.chat.exception.ChatJsonParseException;
import com.space.server.domin.chat.exception.MoveNotFitException;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class AiResponseJsonParsing {

    public AiResponse jsonCreator(String contents, Map<String, String> mapObject) {
        Pattern pattern = Pattern.compile("```json\\s*([\\s\\S]*?)\\s*```", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(contents);
        String result = null;
        while (matcher.find()) {
            result = matcher.group(1);
        }
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        Long[] score = null;
        String[] move = null;

        try {
            jsonObject = (JSONObject) parser.parse(result);
            score = successCheckListIntegerCreator(jsonObject.get("score").toString());
            move = moveStringCreator(jsonObject.get("move").toString());
        } catch (ParseException | NullPointerException e) {
            throw new ChatJsonParseException();
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
        Pattern pattern = Pattern.compile("^\\[\\s*(\"([udrl])\"|5)?(\\s*,\\s*(\"([udrl])\"|5))*\\s*]$");
        Matcher matcher = pattern.matcher(move);

        if (!matcher.matches()) {
            throw new MoveNotFitException();
        }

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
