package net.cloudcentrik.plugboardclient.model;

import com.eclipsesource.json.JsonObject;
import net.cloudcentrik.plugboardclient.util.JsonUtil;

import static net.cloudcentrik.plugboardclient.util.RandomUtil.*;

public class Product {
    public static JsonObject parseProduct(){
        JsonObject productJson=new JsonObject();
        productJson.add("remoteId", randomAlphaNumeric(12));
        productJson.merge(JsonUtil.wrapInLang("title",randomName("sv"),randomName("sv"),randomName("en")));
        productJson.merge(JsonUtil.wrapInLang("shortDescription",randomDescription("sv"),randomName("sv"),randomName("en")));
        productJson.merge(JsonUtil.wrapInLang("description",randomDescription("sv"),randomName("sv"),randomName("en")));
        productJson.add("state", "visible");
        productJson.add("purchasable", Boolean.TRUE);
        productJson.add("sku", randomNumeric(10));
        productJson.add("gtin", randomNumeric(12));
        productJson.add("manufacturersSku", randomNumeric(10));
        productJson.add("vatRatePercent",randomVatRate());
        productJson.add("stock",randomInt(50,500));
        productJson.add("stockType","always-in-stock");
        productJson.add("weight","1.0");
        productJson.add("volume","1.0");
        productJson.add("brand","Ismailsson");
        return productJson;
    }
}
