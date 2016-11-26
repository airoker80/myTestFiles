package com.exampleloksewar.loksewa;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by airoker80 on 11/11/2016.
 */
public class Books extends AppCompatActivity implements ListView.OnItemClickListener {
    public String book_url,filename;
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    public static final String URL_GET_seat = "http://192.168.100.3/loksewa/booksinfo.php";
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books);
        listView=(ListView)findViewById(R.id.list_item);
        listView.setOnItemClickListener(this);
        showBooks();
    }

    private void showBooks() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_seat,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            Toast.makeText(Books.this, response, Toast.LENGTH_LONG).show();
                            //op(response);
                        } else {
                           // Toast.makeText(Books.this, response, Toast.LENGTH_LONG).show();
                            op(response);
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Books
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

    private void op(String response) {

        try {
            JSONObject obj1 = new JSONObject(response);
            JSONArray result = obj1.getJSONArray(Config.TAG_JSON_ARRAY);


            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id ="ID :" + jo.getString("id");
                String books = "Books :"+jo.getString("books");
                String link=jo.getString("url");
                String file=jo.getString("filename");
                HashMap<String, String> Books = new HashMap<>();
                Books.put(Config.TAG_ID, id);
                Books.put(Config.TAG_Route, books);
                Books.put(Config.TAG_URL,link);
                Books.put(Config.TAG_filename,file);
                list.add(Books);
            }
            ListAdapter adapter = new SimpleAdapter(
                    Books.this, list, R.layout.list_items,
                    new String[]{Config.TAG_ID, Config.TAG_Route},
                    new int[]{R.id.listt1, R.id.listt2});

            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Intent intent= new Intent(this,DownloadFileFromURL.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        book_url = map.get(Config.TAG_URL).toString();
        filename=map.get(Config.TAG_filename).toString();
        File file = new File(Environment.getExternalStorageDirectory() + "/download/"+filename);
        if (file.exists()) {
           // startActivity(new Intent(this,Login.class));
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(path, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        if(!file.exists()){
            file_download(book_url);
        }

        //intent.putExtra(Config.EMP_ID,empId);
        //startActivity(intent);
    }
    public void file_download(String uRl) {
        try {
            File direct = new File(Environment.getExternalStorageDirectory()
                    + "/download");
//            File file = new File(Environment.getExternalStorageDirectory() + "/download/facebook.html");
                if (!direct.exists()) {
                    direct.mkdirs();
                }

                DownloadManager mgr = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);

                Uri downloadUri = Uri.parse(uRl);
                DownloadManager.Request request = new DownloadManager.Request(
                        downloadUri);

                request.setAllowedNetworkTypes(
                        DownloadManager.Request.NETWORK_WIFI
                                | DownloadManager.Request.NETWORK_MOBILE)
                        .setAllowedOverRoaming(false).setTitle("LoK Sewa")
                        .setDescription("pdf files")
                        .setDestinationInExternalPublicDir("/download",filename );

                mgr.enqueue(request);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
