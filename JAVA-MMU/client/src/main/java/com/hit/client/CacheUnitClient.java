package main.java.com.hit.client;

import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CacheUnitClient extends java.lang.Object{
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;
    public CacheUnitClient(){
        content="";
    }
    public String send(String request) throws IOException {
    Socket socket = new Socket("127.0.0.1", 12345);
    DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
        writer.writeUTF(request);
        writer.flush();


    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    StringBuilder sb = new StringBuilder();
        do {
        content = in.readUTF();
        sb.append(content);
    } while (in.available() != 0);
    content = sb.toString();

    JsonParser parser = new JsonParser();
        parser.parse(content);
        if (content.equals("null") || content.equals("false")) {
        return "Failed";
    }
        return "Succeeded";
}


}
