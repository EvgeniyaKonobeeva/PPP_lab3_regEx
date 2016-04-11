package com.company;

import java.io.*;

/**
 * Created by Евгения on 11.04.2016.
 */
public class PhoneCorrection {
    private String fileNameIn;
    private String fileNameOut;
    private InputStream in;
    private OutputStream out;
    String gr1 = "(\\s*(\\(*|\\-*)\\s*)"; // gr1,2
    String gr2 = "((\\d\\s*\\d\\s*\\d){1})"; //gr3, 4
    String gr3 = "(\\s*(\\-*|\\)*)\\s*)"; //gr 5, 6
    String gr4 = "((\\d\\s*\\d){1})";//gr 7,8
    private String regEx = "\\+*" + gr1 +"\\d*(\\1)" + gr2 + gr3 + "(\\1)(\\3)(\\5)(\\1)"+gr4 + "(\\5)(\\1)(\\7)";

    String reg = "(\\+*((\\(*|\\-*)\\s*)\\d?(\\s*(\\(*|\\-*)\\s*)" +
            "((\\d\\s*\\d\\s*\\d){1})(\\s*(\\)*|\\-*)\\s*)(\\s*(\\(*|\\-*)\\s*)" +
            "((\\d\\s*\\d\\s*\\d){1})(\\s*(\\)*|\\-*)\\s*)(\\s*(\\(*|\\-*)\\s*)" +
            "((\\d\\s*\\d){1})(\\s*(\\-*|\\)*)\\s*)(\\s*(\\(*|\\-*)\\s*)((\\d\\s*\\d){1}))";
    public PhoneCorrection(String _fileNameIn, String _fileNameOut){
        this.fileNameIn = _fileNameIn;
        this.fileNameOut = _fileNameOut;
        try {
            in = new BufferedInputStream(new FileInputStream(fileNameIn));
            out = new BufferedOutputStream(new FileOutputStream(fileNameOut));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void CorrectNumbers(int CountryCode){

    }

}
