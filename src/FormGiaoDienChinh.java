
import TMH.CTN;
import TMH.DGD;
import TMH.TMH_Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Normalizer;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FormGiaoDienChinh extends javax.swing.JFrame {
    private boolean check = false;
    private CTN ctn = new CTN();
    private static String MTK;
    private TMH_Admin TMH = new TMH_Admin();
    
    //======================================================================================================================================================================
    public FormGiaoDienChinh(boolean check) { 
       this.check = check; // Gán trạng thái đăng nhập.
       TMH.getTMH_Admin(check);
       initComponents(); // Khởi tạo các thành phần giao diện.
       setLocationRelativeTo(null);
        ctn.c(); // Mở kết nối CSDL.
        showDeThi(); // Tải và hiển thị danh sách đề thi lên bảng.
        // kiemTraDangNhap(); // GHI CHÚ: Phương thức này nên được gọi ở đây để bảo mật.
        
    }
    
    
    //======================================================================================================================================================================
    public FormGiaoDienChinh() {
        // Gọi lại hàm khởi tạo chính với giá trị `check` mặc định là `false`.
        this(false); 
    }
    
    public void getMTK(String MTK){
        this.MTK = MTK;
    }
    
    //======================================================================================================================================================================
    private void kiemTraDangNhap() {
        if (!check) { // Nếu biến `check` là false (chưa đăng nhập).
            JOptionPane.showMessageDialog(this, "Bạn chưa đăng nhập tài khoản!");
            dispose(); // Đóng form lại.
        }
    }
    
    
    //======================================================================================================================================================================
     private void showDeThi() {
        try (Connection c = ctn.c()) {
            // Chuẩn bị câu lệnh SQL để lấy thông tin đề thi và đếm số câu hỏi tương ứng.
            // LEFT JOIN: Để đảm bảo những đề thi chưa có câu hỏi nào vẫn được hiển thị (với số lượng câu là 0).
            // GROUP BY: Bắt buộc phải có khi sử dụng hàm tổng hợp như COUNT().
            PreparedStatement Pst = c.prepareStatement(
                "SELECT dethi.MD, dethi.NoidungDeThi, COUNT(ctdt.IDCau) AS SOLUONGCAU, dethi.ThoiGian, dethi.NgayThi "
                + "FROM dethi "
                + "LEFT JOIN ctdt ON ctdt.MD = dethi.MD "
                + "GROUP BY dethi.MD, dethi.NoidungDeThi, dethi.ThoiGian, dethi.NgayThi" // Sửa lại group by cho đúng
            );
            ResultSet rs = Pst.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) tb.getModel();
            tm.setRowCount(0); // Xóa dữ liệu cũ trên bảng.

            // Lặp qua từng dòng kết quả và thêm vào bảng.
            while (rs.next()) {
                Object[] o = {
                    rs.getInt("MD"),
                    rs.getString("NoidungDeThi"),
                    rs.getDate("NgayThi"),
                    rs.getInt("ThoiGian"),
                    rs.getInt("SOLUONGCAU"),
                };
                tm.addRow(o);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi chi tiết ra console để dễ dàng gỡ lỗi.
        }
    }
     
     
     //======================================================================================================================================================================
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jFrame2 = new javax.swing.JFrame();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb = new javax.swing.JTable();
        bt_ganDT = new javax.swing.JButton();
        jMenuBar3 = new javax.swing.JMenuBar();
        mn_QLDT = new javax.swing.JMenu();
        mn_QLND = new javax.swing.JMenu();
        mn_QLL = new javax.swing.JMenu();
        mn_QLKQ = new javax.swing.JMenu();
        mn_QLTK = new javax.swing.JMenu();
        mn_QLCH = new javax.swing.JMenu();
        mn_caidat = new javax.swing.JMenu();
        mnItem_DoiMK = new javax.swing.JMenu();
        mnItem_DangXuat = new javax.swing.JMenu();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jFrame1.setTitle("Trang Chủ : ADMIN");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("ADMIN");

        jButton1.setText("Trang Chủ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Đề Thi", "Ngày Thi", "Thời Gian Thi", "Loại Đề Thi", "Môn Thi", "Thí Sinh"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton5.setText("Đổi Mật Khẩu");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Danh Sách Đề Thi");

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Tạo Đề Thi");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu3.setText("Quản Lí Đề Thi");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Quản Lí Người Dùng");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu1.setText("Quản Lý Lớp");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quản Lý Kết Quả");
        jMenuBar1.add(jMenu2);

        jMenu5.setText("Quản Lí Tài Khoản");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        jMenu6.setText("Quản Lý Câu Hỏi");
        jMenuBar1.add(jMenu6);

        jFrame1.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(20, 20, 20))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jButton5)))
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jFrame2.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jFrame2.setTitle("Trang Chủ : ADMIN");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("ADMIN");

        jButton3.setText("Trang Chủ");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Đề Thi", "Ngày Thi", "Thời Gian Thi", "Loại Đề Thi", "Môn Thi", "Thí Sinh"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton6.setText("Đổi Mật Khẩu");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Danh Sách Đề Thi");

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("Tạo Đề Thi");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jMenu7.setText("Quản Lí Đề Thi");
        jMenuBar2.add(jMenu7);

        jMenu8.setText("Quản Lí Người Dùng");
        jMenu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu8MouseClicked(evt);
            }
        });
        jMenu8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu8ActionPerformed(evt);
            }
        });
        jMenuBar2.add(jMenu8);

        jMenu9.setText("Quản Lý Lớp");
        jMenuBar2.add(jMenu9);

        jMenu10.setText("Quản Lý Kết Quả");
        jMenuBar2.add(jMenu10);

        jMenu11.setText("Quản Lí Tài Khoản");
        jMenu11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu11MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu11);

        jMenu12.setText("Quản Lý Câu Hỏi");
        jMenuBar2.add(jMenu12);

        jFrame2.setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jFrame2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(20, 20, 20))
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addGroup(jFrame2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jButton6)))
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("QUẢN TRỊ VIÊN");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Danh Sách Đề Thi");

        tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Đề", "Đề Thi", "Ngày Thi", "Thời Gian Thi", "Tổng Số Câu "
            }
        ));
        jScrollPane3.setViewportView(tb);

        bt_ganDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_ganDT.setText("Gán Đề Thi");
        bt_ganDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ganDTActionPerformed(evt);
            }
        });

        mn_QLDT.setText("Quản Lí Đề Thi");
        mn_QLDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mn_QLDTMouseClicked(evt);
            }
        });
        jMenuBar3.add(mn_QLDT);

        mn_QLND.setText("Quản Lí Người Dùng");
        mn_QLND.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mn_QLNDMouseClicked(evt);
            }
        });
        jMenuBar3.add(mn_QLND);

        mn_QLL.setText("Quản Lý Lớp");
        mn_QLL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mn_QLLMouseClicked(evt);
            }
        });
        jMenuBar3.add(mn_QLL);

        mn_QLKQ.setText("Quản Lý Kết Quả");
        mn_QLKQ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mn_QLKQMouseClicked(evt);
            }
        });
        jMenuBar3.add(mn_QLKQ);

        mn_QLTK.setText("Quản Lí Tài Khoản");
        mn_QLTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mn_QLTKMouseClicked(evt);
            }
        });
        jMenuBar3.add(mn_QLTK);

        mn_QLCH.setText("Quản Lý Câu Hỏi");
        mn_QLCH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mn_QLCHMouseClicked(evt);
            }
        });
        jMenuBar3.add(mn_QLCH);

        mn_caidat.setText("Cài Đặt");
        mn_caidat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mn_caidatMouseClicked(evt);
            }
        });

        mnItem_DoiMK.setText("Đổi Mật Khẩu");
        mnItem_DoiMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnItem_DoiMKMouseClicked(evt);
            }
        });
        mn_caidat.add(mnItem_DoiMK);

        mnItem_DangXuat.setText("Đăng Xuất");
        mnItem_DangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnItem_DangXuatMouseClicked(evt);
            }
        });
        mn_caidat.add(mnItem_DangXuat);

        jMenuBar3.add(mn_caidat);

        setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_ganDT)
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_ganDT)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked

    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed

    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked

    }//GEN-LAST:event_jMenu5MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenu8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu8MouseClicked

    }//GEN-LAST:event_jMenu8MouseClicked

    private void jMenu8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu8ActionPerformed

    }//GEN-LAST:event_jMenu8ActionPerformed

    private void jMenu11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu11MouseClicked

    }//GEN-LAST:event_jMenu11MouseClicked

    
    //======================================================================================================================================================================
    private void bt_ganDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ganDTActionPerformed
        // Lấy mã đề từ dòng đang được chọn trong bảng (cột đầu tiên, index 0).
        int LMD = Integer.parseInt(tb.getValueAt(tb.getSelectedRow(), 0).toString());
        
        // Mở form FormGanDeThi và truyền mã đề vừa lấy được sang.
        // DGD có thể là một lớp tùy chỉnh để hiển thị form dưới dạng dialog.
        new DGD(new FormGanDeThi(LMD));
        
        // Đóng form hiện tại.
        this.dispose();
    }//GEN-LAST:event_bt_ganDTActionPerformed
    
    
    //======================================================================================================================================================================
    private void mn_QLNDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mn_QLNDMouseClicked
        new DGD(new FormQuanLiUser());
        this.dispose();
    }//GEN-LAST:event_mn_QLNDMouseClicked
    
    
    //======================================================================================================================================================================
    private void mn_QLTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mn_QLTKMouseClicked
        FormQuanLiTaiKhoan FQLTK = new FormQuanLiTaiKhoan();
        new DGD(FQLTK);
        this.dispose();
    }//GEN-LAST:event_mn_QLTKMouseClicked
    
    
    //======================================================================================================================================================================
    private void mn_QLCHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mn_QLCHMouseClicked
        FormQuanLiCauHoi FQLCH = new FormQuanLiCauHoi();
        new DGD(FQLCH);
        this.dispose();
    }//GEN-LAST:event_mn_QLCHMouseClicked
    
    
    //======================================================================================================================================================================
    private void mn_QLLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mn_QLLMouseClicked
        new DGD(new FormQuanLiLop());
        this.dispose();
    }//GEN-LAST:event_mn_QLLMouseClicked
    
    
    //======================================================================================================================================================================
    private void mn_QLDTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mn_QLDTMouseClicked
       new DGD(new FormQuanLiDeThi());
        this.dispose();
    }//GEN-LAST:event_mn_QLDTMouseClicked
    
    
    //======================================================================================================================================================================
    private void mn_QLKQMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mn_QLKQMouseClicked
        new DGD(new FormQuanLyKetQua());
        this.dispose();
    }//GEN-LAST:event_mn_QLKQMouseClicked
    
    
     //======================================================================================================================================================================
    private void mn_caidatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mn_caidatMouseClicked
        
    }//GEN-LAST:event_mn_caidatMouseClicked

    private void mnItem_DoiMKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnItem_DoiMKMouseClicked
        new DGD(new FormDoiMatKhauADMIN(MTK));
        this.dispose();
    }//GEN-LAST:event_mnItem_DoiMKMouseClicked

    private void mnItem_DangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnItem_DangXuatMouseClicked
        new DGD(new DangNhap());
        this.dispose();
        JOptionPane.showMessageDialog(null, "Bạn đã Đăng Xuất Tài Khoản!");
    }//GEN-LAST:event_mnItem_DangXuatMouseClicked

    
    //======================================================================================================================================================================
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormGiaoDienChinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_ganDT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JMenu mnItem_DangXuat;
    private javax.swing.JMenu mnItem_DoiMK;
    private javax.swing.JMenu mn_QLCH;
    private javax.swing.JMenu mn_QLDT;
    private javax.swing.JMenu mn_QLKQ;
    private javax.swing.JMenu mn_QLL;
    private javax.swing.JMenu mn_QLND;
    private javax.swing.JMenu mn_QLTK;
    private javax.swing.JMenu mn_caidat;
    private javax.swing.JTable tb;
    // End of variables declaration//GEN-END:variables
}

