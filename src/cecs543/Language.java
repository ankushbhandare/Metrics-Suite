/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs543;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Ankush
 */
public class Language extends javax.swing.JFrame {

    /**
     * Creates new form Language
     */
    
    
    public FP fp;
    public Language(FP fp) {
        initComponents();
        this.fp = fp;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chkBoxAssembler = new javax.swing.JCheckBox();
        chkBoxAda = new javax.swing.JCheckBox();
        chkBoxC = new javax.swing.JCheckBox();
        chkBoxCPP = new javax.swing.JCheckBox();
        chkBoxCS = new javax.swing.JCheckBox();
        chkBoxCobol = new javax.swing.JCheckBox();
        chkBoxFortran = new javax.swing.JCheckBox();
        chkBoxHtml = new javax.swing.JCheckBox();
        chkBoxJava = new javax.swing.JCheckBox();
        chkBoxJavaScript = new javax.swing.JCheckBox();
        chkBoxVBScript = new javax.swing.JCheckBox();
        chkBoxVisualBasic = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        btnDone = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        chkBoxAssembler.setText("Assembler");

        chkBoxAda.setText("Ada 95");

        chkBoxC.setText("C");

        chkBoxCPP.setText("C++");
        chkBoxCPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBoxCPPActionPerformed(evt);
            }
        });

        chkBoxCS.setText("C#");

        chkBoxCobol.setText("COBOL");

        chkBoxFortran.setText("FORTRAN");

        chkBoxHtml.setText("HTML");

        chkBoxJava.setText("Java");

        chkBoxJavaScript.setText("JavaScript");

        chkBoxVBScript.setText("VBScript");

        chkBoxVisualBasic.setText("Visual Basic");

        jLabel1.setText("Select One Language");

        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDone)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chkBoxAda)
                        .addComponent(chkBoxAssembler)
                        .addComponent(chkBoxC)
                        .addComponent(chkBoxCPP)
                        .addComponent(chkBoxCS)
                        .addComponent(chkBoxCobol)
                        .addComponent(jLabel1)
                        .addComponent(chkBoxFortran)
                        .addComponent(chkBoxHtml)
                        .addComponent(chkBoxJava)
                        .addComponent(chkBoxJavaScript)
                        .addComponent(chkBoxVBScript)
                        .addComponent(chkBoxVisualBasic)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(chkBoxAssembler)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkBoxAda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkBoxC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkBoxCPP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkBoxCS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkBoxCobol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkBoxFortran)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkBoxHtml)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkBoxJava)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkBoxJavaScript)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkBoxVBScript)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkBoxVisualBasic)
                .addGap(18, 18, 18)
                .addComponent(btnDone)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        // TODO add your handling code here:
        FP.LangYaMenu = 0;
        
        if(chkBoxAda.isSelected())
        {
            calculation c = new calculation(fp);
            FileMultipleNameFrame fmnf = new FileMultipleNameFrame("Ada", fp, c);
            fmnf.setVisible(true);
            String tabName = fmnf.getTabName();
            
            c.TabName = tabName;
            
            //fp.openInTab.put("Ada", c);
            FP.cal.put(1,c);
            FP.i[1] = 1;
            FP.visited[1] = 1;
            c.txtLang.setText("Ada");
           
        }
        if(chkBoxAssembler.isSelected())
        {
            calculation c = new calculation(fp);
            FileMultipleNameFrame fmnf = new FileMultipleNameFrame("Assembler", fp, c);
            fmnf.setVisible(true);
            String tabName = fmnf.getTabName();
            
            c.TabName = tabName;
           
            //fp.openInTab.put("Assembler", c);
            FP.cal.put(0,c);
            FP.i[0] = 0;
            c.txtLang.setText("Assembler");
            FP.visited[0] = 0;
        }
        if(chkBoxC.isSelected())
        {
            calculation c = new calculation(fp);
            FileMultipleNameFrame fmnf = new FileMultipleNameFrame("C", fp, c);
            fmnf.setVisible(true);
            String tabName = fmnf.getTabName();
            
            
            //fp.TabbedPanel.addTab(tabName,c );
            c.TabName = tabName;
           
            //fp.openInTab.put("C", c);
            FP.cal.put(2,c);
            FP.i[2] = 2;
            c.txtLang.setText("C");
            FP.visited[2] = 2;
        }
        if(chkBoxCPP.isSelected())
        {
            calculation c = new calculation(fp);
            FileMultipleNameFrame fmnf = new FileMultipleNameFrame("C++", fp, c);
            fmnf.setVisible(true);
            String tabName = fmnf.getTabName();
            
            c.TabName = tabName;
            FP.cal.put(3,c);
            FP.i[3] = 3;
            c.txtLang.setText("C++");
            FP.visited[3] = 3;
        }
        if(chkBoxCS.isSelected())
        {
            calculation c = new calculation(fp);
            FileMultipleNameFrame fmnf = new FileMultipleNameFrame("C#", fp, c);
            fmnf.setVisible(true);
            String tabName = fmnf.getTabName();
            
            //fp.TabbedPanel.addTab(tabName,c );
            c.TabName = tabName;
            
            //fp.openInTab.put("C#", c);
            FP.cal.put(4,c);
            FP.i[4] = 4;
            c.txtLang.setText("C#");
            FP.visited[4] = 4;
        }
        if(chkBoxCobol.isSelected())
        {
            calculation c = new calculation(fp);
            FileMultipleNameFrame fmnf = new FileMultipleNameFrame("COBOL", fp, c);
            fmnf.setVisible(true);
            String tabName = fmnf.getTabName();
            c.TabName = "Cobol";
            fp.root = (DefaultMutableTreeNode) fp.model.getRoot();
            
            FP.cal.put(5,c);
            FP.i[5] = 5;
            c.txtLang.setText("COBOL");
            FP.visited[5] = 5;
        }
        if(chkBoxFortran.isSelected())
        {
            calculation c = new calculation(fp);
            FileMultipleNameFrame fmnf = new FileMultipleNameFrame("Fortran", fp, c);
            fmnf.setVisible(true);
            String tabName = fmnf.getTabName();
            c.TabName = "Fortran";
            
            FP.cal.put(6,c);
            FP.i[6] = 6;
            c.txtLang.setText("Fortran");
            FP.visited[6] = 6;
        }
        if(chkBoxHtml.isSelected())
        {
            calculation c = new calculation(fp);
            FileMultipleNameFrame fmnf = new FileMultipleNameFrame("HTML", fp, c);
            fmnf.setVisible(true);
            String tabName = fmnf.getTabName();
            c.TabName = "HTML";
            
            FP.cal.put(7,c);
            FP.i[7] = 7;
            c.txtLang.setText("HTML");
            FP.visited[7] = 7;
        }
        if(chkBoxJava.isSelected())
        {
            calculation c = new calculation(fp);
            FileMultipleNameFrame fmnf = new FileMultipleNameFrame("Java", fp, c);
            fmnf.setVisible(true);
            String tabName = fmnf.getTabName();
            c.TabName = "Java";
            FP.cal.put(8,c);
            FP.i[8] = 8;
            c.txtLang.setText("Java");
            FP.visited[8] = 8;
        }
        if(chkBoxJavaScript.isSelected())
        {
            calculation c = new calculation(fp);
            FileMultipleNameFrame fmnf = new FileMultipleNameFrame("JavaScript", fp, c);
            fmnf.setVisible(true);
            String tabName = fmnf.getTabName();
            c.TabName = "JavaScript";
            FP.cal.put(9,c);
            FP.i[9] = 9;
            c.txtLang.setText("Java Script");
            FP.visited[9] = 9;
        }
        if(chkBoxVBScript.isSelected())
        {
            calculation c = new calculation(fp);
            FileMultipleNameFrame fmnf = new FileMultipleNameFrame("VB Script", fp, c);
            fmnf.setVisible(true);
            String tabName = fmnf.getTabName();
            c.TabName = "VB Script";
            FP.cal.put(10,c);
            FP.i[10] = 10;
            c.txtLang.setText("VB Script");
            FP.visited[10] = 10;
        }
        if(chkBoxVisualBasic.isSelected())
        {
            calculation c = new calculation(fp);
            FileMultipleNameFrame fmnf = new FileMultipleNameFrame("Visual Basic", fp, c);
            fmnf.setVisible(true);
            String tabName = fmnf.getTabName();
            c.TabName = "Visual Basic";
            FP.cal.put(11,c);
            FP.i[11] = 11;
            c.txtLang.setText("Visual Basic");
            FP.visited[11] = 11;
            /*for(int j= 0;j<12;j++){
                System.out.println(FP.i[j]);
            }*/
            
        }
        fp.model.reload();
        this.setVisible(false);
        
            
        System.out.println("Printing Sets");
        for(String S: fp.openInTab.keySet()){
            System.out.println(S);
        }
        
        for(String S: fp.smiOpenInTab.keySet()){
            System.out.println(S);
        }
        
        for(String S: fp.javaOpenInTab.keySet()){
            System.out.println(S);
        }
        
    }//GEN-LAST:event_btnDoneActionPerformed

    private void chkBoxCPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBoxCPPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkBoxCPPActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Language.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Language.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Language.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Language.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Language(new FP()).setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDone;
    public static javax.swing.JCheckBox chkBoxAda;
    public static javax.swing.JCheckBox chkBoxAssembler;
    public static javax.swing.JCheckBox chkBoxC;
    public static javax.swing.JCheckBox chkBoxCPP;
    public static javax.swing.JCheckBox chkBoxCS;
    public static javax.swing.JCheckBox chkBoxCobol;
    public static javax.swing.JCheckBox chkBoxFortran;
    public static javax.swing.JCheckBox chkBoxHtml;
    public static javax.swing.JCheckBox chkBoxJava;
    public static javax.swing.JCheckBox chkBoxJavaScript;
    public static javax.swing.JCheckBox chkBoxVBScript;
    public static javax.swing.JCheckBox chkBoxVisualBasic;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
