
import TMH.CTN;
import TMH.DGD;
import TMH.TMH_Admin;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class FormTaoTaiKhoan extends javax.swing.JFrame {

    private static final SecureRandom random = new SecureRandom();
    private CTN ctn = new CTN();
    
    
    //======================================================================================================================================================================
    public FormTaoTaiKhoan() {
        initComponents(); // Khởi tạo các thành phần giao diện.
        ctn.c(); // Mở kết nối CSDL.
        setLocationRelativeTo(null);
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
    public boolean kiemTraTonTai(String id) throws SQLException{
        try(Connection c = ctn.c()){ 
            // Chuẩn bị câu lệnh SQL để đếm số bản ghi có mã tài khoản trùng khớp.
            PreparedStatement Pst = c.prepareStatement(""
                + "SELECT COUNT(*) FROM `Dang_Nhap`"
                + " WHERE `MaTaiKhoan` = ?");
            Pst.setString(1, id);
            ResultSet rs = Pst.executeQuery();

            // Nếu có kết quả trả về.
            if (rs.next()) {
                // Trả về true nếu số đếm > 0 (tức là đã tồn tại).
                return rs.getInt(1) > 0;
            }
        }catch(Exception e){
            // Khối catch trống.
        }
        return false;
    }
    
    
    //======================================================================================================================================================================
    public static String taoSoNgauNhien10() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10)); // Thêm một số ngẫu nhiên từ 0-9.
        }
        return sb.toString();
    }
    
    
    
    //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_TenDangnhap = new javax.swing.JTextField();
        txt_TaoMatkhau = new javax.swing.JTextField();
        txt_XacNhanMatkhau = new javax.swing.JTextField();
        Cb_Vaitro = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bt_TaoTaiKhoan = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Cb_Vaitro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giảng Viên", "Sinh Viên" }));

        jLabel1.setText("Tên Đăng Nhập");

        jLabel2.setText("Tạo Mật Khẩu");

        jLabel3.setText("Xác Nhận Mật Khẩu");

        bt_TaoTaiKhoan.setText("Tạo Tài Khoản");
        bt_TaoTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_TaoTaiKhoanActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("TẠO TÀI KHOẢN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_TenDangnhap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(txt_TaoMatkhau, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cb_Vaitro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_XacNhanMatkhau, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(bt_TaoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(txt_TenDangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(txt_TaoMatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_XacNhanMatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Cb_Vaitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(bt_TaoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //======================================================================================================================================================================
    private void bt_TaoTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_TaoTaiKhoanActionPerformed
        String id = "";
        String getTenDangnhap = txt_TenDangnhap.getText();
        String getMaukhau = txt_XacNhanMatkhau.getText();
        // Gán một ngày sinh mặc định cho bản ghi placeholder trong `ttnguoithi`.
        LocalDate ngaySinh = LocalDate.of(1111,01,01);
        String vaitro ="";
        
        // GHI CHÚ: Code nên có thêm các bước kiểm tra (validation) ở đây, ví dụ:
        // 1. Kiểm tra xem các ô text có bị bỏ trống không.
        // 2. Kiểm tra xem `txt_TaoMatkhau` và `txt_XacNhanMatkhau` có khớp nhau không.
        
        // Xác định mã vai trò ("GV" hoặc "NT") dựa trên lựa chọn của ComboBox.
        if(Cb_Vaitro.getSelectedItem().toString().equals("Giảng Viên"))
            vaitro = "GV";
        else
            vaitro = "NT";
        
        // Có vẻ như đây là bước gọi một lớp tùy chỉnh để mã hóa mật khẩu.
        TMH_Admin TMHAD = new TMH_Admin(getMaukhau);
        
        
        try (Connection c = ctn.c()) {

            // Vòng lặp để đảm bảo tạo ra một ID ngẫu nhiên và DUY NHẤT.
            // Nó sẽ tiếp tục tạo ID mới cho đến khi tìm được một ID chưa tồn tại trong CSDL.
            do{
                id = taoSoNgauNhien10();
            }while (kiemTraTonTai(id));
            
            // BƯỚC 1: Thêm thông tin đăng nhập vào bảng `dang_nhap`.
            PreparedStatement pst2 = c.prepareStatement("INSERT INTO dang_nhap (MaTaiKhoan, TenDangNhap, MatKhau, PhanLoai) VALUES (?,?,?,?)");
            pst2.setString(1, id);
            pst2.setString(2, getTenDangnhap);
            pst2.setString(3, TMHAD.getMH()); // Lưu mật khẩu đã được mã hóa.
            pst2.setString(4, vaitro);
            pst2.executeUpdate();
            
            // BƯỚC 2: Thêm một bản ghi placeholder (giữ chỗ) vào bảng thông tin người thi `ttnguoithi`.
            // Điều này đảm bảo mỗi tài khoản đều có một hồ sơ thông tin cá nhân tương ứng,
            // dù cho thông tin đó ban đầu có thể trống.
            PreparedStatement pst3 = c.prepareStatement("INSERT INTO ttnguoithi (MaTaiKhoan,HoTen,Malop,MaDeThi,NgaySinh,MaNganh,SDT) VALUES (?,?,?,?,?,?,?)");
            pst3.setString(1, id);
            pst3.setString(2, ""); // Họ tên để trống
            pst3.setInt(3, 0);    // Mã lớp mặc định
            pst3.setInt(4, 0);    // Mã đề thi mặc định
            pst3.setDate(5,java.sql.Date.valueOf(ngaySinh) ); // Ngày sinh mặc định
            pst3.setString(6, "0"); // Mã ngành mặc định
            pst3.setString(7, ""); // Số điện thoại để trống
            pst3.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Thêm người dùng thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm người dùng: " + e.getMessage());
        }
    }//GEN-LAST:event_bt_TaoTaiKhoanActionPerformed
    
    
    
    //======================================================================================================================================================================
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTaoTaiKhoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Cb_Vaitro;
    private javax.swing.JButton bt_TaoTaiKhoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txt_TaoMatkhau;
    private javax.swing.JTextField txt_TenDangnhap;
    private javax.swing.JTextField txt_XacNhanMatkhau;
    // End of variables declaration//GEN-END:variables
}
