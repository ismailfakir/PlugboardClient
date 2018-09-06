package net.cloudcentrik.plugboardclient.model;

import com.google.gson.JsonObject;

import static net.cloudcentrik.plugboardclient.util.RandomUtil.generateRandomFloat;
import static net.cloudcentrik.plugboardclient.util.RandomUtil.randomAlphaNumeric;
import static net.cloudcentrik.plugboardclient.util.RandomUtil.randomFloat;

public class Order {
    public static JsonObject parseOrder(){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("remoteId",randomAlphaNumeric(16));
        jsonObject.addProperty("customerType","person");
        jsonObject.addProperty("currency","SEK");

        JsonObject totalSumExclVat=new JsonObject();
        totalSumExclVat.addProperty("currency","SEK");
        totalSumExclVat.addProperty("amount",randomFloat(10,1000));
        jsonObject.add("totalSumExclVat",totalSumExclVat);

        JsonObject totalVat=new JsonObject();
        totalSumExclVat.addProperty("currency","SEK");
        totalSumExclVat.addProperty("amount",generateRandomFloat(1,25));
        jsonObject.add("totalVat",totalSumExclVat);

        return jsonObject;
    }

    public static JsonObject parseUpdateOrder(){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("remoteOrderNo",randomAlphaNumeric(16));
        jsonObject.addProperty("orderStatus","processing");
        return jsonObject;
    }

    public static JsonObject parseAmout(String currency,String amount){
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("currency",currency);
        jsonObject.addProperty("amount",amount);
        return jsonObject;
    }
}
