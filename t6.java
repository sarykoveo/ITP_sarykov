import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class t6{

    public static void main(String[] args) {
    // & 1
    System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived")); 
        // * worldevolvesin
    System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood")); 
        // * noldwestactio
    System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison")); 
        // * mrmojorisin
    System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM")); 
        // * anamarg
    System.out.println(hiddenAnagram("D  e b90it->?$ (c)a r...d,,#~", "bad credit")); 
        // * debitcard
    System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth") + "\n"); 
        // * notfound
    // & 2
    System.out.println(collect("intercontinentalisationalism", 6));
        // * ["ationa", "interc", "ntalis", "ontine"]
    System.out.println(collect("strengths", 3));
        // * ["eng", "str", "ths"]
    System.out.println(collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15) + "\n");
        // * ["croscopicsilico", "pneumonoultrami", "volcanoconiosis"]
    // & 3
    System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));
        // * yowmledrovlvsnieesrh
    System.out.println(nicoCipher("andiloveherso", "tesha"));
        // * lnidaevheo s or
    System.out.println(nicoCipher("mubashirhassan", "crazy"));  
        // * bmusarhiahass n
    System.out.println(nicoCipher("edabitisamazing", "matt"));  
        // * deabtiismaaznig 
    System.out.println(nicoCipher("iloveher", "612345") + "\n");  
        // * lovehir    e
    // & 4
    System.out.println(Arrays.toString(twoProduct(new int[] {1, 2, 3, 9, 4, 5, 15}, 45)));
        // * [9, 5]
    System.out.println(Arrays.toString(twoProduct(new int[] {1, 2, 3, 9, 4, 15, 3, 5}, 45)));
        // * [3, 15]
    System.out.println(Arrays.toString(twoProduct(new int[] {1,  2, -1,  4,  5,  6,  10, 7}, 20)));
        // * [4, 5]
    System.out.println(Arrays.toString(twoProduct(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10)));
        // * [2, 5]
    System.out.println(Arrays.toString(twoProduct(new int[] {100, 12, 4, 1, 2}, 15)) + "\n");
        // * []
    // & 5
    System.out.println(Arrays.toString(isExact(6)));            // * [6, 3]
    System.out.println(Arrays.toString(isExact(24)));           // * [24, 4]
    System.out.println(Arrays.toString(isExact(125)));          // * []
    System.out.println(Arrays.toString(isExact(720)));          // * [720, 6]
    System.out.println(Arrays.toString(isExact(1024)));         // * []
    System.out.println(Arrays.toString(isExact(40320)) + "\n"); // * [40320, 8]
    // & 6
    System.out.println(fractions("0.(6)"));            // * "2/3"
    System.out.println(fractions("1.(1)"));            // * "10/9"
    System.out.println(fractions("3.(142857)"));       // * "22/7"
    System.out.println(fractions("0.19(2367)"));       // * "5343/27775"
    System.out.println(fractions("0.1097(3)") + "\n"); // * "823/7500"
    // & 7
    System.out.println(pilish_string("33314444")); // *  333 1 4444
    System.out.println(pilish_string("TOP"));      // *  TOP
    System.out.println(pilish_string("X"));        // *  XXX
    System.out.println(pilish_string("") + "\n");  // *  ""
    // & 8
    System.out.println(generateNonconsecutive("3 + 5 * (2 - 6)"));           // *  -17
    System.out.println(generateNonconsecutive("6 - 18 / (-1 + 4)") + "\n");  // *  0
    // & 9
    System.out.println(isValid("aabbcd"));                  // *  NO
    System.out.println(isValid("aabbccddeefghi"));          // *  NO
    System.out.println(isValid("abcdefghhgfedecba")+ "\n"); // *  YES
    // & 10
    System.out.println(findLCS("abcd", "bd"));        // *  "bd"
    System.out.println(findLCS("aggtab", "gxtxamb")); // *  "gtab"
    }

    /* 1. Создайте функцию, которая принимает две строки. Первая строка содержит предложение, 
    содержащее буквы второй строки в последовательной последовательности, но в другом порядке.
    Cкрытая анаграмма должна содержать все буквы, включая дубликаты, из второй строки в любом порядке 
    и не должна содержать никаких других букв алфавита.
    Напишите функцию, чтобы найти анаграмму второй строки, вложенную где-то в первую строку.
    Вы должны игнорировать регистр символов, любые пробелы и знаки препинания и возвращать анаграмму
    в виде строчной строки без пробелов или знаков препинания. */

    public static String hiddenAnagram(String a, String b) {
        a = a.toLowerCase().replaceAll("[^a-z]", "");
        b = b.toLowerCase().replaceAll("[^a-z]", "");
        int[] setB = new int[26];
        for (char c : b.toCharArray()) setB[c - 97]++;
        
        for (int i = 0; i <= a.length() - b.length(); i++) {
            int[] setA = new int[26];
            for (char c : a.substring(i, i + b.length()).toCharArray()) setA[c - 97]++;
            if (Arrays.equals(setA, setB)) {
                return a.substring(i, i + b.length());
            }
        }
        return "notfound";
    }

    /* 2. Напишите функцию, которая возвращает массив строк,
    заполненных из срезов символов n-длины данного слова
    (срез за другим, в то время как n-длина применяется к слову).

    Примечания:
    - Убедитесь, что результирующий массив лексикографически упорядочен.
    - Возвращает пустой массив, если заданная строка меньше n.
    - Ожидается, что вы решите эту задачу с помощью рекурсивного подхода. */

    public static List<String> collect(String str, int n) {
        if (str.length() < n) {
            return new ArrayList<>();
        } else {
            List<String> list = new ArrayList<>();
            list.add(str.substring(0, n));
            list.addAll(collect(str.substring(n), n));
            return list.stream().sorted().collect(Collectors.toList());
        }
    }

    /* 3. В шифре Nico кодирование осуществляется путем создания цифрового ключа и
    присвоения каждой буквенной позиции сообщения с помощью предоставленного ключа.
    Создайте функцию, которая принимает два аргумента, message и key,и возвращает закодированное сообщение.
    Существуют некоторые вариации правил шифрования.
    Одна из версий правил шифрования изложена ниже:

    message = "mubashirhassan"
    key = "crazy"

    nicoCipher(message, key) ➞ "bmusarhiahass n"

    Шаг 1: Назначьте числа отсортированным буквам из ключа:
    "crazy" = 23154

    Шаг 2: Назначьте номера буквам данного сообщения:
    2 3 1 5 4
    ---------
    m u b a s
    h i r h a
    s s a n

    Шаг 3: Сортировка столбцов по назначенным номерам:
    1 2 3 4 5
    ---------
    b m u s a
    r h i a h
    a s s   n

    Шаг 4: Верните закодированное сообщение по строкам:
    eMessage = "bmusarhiahass n" */

    public static String nicoCipher(String message, String key) {
        int keyLength = key.length();
        int extraSpaces = (keyLength - (message.length() % keyLength)) % keyLength;
        message += new String(new char[extraSpaces]).replace("\0", " "); 
    
        Integer[] order = new Integer[keyLength];
        for (int i = 0; i < keyLength; i++) {
            order[i] = i;
        }
    
        Arrays.sort(order, (a, b) -> Character.compare(key.charAt(a), key.charAt(b)));
    
        char[] result = new char[message.length()];
        for (int i = 0; i < message.length(); i++) {
            int columnIndex = order[i % keyLength];
            int rowIndex = i / keyLength;
            result[i] = message.charAt(rowIndex * keyLength + columnIndex);
        }
    
        return new String(result);
    }

    /* 4. Создайте метод, который принимает массив arr и число n и возвращает массив из двух целых чисел из arr,
    произведение которых равно числу n следующего вида:

    [value_at_lower_index, value_at_higher_index]

    Убедитесь, что вы возвращаете массив из двух целых чисел, который точно делит n
    (произведение n) и что индексы совпадают с указанным выше форматом.
    Таким образом, сортировка значений основана на восходящих индексах.

    Примечание:
    - Дубликатов не будет.
    - Возвращает пустой массив, если совпадение не найдено.
    - Всегда считайте, что пара рассматриваемого элемента
      (текущий элемент, на который указывали во время поиска) находится слева от него.
    - Массив может иметь несколько решений (произведений n),
      поэтому возвращайте первое найденное полное совпадение (как описано выше). */

    public static int[] twoProduct(int[] arr, int n) {
    HashSet<Integer> set = new HashSet<>();
    for (int m : arr) {
        int target = n / m;
        if (n % m == 0 && set.contains(target))
            return new int[] { target, m };
        set.add(m);
    }
        return new int[] {};
    }

    /* 5. Создайте рекурсивную функцию, которая проверяет,
    является ли число точной верхней границей факториала n.
    Если это так, верните массив точной факториальной границы и n, или иначе, пустой массив. */

    public static int[] isExact(int f, int m, int n) {
        return (f < n) ? isExact(f * (m + 1), m + 1, n) : new int[] { f, m };
    }
    
    public static int[] isExact(int n) {
        int[] res = isExact(1, 1, n);
        return (res[0] == n) ? res : new int[] {};
    }

    /* 6. Деление на дробь часто приводит к бесконечно повторяющейся десятичной дроби. 
    Создайте функцию, которая принимает десятичную дробь в строковой форме с повторяющейся 
    частью в круглых скобках и возвращает эквивалентную дробь в строковой форме и в наименьших членах */

    public static String fractions(String frac) {
        int startBracket = frac.indexOf('(');
        if (startBracket != -1) {
            String repeating = frac.substring(startBracket + 1, frac.length() - 1);
            frac = frac.substring(0, startBracket) + repeating.repeat(9 - repeating.length());
        }
        double a = Double.parseDouble(frac);
        int div = 2;
        while (Math.ceil(a * div) - a * div > 0.000001) div++;
        return (int) Math.ceil(a * div) + "/" + div;
    }

    /* 7. В этой задаче преобразуйте строку в серию слов (или последовательности символов),
    разделенных одним пробелом, причем каждое слово имеет одинаковую длину,
    заданную первыми 15 цифрами десятичного представления числа Пи:
    3.14159265358979

    Если строка содержит больше символов, чем общее количество, заданное суммой цифр Пи,
    неиспользуемые символы отбрасываются, и вы будете использовать только те,
    которые необходимы для формирования 15 слов.

    String = "HOWINEEDADRINKALCOHOLICINNATUREAFTERTHEHEAVYLECTURESINVOLVINGQUANTUMMECHANICSANDALLTHESECRETSOFTHEUNIVERSE"

    Pi String = "HOW I NEED A DRINK ALCOHOLIC IN NATURE AFTER THE HEAVY LECTURES INVOLVING QUANTUM MECHANICS"

    Every word has the same length of the digit of Pi found at the same index ?
    "HOW" = 3, "I" = 1, "NEED" = 4, "A" = 1, "DRINK" = 5
    "ALCOHOLIC" = 9, "IN" = 2, "NATURE" = 6, "AFTER" = 5
    "THE" = 3, "HEAVY" = 5, "LECTURES" = 8, "INVOLVING" = 9
    "QUANTUM" = 7, "MECHANICS" = 9
    3.14159265358979

    Также, если строка содержит меньше символов, чем общее количество, заданное суммой цифр Пи,
    в любом случае вы должны соблюдать последовательность цифр для получения слов.

    String = "FORALOOP"

    Pi String = "FOR A LOOP"

    Every word has the same length of the digit of Pi found at the same index ?
    "FOR" = 3, "A" = 1, "LOOP" = 4
    3.14

    Если буквы, содержащиеся в строке, не совпадают в точности с цифрами,
    в этом случае вы будете повторять последнюю букву, пока слово не будет иметь правильную длину.

    String = "CANIMAKEAGUESSNOW"

    Pi String = "CAN I MAKE A GUESS NOWWWWWWW"

    Every word has the same length of the digit of Pi found at the same index ?
    "CAN" = 3, "I" = 1, "MAKE" = 4, "A" = 1, "GUESS" = 5, "NOW" = 3
    3.14153 (Wrong!)
    The length of the sixth word "NOW" (3)...
    ...doesn't match the sixth digit of Pi (9)
    The last letter "W" will be repeated...
    ...until the length of the word will match the digit

    "CAN" = 3, "I" = 1, "MAKE" = 4, "A" = 1, "GUESS" = 5, "NOWWWWWWW" = 9
    3.14159 (Correct!)

    Если данная строка пуста, должна быть возвращена пустая строка.

    Учитывая строку txt, реализуйте функцию, которая возвращает ту же строку,
    отформатированную в соответствии с приведенными выше инструкциями.

    Примечание:
    - Эта задача представляет собой упрощенную концепцию, вдохновленную Пилишем,
    своеобразным типом ограниченного письма, в котором используются все известные возможные цифры Пи.
    Потенциально бесконечный текст может быть написан с использованием знаков препинания и набора дополнительных правил,
    которые позволяют избежать длинных последовательностей маленьких цифр, заменяя их словами больше 9 букв и делая так,
    чтобы композиция выглядела более похожей на стихотворение вольным стихом.
    - Точка, отделяющая целую часть числа Пи от десятичной, не должна учитываться в функции:
    она присутствует в инструкциях и примерах только для удобства чтения. */

    public static String pilish_string(String str) {
        String res = "";
        String pi = String.valueOf(Math.PI).replace(".", "");
        int piIndex = 0, strIndex = 0;

        while (strIndex < str.length()) {
            int p = pi.charAt(piIndex++) - '0';
            int n = Math.min(p, str.length() - strIndex);
            res += str.substring(strIndex, strIndex + n);
            strIndex += n;
            if (strIndex < str.length()) res += ' ';
                else
                    while (n++ < p) res += res.charAt(res.length() - 1);
        }
        return res;
    }

    /* 8.Создайте функцию, которая будет вычислять результат математических выражений, предоставленных в виде строки. 
    Реализуйте алгоритм, который разбирает строку и вычисляет результат выражения,
    учитывая приоритет операций, скобки и т. д. Математические операции, которые нужно поддерживать,
    включают в себя сложение, вычитание, умножение, деление и скобки. Обработайте ошибки,
    такие как деление на ноль или неправильно введенное выражение,
    и верните соответствующее сообщение об ошибке */

    public static String generateNonconsecutive(String str) {
        Pattern part = Pattern.compile("^( [\\-+*/] )*(\\()*(-*\\d+)(\\))*");
        boolean start = true;
        Node currentNode = new Node("+");
        currentNode.setLeft(new Node("0"));
        int parLevel = 0;

        for (int i = 0; i < str.length();) {
            Matcher matcher = part.matcher(str).region(i, str.length());

            if (!matcher.find()){
                System.out.println("Syntax error");
                return null;
            }

            if (matcher.group(1) == null){
                if (!start){
                    System.out.println("Syntax error");
                    return null;
                }
                currentNode.setRight(new Node(matcher.group(3)));
                start = false;
                i = matcher.end();
                if (!(matcher.group(2) == null)){
                    parLevel++;
                }
                continue;
            }
            Node opNode = new Node(String.valueOf(matcher.group(1).charAt(1)));
            Node numNode = new Node(matcher.group(3));
            opNode.setRight(numNode);
            if (parLevel > 0){
                if (matcher.group(1).charAt(1) == '*' || matcher.group(1).charAt(1) == '/'){
                    opNode.setLeft(currentNode.right);
                    currentNode.setRight(opNode);
                }
                else {
                    opNode.setLeft(currentNode.right);
                    currentNode.setRight(opNode);
                    currentNode = opNode;
                }
            }
            if (parLevel == 0){
                if (matcher.group(1).charAt(1) == '*' || matcher.group(1).charAt(1) == '/'){
                    opNode.setLeft(currentNode.right);

                    currentNode.setRight(opNode);
                }
                else {
                    opNode.setLeft(currentNode);
                    currentNode = opNode;
                }
            }

            if (!(matcher.group(2) == null)){
                currentNode = opNode;
                parLevel++;
            }
            if (!(matcher.group(4) == null)){
                if (!(currentNode.root == null)){
                    currentNode = currentNode.root;
                }
                parLevel--;
            }
            i = matcher.end();
        }
        try {
            double res = currentNode.getUltimateRoot().computeNode();
            if (res % 1 == 0){
                return String.valueOf((int) res);
            }
            return String.valueOf(res);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    static class Node {
        String value;
        Node left;
        Node root;
        Node right;


        public void setLeft(Node node){
            this.left = node;
            node.root = this;
        }

        public void setRight(Node node){
            this.right = node;
            node.root = this;
        }

        public Node getUltimateRoot(){
            if (root != null){
                return root.getUltimateRoot();
            }
            return this;
        }

        public double computeNode() throws Exception {
            if ("+-*/".contains(value)){
                double num1 = left.computeNode();
                double num2 = right.computeNode();

                switch (value){
                    case "+":
                        return num1 + num2;
                    case "-":
                        return num1 - num2;
                    case "*":
                        return num1 * num2;
                    case "/":
                        if (num2 == 0){
                            throw new ArithmeticException("Division by zero");
                        }
                        return num1 / num2;
                    default:
                        throw new Exception("Syntax error");
                }

            }
            return Double.parseDouble(value);
        }

        Node(String value) {
            this.value = value;
            this.root = null;
            right = null;
            left = null;
        }
    }


    /* 9. Шерлок считает строку действительной, если все символы строки встречаются одинаковое количество раз. 
    Также допустимо, если он может удалить только 1 символ из 1 индекса в строке,
    а остальные символы будут встречаться одинаковое количество раз. 
    Для данной строки str определите, действительна ли она. Если да, 
    верните «ДА», в противном случае верните «НЕТ».

    Например, если str = "abc", строка действительна, потому что частота символов у всех одинакова. 
    Если str = "abcc", строка также действительна, потому что мы можем удалить 1 "c" и
    оставить по одному символу каждого символа в строке. Однако, если str = "abccc", строка недействительна, 
    поскольку удаление одного символа не приводит к одинаковой частоте символов. */

    public static String isValid(String str) {
        int[] charCounts = new int[26];
        for (char c : str.toCharArray()) {
            charCounts[c - 'a']++;
        }
        int prevCount = -1;
        int removals = 0;
        for (int count : charCounts) {
            if (count > 0) {
                if (prevCount == -1) {
                    prevCount = count;
                } else if (prevCount != count) {
                    removals += Math.abs(prevCount - count);
                    if (removals > 1) return "NO";
                }
            }
        }
        return "YES";
    }

    /* 10. Создайте функцию, которая будет находить наибольшую общую подпоследовательность (LCS) для двух строк.
    LCS – это самая длинная последовательность символов, которая встречается как подпоследовательность в обеих строках. 
    Эта задача требует понимания алгоритма динамического программирования для нахождения наибольшей 
    общей подпоследовательности и его эффективной реализации. */

    public static String findLCS(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        StringBuilder lcs = new StringBuilder();
    
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int i = s1.length(), j = s2.length();

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.reverse().toString();
    }
}