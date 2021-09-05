package src.main.test.memory;

import main.java.com.hit.dao.DaoFileImpl;
import main.java.com.hit.dm.DataModel;
import main.java.com.hit.memory.CacheUnit;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import project.com.hit.algo.MRUAlgoCacheImpl;

public class CacheUnitTest {

    public static DataModel <String> Datamodel2;
    public static DataModel <String> Datamodel1;
    public static DataModel <String> Datamodel3;
    public static CacheUnit<MRUAlgoCacheImpl> CacheUnit;
    public static DaoFileImpl DaoFileImpl;


    @BeforeClass
    public static void beforeTest(){
//        DaoFileImpl = new DaoFileImpl ( "H:\\סמסטר ב' שנה ב\\java\\project1\\src\\resources" ,10)
        Datamodel1 = new DataModel<String>(123L,"hello");
        Datamodel2= new DataModel<String>(121L,"hello");
    }
    @Test
    public void testDataModel(){
        DataModel<String> dm=new DataModel<String>(0L,"a");
        DataModel<String> dm2=new DataModel<String>(1L,"b");
        Assert.assertEquals(dm.getDataModelId().intValue(),0);
        Assert.assertEquals(dm.getContent(),"a");
        Assert.assertNotEquals(dm, dm2);
        DataModel<String> dm3=new DataModel<String>(1L,"b");
        Assert.assertEquals(dm2,dm3);
    }


    @Test
    public void CacheUnitTest(){
        MRUAlgoCacheImpl<Long, DataModel<String>> algo=new MRUAlgoCacheImpl<>(5);
        CacheUnit<String> cache=new CacheUnit<String>(algo);
        DataModel<String>[] dm = new DataModel[6];
        dm[0]=new DataModel<String>(0L,"a");
        dm[1]=new DataModel<String>(1L,"b");
        dm[2]=new DataModel<String>(2L,"c");
        dm[3]=new DataModel<String>(3L,"d");
        dm[4]=new DataModel<String>(4L,"e");
        dm[5]=new DataModel<String>(5L,"f");
        DataModel<String>[] expectedOutput = new DataModel[6];
        expectedOutput[0]=null;
        expectedOutput[1]=null;
        expectedOutput[2]=null;
        expectedOutput[3]=null;
        expectedOutput[4]=null;
        expectedOutput[5]=dm[0];
        DataModel<String>[] actualOutput=cache.putDataModels(dm);
        Assert.assertEquals(actualOutput[0],expectedOutput[0]);
        Assert.assertEquals(actualOutput[1],expectedOutput[1]);
        Assert.assertEquals(actualOutput[2],expectedOutput[2]);
        Assert.assertEquals(actualOutput[3],expectedOutput[3]);
        Assert.assertEquals(actualOutput[4],expectedOutput[4]);
        Assert.assertEquals(actualOutput[5],expectedOutput[5]);
    }



    @Test
    public void testDaoFileImpl(){
        DaoFileImpl<String> dao=new DaoFileImpl<>("H:\\סמסטר ב' שנה ב\\java\\project1\\src\\resources\\datasource.json",20);
        DataModel<String> dm=new DataModel<String>(2L,"a a");
        DataModel<String> dm3=new DataModel<String>(0L,"a");
        DataModel<String> dm2=new DataModel<String>(3L,"bb");
        dao.save(dm);
        dao.save(dm2);
        dao.save(dm3);
        dao.delete(dm);
        dao.delete(dm2);
        dao.delete(dm3);
        dao.save(dm);
        dao.save(dm2);
        dao.save(dm3);
        Assert.assertEquals(dao.find(2L),dm);
    }
}

