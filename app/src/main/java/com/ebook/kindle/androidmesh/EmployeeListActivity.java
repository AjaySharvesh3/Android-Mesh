package com.ebook.kindle.androidmesh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ebook.kindle.androidmesh.adapter.EmployeeAdapter;
import com.ebook.kindle.androidmesh.model.EmployeeModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class EmployeeListActivity extends AppCompatActivity {

    private String URL = "http://dummy.restapiexample.com/api/v1/employees";
    private static ProgressDialog mProgressDialog;
    ArrayList<EmployeeModel> employeeModelArrayList;
    private EmployeeAdapter employeeAdapter;
    private RecyclerView recyclerView;

    private FloatingActionButton addEmployeeFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Employees");
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler);
        fetchingJSON();

        addEmployeeFab = findViewById(R.id.add_employee_fab);
        addEmployeeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addEmployeeIntent = new Intent(EmployeeListActivity.this, AddEmployeeActivity.class);
                startActivity(addEmployeeIntent);
            }
        });
    }


    private void fetchingJSON() {
        showProgressDialog(this, "Loading...", "Fetching Data", false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            hideProgressDialog();

                            JSONObject obj = new JSONObject(response);
                            if (obj.optString("status").equals("success")) {

                                employeeModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("data");

                                Log.d("strrrrr", ">>" + dataArray);

                                for (int i = 0; i < dataArray.length(); i++) {

                                    EmployeeModel employeeModel = new EmployeeModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    employeeModel.setEmployee_name(dataobj.getString("employee_name"));
                                    employeeModel.setEmployee_salary("Salary: " + dataobj.getString("employee_salary"));
                                    employeeModel.setEmployee_age("Age: " + dataobj.getString("employee_age"));
                                    /*employeeModel.setProfile_picture(dataobj.getString("profile_picture"));*/

                                    employeeModelArrayList.add(employeeModel);

                                    Log.d("strrrrrMODEL DATA", ">>" + dataobj);
                                    Log.d("strrrrrMODEL DATA NAME", ">>" + dataobj.getString("employee_name"));

                                }

                                setupRecycler();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void setupRecycler() {
        employeeAdapter = new EmployeeAdapter(this, employeeModelArrayList);
        recyclerView.setAdapter(employeeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
    }

    public static void hideProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showProgressDialog(Context context, String title,
                                          String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
