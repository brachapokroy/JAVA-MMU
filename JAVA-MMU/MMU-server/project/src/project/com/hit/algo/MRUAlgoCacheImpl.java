package project.com.hit.algo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


//an algo//holds a map counter to count the activity of each value cache that flushes the memory that has been used the most in use because now it's the least likely to be used
public class MRUAlgoCacheImpl <k,v> extends AbstractAlgorithm <k,v>{
    // holds a map counter to count the activity of each value
    Map<k ,Integer> counter;

    //constructor
    public MRUAlgoCacheImpl(int capacity) {
        super(capacity);
        this.counter = new HashMap<>();
    }
    //d constructor
    public MRUAlgoCacheImpl(){
    }


    //Returns the element by key
    @Override
    public v getElement(k key) {
        if (getpagetabelelement().containsKey(key)) {
            counter.put(key, (counter.get(key).intValue())+1);
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
            counter.put(key,0);
            return null;

        }
        k maximum = Collections.max(counter.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
        counter.put(maximum,0);
        counter.remove(maximum);
        return getpagetabelelement().remove(maximum);
    }

    //Deletes an element
    @Override
    public void deleteElement(k key) {
        getpagetabelelement().remove(key);
        counter.remove(key);
    }
}

