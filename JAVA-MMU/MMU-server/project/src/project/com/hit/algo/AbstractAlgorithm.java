package project.com.hit.algo;
import java.util.HashMap;
import java.util.*;

public abstract class AbstractAlgorithm <k,v> implements IAlgoCache<k,v>{
    private Map <k,v> pagetabelelement;
    private int capacity;

    public AbstractAlgorithm(int capacity) {
        this.capacity = capacity;
        this.pagetabelelement = new HashMap<>();
    }

    public AbstractAlgorithm() {
        this.pagetabelelement = new HashMap<>();
    }

    public Map<k, v> getpagetabelelement() {
        return pagetabelelement;
    }

    public void setpagetabelelement(Map<k, v> pagetabkeelement) {
        this.pagetabelelement = pagetabkeelement;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity>pagetabelelement.size()) {
            this.capacity = capacity;
        }
    }

}
