package main.java.com.hit.view;

import java.io.Serializable;
import java.util.Map;

public class Request<T> implements Serializable {
    private Map<String, String> headers;
    private T body;

    public Request(Map<String, String> headers, T body) {
        this.body = body;
        this.headers = headers;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public T getBody() {
        return this.body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String toString() {
        String var10000 = this.body.toString();
        return var10000 + " " + this.headers.toString();
    }
}
