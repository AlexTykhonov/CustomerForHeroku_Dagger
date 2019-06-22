package com.tae.customerforheroku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

        custInterface = getApi();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

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

    public static CustInterface getApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fluxcustomer.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        CustInterface custInterface = retrofit.create(CustInterface.class);
        return custInterface;

    }

    public void getBookList() {
        System.out.println("-------------------------------------GET BOOK LIST used ");

        custInterface.getCustomers()
                .subscribeOn(Schedulers.computation())
               .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);}

        public void handleResults (List <Customer> listOfCustomers) {
            if (listOfCustomers != null) {
                System.out.println("&&*^&*^&*^*^^*&^*^*&^*&^^&*^**&^^&* THIS IS LIST OF CUSTOMERS  ---> " + listOfCustomers);
               adapter.setList(listOfCustomers);
            } else {
                Toast.makeText(this, "NO RESULTS FOUND",
                        Toast.LENGTH_LONG).show();
            }
        }

        public void handleError (Throwable t){
                System.out.println(t+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        }





//    public void getBookList(){
//        System.out.println("-------------------------------------GET BOOK LIST used ");
//        Call<List<Customer>> call = custInterface.getCustomers();
//        call.enqueue(new Callback<List<Customer>>(){
//            @Override
//            public void onResponse(Call<List<Customer>> call, Response<List<Customer>> response) {
//                if(response.isSuccessful()){
//                    listOfCustomers = response.body();
//                    adapter.setList(listOfCustomers);
//                    System.out.println("___________________________________" + listOfCustomers);
//                }
//               // listView.setAdapter(new CustomerAdapter(MainActivity.this, R.layout.listcustomer, listOfCustomers));
//
//            }
//            @Override
//            public void onFailure(Call<List<Customer>> call, Throwable t) {
//                System.out.println("++++++++++++++++++++++++++++++ ERROR "+t);
//            }
//        });
//    }
