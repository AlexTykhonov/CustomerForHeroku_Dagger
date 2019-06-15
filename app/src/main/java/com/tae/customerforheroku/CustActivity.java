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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        newid = findViewById(R.id.newid);
        newname = findViewById(R.id.newname);
        newsurname = findViewById(R.id.newsurname);
        newage = findViewById(R.id.newage);

        buttonDelete = findViewById(R.id.buttonDelete);
        buttonSave = findViewById(R.id.buttonSave);

        custInterface = ApiUtils.getCustInterface();
        final Bundle extras = getIntent().getExtras();

        final String custid = String.valueOf(extras.getInt("custid"));
        String firstname = extras.getString("firstname");
        String lastame = extras.getString("lastame");
        String age = extras.getString("age");

//        editFormId.setText(bookId);
//        editFormTitle.setText(title);
//        editFormAuthor.setText(author);
//        editFormDescription.setText(description);
//        editFormPublishDate.setText(published + "");

//        if(custid!=null && custid.trim().length()>0){
//            editFormId.setFocusable(false);
//        } else{
          //  buttonDelete.setVisibility(View.INVISIBLE);
            //editFormId.setVisibility(View.INVISIBLE);
        //}

        buttonSave = findViewById(R.id.buttonSave);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer customer = new Customer();
                customer.setCustId(newid.getId());
                customer.setFirstname(newname.getText().toString());
                customer.setLastname(newsurname.getText().toString());
                int age =Integer.valueOf(newage.getText().toString());
                customer.setAge(age);
//
//                if(custid!=null && custid.trim().length()>0){
//                    updateBook(Integer.parseInt(bookId), customer);
//                } else{
//                    addBook(customer);
//                }
                System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>CUSTOMER!!!"+customer);;
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
//    public void updateBook (final int id, final Customer customer){
//        Call<Customer> callBook = bookInterface.updateBook(id, customer);
//        callBook.enqueue(new Callback<Customer>(){
//            @Override
//            public void onResponse(Call<Customer> call, Response<Customer> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(BookActivity.this, "Customer updated succesful! " + customer.getId(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Customer> call, Throwable t) {
//                Toast.makeText(BookActivity.this, "Customer update was unsuccesful!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
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
