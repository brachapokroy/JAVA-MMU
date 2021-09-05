package main.java.com.hit.util;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.Scanner;

public class CLI extends java.lang.Object implements java.lang.Runnable {
    private PropertyChangeSupport support;
    private java.io.InputStream in;
    private java.io.OutputStream out;
    private String command;
    private	boolean	m_server_stat;



    public CLI(java.io.InputStream in, java.io.OutputStream out)
    {
        this.in	= in;
        this.out= out;
        this.m_server_stat	= false;
        this.command= null;
        support=new PropertyChangeSupport(this);

    }

    public void addPropertyChangeListener(java.beans.PropertyChangeListener pcl) {
        System.out.println("main thread name " +Thread.currentThread().getName());
        support.addPropertyChangeListener(pcl);

    }

    public void removePropertyChangeListener(java.beans.PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);

    }

    public void write(java.lang.String string)
    {
        try {
            out.write((string + "\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {

        Scanner scanner = new Scanner(in);
        do
        {
            System.out.println("main thread name " +Thread.currentThread().getName());
            write("Please enter your command:");
            System.out.println("main thread name " +Thread.currentThread().getName());
            command = scanner.next();
            switch (command)
            {
                case "start":
                    if (!m_server_stat)
                    {
                        try {
                            out.write("Starting server...\n".getBytes());
                            m_server_stat = true;
                            System.out.println("main thread name " +Thread.currentThread().getName());
                            support.firePropertyChange("command",null,command);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            out.write("Server's already online\n".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case "stop":
                    if (m_server_stat)
                    {
                        try {
                            out.write("Shutdown server...\n".getBytes());
                            m_server_stat = false;
                            support.firePropertyChange("command",null,command);
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    } else {
                        try {
                            out.write("Server's already offline".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                default:
                    try {
                        out.write("Not a valid command".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }


    }
        while (!command.equals(" "));
            }
        }






