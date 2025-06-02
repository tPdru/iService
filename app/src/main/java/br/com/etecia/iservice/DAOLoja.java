package br.com.etecia.iservice;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAOLoja {

    //Interface
    InRespostaPerfil mandarListaLojas;


    public void createLoja(ObjCardLoja loja) {

        //Criando e preenchendo o hashMap com as informaçoes da nonova loja
        HashMap<String, String> parametro = new HashMap<>();

        parametro.put("nome", loja.getNomeLoja());
        parametro.put("descricao", loja.getDescricao());
        parametro.put("nota", String.valueOf(loja.getTxtNota()));

        //Requisição
        ApiRequest.post(Api.URL_CREATE_LOJA, parametro,
                response -> {

                },
                error -> {

                });

    }

    public void readLojas(Context context, InRespostaPerfil mandarListaLojas) {
        ApiRequest.get(Api.URL_READ_LOJA,
                response -> {
                    try {

                        List<ObjCardLoja> listaLoja = new ArrayList<>();

                        //Objeto json que tras as informaçoes
                        JSONObject jsonObject = new JSONObject(response);

                        //Colocando o objto Json em um Array Json
                        JSONArray jsonArray = jsonObject.getJSONArray("lojas");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            //Objeto loja para preencher com o construtor vazio
                            ObjCardLoja loja = new ObjCardLoja();
                            //Pegando o objeto json dentro da lista
                            JSONObject obj = jsonArray.getJSONObject(i);

                            //Passando os dados do objeto json para o loja
                            loja.setNomeLoja(obj.getString("nome_loja"));
                            loja.setDescricao(obj.getString("desc_loja"));
                            loja.setEmailDono(obj.getString("email_loja"));
                            loja.setImgLoja(R.drawable.foto_imagem);
                            loja.setCodigLoja(obj.getInt("cod_loja"));
                            loja.setCodUsuario(obj.getInt("cod_usua"));

                            loja.setTemServicos(false);

                            listaLoja.add(loja);
                        }
                        mandarListaLojas.listaReadLoja(new ArrayList<>(listaLoja));

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                },
                error -> {
                    Toast.makeText(context, "Erro em lojas", Toast.LENGTH_SHORT).show();
                });

    }

}
