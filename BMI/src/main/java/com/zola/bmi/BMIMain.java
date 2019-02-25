package com.zola.bmi;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;


public class BMIMain extends AppCompatActivity {

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

            Spinner weightSpinner = (Spinner)findViewById(R.id.weightSpinner);
            Spinner heightSpinner = (Spinner)findViewById(R.id.heightSpinner);
            String weightSpinnerString = weightSpinner.getSelectedItem().toString();
            String heightSpinnerString = heightSpinner.getSelectedItem().toString();

            double weight;
            weight = 0;
            double height;
            height = 0;

            // get the users values from the widget references
            if (!(weightNum.getText().toString().equals(""))) {
                weight = Double.parseDouble(weightNum.getText().toString());
            }

            if (!(heightNum.getText().toString().equals(""))) {
                height = Double.parseDouble(heightNum.getText().toString());
            }

            double bmi;

            // calculate bmi value - pounds and inch
            if (weightSpinnerString.equals("Pounds") && heightSpinnerString.equals("Inches")) {
                bmi = calculateBMI(weight, height);
            } else if (weightSpinnerString.equals("Kilograms") &&
                    heightSpinnerString.equals("Inches")){
                weight = weight * 2.205;
                bmi = calculateBMI(weight, height);
            } else if (weightSpinnerString.equals("Pounds") && heightSpinnerString.equals("Centimetres")){
                height = height / 2.54;
                bmi = calculateBMI(weight, height);
            } else {
                weight = weight * 2.205;
                height = height / 2.54;
                bmi = calculateBMI(weight, height);
            }

            // round to 1 digit
            double newBMI = Math.round(bmi*10.0)/10.0;
            DecimalFormat f = new DecimalFormat("##.0");

            // interpret the meaning of the bmi value
            String bmiInterpretation = interpretBMI(newBMI);

            // now set the value in the results text
            resultLabel.setText("BMI Score = " + newBMI + "\n" + bmiInterpretation);
        }
    }

    // the formula to calculate the BMI index
    private double calculateBMI (double weight, double height) {
        // convert values to metric
            return (double) (((weight / 2.2046) / (height * 0.0254)) / (height * 0.0254));
    }

    // interpret what BMI means
    private String interpretBMI(double bmi) {

        if (bmi == 0) {
            return "Enter your details";
        } else if (bmi < 18.5) {
            return "You are underweight";
        } else if (bmi < 25) {
            return "You are normal weight";
        } else if (bmi < 30) {
            return "You are overweight";
        } else if (bmi < 40) {
            return "You are obese";
        } else {
            return "You are severely obese";
        }
    }

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
