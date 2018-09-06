package net.cloudcentrik.plugboardclient.model;

import com.eclipsesource.json.JsonObject;
import net.cloudcentrik.plugboardclient.util.JsonUtil;

import static net.cloudcentrik.plugboardclient.util.RandomUtil.randomId;
import static net.cloudcentrik.plugboardclient.util.RandomUtil.randomName;

public class PriceList {

    public static JsonObject parsePriceList(){
        JsonObject priceListJson=new JsonObject();
        priceListJson.add("remoteId", randomId(6));
        priceListJson.merge(JsonUtil.wrapInLang("name",randomName("sv"),randomName("sv"),randomName("en")));
        priceListJson.add("currency", "SEK");
        return priceListJson;
    }
}
