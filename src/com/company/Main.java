package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        PhoneCorrection ph = new PhoneCorrection("wrongNumbers.txt", "correctNumbers.txt");
        try {
            ph.correctNumbers(1);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
