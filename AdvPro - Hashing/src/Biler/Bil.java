package Biler;

import java.util.Objects;

public class Bil {
    private String reg;
    private String mærke;
    private String model;
    private String farve;

    public Bil(String reg, String mærke, String model, String farve) {
        this.reg = reg;
        this.mærke = mærke;
        this.model = model;
        this.farve = farve;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getMærke() {
        return mærke;
    }

    public void setMærke(String mærke) {
        this.mærke = mærke;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFarve() {
        return farve;
    }

    public void setFarve(String farve) {
        this.farve = farve;
    }

    public String toString(){
        return mærke + " " + model + "(" + farve + ")" + ", registreringsnummer: " + reg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bil bil = (Bil) o;
        return reg.equals(bil.reg) && mærke.equals(bil.mærke);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reg, mærke);
    }
}
