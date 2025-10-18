
import TMH.CTN;
import TMH.DGD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class FormTaoCauHoi extends javax.swing.JFrame {
    private CTN ctn = new CTN();
    private boolean check = false;
    
    
    //======================================================================================================================================================================
    public FormTaoCauHoi() {
        initComponents(); // Khởi tạo các thành phần giao diện.
        ctn.c(); // Mở kết nối CSDL.
        setLocationRelativeTo(null);
        // Thiết lập hành vi khi người dùng nhấn nút 'X' trên cửa sổ.
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        // Thêm một bộ lắng nghe sự kiện để xử lý việc đóng cửa sổ.
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // SỬA LẠI: Mở lại form Quản Lý Câu Hỏi thay vì Quản Lý Tài Khoản.
                new FormQuanLiCauHoi().setVisible(true);

                // Đóng form hiện tại.
                dispose();
            }
        });
    }
    
    
    //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_noidungch = new javax.swing.JTextField();
        A = new javax.swing.JTextField();
        B = new javax.swing.JTextField();
        C = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        D = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cb_loai = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txt_dapan = new javax.swing.JTextField();
        bt_TaoCauHoi = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nội Dung Câu Hỏi");

        jLabel2.setText("Đáp án A:");

        jLabel3.setText("Đáp án B:");

        jLabel4.setText("Đáp án D:");

        jLabel5.setText("Đáp án C:");

        jLabel7.setText("Loại Đáp Án");

        cb_loai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Loại Đáp Án", "Một Đáp Án", "Nhiều Đáp Án" }));

        jLabel8.setText("Đáp Án Đúng");

        bt_TaoCauHoi.setText("Tạo Câu Hỏi");
        bt_TaoCauHoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_TaoCauHoiActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setText("TẠO CÂU HỎI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(D)
                            .addComponent(C)
                            .addComponent(B)
                            .addComponent(A)
                            .addComponent(txt_noidungch)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(cb_loai, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(txt_dapan, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(bt_TaoCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel9)
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_noidungch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(2, 2, 2)
                .addComponent(C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_loai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dapan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(bt_TaoCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
   
    
    //======================================================================================================================================================================
    private void bt_TaoCauHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_TaoCauHoiActionPerformed
        // Sử dụng try-with-resources để đảm bảo kết nối CSDL được đóng tự động.
        int loai;
        if(cb_loai.getSelectedItem().toString().equals("Một Đáp Án"))
            loai = 1;
        else loai = 2;
        try (Connection c = ctn.c()) {
            // Chuẩn bị câu lệnh SQL INSERT để thêm một câu hỏi mới vào bảng `cauhoi`.
            PreparedStatement pst1 = c.prepareStatement(""
                + "INSERT INTO `cauhoi`(`noidungch`, `A`, `B`, `C`, `D`, `DapAn`, `loaida`) "
                + "VALUES (?,?,?,?,?,?,?)");
            
            // Lấy dữ liệu từ các ô nhập liệu và gán vào câu lệnh SQL.
            pst1.setString(1, txt_noidungch.getText());
            pst1.setString(2, A.getText());
            pst1.setString(3, B.getText());
            pst1.setString(4, C.getText());
            pst1.setString(5, D.getText());
            pst1.setString(6, txt_dapan.getText());
            pst1.setInt(7,loai);
            
            // Thực thi lệnh INSERT.
            pst1.executeUpdate();
            check = true; // Gán biến `check` thành true.
            
            // ========================= LỖI QUAN TRỌNG =========================
            // Dòng code `this.dispose()` dưới đây sẽ đóng form NGAY LẬP TỨC.
            // Điều này khiến cho `JOptionPane` ở dòng dưới có thể không hiển thị đúng cách
            // vì cửa sổ cha của nó đã bị đóng.
            // BẠN NÊN DI CHUYỂN DÒNG NÀY XUỐNG SAU KHI HIỂN THỊ THÔNG BÁO.
            this.dispose();

            // GHI CHÚ: Nội dung thông báo này bị sai ("người dùng" thay vì "câu hỏi").
            JOptionPane.showMessageDialog(null, "Thêm Câu Hỏi thành công!");
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm Câu Hỏi: " + e.getMessage());
        } 
    }//GEN-LAST:event_bt_TaoCauHoiActionPerformed
    
    
    //======================================================================================================================================================================
    public boolean kiemTraTonTai(String macau, String made) throws SQLException{
        try(Connection c = ctn.c()){ 
            PreparedStatement Pst = c.prepareStatement(""
                + "SELECT COUNT(*) FROM `cauhoi`"
                + " WHERE `MC` = ? and `MD` = ?");
            Pst.setString(1, macau);
            Pst.setString(2,made);
            ResultSet rs = Pst.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu số đếm > 0, nghĩa là đã tồn tại.
            }
        }catch(Exception e){
            
        }
        return false;
    }
    
    
    //======================================================================================================================================================================
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FormTaoCauHoi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField A;
    private javax.swing.JTextField B;
    private javax.swing.JTextField C;
    private javax.swing.JTextField D;
    private javax.swing.JButton bt_TaoCauHoi;
    private javax.swing.JComboBox<String> cb_loai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txt_dapan;
    private javax.swing.JTextField txt_noidungch;
    // End of variables declaration//GEN-END:variables
}
