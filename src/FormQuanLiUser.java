
import TMH.CTN;
import TMH.DGD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class FormQuanLiUser extends javax.swing.JFrame {
    private CTN ctn = new CTN();
    private boolean goi = false;
    
    
    //======================================================================================================================================================================
    public FormQuanLiUser() {
        initComponents(); // Khởi tạo các thành phần giao diện.
        ctn.c(); // Mở kết nối CSDL.
        showComboBox(); // Tải dữ liệu cho ComboBox Lớp (bộ lọc).
        showcomboxnganh(); // Tải dữ liệu cho ComboBox Ngành (bộ lọc).
        setcolWidth(tb); // Tùy chỉnh độ rộng cột của bảng.
        viewtable(); // Tải và hiển thị danh sách người dùng ban đầu.
        
        // Tùy chỉnh hành vi khi đóng cửa sổ.
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        // Thêm một bộ lắng nghe sự kiện để xử lý việc đóng cửa sổ.
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // Khi đóng cửa sổ, quay trở lại giao diện chính của admin.
                new FormGiaoDienChinh().setVisible(true);
                // Đóng form hiện tại.
                dispose();
            }
        });
    }

    
    //======================================================================================================================================================================
    void showComboBox(){
        try(Connection c = ctn.c()){
            PreparedStatement ps = c.prepareStatement("select TenLop from `lop` where `TenLop` != ?");ps.setString(1,"");
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                cb_lop1.addItem(rs.getString("TenLop"));
            }
            
        }catch(Exception e){
            // Khối catch trống.
        }
    }
    
    public void showcomboxnganh(){
        try(Connection c = ctn.c()){
            PreparedStatement Pst = c.prepareStatement("select `TenNganh` from `nganh` where `TenNganh` != ?");Pst.setString(1,"");
            ResultSet rs2 = Pst.executeQuery();
            while(rs2.next()){
                cb_nganh1.addItem(rs2.getString("TenNganh"));
            }
        }catch(Exception e){
            // Khối catch trống.
        }
    }
    
    
    //======================================================================================================================================================================
    void setcolWidth(JTable jt){
        TableColumnModel cm = jt.getColumnModel();
        cm.getColumn(0).setMaxWidth(100);
    }
    
    public void viewtable(){
        try(Connection c = ctn.c()){
            // Chuẩn bị câu lệnh SQL sử dụng cú pháp join cũ (dùng dấu phẩy).
            String sql = "SELECT `dang_nhap`.MaTaiKhoan, `dang_nhap`.TenDangNhap,`ttnguoithi`.HoTen, `ttnguoithi`.NgaySinh, `ttnguoithi`.SDT,`lop`.TenLop, `nganh`.TenNganh " +
                         "FROM ttnguoithi, dang_nhap, lop, nganh "
                       + "Where `ttnguoithi`.`MaTaiKhoan` = `dang_nhap`.`MaTaiKhoan` and `ttnguoithi`.`MaNganh` = `nganh`.`MaNganh` and `ttnguoithi`.`Malop` = `lop`.`MaLop` and `dang_nhap`.phanloai = ? ";
            PreparedStatement pst = c.prepareStatement(sql); pst.setString(1,"NT");
            ResultSet rs = pst.executeQuery();

            DefaultTableModel tm = (DefaultTableModel) tb.getModel();
            tm.setRowCount(0); // Xóa dữ liệu cũ.
            // Lặp qua kết quả và thêm từng dòng vào bảng.
            while(rs.next()){
                tm.addRow(new Object[]{
                    rs.getString("MaTaiKhoan"),
                    rs.getString("TenDangNhap"),
                    rs.getString("HoTen"),
                    rs.getString("SDT"),
                    rs.getString("NgaySinh"),
                    rs.getString("TenLop"),
                    rs.getString("TenNganh")
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_ThemCauHoi = new javax.swing.JButton();
        bt_suacauhoi = new javax.swing.JButton();
        bt_XoaCauHoi = new javax.swing.JButton();
        bt_dong = new javax.swing.JButton();
        bt_ThemCauHoi1 = new javax.swing.JButton();
        bt_suacauhoi1 = new javax.swing.JButton();
        bt_XoaCauHoi1 = new javax.swing.JButton();
        bt_dong1 = new javax.swing.JButton();
        cb_nganh = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cb_lop = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb = new javax.swing.JTable();
        bt_ThemSV = new javax.swing.JButton();
        bt_CapNhatTT = new javax.swing.JButton();
        bt_XoaSV = new javax.swing.JButton();
        cb_nganh1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cb_lop1 = new javax.swing.JComboBox<>();
        qltk = new javax.swing.JButton();

        bt_ThemCauHoi.setText("Thêm");
        bt_ThemCauHoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ThemCauHoiActionPerformed(evt);
            }
        });

        bt_suacauhoi.setText("Sửa");
        bt_suacauhoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_suacauhoiActionPerformed(evt);
            }
        });

        bt_XoaCauHoi.setText("Xóa");
        bt_XoaCauHoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_XoaCauHoiActionPerformed(evt);
            }
        });

        bt_dong.setText("Đóng");
        bt_dong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_dongActionPerformed(evt);
            }
        });

        bt_ThemCauHoi1.setText("Thêm");
        bt_ThemCauHoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ThemCauHoi1ActionPerformed(evt);
            }
        });

        bt_suacauhoi1.setText("Sửa");
        bt_suacauhoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_suacauhoi1ActionPerformed(evt);
            }
        });

        bt_XoaCauHoi1.setText("Xóa");
        bt_XoaCauHoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_XoaCauHoi1ActionPerformed(evt);
            }
        });

        bt_dong1.setText("Đóng");
        bt_dong1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_dong1ActionPerformed(evt);
            }
        });

        cb_nganh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Ngành Học" }));
        cb_nganh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_nganhActionPerformed(evt);
            }
        });

        jLabel3.setText("Ngành");

        jLabel2.setText("Lớp");

        cb_lop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Lớp" }));
        cb_lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_lopActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Tài Khoản", "Tên Tài Khoản", "Họ Tên Sinh Viên", "Số Điện Thoại", "Năm Sinh", "Lớp", "Ngành Học"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb);
        if (tb.getColumnModel().getColumnCount() > 0) {
            tb.getColumnModel().getColumn(0).setHeaderValue("Mã Tài Khoản");
            tb.getColumnModel().getColumn(1).setHeaderValue("Tên Tài Khoản");
            tb.getColumnModel().getColumn(2).setHeaderValue("Họ Tên Sinh Viên");
            tb.getColumnModel().getColumn(3).setHeaderValue("Số Điện Thoại");
            tb.getColumnModel().getColumn(4).setHeaderValue("Năm Sinh");
            tb.getColumnModel().getColumn(5).setHeaderValue("Lớp");
            tb.getColumnModel().getColumn(6).setHeaderValue("Ngành Học");
        }

        bt_ThemSV.setText("Thêm");
        bt_ThemSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ThemSVActionPerformed(evt);
            }
        });

        bt_CapNhatTT.setText("Sửa");
        bt_CapNhatTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_CapNhatTTActionPerformed(evt);
            }
        });

        bt_XoaSV.setText("Xóa");
        bt_XoaSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_XoaSVActionPerformed(evt);
            }
        });

        cb_nganh1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Ngành Học" }));
        cb_nganh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_nganh1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Ngành");

        jLabel5.setText("Lớp");

        cb_lop1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Lớp" }));
        cb_lop1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_lop1ActionPerformed(evt);
            }
        });

        qltk.setText("Quản lý tài khoản");
        qltk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qltkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt_ThemSV, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(bt_CapNhatTT, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(bt_XoaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cb_nganh1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cb_lop1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))))
                        .addGap(208, 208, 208)
                        .addComponent(qltk, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cb_nganh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bt_ThemSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_CapNhatTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bt_XoaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_lop1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qltk, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_ThemCauHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ThemCauHoiActionPerformed
       
    }//GEN-LAST:event_bt_ThemCauHoiActionPerformed

    private void bt_suacauhoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_suacauhoiActionPerformed
        
    }//GEN-LAST:event_bt_suacauhoiActionPerformed

    private void bt_XoaCauHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_XoaCauHoiActionPerformed
        
    }//GEN-LAST:event_bt_XoaCauHoiActionPerformed

    private void bt_dongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_dongActionPerformed
        
    }//GEN-LAST:event_bt_dongActionPerformed

    private void bt_ThemCauHoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ThemCauHoi1ActionPerformed
        
    }//GEN-LAST:event_bt_ThemCauHoi1ActionPerformed

    private void bt_suacauhoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_suacauhoi1ActionPerformed
        
    }//GEN-LAST:event_bt_suacauhoi1ActionPerformed

    private void bt_XoaCauHoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_XoaCauHoi1ActionPerformed
       
    }//GEN-LAST:event_bt_XoaCauHoi1ActionPerformed

    private void bt_dong1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_dong1ActionPerformed
       
    }//GEN-LAST:event_bt_dong1ActionPerformed
    
    
    //======================================================================================================================================================================
    private void bt_ThemSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ThemSVActionPerformed
        // Logic này có vẻ hơi lạ: nó mở form "Cập nhật thông tin".
        // Nếu không có dòng nào được chọn, nó mở form với chuỗi rỗng -> tạo mới.
        if(tb.getSelectedRow() == -1 ){
            FormCapNhatThongTinUSER FCNTTU = new FormCapNhatThongTinUSER("");
            new DGD(FCNTTU);
            this.dispose();
        }else{
            // Nếu có dòng được chọn, nó cũng mở form cập nhật và truyền vào Tên tài khoản.
            FormCapNhatThongTinUSER FCNTTU = new FormCapNhatThongTinUSER(tb.getValueAt(tb.getSelectedRow(), 1).toString());
            new DGD(FCNTTU);
            this.dispose();
        }
    }//GEN-LAST:event_bt_ThemSVActionPerformed
    
    
    //======================================================================================================================================================================
    private void bt_CapNhatTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_CapNhatTTActionPerformed
        try{
            // Nếu không có dòng nào được chọn, thông báo cho người dùng.
            if(tb.getSelectedRow() == -1 ){
                JOptionPane.showMessageDialog(null, "Chọn thông tin cần sửa");
            }else{
                // Nếu có, mở form "Sửa thông tin" và truyền vào Mã tài khoản (cột 0).
                FormSuaThongTinUser FSTTU = new FormSuaThongTinUser(tb.getValueAt(tb.getSelectedRow(), 0).toString());
                new DGD(FSTTU);
                this.dispose();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_bt_CapNhatTTActionPerformed

    private void bt_XoaSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_XoaSVActionPerformed

    }//GEN-LAST:event_bt_XoaSVActionPerformed

    private void cb_nganhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_nganhActionPerformed

    }//GEN-LAST:event_cb_nganhActionPerformed

    private void cb_lopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_lopActionPerformed
       
    }//GEN-LAST:event_cb_lopActionPerformed
    
    
    //======================================================================================================================================================================
    private void cb_nganh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_nganh1ActionPerformed
        try(Connection c = ctn.c()){
            DefaultTableModel tm = (DefaultTableModel)tb.getModel();
            // Nếu chọn mục đầu tiên ("Chọn Ngành Học"), hiển thị lại toàn bộ danh sách.
            if(cb_nganh1.getSelectedIndex() == 0){
                viewtable();
            }else{
                // Nếu chọn một ngành cụ thể, thực hiện truy vấn để lọc danh sách.
                PreparedStatement Pst = c.prepareStatement(
                    "select `dang_nhap`.MaTaiKhoan, TenDangNhap, HoTen, SDT, NgaySinh, TenLop, TenNganh from `ttnguoithi`, `lop`, `nganh`,`dang_nhap` "
                    + "where `TenNganh` = ?  "
                    + "and `ttnguoithi`.`MaTaiKhoan` = `dang_nhap`.`MaTaiKhoan` "
                    + "and `ttnguoithi`.`MaNganh` = `nganh`.`MaNganh` "
                    + "and `ttnguoithi`.`Malop` = `lop`.`MaLop` "
                ); 
                Pst.setString(1,cb_nganh1.getSelectedItem().toString()); 
                cb_lop1.setSelectedIndex(0); // Reset bộ lọc lớp về mặc định.
                ResultSet rs = Pst.executeQuery();
                tm.setRowCount(0); // Xóa dữ liệu cũ.
                // Lặp và điền dữ liệu đã lọc vào bảng.
                while(rs.next()){
                    tm.addRow(new Object[]{rs.getString("MaTaiKhoan"),
                        rs.getString("TenDangNhap"),
                        rs.getString("HoTen"),
                        rs.getString("SDT"),
                        rs.getString("NgaySinh"),
                        rs.getString("TenLop"),
                        rs.getString("TenNganh")});
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_cb_nganh1ActionPerformed
    
    
    //======================================================================================================================================================================
    private void cb_lop1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_lop1ActionPerformed
        try(Connection c = ctn.c()){
            DefaultTableModel tm = (DefaultTableModel)tb.getModel();
            // Nếu chọn mục đầu tiên ("Chọn Lớp"), hiển thị lại toàn bộ danh sách.
            if(cb_lop1.getSelectedIndex() == 0){
                viewtable();
            }else{
                // Nếu chọn một lớp cụ thể, thực hiện truy vấn để lọc danh sách.
                PreparedStatement Pst = c.prepareStatement(
                    "select `ttnguoithi`.MaTaiKhoan, TenDangNhap, HoTen, SDT, NgaySinh, TenLop, TenNganh from `ttnguoithi`, `lop`, `nganh`, `dang_nhap` "
                    + "where `TenLop` = ? and `dang_nhap`.`MaTaiKhoan` = `ttnguoithi`.`MaTaiKhoan` and `ttnguoithi`.`MaNganh` = `nganh`.`MaNganh` and `ttnguoithi`.`Malop` = `lop`.`MaLop`"
                ); 
                Pst.setString(1,cb_lop1.getSelectedItem().toString());
                cb_nganh1.setSelectedIndex(0); // Reset bộ lọc ngành về mặc định.
                ResultSet rs = Pst.executeQuery();
                tm.setRowCount(0); // Xóa dữ liệu cũ.
                // Lặp và điền dữ liệu đã lọc vào bảng.
                while(rs.next()){
                     tm.addRow(new Object[]{rs.getString("MaTaiKhoan"),
                        rs.getString("TenDangNhap"),
                        rs.getString("HoTen"),
                        rs.getString("SDT"),
                        rs.getString("NgaySinh"),
                        rs.getString("TenLop"),
                        rs.getString("TenNganh")});
                }
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_cb_lop1ActionPerformed
    
    
    //======================================================================================================================================================================
    private void tbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMouseClicked
        // Logic này có vẻ bị lỗi và biến `goi` không được sử dụng ở đâu khác.
        // `toString()` trên một giá trị null sẽ gây ra NullPointerException.
        // So sánh chuỗi bằng `==` cũng không chính xác.
        if(tb.getValueAt(tb.getSelectedRow(), 1).toString() == null){
            goi = false;
        }else{
            goi = true;
        }
    }//GEN-LAST:event_tbMouseClicked

    private void qltkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qltkActionPerformed
        new DGD(new FormQuanLiTaiKhoan());
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_qltkActionPerformed

    
    //======================================================================================================================================================================
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormQuanLiUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_CapNhatTT;
    private javax.swing.JButton bt_ThemCauHoi;
    private javax.swing.JButton bt_ThemCauHoi1;
    private javax.swing.JButton bt_ThemSV;
    private javax.swing.JButton bt_XoaCauHoi;
    private javax.swing.JButton bt_XoaCauHoi1;
    private javax.swing.JButton bt_XoaSV;
    private javax.swing.JButton bt_dong;
    private javax.swing.JButton bt_dong1;
    private javax.swing.JButton bt_suacauhoi;
    private javax.swing.JButton bt_suacauhoi1;
    private javax.swing.JComboBox<String> cb_lop;
    private javax.swing.JComboBox<String> cb_lop1;
    private javax.swing.JComboBox<String> cb_nganh;
    private javax.swing.JComboBox<String> cb_nganh1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton qltk;
    private javax.swing.JTable tb;
    // End of variables declaration//GEN-END:variables
}
