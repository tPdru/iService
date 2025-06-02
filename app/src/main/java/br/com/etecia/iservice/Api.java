package br.com.etecia.iservice;

public class Api {
    /**
     * Passar o endereço do servidor aqui
     */
    private static final String ROOT_URL_PERFIL = "http://localhost/HeroeApi/v1/ApiTbUsuarios.php?apicall=";
    private static final String ROOT_URL_LOJA = "http://localhost/HeroeApi/v1/ApiTbLojas.php?apicall=";
    private static final String ROOT_URL_SERV = "http://10.67.96.108/HeroeApi/v1/ApiTbUsuarios.php?apicall=";

    /**
     * Constantes para chmar açoes do banco
     */
    //Usuario
    public static final String URL_CREATE_USUARIO = ROOT_URL_PERFIL + "createusuario";
    public static final String URL_READ_USUARIO = ROOT_URL_PERFIL + "getusuarios";
    public static final String URL_UPDATE_USUARIO = ROOT_URL_PERFIL + "updateUsuario";
    public static final String URL_DELETE_USUARIO = ROOT_URL_PERFIL + "deleteUsuario&cod_usua=";

    //Lojas
    public static final String URL_CREATE_LOJA = ROOT_URL_LOJA + "createServico";
    public static final String URL_READ_LOJA = ROOT_URL_LOJA + "getlojas";
    public static final String URL_UPDATE_LOJA = ROOT_URL_LOJA + "updateServico";
    public static final String URL_DELETE_LOJA = ROOT_URL_LOJA + "deleteServico&id=";

    //Serviços
    public static final String URL_CREATE_SERVICO = ROOT_URL_SERV + "createLoja";
    public static final String URL_READ_SERVICO = ROOT_URL_SERV + "getSevice";
    public static final String URL_UPDATE_SERVICO = ROOT_URL_SERV + "updateLoja";
    public static final String URL_DELETE_SERVICO = ROOT_URL_SERV + "deleteLoja&id=";

}
