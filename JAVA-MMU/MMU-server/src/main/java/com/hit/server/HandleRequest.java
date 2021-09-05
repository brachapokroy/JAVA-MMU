package main.java.com.hit.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.com.hit.dm.DataModel;
import main.java.com.hit.services.CacheUnitController;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;

public class  HandleRequest<T> extends java.lang.Object implements java.lang.Runnable {
    private Socket socket;
    private CacheUnitController <T> newController;
    private ArrayList<String>answer;
    private DataOutputStream writer;
    private DataInputStream reader;
    private Request<DataModel<T>[]> request;
    private String action,req;
    private DataModel<T>[]dmarray;
    private T instrr;
    private Type ref;
    public HandleRequest(java.net.Socket s, CacheUnitController<T> controller){
        socket=s;
        newController=controller;

    }
    public void run(){
        reader=null;
        writer=null;
        request=null;
        answer=new ArrayList<String>();
        try {
            reader=new DataInputStream(socket.getInputStream());
            writer=new DataOutputStream(socket.getOutputStream());
            instrr=(T)reader.readUTF();
            ref = (Type) new TypeToken<Request<DataModel<T>[]>>(){}.getType();
            request=(Request<DataModel<T>[]>) new Gson().fromJson((String)instrr,ref);
            action=request.getHeaders().get("action");

            if(action.equals("UPDATE")) {
                if (newController.update(request.getBody())) {
                    answer.add("The update was successful ");
                }
            }
            else {
                answer.add("The update failed");
            }
//            for (int i=0;i<request.getBody().length;i++) {
//                answer.add(request.getBody()[i].toString() + '\n');
//            }

            if(action.equals("GET")){
               dmarray=newController.get(request.getBody());
            for (int i=0;i<dmarray.length;i++){
                if (dmarray[i]!=null){
                    answer.add(dmarray[i].toString()+'\n');
                }
            }
        }
        else if (action.equals("DELETE")){
            if(newController.delete(request.getBody())){
                answer.add("The deletion was successful");
            }
            else {
                answer.add("Deletion failed, the following DataModels: ");
            }
            for (int i=0;i<request.getBody().length;i++){
                answer.add(request.getBody()[i].toString()+ '\n');
            }
        }

        } catch (
        IOException e) {
            e.printStackTrace();
        }
        try {
            req=new Gson().toJson(answer);
            writer.writeUTF(req);
            writer.flush();
            answer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
