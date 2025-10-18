
import TMH.CTN;
import TMH.CTN;
import TMH.DGD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class FormSuaMaDeThi extends javax.swing.JFrame {
    private CTN ctn = new CTN();
    private static int MADE;
    
    
    //======================================================================================================================================================================
    public FormSuaMaDeThi(int Made) {
        this.MADE = Made;
        initComponents();
        ctn.c();
        setLocationRelativeTo(null);
        xuatthongtinDeThi();
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
    private void xuatthongtinDeThi(){
        try(Connection c = ctn.c()){
            PreparedStatement Pst = c.prepareStatement("SELECT `NoidungDeThi`, `ThoiGian`, `NgayTao`, `NgayThi` FROM `dethi` WHERE MD = ?");Pst.setInt(1,MADE);
            ResultSet rs = Pst.executeQuery();
            if(rs.next()){
                txt_noidung.setText(rs.getString("NoidungDeThi"));
                txt_time.setText(rs.getInt("ThoiGian")+"");
                txt_ngay.setText(rs.getString("NgayTao"));
                txt_ngaythi.setText(rs.getString("NgayThi"));
            }
        }catch(Exception e){
            
        }
    }
    
    
    
    //======================================================================================================================================================================
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_ngaythi = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_noidung = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_time = new javax.swing.JTextField();
        bt_CapNhatDT = new javax.swing.JButton();
        txt_ngay = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nội Dung Đề Thi");

        jLabel2.setText("Thời Gian Thi - Phút");

        jLabel9.setText("Ngày Tạo: Ngày -  Tháng - Năm");

        jLabel10.setText("Ngày Thi: Ngày - Tháng - Năm");

        bt_CapNhatDT.setText("CẬP NHẬT ĐỀ THI");
        bt_CapNhatDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_CapNhatDTActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("CẬP NHẬT ĐỀ THI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(bt_CapNhatDT, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txt_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txt_ngaythi, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 109, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_time, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txt_noidung, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
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
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(16, 16, 16)
                .addComponent(txt_ngaythi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_CapNhatDT, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
    //======================================================================================================================================================================
    private void bt_CapNhatDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_CapNhatDTActionPerformed
        if(txt_noidung.getText().equals(null) || txt_time.getText().equals(null) ||
            txt_ngay.getText().equals(null) || txt_ngaythi.getText().equals(null)){
            JOptionPane.showConfirmDialog(null, "Nhap thong tin de thi!");
            return;
        }else{
            
            try(Connection c = ctn.c()){
                
                PreparedStatement Pst = c.prepareStatement("UPDATE `dethi` SET "
                        + "`NoidungDeThi`= ?,"
                        + "`ThoiGian`= ?,"
                        + "`NgayTao`= ?,"
                        + "`NgayThi`= ? WHERE `MD` = ?");
                
                Pst.setString(1,txt_noidung.getText());
                Pst.setInt(2,Integer.parseInt(txt_time.getText()));
                Pst.setString(3,txt_ngay.getText());
                Pst.setString(4,txt_ngaythi.getText());
                Pst.setInt(5, MADE);
                Pst.executeUpdate();
                new DGD(new FormQuanLiDeThi());
                this.dispose();
                JOptionPane.showMessageDialog(null, "Cập Nhật Đề Thì Thành Công!");
            }catch(Exception e){
                
            }
        }
    }//GEN-LAST:event_bt_CapNhatDTActionPerformed

    
    //======================================================================================================================================================================
    public static void main(String args[]) {
 
        java.awt.EventQueue.invokeLater(() -> new FormSuaMaDeThi(MADE).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_CapNhatDT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txt_ngay;
    private javax.swing.JTextField txt_ngaythi;
    private javax.swing.JTextField txt_noidung;
    private javax.swing.JTextField txt_time;
    // End of variables declaration//GEN-END:variables
}
