package ua.kiev.naukma.auth.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;

public class PagingResult<T> implements IsSerializable {
    private ArrayList<T> result;
    private int allLength;


    public PagingResult() {
    }

    public ArrayList<T> getResult() {
        return result;
    }

    public void setResult(ArrayList<T> result) {
        this.result = result;
    }

    public int getAllLength() {
        return allLength;
    }

    public void setAllLength(int allLength) {
        this.allLength = allLength;
    }
}
