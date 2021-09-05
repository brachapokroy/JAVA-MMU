package src.main.test.memory;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import main.java.com.hit.dm.DataModel;
import main.java.com.hit.server.Request;
import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class testPartC {

    private BufferedReader br;

//    @Test
//    public void testUpdate() {
//
//        Map<String, String> headerReq = new HashMap<>();
//        headerReq.put("action", "UPDATE");
//
//        DataModel<String>[] dmArray = new DataModel[]{new DataModel<String>(1L, "b"), new DataModel<String>(2L, "a")};
//
//        Request<DataModel<String>[]> req = new Request<>(headerReq, dmArray);
//        req.getHeaders();
//        Gson gson = new Gson();
//        try {
//            Socket socket = new Socket("127.0.0.1", 12345);
//            System.out.println("1");
//            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
//            System.out.println("2");
//            writer.writeUTF(gson.toJson(req));
//            System.out.println("3");
//            writer.flush();
//            System.out.println("4");
//            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
//            System.out.println("5");
//            StringBuilder sb = new StringBuilder();
//            System.out.println("6");
//            String content = "";
//
//            do {
//                System.out.println("in");
//                content = in.readUTF();
//                sb.append(content);
//            } while (in.available() != 0);
//            System.out.println("7");
//
//            content = sb.toString();
//            try{
//                JsonParser parser = new JsonParser();
//                parser.parse(content);
//            }
//            catch (JsonSyntaxException e){
//                System.out.println(content);
//                return;
//            }
//            System.out.println("8");
//            Boolean response=true;
//            System.out.println("9");
//           // response = new Gson().fromJson(content, response.getClass());
//            System.out.println("message from server: " + response);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }}


//    @Test
//    public void testGet() {
//        Map<String, String> headerReq = new HashMap<>();
//        headerReq.put("action", "GET");
//
//        DataModel<String>[] dmArray = new DataModel[]{new DataModel<String>(1L, "b"), new DataModel<String>(2L, "a")};
//        Request<DataModel<String>[]> req = new Request<>(headerReq, dmArray);
//
//        Gson gson = new Gson();
//        System.out.println("1");
//        try {
//            System.out.println("2");
//            Socket socket = new Socket("127.0.0.1", 12345);
//            System.out.println("3");
//            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
//            System.out.println("4");
//            writer.writeUTF(gson.toJson(req));
//            writer.flush();
//            System.out.println("5");
//            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
//            StringBuilder sb = new StringBuilder();
//            System.out.println("6");
//            String content = "";
//
//            do {
//                content = in.readUTF();
//                System.out.println("7");
//                sb.append(content);
//                System.out.println("8");
//            } while (in.available() != 0);
//
//            content = sb.toString();
//            try {
//                JsonParser parser = new JsonParser();
//                System.out.println("9");
//                parser.parse(content);
//            } catch (JsonSyntaxException e) {
//                System.out.println("10");
//                System.out.println(content);
//                return;
//            }
//            System.out.println("11");
//            Type requestType = new TypeToken<DataModel<String>[]>() {
//            }.getType();
//            System.out.println("12");
//            Boolean response = true;
//            //DataModel<String>[] response = new Gson().fromJson(content, requestType);
//            System.out.println("message from server: " + response);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

    @Test
    public void testDelete(){
        Map<String, String> headerReq = new HashMap<>();
        headerReq.put("action", "DELETE");

        DataModel<String>[] dmArray = new DataModel[]{new DataModel<String>(1L, "b")};
        Request<DataModel<String>[]> req = new Request<>(headerReq, dmArray);

        Gson gson = new Gson();
        try {
            Socket socket = new Socket("127.0.0.1", 12345);
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            writer.writeUTF(gson.toJson(req));
            writer.flush();

            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String content = "";

            do {
                content = in.readUTF();
                sb.append(content);
            } while (in.available() != 0);

            content = sb.toString();
            try{
                JsonParser parser = new JsonParser();
                parser.parse(content);
            }
            catch (JsonSyntaxException e){
                System.out.println(content);
                return;
            }
            Boolean response=true;
            response = new Gson().fromJson(content, response.getClass());
            System.out.println("message from server: " + response);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
