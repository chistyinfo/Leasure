package greendustbd.leasure;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import greendustbd.leasure.DramaVolley.CustomDramaListAdapter;
import greendustbd.leasure.DramaVolley.Drama;

public class DramaActivity extends AppCompatActivity {
    // Log tag
    private static final String TAG = MovieActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "http://192.168.0.103/leasure/dramas.json";
    private ProgressDialog pDialog;
    private List<Drama> dramaList = new ArrayList<Drama>();
    private ListView dlistView;
    String[] DetailsArray;
    private Context con;
    private CustomDramaListAdapter dadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drama);

        dlistView = (ListView) findViewById(greendustbd.leasure.R.id.drama_list);
        dadapter = new CustomDramaListAdapter(this, dramaList);
        dlistView.setAdapter(dadapter);
        dlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view,
                                                                    int position, long id) {
                                                BitmapDrawable bd = (BitmapDrawable) ((NetworkImageView) view.findViewById(R.id.thumbnail))
                                                        .getDrawable();
                                                Bitmap bitmap = bd.getBitmap();
                                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                                bd.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
                                                byte[] imgByte = baos.toByteArray();


                                                Intent intent = new Intent(DramaActivity.this, SingleDramaActivity.class);
                                                intent.putExtra("Details", DetailsArray[position]);
                                                intent.putExtra("image", imgByte);
                                                DramaActivity.this.startActivity(intent);


                                            }
                                        }
        );




        // Creating volley request obj
        JsonArrayRequest dramaReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());



                        // Parsing json
                        DetailsArray = new String[response.length()];

                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Drama drama = new Drama();
                                drama.setTitle(obj.getString("title"));
                                drama.setThumbnailUrl(obj.getString("image"));
                                drama.setDirector(obj.getString("director"));
                                drama.setStars(obj.getString("stars"));

                                //url capturing form server
                                DetailsArray[i] = obj.getString("details");

                                // adding movie to movies array
                                dramaList.add(drama);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        dadapter.notifyDataSetChanged();
//
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(dramaReq);
    }



}
