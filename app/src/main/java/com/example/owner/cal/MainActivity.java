package com.example.owner.cal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity{
    EditText input,result;
    String resultPostfix;
    double dresult;
    boolean complete=false;
    boolean dotfree=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        input =(EditText)findViewById(R.id.in);
        result =(EditText)findViewById(R.id.result);


        findViewById(R.id.num1).setOnClickListener(buttonListener);
        findViewById(R.id.num2).setOnClickListener(buttonListener);
        findViewById(R.id.num3).setOnClickListener(buttonListener);
        findViewById(R.id.num4).setOnClickListener(buttonListener);
        findViewById(R.id.num5).setOnClickListener(buttonListener);
        findViewById(R.id.num6).setOnClickListener(buttonListener);
        findViewById(R.id.num7).setOnClickListener(buttonListener);
        findViewById(R.id.num8).setOnClickListener(buttonListener);
        findViewById(R.id.num9).setOnClickListener(buttonListener);
        findViewById(R.id.num0).setOnClickListener(buttonListener);
        findViewById(R.id.num00).setOnClickListener(buttonListener);
        findViewById(R.id.nudot).setOnClickListener(buttonListener);
        findViewById(R.id.numc).setOnClickListener(buttonListener);

        findViewById(R.id.numequal).setOnClickListener(buttonListener);

        findViewById(R.id.numplus).setOnClickListener(buttonListener);
        findViewById(R.id.nummin).setOnClickListener(buttonListener);
        findViewById(R.id.nummult).setOnClickListener(buttonListener);
        findViewById(R.id.numdiv).setOnClickListener(buttonListener); }

    OnClickListener buttonListener = new OnClickListener(){

        public void onClick(View V) {
            switch(V.getId()){
                case R.id.num00:
                    if(complete==true){complete=false; input.setText("");}
                    if(input.getText().toString().equals(0)){
                        break;}
                    else input.append("00");break;

                case R.id.num0:
                    if(complete==true){complete=false; input.setText("");}
                    input.append("0"); break;
                case R.id.num1:
                    if(complete==true){complete=false; input.setText("");}
                    input.append("1");break;
                case R.id.num2:
                    if(complete==true){complete=false; input.setText("");}
                    input.append("2");break;
                case R.id.num3:
                    if(complete==true){complete=false; input.setText("");}
                    input.append("3"); break;
                case R.id.num4:
                    if(complete==true){complete=false; input.setText("");}
                    input.append("4"); break;
                case R.id.num5:
                    if(complete==true){complete=false; input.setText("");}
                    input.append("5"); break;
                case R.id.num6:
                    if(complete==true){complete=false; input.setText("");}
                    input.append("6"); break;
                case R.id.num7:
                    if(complete==true){complete=false; input.setText("");}
                    input.append("7"); break;
                case R.id.num8:
                    if(complete==true){complete=false; input.setText("");}
                    input.append("8"); break;
                case R.id.num9:
                    if(complete==true){complete=false; input.setText("");}
                    input.append("9"); break;
                case R.id.numc:{
                    if(input.length()!=0)
                        input.setText(input.getText().toString().substring(0,input.getText().toString().length()-1));
                    break;}

                case R.id.nudot:
                    if(dotfree==true){
                        //if(input.getText().toString().contains(".")) break;
                        input.append(""); dotfree=false; break; }
                    else   break;



                case R.id.numplus :
                    if(OperationCheck(input.getText().toString())==false){
                        input.append("+");
                        dotfree=true;
                    }
                    else break;
                case R.id.nummin :
                    if(OperationCheck(input.getText().toString())==false){
                        input.append("-"); dotfree=true;}
                    else break;
                case R.id.nummult :
                    if(OperationCheck(input.getText().toString())==false){
                        input.append("*");dotfree=true;}
                    else break;
                case R.id.numdiv :
                    if(OperationCheck(input.getText().toString())==false){
                        input.append("/"); dotfree=true;}
                    else break;
                case R.id.numequal:
                    if(NothingOperationCheck(input.getText().toString())==true){
                        if(OperationCheck(input.getText().toString())==false){
                            try {
                                String resultPostfix = Calculator.postfix(input.getText().toString());
                                try {
                                    dresult = Calculator.postfixCalc(resultPostfix);

                                    result.setText( Double.toString(dresult));
                                    complete=true;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else break;
                    }else result.setText(input.getText().toString());}
        }

        public boolean OperationCheck(String a){
            if(a.endsWith("+")) return true;
            else if (a.endsWith("-")) return true;
            else if (a.endsWith("*")) return true;
            else if (a.endsWith("/")) return true;
            else return false;
        }
        public boolean NothingOperationCheck(String a){
            if(input.getText().toString().contains("+")){return true;}
            else if(input.getText().toString().contains("*")){return true;}
            else if(input.getText().toString().contains("-")){return true;}
            else if(input.getText().toString().contains("/")){return true;}
            else return false;

        }
    };
}