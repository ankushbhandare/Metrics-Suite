/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs543;

import static cecs543.calculation.totalClassCount;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ContextMenu;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.antlr.runtime.RecognitionException;

/**
 *
 * @author Ankush
 */


public class FP extends javax.swing.JFrame {

    /**
     * Creates new form FP
     */
    public String NameCheck;
    
    public JTree tree;
    public List<File> codeFiles;
    public static int[] visited;
    public DefaultTreeModel model;
    public DefaultMutableTreeNode root;
    public DefaultMutableTreeNode root2;
    public DefaultMutableTreeNode proName;
    public static Map<Integer, calculation> cal;
    public Map<String, calculation> openInTab;
    public Map<String, ANTLRWorks> javaOpenInTab;
    public Map<String, calculation> singleFPOpenInTab;
    public TreePath path;
    public Rectangle pathBounds;
    public Map<String, SMI> smiOpenInTab;
    public static ArrayList<calculation> calArr;
    public static ArrayList<ANTLRWorks> antlrWorks;
    public static Integer[] i;
    public static int[] avgCS;
    public static int LangYaMenu;
    public static FileReader fin=null;
    public static BufferedReader bufferReader; 
    public boolean isSaved = false;
    public String fpFileName;
    WindowListener exitListener = new WindowAdapter(){
        public void windowClosing(WindowEvent e) {
            try{        
            int confirm = JOptionPane.showOptionDialog(
                    null, "Are You Sure to Close Application without saving?", 
                    "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
                    
                    if (confirm == 0) {
                        System.exit(0);
                    }
                    else {
                        //saveFile();
                    }
                }
            catch(Exception exc){
                
            }
        }
    };
    public FP() {
        
        initComponents();
        this.addWindowListener(exitListener);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("CECS 543 Metrics Suite");
        codeFiles = new ArrayList<File>();
        proName = new DefaultMutableTreeNode();
        root = new DefaultMutableTreeNode();
        root2 = new DefaultMutableTreeNode();
        cal = new HashMap<Integer,calculation>();
        openInTab =  new HashMap<String, calculation>() ;
        smiOpenInTab = new HashMap<String, SMI>();
        singleFPOpenInTab = new HashMap<String, calculation>();
        javaOpenInTab = new HashMap<String, ANTLRWorks>();
        calArr = new ArrayList<calculation>();
        i = new Integer[12];
        visited = new int[12];
        for (int j = 0;j<12;j++){
            i[j] = -1;
            visited[j] = -1;
        }
        LangYaMenu = -1;
        avgCS = new int[12];
        avgCS[0]=209;
        avgCS[1]=154;
        avgCS[2]=148;
        avgCS[3]=59;
        avgCS[4]=58;
        avgCS[5]=80;
        avgCS[6]=90;
        avgCS[7]=43;
        avgCS[8]=55;
        avgCS[9]=54;
        avgCS[10]=38;
        avgCS[11]=50;
        checkForName();
        this.model = (DefaultTreeModel) fileTree.getModel();
        this.root = (DefaultMutableTreeNode) this.model.getRoot(); 
            JMenuItem jDelete = new JMenuItem("Delete");
            JMenuItem jOpen = new JMenuItem("Open");
            JMenuItem jClose = new JMenuItem("Close");
            this.fileTree.addMouseListener(new MouseAdapter () {
            
            public void mousePressed(MouseEvent e){
                //System.out.println(fileTree.getParent().toString());
                if(SwingUtilities.isRightMouseButton(e))
                {
                    path = fileTree.getPathForLocation(e.getX(), e.getY());
                    pathBounds = fileTree.getUI().getPathBounds(fileTree, path);
                    if(pathBounds!=null && pathBounds.contains(e.getX(), e.getY())){
                        JPopupMenu menu = new JPopupMenu();
                        menu.add(jOpen);
                        menu.add(jClose);
                        menu.add(jDelete);
                        menu.show(fileTree, pathBounds.x, pathBounds.y + pathBounds.height);
                        
 
                }
                }
            }
            });
            
                        jOpen.addActionListener(new ActionListener() {
                            
                            public void actionPerformed(ActionEvent e){
                                try{
                                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                                String tabName = path.getLastPathComponent().toString();
                                for(String s: javaOpenInTab.keySet()){
                                    System.out.println(s);
                                }
                                
                                //for(int i=0;i<openInTab.size();i++){
                                    if(openInTab.containsKey(tabName)){
                                        TabbedPanel.addTab(tabName, openInTab.get(tabName));
                                        TabbedPanel.setSelectedComponent(openInTab.get(tabName));
                                }
                                //}
                                if (tabName.equals("SMI")) {
                                    TabbedPanel.addTab(tabName, smiOpenInTab.get(tabName));
                                    TabbedPanel.setSelectedComponent(smiOpenInTab.get(tabName));
                                }
                                if (javaOpenInTab.containsKey(tabName)){

                                    TabbedPanel.addTab(tabName, javaOpenInTab.get(tabName));
                                    TabbedPanel.setSelectedComponent(javaOpenInTab.get(tabName));
                                }
                                }
                                catch(Exception exp){
                                    
                                }
                            }
                        });
                        
                        jDelete.addActionListener(new ActionListener() {
                            
                            public void actionPerformed(ActionEvent e){
                                try{
                                int a[];
                                a = fileTree.getSelectionRows();
                                System.out.println("Selected Row is " + a[0]);
                                //fileTree.removeSelectionRows(a);
                                a[0] = a[0]-2;
                                String tabName = path.getLastPathComponent().toString();
                                System.out.println("TabSelected is " + tabName);
                                
                                int confirm = JOptionPane.showOptionDialog(
                                                null, "Are You Sure you want to delete this tab?", 
                                                "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
                                                JOptionPane.QUESTION_MESSAGE, null, null, null);
                                
                                if (confirm == 0)
                                {
                                
                                    for(int i=0;i<openInTab.size();i++){
                                        if(openInTab.containsKey(tabName)){
                                            TabbedPanel.remove(openInTab.get(tabName));
                                            openInTab.remove(tabName);
                                        }
                                    }
                                    if (tabName.equals("SMI")) {
                                        SMI.count -= 1;
                                        TabbedPanel.remove(smiOpenInTab.get(tabName));
                                        smiOpenInTab.remove(tabName);
                                    }
                                    else
                                    if (javaOpenInTab.containsKey(tabName)){
                                        TabbedPanel.remove(javaOpenInTab.get(tabName));
                                        javaOpenInTab.remove(tabName);
                                    }
                                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                                model.removeNodeFromParent(node);
                                model.reload();
                                a = fileTree.getSelectionRows();
                                System.out.println(a[0]);
                                fileTree.removeSelectionRows(a);
                                
                                }
                                
                                }
                                catch(Exception exp){
                                    
                                }
                            }
                        });
                        jClose.addActionListener(new ActionListener() {
                            
                            public void actionPerformed(ActionEvent e){
                                try{

                                String tabName = path.getLastPathComponent().toString();
                                System.out.println("TabSelected is " + tabName);

                                    for(int i=0;i<openInTab.size();i++){
                                        if(openInTab.containsKey(tabName)){
                                            TabbedPanel.remove(openInTab.get(tabName));
                                        }
                                    }
                                    if (tabName.equals("SMI")) {
                                        TabbedPanel.remove(smiOpenInTab.get(tabName));
                                    }
                                    else
                                    if (javaOpenInTab.containsKey(tabName)){
                                        TabbedPanel.remove(javaOpenInTab.get(tabName));
                                    }
                                }
                                catch(Exception exp){
                                    
                                }
                            }
                            
                        });
        
       // });
        
        
    }

    public void checkForName(){
        //String name = NewProject.projectName;

        try {
        if (this.NameCheck == null){
            this.jMenuHelp.setEnabled(false);
            this.jMenuEdit.setEnabled(false);
            this.jMenuMetrices.setEnabled(false);
            this.jMenuProjectCode.setEnabled(false);
            this.jMenuPref.setEnabled(false);
            this.jMenuSave.setEnabled(false);
        }
        else{
            this.jMenuHelp.setEnabled(true);
            this.jMenuEdit.setEnabled(true);
            this.jMenuMetrices.setEnabled(true);
            this.jMenuProjectCode.setEnabled(true);
            this.jMenuPref.setEnabled(true);
            this.jMenuSave.setEnabled(true);
        }
        }
        catch(Exception e){
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane2 = new javax.swing.JSplitPane();
        TabbedPanel = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        fileTree = new javax.swing.JTree();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuNew = new javax.swing.JMenuItem();
        jMenuOpen = new javax.swing.JMenuItem();
        jMenuSave = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuPref = new javax.swing.JMenu();
        miLanguage = new javax.swing.JMenuItem();
        jMenuMetrices = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItemSMI = new javax.swing.JMenuItem();
        jMenuProjectCode = new javax.swing.JMenu();
        jMenuAddCode = new javax.swing.JMenuItem();
        jMenuProCodeStats = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TabbedPanel.setBackground(new java.awt.Color(255, 0, 0));
        TabbedPanel.setPreferredSize(new java.awt.Dimension(1000, 600));
        TabbedPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabbedPanelMouseClicked(evt);
            }
        });
        jSplitPane2.setRightComponent(TabbedPanel);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Project");
        fileTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        fileTree.setAutoscrolls(true);
        fileTree.setPreferredSize(new java.awt.Dimension(107, 16));
        fileTree.setRootVisible(false);
        fileTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fileTreeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(fileTree);

        jSplitPane2.setLeftComponent(jScrollPane1);

        jMenuFile.setText("File");
        jMenuFile.setName("FileMenu"); // NOI18N

        jMenuNew.setText("New");
        jMenuNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNewActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuNew);

        jMenuOpen.setText("Open");
        jMenuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuOpenActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuOpen);

        jMenuSave.setText("Save");
        jMenuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSaveActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuSave);

        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItem4);

        jMenuBar1.add(jMenuFile);

        jMenuEdit.setText("Edit");
        jMenuBar1.add(jMenuEdit);

        jMenuPref.setText("Preferences");

        miLanguage.setText("Language");
        miLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLanguageActionPerformed(evt);
            }
        });
        jMenuPref.add(miLanguage);

        jMenuBar1.add(jMenuPref);

        jMenuMetrices.setText("Metrics");

        jMenu3.setText("Function Points");

        jMenuItem5.setText("Enter FP data");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuMetrices.add(jMenu3);

        jMenuItemSMI.setText("SMI");
        jMenuItemSMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSMIActionPerformed(evt);
            }
        });
        jMenuMetrices.add(jMenuItemSMI);

        jMenuBar1.add(jMenuMetrices);

        jMenuProjectCode.setText("Project Code");

        jMenuAddCode.setText("Add Code");
        jMenuAddCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAddCodeActionPerformed(evt);
            }
        });
        jMenuProjectCode.add(jMenuAddCode);

        jMenuProCodeStats.setText("Project Code Statistics");
        jMenuProCodeStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuProCodeStatsActionPerformed(evt);
            }
        });
        jMenuProjectCode.add(jMenuProCodeStats);

        jMenuBar1.add(jMenuProjectCode);

        jMenuHelp.setText("Help");
        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNewActionPerformed
        // TODO add your handling code here:
        NewProject np = new NewProject(this);
        np.setVisible(true);
        //treeFileExp.
        //this.setVisible(false);
    }//GEN-LAST:event_jMenuNewActionPerformed

    private void miLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLanguageActionPerformed
        // TODO add your handling code here:
        Language l = new Language(this);
        l.setVisible(true);
    }//GEN-LAST:event_miLanguageActionPerformed

    
    private void jMenuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuOpenActionPerformed
        // TODO add your handling code here:
        int count =0;
        String name2=null;
        
        JFileChooser openFile = new JFileChooser();
        openFile.setFileFilter(new MyFileFilter());
        
        FP fp = new FP();
        if(openFile.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
        {
//            FP fp = new FP();
//            fp.setVisible(true);
//            this.setVisible(false);
            
            try {
                File file=openFile.getSelectedFile();
                String name = file.getPath();
                name2 = name;
                String onlyName = file.getName();
                //fp = new FP();
                fp.setVisible(true);
                this.setVisible(false);
                
                fin = new FileReader(name);
                bufferReader = new BufferedReader(fin);
                Integer totalCount = Integer.parseInt(bufferReader.readLine());
                String line = null;
                String proNamename = bufferReader.readLine();
                fp.setTitle("CECS 543 Metrics Suite - "+ onlyName);
                NewProject.projectName = onlyName;
                fp.NameCheck = fp.proName.toString();
                fp.checkForName();
                fp.root = (DefaultMutableTreeNode) fp.model.getRoot(); 
                fp.proName = new DefaultMutableTreeNode(proNamename);
                fp.root.add(fp.proName);
                fp.proName.setAllowsChildren(true);
                fp.proName.setParent(fp.root);
                fp.model.reload();
                
                //calculation c = new calculation();
                for(int i=0;i<totalCount;i++){
                    line = bufferReader.readLine();
                    
                    if(line.equals("cecs543.calculation")){
                        calculation c = new calculation(fp);
                        Integer va;
                        fin = new FileReader(name);
                        line = bufferReader.readLine();
                        String na = line;
                        fp.TabbedPanel.add(line, c);
                        DefaultMutableTreeNode calName = new DefaultMutableTreeNode(line);
                        fp.proName.add(calName);
                        line = bufferReader.readLine();
                        c.txtEI.setText(line);
                        line = bufferReader.readLine();
                        c.txtEO.setText(line);
                        line = bufferReader.readLine();
                        c.txtEInq.setText(line);
                        line = bufferReader.readLine();
                        c.txtILF.setText(line);
                        line = bufferReader.readLine();
                        c.txtEIF.setText(line);
            
                        line = bufferReader.readLine();
                        c.txtEICalc.setText(line);
                        line = bufferReader.readLine();
                        c.txtEOCalc.setText(line);
                        line = bufferReader.readLine();
                        c.txtEInqCalc.setText(line);
                        line = bufferReader.readLine();
                        c.txtILFCalc.setText(line);
                        line = bufferReader.readLine();
                        c.txtEIFCalc.setText(line);
                                
                        line = bufferReader.readLine();
                        if(line.equals(c.rBtnSEI.getText()))    
                            c.rBtnSEI.doClick();
                        if(line.equals(c.rBtnAEI.getText()))    
                            c.rBtnAEI.doClick();
                        if(line.equals(c.rBtnCEI.getText()))    
                            c.rBtnCEI.doClick();
            
                        line = bufferReader.readLine();
                        if(line.equals(c.rBtnSEO.getText()))    
                            c.rBtnSEO.doClick();
                        if(line.equals(c.rBtnAEO.getText()))    
                            c.rBtnAEO.doClick();
                        if(line.equals(c.rBtnCEO.getText()))    
                            c.rBtnCEO.doClick();
                        
                        line = bufferReader.readLine();
                        if(line.equals(c.rBtnSEInq.getText()))    
                            c.rBtnSEInq.doClick();
                        if(line.equals(c.rBtnAEInq.getText()))    
                            c.rBtnAEInq.doClick();
                        if(line.equals(c.rBtnCEInq.getText()))    
                            c.rBtnCEInq.doClick();
          
                        line = bufferReader.readLine();
                        if(line.equals(c.rBtnSILF.getText()))    
                            c.rBtnSILF.doClick();
                        if(line.equals(c.rBtnAILF.getText()))    
                            c.rBtnAILF.doClick();
                        if(line.equals(c.rBtnCILF.getText()))    
                            c.rBtnCILF.doClick();
                            
                        line = bufferReader.readLine();
                        if(line.equals(c.rBtnSEIF.getText()))    
                            c.rBtnSEIF.doClick();
                        if(line.equals(c.rBtnAEIF.getText()))    
                            c.rBtnAEIF.doClick();
                        if(line.equals(c.rBtnCEIF.getText()))    
                            c.rBtnCEIF.doClick();
            
                        line = bufferReader.readLine();
                        c.txtComputeFP.setText(line);
                        line = bufferReader.readLine();
                        c.txtVA.setText(line);
                        line = bufferReader.readLine();
                        c.txtCCS.setText(line);
                        line = bufferReader.readLine();
                        
                        c.txtLang.setText(line);
                        line = bufferReader.readLine();
                        c.txtTCCalc.setText(line);

                        c.q1 = Integer.parseInt(bufferReader.readLine());
                        c.q2 = Integer.parseInt(bufferReader.readLine());
                        c.q3 = Integer.parseInt(bufferReader.readLine());
                        c.q4 = Integer.parseInt(bufferReader.readLine());
                        c.q5 = Integer.parseInt(bufferReader.readLine());
                        c.q6 = Integer.parseInt(bufferReader.readLine());
                        c.q7 = Integer.parseInt(bufferReader.readLine());
                        c.q8 = Integer.parseInt(bufferReader.readLine());
                        c.q9 = Integer.parseInt(bufferReader.readLine());
                        c.q10 = Integer.parseInt(bufferReader.readLine());
                        c.q11 = Integer.parseInt(bufferReader.readLine());
                        c.q12 = Integer.parseInt(bufferReader.readLine());
                        c.q13 = Integer.parseInt(bufferReader.readLine());
                        c.q14 = Integer.parseInt(bufferReader.readLine());
                        fp.openInTab.put(na, c);
                    }
                    else
                    if(line.equals("cecs543.SMI"))
                    {
                        try{
                            
                            SMI smi = new SMI(fp);
                            line = bufferReader.readLine();
                            fp.smiOpenInTab.put(line, smi);
                            fp.TabbedPanel.addTab(line, smi);
                            
                            //fp.root = (DefaultMutableTreeNode) fp.model.getRoot();
                            //fp.root.add(new DefaultMutableTreeNode("SMI"));
                            DefaultMutableTreeNode smiName = new DefaultMutableTreeNode("SMI");
                            fp.proName.add(smiName);
                            
                            smi.model = new DefaultTableModel();
                            smi.jTable2.setModel(smi.model);
                            smi.model.addColumn("SMI");
                            smi.model.addColumn("Modules Added");
                            smi.model.addColumn("Modules Changed");
                            smi.model.addColumn("Modules Deleted");
                            smi.model.addColumn("Total Modules");
                            Integer row = Integer.parseInt(bufferReader.readLine());
                            Integer columns = Integer.parseInt(bufferReader.readLine());
                            fp.TabbedPanel.addTab("SMI", smi);
                            Double value;
                            String val;
                            for(int k=0;k<row;k++){
                                smi.model.addRow(new String[]{"", "","","",""});
                                for(int j=0;j<columns;j++){
                                    val = bufferReader.readLine();
                                    value = Double.parseDouble(val);
                                    smi.model.setValueAt(value, k, j);
                                }
                            }
                            fp.smiOpenInTab.put("SMI", smi);
                

                }
            catch(Exception e){
                
            }
                    }
                    else{
                        String filePath = line;
                        int index = filePath.lastIndexOf("\\");
                        String fileName = filePath.substring(index+1);
                        File file2 = new File(filePath);
                        Iteration4 i4 = new Iteration4();
                        ANTLRWorks aw = new ANTLRWorks(filePath,fileName, file2.length(), i4);
                        fp.javaOpenInTab.put(fileName, aw);
                        System.out.println(javaOpenInTab);
                        fp.TabbedPanel.addTab(fileName,aw);
                        aw.ANTLR();
                        DefaultMutableTreeNode javaName = new DefaultMutableTreeNode(fileName);
                        fp.proName.add(javaName);
                    }
                }
                
        } catch (Exception ex) {
            
        }
            }
            
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
     }
    /*     
    }//GEN-LAST:event_jMenuOpenActionPerformed

    
    public void loadSMI(FileReader fin, String name){
        try{
                fin = new FileReader(name);
                SMI smi = new SMI();
                smi.model = new DefaultTableModel();
                smi.jTable2.setModel(smi.model);
                smi.model.addColumn("SMI");
                smi.model.addColumn("Modules Added");
                smi.model.addColumn("Modules Changed");
                smi.model.addColumn("Modules Deleted");
                smi.model.addColumn("Total Modules");
                
                bufferReader = new BufferedReader(fin);
                Integer totalCount = Integer.parseInt(bufferReader.readLine());
                for(int r=1;r<=(totalCount*34);r++){
                    bufferReader.readLine();
                }
                Integer row = Integer.parseInt(bufferReader.readLine());
                Integer columns = Integer.parseInt(bufferReader.readLine());
                TabbedPanel.addTab("SMI", smi);
                Double value;
                String val;
                for(int i=0;i<row;i++){
                    smi.model.addRow(new String[]{"", "","","",""});
                    for(int j=0;j<columns;j++){
                        val = bufferReader.readLine();
                        value = Double.parseDouble(val);
                        smi.model.setValueAt(value, i, j);
                    }
                }
                

                }
            catch(Exception e){
                
            }
    }
    */
    
    private void jMenuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSaveActionPerformed
        // TODO add your handling code here:
        System.out.println("Multiple Tabs Save");
        for(String s: openInTab.keySet()){    
            System.out.println(s);
        }
        for(String s: smiOpenInTab.keySet()){    
            System.out.println(s);
        }
        for(String s: javaOpenInTab.keySet()){    
            System.out.println(s);
        }
        
        
        String Newline = "\n";
        FileOutputStream fout = null;
        JFileChooser saveFile = new JFileChooser();
        saveFile.setFileFilter(new MyFileFilter());
        
        String kuchBhi = NewProject.projectName;
        saveFile.setSelectedFile(new File(kuchBhi));
        if(saveFile.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
        {
            File file=saveFile.getSelectedFile();
            String name = file.getPath();
            name = name + ".ms";
            try{
                fout= new FileOutputStream(name);
            }
            catch(Exception e){
                
            }
            calculation c = new calculation(this);
            SMI smi = new SMI(this);
            //Integer Tcount = this.TabbedPanel.getTabCount();
            Integer Tcount = this.smiOpenInTab.size() + this.openInTab.size() + this.javaOpenInTab.size(); //+ singleFPOpenInTab.size();
            try {
                fout.write(Tcount.toString().getBytes());
                fout.write(Newline.getBytes());
                fout.write(proName.toString().getBytes());
                fout.write(Newline.getBytes());
                try{
                    File[] simpleCodeFile = new File[this.codeFiles.size()];
                    this.codeFiles.toArray(simpleCodeFile);
                for(int i=0;i<simpleCodeFile.length;i++){
                    fout.write(simpleCodeFile[i].getPath().toString().getBytes());
                    fout.write(Newline.getBytes());
                }
                }
                catch(Exception e){
                
                }
                try{
                for(String key : this.openInTab.keySet()){
                    System.out.println(key);
                    this.openInTab.get(key).saveFile(fout, name);
                }
                }
                catch(Exception e){
                    
                }
                try{
                this.smiOpenInTab.get("SMI").saveSMI(fout, name);
                }
                catch(Exception e){
                    
                }
                /*for(String key: singleFPOpenInTab.keySet()){
                    singleFPOpenInTab.get(key).saveFile(fout, name);
                }*/
            }
            catch (Exception ex) {
                //Logger.getLogger(FP.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            for(int i = 0; i< this.TabbedPanel.getTabCount(); i++){
//                Object o = this.TabbedPanel.getComponentAt(i);
//                String nameC = o.getClass().getName();
//                System.out.println("Name : " + nameC);
//                if(o.getClass().getName().equals("cecs543.calculation")){
//                    c = (calculation) o;
//                    c.saveFile(fout, name);
//                }
//                else if(o.getClass().getName().equals("cecs543.SMI")){
//                    smi = (SMI) o;
//                    smi.saveSMI(fout, name);
//                }
//                else{
//                    System.out.println("Tabb is : " + i);
//                    continue;
//                    
//                }
//            }
            
            
        }
    }//GEN-LAST:event_jMenuSaveActionPerformed

   
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        calculation calc = new calculation(this);
        FileNameFrame fnf = new FileNameFrame(this, calc);
        fnf.setVisible(true);

//        calArr.add(calc);
//        singleFPOpenInTab.put("Function Point", calc);
//        this.TabbedPanel.addTab("Function Point", calc);
//        DefaultMutableTreeNode funPoints = new DefaultMutableTreeNode("Function Point");
//        proName.add(funPoints);
//        model.reload();
        
        
        /*FP.cal.put(8,calc);
        FP.i[8] = 8;
        calc.txtLang.setText("None");*/
        /*for(int j = 0;j<12;j++){
            if (FP.i[j] == 1)
               TabbedPanel.addTab("Function Point", cal.get(i[j]));
        }*/
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItemSMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSMIActionPerformed
        // TODO add your handling code here:
        if(SMI.count == 0){
            SMI smi = new SMI(this);
            this.TabbedPanel.addTab("SMI",smi);
            //this.root = (DefaultMutableTreeNode) model.getRoot();
            DefaultMutableTreeNode smiName = new DefaultMutableTreeNode("SMI");
            this.proName.add(smiName);
            this.smiOpenInTab.put("SMI", smi);
            this.model.reload();
        }
        else{
            JOptionPane.showMessageDialog(null, "SMI tab already open");
        }
        
    }//GEN-LAST:event_jMenuItemSMIActionPerformed

    private void jMenuAddCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAddCodeActionPerformed
        // TODO add your handling code here:
        JFileChooser openFile = new JFileChooser();
        openFile.setMultiSelectionEnabled(true);
        openFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //openFile.addChoosableFileFilter(new MyFileFilter());
        if(openFile.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                
                for(int i=0;i<openFile.getSelectedFiles().length;i++){
                    codeFiles.add(openFile.getSelectedFiles()[i]);
                }
                
        }
    }//GEN-LAST:event_jMenuAddCodeActionPerformed

    private void jMenuProCodeStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuProCodeStatsActionPerformed
        // TODO add your handling code here:
            try{
                System.out.println("length is = " + codeFiles.size());
                
                 File[] simpleCodeFiles = new File[ codeFiles.size()];
                 codeFiles.toArray(simpleCodeFiles);
                 for(int i=0;i<simpleCodeFiles.length;i++){
                    String path = simpleCodeFiles[i].getPath();
                    
                    String name = simpleCodeFiles[i].getName();
                    Long l = simpleCodeFiles[i].length();
                    System.out.println(name);
                    Iteration4 i4 = new Iteration4();
                    ANTLRWorks aw = new ANTLRWorks(path, name, l,i4);
                    
                    if(javaOpenInTab.containsKey(name)){
                        
                    }
                    else{
                        this.javaOpenInTab.put(name, aw);
                        this.TabbedPanel.addTab(simpleCodeFiles[i].getName().toString(), aw);
                        this.TabbedPanel.addTab(simpleCodeFiles[i].getName().toString() + " C&K Metrics", i4);
                        DefaultMutableTreeNode cfiles = new DefaultMutableTreeNode(simpleCodeFiles[i].getName());
                        DefaultMutableTreeNode cfiles2 = new DefaultMutableTreeNode(simpleCodeFiles[i].getName() + " C&K Metrics");
                        this.proName.add(cfiles);
                        this.proName.add(cfiles2);
                        this.model.reload();
                    }
                    //antlrWorks.add(aw);
                    //System.out.println(antlrWorks);
                    //aw.FileName = codeFiles[i].getName();
                    //antlrWorks.add(aw);
                     try {
                         aw.ANTLR();
                     } catch (Exception ex) {
                         //Logger.getLogger(FP.class.getName()).log(Level.SEVERE, null, ex);
                     }
                    
                }
            }
            catch(Exception e){
                
            }
    }//GEN-LAST:event_jMenuProCodeStatsActionPerformed

    private void fileTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileTreeMouseClicked
        // TODO add your handling code here:
       
            //doPop(evt);
        
    }//GEN-LAST:event_fileTreeMouseClicked

//    private void doPop(MouseEvent e){
//        PopUpDemo menu = new PopUpDemo();
//        menu.show(e.getComponent(), e.getX(), e.getY());
//    }
    private void TabbedPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabbedPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TabbedPanelMouseClicked

    
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
            java.util.logging.Logger.getLogger(FP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FP().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTabbedPane TabbedPanel;
    public javax.swing.JTree fileTree;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuAddCode;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItemSMI;
    private javax.swing.JMenu jMenuMetrices;
    private javax.swing.JMenuItem jMenuNew;
    private javax.swing.JMenuItem jMenuOpen;
    private javax.swing.JMenu jMenuPref;
    private javax.swing.JMenuItem jMenuProCodeStats;
    private javax.swing.JMenu jMenuProjectCode;
    private javax.swing.JMenuItem jMenuSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JMenuItem miLanguage;
    // End of variables declaration//GEN-END:variables
}
