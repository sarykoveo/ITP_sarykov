public class t1 {
    public static void main(String[] args) {

        System.out.println("Литров: " + convert(5));
        System.out.println("Литров: " + convert(3));
        System.out.println("Литров: " + convert(8) + "\n");

        System.out.println("Сожжено калорий: " + fitCalc(15, 1));
        System.out.println("Сожжено калорий: " + fitCalc(24, 2));
        System.out.println("Сожжено калорий: " + fitCalc(41, 3) + "\n");

        System.out.println("Кол-во товаров: " + containers(3, 4, 2));
        System.out.println("Кол-во товаров: " + containers(5, 0, 2));
        System.out.println("Кол-во товаров: " + containers(4, 1, 4) + "\n");

        System.out.println("Треугольник: " + triangleType(5, 5, 5) + "\n");

        System.out.println("Это число больше: " + greaterNumber(8, 4));
        System.out.println("Это число больше: " + greaterNumber(1, 11));
        System.out.println("Это число больше: " + greaterNumber(5, 9) + "\n");

        System.out.println("Смогут сшить пододеяльников: " + howManyItems(22, 1.4, 2));
        System.out.println("Смогут сшить пододеяльников: " + howManyItems(45, 1.8, 1.9));
        System.out.println("Смогут сшить пододеяльников: " + howManyItems(100, 2, 2) + "\n");

        System.out.println("Факториал: " + factorial(3));
        System.out.println("Факториал: " + factorial(5));
        System.out.println("Факториал: " + factorial(7) + "\n");

        System.out.println("НОД двух чисел: " + gcd(48, 18));
        System.out.println("НОД двух чисел: " + gcd(52, 8));
        System.out.println("НОД двух чисел: " + gcd(259, 28) + "\n");

        System.out.println("Общая выручка: " + ticketSaler(70, 1500));
        System.out.println("Общая выручка: " + ticketSaler(24, 950));
        System.out.println("Общая выручка: " + ticketSaler(53, 1250) + "\n");

        System.out.println("Не хватает столов: " + tables(5, 2));
        System.out.println("Не хватает столов: " + tables(32, 20));
        System.out.println("Не хватает столов: " + tables(123, 58) + "\n");
    }

    /* 1. Создайте функцию, которая принимает целое число галлонов и преобразует его в литры.

    convert(5) ➞ 18.925 */

    public static double convert(int x) {
        return x * 3.785;
    }

    /* 2. Вы пишете программу для квази-фитнес-приложения и хотите создать функцию для расчета калорий,
    сожженных пользователем во время тренировки.
    Функция должна принимать время тренировки в минутах и интенсивность,
    где 1 – низкая интенсивность, 2 – средняя, 3 – высокая,
    а затем вычислять количество сожженных калорий на основе этой информации.

    fitCalc(15, 1) ➞ 15 */
    
    public static int fitCalc(int min, int intensivnost) {
        int calories_v_min;
        switch (intensivnost) {
            case 1:
                calories_v_min = 1;
                break;
            case 2:
                calories_v_min = 2;
                break;
            case 3:
                calories_v_min = 3;
                break;
            default:
                calories_v_min = 0;
        }
        return min * calories_v_min;
    }

    /* 3. В этой задаче вы управляете складом, где хранятся товары трех типов:

    - Коробки содержат по 20 товаров в каждой.
    - Мешки содержат по 50 товаров в каждом.
    - Бочки содержат по 100 товаров в каждой.

    Вам предоставили информацию о количестве каждого типа емкостей на складе,
    и вам нужно создать функцию, которая вернет общее количество товаров на складе,
    учитывая объекты хранения разных типов.

    containers(3, 4, 2) ➞ 460*/

    public static int containers(int boxes, int meshki, int bochki) {
        return boxes * 20 + meshki * 50 + bochki * 100;
    }

    /* 4. Создайте функцию, которая принимает 3 числа: X, Y и Z.
    Эти числа представляют длины сторон треугольника.
    Функция должна вернуть тип треугольника на основе данных сторон: "равносторонний" (если все стороны равны),
    "равнобедренный" (если две стороны равны), "разносторонний" (если все стороны разные)
    или "не является треугольником" (если невозможно построить треугольник с заданными сторонами).

    triangleType(5, 5, 5) ➞ isosceles */
    
    public static String triangleType(int x, int y, int z) {
        if (x <= 0 || y <= 0 || z <= 0) {
            return "Это не треугольник";
        } else if (x == y && y == z) {
            return "Равносторонний";
        } else if (x == y || y == z || x == z) {
            return "Равнобедренный";
        } else {
            return "Разносторонний";
        }
    }

    /* 5. В Java есть вариация условного оператора – тернарный оператор "? :",
    принимающий три операнда и возвращающий один из них на основе значения условия.
    Он имеет следующую структуру: условие ? выражение1 : выражение2

    Ваша задача создать функцию, которая принимает два числа a и b,
    а затем с помощью тернарного оператора определяет,
    какое из чисел больше, и возвращает большее число.

    ternaryEvaluation(8, 4) ➞ 8  */

    public static int ternaryEvaluation(int a, int b) {

        int greaterNumber = (a > b) ? a : b;
        return greaterNumber;
    }

    /* 6. У меня есть ограниченное количество ткани определенной длины,
    и я хочу сшить как можно больше пододеяльников. Создайте функцию,
    которая будет принимать длину ткани (в метрах) и размер одной детали (ширина и длина в метрах),
    а затем возвращать количество пододеяльников, которые я смогу сшить, прежде чем кончится рулон.

    n * 2 – это количество квадратных метров имеющейся ткани,
    w и h – это длина и ширина одной детали в метрах

    howManyItems(22, 1.4, 2) ➞ 3 */

    public static double howManyItems(int n, double w, double h) {
        float m2_tkani = (float) (w * h);
        double  pododeyalniki = Math.floor(n / (2 * m2_tkani));
        return pododeyalniki;
    }
    
    /* 7. Напишите функцию, вычисляющую факториал выбранного числа. 

    factorial(3) ➞ 6 */

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }


    /* 8. Создайте функцию, которая находит наибольший общий делитель двух чисел.

    gcd(48, 18) ➞ 6 */

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    /* 9. Создайте функцию, которая принимает количество билетов на концерт,
    проданных через веб-сервис, и стоимость одного билета с учетом фиксированной комиссии.
    Функция должна вернуть общую выручку от продажи билетов.

    ticketSaler(70, 1500) ➞ 75600 */

    public static int ticketSaler(int n_biletov, int stoimost_bileta) {
        double komissiya = 0.28 * n_biletov * stoimost_bileta;
        double obshaya_vyruchka = n_biletov * stoimost_bileta - komissiya;
        return (int) obshaya_vyruchka;
    }

    /* 10. Создайте функцию, которая принимает целое число студентов и количество парт в аудитории.
    Функция должна определить, сколько столов не хватает для размещения всех студентов,
    если за одним столом помещается два студента.

    tables(5, 2) ➞ 1 */

    public static int tables(int students, int stoli) {
        int requiredTables = (students + 1) / 2;
        return Math.max(requiredTables - stoli, 0);
    }
}
