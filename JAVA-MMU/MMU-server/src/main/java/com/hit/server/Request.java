package main.java.com.hit.server;

public class Request <T>
        extends java.lang.Object
        implements java.io.Serializable{
    private java.util.Map<java.lang.String,java.lang.String> headers;
    private  T body;


    public Request(java.util.Map<java.lang.String,java.lang.String> headers, T body){
        this.body=body;
        this.headers=headers;

    }

    public java.util.Map<java.lang.String,java.lang.String> getHeaders(){
        return this.headers;
    }
    public void setHeaders(java.util.Map<java.lang.String,java.lang.String> headers){
        this.headers=headers;

    }
    public T getBody(){

        return this.body;
    }
    public void setBody(T body){
        this.body=body;

    }
    public java.lang.String toString(){
        return body.toString() + " " + headers.toString();
    }
}
