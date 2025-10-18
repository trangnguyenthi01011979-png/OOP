
import TMH.CTN;
import TMH.CTN;
import TMH.DGD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class FormTaoMaDeThi extends javax.swing.JFrame {
    private CTN ctn = new CTN();
    
    
    //======================================================================================================================================================================
     public FormTaoMaDeThi() {
        initComponents(); // Khởi tạo các thành phần giao diện.
        ctn.c(); // Mở kết nối CSDL.
        setLocationRelativeTo(null);
        // Thiết lập hành vi khi đóng cửa sổ.
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        // Thêm một bộ lắng nghe sự kiện để xử lý việc đóng cửa sổ.
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                // GHI CHÚ VỀ LOGIC ĐIỀU HƯỚNG:
                // Dòng code bên dưới đang mở lại `FormQuanLiUser`.
                // Tuy nhiên, form này có thể được mở từ `FormQuanLiDeThi`, vì vậy logic đúng
                // nên là quay trở về `FormQuanLiDeThi`.
                
                // Khi đóng cửa sổ, quay trở lại giao diện quản lý người dùng.
                new FormQuanLiDeThi().setVisible(true);

                // Đóng form hiện tại.
                dispose();
            }
        });
    }

    
    //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_noidung = new javax.swing.JTextField();
        txt_time = new javax.swing.JTextField();
        txt_ngay = new javax.swing.JTextField();
        txt_thang = new javax.swing.JTextField();
        txt_nam = new javax.swing.JTextField();
        txt_ngaythi = new javax.swing.JTextField();
        txt_thangthi = new javax.swing.JTextField();
        txt_namthi = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bt_taoDT = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nội Dung Đề Thi");

        jLabel2.setText("Thời Gian Thi - Phút");

        jLabel9.setText("Ngày Tạo: Ngày -  Tháng - Năm");

        jLabel10.setText("Ngày Thi: Ngày - Tháng - Năm");

        bt_taoDT.setText("TẠO ĐỀ THI MỚI");
        bt_taoDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_taoDTActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("TẠO ĐỀ THI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_thang, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_namthi, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                            .addComponent(txt_nam))
                        .addContainerGap(107, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9)
                            .addComponent(txt_noidung, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_time, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_ngaythi, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_thangthi, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(bt_taoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_noidung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_thangthi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_namthi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ngaythi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(bt_taoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    //======================================================================================================================================================================
    private void bt_taoDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_taoDTActionPerformed
        // Lấy và chuyển đổi ngày, tháng, năm tạo đề từ các ô text thành số nguyên.
        int namtao = Integer.parseInt(txt_nam.getText());
        int thangtao = Integer.parseInt(txt_thang.getText());
        int ngaytao = Integer.parseInt(txt_ngay.getText());

        // Sử dụng LocalDate để tạo đối tượng ngày tạo, đây là cách làm rất tốt và hiện đại.
        LocalDate ngaytaodethi = LocalDate.of(namtao, thangtao, ngaytao);
        
        // Lấy và chuyển đổi ngày, tháng, năm thi từ các ô text.
        int namthi = Integer.parseInt(txt_namthi.getText());
        int thangthi = Integer.parseInt(txt_thangthi.getText());
        int ngaythi = Integer.parseInt(txt_ngaythi.getText());

        // Tạo đối tượng LocalDate cho ngày thi.
        LocalDate ngaybatdauthi = LocalDate.of(namthi, thangthi, ngaythi);
        
        // ========================= LỖI QUAN TRỌNG TRONG VALIDATION =========================
        // Khối `if` dưới đây có một vài lỗi:
        // 1. `getText().equals(null)` sẽ luôn gây ra lỗi NullPointerException nếu bạn muốn so sánh với null.
        //    Cách đúng để kiểm tra rỗng là `getText().trim().isEmpty()`.
        // 2. `JOptionPane.showConfirmDialog` dùng để hiển thị hộp thoại Yes/No, không phải để hiển thị thông báo.
        //    Để hiển thị thông báo, bạn nên dùng `JOptionPane.showMessageDialog`.
        // Do đó, khối kiểm tra này sẽ không hoạt động như mong đợi.
        if(txt_noidung.getText().equals(null) || txt_time.getText().equals(null) ||
           txt_nam.getText().equals(null) || txt_thang.getText().equals(null) ||
           txt_ngay.getText().equals(null) || txt_namthi.getText().equals(null) ||
           txt_ngaythi.getText().equals(null) || txt_thangthi.getText().equals(null)){
            JOptionPane.showConfirmDialog(null, "Nhap thong tin de thi!");
            return;
        }else{
            // Nếu validation (dù bị lỗi) được bỏ qua, thực hiện thêm vào CSDL.
            try(Connection c = ctn.c()){
                // Chuẩn bị câu lệnh SQL INSERT để thêm một đề thi mới vào bảng `dethi`.
                PreparedStatement Pst = c.prepareStatement("INSERT INTO `dethi`(`NoidungDeThi`, `ThoiGian`, `NgayTao`, `NgayThi`) "
                        + "VALUES (?,?,?,?)");
                // Gán các giá trị từ form vào câu lệnh.
                Pst.setString(1,txt_noidung.getText());
                Pst.setInt(2,Integer.parseInt(txt_time.getText()));
                // Chuyển đối tượng LocalDate thành java.sql.Date để lưu vào CSDL.
                Pst.setDate(3,java.sql.Date.valueOf(ngaytaodethi));
                Pst.setDate(4,java.sql.Date.valueOf(ngaybatdauthi));
                // Thực thi lệnh INSERT.
                Pst.executeUpdate();
                new DGD(new FormQuanLiDeThi());
                this.dispose();
                JOptionPane.showMessageDialog(null, "Thêm Đề Thì Mới Thành Công!");
                // GHI CHÚ: Sau khi tạo thành công, nên có một thông báo cho người dùng
                // ví dụ: JOptionPane.showMessageDialog(this, "Tạo đề thi thành công!");
            }catch(Exception e){
                // Khối catch trống, lỗi sẽ bị bỏ qua trong im lặng.
                // Nên thêm e.printStackTrace() hoặc một thông báo lỗi ở đây.
            }
        }
    }//GEN-LAST:event_bt_taoDTActionPerformed
    
    
    //======================================================================================================================================================================
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new FormTaoMaDeThi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_taoDT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txt_nam;
    private javax.swing.JTextField txt_namthi;
    private javax.swing.JTextField txt_ngay;
    private javax.swing.JTextField txt_ngaythi;
    private javax.swing.JTextField txt_noidung;
    private javax.swing.JTextField txt_thang;
    private javax.swing.JTextField txt_thangthi;
    private javax.swing.JTextField txt_time;
    // End of variables declaration//GEN-END:variables
}
