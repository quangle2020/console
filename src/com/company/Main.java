package com.company;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) {


    }

    // tìm những phần tử có ở list B nhưng ko có ở list A
    public static List<String> searchEmlementInBNotInA(List<String> listA, List<String> listB){
        return null;
    }

    // đếm số lần xuất hiện của 1 từ trong chuỗi
    public static Integer demSoLanXuatHien(String str, String target){
        String strReplace = str.replace(target, "");

        // kết quả = độ dài cửa chuỗi ban đầu trừ đi độ dài của chuỗi sau khi replace rồi chia cho độ dài của chuỗi target
        return  (str.length() - strReplace.length()) / target.length();
    }

    public static int maxEvents(List<Integer> arrival, List<Integer> duration){
        int maxEvents = 0;
        for( int i = 0; i < arrival.size(); i++){
            if (i == arrival.size() - 1)
                maxEvents++;
            else
            if (i == 0) {

                    maxEvents++;
            }else {
                if((arrival.get(i) + duration.get(i)) <= arrival.get(i+1))
                    maxEvents ++;
            }
        }
        return maxEvents;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}