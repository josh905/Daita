package daita.daita.Fingal;

/**
 * Created on 14/03/2017.
 */

public class FingalPopulation {


    //c for census
    private String place,  c2011, c2006, c2002, c1996, c1991, c1986, c1981;

    public FingalPopulation() {
        place = "";
        c1981="";
        c1986="";
        c1991="";
        c1996="";
        c2002="";
        c2006="";
        c2011="";
    }



    public String getPlace() {
        return place;
    }


    public String getC2011() {
        return c2011;
    }

    public String getC2006() {
        return c2006;
    }

    public String getC2002() {
        return c2002;
    }

    public String getC1996() {
        return c1996;
    }

    public String getC1991() {
        return c1991;
    }

    public String getC1986() {
        return c1986;
    }

    public String getC1981() {
        return c1981;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setC2011(String c2011) {
        this.c2011 = c2011;
    }

    public void setC2006(String c2006) {
        this.c2006 = c2006;
    }

    public void setC2002(String c2002) {
        this.c2002 = c2002;
    }

    public void setC1996(String c1996) {
        this.c1996 = c1996;
    }

    public void setC1991(String c1991) {
        this.c1991 = c1991;
    }

    public void setC1986(String c1986) {
        this.c1986 = c1986;
    }

    public void setC1981(String c1981) {
        this.c1981 = c1981;
    }

    public void setTitles(String titles){
        this.place = titles;            //must be place
    }

    public String getTitles(){
        return place;           //must be place
    }

}
