import java.util.Calendar;
import java.util.Date;

public class Czlowiek {
    String imie;
    String nazwisko;
    Date dataUrodzenia;
    String pesel;

    public Czlowiek(String imie, String nazwisko, Date dataUrodzenia, String pesel) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.pesel = pesel;
    }

    static class Walidacje {
        public boolean isValidPesel(String pesel) {
            char[] peselChars = pesel.toCharArray();
            String peselWithoutCtrlNum = pesel.substring(0, pesel.length() - 1);
            char[] peselWithoutCtrlNumChars = peselWithoutCtrlNum.toCharArray();
            int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
            Integer sum = 0;
            for (int i = 0; i < peselWithoutCtrlNumChars.length; i++) {
                Integer multipliedNum = Integer.parseInt(String.valueOf(peselWithoutCtrlNumChars[i])) * Integer.parseInt(String.valueOf(weights[i]));
                String multipliedNumString = multipliedNum.toString();
                multipliedNum = Integer.parseInt(multipliedNumString.substring(multipliedNumString.length() - 1));
                sum += multipliedNum;
            }
            String sumString = sum.toString();
            sum = Integer.parseInt(sumString.substring(sumString.length() - 1));
            sum = 10 - sum;
            if (Integer.parseInt(String.valueOf(peselChars[peselChars.length - 1])) == sum) {
                return true;
            } else return false;
        }

        public boolean isNameConsistentWithSex(String imie, String pesel) {
            char[] nameChars = imie.toCharArray();
            if (nameChars[nameChars.length - 1] == 'a') {
                return isWoman(pesel);
            } else {
                return !isWoman(pesel);
            }
        }

        private boolean isWoman(String pesel) {
            char[] peselChars = pesel.toCharArray();
            int num = peselChars[9];
            if (num % 2 == 0) return true;
            else return false;
        }

        public boolean isRetirementAge(String pesel, Date dataUrodzenia) {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int birthYear = dataUrodzenia.getYear();
            Integer age = year - birthYear;
            if (isWoman(pesel)) {
                if (age >= 60) {
                    return true;
                } else return false;
            } else {
                if (age >= 65) {
                    return true;
                } else return false;
            }
        }
    }
}
