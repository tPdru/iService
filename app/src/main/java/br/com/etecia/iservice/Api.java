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
    private static final String URL_CREATE_USUARIO = ROOT_URL + "createUsuario";
    private static final String URL_READ_USUARIO = ROOT_URL + "getUsuarios";
    private static final String URL_UPDATE_USUARIO = ROOT_URL + "updateUsuario";
    private static final String URL_DELETE_USUARIO = ROOT_URL + "deleteUsuario&ig=";

    //Lojas
    private static final String URL_CREATE_LOJA = ROOT_URL + "createServico";
    private static final String URL_READ_LOJA = ROOT_URL + "getServicos";
    private static final String URL_UPDATE_LOJA = ROOT_URL + "updateServico";
    private static final String URL_DELETE_LOJA = ROOT_URL + "deleteServico&id=";

    //Serviços
    private static final String URL_CREATE_SERVICO = ROOT_URL + "createLoja";
    private static final String URL_READ_SERVICO = ROOT_URL + "getLojas";
    private static final String URL_UPDATE_SERVICO = ROOT_URL + "updateLoja";
    private static final String URL_DELETE_SERVICO = ROOT_URL + "deleteLoja&id=";

}
