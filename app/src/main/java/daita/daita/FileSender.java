package daita.daita;

import java.util.ArrayList;

/**
 * Created on 15/03/2017.
 */

public class FileSender {

    //c for census
    private String val, n1, n2, n3, n4, n5;

    public FileSender() {
        val = "";
        n1 = "";
        n2 = "";
        n3 = "";
        n4 = "";
        n5 = "";
    }

    public String getN1() {
        return n1;
    }

    public String getN2() {
        return n2;
    }

    public String getN3() {
        return n3;
    }

    public String getN4() {
        return n4;
    }

    public String getN5() {
        return n5;
    }

    public void setVal(int i, String val) {
        this.val = val;
    }

    public String getVal(){
        return val;
    }
}
