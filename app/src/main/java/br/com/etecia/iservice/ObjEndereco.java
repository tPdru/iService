package br.com.etecia.iservice;

public class ObjEndereco {
    private int cepCnpj;
    private String estado;
    private String cidade;
    private String logradouro;
    private String rua;
    private int numero;
    private String complemento;


    public ObjEndereco(int cepCnpj, String cidade, String complemento, String estado, String logradouro, int numero, String rua) {
        this.cepCnpj = cepCnpj;
        this.cidade = cidade;
        this.complemento = complemento;
        this.estado = estado;
        this.logradouro = logradouro;
        this.numero = numero;
        this.rua = rua;
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
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
