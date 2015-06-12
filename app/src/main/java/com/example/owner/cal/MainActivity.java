package com.example.owner.cal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{
    EditText input,result;
//    EditText test;
    String resultPostfix;
    double dresult;
    boolean complete=false;
    boolean dotfree=true;
    int[] numButton = {R.id.num1, R.id.num2, R.id.num3, R.id.num4, R.id.num5, R.id.num6, R.id.num7, R.id.num8,
            R.id.num9, R.id.num0, R.id.num00, R.id.nudot, R.id.numc, R.id.numequal,
            R.id.numplus, R.id.nummult, R.id.nummin, R.id.numdiv, R.id.numback};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        input = (EditText)findViewById(R.id.in);
        input.setFocusable(false);  //수정불가
        input.setClickable(false);
        result =(EditText)findViewById(R.id.result);
        result.setFocusable(false);  //수정불가
        result.setClickable(false);
        //
//        test = (EditText)findViewById(R.id.test1) ;

        for(int i=0; i < numButton.length;i++) {
            findViewById(numButton[i]).setOnClickListener(buttonListener);
        } //end for
    }//onCreate

    OnClickListener buttonListener = new OnClickListener(){
    public void onClick(View V) {

        if(V!=null) {
            if(complete==true) {
                complete=false; input.setText("");
            }//end if
            switch(V.getId()){
                case R.id.num00:
                    if(input.getText().toString().equals("0")) {
                        break;
                    } else if(input.getText().toString().equals("")) {
                        input.append("0");
                        break;
                    } else {
                        input.append("00");
                        break;
                    }// end else
                case R.id.num0:
                    if(input.getText().toString().equals("0")) {
                        break;
                    } else if(input.getText().toString().equals("")) {
                        input.append("0");
                        break;
                    } else{
                        input.append("0");
                        break;
                    }// end else
                case R.id.num1:
                    input.append("1");
                    break;
                case R.id.num2:
                    input.append("2");
                    break;
                case R.id.num3:
                    input.append("3");
                    break;
                case R.id.num4:
                    input.append("4");
                    break;
                case R.id.num5:
                    input.append("5");
                    break;
                case R.id.num6:
                    input.append("6");
                    break;
                case R.id.num7:
                    input.append("7");
                    break;
                case R.id.num8:
                    input.append("8");
                    break;
                case R.id.num9:
                    input.append("9");
                    break;

                case R.id.numback:
                    if(input.length()!=0) {
                        input.setText(input.getText().toString().substring(0, input.getText().toString().length() - 1));
                    }//end if
                    break;

                case  R.id.numc :
                    if(input.length()!=0 || result.length()!=0) {
                        input.setText("");
                        result.setText("");
                        complete=false;
                        dotfree=true;   //모두 초기화
                    }//end if

                case R.id.nudot:
                    if(dotfree==true){
                        if(input.getText().toString().equals("")) {
                            break;
                        }//end if
                        input.append(".");
                        dotfree=false;
                        break;
                    } else {
                        break;
                    }//end else

                case R.id.numplus :
                    if(OperationCheck(input.getText().toString())==false){
                        input.append("+");
                        dotfree=true;
                    } else {
                        Toast.makeText(MainActivity.this, "연산이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.nummin :
                    if(OperationCheck(input.getText().toString())==false){
                        input.append("-");
                        dotfree=true;
                    } else {
                        Toast.makeText(MainActivity.this, "연산이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    }//end else
                    break;
                case R.id.nummult :
                    if(OperationCheck(input.getText().toString())==false){
                        input.append("*");
                        dotfree=true;
                    } else {
                        Toast.makeText(MainActivity.this, "연산이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    }//end else
                    break;
                case R.id.numdiv :
                    if(OperationCheck(input.getText().toString())==false){
                        input.append("/");
                        dotfree=true;
                    } else {
                        Toast.makeText(MainActivity.this, "연산이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    }//end else
                    break;

                case R.id.numequal:
                    if(NothingOperationCheck(input.getText().toString())==true){
                        if(OperationCheck(input.getText().toString())==false){
                            try {
                                String resultPostfix = Calculation.postfix(input.getText().toString());
                                //
//                                test.setText(resultPostfix);

                                try {
                                    dresult = Calculation.postfixCalc(resultPostfix);

                                    result.setText( Double.toString(dresult));
                                    complete=true;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            break;
                        }//end else
                    }else {
                        result.setText(input.getText().toString());
                    }//end else
                }
            }
        }

        public boolean OperationCheck(String a){
            if(a.endsWith("+") || a.endsWith("-") || a.endsWith("*") || a.endsWith("/") ) {
                return true;
            } else {
                return false;
            }//end else
        }

        public boolean NothingOperationCheck(String a){
            if(input.getText().toString().contains("+")){
                return true;
            } else if(input.getText().toString().contains("*")){
                return true;
            } else if(input.getText().toString().contains("-")){
                return true;
            } else if(input.getText().toString().contains("/")){
                return true;
            } else  {
                return false;
            }//end else

        }
    };
}