package main.java.com.hit.client;

import java.beans.PropertyChangeEvent;
import java.io.DataInputStream;
import java.io.IOException;

public class CacheUnitClientObserver extends java.lang.Object implements java.beans.PropertyChangeListener{
    private String command;
    private CacheUnitClient cacheUnitClient;
    private String Response;
    public CacheUnitClientObserver(){
        this.command = null;
        this.cacheUnitClient=new CacheUnitClient();
    }

    public void propertyChange(PropertyChangeEvent evt){
        this.command = (String)evt.getNewValue();
        if (command.equals("Show Statistics")){
            try {
                cacheUnitClient.send(this.command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                String json = (String) this.command ;
                cacheUnitClient.send(json);
                Response=cacheUnitClient.getContent();
                System.out.println("Response: "+Response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
