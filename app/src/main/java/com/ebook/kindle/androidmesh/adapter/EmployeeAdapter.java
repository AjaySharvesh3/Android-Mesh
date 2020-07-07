package com.ebook.kindle.androidmesh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ebook.kindle.androidmesh.R;
import com.ebook.kindle.androidmesh.model.EmployeeModel;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<EmployeeModel> employeeModelArrayList;

    public EmployeeAdapter(Context ctx, ArrayList<EmployeeModel> employeeModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.employeeModelArrayList = employeeModelArrayList;
    }

    @Override
    public EmployeeAdapter.EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_one, parent, false);
        EmployeeViewHolder holder = new EmployeeViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(EmployeeAdapter.EmployeeViewHolder holder, int position) {

        /*Picasso.get()
                .load(employeeModelArrayList.get(position).getProfile_picture())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.profile);*/
        holder.name.setText(employeeModelArrayList.get(position).getEmployee_name());
        holder.salary.setText(employeeModelArrayList.get(position).getEmployee_salary());
        holder.age.setText(employeeModelArrayList.get(position).getEmployee_age());
    }

    @Override
    public int getItemCount() {
        return employeeModelArrayList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView name, salary, age;
        ImageView profile;

        public EmployeeViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.employee_name_tv);
            salary = itemView.findViewById(R.id.employee_salary_tv);
            age = itemView.findViewById(R.id.employee_age_tv);
            /*profile = itemView.findViewById(R.id.profile_picture);*/
        }


    }

}
