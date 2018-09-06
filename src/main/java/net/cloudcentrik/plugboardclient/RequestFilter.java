package net.cloudcentrik.plugboardclient;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;

public class RequestFilter implements ClientRequestFilter {
    @Override
    public void filter(ClientRequestContext clientRequestContext) throws IOException {
        System.out.println(clientRequestContext.getUri().toString());
        if (clientRequestContext.hasEntity()) {
            System.out.println(clientRequestContext.getEntity().toString());
        }
    }
}
