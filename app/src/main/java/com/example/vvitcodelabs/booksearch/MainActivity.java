package com.example.vvitcodelabs.booksearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    EditText ed_bookname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.id_res);
        ed_bookname=findViewById(R.id.id_user_input);
    }

    public void search(View view) {
        String bookname=ed_bookname.getText().toString();
        MyTask task=new MyTask(this,tv);
        task.execute(bookname);
    }
}
