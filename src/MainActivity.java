package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
//In this project I created a calculator for an android device
public class MainActivity extends AppCompatActivity {
    //Stores the result
    private EditText result;
    //Stores the currently entered number
    private EditText newNumber;
    //Stores the operation
    private TextView displayOperation;
    //String that can't be changed and is used as a key
    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    //String that can't be changed and is used as a key
    private static final String STATE_OPERAND1 = "Operand1";

    //Variables to hold the operands and type of calculation
    private Double operand1 = null;
    private String pendingOperation = "=";

    //OnCreate stores everything, what happens when the app gets started
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instance variables are set to the according fields
        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);

        //Button variables are set to the according buttons
        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonDot = (Button) findViewById(R.id.buttonDot);

        Button buttonEquals = (Button) findViewById(R.id.buttonEquals);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
        Button buttonClear = (Button) findViewById(R.id.buttonClear);

        //Defining clear button
        //When clicked all textfields are set to an empty string and the operand is set to null
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newNumber.setText("");
                displayOperation.setText("");
                result.setText("");
                operand1 = null;


            }
        });


        Button buttonNeg = (Button) findViewById(R.id.buttonNeg);
        //Defining negative Button
        //When clicked, the signature is changed from positive to negative or from negative to positive
        buttonNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = newNumber.getText().toString();
                //if no number was entered yet, a minus is simply displayed and also added to the value
                if(value.length() == 0){
                    newNumber.setText("-");
                }else {
                    //If a number was already entered it gets multiplied by minus one, to change the signature
                    try{
                        Double doubleValue = Double.valueOf(value);
                        doubleValue *= -1;
                        newNumber.setText(doubleValue.toString());
                    }catch(NumberFormatException e){
                        newNumber.setText("");
                    }
                }
            }
        });
        //Onlicklistener for all number buttons + the dot button
        //When a button gets clicked, it's number gets added to the newNumber Field
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newNumber.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        //Onclicklistener for operations
        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if statement to check if both, the result and the newNumber fields are empty
                //if so, we return nothing. Without this statement it's possible to enter operations before entering a number, which was really disturbing to me
                if(result.getText().toString().equals("") && newNumber.getText().toString().equals("")) return;
                Button b = (Button) v;
                //op holds the operation of the clicked button
                String op = b.getText().toString();
                //value holds the String of the newNumber field
                String value = newNumber.getText().toString();
                try{
                    //We call the method performOperation, which sets the result field to the outcome of the operation
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);
                }catch(NumberFormatException e){
                    //if no number was entered yet, pretty much nothing happens.
                    //entering a operation before entering a number, was also prevented by the first if statement
                    newNumber.setText("");
                }
                //The pending operation is set to the clicked button and the displayOperation field is set according to the operation
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };
        //Setting operation buttons to the onClickListener
        buttonEquals.setOnClickListener(opListener);
        buttonDivide.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);
        buttonPlus.setOnClickListener(opListener);

    }
    //Performs the operation, when a operation button is clicked
    private void performOperation(Double value, String op){
        //First we need to check, if operand1 is null. If we don't have two operands, we can't perform our simple mathematical operations.
        //if operand1 is null, we set operation1 to the entered value, skip the operations for now and set the result field to operand1
        //Afterwards the newNumber field gets cleared
        if(operand1 == null){
            operand1 = value;
        }else{
            //We only enter the else-statement, if we already have a number in the result field.

            if(pendingOperation.equals("=")){
                pendingOperation = op;
            }
            //switching through the possible operations
            switch (pendingOperation){

                case "=":
                    operand1 = value;
                    break;
                //the number in the result field gets divided by the entered value
                case "/":
                    //We check if the number is 0, as a division with 0 is not allowed
                    if(value == 0){
                        operand1 = 0.0;
                    }else{
                        operand1 /= value;
                    }
                    break;
                case "*":
                    //the number in the result field gets multiplied by the entered value
                    operand1 *= value;
                    break;
                case "-":
                    //the entered number gets subtracted from the number in the result field
                    operand1 -= value;
                    break;
                case "+":
                    //the entered number gets added from the number in the result field
                    operand1 += value;
                    break;
            }
        }
        //The result is set to operand1
        result.setText(operand1.toString());
        //the new number field is cleared
        newNumber.setText("");
    }
    //When switching from the normal layout to the landscape layout, the build gets destroyed, and everything entered is lost.
    //to prevent that from happening we save some variables in onSaveInstanceState
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //We save the pendingOperation and the operand1, so we don't lose them, when switching to landscape mode (or back)
        outState.putString(STATE_PENDING_OPERATION, pendingOperation);
        if(operand1 != null){
            outState.putDouble(STATE_OPERAND1, operand1);
        }
        super.onSaveInstanceState(outState);
    }

    //After Switching layout, we restore our saved variables.
    //This happens in onRestoreInstanceState
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Restoring variables
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
        //Setting the displayOperation field to the restored operation
        displayOperation.setText(pendingOperation);
    }


}