package com.Freelancer.getcitations_freelancer.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONParserUtil {

    public JsonNode parseStringToJson(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        
        try {
            jsonNode = objectMapper.readTree(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonNode;
    }
}