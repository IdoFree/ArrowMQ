package com.yundan.common;

public class Response implements TransportPotocol{
    private String message ;

    public Response(String message) {
        this.message = message;
    }

    @Override
    public String getTransportData() {
        return this.message+"\r\n";
    }
}
