/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ProfInfoHandler;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * 학생 명단을 조회하는 Boundary Class.
 *
 * @author 박지동
 */
public class StuListView extends javax.swing.JFrame {

    /**
     * Creates new form StuListView
     */
    String c_id;
    String s_id;
    private boolean chk;
    ArrayList<String> sArray = new ArrayList<>();
    DefaultTableModel model; //테이블을 만든다
    ProfInfoHandler pHandler = new ProfInfoHandler();
    GradeGrantView gradeGrantView;

    public StuListView(String c_id) {
        this.c_id = c_id;
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        setTitle("학생 명단");
        setVisible(true);
        setResizable(false); //프레임 크기 조정 x
        setLocationRelativeTo(null); //프레임을 중앙으로 배치.

        model = (DefaultTableModel) this.stuinfoTable.getModel();

        model.setNumRows(0); //테이블에 정보가 중복해서 저장되지 않도록 테이블 초기화

        if (pHandler.showStuState(c_id) == true) {
            for (int i = 0; i < pHandler.getShowStuInfoData().size(); i = i + 4) {
                model.addRow(new Object[]{
                    pHandler.getShowStuInfoData().get(i),
                    pHandler.getShowStuInfoData().get(i + 1),
                    pHandler.getShowStuInfoData().get(i + 2),
                    pHandler.getShowStuInfoData().get(i + 3),
                    Boolean.FALSE

                });
            }
        } else {
            JOptionPane.showMessageDialog(null, "테이블 조회에 실패하였습니다.", "테이블 조회 실패", JOptionPane.WARNING_MESSAGE);
        }
    }

    public StuListView() {

    }

    StuListView(ArrayList<String> array) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stuinfoTable = new javax.swing.JTable();
        jGradeGrantBtn = new javax.swing.JButton();
        jCancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("굴림", 0, 36)); // NOI18N
        jLabel1.setText("학생 명단");

        stuinfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "학생번호", "학생이름", "학점", "학점 값", "항목 체크"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stuinfoTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(stuinfoTable);
        if (stuinfoTable.getColumnModel().getColumnCount() > 0) {
            stuinfoTable.getColumnModel().getColumn(0).setResizable(false);
            stuinfoTable.getColumnModel().getColumn(1).setResizable(false);
            stuinfoTable.getColumnModel().getColumn(2).setResizable(false);
            stuinfoTable.getColumnModel().getColumn(3).setResizable(false);
            stuinfoTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jGradeGrantBtn.setText("성적 부여");
        jGradeGrantBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jGradeGrantBtnActionPerformed(evt);
            }
        });

        jCancelBtn.setText("닫기");
        jCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jGradeGrantBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jGradeGrantBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jGradeGrantBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jGradeGrantBtnActionPerformed
        // TODO add your handling code here: 
        sArray.clear();
        if (stuinfoTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "성적을 부여할 학생을 선택해주세요.", "선택 실패", JOptionPane.WARNING_MESSAGE);
        } else {
            for (int i = 0; i < stuinfoTable.getRowCount(); i++) {
                chk = (boolean) stuinfoTable.getValueAt(i, 4);
                if (chk == true) {
                    s_id = (String) stuinfoTable.getValueAt(i, 0);
                    sArray.add(s_id);
                }
            }

            if (!sArray.isEmpty()) {
                gradeGrantView = new GradeGrantView();
                gradeGrantView.setUseInfo(sArray, c_id, model);
            } else {
                JOptionPane.showMessageDialog(null, "체크 박스를 선택해주세요.", "선택 실패", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_jGradeGrantBtnActionPerformed


    private void jCancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jCancelBtnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jCancelBtn;
    private javax.swing.JButton jGradeGrantBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable stuinfoTable;
    // End of variables declaration//GEN-END:variables
}