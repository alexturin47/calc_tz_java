package com.company;

public class RomanNum{
    private static final String[] roman = {"I","IV","V", "IX", "X", "XL", "L", "XC", "C"};
    private static final int[] arabic = {1,4,5,9,10,40,50,90,100};

    public static int romanToArabic(String input){
        StringBuffer sb = new StringBuffer(input);
        int res = 0;
        while (sb.length() != 0) {
            for (int i = roman.length-1; i >= 0; i--) {
                if (roman[i].length() <= sb.length()) {
                    if (roman[i].equals(sb.substring(0, roman[i].length()))) {
                        res += arabic[i];
                        sb.delete(0, roman[i].length());
                        if(sb.length() == 0) break;
                    }
                }
            }
        }
        return res;
    }

    public static String arabicToRoman(int input) throws Exception{
        if(input <= 0) throw new Exception("В римской системе нет отрицательных чисел либо 0");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< arabic.length; i++){
            if(input == arabic[i]){
                sb.append(roman[i]);
                break;
            } else if(input < arabic[i]){
                sb.append(roman[i-1]);
                input -= arabic[i-1];
                sb.append(arabicToRoman(input));
                break;
            }
        }
        return sb.toString();
    }
}

