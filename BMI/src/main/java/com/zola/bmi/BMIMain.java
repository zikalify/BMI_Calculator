package com.zola.bmi;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class BMIMain extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmimain);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public void calculateClickHandler(View view) {
        if (view.getId() == R.id.calcBMI) {

            // get the references to the widgets


            EditText weightNum = (EditText)findViewById(R.id.weightNum);
            EditText heightNum = (EditText)findViewById(R.id.heightNum);
            TextView resultLabel = (TextView)findViewById(R.id.resultLabel);

            // get the users values from the widget references
            double weight = Double.parseDouble(weightNum.getText().toString());
            double height = Double.parseDouble(heightNum.getText().toString());

            // calculate bmi value
            double bmi = calculateBMI(weight, height);

            // round to 2 digits
            double newBMI = Math.round(bmi*100.0)/100.0;

            // interpret the meaning of the bmi value
            String bmiInterpretation = interpretBMI(bmi);

            // now set the value in the results text
            resultLabel.setText(newBMI + "\nYou are " + bmiInterpretation);
        }
    }

    // the formula to calculate the BMI index
    private double calculateBMI (double weight, double height) {
        // convert values to metric
        return (double) (((weight/2.2046) / (height*0.0254)) / (height*0.0254));
    }

    // interpret what BMI means
    private String interpretBMI(double bmi) {

        if (bmi < 16) {
            return "Severely underweight";
        } else if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        }else if (bmi < 30) {
            return "Overweight";
        }else if (bmi < 40) {
            return "Obese";
        }else {
            return "Morbidly Obese";
        }
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bmimain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_bmimain, container, false);
            return rootView;
        }
    }

}
