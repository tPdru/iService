package br.com.etecia.iservice;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ControllerMaster {

    //Atribto estatico que armazena a unica intancia da classe.
    private static final ControllerMaster CONTROLLER_MASTER = new ControllerMaster();

    //Variáveis para controle de informação
    private static List<ObjPerfil> listaPerfis = new ArrayList<>();
    private List<ObjCardLoja> listaLojas = new ArrayList<>();
    private boolean loginOn;
    private int indexConta;
    private ObjPerfil perfilLogado;


    //Construtor privado que impede que outras classes criem novos objetos ControleDados
    private ControllerMaster(){};


    //Metodo publico para acessar
    public static ControllerMaster getControllerMaster(){
        return CONTROLLER_MASTER;
    }

    //Metodo para criar um perfil garantindo que o email é unico
    public boolean criarPerfil(ObjPerfil perfil, String email){
        if (listaPerfis.size() > 0) {
            for (int i = 0; i < listaPerfis.size(); i++) {
                if (listaPerfis.get(i).getEmail().equals(email)) {
                    return false;
                }
            }
            listaPerfis.add(perfil);
            return true;
        }else {
            listaPerfis.add(perfil);
            return true;
        }
    }

    //Getters -----------------------------------------------------------------------

    //passar informações do perfil para o app
    public ObjPerfil getInformacoesPerfil(){
        return perfilLogado;
    }

    public boolean getLoginOn(){
        return loginOn;
    }


    //Upgrade Perfil
    public void addLojaPerfil(ObjCardLoja loja){
        listaPerfis.get(indexConta).setMinhaLoja(loja);

    }

    //Setters --------------------------------------------------------------------------
    public void setLoginOn(boolean login){
        this.loginOn = login;
    }

    //Autenticação (Temporaria)-------------------------------------------------{
    //Localizandoi conta caso tenha
    public void autenticarConta(String email, String senha){
        for (int i = 0; i < listaPerfis.size(); i++){
            if (listaPerfis.get(i).getEmail().equals(email)){
                //Metodo para testar senhas

                if (autenticandoSenha(senha, i)) {
                    indexConta = i;
                    perfilLogado = listaPerfis.get(i);
                    setLoginOn(true);
                    break;
                }
            }
        }
    }
    private boolean autenticandoSenha(String senha, int conta){

        if (listaPerfis.get(conta).getSenha().equals(senha)){
            return true;
        }
        return false;
    }
    //--------------------------------------------------------------------------}

    public void logout() {
        perfilLogado = null;
        indexConta = -1;  // índice inválido, indicando "nenhum usuário logado"
        loginOn = false;
    }


}
