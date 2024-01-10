import java.util.*;
import java.text.SimpleDateFormat;

public class t5{

    public static void main(String[] args) {
    // 1
    System.out.println(sameLetterPattern("ABAB", "CDCD"));        // true
    System.out.println(sameLetterPattern("ABCBA", "BCDCB"));      // true
    System.out.println(sameLetterPattern("FFGG", "CDCD"));        // false
    System.out.println(sameLetterPattern("FFFF", "ABCD") + "\n"); // false


    // 2 !!!
    // ^ fixed 
    System.out.println(spiderVsFly("H3", "E2")); // H3-H2-H1-A0-E1-E2
    System.out.println(spiderVsFly("A4", "B2")); // A4-A3-A2-B2
    System.out.println(spiderVsFly("A4", "C2")); // A4-A3-A2-B2-C2   
    // & Должно быть A4-B4 так как они соседние
    System.out.println(spiderVsFly("A4", "B4") + "\n");



    // 3
    System.out.println(digitsCount(4666));   // 4
    System.out.println(digitsCount(544));    // 3
    System.out.println(digitsCount(121317)); // 6
    System.out.println(digitsCount(0));      // 1
    System.out.println(digitsCount(12345));  // 5
    System.out.println(digitsCount(1289396387328L) + "\n"); // 13
    // 4
    System.out.println(totalPoints(new String[]{"cat", "create", "sat"}, "caster"));
            // 2
    System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant"));
            // 108
    System.out.println(totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed") + "\n");
            // 13


    // ^ 5 !!!  
    // ^ fixed 
    System.out.println(Arrays.deepToString(sumsUp(new int[] {1, 2, 3, 4, 5}).toArray())); // [[3, 5]]
    System.out.println(Arrays.deepToString(sumsUp(new int[] {1, 2, 3, 7, 9}).toArray())); // [[1, 7]]
    System.out.println(Arrays.deepToString(sumsUp(new int[] {10, 9, 7, 2, 8}).toArray())); // []
    System.out.println(Arrays.deepToString(sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7}).toArray()) + "\n"); // [[2, 6], [3, 5], [1, 7]]


    // 6
    System.out.println(takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"})); // 54%
    System.out.println(takeDownAverage(new String[]{"10%"})); // 0%
    System.out.println(takeDownAverage(new String[]{"53%", "79%"}) + "\n"); // 51%
    // 7
    System.out.println(caesarCipher(new String[]{"encode", "hello world"}, 3)); // KHOOR ZRUOG 
    System.out.println(caesarCipher(new String[]{"decode", "almost last task!"}, 4) + "\n"); // EPQSWX PEWX XEWO!
    // 8
    System.out.println(setSetup(5, 3)); // 60
    System.out.println(setSetup(7, 3)+ "\n"); // 210
    // 9
    System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        // 2011-4-2 17:23
    System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
        // 1983-8-1 00:01
    System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing") + "\n");
        // 1971-1-1 02:40
    // 10
    System.out.println(isNew(3));   // true
    System.out.println(isNew(30));  // true
    System.out.println(isNew(321)); // false
    System.out.println(isNew(123)); // true
    }

    // 1. Создайте функцию, которая возвращает true если две строк
    // имеют один и тот же буквенный шаблон, и false в противном случае.

    public static boolean sameLetterPattern(String f_str, String s_str) {
        if (f_str.length() != s_str.length()) {
            return false;
        }
    
        Map<Character, Character> map = new HashMap<>();
    
        for (int i = 0; i < f_str.length(); i++) {
            char char1 = f_str.charAt(i);
            char char2 = s_str.charAt(i);
    
            if (map.containsKey(char1) && map.get(char1) != char2) {
                return false;
            }
    
            map.put(char1, char2);
        }
    
        return true;
    }

    /* 2. Паутина определяется кольцами, пронумерованными от 0 до 4 от центра, и радиалами,
    помеченными по часовой стрелке сверху как A-H.

    Создайте функцию, которая принимает координаты паука и мухи и
    возвращает кратчайший путь для паука, чтобы добраться до мухи.

    Стоит отметить, что кратчайший путь должен быть рассчитан "геометрически",
    а не путем подсчета количества точек, через которые проходит этот путь. 
 	Угол между каждой парой радиалов одинаков (45 градусов).
 	Расстояние между каждой парой колец всегда одинаково (скажем, "x").
 
    На приведенном выше рисунке координаты паука - "H3", а координаты мухи - "E2".
    Паук будет следовать по кратчайшему пути "H3-H2-H1-A0-E1-E2", чтобы добраться до мухи. */

    public static String spiderVsFly(String Spider, String Fly){
        int circleDif = Math.abs(Spider.charAt(0) - Fly.charAt(0));
        int walk = 1;
        int spiderHeight = Character.getNumericValue(Spider.charAt(1));
        int flyHeight = Character.getNumericValue(Fly.charAt(1));

        double minLen = 100;
        int minI = 0;

        StringBuilder path = new StringBuilder();

        int spiderMove = minI > spiderHeight ? 1 : -1;

        int flyMove = minI > flyHeight ? -1 : 1;

        if (circleDif > 8 - circleDif){
            circleDif = 8 - circleDif;
            walk = -1;
        }

        for (int i = 0; i < 5; i++){
            double len = (Math.abs(spiderHeight - i)
                    + Math.abs(flyHeight - i)
            );

            len += circleDif * Math.sqrt(i*i*2 - 2*i*i*Math.cos(Math.toRadians(45)));

            if (len < minLen) {
                minLen = len;
                minI = i;
            }
        }

        for (int i = spiderHeight; i != minI + spiderMove; i += spiderMove){
            if (i == 0){
                path.append('A');
            }
            else {
                path.append(Spider.charAt(0));
            }

            path.append(i).append('-');
        }

        if (minI != 0){
            for (int i = 0; i < circleDif; i++){
                path.append(Character.toString(path.charAt(path.length() - 3) + walk))
                        .append(minI).append('-');
            }
        }

        for (int i = minI + flyMove; i != flyHeight + flyMove; i += flyMove){

            path.append(Fly.charAt(0));
            path.append(i).append('-');
        }

        path.deleteCharAt(path.length()-1);

        return path.toString();
    }
	    
    // 3. Создайте функцию, которая будет рекурсивно подсчитывать количество цифр числа.
    // Преобразование числа в строку не допускается, поэтому подход является рекурсивным.

    public static int digitsCount(long n) {
        if (n == 0) {
            return 1;
        }
        
        return n < 10 ? 1 : 1 + digitsCount(n / 10);
    }

    /* 4.Игроки пытаются набрать очки, формируя слова, используя буквы из 6-буквенного скремблированного слова.
    Они выигрывают раунд, если им удается успешно расшифровать слово из 6 букв.

    Создайте функцию, которая принимает в массив уже угаданных слов расшифрованное 6-буквенное слово 
    и возвращает общее количество очков, набранных игроком в определенном раунде, используя следующую рубрику:

    3-буквенные слова – это 1 очко
    4-буквенные слова – это 2 очка
    5-буквенные слова – это 3 очка
    6-буквенные слова – это 4 очка + 50 пт бонуса (за расшифровку слова)
    Помните, что недопустимые слова (слова, которые не могут быть сформированы из 6-буквенных расшифрованных слов)
    cчитаются 0 очками.

    Примечание:
    Если 6-буквенное слово имеет несколько анаграмм, считайте каждую 6-буквенную расшифровку дополнительными 54 очками.
    Например, если слово arches, а игрок угадал arches и chaser, добавьте 108 очков для обоих слов. */

    public static int totalPoints(String[] words, String s) {
        return Arrays.stream(words)
                .filter(word -> s.chars().allMatch
                (
                    c -> word.chars().filter(w -> w == c).count() <= s.chars().filter(sc -> sc == c).count())
                )
                .mapToInt(word -> word.length() - 2 + (word.length() == 6 ? 50 : 0))
                .sum();
    }
    
    // 5. Создайте функцию, которая получает каждую пару чисел из массива, который суммирует до восьми,
    // и возвращает его как массив пар (отсортированный по возрастанию). 

    public static ArrayList<int[]> sumsUp(int[] arr){
        ArrayList<int[]> result = new ArrayList<>();

        for (int i = 1; i < arr.length; i++){
            for (int j = 0; j < i; j++){
                if (arr[i] + arr[j] == 8){
                    int[] t = new int[]{arr[i], arr[j]};
                    Arrays.sort(t);
                    result.add(t);
                }
            }
        }

        return result;
    }

    // 6. Какой процент вы можете набрать на тесте, который в одиночку снижает средний балл по классу на 5%?
    // Учитывая массив оценок ваших одноклассников, создайте функцию, которая возвращает ответ.
    // Округлите до ближайшего процента.

    public static String takeDownAverage(String[] percents) {
        int avg = 0;
        for (String s : percents)
            avg += Integer.parseInt(s.substring(0, s.length() - 1));
        return (avg / percents.length - percents.length*5 - 5) + "%";
    }

    /* 7. Создайте функцию, которая будет шифровать и дешифровать сообщения с использованием шифра Цезаря.
    Шифр Цезаря – это метод шифрования, в котором каждая буква в сообщении сдвигается на фиксированное 
    количество позиций в алфавите. Например, если сдвиг составляет 3 позиции, то буква 'A' будет зашифрована как
    'D', 'B' как 'E' и так далее.

    Функция должна выполнять следующие действия:

    1. Определять режим работы: шифрование или дешифрование сообщения.
    2. Если пользователь хочет зашифровать сообщение, программа должна запросить само сообщение и сдвиг,
    на который нужно зашифровать текст.
    3. Если пользователь хочет дешифровать сообщение, программа должна запросить зашифрованное сообщение и сдвиг,
    чтобы расшифровать его.
    4. Обрабатывать сообщения только в верхнем регистре и оставлять другие символы (пробелы, цифры и специальные символы)
    без изменений. */

    public static String caesarCipher(String[] operationAndText, int shift) {
        if (operationAndText.length != 2) {
            return "Укажите операцию: кодирование или декодирование и текст.";
        }
    
        String text = operationAndText[1];
        char[] result = new char[text.length()];
    
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
    
            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                char shiftedChar = (char) (base + (currentChar - base + shift) % 26);
                result[i] = shiftedChar;
            } else {
                result[i] = currentChar;
            }
        }
    
        return new String(result).toUpperCase();
    }

    /* 8. Создайте метод для рекурсивного вычисления количества различных способов как можно разместить k элементов
    из множества из n элементов без повторений. Это задача комбинаторики, и она часто используется в комбинаторных
    оптимизациях, таких как размещение задач на стеллажах или распределение ресурсов.

    Метод принимает два параметра, где n - количество элементов в множестве, а k - количество элементов,
    которые нужно разместить (n >= k) и рассчитывает количество размещений по формуле размещений без повторений:
    n! / (n - k)! */

    public static int setSetup(int n, int k) {
        if (k == 0) {
            return 1;
        }
        
        if (n == 0) {
            return 0;
        }
        
        return n * setSetup(n - 1, k - 1);
    }

    /* 9. В этой задаче цель состоит в том, чтобы вычислить, сколько времени сейчас в двух разных городах.
    Вам дается строка cityA и связанная с ней строка timestamp (time in cityA) с датой,
    отформатированной в полной нотации США, как в этом примере:

    "July 21, 1983 23:01"

    Вы должны вернуть новую метку времени с датой и соответствующим временем в cityB, отформатированную как в этом примере:

    "1983-7-22 23:01"

    Список данных городов и их смещения по Гринвичу (среднее время по Гринвичу) приведены в таблице ниже.

    GMT	City
    - 08:00	Los Angeles
    - 05:00	New York
    - 04:30	Caracas
    - 03:00	Buenos Aires
      00:00 London
    + 01:00	Rome
    + 03:00	Moscow
    + 03:30	Tehran
    + 05:30	New Delhi
    + 08:00	Beijing
    + 10:00	Canberra

    Примечание:
    - Обратите внимание на часы и минуты, ведущий 0 необходим в возвращаемой метке времени,
      когда они представляют собой одну цифру.
    - Обратите внимание на города с получасовыми смещениями. */
    
    public static String getGMT(String city) {
        if (city.equals("Los Angeles")) return "GMT-08:00";
        if (city.equals("New York")) return "GMT-05:00";
        if (city.equals("Caracas")) return "GMT-04:30";
        if (city.equals("Buenos Aires")) return "GMT-03:00";
        if (city.equals("London")) return "GMT 00:00";
        if (city.equals("Rome")) return "GMT+01:00";
        if (city.equals("Moscow")) return "GMT+03:00";
        if (city.equals("Tehran")) return "GMT+03:30";
        if (city.equals("New Delhi")) return "GMT+05:30";
        if (city.equals("Beijing")) return "GMT+08:00";
        if (city.equals("Canberra")) return "GMT+10:00";
        return "GMT";
    }

    public static SimpleDateFormat parseDate = new SimpleDateFormat("MMMM d, yyyy HH:mm", Locale.ENGLISH);
    public static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-M-d HH:mm", Locale.ENGLISH);

    public static String timeDifference(
            String cityA, String timestamp, String cityB
    ) 
    {
        try {
            parseDate.setTimeZone(TimeZone.getTimeZone(getGMT(cityA)));
            formatDate.setTimeZone(TimeZone.getTimeZone(getGMT(cityB)));
            Date date = parseDate.parse(timestamp);
            return formatDate.format(date);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* 10. Новое число-это число, которое не является перестановкой любого меньшего числа.
    869-это не новое число, потому что это просто перестановка меньших чисел,
    689 и 698. 509-это новое число, потому что оно не может быть образовано
    перестановкой любого меньшего числа (ведущие нули не допускаются).
    Напишите функцию, которая принимает неотрицательное целое число и возвращает true,
    если целое число является новым числом, и false, если это не так. */

    public static boolean isNew(int n) {
        while (n > 0) {
            int lastDigit = n % 10;
            n /= 10;
            if (lastDigit > 0 && n > 0 && lastDigit < n % 10) {
                return false;
            }
        }
        return true;
    }
}