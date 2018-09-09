package net.cloudcentrik.plugboardclient.util;

import com.eclipsesource.json.*;

public class JsonUtil {

    public static void prettyPrint(JsonValue jsonObject){

        System.out.println(jsonObject.toString(WriterConfig.PRETTY_PRINT));
    }

    public static JsonObject wrapInLang(String propertyName,String propertyValue, String swedish, String english){
        JsonObject object=Json.object().add(propertyName,propertyValue)
                .add(propertyName.concat("_lang"),Json.object()
                        .add("sv",swedish).add("en",english));
        return object;
    }

}
