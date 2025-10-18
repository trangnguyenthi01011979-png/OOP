
import TMH.CTN;
import TMH.DGD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class FormQuanLiLop extends javax.swing.JFrame {
    private Map<String,String> DuyetNganh;
    private CTN ctn = new CTN();
    private boolean isNganhLoaded = false;
    
    
    
    //======================================================================================================================================================================
    public FormQuanLiLop() {
        initComponents(); 
        
        // Thực hiện một kết nối thử đến CSDL (có thể không cần thiết ở đây).
        ctn.c(); 
        setLocationRelativeTo(null);
        // Gọi phương thức để tùy chỉnh độ rộng cột của bảng.
        setcolWidth(tb); 
        
        // Gọi phương thức để tải và hiển thị toàn bộ dữ liệu lên bảng khi form vừa mở.
        viewtable(); 
        
        // Gọi phương thức để đổ dữ liệu các lớp học vào ComboBox cb_lop.
        showComboBox(); 
        
        // Gọi phương thức để đổ dữ liệu các ngành học vào ComboBox cb_nganh.
        showcomboxnganh(); 
        
        // Thiết lập hành động mặc định khi bấm nút X (đóng cửa sổ).
        // DO_NOTHING_ON_CLOSE có nghĩa là không làm gì cả, để ta tự xử lý ở dưới.
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        // Thêm một bộ lắng nghe sự kiện cho cửa sổ.
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            // Phương thức này sẽ được gọi khi người dùng bấm nút X để đóng cửa sổ.
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // Tạo và hiển thị lại form giao diện chính.
                new FormGiaoDienChinh().setVisible(true);
                // Đóng form quản lý lớp hiện tại.
                dispose();
            }
        });
    }
    
    
    //======================================================================================================================================================================
    void setcolWidth(JTable jt){
        TableColumnModel cm = jt.getColumnModel();
        cm.getColumn(0).setMaxWidth(100); // Cột đầu tiên (index 0) có độ rộng tối đa 100 pixel.
    }

    public void viewtable(){
        try(Connection c = ctn.c()){ // Mở kết nối CSDL.
            // Chuẩn bị câu lệnh SQL để lấy Tên Lớp, Tên Ngành và đếm số lượng sinh viên trong mỗi lớp.
            // Lưu ý: Cú pháp "FROM table1, table2 WHERE..." là cú pháp JOIN cũ, nên dùng JOIN..ON.. để rõ ràng hơn.
            PreparedStatement Pst = c.prepareStatement("SELECT nganh.TenNganh, COUNT(ttnguoithi.MaTaiKhoan) AS SoLuongSinhVien " +
                                                       "FROM lop, nganh, ttnguoithi " +
                                                       "WHERE ttnguoithi.MaLop = lop.MaLop " +
                                                       "AND ttnguoithi.MaNganh = nganh.MaNganh " +
                                                       "GROUP BY  nganh.TenNganh");
            
            // Lấy mô hình dữ liệu của bảng để có thể thêm/xóa dòng.
            DefaultTableModel tm = (DefaultTableModel)tb.getModel();
            ResultSet rs = Pst.executeQuery();
            
            // Xóa tất cả các dòng hiện có trong bảng để chuẩn bị hiển thị dữ liệu mới.
            tm.setRowCount(0); 
            
            while(rs.next()){
                // Tạo một mảng đối tượng để chứa dữ liệu cho một dòng.
                Object o[] = {
                    rs.getString("TenNganh"), 
                    "", 
                    rs.getString("SoLuongSinhVien") // COUNT() trả về số, dùng getInt() sẽ tốt hơn.
                };
                // Thêm dòng mới vào bảng.
                tm.addRow(o);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
    }
    
    
    //======================================================================================================================================================================
    void showComboBox(){
        try(Connection c = ctn.c()){ // Mở kết nối CSDL và tự động đóng khi kết thúc.
            // Chuẩn bị câu lệnh SQL để lấy tất cả TenLop từ bảng lop.
            PreparedStatement ps = c.prepareStatement("select TenLop from lop");
            // Thực thi câu lệnh và nhận kết quả.
            ResultSet rs= ps.executeQuery();
            // Lặp qua từng dòng kết quả.
            while(rs.next()){
                // Thêm tên lớp vào ComboBox cb_lop.
                cb_lop.addItem(rs.getString("TenLop"));
            }
        }catch(Exception e){
            // Khối catch này đang để trống, đây là một thói quen không tốt.
            // Nên thêm thông báo lỗi ở đây để dễ dàng gỡ lỗi.
        }
    }
    
    public void showcomboxnganh(){
       try (Connection c = ctn.c()) { // Mở kết nối CSDL.
            // Xóa tất cả các mục hiện có trong ComboBox để tránh bị trùng lặp khi gọi lại.
            cb_nganh.removeAllItems();
            // Thêm một mục mặc định, mang tính hướng dẫn ở đầu danh sách.
            cb_nganh.addItem("Chọn Ngành Học");

            // Chuẩn bị câu lệnh SQL để lấy tất cả TenNganh từ bảng nganh.
            PreparedStatement pst = c.prepareStatement("SELECT TenNganh FROM nganh");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // Thêm tên ngành vào ComboBox cb_nganh.
                cb_nganh.addItem(rs.getString("TenNganh"));
            }
       } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi hiển thị combobox ngành: " + e.getMessage());
       }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cb_lop = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        bt_ThemLop = new javax.swing.JButton();
        bt_Xoalop = new javax.swing.JButton();
        txt_Lop = new javax.swing.JTextField();
        cb_nganh = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bt_XemChiTiet = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ngành", "Lớp", "Tổng Số"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb);

        jLabel1.setText("Danh sách sinh viên lớp");

        cb_lop.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Lớp" }));
        cb_lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_lopActionPerformed(evt);
            }
        });

        jLabel2.setText("Lớp");

        bt_ThemLop.setText("Thêm");
        bt_ThemLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ThemLopActionPerformed(evt);
            }
        });

        bt_Xoalop.setText("Xóa");
        bt_Xoalop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_XoalopActionPerformed(evt);
            }
        });

        cb_nganh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Ngành Học" }));
        cb_nganh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_nganhActionPerformed(evt);
            }
        });

        jLabel3.setText("Ngành");

        jLabel4.setText("Tên Lớp");

        bt_XemChiTiet.setText("Xem Chi Tiết");
        bt_XemChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_XemChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cb_nganh, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addComponent(txt_Lop, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt_ThemLop, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(bt_Xoalop, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(bt_XemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cb_nganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_ThemLop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_Xoalop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_XemChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //======================================================================================================================================================================
    private void bt_ThemLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ThemLopActionPerformed
        try(Connection c = ctn.c()){
            // 1. Lấy MaNganh dựa vào TenNganh được chọn trong ComboBox cb_nganh.
            PreparedStatement Pst2 = c.prepareStatement("select `MaNganh` from `nganh` where `TenNganh` = ?");
            Pst2.setString(1,cb_nganh.getSelectedItem().toString());
            ResultSet rs = Pst2.executeQuery();
            
            // Nếu tìm thấy mã ngành tương ứng.
            if(rs.next()){
                // 2. Thực hiện thêm lớp mới vào bảng `lop`.
                PreparedStatement Pst = c.prepareStatement("INSERT INTO `lop`(`TenLop`, `MaNganh`) VALUES (?,?)");
                Pst.setString(1,txt_Lop.getText().toUpperCase()); // Lấy tên lớp từ ô nhập liệu và viết hoa.
                Pst.setString(2,rs.getString("MaNganh"));   // Sử dụng mã ngành vừa tìm được.
                Pst.executeUpdate(); // Thực thi lệnh INSERT.
                
                JOptionPane.showMessageDialog(null, "Thêm thành công lớp học mới!");
                
                // 3. Cập nhật lại giao diện.
                viewtable(); // Tải lại bảng.
                showComboBox(); // Tải lại combobox lớp.
                showcomboxnganh(); // Tải lại combobox ngành.
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }

    }//GEN-LAST:event_bt_ThemLopActionPerformed
    

    //======================================================================================================================================================================
    private void bt_XoalopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_XoalopActionPerformed
        try(Connection c = ctn.c()){
            // Chuẩn bị câu lệnh SQL để xóa một lớp dựa vào tên lớp.
            PreparedStatement Pst = c.prepareStatement("DELETE FROM `lop` WHERE `TenLop` = ?");
            // Lấy tên lớp cần xóa từ ComboBox cb_lop.
            Pst.setString(1, cb_lop.getSelectedItem().toString());
            Pst.executeUpdate(); // Thực thi lệnh DELETE.
            
            // Cập nhật lại giao diện.
            viewtable();
            showComboBox();
        }catch(Exception e){
            // Khối catch trống, nên thêm thông báo lỗi.
        }
    }//GEN-LAST:event_bt_XoalopActionPerformed

    
    //======================================================================================================================================================================
    private void cb_nganhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_nganhActionPerformed
        try (Connection c = ctn.c()) {
            // Kiểm tra xem có mục nào được chọn không, nếu không thì thoát.
            if(cb_nganh.getSelectedIndex() == -1){
                return;
            }
            
            // Nếu người dùng chọn mục đầu tiên ("Chọn Ngành Học").
            if(cb_nganh.getSelectedIndex() == 0){
                viewtable(); // Hiển thị lại toàn bộ danh sách.
            } else { // Nếu người dùng chọn một ngành cụ thể.
                DefaultTableModel tm = (DefaultTableModel) tb.getModel();
                tm.setRowCount(0); // Xóa dữ liệu cũ trong bảng.
                
                // Chuẩn bị câu lệnh SQL để lọc danh sách lớp theo ngành đã chọn.
                // LEFT JOIN được sử dụng để hiển thị cả những lớp chưa có sinh viên.
                PreparedStatement pst = c.prepareStatement(
                        "SELECT nganh.TenNganh, COUNT(ttnguoithi.MaTaiKhoan) AS SoLuongSinhVien " +
                        "FROM nganh " +
                        "JOIN lop ON nganh.MaNganh = lop.MaNganh " +
                        "LEFT JOIN ttnguoithi ON ttnguoithi.MaLop = lop.MaLop " +
                        "WHERE nganh.TenNganh = ? " +
                        "GROUP BY  nganh.TenNganh"
                );
                // Gán tên ngành được chọn vào tham số '?' của câu lệnh SQL.
                pst.setString(1, cb_nganh.getSelectedItem().toString());
                ResultSet rs = pst.executeQuery();
                
                // Lặp và hiển thị kết quả đã lọc lên bảng.
                while (rs.next()) {
                    Object[] o = {
                        rs.getString("TenNganh"),
                        "",
                        rs.getInt("SoLuongSinhVien") // Dùng getInt() là chính xác.
                    };
                    tm.addRow(o);
                }
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lọc ngành: " + e.getMessage());
        }
    }//GEN-LAST:event_cb_nganhActionPerformed
    
    
    //======================================================================================================================================================================
    private void cb_lopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_lopActionPerformed
        try(Connection c = ctn.c()){
           DefaultTableModel tm = (DefaultTableModel)tb.getModel();
           
           // Nếu người dùng chọn mục đầu tiên ("Chọn Lớp").
           if(cb_lop.getSelectedIndex() == 0){
                viewtable(); // Hiển thị lại toàn bộ danh sách.
           } else { // Nếu người dùng chọn một lớp cụ thể.
               // Chuẩn bị câu lệnh SQL để lọc thông tin của duy nhất lớp được chọn.
                PreparedStatement Pst = c.prepareStatement(
                    "SELECT lop.TenLop, nganh.TenNganh, COUNT(ttnguoithi.MaTaiKhoan) AS SoLuongSinhVien " +
                    "FROM lop, nganh, ttnguoithi " +
                    "WHERE lop.TenLop = ? AND ttnguoithi.MaLop = lop.MaLop " +
                    "AND ttnguoithi.MaNganh = nganh.MaNganh " +
                    "GROUP BY lop.TenLop, nganh.TenNganh;"
                );
                // Gán tên lớp được chọn vào tham số '?' của câu lệnh SQL.
                Pst.setString(1,cb_lop.getSelectedItem().toString());
                ResultSet rs = Pst.executeQuery();
                tm.setRowCount(0); // Xóa dữ liệu cũ.
                
                // Lặp và hiển thị kết quả (chỉ có một dòng) lên bảng.
                while(rs.next()){
                    Object o[] = {
                       rs.getString("TenNganh"), 
                       rs.getString("TenLop"), 
                       rs.getString("SoLuongSinhVien")
                    };
                    tm.addRow(o);
                }
           } 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_cb_lopActionPerformed

    private void bt_XemChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_XemChiTietActionPerformed
       try {
        // Lấy chỉ số dòng đang được chọn
        int selectedRow = tb.getSelectedRow();
        String tenLop = "";

        // Trường hợp 1: Nếu người dùng có chọn dòng trong bảng
        if (selectedRow != -1) {
            tenLop = tb.getValueAt(selectedRow, 1).toString();
        } 
        // Trường hợp 2: Nếu người dùng chưa chọn dòng nào
        else {
            // Kiểm tra xem bảng có dữ liệu không
            if (tb.getRowCount() > 0) {
                // Lấy dòng đầu tiên trong bảng
                tenLop = tb.getValueAt(0, 1).toString();
            } else {
                return; // Dừng lại, không mở form
            }
        }

        // Gọi form chi tiết và truyền tham số vào
        new DGD(new FormQuanLiUser(tenLop));
        this.dispose(); // Đóng form hiện tại
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi mở chi tiết: " + e.getMessage());
    }
    }//GEN-LAST:event_bt_XemChiTietActionPerformed

    
    //======================================================================================================================================================================
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new FormQuanLiLop().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_ThemLop;
    private javax.swing.JButton bt_XemChiTiet;
    private javax.swing.JButton bt_Xoalop;
    private javax.swing.JComboBox<String> cb_lop;
    private javax.swing.JComboBox<String> cb_nganh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb;
    private javax.swing.JTextField txt_Lop;
    // End of variables declaration//GEN-END:variables
}
