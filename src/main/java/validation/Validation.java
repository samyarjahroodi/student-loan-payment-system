package validation;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class Validation {
    public static String generateRandomPassword() {
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialCharacters = "!@#$%^&";
        String lowercaseLettersAndNumbers = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();
        password.append(uppercaseLetters.charAt(random.nextInt(uppercaseLetters.length())));
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        for (int i = 2; i < 8; i++) {
            int randomIndex = random.nextInt(lowercaseLettersAndNumbers.length());
            char randomChar = lowercaseLettersAndNumbers.charAt(randomIndex);
            password.append(randomChar);
        }
        return shuffleString(password.toString());
    }

    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int randomIndex = (int) (Math.random() * (i + 1));
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }


    public static Boolean isValidNationalCode(String nationalCode) {
        String regex = "\\d{10}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(nationalCode).matches();
    }

    public static Boolean isValidNationalIdOfBirthCertificate(String IdOfBirthCertificate) {
        String regex = "\\d{6}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(IdOfBirthCertificate).matches();
    }

    public static Boolean isValidStudentCode(String studentCode) {
        String regex = "\\d{8}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(studentCode).matches();
    }

    public static boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            Date parsedDate = dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean cardValidation(String cardNumber) {
        String pattern = "\\d{16}";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(cardNumber);
        return matcher.matches();
    }

    public static boolean cvv2Validation(String cvv2) {
        String pattern = "\\d{3,4}";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(String.valueOf(cvv2));
        return matcher.matches();
    }
}
