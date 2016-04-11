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

}
