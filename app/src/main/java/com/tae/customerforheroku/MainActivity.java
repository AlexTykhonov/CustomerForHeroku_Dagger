package com.tae.customerforheroku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnAddBook;
    Button btnGetBookList;
    ListView listView;
    CustInterface custInterface;
    List<Customer> listOfCustomers = new ArrayList<>();
    RecyclerView recyclerView;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, listOfCustomers);
        recyclerView.setAdapter(adapter);

        btnAddBook = findViewById(R.id.btnAddBook);
        btnGetBookList = findViewById(R.id.btnGetBookList);

        custInterface = ApiUtils.getCustInterface();
        getBookList();

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CustActivity.class);
                intent.putExtra("bookName", "");
                startActivity(intent);
            }
        });
    }

    public void getBookList(){
        System.out.println("-------------------------------------GET BOOK LIST used ");
        Call<List<Customer>> call = custInterface.getCustomers();
        call.enqueue(new Callback<List<Customer>>(){
            @Override
            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
                if(response.isSuccessful()){
                    listOfCustomers = response.body();
                    adapter.setList(listOfCustomers);
                    System.out.println("___________________________________" + listOfCustomers);
                }
               // listView.setAdapter(new CustomerAdapter(MainActivity.this, R.layout.listcustomer, listOfCustomers));

            }
            @Override
            public void onFailure(Call<List<Customer>> call, Throwable t) {
                System.out.println("++++++++++++++++++++++++++++++ ERROR "+t);
            }
        });
    }


}