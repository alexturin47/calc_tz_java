package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        while(true){
            try {
                if ((s = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String res;
            try{
                res = Calc.evaluate(s);
            } catch (Exception e){
                System.out.println(e.getMessage());
                break;
            }
            System.out.println(res);
        }
    }
}
