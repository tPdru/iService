package br.com.etecia.iservice;

import java.util.List;

public interface InRespostaPerfil {

    //Interface para passar a lista de perfis
    void listaReadPerfil(List<ObjPerfil> listaPerfils);

    //Interface para passar a lista de lojas
    void listaReadLoja(List<ObjCardLoja> listaLojas);

}
