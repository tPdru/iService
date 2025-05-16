package br.com.etecia.iservice;

import java.util.List;

public class ObjCardLoja {

    private List<ObjCardServicoPp> listaServico;
    private String nomeLoja;
    private int imgLoja, txtNota;
    private ObjEndereco enderecoLoja;

    public ObjCardLoja(int imgLoja, List<ObjCardServicoPp> listaServico, String nomeLoja, int txtNota, ObjEndereco enderecoLoja) {
        this.imgLoja = imgLoja;
        this.listaServico = listaServico;
        this.nomeLoja = nomeLoja;
        this.txtNota = txtNota;
        this.enderecoLoja = enderecoLoja;
    }

    public ObjCardLoja(int imgLoja, List<ObjCardServicoPp> listaServico, String nomeLoja, int txtNota) {
        this.imgLoja = imgLoja;
        this.listaServico = listaServico;
        this.nomeLoja = nomeLoja;
        this.txtNota = txtNota;
    }

    public ObjEndereco getEnderecoLoja() {
        return enderecoLoja;
    }

    public void setEnderecoLoja(ObjEndereco enderecoLoja) {
        this.enderecoLoja = enderecoLoja;
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
