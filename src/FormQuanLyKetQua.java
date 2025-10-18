
import TMH.CTN;
import TMH.ChiTietLichSu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class FormQuanLyKetQua extends javax.swing.JFrame {
    private List<ChiTietLichSu> CTLS = new ArrayList<>();
    private CTN ctn = new CTN();

    
    //======================================================================================================================================================================
    public FormQuanLyKetQua() {
        initComponents(); // Khởi tạo các thành phần giao diện (nút, bảng, label,...).
        ctn.c(); // Thực hiện kết nối thử đến CSDL.
        setLocationRelativeTo(null);
        setcolWidth(tb); // Tùy chỉnh độ rộng cột của bảng.
        cbNganh();
        cbLop();
        cbDeThi();// Tải dữ liệu cho ComboBox Lớp.
         xemKETQUA(null, null, null); // Tải và hiển thị tất cả kết quả thi lên bảng.
        
        // Tùy chỉnh hành vi khi đóng cửa sổ.
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        
        // Thêm một bộ lắng nghe sự kiện để xử lý việc đóng cửa sổ.
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                new FormGiaoDienChinh().setVisible(true); // Mở lại form chính.
                dispose(); // Đóng form hiện tại.
            }
        });
    }
    
    
    //======================================================================================================================================================================
    void setcolWidth(JTable jt){
        TableColumnModel cm = jt.getColumnModel();
        cm.getColumn(0).setMaxWidth(100); // Cột đầu tiên (index 0) có độ rộng tối đa 100 pixel.
    }
    
    private void xemKETQUA(String tenNganh, String tenLop, String maDeThi) {
        try (Connection c = ctn.c()) {
            StringBuilder sql = new StringBuilder(
                "SELECT lichsuthi.MaDe AS MD, ttnguoithi.MaTaiKhoan, ttnguoithi.HoTen, " +
                "lop.TenLop, COALESCE(lichsuthi.SoCauDung, 0) AS SoCauDung, " +
                "COALESCE(lichsuthi.KQ, 0) AS KQ, dethi.NoiDungDeThi, lichsuthi.NgayThi, lichsuthi.TTCT " +
                "FROM ttnguoithi " +
                "LEFT JOIN lichsuthi ON ttnguoithi.MaTaiKhoan = lichsuthi.MaTaiKhoan " +
                "LEFT JOIN lop ON ttnguoithi.MaLop = lop.MaLop " +
                "LEFT JOIN nganh ON ttnguoithi.MaNganh = nganh.MaNganh " +
                "LEFT JOIN dethi ON lichsuthi.MaDe = dethi.MD " +
                "WHERE 1=1 "
            );

            List<Object> params = new ArrayList<>();

            if (tenNganh != null && !tenNganh.equals("Chọn Ngành Học")) {
                sql.append(" AND nganh.TenNganh = ?");
                params.add(tenNganh);
            }

            if (tenLop != null && !tenLop.equals("Chọn Lớp Học")) {
                sql.append(" AND lop.TenLop = ?");
                params.add(tenLop);
            }

            if (maDeThi != null && !maDeThi.equals("Chọn Đề Thi")) {
                sql.append(" AND lichsuthi.MaDe = ?");
                params.add(maDeThi);
            }


            PreparedStatement pst = c.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                pst.setObject(i + 1, params.get(i));
            }

            ResultSet rs = pst.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) tb.getModel();
            tm.setRowCount(0);
            CTLS.clear();

            while (rs.next()) {
                tm.addRow(new Object[]{
                    rs.getInt("MD"),
                    rs.getString("MaTaiKhoan"),
                    rs.getString("HoTen"),
                    rs.getString("TenLop"),
                    rs.getInt("SoCauDung"),
                    String.format("%.1f", rs.getFloat("KQ")), // làm tròn 1 số thập phân
                    rs.getString("NoiDungDeThi"), // hiển thị tên/nội dung đề
                    rs.getDate("NgayThi")          // ngày thi
                });

                CTLS.add(new ChiTietLichSu(rs.getString("TTCT")));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    //======================================================================================================================================================================
    private void cbNganh(){
        try(Connection c = ctn.c()){
            PreparedStatement Pst = c.prepareStatement("select * from nganh");
            ResultSet rs = Pst.executeQuery();
            while(rs.next()){
                cb_Nganh.addItem(rs.getString("TenNganh"));
            }
        }catch(Exception e){
            // Khối catch trống.
        }
    }
    
    private void cbLop() {
        try (Connection c = ctn.c()) {
            cb_lop.removeAllItems();
            cb_lop.addItem("Chọn Lớp Học");
            PreparedStatement pst = c.prepareStatement("SELECT TenLop FROM lop");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cb_lop.addItem(rs.getString("TenLop")); // ✅ tên cột chính xác
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private void cbDeThi() {
        try (Connection c = ctn.c()) {
            cb_DeThi.removeAllItems();
            cb_DeThi.addItem("Chọn Đề Thi");
            PreparedStatement pst = c.prepareStatement("SELECT MD, NoidungDeThi FROM dethi");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cb_DeThi.addItem(rs.getInt("MD")+"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txp_ttct = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cb_Nganh = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cb_lop = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cb_DeThi = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Đề", "Mã Tài Khoản", "Họ Tên", "Lớp", "Số Câu Đúng", "Kết Quả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true
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

        jScrollPane2.setViewportView(txp_ttct);

        jLabel1.setText("Danh Sách Kết Quả Bài Thi ");
        jLabel1.setToolTipText("");

        jLabel2.setText("Thông Tin Chi Tiết Đề Thi");
        jLabel2.setToolTipText("");

        cb_Nganh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Ngành Học" }));
        cb_Nganh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_NganhActionPerformed(evt);
            }
        });

        jLabel3.setText("Theo Lớp Học");

        jLabel4.setText("Theo Ngành Học");

        cb_lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_lopActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("DANH SÁCH QUẢN LÝ KẾT QUẢ THI");

        cb_DeThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_DeThiActionPerformed(evt);
            }
        });

        jLabel6.setText("Đề Thi");

        jButton1.setText("Làm Lại");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Làm Bài Lại");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1087, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 95, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_Nganh, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_DeThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(405, 405, 405)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_Nganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_DeThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //======================================================================================================================================================================
    private void tbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMouseClicked
        // Kích hoạt ô JTextPane để hiển thị.
        txp_ttct.setEnabled(true);
        // Lấy chỉ số của dòng được chọn.
        int selectedRow = tb.getSelectedRow();
        // Lấy đối tượng ChiTietLichSu tương ứng từ danh sách CTLS bằng chỉ số vừa lấy.
        // Sau đó, gọi phương thức getCTTT_DT() để lấy chuỗi thông tin chi tiết và hiển thị lên JTextPane.
        txp_ttct.setText(CTLS.get(selectedRow).getCTTT_DT());
    }//GEN-LAST:event_tbMouseClicked

    
    //======================================================================================================================================================================
    private void cb_NganhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_NganhActionPerformed
        xemKETQUA(
        cb_Nganh.getSelectedItem().toString(),
        cb_lop.getSelectedItem().toString(),
        cb_DeThi.getSelectedItem().toString()
    );
    }//GEN-LAST:event_cb_NganhActionPerformed

    
    //======================================================================================================================================================================
    private void cb_lopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_lopActionPerformed
        if (cb_lop.getSelectedItem() == null || 
        cb_Nganh.getSelectedItem() == null || 
        cb_DeThi.getSelectedItem() == null) {
        return; // ⚡ Bỏ qua nếu chưa có dữ liệu
    }

    xemKETQUA(
        cb_Nganh.getSelectedItem().toString(),
        cb_lop.getSelectedItem().toString(),
        cb_DeThi.getSelectedItem().toString()
    );
    }//GEN-LAST:event_cb_lopActionPerformed

    private void cb_DeThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_DeThiActionPerformed
        xemKETQUA(
        cb_Nganh.getSelectedItem().toString(),
        cb_lop.getSelectedItem().toString(),
        cb_DeThi.getSelectedItem().toString()
    );
    }//GEN-LAST:event_cb_DeThiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try(Connection c = ctn.c()){
            PreparedStatement Pst = c.prepareStatement("DELETE FROM `lichsuthi` WHERE `MaTaiKhoan` = ? and MaDe = ?");
            Pst.setString(1,tb.getValueAt(tb.getSelectedRow(), 1).toString()); 
            Pst.setString(2,tb.getValueAt(tb.getSelectedRow(), 0).toString());
            Pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Người Thi Này Sẽ Làm Lại Đề Thi Số: " + tb.getValueAt(tb.getSelectedRow(), 0));
            xemKETQUA(
        cb_Nganh.getSelectedItem().toString(),
        cb_lop.getSelectedItem().toString(),
        cb_DeThi.getSelectedItem().toString()
    );
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
    //======================================================================================================================================================================
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FormQuanLyKetQua().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb_DeThi;
    private javax.swing.JComboBox<String> cb_Nganh;
    private javax.swing.JComboBox<String> cb_lop;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tb;
    private javax.swing.JTextPane txp_ttct;
    // End of variables declaration//GEN-END:variables
}
