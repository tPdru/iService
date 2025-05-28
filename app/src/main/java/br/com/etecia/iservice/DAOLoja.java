package br.com.etecia.iservice;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;

import java.util.HashMap;
import java.util.List;

public class DAOLoja {
    private List<ObjCardLoja> listaLoja;

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

    public List<ObjCardLoja> readLojas() {
        ApiRequest.get(Api.URL_READ_LOJA,
                response -> {

                },
                error -> {

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
