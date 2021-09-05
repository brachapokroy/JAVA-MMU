package main.java.com.hit.memory;

import main.java.com.hit.dao.IDao;
import main.java.com.hit.dm.DataModel;
import project.com.hit.algo.IAlgoCache;

public class CacheUnit<T> extends java.lang.Object {
    private IAlgoCache<Long, DataModel<T>> algo;
    private IDao<Long,DataModel<T>> dao;

    public CacheUnit(IAlgoCache<java.lang.Long,DataModel<T>> algo) {
        this.algo = algo;
    }

    public IAlgoCache<Long, DataModel<T>> getAlgo() {

        return algo;
    }

    public void setAlgo(IAlgoCache<Long, DataModel<T>> algo) {
        this.algo = algo;
    }


//    gets a DataModal by it'd id
    public DataModel<T>[] getDataModels(java.lang.Long[] ids) {
        DataModel<T>[] DataModel = new DataModel[ids.length];
        for (int i = 0; i < ids.length; i++) {
            DataModel[i]=algo.getElement(ids[i]);
        }
        return DataModel;
    }

    //inserts a new DataModal
    public DataModel<T>[] putDataModels(DataModel<T>[] datamodels) {
        try {
            DataModel<T>[] DataModel = new DataModel[datamodels.length];
            for (int i = 0; i < datamodels.length; i++) {
                if (datamodels[i] == null) {
                    System.out.println("not valid content");
                    return null;
                }
                DataModel[i] = ((main.java.com.hit.dm.DataModel<T>) algo.putElement(datamodels[i].getDataModelId(), (main.java.com.hit.dm.DataModel<T>) datamodels[i]));
            }
            return DataModel;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


    //removes a DataModal by it'd id
    public void removeDataModels(java.lang.Long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            try {
                algo.deleteElement(ids[i]);
            }
            catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}