package com.quanglv.utils;

public class ValidateMails {
    Boolean checkMail(String mail){
        // return true is mail, else not mail
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return mail.matches(EMAIL_REGEX);
    }
}
