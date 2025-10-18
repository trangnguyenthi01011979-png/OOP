
import TMH.CTN;
import TMH.DGD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class FormGiaoDienChinhUser extends javax.swing.JFrame {
    private CTN ctn = new CTN();
    private static String MTK;
    private static int made;
    
    
    //======================================================================================================================================================================
    public FormGiaoDienChinhUser(String MTK, int mde) {
        this.MTK = MTK;
        this.made = mde;
        initComponents(); // Khởi tạo các thành phần giao diện.
        ctn.c(); // Mở kết nối CSDL.
        
        // Kiểm tra xem người dùng đã làm bài thi này trước đó chưa.
        if (!kiemtraBaiLam()) {
            // Nếu đã làm rồi (hàm trả về false), vô hiệu hóa nút "THI NGAY".
            bt_thi.setEnabled(false);
        } else {
            // Nếu chưa làm, cho phép nhấn nút "THI NGAY".
            bt_thi.setEnabled(true);
        }
        
        // Tải và hiển thị thông tin của người dùng và đề thi lên giao diện.
        xuatthongtinUSER();
    }
    
    
    //======================================================================================================================================================================
    private void xuatthongtinUSER() {
        try (Connection c = ctn.c()) {
            // Chuẩn bị câu lệnh SQL để lấy thông tin từ nhiều bảng.
            // Cú pháp JOIN bằng dấu phẩy là cú pháp cũ, nên dùng JOIN...ON để rõ ràng hơn.
            PreparedStatement Pst = c.prepareStatement(
                "select HoTen, ttnguoithi.MaTaiKhoan, TenNganh, TenLop, NoidungDeThi, ThoiGian "
                + "from ttnguoithi, lop, nganh, dethi, thi "
                + "where ttnguoithi.MaTaiKhoan = ? "
                + "and ttnguoithi.MaLop = lop.MaLop and ttnguoithi.MaNganh = nganh.MaNganh "
                + "and ttnguoithi.MaTaiKhoan = thi.MaTaiKhoan and thi.MD = dethi.MD"
            );
            Pst.setString(1, MTK);
            ResultSet rs = Pst.executeQuery();
            
            // Nếu tìm thấy thông tin, cập nhật các label trên giao diện.
            if (rs.next()) {
                lbl_ten.setText("Họ và Tên Người Thi: " + rs.getString("HoTen"));
                lbl_mtk.setText("Mã Tài Khoản: " + rs.getString("MaTaiKhoan"));
                lbl_nganh.setText("Ngành: " + rs.getString("TenNganh"));
                lbl_lop.setText("Lớp: " + rs.getString("TenLop"));
                lbl_dethi.setText(rs.getString("Noidungdethi"));
                lbl_time.setText("Thời Gian Làm Bài: " + rs.getInt("ThoiGian") + " phút");
            }
        } catch (Exception e) {
            // Khối catch trống, nên thêm e.printStackTrace() để dễ debug.
            e.printStackTrace();
        }
    }
    
    
    //======================================================================================================================================================================
    private boolean kiemtraBaiLam() {
        try (Connection c = ctn.c()) {
            // Truy vấn bảng `dakt` (có thể là "đã kiểm tra") để tìm bản ghi.
            PreparedStatement pst = c.prepareStatement(
                "SELECT * FROM lichsuthi WHERE MaTaiKhoan = ? AND MaDe = ?"
            );
            pst.setString(1, MTK);
            pst.setInt(2, made);
            ResultSet rs = pst.executeQuery();

            // Nếu rs.next() trả về true, nghĩa là có kết quả -> sinh viên đã thi.
            if (rs.next()) {
                bt_thi.setText("ĐÃ LÀM BÀI"); // Cập nhật text của nút.
                return false; // Trả về false để vô hiệu hóa nút.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Nếu không tìm thấy bản ghi nào, nghĩa là sinh viên chưa thi.
        return true;
    }
    //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        bt_thi = new javax.swing.JButton();
        lbl_ten = new javax.swing.JLabel();
        lbl_lop = new javax.swing.JLabel();
        lbl_nganh = new javax.swing.JLabel();
        lbl_mtk = new javax.swing.JLabel();
        lbl_dethi = new javax.swing.JLabel();
        lbl_time = new javax.swing.JLabel();
        bt_LichSu = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bt_thi.setText("THI NGAY");
        bt_thi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_thiActionPerformed(evt);
            }
        });

        lbl_ten.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_ten.setText("Họ Tên Người Thi:");

        lbl_lop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_lop.setText("Lớp:");

        lbl_nganh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_nganh.setText("Ngành:");

        lbl_mtk.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_mtk.setText("Mã Tài Khoản:");

        lbl_dethi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_dethi.setText("-");
        lbl_dethi.setToolTipText("");
        lbl_dethi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbl_time.setText("THỜI GIAN LÀM BÀI: ");

        bt_LichSu.setText("LỊCH SỬ THI");
        bt_LichSu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_LichSuActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 203, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_thi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(209, 209, 209))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_ten, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(lbl_mtk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_nganh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_lop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_LichSu)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_dethi, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_ten)
                    .addComponent(bt_LichSu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_mtk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_nganh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_lop)
                .addGap(62, 62, 62)
                .addComponent(lbl_dethi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_time)
                .addGap(66, 66, 66)
                .addComponent(bt_thi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //======================================================================================================================================================================
    private void bt_thiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_thiActionPerformed
         try (Connection c = ctn.c()) {
            // 1. Lấy lại mã đề thi được gán cho tài khoản này (để đảm bảo chắc chắn).
            PreparedStatement pst = c.prepareStatement("SELECT MD FROM thi WHERE MaTaiKhoan = ?");
            pst.setString(1, MTK);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                made = rs.getInt("MD");
                
                // 2. GHI NHẬN LƯỢT LÀM BÀI (quan trọng)
                // Chèn một bản ghi vào bảng `dakt` để đánh dấu rằng người dùng đã bắt đầu làm bài.
                // Việc này ngăn người dùng thoát ra và vào lại để làm lại bài thi.
                // GHI CHÚ: Logic này đã được chuyển sang `kiemtraBaiLam` và bảng `lichsuthi`.
                // Việc insert vào `dakt` có thể là một cơ chế riêng.
                PreparedStatement pst1 = c.prepareStatement(
                    "INSERT INTO dakt(MD, MaTaiKhoan, TrangThai) VALUES (?, ?, ?)"
                );
                pst1.setInt(1, made);
                pst1.setString(2, MTK);
                pst1.setString(3, "Đã làm!");
                // pst1.executeUpdate(); // Có thể bật lại dòng này nếu cần.

                // 3. Chuyển sang form làm bài thi.
                new DGD(new FormCauHoi(made, MTK));
                this.dispose(); // Đóng form hiện tại.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_bt_thiActionPerformed
    
    
    //======================================================================================================================================================================
    private void bt_LichSuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_LichSuActionPerformed
        // Mở form Lịch sử thi và truyền vào mã tài khoản, mã đề.
        new DGD(new FormLichSuThi(MTK, made));
        this.dispose(); // Đóng form hiện tại.
    }//GEN-LAST:event_bt_LichSuActionPerformed

    
    //======================================================================================================================================================================
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new FormGiaoDienChinhUser(MTK,made).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_LichSu;
    private javax.swing.JButton bt_thi;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl_dethi;
    private javax.swing.JLabel lbl_lop;
    private javax.swing.JLabel lbl_mtk;
    private javax.swing.JLabel lbl_nganh;
    private javax.swing.JLabel lbl_ten;
    private javax.swing.JLabel lbl_time;
    // End of variables declaration//GEN-END:variables
}
