package com.company;

import java.util.ArrayList;
import java.util.List;

public class Calc {

    private static final int SPACE = 0;
    private static final int ARABIC = 1;
    private static final int ROMAN = 2;
    private static final int OPERATION = 3;
    private static final int NONE = -1;

    private static int typeVal;

    private static List<String> values;
    private static Character operation = null;


    private static int checkChar(char ch){
        if(Character.isWhitespace(ch)) return 0;
        if(ch >= 48 && ch <= 57) {
            return ARABIC;
        }
        if(ch == 'I' || ch == 'V' || ch == 'X') return ROMAN;
        if(ch == '+' || ch == '-' || ch == '*' || ch == '/') return OPERATION;
        return -1;
    }

    private static void parse(String input) throws Exception{
        StringBuilder v = new StringBuilder();
        typeVal = NONE;
        values = new ArrayList<>();

        for(int i = 0; i< input.length(); i++){
            char ch = input.charAt(i);
            switch (checkChar(ch)){
                case NONE: throw new Exception("Неиспользуемый символ в выражении");
                case SPACE: continue;
                case ARABIC: if(typeVal == ROMAN) throw new Exception("Разные системы счисления");
                            typeVal = ARABIC;
                            v.append(ch);
                            break;
                case ROMAN: if(typeVal == ARABIC) throw new Exception("Разные системы счисления");
                    typeVal = ROMAN;
                    v.append(ch);
                    break;
                case OPERATION: if(operation != null) throw new Exception("Подерживается только одна операция");
                    operation = ch;
                    values.add(v.toString());
                    v = new StringBuilder();
                    break;
            }
        }
        if(v.length() != 0) values.add(v.toString());
        if(values.size() != 2) throw new Exception("Неверное количество переменных");
    }

    public static String evaluate(String input) throws Exception {
        int result = 0;
        int a;
        int b;

        parse(input);
        if(typeVal == ROMAN){
           a = RomanNum.romanToArabic(values.get(0));
           b = RomanNum.romanToArabic(values.get(1));
        } else {
            a = Integer.parseInt(values.get(0));
            b = Integer.parseInt(values.get(1));
        }

        switch (operation) {
            case '+': result =  a + b;
            break;
            case '-': result = a - b;
            break;
            case '*': result = a * b;
            break;
            case '/': result = a / b;
        }
        operation = null;
        if(typeVal == ROMAN){
           return RomanNum.arabicToRoman(result);
        }

        return Integer.toString(result);
    }
}
