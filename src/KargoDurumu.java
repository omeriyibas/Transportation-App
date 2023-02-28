public class KargoDurumu {
    private String kargoGorevlisi;
    private String kargoDurumBilgisi;
    private String kargoNo;

    public KargoDurumu(String kargoGorevlisi, String kargoDurumBilgisi, String kargoNo) {
        this.kargoGorevlisi = kargoGorevlisi;
        this.kargoDurumBilgisi = kargoDurumBilgisi;
        this.kargoNo = kargoNo;
    }

    public String getKargoGorevlisi() {
        return kargoGorevlisi;
    }

    public void setKargoGorevlisi(String kargoGorevlisi) {
        this.kargoGorevlisi = kargoGorevlisi;
    }

    public String getKargoDurumBilgisi() {
        return kargoDurumBilgisi;
    }

    public void setKargoDurumBilgisi(String kargoDurumBilgisi) {
        this.kargoDurumBilgisi = kargoDurumBilgisi;
    }

    public String getKargoNo() {
        return kargoNo;
    }

    public void setKargoNo(String kargoNo) {
        this.kargoNo = kargoNo;
    }

    @Override
    public String toString() {
        return kargoNo + " | " + kargoGorevlisi;
    }
}
