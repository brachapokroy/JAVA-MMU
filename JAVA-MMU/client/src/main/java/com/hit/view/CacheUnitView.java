package main.java.com.hit.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;


public class CacheUnitView extends java.lang.Object {
    private PropertyChangeSupport support;
    private JFrame frame,frameRequest;
    private JButton BStatistics,BLoadRequest,Bok,Bcancel,BdataModelList;
    private JLabel Id, content, requestType ;
    private JTextField TId, Tcontent;
    private String action,type;
    private HashMap headers;
    private Request request;
    private String command,ShowStatistics;
    private ArrayList <DataModel>DataModelList;
    public CacheUnitView(){
        this.support = new PropertyChangeSupport(this);
        this.command=null;
        this.ShowStatistics=null;
        this.action=null;
        this.type="GET";
        this.DataModelList = new ArrayList();
        this.headers=new HashMap();
    }
    public void start(){
        frame=new JFrame("CuchUnitUI");
        BLoadRequest = new JButton("Load a Request");
        BStatistics = new JButton("Show Statistics");
        JPanel p = new JPanel();
        p.add(BLoadRequest);
        p.add(BStatistics);
        p.setBackground(Color.white);
        frame.add(p);
        frame.setSize(300, 80);
        frame.show();
    BStatistics.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if ("Show Statistics".equals(event.getActionCommand())) {
                    support.firePropertyChange("command", null, "Show Statistics");
                }
            }

            });
        BLoadRequest.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if ("Load a Request".equals(event.getActionCommand())) {
                    frameRequest = new JFrame("Request");
                    frameRequest.setTitle("Request Form");
                    Id = new JLabel("data model Id");
                    Id.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                    Id.setSize(200, 20);
                    Id.setLocation(100, 40);
                    TId = new JTextField();
                    TId.setFont(new Font("Times New Roman", Font.PLAIN, 15));
                    TId.setSize(190, 20);
                    TId.setLocation(300, 40);
                    content = new JLabel("data model content");
                    content.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                    content.setSize(200, 20);
                    content.setLocation(100, 70);
                    Tcontent = new JTextField();
                    Tcontent.setFont(new Font("Times New Roman", Font.PLAIN, 15));
                    Tcontent.setSize(190, 20);
                    Tcontent.setLocation(300, 70);
                    requestType = new JLabel("select request type");
                    requestType.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                    requestType.setSize(200, 20);
                    requestType.setLocation(100, 110);
                    String RequsName[] = {"GET", "UPDATE", "DELETE"};
                    JComboBox cb = new JComboBox(RequsName);
                    cb.setFont(new Font("Times New Roman", Font.PLAIN, 15));
                    cb.setSize(190, 20);
                    cb.setLocation(300, 110);
                    BdataModelList = new JButton("add to list");
                    BdataModelList.setFont(new Font("Arial", Font.PLAIN, 15));
                    BdataModelList.setSize(190, 20);
                    BdataModelList.setLocation(300, 145);
                    Bok = new JButton("ok");
                    Bok.setFont(new Font("Arial", Font.PLAIN, 15));
                    Bok.setSize(100, 25);
                    Bok.setLocation(150, 190);
                    Bcancel = new JButton("cancel");
                    Bcancel.setFont(new Font("Arial", Font.PLAIN, 15));
                    Bcancel.setSize(100, 25);
                    Bcancel.setLocation(270, 190);
                    frameRequest.add(content);
                    frameRequest.add(Id);
                    frameRequest.add(TId);
                    frameRequest.add(Tcontent);
                    frameRequest.add(cb);
                    frameRequest.add(Bok);
                    frameRequest.add(BdataModelList);
                    frameRequest.add(Bcancel);
                    frameRequest.add(requestType);
                    frameRequest.setLayout(null);
                    frameRequest.setSize(600, 270);
                    frameRequest.setVisible(true);
                    frameRequest.show();
                    cb.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            JComboBox comboBox = (JComboBox) event.getSource();
                            Object selected = comboBox.getSelectedItem();
                            if (selected.toString().equals("GET"))
                                type = "GET";
                            else if (selected.toString().equals("DELETE"))
                                type = "DELETE";
                            else if (selected.toString().equals("UPDATE"))
                                type = "UPDATE";
                        }
                    });
                    BdataModelList.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if ("add to list".equals(e.getActionCommand())) {
                                if (TId.getText().equals(" "))
                                    System.out.printf("the field is required \n");
                                else if (!TId.getText().matches("[0-9]+")) {
                                    System.out.printf("the id must contain digit characters only\n");
                                } else {
                                    frameRequest.remove(cb);
                                    DataModel tempDataModel = new DataModel();
                                    tempDataModel.setDataModelId(Long.valueOf(TId.getText()));
                                    tempDataModel.setContent(Tcontent.getText());
                                    DataModelList.add(tempDataModel);
                                    TId.setText("");
                                    Tcontent.setText("");
                                }
                            }
                        }
                    });
                    Bok.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if ("ok".equals(e.getActionCommand())) {
                                if (TId.getText().equals(" "))
                                    System.out.printf("the field is required\n");
                                if (!TId.getText().matches("[0-9]+")) {
                                    System.out.printf("the id must contain digit characters only\n");
                                    return;
                                }
                                DataModel tempDataModel = new DataModel();
                                tempDataModel.setDataModelId(Long.valueOf(TId.getText()));
                                tempDataModel.setContent(Tcontent.getText());
                                DataModelList.add(tempDataModel);
                                headers.put("action", type);
                                DataModel[] dataModelArray = new DataModel[DataModelList.size()];
                                dataModelArray = DataModelList.toArray(dataModelArray);
                                DataModelList.clear();
                                request = new Request<DataModel<String>[]>(headers, dataModelArray);
                                request.setHeaders(headers);
                                request.setBody(dataModelArray);
                                String currentRequest = new Gson().toJson(request);
                                try {
                                    FileWriter writer;
                                    Gson gson = new Gson();
                                    switch (type) {
                                        case "GET":
                                            writer = new FileWriter("C:\\Users\\פוקרוי ברכה\\Downloads\\client (1)\\src\\main\\resources\\GET.json");
                                            break;
                                        case "UPDATE":
                                            writer = new FileWriter("C:\\Users\\פוקרוי ברכה\\Downloads\\client (1)\\src\\main\\resources\\ADD.json");
                                            break;
                                        default:
                                            writer = new FileWriter("C:\\Users\\פוקרוי ברכה\\Downloads\\client (1)\\src\\main\\resources\\DELETE.json");
                                    }
                                    writer.write(currentRequest);
                                    writer.close();
                                } catch (IOException var5) {
                                    var5.printStackTrace();
                                }
                                support.firePropertyChange("command", null, currentRequest);
                            }
                            frameRequest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frameRequest.setContentPane(Bok);
                            frameRequest.dispose();
                        }
                    });
                    Bcancel.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            if ("CANCEL".equals(e.getActionCommand())) {
                            }
                            frameRequest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frameRequest.setContentPane(Bcancel);
                            frameRequest.dispose();
                        }
                    });


                }}
        });

    }
    public void addPropertyChangeListener(java.beans.PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }
    public void removePropertyChangeListener(java.beans.PropertyChangeListener pcl){
        support.removePropertyChangeListener(pcl);
    }
    public <T> void updateUIData(T t){

    }
}
