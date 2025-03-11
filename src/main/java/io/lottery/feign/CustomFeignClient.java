package io.lottery.feign;

import feign.Client;
import feign.Request;
import feign.Response;

import java.io.IOException;

public class CustomFeignClient implements Client {

    private final Client delegate;

    public CustomFeignClient(Client delegate){
        this.delegate = delegate;
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        Response response = null;
        try {
            response = delegate.execute(request,options);
        } catch (Exception e){
            System.out.println("Response body: " + response.body());
        }



//        if(response.headers("Set-Cookie")){
//
//        }
        return response;
    }
}
