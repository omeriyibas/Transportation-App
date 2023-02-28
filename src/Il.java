public class Il {
    private String ilAdi;
    private double ilKoordinati;

    public Il(String ilAdi, double ilKoordinati) {
        this.ilAdi = ilAdi;
        this.ilKoordinati = ilKoordinati;
    }

    public String getIlAdi() {
        return ilAdi;
    }

    public double getIlKoordinati() {
        return ilKoordinati;
    }

    @Override
    public String toString() {
        return ilAdi;
    }
}
