package src;
import advancedMath.calculator.Calculator;
import advancedMath.test.Test;

public class Entrypoint{
    public static void main(String args[]){
        Test calculatorTest = new Test();
        calculatorTest.start();

        Calculator calc = new Calculator();

        try {
            System.out.println(calc.resolve("100-20"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}