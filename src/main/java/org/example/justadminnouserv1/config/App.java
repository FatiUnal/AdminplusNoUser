package org.example.justadminnouserv1.config;

import org.example.justadminnouserv1.exception.InvalidDataException;
import org.example.justadminnouserv1.exception.NullArgumentException;

import java.util.regex.Pattern;

public class App {
    public static void isValidEmail(String email) {
        if (email == null || email.isEmpty())
            throw new NullArgumentException("E-mail bulunamadı!");

        if (!Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$").matcher(email).matches())
            throw new InvalidDataException("E-mail geçerli formatta değildir!");

    }

    public static void validatePassword(String password,String confirmedPassword) {
        if (password == null || password.isEmpty())
            throw new InvalidDataException("Şifre boş olamaz!");

        if (password.length() < 8 || password.length() > 20)
            throw new InvalidDataException("Şifre 8-20 karakter uzunluğunda olmak zorundadır!");


        if (!Pattern.matches(".*[A-Z].*", password))
            throw new InvalidDataException("Şifre en az bir büyük harf içermelidir!");


        if (!Pattern.matches(".*[a-z].*", password))
            throw new InvalidDataException("Şifre en az bir küçük harf içermelidir!");


        // 5. Şifre rakam içeriyor mu?
        if (!Pattern.matches(".*\\d.*", password))
            throw new InvalidDataException("Şifre en az bir rakam içermelidir!");


        if (!Pattern.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*", password))
            throw new InvalidDataException("Şifre en az bir özel karakter içermelidir!");


        // 7. Şifre boşluk içeriyor mu?
        if (password.contains(" "))
            throw new InvalidDataException("Şifre boşluk içermemelidir.");

        if (!password.equals(confirmedPassword))
            throw new InvalidDataException("Girilen iki şifrede aynı olmak zorundadır!");
    }
}
