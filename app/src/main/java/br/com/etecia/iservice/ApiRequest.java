package br.com.etecia.iservice;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class ApiRequest {

    //Fila de requisiçoes
    private static RequestQueue requestQueue;

    //O volley vai presisar de um contextoi para adiciona a fila
    public void init(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public static void post(String url, Map<String, String> parans,
                            Response.Listener<String> listener,
                            Response.ErrorListener errorListener) {

        final Map<String, String> finalParams = parans;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return finalParams;  // Aqui você envia os parâmetros!
            }
        };
        requestQueue.add(stringRequest);
    }

    public static void get(String url,
                           Response.Listener<String> listener,
                           Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);
        requestQueue.add(stringRequest);
    }


}
