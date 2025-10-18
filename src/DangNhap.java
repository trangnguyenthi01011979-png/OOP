
import TMH.CTN;
import TMH.DGD;
import TMH.TMH_Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class DangNhap extends javax.swing.JFrame {

    private CTN ctn = new CTN();
    public DangNhap() {
        initComponents();
        setLocationRelativeTo(null);
        ctn.c();
    }
    
    
    //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_Taikhoan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_Matkhau = new javax.swing.JTextField();
        bt_Dangnhap = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chương trình thi trắc nghiệm");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tài Khoản :");

        txt_Taikhoan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mật Khẩu :");

        txt_Matkhau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        bt_Dangnhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_Dangnhap.setText("Đăng Nhập");
        bt_Dangnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_DangnhapActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Đăng Nhập");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_Dangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_Taikhoan)
                                .addComponent(txt_Matkhau, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel3)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel3)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_Taikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_Matkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(bt_Dangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //======================================================================================================================================================================
    private void bt_DangnhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_DangnhapActionPerformed
        String getMatkhau = txt_Matkhau.getText();
        String getTaikhoan = txt_Taikhoan.getText();

        //  Tạo đối tượng TMH_Admin, có thể dùng để xử lý hoặc mã hóa mật khẩu
        TMH_Admin TMHAD = new TMH_Admin(getMatkhau);

        try (Connection c = ctn.c()) { // Mở kết nối đến cơ sở dữ liệu bằng đối tượng CTN
            //  Chuẩn bị câu truy vấn SQL kiểm tra thông tin đăng nhập
            PreparedStatement Pst = c.prepareStatement(
                "SELECT `MaTaiKhoan`,`TenDangNhap`, `MatKhau`, `PhanLoai` FROM `dang_nhap` "
                + "WHERE `TenDangNhap` = ? AND `MatKhau` = ?"
            );

            //  Gán dữ liệu người dùng nhập vào cho dấu "?" trong câu truy vấn
            Pst.setString(1, getTaikhoan);           // Gán tên đăng nhập
            Pst.setString(2, TMHAD.getMH());         // Gán mật khẩu (đã được xử lý nếu có mã hóa)

            //  Thực thi câu truy vấn và nhận kết quả trả về
            ResultSet rs = Pst.executeQuery();

            //  Nếu có kết quả → tài khoản tồn tại → đăng nhập thành công
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
                this.dispose(); // Đóng form đăng nhập

                //  Kiểm tra loại tài khoản để mở giao diện tương ứng
                if (rs.getString("PhanLoai").equals("GV")) { // Nếu là giáo viên
                    FormGiaoDienChinh FGDC = new FormGiaoDienChinh(true);
                    FGDC.getMTK(rs.getString("MaTaiKhoan"));
                    new DGD(FGDC); // Mở giao diện chính của giáo viên

                } else if (rs.getString("PhanLoai").equals("NT")) { // Nếu là người thi (sinh viên)
                    PreparedStatement pst = c.prepareStatement("select MD from thi where MaTaiKhoan = ?"); pst.setString(1,rs.getString("MaTaiKhoan")); ResultSet RS = pst.executeQuery();
                    if(RS.next()){
                        new DGD(new FormGiaoDienChinhUser(rs.getString("MaTaiKhoan"),RS.getInt("MD")));
                    }
                   
                    
                }

            } else {
                //  Nếu không có kết quả → sai tài khoản hoặc mật khẩu
                JOptionPane.showMessageDialog(this, "Sai tài khoản hoặc mật khẩu!");
            }

        } catch (Exception e) {
            //  Bắt lỗi nếu có vấn đề trong quá trình kết nối hoặc truy vấn
        }
    }//GEN-LAST:event_bt_DangnhapActionPerformed
    
    
    //======================================================================================================================================================================
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Dangnhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txt_Matkhau;
    private javax.swing.JTextField txt_Taikhoan;
    // End of variables declaration//GEN-END:variables
}
