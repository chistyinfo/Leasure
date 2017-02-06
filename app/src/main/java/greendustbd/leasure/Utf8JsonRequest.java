package greendustbd.leasure;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by chisty on 2/6/2017.
 */

public class Utf8JsonRequest extends JsonRequest<JSONObject> {
    public Utf8JsonRequest(String url, String requestBody, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, requestBody, listener, errorListener);
    }



    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String utf8String = new String(response.data, "UTF-8");
            return Response.success(new JSONObject(utf8String), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            // log error
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            // log error
            return Response.error(new ParseError(e));
        }
    }
}
