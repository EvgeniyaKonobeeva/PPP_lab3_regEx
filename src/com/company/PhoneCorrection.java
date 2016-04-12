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
    private String regEx = "\\+*" + gr1 +"\\d"+gr1 + gr2 + gr3 + gr1+ gr2 +gr3 + gr1 +gr4 + gr3+ gr1 + gr4;
    public PhoneCorrection(String _fileNameIn, String _fileNameOut){
        this.fileNameIn = _fileNameIn;
        this.fileNameOut = _fileNameOut;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileNameIn)));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileNameOut)));

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void correctNumbers(int countryCode)throws IOException{
        Pattern pat =Pattern.compile(regEx);
        Matcher mat;
        String number = "", whole;
        while(reader.read() != -1){
            whole = reader.readLine();
            System.out.println("whole1 " + whole);
            mat = pat.matcher(whole);
            while(mat.find()){
                number = whole.substring(mat.start(), mat.end());
                System.out.println("n1 " + number);
                whole = whole.replace(number, changeNumber(countryCode, number));
            }
            writer.write(whole + "\n");


        }
        reader.close();
        writer.close();
    }
    public String changeNumber(int countryCode, String number){
        number = number.replaceAll("\\s","");
        number = number.replaceAll("\\(","");
        number = number.replaceAll("\\)","");
        number = number.replaceAll("-","");
        number = number.replaceAll("\\+","");
        number = number.substring(1);
        System.out.println("number2 "+number);

        Pattern pat;
        Matcher mat;
        String correctNumber = "+" + countryCode;

        pat =Pattern.compile("(\\d\\d\\d){1}");
        mat = pat.matcher(number);
        int count = 0;
        while (mat.find() && count <1) {
            correctNumber += "(" + mat.group() + ") ";
            number = number.substring(mat.end());
            count++;
        }

        pat =Pattern.compile("(\\d\\d\\d){1}");
        mat = pat.matcher(number);
        count = 0;
        while(mat.find() && count <1) {
            correctNumber += mat.group() + "-";
            number = number.substring(mat.end());
            count++;
        }

        pat =Pattern.compile("(\\d\\d){1}");
        mat = pat.matcher(number);
        count = 0;
        while(mat.find() && count <1) {
            correctNumber += mat.group() + "-";
            number = number.substring(mat.end());
            count++;
        }


        pat =Pattern.compile("(\\d\\d){1}");
        mat = pat.matcher(number);
        count = 0;
        while (mat.find()&& count <1) {
            correctNumber += mat.group();
            count++;
        }
        System.out.println("correctNumber2 " + correctNumber);


        return correctNumber;
    }

    }
