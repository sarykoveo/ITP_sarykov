import java.util.Arrays;
import java.util.Random;

public class t2 {
    public static void main(String[] args) {

        System.out.println(duplicateChars("Donald")); // ➞ true 
        System.out.println(duplicateChars("orange") + "\n"); // ➞ false

        System.out.println(getInitials("Ryan Gosling")); // ➞ RG
        System.out.println(getInitials("Barack Obama") + "\n"); // ➞ BA

        System.out.println(differenceEvenOdd(new int[]{44, 32, 86, 19})); // ➞ 143
        System.out.println(differenceEvenOdd(new int[]{22, 50, 16, 63, 31, 55}) + "\n"); // ➞ 61

        System.out.println(equalToAvg(new int[]{1, 2, 3, 4, 5})); // ➞ true
        System.out.println(equalToAvg(new int[]{1, 2, 3, 4, 6}) + "\n"); // ➞ false

        System.out.println(Arrays.toString(indexMult(new int[]{1, 2, 3}))); // ➞ [0, 2, 6]
        System.out.println(Arrays.toString(indexMult(new int[]{3, 3, -2, 408, 3, 31})) + "\n"); // ➞ [0, 3, -4, 1224, 15, 186]

        System.out.println(reverse("Hello World")); // ➞ dlroW olleH
        System.out.println(reverse("The quick brown fox.") + "\n"); // ➞ .xof nworb kciuq ehT

        System.out.println(Tribonacci(7)); // ➞ 7
        System.out.println(Tribonacci(11) + "\n"); // ➞ 81

        System.out.println(pseudoHash(5));
        System.out.println(pseudoHash(10));
        System.out.println(pseudoHash(0));

        System.out.println(botHelper("Hello, I’m under the water, please help, me")); // ➞ Calling for a staff member
        System.out.println(botHelper("asdfasdb helP")); // ➞ Calling for a staff member
        System.out.println(botHelper("hello") + "\n"); // ➞ Keep waiting
        

        System.out.println(isAnagram("listen", "silent")); // ➞ true
        System.out.println(isAnagram("eleven plus two", "twelve plus one")); // ➞ true
        System.out.println(isAnagram("hello", "world")); // ➞ false 

    }

    // 1. Создайте функцию, которая определяет, есть ли в строке повторяющиеся символы.

    public static boolean duplicateChars(String str) {
        str = str.toLowerCase(); 
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }
        
    // 2. Создайте метод, который принимает строку (фамилию и имя человека) и возвращает строку с инициалами без пробелов.

    public static String getInitials(String imya_familia) {
        String[] parts = imya_familia.split(" ");
        String initials = "";

        for (String part : parts) {
            initials += part.charAt(0);
        }

        return initials;
    }
        
    // 3. Создайте функцию, которая принимает массив и возвращает разницу между суммой четных и нечетных.

    public static int differenceEvenOdd(int[] arr) {
        int sumEven = 0;
        int sumOdd = 0;

        for (int num : arr) {
            if (num % 2 == 0) {
                sumEven += num;
            } else {
                sumOdd += num;
            }
        }

        return Math.abs(sumEven - sumOdd);
    } 

   // 4. Создайте функцию, которая принимает массив и возвращает true, если в массиве есть хотя бы один элемент,
   // который равен среднему арифметическому всех элементов массива, и false в противном случае.

   public static boolean equalToAvg(int[] arr) {

        if (arr.length == 0) {
            return false;
        }

        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        double average = (double) sum / arr.length;
        for (int num : arr) {
            if (num == average) {
                return true;
            }
        }

        return false;
    }

    // 5. Создайте метод, который берет массив целых чисел и возвращает массив,
    // в котором каждое целое число умножено на индекс этого числа в массиве. 
    
    public static int[] indexMult(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i] * i;
        }
        return result;
    }

    // 6. Создайте метод, который принимает строку в качестве аргумента и возвращает ее в обратном порядке.

    public static String reverse(String input) {
    String reversed = "";
    for (int i = input.length() - 1; i >= 0; i--) {
        reversed += input.charAt(i);
    }
        return reversed;
    }


    // 7. Создайте функцию, которая при заданном числе возвращает соответствующее число Трибоначчи.
    // Последовательность Трибоначчи начинается с элементов «0, 0, 1».

    // Последовательность чисел трибоначчи начинается так:
    // 0, 0, 1, 1, 2, 4, 7, 13, 24, 44, 81, 149, 274 и дальше

    public static int Tribonacci(int n) {
        if (n == 1 || n == 2) return 0;
        if (n == 3 || n == 4) return 1;
        
        return Tribonacci(n - 1) + Tribonacci(n - 2) + Tribonacci(n - 3);
    }
    
    // 8. Хэш-суммы в системе контроля версий (например, Git) выглядят как уникальная строка из символов 
    // (от a до f) и цифр (от 0 до 9) длиной в 40 элементов. В Git используется SHA-1 хэш-функция для создания хэшей коммитов. 

    // Создайте функцию, генерирующую квази-хэш заданной пользователем длины.
        
    public static String pseudoHash(int length) {
        String characters = "0123456789abcdef";
        
        Random random = new Random();
        
        char[] pseudoHash = new char[length];
        
        for (int i = 0; i < length; i++) {

            int randomIndex = random.nextInt(characters.length());

            char randomChar = characters.charAt(randomIndex);

            pseudoHash[i] = randomChar;
        }
        
        return new String(pseudoHash);
    }

    // 9. Напишите функцию, которая находит слово "help" в данной строке-стенограмме автоматизированного 
    // телефонного диспетчера службы спасения. Ответьте "Вызов сотрудника",
    // если слово найдено, в противном случае – "Продолжайте ожидание".
    // УЧИТЫВАТЬ РЕГИСТР хэлпа

    public static String botHelper(String x) { 
        String lowercaseInput = x.toLowerCase().replaceAll("[,.]", ""); 
        String[] lowercase2 = lowercaseInput.split(" "); 
        if (Arrays.asList(lowercase2).contains("help")) { 
            return "Calling for a staff member"; 
        } else { 
            return "Keep waiting"; 
        } 
    } 

    // 10. Создайте функцию, которая принимает две строки и определяет, являются ли они анаграммами.

    public static boolean isAnagram(String string_1, String string_2) {
        if (string_1.length() != string_2.length()) {
            return false;
        }
        
        char[] x = string_1.toLowerCase().toCharArray();
        char[] y = string_2.toLowerCase().toCharArray();
        
        Arrays.sort(x);
        Arrays.sort(y);
        
        return Arrays.equals(x, y);
    }
    
}