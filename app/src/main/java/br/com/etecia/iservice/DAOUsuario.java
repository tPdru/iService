package br.com.etecia.iservice;

import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;

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
        parametro.put("name", perfil.getNome());
        parametro.put("email", perfil.getEmail());
        parametro.put("login", perfil.getUsuario());
        parametro.put("senha", perfil.getSenha());
        if ( !perfil.getCelular().isEmpty() ) {
            parametro.put("cel", perfil.getCelular());
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

    public ObjPerfil readPerfil (Context context){
        ApiRequest.get(Api.URL_READ_USUARIO,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        ObjPerfil perfil = new ObjPerfil();
                        /**
                         * Ajustar esta parte de acordo com o Select

                        JSONObject obj = jsonArray.get(i)

                        perfil.setCodigo();
                         */

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
