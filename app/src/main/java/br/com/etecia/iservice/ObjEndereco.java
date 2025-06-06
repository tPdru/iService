package br.com.etecia.iservice;

public class ObjEndereco {

    private int cepCnpj;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;
    private long codigo;
    private long codigoLoja;


    public ObjEndereco(int cepCnpj, String cidade, String complemento, String estado, String bairro, int numero, String rua) {
        this.cepCnpj = cepCnpj;
        this.cidade = cidade;
        this.complemento = complemento;
        this.estado = estado;
        this.bairro = bairro;
        this.numero = numero;
        this.rua = rua;
    }

    public ObjEndereco() {

    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public long getCodigoLoja() {
        return codigoLoja;
    }

    public void setCodigoLoja(long codigoLoja) {
        this.codigoLoja = codigoLoja;
    }

    public int getCepCnpj() {
        return cepCnpj;
    }

    public void setCepCnpj(int cepCnpj) {
        this.cepCnpj = cepCnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }
}
