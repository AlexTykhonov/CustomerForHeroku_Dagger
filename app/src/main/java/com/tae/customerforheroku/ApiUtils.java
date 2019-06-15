package com.tae.customerforheroku;

public class ApiUtils {

    public static final String API_URL = "https://fluxcustomer.herokuapp.com/";

    public ApiUtils() {
    }

    public static CustInterface getCustInterface(){
        return RetrofitClient.getCustomer(API_URL).create(CustInterface.class);
    }

}