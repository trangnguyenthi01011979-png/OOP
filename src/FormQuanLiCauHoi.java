
import TMH.CTN;
import TMH.DGD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;


public class FormQuanLiCauHoi extends javax.swing.JFrame {
    private CTN ctn = new CTN();
    private String idMD = "";
    private String idMC = "";
    
    
    //======================================================================================================================================================================
    public FormQuanLiCauHoi() {
        initComponents(); // Khởi tạo các thành phần giao diện.
        ctn.c(); // Mở kết nối CSDL.
        setcolWidth(tb); // Tùy chỉnh độ rộng cột của bảng.
        viewtable(); // Tải và hiển thị danh sách câu hỏi.
        
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
            // Chuẩn bị câu lệnh SQL để lấy tất cả các cột từ bảng `cauhoi`.
            PreparedStatement Pst = c.prepareStatement("SELECT `MC`, `noidungch`, `A`, `B`, `C`, `D`, `DapAn` FROM `cauhoi`");
            // Lấy mô hình dữ liệu của bảng `tb` để có thể thêm/xóa dòng.
            DefaultTableModel tm = (DefaultTableModel)tb.getModel();
            // Thực thi câu lệnh SQL.
            ResultSet rs = Pst.executeQuery();
            // Xóa tất cả các dòng hiện có trong bảng để chuẩn bị hiển thị dữ liệu mới.
            tm.setRowCount(0);
            // Bắt đầu vòng lặp, xử lý từng dòng kết quả trả về từ CSDL.
            while(rs.next()){
                // Tạo một mảng đối tượng để chứa dữ liệu cho một hàng trong JTable.
                Object o[] = {
                    rs.getInt("MC"), 
                    rs.getString("noidungch"), rs.getString("A"), 
                    rs.getString("B"), rs.getString("C"),
                    rs.getString("D"),rs.getString("DapAn")};
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

        jScrollPane3 = new javax.swing.JScrollPane();
        tb = new javax.swing.JTable();
        bt_ThemCauHoi = new javax.swing.JButton();
        bt_suacauhoi = new javax.swing.JButton();
        bt_XoaCauHoi = new javax.swing.JButton();
        bt_TaiLaiTrang = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Câu Hỏi", "Nội Dung Câu Hỏi", "Đáp Án A", "Đáp Án B", "Đáp Án C", "Đáp Án D", "Đáp Án Đúng"
            }
        ));
        tb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb);

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

        bt_TaiLaiTrang.setText("Làm mới");
        bt_TaiLaiTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_TaiLaiTrangActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ CÂU HỎI ĐỀ THI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_ThemCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(bt_suacauhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(bt_XoaCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_TaiLaiTrang)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(bt_TaiLaiTrang))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_ThemCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_suacauhoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_XoaCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    //======================================================================================================================================================================
    private void bt_suacauhoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_suacauhoiActionPerformed
        // Tạo một đối tượng của FormSuaCauHoi.
        // Truyền `idMC` (Mã Câu hỏi đã được lưu khi click chuột) vào constructor của form sửa.
        FormSuaCauHoi FSCH = new FormSuaCauHoi(idMC);
        // Hiển thị form sửa câu hỏi dưới dạng một dialog.
        new DGD(FSCH);
    }//GEN-LAST:event_bt_suacauhoiActionPerformed
    
    
    //======================================================================================================================================================================
    private void bt_ThemCauHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ThemCauHoiActionPerformed
        // Mở form FormTaoCauHoi để người dùng nhập câu hỏi mới.
        new DGD(new FormTaoCauHoi());
        // Đóng form quản lý câu hỏi hiện tại.
        this.dispose();
    }//GEN-LAST:event_bt_ThemCauHoiActionPerformed
    
    
    //======================================================================================================================================================================
    private void tbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMouseClicked
         // Lấy mô hình dữ liệu của bảng.
        TableModel tm = tb.getModel();
        // Lấy chỉ số của hàng mà người dùng vừa click.
        int i = tb.getSelectedRow();
        // Lấy giá trị từ cột đầu tiên (index 0) của hàng được chọn, đây chính là Mã Câu hỏi.
        // Chuyển giá trị đó thành chuỗi và lưu vào biến toàn cục `idMC`.
        idMC = tm.getValueAt(i, 0).toString();
    }//GEN-LAST:event_tbMouseClicked
    
    
    //======================================================================================================================================================================
    private void bt_XoaCauHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_XoaCauHoiActionPerformed
        try(Connection c = ctn.c()){
            // Chuẩn bị câu lệnh SQL để xóa một bản ghi khỏi bảng `cauhoi`.
            PreparedStatement Pst = c.prepareStatement("DELETE FROM `cauhoi` WHERE `MC` = ?");
            // Gán giá trị của `idMC` (đã được lưu khi click chuột) vào câu lệnh.
            // Cần chuyển đổi `idMC` từ String về Integer.
            Pst.setInt(1, Integer.parseInt(idMC));
            // Thực thi lệnh xóa.
            Pst.executeUpdate();
            // Tải lại dữ liệu trên bảng để cập nhật giao diện.
            viewtable();
        }catch(Exception e){
            // Khối catch trống.
        }
    }//GEN-LAST:event_bt_XoaCauHoiActionPerformed
    
    
    //======================================================================================================================================================================
    private void bt_TaiLaiTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_TaiLaiTrangActionPerformed
       // Gọi lại phương thức viewtable() để tải lại toàn bộ dữ liệu từ CSDL.
       viewtable();
    }//GEN-LAST:event_bt_TaiLaiTrangActionPerformed
    
    
    //======================================================================================================================================================================
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormQuanLiCauHoi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_TaiLaiTrang;
    private javax.swing.JButton bt_ThemCauHoi;
    private javax.swing.JButton bt_XoaCauHoi;
    private javax.swing.JButton bt_suacauhoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tb;
    // End of variables declaration//GEN-END:variables
}
