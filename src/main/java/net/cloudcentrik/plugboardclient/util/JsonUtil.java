package net.cloudcentrik.plugboardclient.util;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonUtil {
    public static void prettyPrint(JsonElement jsonObject){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jsonObject);
        System.out.println(json);
    }

    public static JsonObject wrapInLang(String propertyName,String propertyValue, String swedish, String english){
        JsonObject object=Json.object().add(propertyName,propertyValue)
                .add(propertyName.concat("_lang"),Json.object()
                        .add("sv",swedish).add("en",english));
        return object;
    }

    public static com.google.gson.JsonObject convertToGson(com.eclipsesource.json.JsonObject simpleJsonObject){
        JsonParser parser = new JsonParser();
        com.google.gson.JsonObject gsonJsonObject = parser.parse(simpleJsonObject.toString()).getAsJsonObject();
        return gsonJsonObject;
    }
}
