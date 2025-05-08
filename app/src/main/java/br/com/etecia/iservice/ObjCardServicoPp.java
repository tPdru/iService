package br.com.etecia.iservice;

public class ObjCardServicoPp {
    int imgServicoPp, txtValorServicoPp;
    String txtNomeServicoPp;


    public ObjCardServicoPp(int imgServicoPp, String txtNomeServicoPp, int txtValorServicoPp) {
        this.imgServicoPp = imgServicoPp;
        this.txtNomeServicoPp = txtNomeServicoPp;
        this.txtValorServicoPp = txtValorServicoPp;
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

    public int getTxtValorServicoPp() {
        return txtValorServicoPp;
    }

    public void setTxtValorServicoPp(int txtValorServicoPp) {
        this.txtValorServicoPp = txtValorServicoPp;
    }
}
