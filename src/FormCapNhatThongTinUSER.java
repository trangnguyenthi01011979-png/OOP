
import TMH.CTN;
import TMH.DGD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import javax.swing.JOptionPane;


public class FormCapNhatThongTinUSER extends javax.swing.JFrame {
    private CTN ctn = new CTN();
    private static String getMTK = "";
    
    
    //======================================================================================================================================================================
    public FormCapNhatThongTinUSER(String LTK) {
        this.getMTK = LTK;
        initComponents();
        setLocationRelativeTo(null);
        ctn.c();
        showcomboxnganh();
        showTK();
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

    // Lắng nghe sự kiện đóng cửa sổ
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // Gọi form bạn muốn mở lại
                new FormQuanLiUser().setVisible(true);

                // Đóng form hiện tại
                dispose();
            }
        });
    }
    
    
    //======================================================================================================================================================================
    private void showcomboxnganh() {
        try (Connection c = ctn.c()) { // Kết nối tới cơ sở dữ liệu
            // Chuẩn bị câu truy vấn lấy tên ngành, bỏ qua giá trị rỗng
            PreparedStatement ps2 = c.prepareStatement("select `TenNganh` from `nganh` where `TenNganh` != ? ");
            ps2.setString(1, ""); // Loại bỏ bản ghi có tên ngành trống
            ResultSet rs2 = ps2.executeQuery();
            // Xóa hết các phần tử cũ trong combobox ngành
            cb_nganh.removeAllItems();
            cb_nganh.addItem("Chọn Ngành Học"); // Thêm lựa chọn mặc định

            // Duyệt qua kết quả và thêm từng tên ngành vào combobox
            while (rs2.next()) {
                cb_nganh.addItem(rs2.getString("TenNganh"));
            }
        } catch (Exception e) {
            //  Bắt lỗi (nếu có lỗi truy vấn hoặc kết nối)
        }
        // Kiểm tra trạng thái combobox ngành để bật/tắt combobox lớp
        if (cb_nganh.getSelectedIndex() == 0) {
            cb_lop.setEnabled(false); // Nếu chưa chọn ngành → khóa combobox lớp
        } else {
            cb_lop.setEnabled(true); // Nếu đã chọn ngành → bật combobox lớp
            showComboBox(cb_nganh.getSelectedItem().toString()); // Gọi hàm hiển thị danh sách lớp theo ngành đã chọn
        }
    }

    
// Hàm gán giá trị mã tài khoản được truyền từ bên ngoài
    public void setTK(String goi) {
        this.getMTK = goi; // Lưu lại tên hoặc mã tài khoản
    }

    
// Hàm hiển thị danh sách lớp theo ngành được chọn
    private void showComboBox(String chon) {
        try (Connection c = ctn.c()) {
            // Truy vấn lấy danh sách lớp theo tên ngành
            PreparedStatement ps = c.prepareStatement(
                "select tenlop from `lop`,`nganh` where `TenNganh` = ? and `lop`.`MaNganh` = `nganh`.`MaNganh` "
            );
            ps.setString(1, chon); // Gán tên ngành người dùng chọn
            ResultSet rs = ps.executeQuery();

            // Duyệt kết quả và thêm tên lớp vào combobox lớp
            while (rs.next()) {
                cb_lop.addItem(rs.getString("TenLop"));
            }

        } catch (Exception e) {
            // Bắt lỗi (nếu có lỗi kết nối hoặc truy vấn)
        }
    }

// Hàm hiển thị danh sách tài khoản người thi (NT) lên combobox `cb_tk`
    private void showTK() {
        try (Connection c = ctn.c()) {
            // Nếu chưa truyền mã tài khoản cụ thể → hiển thị tất cả tài khoản người thi
            if (getMTK == "") {
                PreparedStatement Pst1 = c.prepareStatement(
                    "select * from `dang_nhap` where `PhanLoai` = ?"
                );
                Pst1.setString(1, "NT"); // Chỉ lấy người thi (NT)
                ResultSet rs = Pst1.executeQuery();

                // Duyệt kết quả và thêm từng tên đăng nhập vào combobox
                while (rs.next()) {
                    cb_tk.addItem(rs.getString("TenDangNhap"));
                }

            } else {
                // Nếu có mã tài khoản cụ thể → chỉ hiển thị tài khoản tương ứng
                PreparedStatement Pst1 = c.prepareStatement(
                    "select * from `dang_nhap` where `PhanLoai` = ? and `TenDangNhap` = ?"
                );
                Pst1.setString(1, "NT");
                Pst1.setString(2, getMTK);

                ResultSet rs = Pst1.executeQuery();

                cb_tk.removeAllItems(); // Xóa danh sách cũ

                // Thêm tài khoản cụ thể vào combobox
                while (rs.next()) {
                    cb_tk.addItem(rs.getString("TenDangNhap"));
                }
            }

        } catch (Exception e) {
            // Bắt lỗi khi thao tác với cơ sở dữ liệu
        }
    }
    
    
    //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_HoTen = new javax.swing.JTextField();
        cb_lop = new javax.swing.JComboBox<>();
        cb_nganh = new javax.swing.JComboBox<>();
        txt_NamSinh = new javax.swing.JTextField();
        txt_ThangSinh = new javax.swing.JTextField();
        txt_NgaySinh = new javax.swing.JTextField();
        bt_TaoTaiKhoan = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cb_tk = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_SDT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cb_nganh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_nganhActionPerformed(evt);
            }
        });

        bt_TaoTaiKhoan.setText("Thêm Thông Tin");
        bt_TaoTaiKhoan.setActionCommand("Cập Nhật Thông TIn");
        bt_TaoTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_TaoTaiKhoanActionPerformed(evt);
            }
        });

        jLabel4.setText("Họ và Tên");

        jLabel5.setText("Lớp");

        jLabel6.setText("Năm / Tháng / Ngày");

        jLabel8.setText("Ngành");

        cb_tk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Tên Tài Khoản" }));

        jLabel1.setText("Tên Tài Khoản");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("THÊM THÔNG TIN ");

        jLabel3.setText("Số Điện Thoại");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cb_nganh, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_NamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ThangSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(cb_tk, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(189, 189, 189)
                                .addComponent(jLabel5))
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(bt_TaoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jLabel2)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_tk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_nganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_NamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ThangSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bt_TaoTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //======================================================================================================================================================================
    private void bt_TaoTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_TaoTaiKhoanActionPerformed
                // Khai báo các biến cần thiết để lưu thông tin lấy từ cơ sở dữ liệu và form nhập
        String MTK = "";   // Mã tài khoản
        int ML = 0;        // Mã lớp
        String MN = "";    // Mã ngành

        // Lấy dữ liệu ngày sinh từ các ô nhập (năm, tháng, ngày)
        int year = Integer.parseInt(txt_NamSinh.getText());
        int month = Integer.parseInt(txt_ThangSinh.getText());
        int day = Integer.parseInt(txt_NgaySinh.getText());

        // Tạo đối tượng LocalDate đại diện cho ngày sinh
        LocalDate ngaySinh = LocalDate.of(year, month, day);

        try (Connection c = ctn.c()) { // Mở kết nối tới cơ sở dữ liệu thông qua phương thức c() trong đối tượng ctn

            // Kiểm tra xem biến getMTK có rỗng không để xác định cách lấy mã tài khoản
            if (getMTK == "") {
                // Nếu chưa có mã tài khoản cụ thể → lấy theo tên tài khoản được chọn trong combobox cb_tk
                PreparedStatement Pst = c.prepareStatement(
                    "select `MaTaiKhoan` from `dang_nhap` where `TenDangNhap` = ?"
                );
                Pst.setString(1, cb_tk.getSelectedItem().toString());
                ResultSet rs = Pst.executeQuery();

                // Nếu tìm thấy kết quả → gán mã tài khoản
                if (rs.next()) {
                    MTK = rs.getString("MaTaiKhoan");
                }

            } else {
                // Nếu đã có sẵn tên tài khoản (getMTK) → lấy mã tài khoản tương ứng
                PreparedStatement Pst4 = c.prepareStatement(
                    "select `MaTaiKhoan` from `dang_nhap` where `TenDangNhap` = ?"
                );
                Pst4.setString(1, getMTK);
                ResultSet rs4 = Pst4.executeQuery();

                if (rs4.next()) {
                    MTK = rs4.getString("MaTaiKhoan");
                }
            }
            // Lấy mã lớp từ bảng `lop` dựa vào tên lớp được chọn trong combobox cb_lop
            PreparedStatement Pst2 = c.prepareStatement(
                "select `MaLop` from `lop` where `TenLop` = ?"
            );
            Pst2.setString(1, cb_lop.getSelectedItem().toString());
            ResultSet rs2 = Pst2.executeQuery();

            if (rs2.next()) {
                ML = rs2.getInt("MaLop");
            }

            // Lấy mã ngành từ bảng `nganh` dựa vào tên ngành được chọn trong combobox cb_nganh
            PreparedStatement Pst3 = c.prepareStatement(
                "select `MaNganh` from `nganh` where `TenNganh` = ?"
            );
            Pst3.setString(1, cb_nganh.getSelectedItem().toString());
            ResultSet rs3 = Pst3.executeQuery();

            if (rs3.next()) {
                MN = rs3.getString("MaNganh");
            }

            // Cập nhật thông tin người thi trong bảng `ttnguoithi`
            PreparedStatement Pst1 = c.prepareStatement(
                "UPDATE `ttnguoithi` SET "
                + "`HoTen`= ? ,"
                + "`Malop`= ? ,"
                + "`NgaySinh`= ? ,"
                + "`MaNganh`= ? ,"
                + "`SDT`= ? WHERE MaTaiKhoan = ?"
            );

            // Gán các giá trị lấy được vào câu lệnh UPDATE
            Pst1.setString(1, txt_HoTen.getText());            // Họ tên
            Pst1.setInt(2, ML);                                // Mã lớp
            Pst1.setDate(3, java.sql.Date.valueOf(ngaySinh));  // Ngày sinh (chuyển LocalDate sang SQL Date)
            Pst1.setString(4, MN);                             // Mã ngành
            Pst1.setString(5, txt_SDT.getText());              // Số điện thoại
            Pst1.setString(6, MTK);                            // Mã tài khoản dùng để xác định dòng cần cập nhật

            // Thực thi câu lệnh cập nhật
            Pst1.executeUpdate();

            // Hiển thị thông báo cho người dùng biết việc cập nhật thành công
            JOptionPane.showMessageDialog(null, "Thêm Thông Tin Tài Khoản thành công!");

        } catch (Exception e) {
            // Nếu xảy ra lỗi trong quá trình xử lý hoặc kết nối CSDL, khối catch này sẽ được thực thi
            // Tuy nhiên hiện tại khối này chưa xử lý cụ thể lỗi nào (nên có thể thêm e.printStackTrace() để dễ debug)
        }

    }//GEN-LAST:event_bt_TaoTaiKhoanActionPerformed
    
    
    //======================================================================================================================================================================
    private void cb_nganhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_nganhActionPerformed
                // Kiểm tra xem người dùng có chọn ngành hợp lệ hay chưa
        if (!cb_nganh.getSelectedItem().equals("Chọn Ngành Học")) {

            // Nếu người dùng đã chọn một ngành cụ thể:
            cb_lop.setEnabled(true); // Cho phép chọn lớp (bật combobox lớp lên)

            // Lấy tên ngành mà người dùng vừa chọn trong combobox ngành
            String chon = cb_nganh.getSelectedItem().toString();

            // Xóa toàn bộ các lớp cũ trong combobox lớp để tránh hiển thị sai
            cb_lop.removeAllItems();

            // Gọi hàm showComboBox(chon) để nạp lại danh sách lớp thuộc ngành vừa chọn
            showComboBox(chon);

        } else {
            // Nếu người dùng chọn lại “Chọn Ngành Học” (nghĩa là chưa chọn ngành cụ thể):

            cb_lop.removeAllItems();  // Xóa toàn bộ danh sách lớp hiện có
            cb_lop.setEnabled(false); // Khóa combobox lớp (không cho chọn)
        }

    }//GEN-LAST:event_cb_nganhActionPerformed

    
    //======================================================================================================================================================================
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCapNhatThongTinUSER(getMTK).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_TaoTaiKhoan;
    private javax.swing.JComboBox<String> cb_lop;
    private javax.swing.JComboBox<String> cb_nganh;
    private javax.swing.JComboBox<String> cb_tk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txt_HoTen;
    private javax.swing.JTextField txt_NamSinh;
    private javax.swing.JTextField txt_NgaySinh;
    private javax.swing.JTextField txt_SDT;
    private javax.swing.JTextField txt_ThangSinh;
    // End of variables declaration//GEN-END:variables
}
