public class Kargo {
    private String adSoyadi;
    private String adres;
    private String takipNo;

    public Kargo(String adSoyadi, String adres, String takipNo) {
        this.adSoyadi = adSoyadi;
        this.adres = adres;
        this.takipNo = takipNo;
    }

    public String getAdSoyadi() {
        return adSoyadi;
    }

    public String getAdres() {
        return adres;
    }

    public String getTakipNo() {
        return takipNo;
    }

    @Override
    public String toString() {
        return takipNo + " | " + adres + " | " + adSoyadi;
    }
}
