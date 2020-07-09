package com.ebook.kindle.androidmesh;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ebook.kindle.androidmesh.adapter.PlayerListAdapter;
import com.ebook.kindle.androidmesh.model.PlayerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PlayerListActivity extends AppCompatActivity {

    //Created entities as String URL, ProgressDialog, ListView, ArrayList<PlayerModel> and PlayerListAdapter attributes
    private String URL = "https://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php";
    private static ProgressDialog mProgressDialog;
    private ListView listView;
    ArrayList<PlayerModel> playerModelArrayList;
    private PlayerListAdapter playerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Players List");
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Identify ListView as findViewById
        listView = findViewById(R.id.player_lv);

        //Call retrieveJSON() method
        retrieveJSON();
    }

    //Created method called retrieveJSON() method
    private void retrieveJSON() {

        showProgressDialog(this, "Loading...","Fetching Json",false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("status").equals("true")){

                                playerModelArrayList = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    PlayerModel playerModel = new PlayerModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel.setName(dataobj.getString("name"));
                                    playerModel.setCountry(dataobj.getString("country"));
                                    playerModel.setCity(dataobj.getString("city"));
                                    playerModel.setImageURL(dataobj.getString("imgURL"));

                                    playerModelArrayList.add(playerModel);

                                }

                                setupListview();

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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    //Created method called setupListView() method
    private void setupListview(){
        removeProgressDialog();  //will remove progress dialog
        playerListAdapter = new PlayerListAdapter(this, playerModelArrayList);
        listView.setAdapter(playerListAdapter);
    }

    //Created method called showProgressDialog() method
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


    //Created method called removeProgressDialog() method
    public static void removeProgressDialog() {
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

}
