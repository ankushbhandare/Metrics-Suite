/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs543;

import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import java.math.*;
import java.util.Set;


/**
 *
 * @author Ankush
 */
public class ANTLRWorks extends javax.swing.JPanel {

    public JavaMetrics jm;
    public Integer identCount;
    public String FilePath;
    public String FileName;
    public Integer uniqueKeywords;
    public Integer uniqueIdentifiers;
    public Integer uniqueConstants;
    public Integer uniqueSpecial;    
    public Integer mccabeValues;
    public Integer n1,n2,N1,N2;
    public Integer programLength;
    public Integer programVocabulary;
    public Double volume;
    public Double difficulty;
    public Double effort;
    public Double Time;
    public Double NoOfBugs;
    public String TextAreaFill;
    public Long len;
    public Integer commentCount;
    public Integer whiteSpace;
    public Double commentPercent;
    public Iteration4 iter;
    
    public ANTLRWorks(String path, String name, Long len, Iteration4 i) {
        initComponents();
        FilePath = path;
        FileName = name;
        this.len = len;
        this.iter = i;
    }

    public void ANTLR() throws IOException, RecognitionException {
        JavaJavaLexer lexer = new JavaJavaLexer(new ANTLRFileStream(FilePath));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaJavaParser parser = new JavaJavaParser(tokens);
        parser.compilationUnit();
        commentCount = lexer.commentcount;
        whiteSpace = lexer.ws;
        this.uniqueConstants = JavaMetrics.uniqueConstants.size();
        this.uniqueKeywords = JavaMetrics.uniqueKeywords.size();
        this.uniqueSpecial = JavaMetrics.uniqueSpecial.size();
        this.uniqueIdentifiers = JavaMetrics.uniqueIdentifiers.size();
        this.mccabeValues = JavaMetrics.mccabeValues.size();

        this.n1 = this.uniqueKeywords + this.uniqueSpecial;
        this.n2 = this.uniqueConstants + this.uniqueIdentifiers;

        this.N1 = parser.keywordCount + parser.specialcount;
        this.N2 = parser.identcount + lexer.constantcount;
        this.programLength = this.N1+this.N2;
        this.programVocabulary = this.n1+this.n2;
        this.volume = this.programLength*(Math.log(this.programVocabulary)/Math.log(2));
        this.difficulty = (this.n1/2.0)*(this.N2/this.n2);
        this.effort = (double)this.difficulty * this.volume;
        this.Time = this.effort/18;
        this.NoOfBugs = this.volume/3000;
        this.commentPercent = ((Double.parseDouble(this.commentCount.toString()))/this.len)*100.0;
        String len = this.len.toString();
        String mcCabe = new String();
        String methodcount = new String();
        String dit = new String();
        String childclass = new String();
        
        ////
        
        ////
        
        for(String s: JavaMetrics.mccabeValues){
            mcCabe = mcCabe + s + "\n  ";
        }
//        
//        Set<String> s1 =  JavaMetrics.methodCount.keySet();
//        Set<String> s2 =  JavaMetrics.DIT.keySet();
//        Set<String> s3 =  JavaMetrics.classChildMap.keySet();
//        
//        for(String st: s1){
//            methodcount = JavaMetrics.methodCount.get(st).toString() + "\n  ";
//        }   
//            
//        for(String st: s2){
//            dit = JavaMetrics.DIT.get(st).toString() + "\n  ";
//        }   
//        
//        for(String st: s3){
//            childclass = JavaMetrics.classChildMap.get(st).toString()+ "\n  ";
//        }   
        
        TextAreaFill = "Program Name: " + this.FileName + "\n" +
                "File length in bytes: " + this.len + "\n" +
                "File White space: " + this.whiteSpace + "\n" +
                "File Comment space in bytes: " + this.commentCount + "\n" +
                "Comment percentage of file: " + this.commentPercent  + " %" + "\n" + 
                "Halstead metrices: " + "\n" +
                "   Unique operators: " + this.n1 + "\n" +
                "   Unique operands: " + this.n2 + "\n" +
                "   Total operators: " + this.N1 + "\n" +
                "   Unique operands: " + this.N2 + "\n" +
                "   Program length (N): " + this.programLength + "\n" +
                "   Program vocabulary (n) " + this.programVocabulary + "\n" +
                "   Volume = " + this.volume + "\n" +
                "   Difficulty = " + this.difficulty + "\n" + 
                "   Effort = " + this.effort + "  Time =" + this.Time + "(" + this.Time/60.0 + " minutes or " + this.Time/(60*60) + " hours " + this.Time/(60*60*160) + " person months " +  ")"+ "\n" +
                "   Bugs Expected = " + this.NoOfBugs +"\n\n\n" +
                "McCabe's Cyclomatic Complexity: " + "\n" + mcCabe;
        
        
        Set<String> countMethod = JavaMetrics.methodCount.keySet();
        for(String s: countMethod){
            methodcount = methodcount + s + " : " + JavaMetrics.methodCount.get(s) + " \n ";
        }
        Set<String> numChild = JavaMetrics.classChildMap.keySet();
        for(String s: numChild){
            childclass = childclass + s + " : " + JavaMetrics.classChildMap.get(s) + " \n ";
        }
        Set<String> depthTree = JavaMetrics.DIT.keySet();
        for(String s: depthTree){
            dit = dit + s + " : " + JavaMetrics.DIT.get(s) + " \n ";
        }
        String dis = "Program Name " + this.FileName + "\n\n\n" + 
                "Number of Children ==> \n" + childclass + "\n\n\n"
                + " Depth of Inheritance Tree ==> \n" + dit + "\n\n\n"
                + " Methods per class ==> \n" + methodcount  + "\n\n\n";
        
        iter.setDisplay(dis);
         
        txtAreaStats.setText(TextAreaFill);
        JavaMetrics.mccabeValues.clear();
        JavaMetrics.uniqueConstants.clear();
        JavaMetrics.uniqueIdentifiers.clear();
        JavaMetrics.uniqueSpecial.clear();
        JavaMetrics.uniqueKeywords.clear();
        JavaMetrics.DIT.clear();
        JavaMetrics.classChildMap.clear();
        JavaMetrics.methodCount.clear();
        
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaStats = new javax.swing.JTextArea();

        txtAreaStats.setColumns(20);
        txtAreaStats.setRows(5);
        jScrollPane1.setViewportView(txtAreaStats);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtAreaStats;
    // End of variables declaration//GEN-END:variables
}
