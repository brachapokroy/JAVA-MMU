package project.com.hit.algo;
import java.util.LinkedList;
import java.util.Random;

//When the ram is filled it randomly transfers to the main memory
public class RandomAlgo<k,v>extends AbstractAlgorithm <k,v>{
    private LinkedList<k> randomaly;
    public RandomAlgo(int capacity){
        super(capacity);
        this.randomaly = new LinkedList<k>();
    }
    public RandomAlgo(){

    }


    //Returns the element by key
    @Override
    public v getElement(k key) {
        if (getpagetabelelement().containsKey(key)) {
            randomaly.remove(key);
            randomaly.addLast(key);
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
            randomaly.addLast(key);
            return null;
        }
        else {
            Random rand = new Random();
            int ran = rand.nextInt(getCapacity());
            k temp =randomaly.remove(ran);
            randomaly.addLast(key);
            return getpagetabelelement().get(ran);
        }
    }

    //Deletes an element
    @Override
    public void deleteElement(k key) {
        getpagetabelelement().remove(key);
        randomaly.remove(key);
    }
}
