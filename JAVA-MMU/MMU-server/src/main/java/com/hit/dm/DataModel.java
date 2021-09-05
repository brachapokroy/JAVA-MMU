package main.java.com.hit.dm;

public class DataModel<T> implements java.io.Serializable{
    private T content;
    private java.lang.Long DataModelId;


    public DataModel(java.lang.Long id, T content){
        this.DataModelId =id;
        this.content=content;
    }

    public DataModel(){
        this.DataModelId =1L;
        this.content=null;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Long getDataModelId() {
        return DataModelId;
    }

    public void setDataModelId(Long dataModelId) {
        DataModelId = dataModelId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataModel dataModel = (DataModel) o;
        return DataModelId.equals(dataModel.DataModelId);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "DataModel{" +
                " DataModelId=" +DataModelId  +
                ",content=" + content +
                '}';
    }

}
