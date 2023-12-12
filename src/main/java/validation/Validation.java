package validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class Validation {
    public static Boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%&]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(password).matches();
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

}
