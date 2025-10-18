
import TMH.CTN;
import TMH.DGD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;



public class FormQuanLiTaiKhoan extends javax.swing.JFrame {
    private  String idtk =""; 
    private CTN ctn = new CTN();
    
    
    //======================================================================================================================================================================
     public FormQuanLiTaiKhoan() {
        initComponents(); // Khởi tạo các thành phần giao diện.
        ctn.c(); // Mở kết nối CSDL.
        setLocationRelativeTo(null);
        viewtable(); // Tải và hiển thị danh sách tài khoản.
        
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
    void setcolWidth(JTable jt){
        TableColumnModel cm = jt.getColumnModel();
        cm.getColumn(0).setMaxWidth(100);
    }
    
    public void viewtable(){
        // Sử dụng try-with-resources để đảm bảo kết nối CSDL được đóng tự động.
        try(Connection c = ctn.c()){
            // Chuẩn bị câu lệnh SQL để lấy các tài khoản có phân loại là "NT" (Người Thi).
            PreparedStatement Pst = c.prepareStatement("select * from `dang_nhap` where `phanloai` = ?");
            Pst.setString(1,"NT"); // Gán giá trị "NT" vào tham số.
            // Lấy mô hình dữ liệu của bảng `tb`.
            DefaultTableModel tm = (DefaultTableModel)tb.getModel();
            // Thực thi câu lệnh SQL.
            ResultSet rs = Pst.executeQuery();
            // Xóa tất cả các dòng hiện có trong bảng để chuẩn bị hiển thị dữ liệu mới.
            tm.setRowCount(0);
            // Bắt đầu vòng lặp, xử lý từng dòng kết quả trả về từ CSDL.
            while(rs.next()){
                // Tạo một mảng đối tượng để chứa dữ liệu cho một hàng trong JTable.
                Object o[] = {
                    rs.getString("MaTaiKhoan"),rs.getString("TenDangNhap"), rs.getString("MatKhau"), rs.getString("PhanLoai")};
                // Thêm hàng mới vào bảng.
                tm.addRow(o);
            }
        }catch(Exception e){
            // Khối catch trống, nếu có lỗi sẽ không có thông báo.
        }
    }
    
    
    //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_Taotaikhoan = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb = new javax.swing.JTable();
        bt_Taotaikhoan1 = new javax.swing.JButton();
        bt_XoaTaikhoan = new javax.swing.JButton();
        bt_SuaTaikhoan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        bt_Taotaikhoan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_Taotaikhoan.setText("Tạo Tài Khoản");
        bt_Taotaikhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_TaotaikhoanActionPerformed(evt);
            }
        });

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Tài Khoản", "Tên Tài Khoản", "Mật Khẩu", "Phân loại"
            }
        ));
        tb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb);

        bt_Taotaikhoan1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_Taotaikhoan1.setText("Tạo Tài Khoản");
        bt_Taotaikhoan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_Taotaikhoan1ActionPerformed(evt);
            }
        });

        bt_XoaTaikhoan.setText("Xóa Tài Khoản");
        bt_XoaTaikhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_XoaTaikhoanActionPerformed(evt);
            }
        });

        bt_SuaTaikhoan.setText("Sửa Tài Khoản");
        bt_SuaTaikhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_SuaTaikhoanActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ TÀI KHOẢN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(bt_Taotaikhoan1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_SuaTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(bt_XoaTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 189, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(297, 297, 297)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_Taotaikhoan1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_XoaTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_SuaTaikhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    //======================================================================================================================================================================
    private void bt_TaotaikhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_TaotaikhoanActionPerformed
        FormTaoTaiKhoan FTK = new FormTaoTaiKhoan();
        new DGD(FTK);
        this.dispose();
    }//GEN-LAST:event_bt_TaotaikhoanActionPerformed
    
    
    //======================================================================================================================================================================
    private void bt_Taotaikhoan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_Taotaikhoan1ActionPerformed
         // Mở form FormTaoTaiKhoan để người dùng nhập thông tin tài khoản mới.
        FormTaoTaiKhoan FTK = new FormTaoTaiKhoan();
        new DGD(FTK);
        // Đóng form quản lý tài khoản hiện tại.
        this.dispose();
    }//GEN-LAST:event_bt_Taotaikhoan1ActionPerformed
    
    
    //======================================================================================================================================================================
    private void bt_SuaTaikhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_SuaTaikhoanActionPerformed
        // Lấy mô hình dữ liệu của bảng.
        TableModel tm = tb.getModel();
        // Lấy chỉ số của hàng đang được chọn.
        int i = tb.getSelectedRow();
        // Mở form FormSuaTaiKhoan.
        // Truyền Mã Tài Khoản (lấy từ cột đầu tiên của hàng được chọn) vào constructor của form sửa.
        FormSuaTaiKhoan FSTK = new FormSuaTaiKhoan(tm.getValueAt(i, 0).toString());
        new DGD(FSTK);
        // Đóng form hiện tại.
        this.dispose();
    }//GEN-LAST:event_bt_SuaTaikhoanActionPerformed
    
    
    //======================================================================================================================================================================
    private void tbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMouseClicked
         // Lấy mô hình dữ liệu của bảng.
        TableModel tm = tb.getModel();
        // Lấy chỉ số của hàng mà người dùng vừa click.
        int i = tb.getSelectedRow();
        // Lấy giá trị từ cột đầu tiên (Mã Tài Khoản) của hàng được chọn.
        // Chuyển giá trị đó thành chuỗi và lưu vào biến toàn cục `idtk`.
        idtk = tm.getValueAt(i, 0).toString();
    }//GEN-LAST:event_tbMouseClicked
    
    
    //======================================================================================================================================================================
    private void bt_XoaTaikhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_XoaTaikhoanActionPerformed
        try(Connection c =ctn.c() ){
            // Chuẩn bị câu lệnh SQL để xóa từ nhiều bảng (cú pháp này hoạt động trên MySQL).
            // Lệnh này sẽ xóa đồng thời bản ghi trong `dang_nhap` và `ttnguoithi`
            // có cùng `MaTaiKhoan`.
            PreparedStatement Pst = c.prepareStatement("DELETE FROM `dang_nhap`,`ttnguoithi` "
                                                     + "WHERE `dang_nhap`.`MaTaiKhoan` = ? "
                                                     + "and `dang_nhap`.`MaTaiKhoan` = `ttnguoithi`.`MaTaiKhoan`");
            // Gán giá trị của `idtk` (đã được lưu khi click chuột) vào câu lệnh.
            Pst.setString(1,idtk);
            // Thực thi lệnh xóa.
            Pst.executeUpdate();
            // Tải lại dữ liệu trên bảng để cập nhật giao diện.
            viewtable();
            // Hiển thị thông báo thành công.
            JOptionPane.showMessageDialog(null, "Đã xóa tài khoản người dùng!");
        }catch(Exception e){
            // Khối catch trống.
        }
    }//GEN-LAST:event_bt_XoaTaikhoanActionPerformed
    
    
    //======================================================================================================================================================================
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormQuanLiTaiKhoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_SuaTaikhoan;
    private javax.swing.JButton bt_Taotaikhoan;
    private javax.swing.JButton bt_Taotaikhoan1;
    private javax.swing.JButton bt_XoaTaikhoan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable tb;
    // End of variables declaration//GEN-END:variables
}
