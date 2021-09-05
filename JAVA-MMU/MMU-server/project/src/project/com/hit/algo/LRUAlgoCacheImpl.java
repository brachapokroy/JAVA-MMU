package project.com.hit.algo;
import java.util.LinkedList;


//Runs according to the last usage time and the value that was used the longest before is transferred to Ram
public class LRUAlgoCacheImpl <k,v>extends AbstractAlgorithm <k,v>{
    //Checks the last usage time
    private LinkedList<k>checkFirst;
    public LRUAlgoCacheImpl (int capacity) {
        super(capacity);
        this.checkFirst=new LinkedList<k>();
    }
    public LRUAlgoCacheImpl (){}

    //Returns the element by key
    @Override
    public v getElement(k key) {
        if (getpagetabelelement().containsKey(key)) {
            checkFirst.remove(key);
            checkFirst.addLast(key);
            return getpagetabelelement().get(key);
        }
        return null;
    }


    //Inserts a new element by key and value
    @Override
    public v putElement(k key, v value) {
        if (getpagetabelelement().containsKey(key)){
            return null;
        }
        if (getCapacity()>getpagetabelelement().size()){
            getpagetabelelement().put(key,value);
            checkFirst.addLast(key);
            return null;
        }
        else {
            k temp =checkFirst.remove();
            checkFirst.addLast(key);
            return getpagetabelelement().get(temp);
        }
    }

    //Deletes an element
    @Override
    public void deleteElement(k key) {
        getpagetabelelement().remove(key);
        checkFirst.remove(key);
    }
}
