package br.com.etecia.iservice;

import java.util.List;

public class ObjCardLoja {
    List<ObjCardServicoPp> listaServico;
    String nomeLoja;
    int imgLoja, txtNota;

    public ObjCardLoja(int imgLoja, List<ObjCardServicoPp> listaServico, String nomeLoja, int txtNota) {
        this.imgLoja = imgLoja;
        this.listaServico = listaServico;
        this.nomeLoja = nomeLoja;
        this.txtNota = txtNota;
    }


    public int getImgLoja() {
        return imgLoja;
    }

    public void setImgLoja(int imgLoja) {
        this.imgLoja = imgLoja;
    }

    public List<ObjCardServicoPp> getListaServico() {
        return listaServico;
    }

    public void setListaServico(List<ObjCardServicoPp> listaServico) {
        this.listaServico = listaServico;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public int getTxtNota() {
        return txtNota;
    }

    public void setTxtNota(int txtNota) {
        this.txtNota = txtNota;
    }
}
