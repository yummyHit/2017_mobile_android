package com.test.yummy.finalterm;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.test.yummy.finalterm.LoginActivity.DB_NAME;

/**
 * Created by hyeoks on 2017-06-12.
 */

public class JoinActivity extends AppCompatActivity {
    private Button registOKBtn, registCancelBtn;
    private EditText etRegistID, etRegistPW, etRegistPWCK, etRegistName, etRegistPhone;
    private TextView tvOX;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), DB_NAME, null, 1);

        registOKBtn = (Button)findViewById(R.id.registOKBtn);
        registCancelBtn = (Button)findViewById(R.id.registCancelBtn);

        etRegistID = (EditText)findViewById(R.id.etRegistID);
        etRegistPW = (EditText)findViewById(R.id.etRegistPW);
        etRegistPWCK = (EditText)findViewById(R.id.etRegistPWCK);
        etRegistName = (EditText)findViewById(R.id.etRegistName);
        etRegistPhone = (EditText)findViewById(R.id.etRegistPhone);

        tvOX = (TextView)findViewById(R.id.tvOX);

        etRegistPWCK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //REGISTRATION PASSWORD CHECK
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etRegistPW.getText().toString().equals(etRegistPWCK.getText().toString())){
                    tvOX.setText("O");
                    tvOX.setTextColor(Color.GREEN);
                    tvOX.setTextSize(18);
                } else{
                    tvOX.setText("X");
                    tvOX.setTextColor(Color.RED);
                    tvOX.setTextSize(18);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //REGISTRATION USER INFORMATION
        registOKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(etRegistID.getText().toString().isEmpty() || etRegistPW.getText().toString().isEmpty() || etRegistPWCK.getText().toString().isEmpty() || etRegistName.getText().toString().isEmpty() || etRegistPhone.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "빈 칸을 먼저 채워주세요.", Toast.LENGTH_SHORT).show();
              } else {
                final String id = etRegistID.getText().toString();
                final String pw = etRegistPW.getText().toString();
                final String pwck = etRegistPWCK.getText().toString();
                final String name = etRegistName.getText().toString();
                final String phone = etRegistPhone.getText().toString();

                //GET SYSTEM TIME
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat curDateFormat = new SimpleDateFormat("yyyy-mm-dd");
                final String joindate = curDateFormat.format(date);

                if (!pw.equals(pwck)) {
                  Toast.makeText(getApplicationContext(), "CHECK YOUR PASSWORD!!", Toast.LENGTH_SHORT).show();
                } else {
                  dbHelper.insertRegistData(id, pw, name, phone, joindate);
                  finish();
                }
              }
            }
        });

        registCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
