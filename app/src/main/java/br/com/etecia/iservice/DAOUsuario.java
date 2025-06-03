package br.com.etecia.iservice;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DAOUsuario{

    List<ObjPerfil> listaPerfis;
    InRespostaPerfil mandarLista;
    /**
     * CRUD do usario
     */

    //Metodo converter byte[] em Base64
    private String byteArrayToBase64 (byte[] imageBytes){
        return android.util.Base64.encodeToString(imageBytes, Base64.NO_WRAP);
    }
    //Create
    public void creatPerfil(ObjPerfil perfil, Context context, byte[] imageBytes) {

        //Conexão entre o Android e o PHP através do Hash.
        //passando os dados do objeto para um HashMap
        HashMap<String, String> parametro = new HashMap<>();
        parametro.put("nome_usua", perfil.getNome());
        parametro.put("email_usua", perfil.getEmail());
        parametro.put("login_usua", perfil.getUsuario());
        parametro.put("senha_usua", perfil.getSenha());

        if (imageBytes != null){
            parametro.put("imagem_usuario", byteArrayToBase64(imageBytes));
        }else {
            parametro.put("imagem_usuario", "");
        }

        //colocando informaçoes para ajustes
        parametro.put("end_usua", "Rua teste");
        parametro.put("profis_usua", "Barbeiro");
        parametro.put("tel_usua", "1122221111");
        parametro.put("cpf_usua", "123456789");
        parametro.put("sexo_usua", "M");
        parametro.put("cel_usua", "11944443333");

        /** if ( !perfil.getCelular().isEmpty() ) {
         parametro.put("cel_usua", perfil.getCelular());
         }else {
         parametro.put("cel_usua", "11944443333");
         }*/

        ApiRequest.post(Api.URL_CREATE_USUARIO, parametro,
                //Resposta caso seja sucesso
                response -> {
                    Toast.makeText(context, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show();
                },
                //Resposta caso seja erro
                error -> {
                    Toast.makeText(context, "Erro de conexão.", Toast.LENGTH_SHORT).show();
                    //vendo o erro no log
                    if (error.networkResponse != null) {
                        Log.e("API", "Status Code: " + error.networkResponse.statusCode);
                        Log.e("API", "Data: " + new String(error.networkResponse.data));
                    }
                });

    }

    // Pasando a interface como parametro
    public void readPerfil(Context context, InRespostaPerfil mandarLista) {
        ApiRequest.get(Api.URL_READ_USUARIO,
                response -> {
                    Toast.makeText(context, "passou aqui", Toast.LENGTH_SHORT).show();
                    try {
                        /// Criaar o objeto json antes de transformar em array
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("usuarios");

                        listaPerfis = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            //pegar o objeto json dentro do jsonArray
                            JSONObject obj = jsonArray.getJSONObject(i);
                            //Criar um novo objeto vazio
                            ObjPerfil perfil = new ObjPerfil();
                            //Setar s informaçoes no0 perfil vazio
                            perfil.setCodigo(obj.getInt("cod_usua"));
                            perfil.setUsuario(obj.getString("login_usua"));
                            perfil.setNome(obj.getString("nome_usua"));
                            perfil.setEmail(obj.getString("email_usua"));
                            perfil.setSenha(obj.getString("senha_usua"));
                            perfil.setCelular(obj.getString("cel_usua"));
                            //adicionar o perfil na lista
                            listaPerfis.add(perfil);
                        }
                        //mandar a lista completata atravez da interface
                        mandarLista.listaReadPerfil(new ArrayList<>(listaPerfis));
                        Toast.makeText(context, listaPerfis.get(0).getNome(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                    Toast.makeText(context, "Erro no servidor!", Toast.LENGTH_SHORT).show();
                });
    }


    /// Deletar perfil--------------------------------------------------------
    public void deletePerfil(int cod, Context context) {
        ApiRequest.delete(Api.URL_DELETE_USUARIO, cod, response -> {
                    Toast.makeText(context, "Sua conta foi finalizada!", Toast.LENGTH_SHORT).show();
                },
                error -> {
                    Toast.makeText(context, "Erro no servidor!", Toast.LENGTH_SHORT).show();
                });

    }

    // Método para atualizar um perfil existente
    public void updatePerfil(ObjPerfil perfil, Context context) {
        // Criando um HashMap para enviar os dados via POST
        HashMap<String, String> parametro = new HashMap<>();

        // Passando todos os dados necessários
        parametro.put("cod_usua", String.valueOf(perfil.getCodigo())); // ID do usuário a ser atualizado
        parametro.put("nome_usua", perfil.getNome());
        parametro.put("email_usua", perfil.getEmail());
        parametro.put("login_usua", perfil.getUsuario());
        parametro.put("senha_usua", perfil.getSenha());

        // Colocando informações adicionais - você pode ajustar conforme seu app
        parametro.put("end_usua", "Rua teste");
        parametro.put("profis_usua", "Barbeiro");
        parametro.put("tel_usua", "1122221111");
        parametro.put("cpf_usua", "123456789");
        parametro.put("sexo_usua", "M");
        parametro.put("cel_usua", perfil.getCelular());

        // Fazendo a requisição POST para atualizar o usuário
        ApiRequest.post(Api.URL_UPDATE_USUARIO, parametro,
                // Callback de sucesso
                response -> {
                    Toast.makeText(context, "Perfil atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                },
                // Callback de erro
                error -> {
                    Toast.makeText(context, "Erro ao atualizar o perfil.", Toast.LENGTH_SHORT).show();
                    if (error.networkResponse != null) {
                        Log.e("API", "Status Code: " + error.networkResponse.statusCode);
                        Log.e("API", "Data: " + new String(error.networkResponse.data));
                    }
                });
    }


}
