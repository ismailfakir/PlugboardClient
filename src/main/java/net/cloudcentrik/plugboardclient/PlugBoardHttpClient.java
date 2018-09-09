package net.cloudcentrik.plugboardclient;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import net.cloudcentrik.plugboardclient.restclient.RestClient;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static net.cloudcentrik.plugboardclient.ClientCredientils.PLUGBOARD_URL;
import static net.cloudcentrik.plugboardclient.ClientCredientils.PLUGBOARD_USER_NAME;
import static net.cloudcentrik.plugboardclient.ClientCredientils.PLUGBOARD_USER_PASSWORD;
import static net.cloudcentrik.plugboardclient.model.PlugboardFile.parseFile;

public class PlugBoardHttpClient {

    private static RestClient buildPlugboardClient() throws Exception{
        Map<String, String> headers=new HashMap<>();
        headers.put("X-Tenant",ClientCredientils.PLUGBOARD_X_TENANT);
        headers.put("X-ConnectionId",ClientCredientils.PLUGBOARD_X_CONNECTION_ID);
        return RestClient.getBasicJsonClient(PLUGBOARD_URL,PLUGBOARD_USER_NAME,PLUGBOARD_USER_PASSWORD,headers,true);
    }

    public static JsonArray getProductList(String date) throws Exception{
        RestClient restClient=buildPlugboardClient();
        Map<String,String> queryParam=new HashMap<>();
        queryParam.put("modifiedAtOrAfter",date);
        return restClient.getRequest("product",queryParam).asArray();
    }

    //FILE

    public static JsonObject createFile(String name,String mimeType,boolean isPublicAccess) throws Exception{

        RestClient restClient=buildPlugboardClient();
        return restClient.postRequest("file",null,parseFile(name,mimeType,isPublicAccess)).asObject();
    }

    public static JsonObject postFile(String filePath, String mimeType,String localId) throws Exception{

        File file = new File(filePath);
        FileEntity entity = new FileEntity(file,
                ContentType.create(mimeType, "UTF-8"));

        RestClient restClient=buildPlugboardClient();
        return restClient.putHttpEntityRequest("file/"+localId+"/data",null,entity).asObject();
    }
}
