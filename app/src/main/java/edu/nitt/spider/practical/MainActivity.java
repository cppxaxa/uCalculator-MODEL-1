package edu.nitt.spider.practical;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    TextView tvOuput;
    CalcInputParser evaluator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOuput = (TextView)findViewById(R.id.tvOutput);
        evaluator = new CalcInputParser();
    }

    protected void setTVOutput(String symbol){
        this.tvOuput.setText(this.tvOuput.getText()+symbol);
    }

    public void set0(View v){
        setTVOutput("0");
        //Toast.makeText(getApplicationContext(),"Hello", Toast.LENGTH_SHORT).show();
    }
    public void set1(View v){
        setTVOutput("1");
    }
    public void set2(View v){
        setTVOutput("2");
    }
    public void set3(View v){
        setTVOutput("3");
    }
    public void set4(View v){
        setTVOutput("4");
    }
    public void set5(View v){
        setTVOutput("5");
    }
    public void set6(View v){
        setTVOutput("6");
    }
    public void set7(View v){
        setTVOutput("7");
    }
    public void set8(View v){
        setTVOutput("8");
    }
    public void set9(View v){
        setTVOutput("9");
    }
    public void setPlus(View v){
        setTVOutput("+");
    }
    public void setMinus(View v){
        setTVOutput("-");
    }
    public void setAsterisk(View v){
        setTVOutput("*");
    }
    public void setSlash(View v){
        setTVOutput("/");
    }
    public void fEqual(View v){
        this.tvOuput.setText(this.evaluator.parse(this.tvOuput.getText().toString()));
    }
    public void setClear(View v){
        this.tvOuput.setText("");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
