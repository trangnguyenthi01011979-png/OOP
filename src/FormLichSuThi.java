
import TMH.CTN;
import TMH.ChiTietLichSu;
import TMH.DGD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class FormLichSuThi extends javax.swing.JFrame {
    private CTN ctn = new CTN();
    private List<ChiTietLichSu> CTLS = new ArrayList<>();
    private static String MTK;
    private static int made;
    
    
    //======================================================================================================================================================================
    public FormLichSuThi(String MTK, int mde) {
        this.MTK = MTK;
        this.made = mde;
        initComponents(); // Khởi tạo các thành phần giao diện.
        setLocationRelativeTo(null);
        ctn.c(); // Mở kết nối CSDL.
        setcolWidth(tb); // Tùy chỉnh độ rộng cột của bảng.
        viewtable(); // Tải và hiển thị dữ liệu lịch sử thi.
        
        // Tùy chỉnh hành vi khi đóng cửa sổ.
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        // Thêm một bộ lắng nghe sự kiện để xử lý việc đóng cửa sổ.
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // Khi đóng cửa sổ lịch sử, quay trở lại giao diện chính của người dùng.
                new FormGiaoDienChinhUser(MTK, made).setVisible(true);
                dispose(); // Đóng form hiện tại.
            }
        });
    }
    
    
    //======================================================================================================================================================================
    private void setcolWidth(JTable jt) {
        TableColumnModel cm = jt.getColumnModel();
        cm.getColumn(0).setMaxWidth(100);
    }
    
    public void viewtable() {
        // Sử dụng try-with-resources để đảm bảo kết nối CSDL được đóng tự động.
        try (Connection c = ctn.c()) {
            // Chuẩn bị câu lệnh SQL để lấy lịch sử thi.
            // Câu lệnh này nối hai bảng `dethi` và `lichsuthi`, và lọc kết quả
            // theo mã tài khoản của người dùng hiện tại.
            PreparedStatement Pst = c.prepareStatement(
                "select MaTaiKhoan, NoidungDeThi, MaDe, ThoiGianLam, lichsuthi.NgayThi, socaudung, TTCT, KQ from dethi, lichsuthi "
                + "where lichsuthi.MaDe = dethi.MD and lichsuthi.MaTaiKhoan = ?"
            );
            // Gán giá trị của biến MTK (Mã Tài Khoản) vào dấu chấm hỏi (?) đầu tiên trong câu lệnh.
            Pst.setString(1, MTK);

            // Thực thi câu lệnh SQL và nhận về một tập kết quả (ResultSet).
            ResultSet rs = Pst.executeQuery();

            // Lấy mô hình dữ liệu của bảng `tb` để có thể thêm/xóa dòng.
            DefaultTableModel tm = (DefaultTableModel) tb.getModel();

            // Xóa tất cả các dòng hiện có trong bảng để chuẩn bị hiển thị dữ liệu mới.
            tm.setRowCount(0);

            // Bắt đầu vòng lặp, xử lý từng dòng kết quả trả về từ CSDL.
            while (rs.next()) {
                // Tạo một mảng đối tượng `o` để chứa dữ liệu cho một hàng trong JTable.
                // Mảng này chỉ chứa các thông tin tóm tắt sẽ hiển thị trên bảng.
                Object o[] = {
                    rs.getString("NoidungDeThi"),
                    rs.getString("ThoiGianLam"),
                    rs.getString("NgayThi"),
                    rs.getInt("socaudung"),
                    rs.getDouble("KQ")
                };
                // Thêm hàng mới vào bảng.
                tm.addRow(o);

                // Tạo một đối tượng `ChiTietLichSu` để lưu trữ TẤT CẢ thông tin của lần thi,
                // bao gồm cả thông tin chi tiết "TTCT" không hiển thị trực tiếp trên bảng.
                ChiTietLichSu ct_ls = new ChiTietLichSu(
                    rs.getString("NoidungDeThi"),
                    rs.getInt("MaDe"),
                    rs.getFloat("KQ"),
                    rs.getInt("socaudung"),
                    rs.getString("NgayThi"),
                    rs.getString("ThoiGianLam"),
                    rs.getString("TTCT") // Lấy cả chuỗi chi tiết bài làm
                );
                // Thêm đối tượng chi tiết này vào danh sách `CTLS`.
                // Danh sách này sẽ được dùng để hiển thị chi tiết khi người dùng nhấn vào một dòng.
                CTLS.add(ct_ls);
            }
        } catch (Exception e) {
            // Nếu có lỗi xảy ra trong quá trình truy vấn CSDL, hiển thị thông báo lỗi.
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
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
        jScrollPane2 = new javax.swing.JScrollPane();
        txp_ttct = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Đề thi", "Thời gian", "Ngày Thi", "Số Câu Đúng", "Kết Quả Thi"
            }
        ));
        tb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb);

        jScrollPane2.setViewportView(txp_ttct);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    //======================================================================================================================================================================
    private void tbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMouseClicked
        // Lấy chỉ số của dòng được chọn.
        int selectedRow = tb.getSelectedRow();
        
        // Dựa vào chỉ số đó, lấy đối tượng ChiTietLichSu tương ứng từ danh sách CTLS.
        // Sau đó, gọi phương thức getCTTT_DT() để lấy chuỗi thông tin chi tiết của bài làm
        // và hiển thị lên JTextPane (txp_ttct).
        txp_ttct.setText(CTLS.get(selectedRow).getCTTT_DT());
    }//GEN-LAST:event_tbMouseClicked

    
    //======================================================================================================================================================================
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLichSuThi(MTK,made).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tb;
    private javax.swing.JTextPane txp_ttct;
    // End of variables declaration//GEN-END:variables
}
