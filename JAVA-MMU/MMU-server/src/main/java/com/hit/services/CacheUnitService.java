package main.java.com.hit.services;

import main.java.com.hit.dao.DaoFileImpl;
import main.java.com.hit.dm.DataModel;
import main.java.com.hit.memory.CacheUnit;
import project.com.hit.algo.IAlgoCache;
import project.com.hit.algo.LRUAlgoCacheImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CacheUnitService<T> extends java.lang.Object {
    private CacheUnit<T> cacheUnit;
    private List<DataModel<T>> dmArray;
    private java.lang.Long[] Ids;
    private int	capacity;
    //    private String algoInUse;
    private int	countSwap;
    private String algoUsed;
    private  DaoFileImpl<String> dao;

    public CacheUnitService(){
        dmArray=new ArrayList<DataModel<T>>();
        Ids=null;
        capacity=100;
        countSwap=0;
        algoUsed=new String("MRU");
        cacheUnit=new CacheUnit<>(new LRUAlgoCacheImpl<>(100));
        dao=new DaoFileImpl<>("H:\\projectJava\\src\\resources\\datasource.json",100);
    }
    public boolean delete(DataModel<T>[] dataModels){
        Ids=new java.lang.Long[dataModels.length];
        for (int i=0;i<dataModels.length;i++){
            Ids[i]=dataModels[i].getDataModelId();
        }
        try {
            this.dmArray= Arrays.asList(cacheUnit.getDataModels(Ids));
            DataModel<T> dataModelTemp = null;
            DataModel<T> dmTmpNull = (DataModel<T>) new DataModel<String>((1L),new String("null"));
           //IDao<java.lang.Long, DataModel<T>> dao = (IDao<Long, DataModel<T>>) cacheUnit.getAlgo();
            IAlgoCache<java.lang.Long,DataModel<T>> algo = cacheUnit.getAlgo();
            for (int i = 0;i< dataModels.length ; i++)
            {
                dao.delete((DataModel<String>) dataModels[i]);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public DataModel<T>[] getS(DataModel<T>[] dataModels){
        Ids=new java.lang.Long[dataModels.length];
        for (int i=0;i<dataModels.length;i++){
            Ids[i]=dataModels[i].getDataModelId();
        }
        this.dmArray=new ArrayList<DataModel<T>>(Ids.length);
        dmArray= Arrays.asList(cacheUnit.getDataModels(Ids));
        return dmArray.toArray(new DataModel[0]);
    }



    public boolean update(DataModel<T>[] dataModels) {
        Ids=new java.lang.Long[dataModels.length];
        for (int i=0;i<dataModels.length;i++){
            Ids[i]=dataModels[i].getDataModelId();
        }
        try {
           this.dmArray= Arrays.asList(cacheUnit.getDataModels(Ids));
            DataModel<T> tDataModel = null;
            IAlgoCache<java.lang.Long,DataModel<T>> algo = cacheUnit.getAlgo();
            for (int i =0 ;i<this.dmArray.size();i++){
                //tDataModel.setContent(dataModels[i].getContent());
                this.dmArray.set(i,dataModels[i]);
                //save to the hard disc (update if already exists)
                dao.save((DataModel<String>) dmArray.get(i));
                //update cache using its algorithm
                algo.putElement(dataModels[i].getDataModelId(), dataModels[i]);
            }
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}