package daita.daita.Fingal;

    import android.content.Context;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.TextView;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;

    import daita.daita.R;

    /**
     * Created on 14/03/2017.
     */

public class FingalPopulationTitles extends ArrayAdapter<FingalPopulation> {

    private Context con;
    private FingalPopulation ob;


    public FingalPopulationTitles(Context con, int res){
        super(con,res);

        this.con = con;

        parse();

    }



    private void parse(){
        int r = R.raw.fingal_population_res;
        try{
            InputStream input = con.getResources().openRawResource(r);
            BufferedReader buff = new BufferedReader(new InputStreamReader(input));
            String col;

            while ((col = buff.readLine())!=null){
                //make an array for this data
                String[] array = col.split(",");


                ob = new FingalPopulation();
                ob.setTitles(array[0]);
                this.add(ob);

            }

        }
        catch(IOException error){
            error.printStackTrace();
        }
    }



    //possibly re-do this into its own class ? (a generic version suited for all data)


    public View getView(final int pos, View convertView, final ViewGroup parent){
        TextView theView = (TextView)convertView;

        if(theView==null){
            theView = new TextView(parent.getContext());
            theView.setTextSize(29);
        }

        theView.setText(getItem(pos).getTitles());

        return theView;
    }









}