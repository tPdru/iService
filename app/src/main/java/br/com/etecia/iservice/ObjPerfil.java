package br.com.etecia.iservice;

public class ObjPerfil {
    private boolean temLoja;
    private String nome;
    private String email;
    private double notaCliente;
    private int quantServFinal = 0;
    private byte[] foto;
    private String senha;
    private String usuario;
    private String celular;
    private ObjCardLoja minhaLoja;

    private long codigo;


    //Construtor sem loja
    public ObjPerfil(long codigo, String email, String nome, String senha, String usuario, byte[] foto, boolean temLoja) {
        this.codigo = codigo;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.usuario = usuario;
        this.foto = foto;
        this.temLoja = temLoja;


    }

    //Construtor com loja
    public ObjPerfil(long codigo, String email, String nome, String senha, String usuario, byte[] imagem, boolean temLoja, ObjCardLoja minhaLoja) {
        this.codigo = codigo;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.usuario = usuario;
        this.temLoja = temLoja;
        this.foto = foto;
        this.minhaLoja = minhaLoja;

    }

    //Construtor sem imagem
    public ObjPerfil(long codigo, String email, String nome, String senha, String usuario, boolean temLoja, ObjCardLoja minhaLoja) {
        this.codigo = codigo;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.usuario = usuario;
        this.temLoja = temLoja;
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
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
