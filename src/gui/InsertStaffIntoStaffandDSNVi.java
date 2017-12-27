/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import modelclasses.Departmant;
import modelclasses.StaffList;

/**
 *
 * @author CỌP
 */
public class InsertStaffIntoStaffandDSNVi extends javax.swing.JPanel {

    /**
     * Creates new form InsertStaffIntoStaffandDSNVi
     */
    public InsertStaffIntoStaffandDSNVi() {
        initComponents();
        // get năm sinh form 1900- (năm hiện hành-18): nhân viên> 18 tuổi mới đi làm được.      
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        for (int i = 1900; i <= (year - 18); i++) {
            String a = String.valueOf(i);
            this.cboNamSinh.addItem(a);
        }
        // tháng set từ 1-12:
        for (int i = 1; i <= 12; i++) {
            // chuyển số sang string:
            String j = String.valueOf(i);
            System.out.println(j);
            //add vào từng item:
            this.cboThangSinh.addItem(j);
        }
        // còn xữ lý ngày: tại event của cboNgay=> goto cboNgaySinh:
        // setmodel cho combobox Chọn đơn vị:
        DefaultComboBoxModel d = new DefaultComboBoxModel(Departmant.getMaDVandTenDV());
        cboChonDonVi.setModel(d);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Jpanel12 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cboChonDonVi = new javax.swing.JComboBox<>();
        txtChucVu = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cboNgaySinh = new javax.swing.JComboBox<>();
        cboThangSinh = new javax.swing.JComboBox<>();
        cboNamSinh = new javax.swing.JComboBox<>();
        txtHo = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        rdoNu = new javax.swing.JRadioButton();
        rdoNam = new javax.swing.JRadioButton();
        txtCMND = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        txtNhapLaiMatKhau = new javax.swing.JPasswordField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaStatus = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtareaGhiChu = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        btnLuu = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnQuayLai = new javax.swing.JButton();

        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THÊM NHÂN VIÊN MỚI");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setText("Họ");

        jLabel3.setText("Ngày sinh(*)");

        jLabel4.setText("Giới tính");

        jLabel5.setText("CMND");

        jLabel6.setText("Mật khẩu");

        jLabel7.setText("Nhập lại mật khẩu");

        jLabel8.setText("Status");

        jLabel10.setText("Đơn vị");

        jLabel11.setText("Chức vụ");

        cboChonDonVi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboChonDonVi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChonDonViActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("CHO ĐƠN VỊ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("THÔNG TIN NHÂN VIÊN");

        jLabel14.setText("Ghi chú");

        cboThangSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThangSinhActionPerformed(evt);
            }
        });

        cboNamSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamSinhActionPerformed(evt);
            }
        });

        jLabel9.setText("Tên");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        txtAreaStatus.setColumns(20);
        txtAreaStatus.setRows(5);
        jScrollPane2.setViewportView(txtAreaStatus);

        jLabel15.setForeground(new java.awt.Color(255, 0, 51));
        jLabel15.setText("(Chọn theo thứ tự năm trước, tháng sau, đến ngày)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(rdoNam)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNu)
                        .addGap(0, 38, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(cboNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(cboThangSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(cboNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                            .addComponent(txtNhapLaiMatKhau)
                            .addComponent(txtMatKhau)
                            .addComponent(txtCMND)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboThangSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNu)
                    .addComponent(rdoNam))
                .addGap(30, 30, 30)
                .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(txtNhapLaiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        txtareaGhiChu.setColumns(20);
        txtareaGhiChu.setRows(5);
        jScrollPane3.setViewportView(txtareaGhiChu);

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));

        btnLuu.setBackground(new java.awt.Color(51, 0, 255));
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Thêm nhân viên khác");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnQuayLai.setText("Quay lại");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnLuu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLamMoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnQuayLai)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu)
                    .addComponent(btnLamMoi)
                    .addComponent(btnQuayLai))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout Jpanel12Layout = new javax.swing.GroupLayout(Jpanel12);
        Jpanel12.setLayout(Jpanel12Layout);
        Jpanel12Layout.setHorizontalGroup(
            Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(111, 111, 111)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpanel12Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14)
                            .addComponent(jLabel10))
                        .addGap(30, 30, 30)
                        .addGroup(Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                            .addComponent(cboChonDonVi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtChucVu)))
                    .addGroup(Jpanel12Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(153, 153, 153))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184))
            .addGroup(Jpanel12Layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(223, 223, 223))
        );
        Jpanel12Layout.setVerticalGroup(
            Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel12Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpanel12Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Jpanel12Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGroup(Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Jpanel12Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addGroup(Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(cboChonDonVi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52)
                                .addGroup(Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(Jpanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Jpanel12Layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(jLabel14))
                                    .addGroup(Jpanel12Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(40, 40, 40)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Jpanel12Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel2)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel3)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel4)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel5)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel6)
                                .addGap(45, 45, 45)
                                .addComponent(jLabel7)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel8))
                            .addGroup(Jpanel12Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(478, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Jpanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(424, 424, 424))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Jpanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboThangSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThangSinhActionPerformed
        // get from combobox cboThangSinh, ép kiểu to int type for cboThangSinh:
        String strthang = cboThangSinh.getSelectedItem().toString();
        int thang = Integer.parseInt(strthang);
        // get from combobox cboNamSinh, ép kiểu to int typpe for cboNamSinh:
        String strnam = cboNamSinh.getSelectedItem().toString();
        int nam = Integer.parseInt(strnam);
        // kiểm tra số ngày trong tháng, của các năm:
        switch (thang) {
            case 2: {
                if (nam % 400 == 0 && nam % 100 != 0) {
                    // xóa các item đã có trước, để add cái mới vào:
                    this.cboNgaySinh.removeAllItems();
                    // add 29 ngày cho tháng 2:
                    for (int i = 1; i <= 29; i++) {
                        String j = String.valueOf(i);
                        this.cboNgaySinh.addItem(j);
                    }
                } else {
                    // xóa các item đã có trước, để add cái mới vào:
                    this.cboNgaySinh.removeAllItems();
                    // add 29 ngày cho tháng 2:
                    for (int i = 1; i <= 28; i++) {
                        String j = String.valueOf(i);
                        this.cboNgaySinh.addItem(j);
                    }
                }
            }
            break;
            case 4: {
                // xóa các item đã có trước, để add cái mới vào:
                this.cboNgaySinh.removeAllItems();
                // add 29 ngày cho tháng 2:
                for (int i = 1; i <= 30; i++) {
                    String j = String.valueOf(i);
                    this.cboNgaySinh.addItem(j);
                }
            }
            break;
            case 6: {
                // xóa các item đã có trước, để add cái mới vào:
                this.cboNgaySinh.removeAllItems();
                // add 29 ngày cho tháng 2:
                for (int i = 1; i <= 30; i++) {
                    String j = String.valueOf(i);
                    this.cboNgaySinh.addItem(j);
                }
            }
            break;
            case 9: {
                // xóa các item đã có trước, để add cái mới vào:
                this.cboNgaySinh.removeAllItems();
                // add 29 ngày cho tháng 2:
                for (int i = 1; i <= 30; i++) {
                    String j = String.valueOf(i);
                    this.cboNgaySinh.addItem(j);
                }
            }
            break;
            case 11: {
                // xóa các item đã có trước, để add cái mới vào:
                this.cboNgaySinh.removeAllItems();
                // add 29 ngày cho tháng 2:
                for (int i = 1; i <= 30; i++) {
                    String j = String.valueOf(i);
                    this.cboNgaySinh.addItem(j);
                }
            }
            break;
            default: {
                // xóa các item đã có trước, để add cái mới vào:
                this.cboNgaySinh.removeAllItems();
                // add 29 ngày cho tháng 2:
                for (int i = 1; i <= 31; i++) {
                    String j = String.valueOf(i);
                    this.cboNgaySinh.addItem(j);
                }
            }
        }


    }//GEN-LAST:event_cboThangSinhActionPerformed

    private void cboNamSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamSinhActionPerformed
        this.cboNgaySinh.removeAllItems();
    }//GEN-LAST:event_cboNamSinhActionPerformed

    private void cboChonDonViActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChonDonViActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboChonDonViActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed

        // 1. Ho and ten:
        String ho = txtHo.getText();
        String ten = txtTen.getText();
        // 2. dob:
        String dob, ngay=null, thang=null, nam=null;
        try {
            ngay = cboNgaySinh.getSelectedItem().toString();
            thang = cboNgaySinh.getSelectedItem().toString();
            nam = cboNamSinh.getSelectedItem().toString();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng ngày tháng năm sinh", "Messege", 1);
        }
        if (ngay.length() != 0) {
            dob = ngay + "/" + thang + "/" + nam;
        } else {
            dob = null;
            JOptionPane.showMessageDialog(this, "Ngày sinh không đúng", "Messege", 1);
        }
        // 3. sex:
        String sex;
        if (this.rdoNam.isSelected()) {
            sex = "M";
        } else if (this.rdoNu.isSelected()) {
            sex = "F";
        } else {
            sex = "U";
        }
        // 4. CMND:
        String cmnd = txtCMND.getText();
        // 5. pw: lấy password nhập lại
        String nhaplaipw = txtNhapLaiMatKhau.getText();
        // 6. status:
        String status = txtAreaStatus.getText();
        // 7. madv, get từ cboChonDonVi, lấy Vector<Departmant>:
        Departmant departmant2;
        String madv = cboChonDonVi.getSelectedItem().toString();
        departmant2 = (Departmant) (cboChonDonVi.getSelectedItem());
        madv = String.valueOf(departmant2.getMaDV());
        int madv2 = Integer.parseInt(madv);
        // 8. chucvu:
        String chucvu = txtChucVu.getText();
        // 9. ghi chu:
        String ghichu = txtareaGhiChu.getText();
        // 10. Gọi methods insert:////InsertInTableStaff(String ho, String ten, String dob, String sex, String cmnd, String pw, String status, int madv, String chucvu, String ghichu)        
        if (txtMatKhau.getText().equals(nhaplaipw) == false) {
            JOptionPane.showMessageDialog(this, "Nhập lại mật khẩu không đúng!, xin vui lòng kiểm tra lại ", "", 1);
        } else {
            if (cboNgaySinh.getSelectedItem().equals("") == true || ho.length() == 0 || ten.length() == 0 || cmnd.length() == 0 || nhaplaipw.length() == 0 || chucvu.length() == 0 || ghichu.length() == 0 || (rdoNam.isSelected() == false && rdoNu.isSelected() == false) || status.length() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin ", "", 1);
            } else {
                StaffList.InsertInTableStaff(ho, ten, dob, sex, cmnd, nhaplaipw, status, madv2, chucvu, ghichu);
                JOptionPane.showMessageDialog(this, " Thêm nhân viên thành công.", " Thông báo", 1);     // sao để getMaNV ra, vì nó identity, sao show ra cho user thấy để login                
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed

        // 1. Ho and ten
        txtHo.setText("");
        txtTen.setText("");
        // 2. sex: how to set nó không chọn?
        rdoNam.setSelected(false);
        rdoNu.setSelected(false);
        // 3. cmnd:
        txtCMND.setText("");
        // 4. mat khau and nhaplaipw:
        txtMatKhau.setText("");
        txtNhapLaiMatKhau.setText("");
        // 5. status:
        txtAreaStatus.setText("");
        // 5. chucvu:
        txtChucVu.setText("");
        // 6. ghichu:
        txtareaGhiChu.setText("");
        // 8: sex:

    }//GEN-LAST:event_btnLamMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Jpanel12;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChonDonVi;
    private javax.swing.JComboBox<String> cboNamSinh;
    private javax.swing.JComboBox<String> cboNgaySinh;
    private javax.swing.JComboBox<String> cboThangSinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextArea txtAreaStatus;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtChucVu;
    private javax.swing.JTextField txtHo;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JPasswordField txtNhapLaiMatKhau;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextArea txtareaGhiChu;
    // End of variables declaration//GEN-END:variables
}
