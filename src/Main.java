import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try {
            File plik = new File("dane.txt");
            Scanner scanner = new Scanner(plik);
            String year, month, day, actualYear, linia;
            String[] months = {"stycznia", "lutego", "marca", "kwietnia", "maja", "czerwca", "lipca", "sierpnia", "września", "października", "listopada", "grudnia"};
            char gender, k;
            int actualMonth, women = 0, men = 0,  born1950_1980 = 0, born1981_2000 = 0, born2001_2025 = 0, averageAgeCounter = 0, parsedYear;
            double averageAge = 0;

            while (scanner.hasNextLine()) {
                linia = scanner.nextLine();
                year = "" + linia.charAt(0) + linia.charAt(1);
                parsedYear = Integer.parseInt(year);
                month = "" + linia.charAt(2) + linia.charAt(3);
                day = "" + linia.charAt(4) + linia.charAt(5);
                gender = linia.charAt(9);
                k = linia.charAt(10);

                if(month.charAt(0) == '2' || month.charAt(0) == '3'){
                    actualYear = "20";
                    if(Integer.parseInt(year) >= 1 && Integer.parseInt(year) <= 25){
                        born2001_2025++;
                        averageAge += (25 - parsedYear < 0) ? parsedYear : parsedYear * (-1);
                        averageAgeCounter++;
                    }
                }
                else if(month.charAt(0) == '4' || month.charAt(0) == '5') actualYear = "21";

                else{
                    actualYear = "19";
                    if(parsedYear >= 50 && parsedYear <= 80){
                        born1950_1980++;
                        averageAge += 25 - parsedYear + 100;
                        averageAgeCounter++;
                    }
                    else if((parsedYear >= 81 && parsedYear <= 99) || parsedYear == 0){
                        born1981_2000++;
                        averageAge += parsedYear == 0 ? parsedYear : 25 - parsedYear + 100;
                        averageAgeCounter++;
                    }
                }

                System.out.print("PESEL " + linia + " należy do ");
                if(day.charAt(0) == '0') day = String.valueOf(day.charAt(1));
                if(gender == '0' || gender == '2' || gender == '4' || gender == '6' || gender == '8'){
                    System.out.print("kobiety, która urodziła się " + day + " ");
                    women++;
                }
                else{
                    System.out.print("mężczyzny, który urodził się " + day + " ");
                    men++;
                }
                actualMonth = Integer.parseInt(month);
                while(actualMonth > 12) actualMonth -= 20;
                System.out.print(months[actualMonth-1] + " " + actualYear + year + " roku.\n");
            }
            System.out.println("Ilość kobiet: " + women);
            System.out.println("Ilość mężczyzn: " + men);
            System.out.println("Ilość osób urodzonych w latach 1950-1980: " + born1950_1980);
            System.out.println("Ilość osób urodzonych w latach 1981-2000: " + born1981_2000);
            System.out.println("Ilość osób urodzonych w latach 2001-2025: " + born2001_2025);
            System.out.println("Średni wiek dla powyższych danych: " + (averageAge/averageAgeCounter));
        }
        catch (IOException e) {
            System.out.println("Błąd zapisu.");
            e.printStackTrace();
        }
    }
}