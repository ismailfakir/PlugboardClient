package net.cloudcentrik.plugboardclient;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.cloudcentrik.plugboardclient.model.Product;
import net.cloudcentrik.plugboardclient.util.JsonUtil;

import static net.cloudcentrik.plugboardclient.util.JsonUtil.convertToGson;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "connecting to plugboard" );

        /*JsonArray jsonObject=PlugboardClient.getProducts("2010-01-06T15:00:00.05Z");
        System.out.println(jsonObject.toString());*/

        /*JsonObject jsonObject=PlugboardClient.updatePlugboardOrder(Order.parseUpdateOrder(),"00085e856131395f9a5c4ce5f4c42a11");
        JsonUtil.prettyPrint(jsonObject);*/

        //JsonObject jsonObject=PlugboardClient.addPlugboardOrder(Order.parseOrder());

        //String timestamp = TimeUtil.formatTime("2015-01-02T03:04:05.000Z");

        //System.out.println(timestamp);

        //JsonArray jsonObject=PlugboardClient.listPlugboardOrder("2015-01-02T03:04:05.000Z");
        //JsonUtil.prettyPrint(jsonObject);

        //String string=JsonUtil.wrapInLang("description","namn","name").toString(WriterConfig.PRETTY_PRINT);
        //System.out.println(Product.parseProduct().toString(WriterConfig.PRETTY_PRINT));

        JsonObject jsonObject=PlugboardClient.addPlugboardProduct(convertToGson(Product.parseProduct()));

        JsonUtil.prettyPrint(jsonObject);

    }
}
