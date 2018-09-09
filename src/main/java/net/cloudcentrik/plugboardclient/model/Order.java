package net.cloudcentrik.plugboardclient.model;

import com.eclipsesource.json.JsonObject;

import static net.cloudcentrik.plugboardclient.util.RandomUtil.generateRandomFloat;
import static net.cloudcentrik.plugboardclient.util.RandomUtil.randomAlphaNumeric;
import static net.cloudcentrik.plugboardclient.util.RandomUtil.randomFloat;

public class Order {
    public static JsonObject parseOrder(){
        JsonObject jsonObject=new JsonObject();
        jsonObject.add("remoteId",randomAlphaNumeric(16));
        jsonObject.add("customerType","person");
        jsonObject.add("currency","SEK");

        JsonObject totalSumExclVat=new JsonObject();
        totalSumExclVat.add("currency","SEK");
        totalSumExclVat.add("amount",randomFloat(10,1000));
        jsonObject.add("totalSumExclVat",totalSumExclVat);

        JsonObject totalVat=new JsonObject();
        totalSumExclVat.add("currency","SEK");
        totalSumExclVat.add("amount",generateRandomFloat(1,25));
        jsonObject.add("totalVat",totalSumExclVat);

        return jsonObject;
    }

    public static JsonObject parseUpdateOrder(){
        JsonObject jsonObject=new JsonObject();
        jsonObject.add("remoteOrderNo",randomAlphaNumeric(16));
        jsonObject.add("orderStatus","processing");
        return jsonObject;
    }

    public static JsonObject parseAmout(String currency,String amount){
        JsonObject jsonObject=new JsonObject();
        jsonObject.add("currency",currency);
        jsonObject.add("amount",amount);
        return jsonObject;
    }
}
