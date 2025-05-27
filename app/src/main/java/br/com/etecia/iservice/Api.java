package br.com.etecia.iservice;

public class Api {
    /**
     * Passar o endereço do servidor aqui
     */
    private static final String ROOT_URL = "";

    /**
     * Constantes para chmar açoes do banco
     */
    //Usuario
    public static final String URL_CREATE_USUARIO = ROOT_URL + "createUsuario";
    public static final String URL_READ_USUARIO = ROOT_URL + "getUsuarios";
    public static final String URL_UPDATE_USUARIO = ROOT_URL + "updateUsuario";
    public static final String URL_DELETE_USUARIO = ROOT_URL + "deleteUsuario&ig=";

    //Lojas
    public static final String URL_CREATE_LOJA = ROOT_URL + "createServico";
    public static final String URL_READ_LOJA = ROOT_URL + "getServicos";
    public static final String URL_UPDATE_LOJA = ROOT_URL + "updateServico";
    public static final String URL_DELETE_LOJA = ROOT_URL + "deleteServico&id=";

    //Serviços
    public static final String URL_CREATE_SERVICO = ROOT_URL + "createLoja";
    public static final String URL_READ_SERVICO = ROOT_URL + "getLojas";
    public static final String URL_UPDATE_SERVICO = ROOT_URL + "updateLoja";
    public static final String URL_DELETE_SERVICO = ROOT_URL + "deleteLoja&id=";

}
