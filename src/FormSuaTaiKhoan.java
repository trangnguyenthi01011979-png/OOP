
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import TMH.CTN;
import TMH.DGD;


public class FormSuaTaiKhoan extends javax.swing.JFrame {
    private static String id;
    private CTN ctn = new CTN();
    
    
    //======================================================================================================================================================================
    public FormSuaTaiKhoan(String id) {
        initComponents(); // Khởi tạo các thành phần giao diện.
        this.id = id; // Lưu mã tài khoản vào biến toàn cục.
        ctn.c(); // Mở kết nối CSDL.
        setLocationRelativeTo(null);
        thongtincapnhat(); // Gọi phương thức để tải dữ liệu của tài khoản này lên form.
        
        // Tùy chỉnh hành vi khi đóng cửa sổ.
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        // Thêm một bộ lắng nghe sự kiện để xử lý việc đóng cửa sổ.
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // Khi đóng cửa sổ, quay trở lại giao diện quản lý tài khoản.
                new FormQuanLiTaiKhoan().setVisible(true);
                // Đóng form hiện tại.
                dispose();
            }
        });
    }
    
 
    //======================================================================================================================================================================
    public void thongtincapnhat(){
        // Sử dụng try-with-resources để đảm bảo kết nối CSDL được đóng tự động.
        try(Connection c = ctn.c() ){
            // Chuẩn bị câu lệnh SQL để lấy thông tin từ hai bảng `dang_nhap` và `ttnguoithi`.
            PreparedStatement Pst = c.prepareStatement(""
                + "select `TenDangNhap`, `MatKhau`, `HoTen`, `PhanLoai` from `dang_nhap`, `ttnguoithi` "
                + "where `dang_nhap`.`MaTaiKhoan` = `ttnguoithi`.`MaTaiKhoan` and `dang_nhap`.`MaTaiKhoan` = ?");
            Pst.setString(1, id); // Gán mã tài khoản vào câu lệnh.
            ResultSet rs = Pst.executeQuery();
            
            // Nếu tìm thấy tài khoản.
            if(rs.next()){
                // Điền thông tin lấy được vào các thành phần trên giao diện.
                txt_TenTaikhoan.setText(rs.getString("TenDangNhap"));
                txt_Matkhau.setText(rs.getString("MatKhau")); // GHI CHÚ: Mật khẩu hiển thị ở đây có thể là dạng đã mã hóa.
                lb_TenNguoidung.setText(rs.getString("HoTen"));
                // Kiểm tra vai trò và chọn mục tương ứng trong ComboBox.
                if(rs.getString("PhanLoai").equals("NT"))
                    cb_vaitro.setSelectedItem("Sinh Viên");
                // GHI CHÚ: Thiếu trường hợp `else if` để xử lý vai trò "Giảng Viên" (GV).
            }
        }catch(Exception e){
            // Khối catch trống.
        }
    }
    
    
    //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_TenTaikhoan = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_Matkhau = new javax.swing.JTextField();
        cb_vaitro = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lb_TenNguoidung = new javax.swing.JLabel();
        bt_CapNhat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tên Đăng Nhập");

        jLabel2.setText("Mật Khẩu");

        cb_vaitro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giảng Viên", "Sinh Viên" }));

        jLabel4.setText("Vai Trò");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("SỬA TÀI KHOẢN");

        lb_TenNguoidung.setText("jLabel3");

        bt_CapNhat.setText("CẬP NHẬT THÔNG TIN");
        bt_CapNhat.setToolTipText("");
        bt_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_CapNhatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lb_TenNguoidung)
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TenTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(txt_Matkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(cb_vaitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(bt_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lb_TenNguoidung)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TenTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Matkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_vaitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(bt_CapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    //======================================================================================================================================================================
    private void bt_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_CapNhatActionPerformed
        String vaitro = "NT"; // Mặc định vai trò là "NT" (Người Thi / Sinh Viên).
        
        try(Connection c = ctn.c()){
            // Chuẩn bị câu lệnh SQL UPDATE để cập nhật bảng `dang_nhap`.
            PreparedStatement Pst = c.prepareStatement(""
                + "UPDATE `dang_nhap` SET "
                + "`TenDangNhap`= ? ,"
                + "`MatKhau`= ? ,"
                + "`PhanLoai`= ? "
                + "WHERE `MaTaiKhoan` = ?");
            
            // GHI CHÚ VỀ LỖI LOGIC:
            // Điều kiện `if(txt_TenTaikhoan != null)` sẽ luôn đúng vì đối tượng JTextField đã được tạo.
            // Để kiểm tra người dùng có nhập liệu hay không, nên dùng: `!txt_TenTaikhoan.getText().isEmpty()`
            if(txt_TenTaikhoan != null && txt_Matkhau != null){
                // Kiểm tra vai trò được chọn trong ComboBox.
                if(cb_vaitro.getSelectedItem().toString().equals("Giảng Viên"))
                    vaitro = "GV";
                
                // ========================= LỖI QUAN TRỌNG =========================
                // Khối `else` dưới đây bị đặt sai vị trí.
                // Nó làm cho code cập nhật chỉ được thực thi khi vai trò được chọn KHÔNG PHẢI là "Giảng Viên".
                // Nếu bạn chọn "Giảng Viên", `vaitro` sẽ được đổi thành "GV" và sau đó không có gì xảy ra cả.
                // BẠN CẦN XÓA `else` VÀ ĐƯA KHỐI LỆNH BÊN DƯỚI RA NGOÀI.
                else
                    // Các dòng code này chỉ chạy khi vai trò là "Sinh Viên".
                    Pst.setString(1, txt_TenTaikhoan.getText());
                    // Dòng này có vẻ đang gọi một lớp để mã hóa mật khẩu trước khi lưu.
                    Pst.setString(2, new TMH.TMH_Admin(txt_Matkhau.getText()).getMH());
                    Pst.setString(3, vaitro); // Gán vai trò (NT hoặc GV).
                    Pst.setString(4, id); // Dùng `id` làm điều kiện WHERE.
                    Pst.executeUpdate(); // Thực thi lệnh UPDATE.
                    JOptionPane.showMessageDialog(null, "Cập Nhật Thành Công");
            }
            // Sau khi cập nhật, quay về form quản lý tài khoản.
            FormQuanLiTaiKhoan FQLTK = new FormQuanLiTaiKhoan();
            new DGD(FQLTK);
            this.dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Cập Nhật Không Thành Công");
        }
    }//GEN-LAST:event_bt_CapNhatActionPerformed

    
    //======================================================================================================================================================================
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormSuaTaiKhoan(id).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_CapNhat;
    private javax.swing.JComboBox<String> cb_vaitro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lb_TenNguoidung;
    private javax.swing.JTextField txt_Matkhau;
    private javax.swing.JTextField txt_TenTaikhoan;
    // End of variables declaration//GEN-END:variables
}
