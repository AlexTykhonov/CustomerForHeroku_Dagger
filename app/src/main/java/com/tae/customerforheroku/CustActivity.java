package com.tae.customerforheroku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        final Bundle extras = getIntent().getExtras();

        final Long custid = extras.getLong("id");
        String firstname = extras.getString("firstname");
        String lastame = extras.getString("lastame");
        String age = extras.getString("age");


        if(custid>0){
            newid.setFocusable(false);
            newid.setText(custid.toString());
        } else{
            buttonDelete.setVisibility(View.INVISIBLE);
            buttonUpdate.setVisibility(View.INVISIBLE);
        }


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer customer = new Customer();
                customer.setCustId(custid);

                System.out.println(" >>>>>>>>>>>>>>>>> THIS IS custid"+custid);
                customer.setFirstname(newname.getText().toString());
                customer.setLastname(newsurname.getText().toString());
                int age =Integer.valueOf(newage.getText().toString());
                customer.setAge(age);
//
                if(custid>0){
                    updateCustomer(Long.valueOf(newid.getText().toString()), customer);
                } else{
                    saveCustomer(customer);
                }



//               System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>CUSTOMER!!!"+customer);
//               Call<Customer> customer1 = custInterface.postCustomer(customer);
//                        customer1.enqueue(new Callback<Customer>() {
//                    @Override
//                    public void onResponse(Call<Customer> call, Response<Customer> response) {
//                        System.out.println(response.body()+"**************************************************");
//                    }
//
//                    @Override
//                    public void onFailure(Call<Customer> call, Throwable t) {
//                        System.out.println("ERROR____________ERRR________ERRR_____!!!!!__"+t);
//                    }
//                });

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

//    public void addBook(Customer customer){
//        Call<Customer> callBook = custInterface.add(customer);
//
//        callBook.enqueue(new Callback<Customer>(){
//            @Override
//            public void onResponse(Call<Customer> call, Response<Customer> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(BookActivity.this, "Customer created successfully!", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Customer> call, Throwable t) {
//                Toast.makeText(BookActivity.this, "Customer has not save!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//

   public void saveCustomer (final Customer customer) {
       System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>CUSTOMER!!!"+customer);
       Call<Customer> customer1 = custInterface.postCustomer(customer);
       customer1.enqueue(new Callback<Customer>() {
           @Override
           public void onResponse(Call<Customer> call, Response<Customer> response) {
               System.out.println(response.body()+"**************************************************");
           }

           @Override
           public void onFailure(Call<Customer> call, Throwable t) {
               System.out.println("ERROR____________ERRR________ERRR_____!!!!!__"+t);
           }
       });
   }



    public void updateCustomer (final long id, final Customer customer){
        System.out.println("!!!! Update Customer method was called. If here is" +id+" and customer is"+ customer+". Attention!");
        Call<Customer> callBook = custInterface.updateCustomer(id, customer);
        callBook.enqueue(new Callback<Customer>(){
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CustActivity.this, "Customer updated succesfully! " + customer.getCustId(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                Toast.makeText(CustActivity.this, "Customer update was unsuccesful!", Toast.LENGTH_SHORT).show();
            }
        });
    }
//
//    public void deleteBook(final int id){
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
//    }
}

//Home task
// Сделать пут и делейт с возвратом в мейн
// Вход на второй экран из ресайкл вью
