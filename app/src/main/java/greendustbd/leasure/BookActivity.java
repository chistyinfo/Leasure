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

import greendustbd.leasure.BookVolley.Book;
import greendustbd.leasure.BookVolley.CustomBookListAdapter;

public class BookActivity extends AppCompatActivity {
    // Log tag
    private static final String TAG = MovieActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "http://192.168.0.103/leasure/books.json";
    private ProgressDialog pDialog;
    private List<Book> bookList = new ArrayList<Book>();
    private ListView blistView;
    String[] DetailsArray;
    private Context con;
    private CustomBookListAdapter badapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
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


        // Creating volley request obj
    JsonArrayRequest bookReq = new JsonArrayRequest(url,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d(TAG, response.toString());



                    // Parsing json
                    DetailsArray = new String[response.length()];

                    for (int i = 0; i < response.length(); i++) {
                        try {

                            JSONObject obj = response.getJSONObject(i);
                            Book book = new Book();
                            book.setTitle(obj.getString("title"));
                            book.setThumbnailUrl(obj.getString("image"));
                            book.setDirector(obj.getString("director"));
                            book.setActor(obj.getString("actor"));
                            book.setGenres(obj.getString("genres"));
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

        }
    });
    AppController.getInstance().addToRequestQueue(bookReq);
}



}
