package com.tae.customerforheroku;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CustInterface {

    @GET("api/customer")
    Observable<List<Customer>> getCustomers();

    @GET("api/customer/{id}")
    Observable<Customer> getCustomer(@Path("id") long id);

    @POST("api/customer/post")
    Observable<Customer> postCustomer (@Body Customer customer);

    @PUT("api/customer/put/{id}")
    Observable<Customer> updateCustomer(@Path("id") long id, @Body Customer customer);

    @DELETE("api/customer/delete/{id}")
    Observable<Customer> deleteCustomer(@Path("id") long id);
}
