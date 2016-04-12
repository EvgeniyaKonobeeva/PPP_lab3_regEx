package com.company;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Евгения on 11.04.2016.
 */
public class PhoneCorrection {
    private String fileNameIn;
    private String fileNameOut;
    private InputStream in;
    private OutputStream out;
    private BufferedReader reader;
    private BufferedWriter writer;
    String gr1 = "(\\s*(\\(*|\\-*)\\s*)"; // gr1,2
    String gr2 = "((\\d\\s*\\d\\s*\\d){1})"; //gr3, 4
    String gr3 = "(\\s*(\\-*|\\)*)\\s*)"; //gr 5, 6
    String gr4 = "((\\d\\s*\\d){1})";//gr 7,8
    private String regEx = "\\+*" + gr1 +"\\d*"+gr1 + gr2 + gr3 + gr1+ gr2 +gr3 + gr1 +gr4 + gr3+ gr1 + gr4;

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
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileNameIn)));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileNameIn)));


        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void correctNumbers(int countryCode)throws IOException{
        Pattern pat =Pattern.compile(regEx);
        Matcher mat;
        String number, whole;
        while(reader.read() != -1){
            whole = reader.readLine();
            mat = pat.matcher(whole);
            while(mat.find()){
                number = whole.substring(mat.start(), mat.end());
                whole.replace(number, changeNumber(countryCode, number));
            }

        }
    }
    public String changeNumber(int countryCode, String number){

        number.replaceAll("\\s+","");
        number.replaceAll("\\(+","");
        number.replaceAll("\\)+","");
        number.replaceAll("\\-+","");

        Pattern pat = Pattern.compile("\\d{1}");
        Matcher mat = pat.matcher(number);
        String correctNumber = "+" + countryCode;

        pat =Pattern.compile("(\\d\\d\\d){1}");
        mat = pat.matcher(number);
        correctNumber += "(" + mat.start() + mat.end() + ") ";
        number = number.substring(mat.end());

        pat =Pattern.compile("(\\d\\d\\d){1}");
        mat = pat.matcher(number);
        correctNumber += mat.start() + mat.end() + "-";
        number = number.substring(mat.end());

        pat =Pattern.compile("(\\d\\d){1}");
        mat = pat.matcher(number);
        correctNumber += mat.start() + mat.end() + "-";
        number = number.substring(mat.end());

        pat =Pattern.compile("(\\d\\d){1}");
        mat = pat.matcher(number);
        correctNumber += mat.start() + mat.end();


        return correctNumber;
    }

    }
