import java.util.*;

public class Main {

    public static void main(String[] args) {
        factorial();
        triangle();
        age();
        persons();
    }

    public static void factorial() {
        System.out.println("\nZadanie 1");
        Scanner scan = new Scanner(System.in);
        System.out.print("Podaj liczbę: ");
        int n = scan.nextInt();
        int countedFactorial = countFactorial(n);
        String text = String.format("Silnia z liczby %d wynosi %d.", n, countedFactorial);
        System.out.println(text);
    }

    private static int countFactorial(int n) {
        int factorial = 1;
        for (int i = n; i > 1; i--) {
            factorial *= i;
        }
        return factorial;
    }

    public static void triangle() {
        System.out.println("\nZadanie 2");
        Scanner scan = new Scanner(System.in);
        System.out.print("Podaj pierwszą liczbę (A): ");
        int a = scan.nextInt();
        System.out.print("Podaj drugą liczbę (B): ");
        int b = scan.nextInt();
        System.out.print("Podaj trzecią liczbę (C): ");
        int c = scan.nextInt();
        boolean buildPossible = isTriangleBuildPossible(a, b, c);
        String text;
        if (buildPossible) {
            String triangleType = getTriangleType(a, b, c);
            text = String.format("Z podanych długości odcinków można zbudować trójkąt %s.", triangleType);

        } else {
            text = String.format("Z podanych długości odcinków nie można zbudować trójkąta.");
        }
        System.out.println(text);
    }

    private static boolean isTriangleBuildPossible(int a, int b, int c) {
        if (a + b > c && b + c > a && a + c > b) {
            return true;
        } else return false;
    }

    private static String getTriangleType(int a, int b, int c) {
        int[] tab = new int[]{a, b, c};
        Arrays.sort(tab);
        int min_squared = tab[0] * tab[0];
        int mid_squared = tab[1] * tab[1];
        int max_squared = tab[2] * tab[2];
        if (min_squared + mid_squared > max_squared) {
            return "ostrokątny";
        } else if (min_squared + mid_squared < max_squared) {
            return "rozwartokątny";
        } else return "prostokątny";
    }

    public static void age() {
        System.out.println("\nZadanie 3");
        Scanner scan = new Scanner(System.in);
        System.out.print("Podaj rok urodzenia: ");
        int dateOfBirth = scan.nextInt();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int age = year - dateOfBirth;
        if (age % 2 == 0) {
            List<Integer> primeNumbers = getPrimeNumbers(age);
            System.out.println("Liczby pierwsze mniejsze od wieku:");
            for (Integer primeNumber : primeNumbers) {
                System.out.println(primeNumber);
            }
            int avg = getAvg(age);
            System.out.println(String.format("Średnia z liczb nieparzystych mniejszych od wieku wynosi: %d.", avg));
        } else {
            System.out.println("Wiek jest nieparzysty.");
        }
    }

    public static List<Integer> getPrimeNumbers(int age) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= age; i++) {
            if (isPrime(i)) {
                list.add(i);
            }
        }
        return list;
    }

    private static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;
    }

    private static int getAvg(int age) {
        List<Integer> list = new ArrayList<>();
        int avg = 0;
        for (int i = 0; i < age; i++) {
            if (i % 2 == 0) {
                list.add(i);
            }
        }
        for (Integer num : list) {
            avg += num;
        }
        avg /= list.size();
        return avg;
    }

    public static void persons() {
        System.out.println("\nZadanie 4");
        List<Czlowiek> ludzie = Arrays.asList(
                new Czlowiek("Jan", "Kowalski", new Date(1928, 4, 3), "28040341637"),
                new Czlowiek("Andrzej", "Nowak", new Date(1966, 5, 19), "66051934839"),
                new Czlowiek("Grzegorz", "Wiśniewski", new Date(1981, 1, 1), "81010131069"),
                new Czlowiek("Anna", "Nowakowska", new Date(1977, 3, 30), "77033038061"),
                new Czlowiek("Jadwiga", "Lewandowska", new Date(1925, 8, 23), "25082315541")
        );
        Czlowiek.Walidacje walidacje = new Czlowiek.Walidacje();
        for (Czlowiek czlowiek : ludzie) {
            System.out.println(String.format("\n" + czlowiek.imie + " " + czlowiek.nazwisko + ": "));
            boolean isValidPesel = walidacje.isValidPesel(czlowiek.pesel);
            if (isValidPesel) {
                System.out.println("Pesel poprawny.");
            } else {
                System.out.println("Pesel niepoprawny.");
            }
            boolean isNameConsistentWithSex = walidacje.isNameConsistentWithSex(czlowiek.imie, czlowiek.pesel);
            if (isNameConsistentWithSex) {
                System.out.println("Płeć w peselu zgadza się z imieniem.");
            } else {
                System.out.println("Płeć w peselu nie zgadza się z imieniem.");
            }
            if (isValidPesel) {
                boolean isRetirementAge = walidacje.isRetirementAge(czlowiek.pesel, czlowiek.dataUrodzenia);
                if (isRetirementAge) {
                    System.out.println("Człowiek jest w wieku emerytalnym.");
                } else {
                    System.out.println("Człowiek nie jest w wieku emerytalnym.");
                }
            }
        }
    }
}
