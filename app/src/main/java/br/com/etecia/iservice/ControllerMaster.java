package br.com.etecia.iservice;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ControllerMaster {

    //Atribto estatico que armazena a unica intancia da classe.
    private static final ControllerMaster CONTROLLER_MASTER = new ControllerMaster();

    //Variáveis para controle de informação
    private List<ObjPerfil> listaPerfis = new ArrayList<>();
    private List<ObjCardLoja> listaLojas = new ArrayList<>();
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
    //Passa informações da loja se ouver
    public ObjCardLoja getInformacoesLoja(){
        return listaPerfis.get(indexConta).getMinhaLoja();
    }

    public boolean getLoginOn(){
        if (loginOn){
            return true;
        }else {
            return false;
        }
    }

    public List<ObjCardLoja> getListaLojas(){
        return listaLojas;
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

    //metodo para encontrar a loja atraves do codigo(email)
    public ObjPerfil localizaadorLojas( String email){

        int ind;
        for ( int i = 0; i < listaPerfis.size(); i++) {

           if (listaPerfis.get(i).getEmail().equals(email)){
               return listaPerfis.get(i);
           }

        }
        return localizaadorLojas(email);
    }

    //Recarregando a lista de lojas
    public void carregarLojas(){
        listaLojas.clear();
        for (int i = 0; i < listaPerfis.size(); i++){
            if (listaPerfis.get(i).isTemLoja()){
                listaLojas.add(listaPerfis.get(i).getMinhaLoja());
            }
        }
    }

    public void carregarLojasBanco(List<ObjCardLoja> lista) {

        for (int i = 0; i < lista.size(); i++) {
            listaLojas.add(lista.get(i));
        }

    }

    



}
