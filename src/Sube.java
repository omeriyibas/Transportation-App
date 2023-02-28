public class Sube {
    private String ilAdi;
    private double ilKoordinat;
    private double subeKapasitesi;

    public Sube(String ilAdi, double ilKoordinat, double subeKapasitesi) {
        this.ilAdi = ilAdi;
        this.ilKoordinat = ilKoordinat;
        this.subeKapasitesi = subeKapasitesi;
    }

    public String getIlAdi() {
        return ilAdi;
    }

    public void setIlAdi(String ilAdi) {
        this.ilAdi = ilAdi;
    }

    public double getIlKoordinat() {
        return ilKoordinat;
    }

    public void setIlKoordinat(double ilKoordinat) {
        this.ilKoordinat = ilKoordinat;
    }

    public double getSubeKapasitesi() {
        return subeKapasitesi;
    }

    public void setSubeKapasitesi(double subeKapasitesi) {
        this.subeKapasitesi = subeKapasitesi;
    }

    @Override
    public String toString() {
        return ilAdi;

    }

}
