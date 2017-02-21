package greendustbd.leasure;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aapbd.appbajarlib.notification.BusyDialog;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import greendustbd.leasure.MovieVolley.CustomListAdapter;
import greendustbd.leasure.MovieVolley.Movie;

public class MovieActivity extends AppCompatActivity {

    // Log tag
    private static final String TAG = MovieActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "http://192.168.0.100/leasure/movies.json";
    private List<Movie> movieList = new ArrayList<Movie>();
    private ListView listView;
    String[] DetailsArray;
    private Context con;
    private CustomListAdapter adapter;
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_movie);
        con=this;

        NativeExpressAdView adView = (NativeExpressAdView)findViewById(R.id.adViewm);
        adView.loadAd(new AdRequest.Builder().build());

        listView = (ListView) findViewById(greendustbd.leasure.R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view,
                                                                    int position, long id) {
                                                BitmapDrawable bd = (BitmapDrawable) ((NetworkImageView) view.findViewById(R.id.thumbnail))
                                                        .getDrawable();
                                                Bitmap bitmap = bd.getBitmap();
                                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                                bd.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
                                                byte[] imgByte = baos.toByteArray();


                                                Intent intent = new Intent(MovieActivity.this, SingleMovieActivity.class);
                                                intent.putExtra("Details", DetailsArray[position]);
                                                intent.putExtra("image", imgByte);
                                                MovieActivity.this.startActivity(intent);


                                            }
                                        }
        );


        if (isNetworkAvailable()) {

            final BusyDialog busydialog=new BusyDialog(con,true,"Loading........");
            busydialog.show();


        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());

                        busydialog.dismis();


                        // Parsing json
                        DetailsArray = new String[response.length()];


                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Movie movie = new Movie();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setDirector(obj.getString("director"));
                                movie.setActor(obj.getString("actor"));
                                movie.setGenres(obj.getString("genres"));
                                //url capturing form server
                                DetailsArray[i] = obj.getString("details");

                                // adding movie to movies array
                                movieList.add(movie);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

                if (busydialog != null) {

                    busydialog.dismis();

                }


            }
        });
        AppController.getInstance().addToRequestQueue(movieReq);

        }else {

            webView= (WebView) findViewById(R.id.wvMv);
            webView.loadUrl("file:///android_asset/notification.png");

        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void onBackPressed() {

        Intent intent = new Intent(MovieActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }


    }


