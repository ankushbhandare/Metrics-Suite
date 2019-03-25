/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs543;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Ankush
 */
class MyFileFilter extends FileFilter{
    
    final static String ext = "ms";

    @Override
    public boolean accept(File file) {
        if(file.isDirectory())
            return true;
        String s = file.getName();
        int i = s.lastIndexOf('.');
        if(i > 0 &&  i < s.length() - 1) {
            String extension = 
            s.substring(i+1).toLowerCase();
               if (ext.equals(extension)) 
                   return true;
               else 
                   return false;
            }
         return false;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        return "Metric Suite Files(*.ms)";
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
