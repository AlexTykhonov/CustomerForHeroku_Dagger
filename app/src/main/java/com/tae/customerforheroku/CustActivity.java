package com.tae.customerforheroku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

        String firstname, lastname;
        Integer age;

        Long custid = 0L;
        final Bundle extras = getIntent().getExtras();

        if (extras != null) {
           custid = extras.getLong("id");
            System.out.println(")))))))))))))))))))))) THIS IS CUSTOMER ID WE GOT FROM EXTRAS )))) "+ custid+" !!!!!!!!!!!!!!!$$$$$$$$");
           firstname = extras.getString("firstname");
           lastname = extras.getString("lastname");
            age = extras.getInt("age");
            newage.setText(age.toString());
            newname.setText(firstname);
            newsurname.setText(lastname);
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
                //String userId = String.valueOf(extras.get("id"));
               deleteCustomer(finalCustid);
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





// update the customer

    public void updateCustomer (final long id, final Customer customer){
        System.out.println("_________________________________________ CUSTOMER" + customer);
        custInterface.updateCustomer(id, customer)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResultsU, this::handleErrorU);
        System.out.println("!!!! Update Customer method was called. If here is" +id+" and customer is"+ customer+". Attention!");
    }

    void handleResultsU (Customer updatedCustomer)
    {
        if (updatedCustomer != null) {
            System.out.println("&&*^&*^&*^*^^*&^*^*&^*&^^&*^**&^^&* THIS IS UDATED CUSTOMER  ---> " + updatedCustomer);
        } else {
            Toast.makeText(this, "NO UPDATED CUSTOMERS", Toast.LENGTH_LONG).show();
        }
    }
    void handleErrorU (Throwable t){
        System.out.println(t + "ERROR WHILE UPDATE");
    }



// delete Customer
    public void deleteCustomer(final long id){
        System.out.println(" DELETE METHOD CALLED!!!!!");
         custInterface.deleteCustomer(id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResultsD, this::handleErrorD);
        System.out.println("!!!! Delete Customer method was called. Customer   " +id+" to be deleted!!!!");
    }

    void handleResultsD (Customer updatedCustomer)
    {
        if (updatedCustomer != null) {
            System.out.println("&&*^&*^&*^*^^*&^*^*&^*&^^&*^**&^^&* THIS IS DELETED CUSTOMER  ---> " + updatedCustomer);
        } else {
            Toast.makeText(this, "NO UPDATED CUSTOMERS", Toast.LENGTH_LONG).show();
        }
    }
    void handleErrorD (Throwable t){
        System.out.println(t + "ERROR WHILE Delete");
    }

}
