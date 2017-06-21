package com.test.yummy.finalterm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

  final static String DB_NAME = "TEST_DB.db";
  final static String REGIST_TABLE_NAME = "REGIST_TABLE";

  private Button OKBtn, cancelBtn;
  private TextView registrationTxt;
  private EditText etLoginID, etLoginPW;
  SQLiteDatabase db;
  DBHelper dbHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    OKBtn = (Button)findViewById(R.id.OKBtn);
    cancelBtn = (Button)findViewById(R.id.cancelBtn);
    registrationTxt = (TextView)findViewById(R.id.registrationTxt);

    etLoginID = (EditText)findViewById(R.id.etLoginID);
    etLoginPW = (EditText)findViewById(R.id.etLoginPW);

    registrationTxt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
        startActivity(intent);
      }
    });

    dbHelper = new DBHelper(LoginActivity.this, DB_NAME, null, 1);

    OKBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        db = dbHelper.getReadableDatabase();
        String id = etLoginID.getText().toString();
        String pw = etLoginPW.getText().toString();
        String db_id = null;
        String db_pw = null;

        Cursor cursor = db.rawQuery("SELECT * FROM TEST_TABLE", null);
        //READ USER ID & PASSWORD
        while(cursor.moveToNext()){
          db_id = cursor.getString(cursor.getColumnIndex("USER_ID"));
          db_pw = cursor.getString(cursor.getColumnIndex("USER_PW"));
        }

        //LOGIN PASSWORD CHECK
        if(id.equals(db_id) && pw.equals(db_pw)){
          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
          startActivity(intent);
        } else if(TextUtils.isEmpty(id) || TextUtils.isEmpty(pw)){
          Toast.makeText(getApplicationContext(), "INPUT ID OR PASSWORD!", Toast.LENGTH_SHORT).show();
        } else {
          Toast.makeText(getApplicationContext(), "CHECK YOUR ID OR PASSWORD!!", Toast.LENGTH_SHORT).show();
        }
      }
    });

    //FINISH ACTIVITY
    cancelBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }
}
