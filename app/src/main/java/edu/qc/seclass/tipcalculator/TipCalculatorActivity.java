package edu.qc.seclass.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TipCalculatorActivity extends AppCompatActivity {
    double checkAmount;
    int partySize;
    //declaration of objects to hold the data of the identifiers
    EditText checkAmountInput;
    EditText partySizeInput;
    Button computeButton;
    EditText percent15Tip;
    EditText percent20Tip;
    EditText percent25Tip;
    EditText percent20Total;
    EditText percent15Total;
    EditText percent25Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        //Initialize the identifiers to the declared objects
        checkAmountInput = findViewById(R.id.checkAmountValue);
        partySizeInput = findViewById(R.id.partySizeValue);
        computeButton = findViewById(R.id.buttonCompute);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calls method to close keyboard upon button press
                closeKeyboard();
                //throws a toast message if the input values of check amount and party size are invalid.
                if ((Integer.valueOf(partySizeInput.getText().toString()) <= 0) || (Integer.valueOf(checkAmountInput.getText().toString())) <= 0) {
                    Toast.makeText(getApplicationContext(), "Invalid Inputs", Toast.LENGTH_LONG).show();
                    //set the values back to null after every calculation.
                    percent15Tip.setText("");
                    percent20Tip.setText("");
                    percent25Tip.setText("");
                    percent15Total.setText("");
                    percent20Total.setText("");
                    percent25Total.setText("");
                    }
                //otherwise starts computing tips and total
                else {
                    //initialize all editText Id's to corresponding objects
                    percent15Tip = findViewById(R.id.fifteenPercentTipValue);
                    percent20Tip = findViewById(R.id.twentyPercentTipValue);
                    percent25Tip = findViewById(R.id.twentyfivePercentTipValue);

                    percent15Total = findViewById(R.id.fifteenPercentTotalValue);
                    percent20Total = findViewById(R.id.twentyPercentTotalValue);
                    percent25Total = findViewById(R.id.twentyfivePercentTotalValue);

                    checkAmount = Double.valueOf(checkAmountInput.getText().toString());
                    partySize = Integer.valueOf((partySizeInput.getText().toString()));

                    //calls calculatetip method 3 times, each for the different tip bracket
                    percent15Tip.setText(String.valueOf(calculateTip(checkAmount, partySize, 0.15)));
                    percent20Tip.setText(String.valueOf(calculateTip(checkAmount, partySize, 0.20)));
                    percent25Tip.setText(String.valueOf(calculateTip(checkAmount, partySize, 0.25)));

                    //calls calculatettotal method 3 times, each for the different tip bracket
                    percent15Total.setText(String.valueOf(calculateTotal(checkAmount, partySize, 0.15)));
                    percent20Total.setText(String.valueOf(calculateTotal(checkAmount, partySize, 0.20)));
                    percent25Total.setText(String.valueOf(calculateTotal(checkAmount, partySize, 0.25)));
                    }
            }
        });
    }

    //method that calculates the tip
    public int calculateTip(double checkAmount, int partySize, double percent){
        int total = (int) Math.ceil((checkAmount / partySize) * percent);
        return  total;
        }

    //method that calculates the total including tip
    public int calculateTotal(double checkAmount, int partySize, double percent){
        double tip = ((checkAmount / partySize) * percent);
        int total = (int) Math.ceil((checkAmount / partySize) + tip);
        return  total;
        }

    //method to close the keyboard upon call
    public void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }

    }