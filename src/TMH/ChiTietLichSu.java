
package TMH;


public class ChiTietLichSu {
    private String MTK;
    private int Made;
    private float KetQua;
    private int CauDung;
    private String ngaythi;
    private String thoigian;
    private String CTTT_dethi;
    
    public ChiTietLichSu(String MTK, int made, float KQ, int CD, String NT, String TG, String CTTT_DT){
        this.MTK = MTK;
        this.Made = made;
        this.KetQua = KQ;
        this.CauDung = CD;
        this.ngaythi = NT;
        this.thoigian = TG;
        this.CTTT_dethi = CTTT_DT;
    }
    
    public ChiTietLichSu(String CTTT_DT){
        this.CTTT_dethi = CTTT_DT;
    }
    
    public String getMTK() { return MTK; }
    public int getMD() { return Made; }
    public float getKQ() { return KetQua; }
    public int getCD() { return CauDung; }
    public String getNT() { return ngaythi; }
    public String getTG() { return thoigian; }
    public String getCTTT_DT() { return CTTT_dethi;}
    
}
