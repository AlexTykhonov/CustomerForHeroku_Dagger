package com.tae.customerforheroku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustActivity extends AppCompatActivity {

    CustInterface custInterface;
    EditText newid;
    EditText newname;
    EditText newsurname;
    EditText newage;
    Button buttonSave;
    Button buttonDelete;
    Button buttonUpdate;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        newid = findViewById(R.id.newid);
        newname = findViewById(R.id.newname);
        newsurname = findViewById(R.id.newsurname);
        newage = findViewById(R.id.newage);

        buttonSave = findViewById(R.id.buttonSave);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        custInterface = ApiUtils.getCustInterface();

        Long custid = 0L;
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            custid = extras.getLong("id");
           String firstname = extras.getString("firstname");
           String lastame = extras.getString("lastame");
           String age = extras.getString("age");
        }

        if(custid>0){
            newid.setFocusable(false);
            newid.setText(custid.toString());
        } else{
            buttonDelete.setVisibility(View.INVISIBLE);
            buttonUpdate.setVisibility(View.INVISIBLE);
        }


        Long finalCustid = custid;
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer customer = new Customer();
                customer.setCustId(Long.valueOf(newid.getText().toString()));
                System.out.println(" >>>>>>>>>>>>>>>>> THIS IS custid"+ finalCustid);
                customer.setFirstname(newname.getText().toString());
                customer.setLastname(newsurname.getText().toString());
                int age =Integer.valueOf(newage.getText().toString());
                customer.setAge(age);

                if(finalCustid >0){
                    updateCustomer(Long.valueOf(newid.getText().toString()), customer);
                } else{
                    saveCustomer(customer);
                }

                System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>CUSTOMER!!!"+customer);
                                         }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String userId = String.valueOf(extras.getInt("id"));
//                deleteBook(Integer.parseInt(userId));
            }
            });
    }



   public void saveCustomer (final Customer customer) {

            custInterface.postCustomer(customer)
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResults, this::handleError);
    }

    void handleResults (Customer savedCustomers){
        if (savedCustomers != null) {
            System.out.println("&&*^&*^&*^*^^*&^*^*&^*&^^&*^**&^^&* THIS IS SAVED CUSTOMER  ---> " + savedCustomers);
        } else {
            Toast.makeText(this, "NO RESULTS FOUND", Toast.LENGTH_LONG).show();
        }
    }

    void handleError (Throwable t){
        System.out.println(t + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }







    public void updateCustomer (final long id, final Customer customer){

//        custInterface.updateCustomer(id, customer)
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(this::handleResults, this::handleError);
//
//        System.out.println("!!!! Update Customer method was called. If here is" +id+" and customer is"+ customer+". Attention!");
//
//        void handleResults (List<Customer> listOfCustomers){
//            if (listOfCustomers != null) {
//                System.out.println("&&*^&*^&*^*^^*&^*^*&^*&^^&*^**&^^&* THIS IS LIST OF CUSTOMERS  ---> " + listOfCustomers);
//                adapter.setList(listOfCustomers);
//            } else {
//                Toast.makeText(this, "NO UPDATED CUSTOMERS", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        void handleError (Throwable t){
//            System.out.println(t + "ERROR WHILE UPDATE");
//        }

    }



    public void deleteBook(final int id){
//        Call<Customer> callBook = bookInterface.deleteBook(id);
//        System.out.println(id);
//        callBook.enqueue(new Callback<Customer>(){
//            @Override
//            public void onResponse(Call<Customer> call, Response<Customer> response) {
//                if(response.isSuccessful()) {
//                    Toast.makeText(BookActivity.this, "Deletion was successful! " + id, Toast.LENGTH_SHORT).show();
//                } else{
//                    Toast.makeText(BookActivity.this, "Unsuccessful!", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                Toast.makeText(BookActivity.this, "Deletion was not executed!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}


//  реализовать апдейт