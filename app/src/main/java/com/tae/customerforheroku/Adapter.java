package com.tae.customerforheroku;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Customer> customers;

    Adapter(Context context, List<Customer> customers) {
        this.customers = customers;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.listcustomer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        Customer customer = customers.get(position);

        holder.firstname.setText(customer.getFirstname());
        holder.secondname.setText(customer.getLastname());
        holder.customerid.setText(customer.getCustId().toString());
        holder.age.setText(customer.getAge().toString());
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView customerid,firstname, secondname, age;
        ViewHolder(View view){
            super(view);
            firstname = (TextView) view.findViewById(R.id.firstname);
            secondname = (TextView) view.findViewById(R.id.secondname);
            customerid = (TextView) view.findViewById(R.id.customerid);
            age = (TextView) view.findViewById(R.id.age);
        }
    }

    public void setList (List<Customer> list) {
        this.customers = list;
        notifyDataSetChanged();

    }
}

