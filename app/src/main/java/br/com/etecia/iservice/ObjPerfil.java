package br.com.etecia.iservice;

public class ObjPerfil {
    private boolean temLoja;
    private String nome;
    private String email;
    private double notaCliente;
    private int quantServFinal;
    private int fotoUsuario;
    private int fotoLoja;
    private String senha;

    private int codigo;//Codigo para criar contas, feito para testes

    //Construtor sem loja
    public ObjPerfil(int codigo, String email, String nome, String senha, boolean temLoja) {
        this.codigo = codigo;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.temLoja = temLoja;
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
