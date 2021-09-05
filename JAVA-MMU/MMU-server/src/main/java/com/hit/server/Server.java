package main.java.com.hit.server;

import main.java.com.hit.services.CacheUnitController;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server extends java.lang.Object implements java.beans.PropertyChangeListener, java.lang.Runnable{
    private CacheUnitController<String> cuc;
    private ServerSocket mySocket;
    private java.io.InputStream  in;
    public String command;
    private ExecutorService exec;
    private boolean statusServer;

    private java.io.OutputStream out;

    public Server(){
        System.out.println("i'm in contractor\n");
        cuc = new CacheUnitController<String>();
        this.mySocket=null;
        this.in=null;
        this.command="close";
        this.statusServer=false;
        this.exec= Executors.newFixedThreadPool(5);
        try {
            mySocket = new ServerSocket(12345);
            System.out.println("i built a new socket");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("ERROR: Cannot connect to the server. check if the port is already in used");
            e.printStackTrace();
        }
    }


    public void propertyChange(PropertyChangeEvent evt){
        command=((String) evt.getNewValue());
        if (command.equals("start")&&(statusServer==false)){
            statusServer=true;
            System.out.println("server went up pc");
            exec.execute(this);
        }
        else if(command.equals("stop")&&(statusServer=true)) {
            statusServer = false;
        }
    }
    public void run(){
        java.io.OutputStream out=null;
        // System.out.println("server thread name " +Thread.currentThread().getName());
        System.out.println("entered server run");
        while (statusServer)
        {
            try {
                Socket client = mySocket.accept();   //waiting for client to ask for connection
                System.out.println("New client just connected\n");
//                if (statusServer)
                {
                    new Thread(new HandleRequest<String>(client, cuc)).start(); //starting HandleRequest using new thread
                }
//                else {
//                    out = new ObjectOutputStream(client.getOutputStream());
//                    out.write("Server OFFLINE\n".getBytes());
//                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            mySocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
