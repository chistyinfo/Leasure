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

import greendustbd.leasure.BookVolley.Book;
import greendustbd.leasure.BookVolley.CustomBookListAdapter;

public class BookActivity extends AppCompatActivity {
    // Log tag
    private static final String TAG = MovieActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "http://greendust.netau.net/leasure/books.json";
    private List<Book> bookList = new ArrayList<Book>();
    private ListView blistView;
    String[] DetailsArray;
    private Context con;
    private CustomBookListAdapter badapter;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        con=this;

        NativeExpressAdView adView = (NativeExpressAdView)findViewById(R.id.adViewb);
        adView.loadAd(new AdRequest.Builder().build());

        blistView = (ListView) findViewById(greendustbd.leasure.R.id.book_list);
        badapter = new CustomBookListAdapter(this, bookList);
        blistView.setAdapter(badapter);
        blistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view,
                                                                    int position, long id) {
                                                BitmapDrawable bd = (BitmapDrawable) ((NetworkImageView) view.findViewById(R.id.thumbnail))
                                                        .getDrawable();
                                                Bitmap bitmap = bd.getBitmap();
                                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                                bd.getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
                                                byte[] imgByte = baos.toByteArray();


                                                Intent intent = new Intent(BookActivity.this, SingleBookActivity.class);
                                                intent.putExtra("Details", DetailsArray[position]);
                                                intent.putExtra("image", imgByte);
                                                BookActivity.this.startActivity(intent);


                                            }
                                         }
        );

        if (isNetworkAvailable()) {

            final BusyDialog busydialog=new BusyDialog(con,true,"Loading........");
            busydialog.show();


        // Creating volley request obj
    JsonArrayRequest bookReq = new JsonArrayRequest(url,
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
                            Book book = new Book();
                            book.setTitle(obj.getString("title"));
                            book.setThumbnailUrl(obj.getString("image"));
                            book.setPublisher(obj.getString("publisher"));
                            book.setWriter(obj.getString("writer"));
                            //url capturing form server
                            DetailsArray[i] = obj.getString("details");

                            // adding movie to movies array
                            bookList.add(book);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    // notifying list adapter about data changes
                    // so that it renders the list view with updated data
                    badapter.notifyDataSetChanged();
//
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
            AppController.getInstance().addToRequestQueue(bookReq);

        }else {

            webView= (WebView) findViewById(R.id.wvBk);
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

        Intent intent = new Intent(BookActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

}
