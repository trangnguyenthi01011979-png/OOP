
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import TMH.CTN;
import TMH.DGD;
import TMH.TMH_Admin;

public class FormDoiMatKhauADMIN extends javax.swing.JFrame {
    private CTN ctn = new CTN();
    private static String MTK;
    public FormDoiMatKhauADMIN(String MTK) {
        this.MTK = MTK;
        initComponents();
        setLocationRelativeTo(null);
        ctn.c();
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

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_MKC = new javax.swing.JTextField();
        txt_MKM = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bt_CapNhatMK = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nhập Mật Khẩu Cũ");

        jLabel2.setText("Nhập Mật Khẩu Mới");

        bt_CapNhatMK.setText("CẬP NHẬT MẬT KHẨU");
        bt_CapNhatMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_CapNhatMKActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("ĐỔI MẬT KHẨU QUẢN TRỊ VIÊN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txt_MKC, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_MKM, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3)))
                .addGap(14, 14, 14))
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(bt_CapNhatMK, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MKC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_MKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(bt_CapNhatMK, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_CapNhatMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_CapNhatMKActionPerformed
        if(txt_MKC.getText().isEmpty() || txt_MKM.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Xin Nhập Mật Khẩu!");
        }else{
            TMH_Admin TMH = new TMH_Admin(txt_MKC.getText());
            TMH_Admin MKM = new TMH_Admin(txt_MKM.getText());
            try(Connection c = ctn.c()){
                PreparedStatement Pst = c.prepareStatement("select MatKhau from dang_nhap where MaTaiKhoan = ?");Pst.setString(1,MTK);
                ResultSet rs = Pst.executeQuery();
                if(rs.next()){
                    if(rs.getString("MatKhau").equals(TMH.getMH())){
                        PreparedStatement Pst1 = c.prepareStatement("UPDATE `dang_nhap` SET `MatKhau`= ? WHERE `MaTaiKhoan` = ?");
                        Pst1.setString(1,MKM.getMH());
                        Pst1.setString(2,MTK);
                        Pst1.executeUpdate();
                        this.dispose();
                        new DGD(new FormGiaoDienChinh());
                        JOptionPane.showMessageDialog(null, "Đổi Mật Khẩu Thành Công!");
                        
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Mật Khẩu Cũ Không Đúng!");
                }
            }catch(Exception e){

            }
        }
    }//GEN-LAST:event_bt_CapNhatMKActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> new FormDoiMatKhauADMIN(MTK).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_CapNhatMK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txt_MKC;
    private javax.swing.JTextField txt_MKM;
    // End of variables declaration//GEN-END:variables
}
