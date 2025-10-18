
import TMH.CTN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class FormSuaCauHoi extends javax.swing.JFrame {
    private CTN ctn = new CTN();
    private static String MC;
   
    
    //======================================================================================================================================================================
    public FormSuaCauHoi(String mc) {
        initComponents(); // Khởi tạo các thành phần giao diện.
        ctn.c(); // Mở kết nối CSDL.
        setLocationRelativeTo(null);
        this.MC = mc; // Lưu mã câu hỏi vào biến toàn cục.
        capnhat(); // Gọi phương thức để tải dữ liệu của câu hỏi này lên form.
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        // Thêm một bộ lắng nghe sự kiện để xử lý việc đóng cửa sổ.
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                new FormQuanLiCauHoi().setVisible(true); // Mở lại form chính.
                dispose(); // Đóng form hiện tại.
            }
        });
    }
    
    
    //======================================================================================================================================================================
    public void capnhat(){
        try(Connection c = ctn.c()){
            // Chuẩn bị câu lệnh SQL để lấy thông tin chi tiết của một câu hỏi dựa vào Mã Câu (MC).
            PreparedStatement Pst2 = c.prepareStatement("select * from `cauhoi` where `MC` = ?");
            // Gán giá trị của biến MC vào câu lệnh SQL.
            Pst2.setInt(1,Integer.parseInt(MC));
            // Thực thi câu lệnh.
            ResultSet rs = Pst2.executeQuery();
            // Nếu tìm thấy câu hỏi.
            if(rs.next()){
                // Cập nhật các label và ô text với dữ liệu lấy từ CSDL.
                lbl_MaCau.setText(lbl_MaCau.getText()+" "+MC);
                txt_Noidung.setText(rs.getString("noidungch"));
                A.setText(rs.getString("A"));
                B.setText(rs.getString("B"));
                C.setText(rs.getString("C"));
                D.setText(rs.getString("D"));
                txt_DA.setText(rs.getString("DapAn"));
                    // GHI CHÚ: Cần thêm code ở đây để chọn đúng đáp án trong ComboBox `cb_dapAn`.
                    // Ví dụ: cb_dapAn.setSelectedItem(rs.getString("DapAn"));
                
            }
            
            // ========================= LỖI QUAN TRỌNG =========================
            // Dòng code `this.dispose()` dưới đây sẽ ngay lập tức ĐÓNG form lại
            // sau khi tải dữ liệu xong. Điều này khiến người dùng không bao giờ
            // nhìn thấy được dữ liệu và không thể sửa được.
            // BẠN CẦN XÓA DÒNG CODE NÀY ĐI.
            this.dispose();
            // ===================================================================

        }catch(Exception e){
            // Khối catch trống.
        }
    }
    
    
    //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        C = new javax.swing.JTextField();
        lbl_MaDe = new javax.swing.JLabel();
        lbl_MaCau = new javax.swing.JLabel();
        bt_SuaCauhoi = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_Noidung = new javax.swing.JTextField();
        D = new javax.swing.JTextField();
        A = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        B = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_DA = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setText("Đán Án C");

        lbl_MaDe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_MaDe.setText("CẬP NHẬT CÂU HỎI");

        lbl_MaCau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_MaCau.setText("Câu Hỏi");

        bt_SuaCauhoi.setText("CẬP NHẬT CÂU HỎI");
        bt_SuaCauhoi.setActionCommand("Sửa Câu Hỏi");
        bt_SuaCauhoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_SuaCauhoiActionPerformed(evt);
            }
        });

        jLabel2.setText("Nội Dung Câu Hỏi");

        jLabel7.setText("Đáp Án D");

        jLabel3.setText("Đáp Án A");

        jLabel8.setText("Đáp Án Đúng");

        jLabel4.setText("Đáp Án B");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Noidung)
                            .addComponent(A)
                            .addComponent(B)
                            .addComponent(C)
                            .addComponent(D)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_MaCau)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(lbl_MaDe, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addComponent(bt_SuaCauhoi))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txt_DA, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 126, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbl_MaDe)
                .addGap(18, 18, 18)
                .addComponent(lbl_MaCau)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Noidung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_DA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_SuaCauhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //======================================================================================================================================================================
    private void bt_SuaCauhoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_SuaCauhoiActionPerformed
        // Sử dụng try-with-resources để đảm bảo kết nối CSDL được đóng tự động.
        try (Connection c = ctn.c()) {
            // Chuẩn bị câu lệnh SQL UPDATE để cập nhật lại thông tin câu hỏi.
            PreparedStatement Pst1 = c.prepareStatement(""
                + "UPDATE `cauhoi` SET "
                + "`noidungch`= ? ,"
                + "`A`= ? ,"
                + "`B`= ? ,"
                + "`C`= ? ,"
                + "`D`= ? ,"
                + "`DapAn`= ? "
                + "WHERE `MC` = ?"); // Điều kiện để cập nhật đúng câu hỏi
            
            // Lấy dữ liệu mới từ các ô nhập liệu và gán vào câu lệnh SQL.
            Pst1.setString(1, txt_Noidung.getText());
            Pst1.setString(2, A.getText());
            Pst1.setString(3, B.getText());
            Pst1.setString(4, C.getText());
            Pst1.setString(5, D.getText());
            Pst1.setString(6, txt_DA.getText());
            Pst1.setInt(7, Integer.parseInt(MC)); // Sử dụng MC làm điều kiện WHERE.
            
            // Thực thi lệnh UPDATE.
            Pst1.executeUpdate();
            
            // Thông báo cho người dùng đã sửa thành công.
            JOptionPane.showMessageDialog(null, "Sửa câu hỏi thành công!");
            
            // GHI CHÚ: Sau khi sửa xong, bạn nên đóng form này (`this.dispose()`)
            // và gọi lại phương thức `viewtable()` trên `FormQuanLiCauHoi` để làm mới danh sách.
            
        }catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi sửa câu hỏi: " + e.getMessage());
        }
    }//GEN-LAST:event_bt_SuaCauhoiActionPerformed

    
    //======================================================================================================================================================================
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormSuaCauHoi(MC).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField A;
    private javax.swing.JTextField B;
    private javax.swing.JTextField C;
    private javax.swing.JTextField D;
    private javax.swing.JButton bt_SuaCauhoi;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lbl_MaCau;
    private javax.swing.JLabel lbl_MaDe;
    private javax.swing.JTextField txt_DA;
    private javax.swing.JTextField txt_Noidung;
    // End of variables declaration//GEN-END:variables
}
