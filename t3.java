import java.util.Arrays;

public class t3 {
    public static void main(String[] args) {
    // 1
    System.out.println(replaceVovels("apple") + "\n"); // "*ppl*" 
    // 2
    System.out.println(stringTransform("hello")); // "heDoubleLo"
    System.out.println(stringTransform("bookkeeper") + "\n"); // "bDoubleODoubleKDoubleEper"
    // 3
    System.out.println(doesBlockFit(1, 2, 3, 4, 5) ? "true" : "false"); // true
    System.out.println(doesBlockFit(1, 8, 1, 1, 1) ? "true" : "false"); // true
    System.out.println(doesBlockFit(1, 2, 2, 1, 1) ? "true" : "false" + "\n"); // false
    // 4
    System.out.println(numCheck(243)); // true
    System.out.println(numCheck(52) + "\n"); // false
    // 5
    System.out.println(countRoots(new int[]{1, -3, 2})); // 2
    System.out.println(countRoots(new int[]{2, 5, 2})); // 1
    System.out.println(countRoots(new int[]{1, 5, 4}) + "\n"); // 2
    // 6
    System.out.println(Arrays.toString(salesData(new String[][]{
        {"Fridge", "Shop2", "Shop3"},
        {"Microwave", "Shop1", "Shop2", "Shop3", "Shop4"},
        {"Laptop", "Shop3", "Shop4"},
        {"Phone", "Shop1", "Shop2", "Shop3", "Shop4"}})) + "\n");
    // 7
    System.out.println(validSplit("apple eagle egg goat")); // true
    System.out.println(validSplit("cat dog goose fish")); // false
    System.out.println(validSplit("cat Town") + "\n"); // true
    // 8
    System.out.println(waveForm(new int[]{3, 1, 4, 2, 7, 5})); // true
    System.out.println(waveForm(new int[]{1, 2, 3, 4, 5})); // false
    System.out.println(waveForm(new int[]{1, 2, -6, 10, 3}) + "\n"); // true
    // 9
    System.out.println(commonVowel("Hello world")); // 'o'
    System.out.println(commonVowel("aaaa eee o") + "\n"); // 'a'
    // 10
    System.out.println(Arrays.deepToString(dataScience(new int[][]{
        {6, 4, 19, 0, 0},
        {81, 25, 3, 1, 17},
        {48, 12, 60, 32, 14},
        {91, 47, 16, 65, 217},
        {5, 73, 0, 4, 21}})).replace("], [", "],\n ["));
    }

    // 1. Создайте функцию, которая принимает строку и заменяет все гласные буквы на символ «*».

    public static String replaceVovels(String string) {
        String re = "[AEIOUaeiou]";

        String result = string.replaceAll(re, "*");
        
        return result;
    }

    // 2. Напишите функцию, которая принимает строку и заменяет две идущие подряд буквы по шаблону «Double*».

    public static String stringTransform(String string) {
        String re = "(.)\\1";
        
        String result = string.replaceAll(re, "Double$1");
        
        return result;
    }
    
    /* 3. Помогите ребенку разобраться с игрушкой на развитие - поместится ли параллелепипед
     в коробку с отверстиями определенных параметров. Напишите функцию, которая принимает три 
     измерения игрушечного блока: 
     высоту(a), 
     ширину(b) и 
     глубину(c) и возвращает true, если этот блок может поместиться в отверстие с 
     шириной(w) 
     высотой(h). 
    
    Примечание:
    - Вы можете повернуть блок любой стороной к отверстию.
    - Мы предполагаем, что блок подходит, если его размеры равны размерам отверстия, а не строго меньше.
    - Блок можно класть только под прямым углом к поверхности. */

    static boolean doesBlockFit(int a, int b, int c, int w, int h) {
        int[] kubik = {a, b, c};
        int[] otverstie = {w, h};
        
        Arrays.sort(kubik);
        Arrays.sort(otverstie);
        
        return kubik[0] <= otverstie[0] && kubik[1] <= otverstie[1];
    }
    
    

    /* 4. Создайте функцию, которая принимает число в качестве входных данных и возвращает true,
    если сумма квадратов его цифр имеет ту же четность, что и само число. В противном случае верните false.*/

    public static boolean numCheck(int x) {
        int summa_kvadratov = 0;
        int num;
        int orig_num = x; 
        
        while (x > 0) {
            num = x % 10;
            summa_kvadratov += num * num;
            x /= 10;
        }
        
        return summa_kvadratov % 2 == orig_num % 2; 
    }

    // 5. Создайте метод, который берет массив целых чисел-коэффициентов и возвращает количество 
    // целочисленных корней квадратного уравнения. 

    public static int countRoots(int[] coefficients) {
        int a = coefficients[0];
        int b = coefficients[1];
        int c = coefficients[2];
    
        int discriminant = b * b - 4 * a * c;
        int intRootCount = 0;
    
        if (discriminant >= 0) {
            double x_1 = (-b + Math.sqrt(discriminant)) / (2.0 * a);
            double x_2  = (-b - Math.sqrt(discriminant)) / (2.0 * a);
            
            intRootCount += (int)x_1 == x_1 ? 1 : 0;
            if (discriminant > 0) {
                intRootCount += (int)x_2 == x_2 ? 1 : 0;
            }
        }
    
        return intRootCount;
    }

    /* 6. Создайте метод, который принимает двумерный массив, представляющий информацию
    о продажах разных товаров в различных магазинах, и возвращает товары, которые были
    проданы в каждом из магазинов. */

    public static String[] salesData(String[][] data) {
        int maxLen = 0;
        for (String[] row : data) {
            if (row.length > maxLen) {
                maxLen = row.length;
            }
        }
        int count = 0;
        String[] result = new String[data.length];
        for (String[] row : data) {
            if (row.length == maxLen) {
                result[count] = row[0];
                count++;
            }
        }
        return Arrays.copyOf(result, count);
    }

    /* 7. Создайте функцию, которая определяет, можно ли разбить заданное предложение на слова так,
    чтобы каждое слово начиналось с последней буквы предыдущего слова. */

    public static boolean validSplit(String string) {
        String[] words = string.split(" ");
        for (int i = 1; i < words.length; i++) {
            if (words[i].toLowerCase().charAt(0) != words[i-1].toLowerCase().charAt(words[i-1].length()-1)) 
            return false;
        }
        return true;
    }
    
    /* 8. Напишите метод, который определяет, является ли заданный массив «волнообразным».
    Последовательность чисел считается волнообразной, если разница между соседними элементами
    чередуется между убыванием и возрастанием. */

    public static boolean waveForm(int[] x) {
        if (x.length < 3) 
            return false;
    
        boolean increasing = x[1] > x[0];
        for (int i = 2; i < x.length; i++) {
            if (increasing == (x[i] > x[i-1])) 
                return false;
            increasing = !increasing;
        }
        
        return true;
    }

    // 9. Напишите функцию, которая находит наиболее часто встречающуюся гласную в предложении.

    public static char commonVowel(String sentence) {
        String vowels = "aeiouAEIOU"; // гласные буквы на английском языке.
        int[] counts = new int[200]; 
        char commonVowel = ' ';
    
        for (char c : sentence.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                int index = (int) c;
                if (++counts[index] > counts[commonVowel]) {
                    commonVowel = c;
                }
            }
        }
    
        return commonVowel;
    }
    
    // 10. Создайте функцию, которая принимает n целочисленных массивов длины n,
    // а затем изменяет каждый n-ый элемент n-го массива на среднее арифметическое
    // элементов n-го столбца остальных массивов.

    public static int[][] dataScience(int[][] arrays) { 
        int n = arrays.length; 
        for (int i = 0; i < n; i++) { 
            int total = 0; 
            for (int k = 0; k < n; k++) { 
                if (k != i) { 
                    total += arrays[k][i]; 
                } 
            } 
            arrays[i][i] = total / (n - 1); 
        }
        return arrays;
    }
}