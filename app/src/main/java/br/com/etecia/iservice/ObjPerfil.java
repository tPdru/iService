package br.com.etecia.iservice;

import java.sql.Blob;

public class ObjPerfil {
    private boolean temLoja;
    private String nome;
    private String email;
    private double notaCliente;
    private int quantServFinal = 0;
    private int fotoUsuario;
    private String senha;
    private String usuario;
    private String celular;
    private ObjCardLoja minhaLoja;
    private int codigo;//Codigo para criar contas, feito para testes



    //Construtor sem loja
    public ObjPerfil(int codigo, String email, String nome, String senha, String usuario, int fotoUsuario, boolean temLoja) {
        this.codigo = codigo;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.usuario = usuario;
        this.temLoja = temLoja;
        this.fotoUsuario = fotoUsuario;

    }

    //Construtor com loja
    public ObjPerfil(int codigo, String email, String nome, String senha, String usuario, int fotoUsuario, boolean temLoja, ObjCardLoja minhaLoja) {
        this.codigo = codigo;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.usuario = usuario;
        this.temLoja = temLoja;
        this.fotoUsuario = fotoUsuario;
        this.minhaLoja = minhaLoja;

    }

    //Construtor vazio para o json
    public ObjPerfil() {

    }

    public ObjCardLoja getMinhaLoja() {
        return minhaLoja;
    }

    public void setMinhaLoja(ObjCardLoja minhaLoja) {
        this.minhaLoja = minhaLoja;
    }

    public double getNotaCliente() {
        return notaCliente;
    }

    public void setNotaCliente(double notaCliente) {
        this.notaCliente = notaCliente;
    }

    public int getQuantServFinal() {
        return quantServFinal;
    }

    public void setQuantServFinal(int quantServFinal) {
        this.quantServFinal += quantServFinal;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(int fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isTemLoja() {
        return temLoja;
    }

    public void setTemLoja(boolean temLoja) {
        this.temLoja = temLoja;
    }

}
