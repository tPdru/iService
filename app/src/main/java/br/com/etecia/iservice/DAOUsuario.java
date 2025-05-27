package br.com.etecia.iservice;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DAOUsuario {

    //Definindo o tipo da conexão
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    /**
     * CRUD do usario
     */

    //Create
    public void creatPerfil (ObjPerfil perfil, Context context) {

        //Conexão entre o Android e o PHP através do Hash.
        //passando os dados do objeto para um HashMap
        HashMap<String, String> parametro = new HashMap<>();
        parametro.put("nome_usua", perfil.getNome());
        parametro.put("email_usua", perfil.getEmail());
        parametro.put("login_usua", perfil.getUsuario());
        parametro.put("senha_usua", perfil.getSenha());
        Toast.makeText(context, "Passei  !", Toast.LENGTH_SHORT).show();
        if ( !perfil.getCelular().isEmpty() ) {
            parametro.put("cel_usua", perfil.getCelular());
        }

        ApiRequest.post(Api.URL_CREATE_USUARIO, parametro,
                //Resposta caso seja sucesso
                response -> {
                    Toast.makeText(context, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show();
        },
                //Resposta caso seja erro
                error -> {
                    Toast.makeText(context, "Erro de conexão.", Toast.LENGTH_SHORT).show();
                });

    }



    public List<ObjPerfil> readPerfil (Context context){
        ApiRequest.get(Api.URL_READ_USUARIO,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        ObjPerfil perfil = new ObjPerfil();
                        List<ObjPerfil> lista = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            perfil.setCodigo(obj.getInt("cod_usua"));
                            perfil.setUsuario(obj.getString("login_usua"));
                            perfil.setNome(obj.getString("nome_usua"));
                            perfil.setEmail(obj.getString("email_usua"));
                            perfil.setSenha(obj.getString("senha_usua"));
                            perfil.setCelular(obj.getString("cel_usua"));
                            lista.add(perfil);
                        }

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                },
                error -> {
                    Toast.makeText(context, "Erro no servidor!", Toast.LENGTH_SHORT).show();
                });

        return readPerfil(context);
    }

}
