/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cecs543;

//import com.sun.java_cup.internal.runtime.Symbol;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author Ankush
 */
public class JavaMetrics {
    static Set<String> uniqueKeywords = new LinkedHashSet<String>();
    static Set<String> uniqueIdentifiers= new LinkedHashSet<String>();
    //static Set<Symbol> uIDSym=new LinkedHashSet<Symbol>();
    static Integer noOfMethods = 0;
    static Integer noOfChildren = 0;
    static Set<String> uniqueConstants = new LinkedHashSet<String>();
    static Set<String> uniqueSpecial = new LinkedHashSet<String>();    
    static Set<String> mccabeValues = new LinkedHashSet<String>();
    static Map<String, Integer> classChildMap = new HashMap<String, Integer>();
    static Map<String, Integer> DIT = new HashMap<String, Integer>();
    static Map<String, Integer> methodCount = new HashMap<String, Integer>();
}
