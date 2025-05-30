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
    private List<ObjCardLoja> listaLoja = new ArrayList<>();

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

    public List<ObjCardLoja> readLojas(Context context) {
        ApiRequest.get(Api.URL_READ_LOJA,
                response -> {
                    try {
                        //Lista json que tras as informaçoes
                        JSONArray jsonLista = new JSONArray(response);
                        //Objeto loja para preencher com o construtor vazio
                        ObjCardLoja loja = new ObjCardLoja();

                        for (int i = 0; i < jsonLista.length(); i++) {
                            //Transformando o json em um objeto
                            JSONObject obj = jsonLista.getJSONObject(i);

                            //Criando as lojas e adicionando a lista
                            loja.setNomeLoja(obj.getString("nome_loja"));
                            loja.setDescricao(obj.getString("desc_loja"));
                            loja.setEmailDono(obj.getString("email_loja"));
                            loja.setImgLoja(R.drawable.foto_imagem);
                            loja.setCodigLoja(obj.getInt("cod_loja"));
                            loja.setCodUsuario(obj.getInt("cod_usua"));
                            listaLoja.add(loja);
                        }


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                },
                error -> {
                    Toast.makeText(context, "Erro em lojas", Toast.LENGTH_SHORT).show();


                });


        return listaLoja;
    }





    /*Função de identificação de erros
    private void erros() {
        if (error instanceof NetworkError) {
            // Erro de rede
        } else if (error instanceof ServerError) {
            // Erro no servidor
        } else if (error instanceof AuthFailureError) {
            // Falha de autenticação
        } else if (error instanceof ParseError) {
            // Erro ao processar resposta
        } else if (error instanceof TimeoutError) {
            // Timeout
        }
    }*/



}
