package net.cloudcentrik.plugboardclient.model;

import com.eclipsesource.json.JsonObject;

public class PlugboardFile {

    public static JsonObject parseFile(String name,String mimeType,boolean isPublicAccess){
        JsonObject jsonObject=new JsonObject();
        jsonObject.add("name",name);
        jsonObject.add("mimeType",mimeType);
        jsonObject.add("publicAccess",isPublicAccess);
        return jsonObject;
    }
}
