package br.com.etecia.iservice;

public class ObjCardServicoPp {
    byte[] imgServicoPp;
    String txtNomeServicoPp, txtDetalhesServicoPp;
    double txtValorServicoPp;
    private long codigo;
    private long codigoLoja;


    public ObjCardServicoPp(byte[] imgServicoPp, String txtNomeServicoPp, String txtDetalhesServicoPp, double txtValorServicoPp) {
        this.imgServicoPp = imgServicoPp;
        this.txtNomeServicoPp = txtNomeServicoPp;
        this.txtDetalhesServicoPp =txtDetalhesServicoPp;
        this.txtValorServicoPp = txtValorServicoPp;
    }

    public ObjCardServicoPp() {

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

    public String getTxtDetalhesServicoPp() {
        return txtDetalhesServicoPp;
    }

    public void setTxtDetalhesServicoPp(String txtDetalhesServicoPp) {
        this.txtDetalhesServicoPp = txtDetalhesServicoPp;
    }

    public byte[] getImgServicoPp() {
        return imgServicoPp;
    }

    public void setImgServicoPp(byte[] imgServicoPp) {
        this.imgServicoPp = imgServicoPp;
    }

    public String getTxtNomeServicoPp() {
        return txtNomeServicoPp;
    }

    public void setTxtNomeServicoPp(String txtNomeServicoPp) {
        this.txtNomeServicoPp = txtNomeServicoPp;
    }

    public double getTxtValorServicoPp() {
        return txtValorServicoPp;
    }

    public void setTxtValorServicoPp(double txtValorServicoPp) {
        this.txtValorServicoPp = txtValorServicoPp;
    }
}
