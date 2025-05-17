package br.com.etecia.iservice;

public class ObjCardServicoPp {
    int imgServicoPp;
    String txtNomeServicoPp, txtDetalhesServicoPp;
    double txtValorServicoPp;


    public ObjCardServicoPp(int imgServicoPp, String txtNomeServicoPp, String txtDetalhesServicoPp, double txtValorServicoPp) {
        this.imgServicoPp = imgServicoPp;
        this.txtNomeServicoPp = txtNomeServicoPp;
        this.txtDetalhesServicoPp =txtDetalhesServicoPp;
        this.txtValorServicoPp = txtValorServicoPp;
    }


    public String getTxtDetalhesServicoPp() {
        return txtDetalhesServicoPp;
    }

    public void setTxtDetalhesServicoPp(String txtDetalhesServicoPp) {
        this.txtDetalhesServicoPp = txtDetalhesServicoPp;
    }

    public int getImgServicoPp() {
        return imgServicoPp;
    }

    public void setImgServicoPp(int imgServicoPp) {
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
