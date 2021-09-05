package main.java.com.hit.view;

import java.io.Serializable;

public class DataModel<T> implements Serializable {
    private T content;
    private Long DataModelId;

    public DataModel(Long id, T content) {
        this.DataModelId = id;
        this.content = content;
    }

    public DataModel() {
        this.DataModelId = 1L;
        this.content = null;
    }

    public T getContent() {
        return this.content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Long getDataModelId() {
        return this.DataModelId;
    }

    public void setDataModelId(Long dataModelId) {
        this.DataModelId = dataModelId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            DataModel dataModel = (DataModel)o;
            return this.DataModelId.equals(dataModel.DataModelId);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return "DataModel{ID=" + this.DataModelId  + ", content=" + this.content + "}";
    }
}
