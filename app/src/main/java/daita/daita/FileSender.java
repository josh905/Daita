package daita.daita;

import java.util.ArrayList;

/**
 * Created on 15/03/2017
 * @author  Josh Reynolds x15389521
 */

public class FileSender {

    //c for column
    private String c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,
    c11,c12,c13,c14,c15,c16,c17,c18,c19,c20;

    private int numCols;



    public FileSender(String c1, String c2, String c3, String c4, String c5, String c6, String c7, String c8, String c9, String c10, String c11, String c12, String c13, String c14, String c15, String c16, String c17, String c18, String c19, String c20) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.c5 = c5;
        this.c6 = c6;
        this.c7 = c7;
        this.c8 = c8;
        this.c9 = c9;
        this.c10 = c10;
        this.c11 = c11;
        this.c12 = c12;
        this.c13 = c13;
        this.c14 = c14;
        this.c15 = c15;
        this.c16 = c16;
        this.c17 = c17;
        this.c18 = c18;
        this.c19 = c19;
        this.c20 = c20;
        numCols=0;
    }


    public String getC1() {
        return c1;
    }

    public String getC2() {
        return c2;
    }

    public String getC3() {
        return c3;
    }

    public String getC4() {
        return c4;
    }

    public String getC5() {
        return c5;
    }

    public String getC6() {
        return c6;
    }

    public String getC7() {
        return c7;
    }

    public String getC8() {
        return c8;
    }

    public String getC9() {
        return c9;
    }

    public String getC10() {
        return c10;
    }

    public String getC11() {
        return c11;
    }

    public String getC12() {
        return c12;
    }

    public String getC13() {
        return c13;
    }

    public String getC14() {
        return c14;
    }

    public String getC15() {
        return c15;
    }

    public String getC16() {
        return c16;
    }

    public String getC17() {
        return c17;
    }

    public String getC18() {
        return c18;
    }

    public String getC19() {
        return c19;
    }

    public String getC20() {
        return c20;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }
}
