import java.util.*;

public class t4{

    public static void main(String[] args) {
    // 1
    System.out.println(nonRepeatable("abracadabra")); // "abrcd" 
    System.out.println(nonRepeatable("paparazzi") + "\n"); // "parzi"
    // 2
    System.out.println(generateBrackets(1)); // ["()"]
    System.out.println(generateBrackets(2)); // ["(())", "()()"]
    System.out.println(generateBrackets(3) + "\n"); // ["((()))", "(()())", "(())()", "()(())", "()()()"]
    // 3
    System.out.println(binarySystem(3)); // ["010", "011", "101", "110", "111"]
    System.out.println(binarySystem(4) + "\n"); // ["0101", "0110", "0111", "1010", "1011", "1101", "1110", "1111"]
    // 4
    System.out.println(alphabeticRow("ababa")); // ab
    System.out.println(alphabeticRow("abc")); // abc
    System.out.println(alphabeticRow("abcdjuwx"));  // abcd
    System.out.println(alphabeticRow("klmabzyxw") + "\n"); // zyxw
    // 5
    System.out.println(five("aaabbcdd")); // "c1b2d2a3" 
    System.out.println(five("vvvvaajaaaaa") + "\n"); // "j1a2v4a5"
    //6
    System.out.println(convertToNum("eight")); // 8
    System.out.println(convertToNum("five hundred sixty seven")); // 567
    System.out.println(convertToNum("thirty one") + "\n"); // 31
    //7
    System.out.println(uniqueSubstring("123412324"));  // 1234
    System.out.println(uniqueSubstring("111111"));  // 1
    System.out.println(uniqueSubstring("77897898") + "\n");  // 789
    //8
    System.out.println(shortestWay(new int[][]{
        {1, 3, 1},
        {1, 5, 1},
        {4, 2, 1}})); // 7

    System.out.println(shortestWay(new int[][]{
        {2, 7, 3},
        {1, 4, 8},
        {4, 5, 9}}) + "\n"); // 21
    //9
    System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng")); 
        // One ring to rule them all
    System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat") + "\n");
        // With great power comes great responsibility
    //10
    System.out.println(switchNums(519, 723)); //  953
    System.out.println(switchNums(491, 3912)); // 9942
    System.out.println(switchNums(6274, 71259)); // 77659 
    }

    /* 1. Напишите рекурсивную функцию, которая принимает строку и удаляет из неё повторяющиеся символы.
    Функция должна вернуть строку, в которой каждый символ встречается только один раз. */
    
    public static String nonRepeatable(String str) {
        if (str.isEmpty()) {
            return "";
        }
        
        char x = str.charAt(0);

        String restString = str.substring(1);

        return x + nonRepeatable(restString.replaceAll(Character.toString(x), ""));
    }

    /* 2. Напишите функцию, которая генерирует все возможные правильные комбинации пар 
    скобок для заданного числа n. */

    public static List<String> generateBrackets(int n) {
        List<String> result = new ArrayList<>();
        generateBrackets(n, n, "", result);
        return result;
    }

    private static void generateBrackets(int left, int right, String current, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(current);
            return ;
        }

        if (left > 0) {
            generateBrackets(left - 1, right, current + "(", result);
        }

        if (right > left) {
            generateBrackets(left, right - 1, current + ")", result);
        }
    }

    /* 3. Напишите функцию, которая генерирует все возможные бинарные комбинации длины n,
    в которых не может быть соседствующих нулей или единиц. */

    public static String binarySystem(int n) {
        String result = "[";
        boolean isFirst = true;
    
        for (int i = 0; i < (1 << n); i++) {
            String bString = Integer.toBinaryString(i | (1 << n)).substring(1);
            if (!bString.contains("00")) {
                if (!isFirst) {
                    result += ", ";
                }
                result += "\"" + bString + "\"";
                isFirst = false;
            }
        }
        
        result += "]";
        return result;
    }
    
    /* 4. Реализуйте функцию, которая принимает строку и возвращает длину самого длинного
    последовательного ряда в этом массиве. Последовательный ряд – это список соседних элементов,
    идущих подряд в алфавитном порядке, который может быть как увеличивающимся, так и уменьшающимся. */

    public static String alphabeticRow(String str) {
        String longestRow = "";
        int n = str.length();
    
        for (int i = 0; i < n; i++) {
            for (int route : new int[]{1, -1}) {
                int j = i;
                while (j < n - 1 && str.charAt(j + 1) == str.charAt(j) + route) {
                    j++;
                }
                if (j - i + 1 > longestRow.length()) {
                    longestRow = str.substring(i, j + 1);
                }
            }
        }
    
        return longestRow;
    }

    /* 5. Напишите функцию, которая принимает строку и подсчитывает количество идущих подряд символов,
    заменяя соответствующим числом повторяющиеся символы. 
    Отсортируйте строку по возрастанию длины буквенного паттерна. */

    public static String five(String s) {
        List<String> pairs = new ArrayList<>();
         
        for (int i = 0; i < s.length();) {
            char currentChar = s.charAt(i);
            int count = 0;
            while (i < s.length() && s.charAt(i) == currentChar) {
                i++;
                count++;
            }
            pairs.add(currentChar + String.valueOf(count));
        }
    
        pairs.sort(
            Comparator
                .comparingInt((String a) -> a.charAt(1))  
                .thenComparingInt(a -> a.charAt(0))
        );

        return String.join("", pairs);
    }

    /* 6. Напишите функцию, принимающую положительное целое число в строковом формате, не превышающее 1000,
    и возвращающую его целочисленное представление. */

    private static final Map<String, Integer> wordsToNum = new HashMap<String, Integer>() {{
        put("one", 1); put("two", 2); put("three", 3); put("four", 4); put("five", 5);
        put("six", 6); put("seven", 7); put("eight", 8); put("nine", 9); put("ten", 10);
        put("twenty", 20); put("thirty", 30); put("forty", 40); put("fifty", 50);
        put("sixty", 60); put("seventy", 70); put("eighty", 80); put("ninety", 90);
        put("hundred", 100);
    }};

    public static int convertToNum(String str) {
        int result = 0, currentNum = 0;
        for (String word : str.toLowerCase().split("\\W+")) {
            if (wordsToNum.containsKey(word)) {
                int value = wordsToNum.get(word);
                currentNum = value == 100 ? currentNum * value : currentNum + value;
            } else {
                result += currentNum;
                currentNum = 0;
            }
        }
        return result + currentNum;
    }

    /* 7. Напишите функцию, принимающую строку цифр, выполняющую поиск подстроки максимальной длины
    с уникальными элементами. Если найдено несколько подстрок одинаковой длины, верните первую. */

    public static String uniqueSubstring(String s) {
        int[] chars = new int[50];
        int maxLength = 0, start = 0, newStart = 0;
        String result = "";
        
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - '0';
            if (chars[idx] > 0) {
                while (s.charAt(newStart) != s.charAt(i)) chars[s.charAt(newStart++) - '0']--;
                start = ++newStart; 
            } else {
                chars[idx]++;
                if (i - start + 1 > maxLength) {  
                    maxLength = i - start + 1;
                    result = s.substring(start, i + 1);
                }
            }
        }
        return result;
    }

    /* 8. Напишите функцию поисковик наименьшего матричного пути.
    На вход поступает двумерный массив, размера n x n,
    ваша задача найти путь с минимальной суммой чисел, передвигаясь только вправо или вниз. */

    public static int shortestWay(int[][] matrix) {
        int n = matrix.length; 
        int m = matrix[0].length; 
        int[][] dp = new int[n][m];
    
        dp[0][0] = matrix[0][0];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0)
                    continue; 
                else 
                    if (i == 0) dp[i][j] = dp[i][j-1] + matrix[i][j]; 
                else
                    if (j == 0) dp[i][j] = dp[i-1][j] + matrix[i][j]; 
                else 
                    dp[i][j] = matrix[i][j] + Math.min(dp[i-1][j], dp[i][j-1]); 
            }
        }
    
        return dp[n-1][m-1];
    }

    /* 9. Создайте функцию, принимающую строку, содержащую числа внутри слов.
    Эти числа представляют расположение слова для новой строящейся строки. */

    public static String numericOrder(String str) {
        String[] words = str.split(" ");
        String[] orderedWords = new String[words.length];

        for (String word : words) {
            int index = Integer.parseInt(word.replaceAll("\\D", "")) - 1; 
            orderedWords[index] = word.replaceAll("\\d", "");
        }

        return String.join(" ", orderedWords);
    }

    /* 10. Напишите функцию, принимающую два числа, которая делает второе число максимально
    возможным за счет замены своих элементов элементами первого числа.
    Брать цифру можно только один раз. */

    public static int switchNums(int num1, int num2) {
        char[] chars1 = String.valueOf(num1).toCharArray();
        char[] chars2 = String.valueOf(num2).toCharArray();
        
        Arrays.sort(chars1);
        
        for (int i = 0; i < chars2.length; i++) {
            for (int j = chars1.length - 1; j >= 0; j--) {
                if (chars1[j] > chars2[i]) {
                    chars2[i] = chars1[j];
                    chars1[j] = '0';
                    break;
                }
            }
        }
        
        return Integer.parseInt(new String(chars2));
    }
}