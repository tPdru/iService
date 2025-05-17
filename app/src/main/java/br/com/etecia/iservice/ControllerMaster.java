package br.com.etecia.iservice;

import java.util.ArrayList;
import java.util.List;

public class ControllerMaster {

    //Atribto estatico que armazena a unica intancia da classe.
    private static final ControllerMaster CONTROLLER_MASTER = new ControllerMaster();

    //Variáveis para controle de informação
    private List<ObjPerfil> listaPerfis = new ArrayList<>();
    private boolean loginOn;
    private int indexConta;


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
    public int getCodigoList(){
        return listaPerfis.size();
    }

    //passar informações do perfil para o app
    public ObjPerfil getInformacoesPerfil(){
        return listaPerfis.get(indexConta);
    }

    public boolean getLoginOn(){
        if (loginOn){
            return true;
        }else {
            return false;
        }
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
                    setLoginOn(true);
                    break;
                }
            }
        }
    }
    private boolean autenticandoSenha(String senha, int conta){
        /*int tentativas = 3;
        for (int i = 0; i < tentativas; i++){
            if (listaPerfis.get(conta).getSenha().equals(senha)){

            }
        }*/
        if (listaPerfis.get(conta).getSenha().equals(senha)){
            return true;
        }
        return false;
    }
    //--------------------------------------------------------------------------}

    //metodo de criaçao de objetos para carregar recycleViews com exemplos
}
