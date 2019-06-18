package com.tae.customerforheroku;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CustInterface {

    @GET("api/customer")
    Call<List<Customer>> getCustomers();

    @GET("api/customer/{id}")
    Call<Customer> getCustomer(@Path("id") long id);

    @POST("api/customer/post")
    Call<Customer> postCustomer (@Body Customer customer);

    @PUT("/api/books/{id}")
    Call<Customer> updateCustomer(@Path("id") long id, @Body Customer customer);

    @DELETE("/api/books/{id}")
    Call<Customer> deleteBook(@Path("id") long id);
}
