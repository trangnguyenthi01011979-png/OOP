


import TMH.CTN;
import TMH.CauHoi;
import TMH.DGD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormCauHoi extends javax.swing.JFrame {
    private static int Made;
    private static String MTK;
    private int i = 0;
    private List<CauHoi> CHD = new ArrayList<>();
    private Map<Integer, String> luubai = new HashMap<>();
    private CTN ctn = new CTN();
    private javax.swing.Timer timer;
    private int thoiGianConLai;
    private int tongThoiGianLamBai;
    private int H,M,S;
    
    
    //======================================================================================================================================================================
    public FormCauHoi(int made, String MTK) {
        this.Made = made;
        this.MTK = MTK;
        setLocationRelativeTo(null);
        initComponents(); // Khởi tạo các thành phần giao diện.
        ctn.c(); // Mở kết nối CSDL.
        nhom(); // Nhóm các RadioButton lại với nhau.
        gancauhoi(); // Tải câu hỏi từ CSDL vào danh sách `CHD`.
        hienthicauhoi(0); // Hiển thị câu hỏi đầu tiên (index 0).
        getThoiGianLamBai(); // Lấy thời gian làm bài từ CSDL.
        startTimer(); // Bắt đầu đếm ngược thời gian.
    }
    
    
    //======================================================================================================================================================================
    public void nhom(){
        group.add(A);
        group.add(B);
        group.add(C);
        group.add(D);
    }
    
    
    //======================================================================================================================================================================
    public void gancauhoi() {
        bt_Nopbai.setVisible(false); // Ẩn nút "Nộp bài" lúc đầu.
        try (Connection c = ctn.c()) {
            // 1. Tải các câu hỏi của đề thi.
            PreparedStatement pst = c.prepareStatement("SELECT c.MC, c.noidungch, c.A, c.B, c.C, c.D, c.DapAn, c.loaida " +
                                                       "FROM cauhoi AS c JOIN ctdt ON c.MC = ctdt.MC WHERE ctdt.MD = ?");
            pst.setInt(1, Made);
            ResultSet rs = pst.executeQuery();
            int cauhoiso = 1;
            while (rs.next()) {
                // Tạo đối tượng CauHoi từ dữ liệu đọc được và thêm vào danh sách CHD.
                CauHoi CHM = new CauHoi(
                    cauhoiso,
                    rs.getString("noidungch"),
                    rs.getString("A"),
                    rs.getString("B"),
                    rs.getString("C"),
                    rs.getString("D"),
                    rs.getString("DapAn"),
                    rs.getInt("loaida") // 🟩 thêm dòng này
                );

                CHD.add(CHM);
                cauhoiso++;
            }

            // 2. Tải thông tin chung của đề thi (tên đề, thời gian).
            PreparedStatement Pst = c.prepareStatement("select NoidungDeThi, ThoiGian from dethi where MD = ?");
            Pst.setInt(1, Made);
            ResultSet RS = Pst.executeQuery();
            if (RS.next()) {
                lbl_noidungdt.setText(RS.getString("NoidungDeThi"));
                // Ghi chú: lbl_time sẽ được cập nhật liên tục bởi timer, dòng này chỉ để hiển thị lúc đầu.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //======================================================================================================================================================================
    private void hienthicauhoi(int index) {
        if (index < 0 || index >= CHD.size()) return; // Kiểm tra index hợp lệ.
        
        CauHoi ch = CHD.get(index);
        
        if (ch.getLoaida() == 1) {
            // Gộp vào nhóm chỉ chọn được 1
            group = new javax.swing.ButtonGroup();
            group.add(A);
            group.add(B);
            group.add(C);
            group.add(D);
        } 
        // Nếu là câu nhiều đáp án (loaida = 2)
        else {
            // Bỏ nhóm ra → chọn nhiều được
            if (group != null) {
                group.remove(A);
                group.remove(B);
                group.remove(C);
                group.remove(D);
            }
        }
        
        // Cập nhật nội dung câu hỏi và các đáp án.
        lblCauHoi.setText("Câu " + (index + 1) + ": " + ch.getNoiDung());
        A.setText("A. " + ch.getDapAnA());
        B.setText("B. " + ch.getDapAnB());
        C.setText("C. " + ch.getDapAnC());
        D.setText("D. " + ch.getDapAnD());

        // Đặt ActionCommand cho mỗi RadioButton để dễ dàng lấy giá trị ("A", "B",...) khi người dùng chọn.
        A.setActionCommand("A");
        B.setActionCommand("B");
        C.setActionCommand("C");
        D.setActionCommand("D");

        // Xóa lựa chọn cũ trước khi hiển thị câu hỏi mới.
        group.clearSelection();

        // Kiểm tra xem người dùng đã trả lời câu này trước đó chưa.
        // Nếu có, hiển thị lại đáp án đã chọn.
        String daChon = luubai.get(ch.getMc());
        if (daChon != null) {
            switch (daChon) {
                case "A": A.setSelected(true); break;
                case "B": B.setSelected(true); break;
                case "C": C.setSelected(true); break;
                case "D": D.setSelected(true); break;
            }
        }

        // Quản lý việc hiển thị các nút điều hướng.
        bt_ChTruoc.setEnabled(index > 0); // Chỉ bật nút "Trước" khi không phải câu đầu tiên.
        bt_ChTieptheo.setVisible(true);   // Luôn hiện nút "Tiếp theo" (sẽ bị ẩn ở câu cuối).
        bt_Nopbai.setVisible(false);      // Ẩn nút "Nộp bài".
    }
    
    
    
    //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group = new javax.swing.ButtonGroup();
        lblCauHoi = new javax.swing.JLabel();
        A = new javax.swing.JRadioButton();
        B = new javax.swing.JRadioButton();
        C = new javax.swing.JRadioButton();
        D = new javax.swing.JRadioButton();
        bt_ChTieptheo = new javax.swing.JButton();
        bt_ChTruoc = new javax.swing.JButton();
        bt_Nopbai = new javax.swing.JButton();
        lbl_noidungdt = new javax.swing.JLabel();
        lbl_time = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblCauHoi.setText("jLabel1");

        bt_ChTieptheo.setText("Câu hỏi tiếp theo");
        bt_ChTieptheo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ChTieptheoActionPerformed(evt);
            }
        });

        bt_ChTruoc.setText("Câu hỏi trước");
        bt_ChTruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ChTruocActionPerformed(evt);
            }
        });

        bt_Nopbai.setText("Nộp Bài");
        bt_Nopbai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_NopbaiActionPerformed(evt);
            }
        });

        lbl_noidungdt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_noidungdt.setText("ĐỀ THI ");

        lbl_time.setText("THỜI GIAN LÀM BÀI: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(B)
                            .addComponent(A)
                            .addComponent(C)
                            .addComponent(D)
                            .addComponent(lbl_noidungdt)
                            .addComponent(lbl_time)
                            .addComponent(lblCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(bt_ChTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(bt_ChTieptheo)
                        .addGap(51, 51, 51)
                        .addComponent(bt_Nopbai, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lbl_noidungdt)
                .addGap(18, 18, 18)
                .addComponent(lbl_time)
                .addGap(15, 15, 15)
                .addComponent(lblCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(A)
                .addGap(18, 18, 18)
                .addComponent(B)
                .addGap(18, 18, 18)
                .addComponent(C)
                .addGap(18, 18, 18)
                .addComponent(D)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_ChTieptheo)
                    .addComponent(bt_ChTruoc)
                    .addComponent(bt_Nopbai))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    //======================================================================================================================================================================
    private void bt_ChTieptheoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ChTieptheoActionPerformed
           // 1. Lưu lại đáp án của câu hỏi hiện tại (nếu có chọn).
        CauHoi ch = CHD.get(i);

        if (ch.getLoaida() == 1) {
            // Câu hỏi 1 đáp án – giữ như cũ
            if (group.getSelection() != null) {
                luubai.put(ch.getMc(), group.getSelection().getActionCommand());
            }
        } else {
            // Câu hỏi nhiều đáp án – lưu tất cả các đáp án được chọn
            StringBuilder dapAnChon = new StringBuilder();
            if (A.isSelected()) dapAnChon.append("A");
            if (B.isSelected()) dapAnChon.append("B");
            if (C.isSelected()) dapAnChon.append("C");
            if (D.isSelected()) dapAnChon.append("D");

            luubai.put(ch.getMc(), dapAnChon.toString());
        }


        // 2. Nếu chưa phải câu cuối cùng, chuyển sang câu tiếp theo.
        if (i < CHD.size() - 1) {
            i++;
            hienthicauhoi(i);
        }
        // 3. Nếu đang ở câu cuối cùng.
        else if (i == CHD.size() - 1) {
            bt_ChTieptheo.setVisible(false); // Ẩn nút "Tiếp theo".
            bt_Nopbai.setVisible(true);      // Hiện nút "Nộp bài".
            JOptionPane.showMessageDialog(this,
                "Bạn đã đến câu cuối cùng.\nNhấn 'Nộp Bài' để xem kết quả!",
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_bt_ChTieptheoActionPerformed
    
    
    
    //======================================================================================================================================================================
    private void bt_ChTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ChTruocActionPerformed
        // 1. Lưu lại đáp án của câu hỏi hiện tại (nếu có chọn).
        CauHoi ch = CHD.get(i);

        if (ch.getLoaida() == 1) {
            // Câu hỏi 1 đáp án – giữ như cũ
            if (group.getSelection() != null) {
                luubai.put(ch.getMc(), group.getSelection().getActionCommand());
            }
        } else {
            // Câu hỏi nhiều đáp án – lưu tất cả các đáp án được chọn
            StringBuilder dapAnChon = new StringBuilder();
            if (A.isSelected()) dapAnChon.append("A");
            if (B.isSelected()) dapAnChon.append("B");
            if (C.isSelected()) dapAnChon.append("C");
            if (D.isSelected()) dapAnChon.append("D");

            luubai.put(ch.getMc(), dapAnChon.toString());
        }

        
        // 2. Nếu không phải câu đầu tiên, lùi về câu trước.
        if (i > 0) {
            i--;
            hienthicauhoi(i);
            // Sau khi lùi lại, cần đảm bảo nút "Tiếp theo" hiện và nút "Nộp bài" ẩn.
            bt_ChTieptheo.setVisible(true);
            bt_Nopbai.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Đang ở câu đầu tiên!");
        }
    }//GEN-LAST:event_bt_ChTruocActionPerformed
    
    
    
    //======================================================================================================================================================================
    private void bt_NopbaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_NopbaiActionPerformed
            if (timer != null) timer.stop(); // Dừng đồng hồ đếm ngược.

        // Tính toán thời gian đã làm bài.
        int thoiGianDaLam = tongThoiGianLamBai - thoiGianConLai;
        M = thoiGianDaLam / 60; // Phút
        S = thoiGianDaLam % 60; // Giây
        H = 0; // Giờ (có thể tính thêm nếu cần)

        xuatKetQuaChiTiet1(); // Xử lý, chấm điểm và lưu kết quả.
        xuatKetQuaChiTiet();
        // Vô hiệu hóa các nút sau khi nộp bài.
        bt_Nopbai.setEnabled(false);
        bt_ChTruoc.setEnabled(false);
        
        // Đóng form làm bài và quay về giao diện chính của người dùng.
        this.dispose();
        new DGD(new FormGiaoDienChinhUser(MTK, Made)); // DGD có thể là một lớp tùy chỉnh để hiển thị dialog.   
    }//GEN-LAST:event_bt_NopbaiActionPerformed
    
    
    //======================================================================================================================================================================
    public void xuatKetQuaChiTiet1() {
        int dung = 0, sai = 0;

        // Lặp qua từng câu hỏi để tính số câu đúng/sai
        for (CauHoi ch : CHD) {
            int maCau = ch.getMc();
            String dapAnDung = ch.getDapAnDung();
            String dapAnNguoiChon = luubai.get(maCau);

            if (dapAnNguoiChon == null) {
                sai++;
                continue;
            }

            if (ch.getLoaida() == 1) {
                // Câu hỏi 1 đáp án
                if (dapAnNguoiChon.equalsIgnoreCase(dapAnDung)) {
                    dung++;
                } else {
                    sai++;
                }
            } else {
                // Câu hỏi nhiều đáp án
                if (dapAnNguoiChon.length() == dapAnDung.length() &&
                    dapAnDung.chars().allMatch(ca -> dapAnNguoiChon.toUpperCase().indexOf(ca) != -1)) {
                    dung++;
                } else {
                    sai++;
                }
            }
        }

        // Tính điểm (theo thang 10)
        double diem = 0;
        if (CHD.size() > 0) {
            diem = ((double) dung / CHD.size()) * 10;
        }

        // Chuỗi tổng kết ngắn gọn
        StringBuilder kq = new StringBuilder();
        kq.append("KẾT QUẢ BÀI LÀM\n")
          .append("---------------------------\n")
          .append("Tổng số câu: ").append(CHD.size()).append("\n")
          .append("Số câu đúng: ").append(dung).append("\n")
          .append("Số câu sai: ").append(sai).append("\n")
          .append("Số điểm: ").append(String.format("%.2f", diem)).append("\n"); // làm tròn 2 chữ số

        // Hiển thị thông báo
        JOptionPane.showMessageDialog(this, kq.toString(), "Kết quả bài làm", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void xuatKetQuaChiTiet() {
        StringBuilder kq = new StringBuilder(); 
        int dung = 0, sai = 0;

        kq.append("===== KẾT QUẢ CHI TIẾT BÀI LÀM =====\n\n");

        for (CauHoi ch : CHD) {
            int maCau = ch.getMc();
            String dapAnDung = ch.getDapAnDung();
            String dapAnNguoiChon = luubai.get(maCau);

            // In tiêu đề câu hỏi
            kq.append("Câu ").append(maCau).append(": ").append(ch.getNoiDung()).append("\n");

            // Hiển thị toàn bộ 4 đáp án
            kq.append("   A. ").append(ch.getDapAnA()).append("\n");
            kq.append("   B. ").append(ch.getDapAnB()).append("\n");
            kq.append("   C. ").append(ch.getDapAnC()).append("\n");
            kq.append("   D. ").append(ch.getDapAnD()).append("\n");

            // Kiểm tra người dùng đã chọn chưa
            if (dapAnNguoiChon == null || dapAnNguoiChon.isEmpty()) {
                kq.append("   Bạn chưa chọn đáp án nào.\n");
                kq.append("   Đáp án đúng: ").append(dapAnDung).append("\n");
                kq.append("   Kết quả: Sai\n\n");
                sai++;
                continue;
            }

            // So sánh kết quả
            boolean laDung;
            if (ch.getLoaida() == 1) {
                laDung = dapAnNguoiChon.equalsIgnoreCase(dapAnDung);
            } else {
                laDung = dapAnNguoiChon.length() == dapAnDung.length() &&
                         dapAnDung.chars().allMatch(ca -> dapAnNguoiChon.toUpperCase().indexOf(ca) != -1);
            }

            if (laDung) {
                dung++;
                kq.append("   Kết quả: Đúng\n");
            } else {
                sai++;
                kq.append("   Kết quả: Sai\n");
            }

            // Thông tin đáp án đã chọn và đúng
            kq.append("   Bạn chọn: ").append(dapAnNguoiChon).append("\n");
            kq.append("   Đáp án đúng: ").append(dapAnDung).append("\n\n");
        }

        // Thêm phần tổng kết
        double diem = (CHD.size() > 0) ? ((double) dung / CHD.size()) * 10 : 0;

        kq.append("=====================================\n")
          .append("TỔNG KẾT:\n")
          .append("   Tổng số câu: ").append(CHD.size()).append("\n")
          .append("   Số câu đúng: ").append(dung).append("\n")
          .append("   Số câu sai: ").append(sai).append("\n")
          .append("   Số điểm: ").append(String.format("%.2f", diem)).append("\n");

        // Lưu vào lịch sử thi
        luuKetQuaVaoLichSuThi(dung, sai, kq.toString());

    }

    
    
    //======================================================================================================================================================================
    private void luuKetQuaVaoLichSuThi(int dung, int sai, String TTChiTiet) {
        try (Connection c = ctn.c()) {
            int tong = dung + sai;
            // Tính điểm trên thang 10.
            double kq = (tong > 0) ? ((double) dung / CHD.size()) * 10 : 0;
            String thoigianlam = H + ":" + M + ":" + S;

            // Lấy ngày thi từ bảng dethi để đảm bảo tính nhất quán.
            java.sql.Date ngayThi = new java.sql.Date(System.currentTimeMillis()); // Mặc định là ngày hiện tại.
            PreparedStatement pstNgay = c.prepareStatement("SELECT NgayThi FROM dethi WHERE MD = ?");
            pstNgay.setInt(1, Made);
            ResultSet rs = pstNgay.executeQuery();
            if (rs.next()) {
                ngayThi = rs.getDate("NgayThi");
            }

            // Chèn dữ liệu vào bảng lichsuthi.
            PreparedStatement pst = c.prepareStatement(
                "INSERT INTO lichsuthi (MaTaiKhoan, MaDe, KQ, socaudung, NgayThi, ThoiGianLam, TTCT) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            pst.setString(1, MTK);

            pst.setInt(2, Made);
            pst.setDouble(3, Math.round(kq * 10.0) / 10.0); // Làm tròn điểm đến 1 chữ số thập phân.
            pst.setInt(4, dung);
            pst.setDate(5, ngayThi);
            pst.setString(6, thoigianlam);
            pst.setString(7, TTChiTiet);
            pst.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu kết quả thi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    

    //======================================================================================================================================================================
     private void getThoiGianLamBai() {
        try (Connection c = ctn.c()) {
            PreparedStatement pst = c.prepareStatement("SELECT ThoiGian FROM dethi WHERE MD = ?");
            pst.setInt(1, Made);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int phut = rs.getInt("ThoiGian");
                tongThoiGianLamBai = phut * 60; // Chuyển phút thành giây.
                thoiGianConLai = tongThoiGianLamBai;
            } else {
                tongThoiGianLamBai = thoiGianConLai = 600; // Mặc định 10 phút nếu không tìm thấy.
            }
        } catch (Exception e) {
            e.printStackTrace();
            tongThoiGianLamBai = thoiGianConLai = 600; // Mặc định 10 phút nếu có lỗi.
        }
    }
    
    
    //======================================================================================================================================================================
    private void startTimer() {
        // Tạo một Timer, sẽ kích hoạt sự kiện mỗi 1000ms (1 giây).
        timer = new javax.swing.Timer(1000, e -> {
            thoiGianConLai--; // Giảm thời gian còn lại đi 1 giây.
            int phut = thoiGianConLai / 60;
            int giay = thoiGianConLai % 60;
            // Cập nhật label hiển thị thời gian.
            lbl_time.setText(String.format("Thời gian còn lại: %02d:%02d", phut, giay));

            // Nếu hết giờ.
            if (thoiGianConLai <= 0) {
                timer.stop(); // Dừng đồng hồ.
                JOptionPane.showMessageDialog(this,
                    "Hết thời gian làm bài! Hệ thống sẽ tự động nộp bài.",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
                bt_NopbaiActionPerformed(null); // Tự động gọi sự kiện nộp bài.
            }
        });
        timer.start(); // Bắt đầu chạy timer.
    }
    
    
    //======================================================================================================================================================================
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCauHoi(Made,MTK).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton A;
    private javax.swing.JRadioButton B;
    private javax.swing.JRadioButton C;
    private javax.swing.JRadioButton D;
    private javax.swing.JButton bt_ChTieptheo;
    private javax.swing.JButton bt_ChTruoc;
    private javax.swing.JButton bt_Nopbai;
    private javax.swing.ButtonGroup group;
    private javax.swing.JLabel lblCauHoi;
    private javax.swing.JLabel lbl_noidungdt;
    private javax.swing.JLabel lbl_time;
    // End of variables declaration//GEN-END:variables
}
