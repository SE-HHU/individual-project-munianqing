import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

public class RandomFormula {
    public int getOperator_number() {
        return operator_number;
    }

    public void setOperator_number(int operator_number) {
        this.operator_number = operator_number;
    }

    public int getOperand_number() {
        return operand_number;
    }

    public void setOperand_number() {
        Random random = new Random();
        operand_number = (random.nextInt(2) == 1) ? 2 : 3;
    }

    private int operator_number;
    private int operand_number;
    int parameter_range = 100;
    int result_range = 100;

    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(parameter_range);
    }

    public String getRandomSymbol() {
        Random random = new Random();
        String[] symbol = {"+", "-"};
        return symbol[random.nextInt(2)];
    }

    public String getOperator() {
        String operator = "";
        setOperand_number();
        int operandnumber = getOperand_number();
        for (int i = 0; i < operandnumber; i++) {
            if (i == (operandnumber - 1)) {
                operator += this.getRandomNumber();
                break;
            }
            operator += this.getRandomNumber() + " " + this.getRandomSymbol() + " ";
        }
        return operator;
    }

    public HashSet<String> checkDuplication() {
        HashSet<String> hashset = new HashSet<String>();
        while (hashset.size() < this.operator_number) {
            String operator = this.getOperator();
            if (this.result_range >= this.answerTest(operator) && this.answerTest(operator) >= 0) {
                hashset.add(operator);
            }
        }
        return hashset;
    }

    public int answerTest(String operator) {
        int length = 0;
        String[] operatorArr = operator.split(" ");
        String symbol = "+-";
        Stack<Integer> opNumbers = new Stack<Integer>();
        Stack<String> opOperators = new Stack<String>();
        opOperators.add("#");
        while (length < operatorArr.length) {
            String op = operatorArr[length++];
            if (symbol.indexOf(op) > -1) {
                if (opNumbers.size() == 2)
                    opNumbers.add(evaluation(opOperators, opNumbers));
                opOperators.add(op);
            } else {
                opNumbers.add(Integer.parseInt(op));
            }
        }
        while (opOperators.peek() != "#") {
            opNumbers.add(evaluation(opOperators, opNumbers));
        }
        return opNumbers.pop();
    }

    public int evaluation(Stack<String> opOperators, Stack<Integer> opNumbers) {
        int num2 = opNumbers.pop();
        int num1 = opNumbers.pop();
        String op = opOperators.pop();
        int result = 0;
        if (op.equals("+")) {
            result = num1 + num2;
        } else {
            result = num1 - num2;
        }
        return result;

    }

    public int[] setAnswer(HashSet<String> hashset) {
        int[] arr = new int[hashset.size()];
        int i = 0;
        for (String str : hashset) {
            arr[i++] = answerTest(str);
        }
        return arr;
    }

    public String outputFormulas(HashSet<String> set) {
        File file = new File("Exercises.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            int i = 1;
            for (String str : set) {
                fileWriter.write("(" + i++ + ") " + str + "=" + "\n");
            }
            fileWriter.close();
        } catch (Exception error) {
            System.out.println("Error" + error.getMessage());
            System.exit(0);
        }
        return file.getAbsolutePath();
    }

    public String outputAnswers(int[] arr) {
        File file = new File("Answers.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            int i = 1;
            for (int j = 0; j < arr.length; j++) {
                fileWriter.write("(" + i++ + ") " + arr[j] + "\n");
            }
            fileWriter.close();
        } catch (Exception error) {
            System.out.println("Error" + error.getMessage());
            System.exit(0);
        }
        return file.getAbsolutePath();
    }
}
