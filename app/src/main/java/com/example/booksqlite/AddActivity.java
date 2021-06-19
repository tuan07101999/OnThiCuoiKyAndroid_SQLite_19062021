package com.example.booksqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    Button btAdd,btBack;
    EditText edtName,edtAuthor,edtDiscrip;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().hide();
        databaseHelper = new DatabaseHelper(this);
        btAdd = findViewById(R.id.btnAddtoList);
        btBack = findViewById(R.id.btnMoveToMain2);

        edtName = findViewById(R.id.edtBookNameADD);
        edtAuthor = findViewById(R.id.edtAuthorAdd);
        edtDiscrip = findViewById(R.id.edtDiscriptionADD);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String author = edtAuthor.getText().toString();
                String dis = edtDiscrip.getText().toString();
                databaseHelper.addBook(new Book(name,author,dis));
                add();
                startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                finish();
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void add(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://60b092371f26610017ffe77b.mockapi.io/books";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(AddActivity.this,"Thêm thành công API",Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> postParam= new HashMap<String, String>();
                postParam.put("name", edtName.getText().toString().trim());
                postParam.put("author", edtAuthor.getText().toString().trim());
                postParam.put("discription", edtDiscrip.getText().toString().trim());
                return postParam;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}