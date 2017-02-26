package com.example.firoz.wallpaperchange;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private Button SetButton,UnsetButton;
    private RadioGroup TimeRadioGroup;
    private RadioButton OneMin, FiveMin,TenMin,ThirtyMin,OneHour;

    public static int ChangeTime=60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetButton=(Button)findViewById(R.id.button);
        UnsetButton=(Button)findViewById(R.id.button2);
        OneMin=(RadioButton)findViewById(R.id.radioButton1);
        FiveMin=(RadioButton)findViewById(R.id.radioButton2);
        TenMin=(RadioButton)findViewById(R.id.radioButton3);
        ThirtyMin=(RadioButton)findViewById(R.id.radioButton4);
        OneHour=(RadioButton)findViewById(R.id.radioButton5);
        TimeRadioGroup=(RadioGroup)findViewById(R.id.radioGroup1);

        SetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //it will return Id of radio button which i have selected
               int RadioId=TimeRadioGroup.getCheckedRadioButtonId();

                switch(RadioId)
                {
                    case R.id.radioButton1:
                        ChangeTime=30;
                        break;
                    case R.id.radioButton2:
                        ChangeTime=1*60;
                        break;
                    case R.id.radioButton3:
                        ChangeTime=2*60;
                        break;
                    case R.id.radioButton4:
                        ChangeTime=5*60;
                        break;
                    case R.id.radioButton5:
                        ChangeTime=10*60;
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"Please select the valid option",Toast.LENGTH_SHORT).show();
                        return;

                }


                //it will start service

                Intent startIntent=new Intent(MainActivity.this,WallpaperChange.class);
                Bundle bundleTime=new Bundle();
                bundleTime.putInt("time",ChangeTime);
                startIntent.putExtras(bundleTime);
                startService(startIntent);
                //end the activity
                finish();
            }
        });
        UnsetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stopIntent=new Intent(MainActivity.this,WallpaperChange.class);
                stopService(stopIntent);
                finish();
            }
        });
    }
}
