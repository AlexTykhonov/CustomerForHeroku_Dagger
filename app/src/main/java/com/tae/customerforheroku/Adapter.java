package com.tae.customerforheroku;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Customer> customers;
    Context context;

    Adapter(Context context, List<Customer> customers) {
        this.customers = customers;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.listcustomer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        final Customer customer = customers.get(position);

        holder.firstname.setText(customer.getFirstname());
        holder.secondname.setText(customer.getLastname());
        holder.customerid.setText(customer.getCustId().toString());
        holder.age.setText(customer.getAge().toString());
        holder.linearrecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CustActivity.class);
                intent.putExtra("id", customer.getCustId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView customerid,firstname, secondname, age;
        final LinearLayout linearrecycler;
        ViewHolder(View view){
            super(view);
            firstname = (TextView) view.findViewById(R.id.firstname);
            secondname = (TextView) view.findViewById(R.id.secondname);
            customerid = (TextView) view.findViewById(R.id.customerid);
            age = (TextView) view.findViewById(R.id.age);
            linearrecycler = (LinearLayout)view.findViewById(R.id.linearrecycler);
        }
    }

    public void setList (List<Customer> list) {
        this.customers = list;
        notifyDataSetChanged();

    }
}

