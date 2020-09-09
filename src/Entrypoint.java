package src;
import advancedMath.calculator.Calculator;
import advancedMath.test.Test;

public class Entrypoint{
    public static void main(String args[]){
        Test calculatorTest = new Test();
        calculatorTest.methodsWorking();

        Calculator calc = new Calculator();

        try {
            System.out.println(calc.resolve("100-20*2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}