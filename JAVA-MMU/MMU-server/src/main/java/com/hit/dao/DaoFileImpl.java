package main.java.com.hit.dao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.com.hit.dm.DataModel;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class DaoFileImpl<T> extends java.lang.Object implements IDao<java.lang.Long,DataModel<T>> {
    private int location;
    private List <DataModel <T>> TempFile;
    private java.lang.String filePath;
    private int capacity=1000;
    private File file;
    //constructor
    DaoFileImpl(java.lang.String filePath){
        this.filePath=filePath;
        this.location=0;
        read();
    }
//constructor
    public DaoFileImpl(java.lang.String filePath, int capacity){
        System.out.println("in constractor Dao");
        this.filePath=filePath;
        this.capacity=capacity;
        this.location=0;
        read();
    }

    //updated the data of rhe file at any change according to the current TempFile
    public void write(DataModel<T> entity){
        try {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(this.filePath);
        String string= gson.toJson(this.TempFile);
            writer.write(string);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//loads the data of the file to TempFile
    private void read() {
        try {
            Gson gson = new Gson();
            Type types = new TypeToken<ArrayList<DataModel<T>>>() {
            }.getType();
            FileReader fileReader = new FileReader(filePath);
            List<DataModel<T>> fileData = gson.fromJson(fileReader, types);
            fileReader.close();
            if (fileData != null) {
                TempFile = fileData;
                this.location=this.TempFile.size();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Description copied from interface: IDao
     * Saves a given entity.
     * @param entity - given entity.
     */

    @Override
    public void save(DataModel<T> entity) {
        for (int i = 0; i < TempFile.size(); i++) {
            if (entity.getDataModelId()==TempFile.get(i).getDataModelId())
                TempFile.remove(i);
        }
            TempFile.add(entity);
            try {
                write(entity);
            } catch (Exception e) {
        }
    }

//    public void save(DataModel<T> entity) {
//        if(this.TempFile.size()+1<this.capacity){
//            for(int i=0;i<this.location;i++){
//                if(this.TempFile.get(i).getDataModelId()==entity.getDataModelId()){
//                    this.TempFile.get(i).setContent(entity.getContent());
//                    write(entity);
//                    return;
//                }
//            }
//            this.TempFile.add(this.location,entity);
//            this.location+=1;
//            write(entity);
//            return;
//        }
//        else {
//            System.out.println("There is not enough space in capacity");
//            return;
//        }
//    }

    /**
     * Deletes a given entity.
     * @param entity - given entity.
     */
    @Override
    public void delete(DataModel<T> entity) {
        int a=this.TempFile.indexOf(entity);
        if (a==-1){
            System.out.println("There is no such organ in the array");
        }
        else {
            this.TempFile.remove(a);
            this.location-=1;
            write(entity);
        }
        return;
    }

    /**
     * Retrieves an entity by its id.
     * @param aLong id - must not be null.
     * @return the entity with the given id or null if none found
     */
    @Override
    public DataModel<T>find(Long aLong) {
        for(int i=0;i<this.location;i++){
            if(this.TempFile.get(i).getDataModelId()==aLong){
                return this.TempFile.get(i);
            }
        }
        System.out.println("There is no such organ in the array");
        return null;
    }
}
