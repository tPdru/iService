package br.com.etecia.iservice;

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
    public void creatPerfil (ObjPerfil perfil) {

        //Conexão entre o Android e o PHP através do Hash.
        HashMap<String, String> parametro = new HashMap<>();
        parametro.put("name", perfil.getNome());
        parametro.put("email", perfil.getEmail());
        parametro.put("login", perfil.getUsuario());
        parametro.put("senha", perfil.getSenha());
        if ( !perfil.getCelular().isEmpty() ) {
            parametro.put("cel", perfil.getCelular());
        }

    }

}
