package com.apress.gerber.trytodo18_iofile;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    Button bn ;
    EditText et1;
    EditText et2;
    String FILE_NAME = "trytry.txt";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bn = (Button) findViewById(R.id.button);
        bn.setOnClickListener(new View.OnClickListener() { //写入信息button
            FileOutputStream fos = null ;
            public void onClick(View view) {
                et1 = (EditText) findViewById(R.id.editText);
                et2 = (EditText) findViewById(R.id.editText2);
                try {
                    fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
                    fos.write(("name:"+ et1.getText().toString()+
                            " ID:"+ et2.getText().toString()).getBytes(StandardCharsets.UTF_8));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if(fos != null)
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }


            }
        });

        bn =(Button)findViewById(R.id.button2);
        bn.setOnClickListener(new View.OnClickListener() {
            String ss;
            int c;
            public void onClick(View view) {
                FileInputStream fis = null;
                ss ="";
                try{
                    fis = openFileInput(FILE_NAME);
                    c = fis.read();
                        while(c!= -1) {
                            ss = ss+(char)c;
                            c = fis.read();
                         }
                    }
                catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if(fis!=null)
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }

                Toast.makeText(MainActivity.this,ss,Toast.LENGTH_LONG).show();
            }
        });
    }

}
