import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RandomFormula randomFormula = new RandomFormula();
        System.out.println("请输入您想要生成的题目数量：");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        randomFormula.setOperator_number(input);
        HashSet<String> hashset = new HashSet<>();
        hashset = randomFormula.checkDuplication();
        randomFormula.outputFormulas(hashset);
        int[] arr = randomFormula.setAnswer(hashset);
        randomFormula.outputAnswers(arr);
    }
}