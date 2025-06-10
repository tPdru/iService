package br.com.etecia.iservice;

import java.util.List;

public class ObjCardLoja {

    private List<ObjCardServicoPp> listaServico;
    private String nomeLoja;
    private byte[] imgLoja;
    private double txtNota;
    private ObjEndereco enderecoLoja;
    private String descricao;
    private boolean temEndereco;
    private boolean temServicos;
    private String emailDono;
    private long codigLoja;
    private long codUsuario;
    private String cpfCnpj;

    public ObjCardLoja(String emailDono, byte[] imgLoja, List<ObjCardServicoPp> listaServico, String nomeLoja, double txtNota, ObjEndereco enderecoLoja) {
        this.emailDono = emailDono;
        this.imgLoja = imgLoja;
        this.listaServico = listaServico;
        this.nomeLoja = nomeLoja;
        this.txtNota = txtNota;
        this.enderecoLoja = enderecoLoja;
    }

    public ObjCardLoja(String emailDono, byte[] imgLoja, List<ObjCardServicoPp> listaServico, String nomeLoja, double txtNota) {
        this.emailDono = emailDono;
        this.imgLoja = imgLoja;
        this.listaServico = listaServico;
        this.nomeLoja = nomeLoja;
        this.txtNota = txtNota;
    }

    public ObjCardLoja() {

    }


    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public long getCodUsuario() {
        return codUsuario;
    }
    public void setCodUsuario(long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public boolean isTemServicos() {
        return temServicos;
    }

    public void setTemServicos(boolean temServicos) {
        this.temServicos = temServicos;
    }



    public long getCodigLoja() {
        return codigLoja;
    }

    public void setCodigLoja(long codigLoja) {
        this.codigLoja = codigLoja;
    }

    public boolean isTemEndereco() {
        return temEndereco;
    }

    public void setTemEndereco(boolean temEndereco) {
        this.temEndereco = temEndereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ObjEndereco getEnderecoLoja() {
        return enderecoLoja;
    }

    public void setEnderecoLoja(ObjEndereco enderecoLoja) {
        this.enderecoLoja = enderecoLoja;
    }

    public byte[] getImgLoja() {
        return imgLoja;
    }

    public void setImgLoja(byte[] imgLoja) {
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

    public double getTxtNota() {
        return txtNota;
    }

    public void setTxtNota(int txtNota) {
        this.txtNota = txtNota;
    }

    public String getEmailDono() {
        return emailDono;
    }

    public void setEmailDono(String emailDono) {
        this.emailDono = emailDono;
    }
}
