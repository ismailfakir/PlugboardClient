package net.cloudcentrik.plugboardclient;

import com.eclipsesource.json.JsonObject;
import net.cloudcentrik.plugboardclient.model.Product;
import net.cloudcentrik.plugboardclient.restclient.RestClient;

import java.util.HashMap;
import java.util.Map;

import static net.cloudcentrik.plugboardclient.ClientCredientils.PLUGBOARD_URL;
import static net.cloudcentrik.plugboardclient.ClientCredientils.PLUGBOARD_USER_NAME;
import static net.cloudcentrik.plugboardclient.ClientCredientils.PLUGBOARD_USER_PASSWORD;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main( String[] args ) throws Exception
    {
        System.out.println( "connecting to plugboard" );

        //String timestamp = TimeUtil.formatTime("2015-01-02T03:04:05.000Z");

        //System.out.println(timestamp);

        //JsonArray jsonObject=PlugboardJerseyClient.listPlugboardOrder("2015-01-02T03:04:05.000Z");
        //JsonUtil.prettyPrint(jsonObject);

        //String string=JsonUtil.wrapInLang("description","namn","name").toString(WriterConfig.PRETTY_PRINT);
        //System.out.println(Product.parseProduct().toString(WriterConfig.PRETTY_PRINT));

        //JsonObject jsonObject=PlugboardJerseyClient.addPlugboardProduct(convertToGson(Product.parseProduct()));

        /*JsonArray jsonObject=PlugboardJerseyClient.listProduct(TimeUtil.formatTime("2017-09-01"));


        for(JsonValue object:jsonObject){
            JsonUtil.prettyPrint(object.asObject().get("remoteId"));
            JsonUtil.prettyPrint(object.asObject().get("localId"));
        }*/

        //RestClientTemp.build();

        /*
        * TODO update product
         */

        Map<String, String> headers=new HashMap<>();
        headers.put("X-Tenant",ClientCredientils.PLUGBOARD_X_TENANT);
        headers.put("X-ConnectionId",ClientCredientils.PLUGBOARD_X_CONNECTION_ID);
        RestClient restClient=RestClient.getBasicJsonClient(PLUGBOARD_URL,PLUGBOARD_USER_NAME,PLUGBOARD_USER_PASSWORD,headers,true);

        Map<String, String> params=new HashMap<>();
        params.put("modifiedAtOrAfter","2015-01-02T03:04:05.000Z");
        //restClient.getRequest("product",params);

        //String localId="000812acedf4c99865ab808106edfef2";
        JsonObject jsonObject=new JsonObject().add("remoteId","1122334455");

        //restClient.putRequest("product/"+localId,null,jsonObject);

        //restClient.postRequest("product",null,Product.parseProduct());

        //PlugBoardHttpClient.getProductList("2015-01-02T03:04:05.000Z");
        //PlugBoardHttpClient.createFile("product1001.jpeg","image/jpeg",true);

        String localFileId="000812acedf4c99865ab808106edff8f";

        PlugBoardHttpClient.postFile("/home/ismail/Desktop/minix.jpeg","image/jpeg",localFileId);




    }

}
