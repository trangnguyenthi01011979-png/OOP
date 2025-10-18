
import TMH.CTN;
import TMH.DGD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class FormTaoDeThi extends javax.swing.JFrame {
    private CTN ctn = new CTN();
    private static int LMD;
    
    
    //======================================================================================================================================================================
    public FormTaoDeThi(int LMD) {
        this.LMD = LMD; // Lưu mã đề thi vào biến toàn cục.
        initComponents(); // Khởi tạo các thành phần giao diện.
        ctn.c(); // Mở kết nối CSDL.
        setLocationRelativeTo(null);
        setcolWidth(tb); // Tùy chỉnh độ rộng cột của bảng.
        showCauhoi(); // Tải và hiển thị danh sách các câu hỏi có thể thêm.
        
        // Thiết lập hành vi khi người dùng nhấn nút 'X' trên cửa sổ.
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        // Thêm một bộ lắng nghe sự kiện để xử lý việc đóng cửa sổ.
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // GHI CHÚ VỀ LOGIC ĐIỀU HƯỚNG:
                // Dòng code bên dưới đang mở lại `FormQuanLiTaiKhoan`.
                // Tuy nhiên, form này được mở từ `FormQuanLiDeThi`, vì vậy logic đúng
                // nên là quay trở về `FormQuanLiDeThi` để người dùng có thể tiếp tục.
                // Bạn có thể cân nhắc sửa `FormQuanLiTaiKhoan` thành `FormQuanLiDeThi`.
                
                // Khi đóng cửa sổ, quay trở lại giao diện quản lý tài khoản.
                new FormQuanLiDeThi().setVisible(true);
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
    
    private void showCauhoi(){
        // Cập nhật tiêu đề để người dùng biết đang thao tác với đề thi nào.
        lbl_Dethi.setText("Thêm/Xóa Câu Hỏi Cho Đề Thi Số: " + LMD);
        
        // Chuẩn bị câu lệnh SQL để lấy danh sách các câu hỏi chưa có trong đề.
        // Logic: Lấy tất cả câu hỏi từ bảng `cauhoi` có Mã Câu (MC)
        // KHÔNG NẰM TRONG (NOT IN) danh sách các câu hỏi đã thuộc về Mã Đề (MD) này trong bảng `ctdt`.
        String sql = "SELECT c.MC, c.noidungch, c.A, c.B, c.C, c.D FROM cauhoi c " +
                       "WHERE c.MC NOT IN (SELECT MC FROM ctdt WHERE MD = ?)";
        
        // Sử dụng try-with-resources để đảm bảo kết nối CSDL được đóng tự động.
        try(Connection c = ctn.c()){
            PreparedStatement Pst = c.prepareStatement(sql);
            // Gán giá trị của mã đề hiện tại vào dấu "?" trong câu truy vấn con.
            Pst.setInt(1, LMD); 
            
            ResultSet rs = Pst.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) tb.getModel();
            tm.setRowCount(0); // Xóa dữ liệu cũ.
            
            // Lặp qua kết quả và thêm từng câu hỏi vào bảng.
            while(rs.next()){
                Object[] o = {
                    rs.getInt("MC"), 
                    rs.getString("noidungch"), 
                    rs.getString("A"),
                    rs.getString("B"),
                    rs.getString("C"),
                    rs.getString("D"),
                    "Chưa thêm" // Hiển thị trạng thái mặc định.
                };
                tm.addRow(o);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách câu hỏi.", "Lỗi SQL", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb = new javax.swing.JTable();
        bt_Them = new javax.swing.JButton();
        lbl_Dethi = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Câu Hỏi", "Nội Dung Câu Hỏi", "Đáp Án A", "Đáp Án B", "Đáp Án C", "Đáp Án D", "TÌNH TRẠNG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb);

        bt_Them.setText("THÊM CÂU HỎI CHO ĐỀ THI");
        bt_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ThemActionPerformed(evt);
            }
        });

        lbl_Dethi.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Dethi)
                            .addComponent(bt_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbl_Dethi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void bt_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ThemActionPerformed
        // Lấy Mã Câu hỏi (MC) từ dòng đang được chọn trong bảng (cột đầu tiên, index 0).
        int LMC = Integer.parseInt(tb.getValueAt(tb.getSelectedRow(), 0).toString());
        try(Connection c = ctn.c()){
            // Chuẩn bị câu lệnh INSERT để tạo một bản ghi mới trong bảng `ctdt`.
            // Bảng `ctdt` là bảng trung gian, lưu mối quan hệ giữa câu hỏi và đề thi.
            PreparedStatement Pst = c.prepareStatement("INSERT INTO `ctdt`(`MC`, `MD`) VALUES (?,?)");
            // Gán Mã Câu (LMC) và Mã Đề (LMD) vào câu lệnh.
            Pst.setInt(1,LMC);
            Pst.setInt(2,LMD);
            // Thực thi lệnh INSERT.
            Pst.executeUpdate();
            // Sau khi thêm thành công, gọi lại hàm `showCauhoi()` để làm mới danh sách.
            // Câu hỏi vừa được thêm sẽ biến mất khỏi bảng này.
            showCauhoi();
        }catch(Exception e){
            // Khối catch trống.
        }
    }//GEN-LAST:event_bt_ThemActionPerformed
    
    
    //======================================================================================================================================================================
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTaoDeThi(LMD).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Them;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_Dethi;
    private javax.swing.JTable tb;
    // End of variables declaration//GEN-END:variables
}
