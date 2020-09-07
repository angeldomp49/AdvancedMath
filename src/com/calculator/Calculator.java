package src.com.calculator;
import java.math.*;
import java.lang.StringIndexOutOfBoundsException;
import src.com.exceptions.CalculatorException;

public class Calculator {
  private String cadena;
  private boolean exceptionRempCad=false;
  private int PAbierto;
  private int PCerrado;
  private String buferDeQuitarParetesis;
  
  public double resolver2(String cadena){
    /*resuelve una cadena que contenga una operacion a realizar con parentesis*/
      String cadSP;
      String cadCP;
      String res;
      double r;
      this.buferDeQuitarParetesis = cadena;
      
      do{
          //obtenemos los dos indices de los parentesis
          for(int i=0;i<this.buferDeQuitarParetesis.length();i++){
              if(this.buferDeQuitarParetesis.charAt(i)=='('){
                  this.PAbierto = i;
              }
              if(this.buferDeQuitarParetesis.charAt(i)==')'){
                  this.PCerrado = i;
                  break;
              }
          }
          
      //recortamos la cadena delimitada por los parentesis, la resolvemos y sustituimos por su resultado
      cadCP = this.buferDeQuitarParetesis.substring(this.PAbierto, (this.PCerrado +1));
      cadSP = this.buferDeQuitarParetesis.substring((this.PAbierto+1), this.PCerrado);
      System.out.println(cadCP);
      System.out.println(cadSP);
      
      //verificamos que cadSparentesis no lleve algun parentesis, puesto que esto genera un error
      if((this.buscarC(cadSP, '(')!= -1)||((this.buscarC(cadSP, ')')!= -1)))
      {   System.out.println("error en la variable casSParentesis de la funcion resolver2");
          return 0;}
      
      r = this.resolver(cadSP);
      res = this.RAL(r);
      this.buferDeQuitarParetesis = this.rempCad(this.buferDeQuitarParetesis, cadCP, res);
      }
      while(this.buscarC(this.buferDeQuitarParetesis, '(') != -1);
      
      return this.resolver(this.buferDeQuitarParetesis);
  }
  
  public int findChar(String str, char c) throws CalculatorException {

    if(str.length() == 0)
      throw new CalculatorException("Error in findChar: string empty");

    int index=0;
    
    while(str.charAt(index) != c){
      
      if(index == str.length()){
        throw new CalculatorException("Error in findChar: character not found");
      }
      index++;
    }
    return index;
  }
  
  public boolean isDigit(char c){
    ((c>= '0') && (c<= '9')) ? return true : return false;
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
    
    length2=subStrs[1].length();
    
    if(length2==0){
      return stack;
    }
	  else{
	    for(int n=0;n<= length2-1;n++){
		    stack+=(subStrs[1].charAt(n)-48)*(pow(10,(0-n-1)));
	    }
	  }
	  return stack;
  }
  
  public String repStr(String origin, String old, String actual){

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
      if(g==(origin.length()-1)){
        this.exceptionRempCad=true;
        return c;
      }
		g++;
    }while(cuenta<l);
    g--;
    String x, y, z;
    x=c.substring(0,g);
    y=c.substring(g+l);
    z=x+n+y;
    return z;
  }
  public String removeZeros(String str){
    String intPart;
    String floatPart;
    int i=0;
    
    if(str.length() == 0) throw new Exception("cadena vacia en Algebra.removeZeros()") ;
    
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
      if((intPart.charAt(i))=='.')floatPart=intPart.substring(0,i);
      else floatPart=intPart.substring(0,i+1);
    }
    }
    return c;
  }
  
  public double resolver(String cadena){
      //resuelve la operacion cadena y devuelve su resultado double si no tiene parentesis
    do{
      int op;
      op=buscarC(cadena, '*');
	    if(op<0) op=buscarC(cadena, '/');
	    if(op<0) op=buscarC(cadena, '-');
	    if(op<0) op=buscarC(cadena, '+');
	    if(op==-1) return this.LAR(cadena);
	    
		  int i=op-1;
	    int m=9;
	    String p="";
	    String s="";
	    
	    try{
	      while(this.esDigito(cadena.charAt(i)) || (cadena.charAt(i) =='.')){
	          if(i==0)break;
		        m--;
		        i--;
	      }
	    }
	    catch(StringIndexOutOfBoundsException e){
	      System.err.println("error en la linea 176");
	      return - 1;
      }
      if(i!=0)i++;
	    p=cadena.substring(i,op);
	    m=0;
	    i=op+1;
	    try{
	      while((this.esDigito(cadena.charAt(i)))||(cadena.charAt(i)=='.')){
	        if(i== cadena.length()-1)break;
		      m++;
		      i++;
	      }
	    }
	    catch(StringIndexOutOfBoundsException e){
	      System.err.println("error en la linea 188");
	      return - 1;
      };
	    if(i==cadena.length()-1)i++;
	    s=cadena.substring((op+1),i);
	    
	    String v=p+cadena.charAt(op)+s;
	    double x, y, r;
	    x=this.LAR(p);
	    y=this.LAR(s);
	    
	    switch(cadena.charAt(op)){
		    case '*': r=x*y; break;
	      case '/': r=x/y; break;
		    case '+': r=x+y; break;
		    case '-': r=x-y; break;
		    default: r=0;
	    }
	    String res=this.RAL(r);
	    cadena=rempCad(cadena, v, res);
    }while(true);
  }
}

