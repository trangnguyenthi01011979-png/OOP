
import TMH.CTN;
import TMH.DGD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


public class FormQuanLiDeThi extends javax.swing.JFrame {
    private CTN ctn = new CTN();
    

    //======================================================================================================================================================================
    public FormQuanLiDeThi() {
        initComponents(); // Khởi tạo các thành phần giao diện.
        ctn.c(); // Mở kết nối CSDL.
        setLocationRelativeTo(null);
        showDeThi(); // Tải và hiển thị danh sách đề thi lên bảng.
        
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
    private void showDeThi() {
        // Sử dụng try-with-resources để đảm bảo kết nối CSDL được đóng tự động.
        try (Connection c = ctn.c()) {
            // Chuẩn bị câu lệnh SQL để lấy thông tin các đề thi và đếm số lượng câu hỏi của mỗi đề.
            // LEFT JOIN: Đảm bảo những đề thi chưa có câu hỏi nào vẫn được hiển thị (với số lượng câu là 0).
            // GROUP BY: Bắt buộc phải có khi sử dụng hàm tổng hợp như COUNT().
            PreparedStatement Pst = c.prepareStatement(
                "SELECT dethi.MD, COUNT(ctdt.IDCau) AS SOLUONGCAU, dethi.ThoiGian, dethi.NgayTao, dethi.NgayThi "
                + "FROM dethi "
                + "LEFT JOIN ctdt ON ctdt.MD = dethi.MD "
                + "GROUP BY dethi.MD, dethi.ThoiGian, dethi.NgayTao, dethi.NgayThi"
            );
            // Thực thi câu lệnh SQL.
            ResultSet rs = Pst.executeQuery();
            // Lấy mô hình dữ liệu của bảng `tb` để có thể thêm/xóa dòng.
            DefaultTableModel tm = (DefaultTableModel) tb.getModel();
            // Xóa tất cả các dòng hiện có trong bảng để chuẩn bị hiển thị dữ liệu mới.
            tm.setRowCount(0);

            // Bắt đầu vòng lặp, xử lý từng dòng kết quả trả về từ CSDL.
            while (rs.next()) {
                // Tạo một mảng đối tượng để chứa dữ liệu cho một hàng trong JTable.
                Object[] o = {
                    rs.getInt("MD"),
                    rs.getInt("SOLUONGCAU"),
                    rs.getInt("ThoiGian"),
                    rs.getDate("NgayTao"),
                    rs.getDate("NgayThi")
                };
                // Thêm hàng mới vào bảng.
                tm.addRow(o);
            }
        } catch (Exception e) {
            // In chi tiết lỗi ra console để lập trình viên có thể gỡ lỗi.
            e.printStackTrace(); 
        }
    }
    
    
    //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb = new javax.swing.JTable();
        bt_TaoCHDT = new javax.swing.JButton();
        bt_TaoDT = new javax.swing.JButton();
        bt_TaoDT1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Đề", "Tổng Số Câu Hỏi", "Thời Gian Làm Bài", "Ngày Tạo", "Ngày Thi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb);

        bt_TaoCHDT.setText("TẠO CÂU HỎI CHO ĐỀ THI");
        bt_TaoCHDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_TaoCHDTActionPerformed(evt);
            }
        });

        bt_TaoDT.setText("TẠO ĐỀ THI");
        bt_TaoDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_TaoDTActionPerformed(evt);
            }
        });

        bt_TaoDT1.setText("CẬP NHẬT ĐỀ THI");
        bt_TaoDT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_TaoDT1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ ĐỀ THI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt_TaoCHDT, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bt_TaoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bt_TaoDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_TaoDT1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(bt_TaoDT, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addComponent(bt_TaoCHDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
    
   
    //======================================================================================================================================================================
    private void bt_TaoDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_TaoDTActionPerformed
        new DGD(new FormTaoMaDeThi());
        this.dispose();
    }//GEN-LAST:event_bt_TaoDTActionPerformed
    
    
    //======================================================================================================================================================================
    private void bt_TaoCHDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_TaoCHDTActionPerformed
        // Lấy chỉ số của hàng đang được chọn trong bảng.
        // Lấy giá trị từ cột đầu tiên (index 0) của hàng đó, đây chính là Mã Đề.
        // Chuyển giá trị đó thành Integer.
        // Mở form FormTaoDeThi (form này thực chất là để thêm câu hỏi vào đề thi đã có)
        // và truyền mã đề vừa lấy được sang cho form đó xử lý.
        new DGD(new FormTaoDeThi(Integer.parseInt(tb.getValueAt(tb.getSelectedRow(), 0).toString())));
        // Đóng form hiện tại.
        this.dispose();
    }//GEN-LAST:event_bt_TaoCHDTActionPerformed
    
    
    //======================================================================================================================================================================
    private void bt_TaoDT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_TaoDT1ActionPerformed
        new DGD(new FormSuaMaDeThi(Integer.parseInt(tb.getValueAt(tb.getSelectedRow(), 0).toString())));
        this.dispose();
    }//GEN-LAST:event_bt_TaoDT1ActionPerformed


    //======================================================================================================================================================================
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new FormQuanLiDeThi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_TaoCHDT;
    private javax.swing.JButton bt_TaoDT;
    private javax.swing.JButton bt_TaoDT1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb;
    // End of variables declaration//GEN-END:variables
}
