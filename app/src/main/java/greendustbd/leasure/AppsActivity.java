package greendustbd.leasure;

import android.app.ProgressDialog;
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

import greendustbd.leasure.AppsVolley.Apps;
import greendustbd.leasure.AppsVolley.CustomAppsListAdapter;

public class AppsActivity extends AppCompatActivity {
    // Log tag
    private static final String TAG = AppsActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "https://greendustbdplus.000webhostapp.com/Leasure/apps.json";
    private ProgressDialog pDialog;
    private List<Apps> appsList = new ArrayList<Apps>();
    private ListView alistView;
    String[] DetailsArray;
    String[] urlStrArray;
    private Context con;
    private WebView webView;
    private CustomAppsListAdapter aadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
        con=this;

        NativeExpressAdView adView = (NativeExpressAdView)findViewById(R.id.adViewa);
        adView.loadAd(new AdRequest.Builder().build());

        alistView = (ListView) findViewById(R.id.apps_list);
        aadapter = new CustomAppsListAdapter(this, appsList);
        alistView.setAdapter(aadapter);
        alistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view,
                                                                    int position, long id) {
                                                BitmapDrawable bd = (BitmapDrawable) ((NetworkImageView) view.findViewById(R.id.thumbnail))
                                                        .getDrawable();
                                                Bitmap bitmap = bd.getBitmap();
                                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                                bd.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
                                                byte[] imgByte = baos.toByteArray();


                                                Intent intent = new Intent(AppsActivity.this, SingleAppsActivity.class);
                                                intent.putExtra("Details", DetailsArray[position]);
                                                intent.putExtra("url", urlStrArray[position]);
                                                intent.putExtra("image", imgByte);
                                                AppsActivity.this.startActivity(intent);


                                            }
                                        }
        );

        if (isNetworkAvailable()) {

            final BusyDialog busydialog=new BusyDialog(con,true,"Loading........");
            busydialog.show();


            // Creating volley request obj
            JsonArrayRequest appsReq = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());

                            busydialog.dismis();



                            // Parsing details
                            DetailsArray = new String[response.length()];
                            // Parsing url
                            urlStrArray = new String[response.length()];


                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    Apps apps = new Apps();
                                    apps.setTitle(obj.getString("title"));
                                    apps.setThumbnailUrl(obj.getString("image"));
                                    apps.setPublisher(obj.getString("publisher"));
                                    //details capturing form server
                                    DetailsArray[i] = obj.getString("details");
                                    //url capturing form server
                                    urlStrArray[i] = obj.getString("url");

                                    // adding movie to movies array
                                    appsList.add(apps);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            aadapter.notifyDataSetChanged();
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
            AppController.getInstance().addToRequestQueue(appsReq);

        }else {

            webView= (WebView) findViewById(R.id.wvAP);
            webView.loadUrl("file:///android_asset/notification.png");

        }
        }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    }


