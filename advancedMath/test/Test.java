package advancedMath.test;

import advancedMath.calculator.Calculator;
import advancedMath.exceptions.CalculatorException;

public class Test {

    public void methodsWorking(){

        Calculator ct = new Calculator();

        System.out.println("testing Calculator.thereIsChar(String str, char c)...");
        try {
            if(!ct.thereIsChar("0000f0000", 'f'))
                throw new CalculatorException("Function Calculator.thereIsChar(char c) is not working correctly");
        }
        catch(CalculatorException calcE) {
            calcE.printStackTrace();
        }
        System.out.println("passed");

        System.out.println("testing Calculator.findChar(String str, char c) ...");
        try {
            int indexReturned = ct.findChar("0000f0000", 'f');

            if(indexReturned != 4){
                throw new CalculatorException("Function Calculator.findChar(String str, char c) is not working correctly");
            }
        } 
        catch(CalculatorException calcE) {
            calcE.printStackTrace();
        }
        System.out.println("passed");

        System.out.println("testing Calculator.isDigit(char c) ...");
        try {

            boolean error = false;


            if(!ct.isDigit('0'))
                error = true;
            if(!ct.isDigit('1'))
                error = true;
            if(!ct.isDigit('2'))
                error = true;
            if(!ct.isDigit('3'))
                error = true;
            if(!ct.isDigit('4'))
                error = true;
            if(!ct.isDigit('5'))
                error = true;
            if(!ct.isDigit('6'))
                error = true;
            if(!ct.isDigit('7'))
                error = true;
            if(!ct.isDigit('8'))
                error = true;
            if(!ct.isDigit('9'))
                error = true;

            if(error)
            throw new CalculatorException("Function Calculator.isDigit(char c) is not working correctly");
        } 
        catch(CalculatorException calcE) {
            calcE.printStackTrace();
        }
        System.out.println("passed");

        System.out.println("testing Calculator.divideStr(String str, char c) ...");
        try {
            String strs[] = ct.divideStr("123.456", '.');
            if((strs[0].length() != 3) || (strs[1].length() != 3)){
                throw new CalculatorException("");
            }
        } 
        catch(CalculatorException calcE) {
            calcE.printStackTrace();
        }
        System.out.println("passed");

        System.out.println("testing Calculator.pow(double number, int pot) ...");
        try {
            if(ct.pow(2.5, 2) != 6.25)
                throw new CalculatorException("Function Calculator.pow(double number, int pot) is not working correctly");
        }
        catch (CalculatorException calcE) {
            calcE.printStackTrace();
        }
        System.out.println("passed");

        System.out.println("testing Calculator.stringToDouble(String str) ...");
        try {
            double returned = ct.stringToDouble("654.321");
            if( returned != 654.321){
                System.err.println("expected: 654.321");
                System.err.println("received: "+returned);
                throw new CalculatorException("Function Calculator.stringToDouble(String str) is not working correctly");
            }
            
        }
        catch(CalculatorException calcE) {
            calcE.printStackTrace();
        }
        System.out.println("passed");

        System.out.println("testing Calculator.doubleToString(double r) ...");
        try {
            String str = ct.doubleToString(123.456);
            if(ct.stringToDouble(str) != 123.456)
            throw new CalculatorException("Function Calculator.doubleToString(double r) is not working correctly");
        }
        catch(CalculatorException calcE) {
            calcE.printStackTrace();
        }
        System.out.println("passed");

        System.out.println("testing Calculator.repStr(String origin, String old, String actual) ...");
        try {
            String returned = ct.repStr("123.456", "23", "00");

            if(ct.stringToDouble(returned) != 100.456){
                System.err.println("expected: 100.456");
                System.err.println("received: "+returned);
                throw new CalculatorException("Function Calculator.repStr(String origin, String old, String actual) is not working correctly");
            }
        }
        catch(CalculatorException calcE) {
            calcE.printStackTrace();
        }
        System.out.println("passed");

        System.out.println("testing Calculator.removeZeros(String str) ...");
        try {
            String withoutZeros = ct.removeZeros("0000555.123");
            if(ct.thereIsChar(withoutZeros, '0')){
                System.err.println("expected: 555.123");
                System.err.println("received: "+withoutZeros);
                throw new CalculatorException("Function Calculator.removeZeros(String str) is not working correctly");
            }
        }
        catch(CalculatorException calcE) {
            calcE.printStackTrace();
        }
        System.out.println("passed");

        System.out.println("testing Calculator.resolve(String str) ...");
        try {
            double result = ct.resolve("100+100");
            if(result != 200){
                System.err.println("expected: 200");
                System.err.println("received: "+result);
                throw new CalculatorException("Function Calculator.removeZeros(String str) is not working correctly");
            }
        }
        catch(CalculatorException calcE) {
            calcE.printStackTrace();
        }
        System.out.println("passed");

        System.out.println("testing Calculator.properlyClosedParenthesis(String str) ...");
        try {
            ct.properlyClosedParenthesis("(2*3)-(5/2)");
        }
        catch(CalculatorException calcE) {
            calcE.printStackTrace();
        }
        System.out.println("passed");

        System.out.println("testing Calculator.resolveParenthesis(String str) ...");
        try {
            ct.resolveParenthesis("(2*3)-(5/2)");
        }
        catch(CalculatorException calcE) {
            calcE.printStackTrace();
        }
        System.out.println("passed");

        System.out.println("test finish");
    }

    public void exceptionsLaunched(){

    }
}
