package com.ebook.kindle.androidmesh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class AddEmployeeActivity extends AppCompatActivity {

    private String URL = "http://dummy.restapiexample.com/api/v1/create";
    private Button addEmployeeButton;
    private EditText employeeNameEt;
    private EditText employeeSalaryEt;
    private EditText employeeAgeEt;
    private TextView employeeNameTv;
    private TextView employeeSalaryTv;
    private TextView employeeAgeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add Employee");
        actionBar.setDisplayHomeAsUpEnabled(true);

        employeeNameEt = findViewById(R.id.employee_name_et);
        employeeSalaryEt = findViewById(R.id.employee_salary_et);
        employeeAgeEt = findViewById(R.id.employee_age_et);

        employeeNameTv = findViewById(R.id.employee_name_tv);
        employeeSalaryTv = findViewById(R.id.employee_salary_tv);
        employeeAgeTv = findViewById(R.id.employee_age_tv);

        addEmployeeButton = findViewById(R.id.add_employee_button);

        addEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postEmployee();
            }
        });

    }


    public void postEmployee() {
            final ProgressDialog loading = new ProgressDialog(AddEmployeeActivity.this);
            loading.setMessage("Adding New Employee...");
            loading.setCanceledOnTouchOutside(false);
            loading.show();

            JSONObject object = new JSONObject();
            try {
                //input your API parameters
                object.put("name", employeeNameEt.getText());
                object.put("salary", employeeSalaryEt.getText());
                object.put("age", employeeAgeEt.getText());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Enter the correct url for your api service site
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, object,
                    new Response.Listener<JSONObject>() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onResponse(JSONObject response) {

                            Toast.makeText(AddEmployeeActivity.this,"String Response : "+ response.toString(),Toast.LENGTH_LONG).show();

                            try {
                                    loading.dismiss();
                                    String status = response.getString("status");

                                    JSONObject body = response.getJSONObject("data");
                                    Toast.makeText(AddEmployeeActivity.this,"String Response : "+ body.toString(),Toast.LENGTH_LONG).show();

                                    employeeNameTv.setText(body.getString("name"));
                                    employeeSalaryTv.setText("Salary " + body.getString("salary"));
                                    employeeAgeTv.setText("Age " + body.getString("age"));


                            } catch (JSONException e) {
                                e.printStackTrace();
                                loading.dismiss();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    loading.dismiss();
                    VolleyLog.d("Error", "Error: " + error.getMessage());
                    Toast.makeText(AddEmployeeActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(jsonObjectRequest);


        /*StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        Toast.makeText(AddEmployeeActivity.this, "Added Successfully... " + response , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                        Toast.makeText(AddEmployeeActivity.this, "Failed To Add... " + error.getMessage() , Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("name", "Ajay");
                params.put("salary", "100000");
                params.put("age", "20");

                return params;
            }
        };

        requestQueue.add(postRequest);*/
    }
}