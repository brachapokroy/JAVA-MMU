package project.com.hit.algorithm;
import org.junit.Test;
import project.com.hit.algo.MRUAlgoCacheImpl;
import project.com.hit.algo.RandomAlgo;
import project.com.hit.algo.LRUAlgoCacheImpl;

import org.junit.Assert;
import org.junit.BeforeClass;

public class IAlgoCacheTest {
    private static MRUAlgoCacheImpl<Integer, Integer>  MRUAlgoCacheImpl;
    private static RandomAlgo<Integer, Integer> RandomAlgo;
    private static LRUAlgoCacheImpl<Integer, Integer> LRUAlgoCacheImpl;


    @BeforeClass
    public static void beforeTest(){

        MRUAlgoCacheImpl =new MRUAlgoCacheImpl(5);
        MRUAlgoCacheImpl.putElement(12,12);
        MRUAlgoCacheImpl.putElement(65,3454);
        MRUAlgoCacheImpl.putElement(3254,543);
        MRUAlgoCacheImpl.putElement(27,898);
        MRUAlgoCacheImpl.putElement(7678,567);
        MRUAlgoCacheImpl.getElement(65);
        MRUAlgoCacheImpl.getElement(65);
        MRUAlgoCacheImpl.putElement(89,8909);


        LRUAlgoCacheImpl=new LRUAlgoCacheImpl (10);
        LRUAlgoCacheImpl.putElement(324,32);
        LRUAlgoCacheImpl.putElement(54,732);
        LRUAlgoCacheImpl.putElement(12,355);
        LRUAlgoCacheImpl.putElement(4,432);
        LRUAlgoCacheImpl.putElement(12,233);
        LRUAlgoCacheImpl.putElement(2,333);
        LRUAlgoCacheImpl.putElement(43,1);
        LRUAlgoCacheImpl.putElement(23,43);
        LRUAlgoCacheImpl.putElement(4323,433);

        RandomAlgo = new RandomAlgo (4);
        RandomAlgo.putElement(324,32);
        RandomAlgo.putElement(54,732);
        RandomAlgo.putElement(12,355);
        RandomAlgo.putElement(4,432);
    }


    @Test
    public void MRUAlgoCacheImpl(){
        Assert.assertTrue( "Capacity is incorrect",MRUAlgoCacheImpl.getCapacity()==5);
        Assert.assertTrue( "The function getElement does not work",(MRUAlgoCacheImpl.getElement(7678))==567);
        Assert.assertTrue( "The function getElement does not work",(MRUAlgoCacheImpl.getElement(27))==898);
    }
    @Test
    public void RandomAlgo(){
        Assert.assertTrue( "Capacity is incorrect",RandomAlgo.getCapacity()==4);
        Assert.assertTrue( "The function getElement does not work",(RandomAlgo.getElement(324))==32);
    }

    @Test
    public void LRUAlgoCacheImpl(){
        Assert.assertTrue( "Capacity is incorrect",LRUAlgoCacheImpl.getCapacity()==10);
        Assert.assertTrue( "The function getElement does not work",(LRUAlgoCacheImpl.getElement(4323))==433);
        Assert.assertTrue( "The function getElement does not work",(LRUAlgoCacheImpl.getElement(324))==32);
    }
}