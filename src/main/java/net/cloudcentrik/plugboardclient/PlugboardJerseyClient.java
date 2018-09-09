package net.cloudcentrik.plugboardclient;

import com.eclipsesource.json.*;
import javafx.util.Pair;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import static net.cloudcentrik.plugboardclient.util.Param.param;

public class PlugboardJerseyClient {
    private static Invocation.Builder getClient(String path, Pair<String,String> ...queryParams){
        HttpAuthenticationFeature authenticationFeature = HttpAuthenticationFeature.basic(
                ClientCredientils.PLUGBOARD_USER_NAME, ClientCredientils.PLUGBOARD_USER_PASSWORD);

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(authenticationFeature);
        clientConfig.register(RequestFilter.class);
        Client client = ClientBuilder.newClient(clientConfig);

        //MAIN ENTRY POINT
        WebTarget webTarget = client.target(ClientCredientils.PLUGBOARD_URL).path(path);
        for (Pair<String, String> queryParam : queryParams) {
            webTarget=webTarget.queryParam(queryParam.getKey(),queryParam.getValue());
        }

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("X-Tenant", ClientCredientils.PLUGBOARD_X_TENANT);
        invocationBuilder.header("X-ConnectionId", ClientCredientils.PLUGBOARD_X_CONNECTION_ID);

        return invocationBuilder;
    }

    private static JsonValue parsePlugboardResponse(Response response){
        JsonValue jsonElement=null;

        if(response.getStatus()==200||response.getStatus()==201){
            jsonElement = Json.parse(response.readEntity(String.class));
        }else if(response.getStatus()==400){
            jsonElement = Json.parse(response.readEntity(String.class));
        }

        return jsonElement;
    }

    //get as json object
    public static JsonObject getPlugboardJsonObject(String path, Pair<String,String> ...queryParam){
        JsonObject jsonObject=null;

        Invocation.Builder client=getClient(path,queryParam);
        Response response=client.get();

        return parsePlugboardResponse(response).asObject();
    }

    public static JsonArray getPlugboardJsonArray(String path, Pair<String,String> ...queryParam){
        JsonObject jsonObject=null;

        Invocation.Builder client=getClient(path,queryParam);
        Response response=client.get();

        return parsePlugboardResponse(response).asArray();
    }

    public static JsonObject postPlugboardJsonObject(String path,JsonObject jsonObject,Pair<String,String> ...queryParams){

        Invocation.Builder client=getClient(path,queryParams);
        Response response=client.post(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON));

        return parsePlugboardResponse(response).asObject();
    }

    public static JsonObject putPlugboardJsonObject(String path,JsonObject jsonObject,Pair<String,String> ...queryParams){

        Invocation.Builder client=getClient(path,queryParams);
        Response response=client.put(Entity.entity(jsonObject.toString(),MediaType.APPLICATION_JSON));

        return parsePlugboardResponse(response).asObject();
    }



    //get product
    public static JsonArray getProducts(String modifiedAtOrAfter){
        MultivaluedMap params=new MultivaluedHashMap();
        params.add("modifiedAtOrAfter",modifiedAtOrAfter);

        return getPlugboardJsonArray(
                "product",param("modifiedAtOrAfter",modifiedAtOrAfter));
    }

    //post order
    public static JsonObject addPlugboardOrder(JsonObject jsonObject){

        return postPlugboardJsonObject("order",jsonObject,param("updateOnRemoteId",Boolean.TRUE),param("suppressTotalsRecalc",Boolean.TRUE));
    }

    //post order
    public static JsonObject updatePlugboardOrder(JsonObject jsonObject,String localId){

        return putPlugboardJsonObject("order/"+localId,jsonObject,param("suppressTotalsRecalc",Boolean.TRUE));
    }

    //list order
    public static JsonArray listPlugboardOrder(String modifiedAtOrAfter){

        return getPlugboardJsonArray("order",param("modifiedAtOrAfter",modifiedAtOrAfter));
    }

    //post product
    public static JsonObject addPlugboardProduct(JsonObject jsonObject){

        return postPlugboardJsonObject("product",jsonObject,param("updateOnRemoteId",Boolean.TRUE));
    }

    //list order
    public static JsonArray listProduct(String modifiedAtOrAfter){

        return getPlugboardJsonArray("product",param("modifiedAtOrAfter",modifiedAtOrAfter));
    }




}
