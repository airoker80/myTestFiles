package com.exampleloksewar.loksewa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by airoker80 on 11/16/2016.
 */
public class Administration extends AppCompatActivity{
    ListView listView1;
    public static final String URL_GET_Practice = "http://192.168.100.3/loksewa/SachibQA.php";
    ArrayList<HashMap<String, String>> list1 = new ArrayList<HashMap<String, String>>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice);
        listView1=(ListView)findViewById(R.id.practice_list_item);
        //   listView1.setOnItemClickListener(this);
        showPosts();
    }
    private void showPosts() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_Practice,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(Administration.this, response, Toast.LENGTH_LONG).show();
                            //op(response);
                        } else {
                            Toast.makeText(Administration.this, response, Toast.LENGTH_LONG).show();
                            op1(response);
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Administration
                                .this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        )


        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();
                //   map.put(KEY_USERNAME, Config.username);
                // map.put(key_route, route);
                return map;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void op1(String response) {

        try {
            JSONObject obj1 = new JSONObject(response);
            JSONArray result = obj1.getJSONArray(Config.TAG_JSON_ARRAY1);


            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id =jo.getString("id");
                String ques ="Question :" + jo.getString("Question");
                String ans ="Answer :" + jo.getString("Answer");
                HashMap<String, String> Books = new HashMap<>();
                Books.put(Config.TAG_ID, id);
                Books.put(Config.TAG_Route, ques);
                Books.put(Config.TAG_filename,ans);
                list1.add(Books);
            }
            ListAdapter adapter = new SimpleAdapter(
                    Administration.this, list1, R.layout.sachibqa,
                    new String[]{Config.TAG_ID, Config.TAG_Route,Config.TAG_filename},
                    new int[]{R.id.listt3, R.id.listt4 ,R.id.listt5});

            listView1.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
