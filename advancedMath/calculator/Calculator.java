package advancedMath.calculator;
import java.math.*;
import java.text.DecimalFormat;
import java.lang.StringIndexOutOfBoundsException;
import advancedMath.exceptions.CalculatorException;

public class Calculator {
  
  public double resolver2(String str) throws CalculatorException{

    String withoutParentesis = "";
    String withParentesis    = "";
    String answerString      = "";
    String buffer            = str;
    int indexOpenParentesis   = 0;
    int indexClosedParentesis = 0;
    double answerDouble = 0;
    double finalAnswer  = 0;
    boolean thereIsParentesis = false;
      
    do{

      for(int i = 0;i < buffer.length();i++){
          if( buffer.charAt(i) == '(' ){
              indexOpenParentesis = i;
          }
          if( buffer.charAt(i) == ')' ){
              indexClosedParentesis = i;
              break;
          }
      }
        
      withParentesis    = buffer.substring(indexOpenParentesis, indexClosedParentesis + 1);
      withoutParentesis = buffer.substring((indexOpenParentesis + 1), indexClosedParentesis);
      
      try {
        answerDouble = resolve(withoutParentesis);
        answerString = this.doubleToString(answerDouble);
        buffer       = this.repStr(buffer, withParentesis, answerString);
      } 
      catch(CalculatorException answerException) {
        throw new CalculatorException("Error in Calculator.resolveParentesis(String str): Failed to replace the answer in the original expression");
      }
  
      thereIsParentesis = thereIsChar(buffer, '(');
    }
    while(thereIsParentesis);
    
    try {
      finalAnswer = resolve(buffer);
    } 
    catch(CalculatorException finalAnswerE) {
      throw new CalculatorException("Error in Calculator.resolveParentesis(String str): Failed to put the final answer");
    }
    return finalAnswer;
  }
  
  public boolean thereIsChar(String str, char c){
    try {
      findChar(str, c);
    } catch (CalculatorException isException) {
      return false;
    }

    return true;
  }

  public int findChar(String str, char c) throws CalculatorException {

    if(str.length() == 0)
      throw new CalculatorException("Error in findChar: string empty");

    int index=0;
    
    while(str.charAt(index) != c){
      
      if(index == str.length()-1){
        throw new CalculatorException("Error in findChar: character not found");
      }
      index++;
    }
    return index;
  }
  
  public boolean isDigit(char c){
    if(c >= '0' && c <= '9')
      return true;

    return false;
  }
  
  public String[] divideStr(String str, char c)throws CalculatorException{

    int index;
    String subStrs[] = { "", "" };

    try{
      index=findChar(str, c);
    }
    catch(CalculatorException e){
      throw new CalculatorException("Error in Calculator.divideStr(String str, char c): the character "+c+" has not been found or string "+str+" is empty");
    }

    subStrs[0] = str.substring(0,index);
    index++;
    subStrs[1] = str.substring(index);

    return subStrs;
  }
  
  public double pow(double number, int pot){

    if(pot == 1)
      return number ;
    if(pot == 0) 
      return 1 ;
    
    double stack = number;
    int level    = pot;
    
    if(pot < 0){
      level = pot*(-1);

      for(int i = 1; i < level;i++){
        stack=stack*number;
      }

      return (1/stack);

    }

    else{
      for(int i = 1; i < level;i++){
        stack=stack*number;
      }

      return stack;
    }
  } 

  public String doubleToString(double r){
    return ""+r;
  }
  
  public double stringToDouble(String str) throws CalculatorException{
    char point='.';
    char coma =',';
    int length1, length2;
    double stack=0;
    String subStrs[] = {"",""};
    
    try{
      subStrs = divideStr(str, point);
    }
    catch(CalculatorException pointException){
      try{
        subStrs = divideStr(str, coma);
      }
      catch(CalculatorException comaException){
        subStrs[0] = str.substring(0);
        subStrs[1] = "";
      }
    }

    length1 = subStrs[0].length();

    for(int n=0;n<= length1-1;n++){
		  stack += (subStrs[0].charAt(n)-48)*(pow(10, (length1-n-1)) );
    }
    
    length2 = subStrs[1].length();
    
    if(length2 == 0){
      return stack;
    }
	  else{
	    for(int n=0;n<= length2-1;n++){
		    stack+=(subStrs[1].charAt(n)-48)*(pow(10,(0-n-1)));
	    }
    }
    
    double multiplierForReadondly = pow(10, length2);

    double stackReadondlied = (Math.round(stack*multiplierForReadondly))/multiplierForReadondly;

	  return stackReadondlied;
  }
  
  public String repStr(String origin, String old, String actual) throws CalculatorException{

    int g         = 0;
    int charge    = 0;
    int lengthOld = old.length();

    do{
      for(int i=0;i<= lengthOld -1;i++){
        if(origin.charAt(i+g) == old.charAt(i))
          charge++;
        else 
          charge=0;
		  }
      if(g == (origin.length()-1)){
        throw new CalculatorException("Error in Calculator.repStr(String origin, String old, String actual): string not found");
      }
		  g++;
    }
    while(charge<lengthOld);

    g--;
    String firstPart, lastPart, returnedString;

    firstPart      = origin.substring(0,g);
    lastPart       = origin.substring(g+lengthOld);
    returnedString = firstPart+actual+lastPart;

    return returnedString;
  }
  
  public String removeZeros(String str) throws CalculatorException{
    String intPart   = "" ;
    String floatPart = "" ;
    int i = 0;
    
    if(str.length() == 0) throw new CalculatorException("Error in Calculator.removeZeros(String str): empty string") ;
    
    while((str.charAt(i)) == '0'){
      
      if(i==0){
        intPart=str.substring(0);
      }
      else{
        if((str.charAt(i)) == '.'){
          intPart=str.substring(i-1);
        }
        else{
          intPart=str.substring(i);
        }
      }
      i++;
    }
    
    i=intPart.length()-1;

    while((intPart.charAt(i)) == '0'){
      i--;
      if(i==(intPart.length()-1)){
        floatPart=intPart.substring(0,intPart.length());
      }
      else{
        if((intPart.charAt(i))=='.')
          floatPart=intPart.substring(0,i);
        else 
          floatPart=intPart.substring(0,i+1);
      }
    }
    return floatPart;
  }
  
  public double resolve(String str) throws CalculatorException{
    do{

      int operation = 0;
      int index     = 0;
      double firstOperatorDouble  = 0;
      double secondOperatorDouble = 0;
      double answerDouble         = 0;
      String firstOperator   = "";
      String secondOperator  = "";
      String expressionString = "";
      String answerString    = "";

      try{
        operation = findChar(str, '*');
      }
      catch(CalculatorException multipliE){
        try {
          operation = findChar(str, '/');
        } catch (CalculatorException divideE) {
          try {
            operation = findChar(str, '-');
          } catch (CalculatorException lessE) {
            try {
              operation = findChar(str, '+');
            } catch (CalculatorException plusE) {
              try{
                return stringToDouble(str);
              }
              catch(CalculatorException toDoubleException){
                throw new CalculatorException("Error in Calculator.resolve(String str): it can't resolve the operation");
              }
            }
          }
        }
      }
      
		  index = operation-1;
	    
	    
	    try{
	      while(isDigit(str.charAt(index)) || (str.charAt(index) == '.')){
          
          firstOperator = str.charAt(index)+firstOperator;
          
          if(index == 0)
              break;

          index--;
	      }
	    }
	    catch(StringIndexOutOfBoundsException e){
	      throw new CalculatorException("Error in Calculator.resolve(String str): fail to copy the first operator");
      }

      index = operation+1;

	    try{
	      while(isDigit(str.charAt(index)) || (str.charAt(index) == '.')){

          secondOperator = secondOperator + str.charAt(index);

          if(index == str.length()-1)
            break;
		      index++;
	      }
	    }
	    catch(StringIndexOutOfBoundsException e){
	      throw new CalculatorException("Error in Calculator.resolve(String str): Failed to copy the second operator");
      }

	    try {
        firstOperatorDouble  = stringToDouble(firstOperator);
        secondOperatorDouble = stringToDouble(secondOperator);
      } 
      catch(CalculatorException toDoubleException) {
        throw new CalculatorException("Error in Calculator.resolve(String str): Failed to convert first or second operator");
      }

      expressionString = firstOperator+str.charAt(operation)+secondOperator;
	    
	    switch(str.charAt(operation)){
		    case '*': answerDouble = firstOperatorDouble * secondOperatorDouble; break;
	      case '/': answerDouble = firstOperatorDouble / secondOperatorDouble; break;
		    case '+': answerDouble = firstOperatorDouble + secondOperatorDouble; break;
		    case '-': answerDouble = firstOperatorDouble - secondOperatorDouble; break;
		    default: answerDouble  = 0;
      }
      
      answerString = doubleToString(answerDouble);
      
      try {
        str = repStr(str, expressionString, answerString);
      } 
      catch(CalculatorException replaceException) {
        throw new CalculatorException("Error in Calculator.resolve(String str): Failed to replace the answer string in the original expression");
      }
      
      
    }while(true);
  }



}

