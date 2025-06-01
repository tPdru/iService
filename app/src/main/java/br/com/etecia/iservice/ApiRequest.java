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

    //O volley vai presisar de um contexto para adicionar a fila
    public static void init(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    //Post para criar
    public static void post(String url, Map<String, String> params,
                            Response.Listener<String> listener,
                            Response.ErrorListener errorListener) {

        final Map<String, String> finalParams = params;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return finalParams;  // Aqui você envia os parâmetros!
            }
        };
        requestQueue.add(stringRequest);
    }

    //Get para ler
    public static void get(String url,
                           Response.Listener<String> listener,
                           Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);
        requestQueue.add(stringRequest);
    }

    //Delete para deletar
    public static void delete(String url, int codigo, Response.Listener listener, Response.ErrorListener error) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + codigo, listener, error);
        requestQueue.add(stringRequest);

    }

}
