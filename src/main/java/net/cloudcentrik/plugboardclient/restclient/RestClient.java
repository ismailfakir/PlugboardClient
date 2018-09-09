package net.cloudcentrik.plugboardclient.restclient;

import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.WriterConfig;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;

import javax.ws.rs.HttpMethod;
import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RestClient {
    private static boolean isDebugEnabled = false;
    private static URI baseUrl = null;
    private static List<Header> defaultHeaders;
    private static CloseableHttpClient httpClient;
    private static RequestConfig requestConfig;

    private static RestClient ourInstance = new RestClient();

    public static String parseBasicAuthorizationHeader(String user, String password){

        String basicAuthorizationHeader="";
        try {
            byte[] bytes = new String(user + ":" + password).getBytes("UTF-8");
            basicAuthorizationHeader= "Basic " + Base64.getEncoder().encodeToString(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return basicAuthorizationHeader;

    }

    public static RestClient getInstance(String url, Map<String, String> headers,Boolean isDebugable) throws Exception{

        isDebugEnabled = isDebugable;

        baseUrl=new URI(url);

        defaultHeaders = headers.entrySet().stream()
                .map(entry -> new BasicHeader(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        requestConfig = RequestConfig.custom()
                .setAuthenticationEnabled(true)
                .setSocketTimeout(1000)
                .setConnectTimeout(1000)
                .build();

        httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setDefaultHeaders(defaultHeaders)
                .addInterceptorLast(new HttpRequestInterceptor() {
                    @Override
                    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {

                        if (isDebugEnabled) {
                            System.out.println(httpRequest.getRequestLine().getMethod() + " " +
                                    httpContext.getAttribute(HttpCoreContext.HTTP_TARGET_HOST) + httpRequest.getRequestLine().getUri());
                        }
                    }
                })
                .addInterceptorLast(new HttpResponseInterceptor() {
                    @Override
                    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
                        if (isDebugEnabled) {
                            System.out.println(httpResponse.getStatusLine().getReasonPhrase());
                        }
                    }

                })
                .build();

        return ourInstance;
    }

    public static RestClient getBasicJsonClient(String baseUrl,String user, String password,Map<String, String> customHeaders,boolean isDebugEnabled) throws Exception{
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.put(HttpHeaders.ACCEPT, "application/json");
        headers.put("Authorization", parseBasicAuthorizationHeader(user, password));
        headers.putAll(customHeaders);
        return getInstance(baseUrl, headers, isDebugEnabled);
    }

    private JsonValue baseRequest(String httpMethod,String path,HttpEntity entity,Map<String,String> params) throws Exception{

        JsonValue jsonResponse=null;
        HttpUriRequest request=null;
        URIBuilder uriBuilder=new URIBuilder();

        //parametes
        if(params!=null){
            List<NameValuePair> paramList=params.entrySet().stream()
                    .map(entry->new BasicNameValuePair(entry.getKey(),entry.getValue()))
                    .collect(Collectors.toList());
            uriBuilder.addParameters(paramList);
        }

        if(path!=null){
            baseUrl =URI.create(baseUrl+path);

        }
        URI uri=uriBuilder
                .setScheme(baseUrl.getScheme())
                .setHost(baseUrl.getHost())
                .setPath(baseUrl.getPath())
                .build();

        if(httpMethod.equals(HttpMethod.GET)){
            request = RequestBuilder.get()
                    .setUri(uri)
                    .build();
        }else if(httpMethod.equals(HttpMethod.POST)){
            request = RequestBuilder.post()
                    .setUri(uri)
                    .setEntity(entity)
                    .build();
        }else if(httpMethod.equals(HttpMethod.PUT)){
            request = RequestBuilder.put()
                    .setUri(uri)
                    .setEntity(entity)
                    .build();
        }else if(httpMethod.equals(HttpMethod.DELETE)){
            request = RequestBuilder.delete()
                    .setUri(uri)
                    .build();
        }

        jsonResponse=httpClient.execute(request,new JsonResponseHandler());
        if(isDebugEnabled){
            System.out.println(jsonResponse.toString(WriterConfig.PRETTY_PRINT));
        }


        return jsonResponse;
    }

    public JsonValue getRequest(String path,Map<String,String> params) throws Exception{

        return  baseRequest(HttpMethod.GET,path,null,params);
    }

    public JsonValue postRequest(String path,Map<String,String> params,JsonValue jsonValue) throws Exception{

        HttpEntity entity=new StringEntity(jsonValue.toString());
        return  baseRequest(HttpMethod.POST,path,entity,params);
    }

    public JsonValue putRequest(String path,Map<String,String> params,JsonValue jsonValue) throws Exception{

        HttpEntity entity=new StringEntity(jsonValue.toString());
        return  baseRequest(HttpMethod.PUT,path,entity,params);
    }

    public JsonValue putHttpEntityRequest(String path,Map<String,String> params,HttpEntity entity) throws Exception{

        return  baseRequest(HttpMethod.PUT,path,entity,params);
    }

    private RestClient() {
    }

}
