package main.java.com.hit.services;

import main.java.com.hit.dm.DataModel;

public class CacheUnitController<T> extends java.lang.Object{
    private CacheUnitService<T> cacheUnitservice = null;
    public CacheUnitController(){
        cacheUnitservice = new CacheUnitService<T>();
    }
    public boolean update(DataModel<T>[] dataModels){
        return cacheUnitservice.update(dataModels);
    }
    public boolean delete(DataModel<T>[] dataModels){

        return cacheUnitservice.delete(dataModels);
    };
    public DataModel<T>[] get(DataModel<T>[] dataModels){

        return cacheUnitservice.getS(dataModels);
    }
}
