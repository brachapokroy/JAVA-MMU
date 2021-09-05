package project.com.hit.algo;


public interface IAlgoCache<k,v> {
    public v getElement(k key);
    public v putElement(k key, v value);
    public void deleteElement(k key);
}
