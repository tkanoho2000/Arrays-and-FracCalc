package fracCalc;
/*Tyler Kanoho
 * 
 * Version 1.0
 * 
 * This really hurt to make:'(
 * I lost sleep over this
 */


import java.util.*;
public class FracCalc {

    public static void main(String[] args) 
    {
    	Scanner input = new Scanner(System.in);
    	String end = "";
    	System.out.print("Please kind sir, would you spare a fraction:");//asks user for fraction
		end = input.nextLine();//takes user input
    	while(!end.toUpperCase().equals("QUIT")) {//this runs so long as the user doesn't enter "quit"
    		
    		System.out.println(produceAnswer(end));//takes user input and puts it in to produceAnswer
			System.out.print("Please kind sir, would you spare a fraction:");//asks user for fraction
    		end = input.nextLine();//takes user input
			
    	}
    	System.out.println("Thanks for the fractions loser!");
		System.out.println("Now get outta here ya filthy animal!");
		System.out.println("Scram before I change my mind!");
    	input.close();//when taken out of the loop, it won't accept input anymore
   
    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input) {//this method formats the user input so that it looks somewhat presentable
    	String[] fractions = input.split(" ");//this splits the user input by the spaces and puts it in an array called fractions
    	String operand1 = fractions[0];//assigns index 0 of fractions as operand1
    	String operator = fractions[1];//assigns index 1 of fractions as operator
    	String operand2 = fractions[2];//assigns index 2 of fractions as operand2
    	int wholeNum1 = 0;//set all values equal to 0 since they aren't being used currently
    	int wholeNum2 = 0;
    	int numerator1 = 0;
    	int denominator1 = 1;//except the denominators which must not be equal to 0 or they won't work
    	int numerator2 = 0;
    	int denominator2 = 1;
     	if (operand2.indexOf("_") > -1) { //when there is an "_" in operand2
    		wholeNum2 = Integer.parseInt(operand2.split("_")[0]); //splits everything on the left side of the "_" and names it wholeNum2
    		numerator2 = Integer.parseInt((operand2.split("_")[1]).split("/")[0]); //splits other half of operand2 and names it numerator2
    		denominator2 = Integer.parseInt((operand2.split("_")[1]).split("/")[1]); //names the half to the right of operand2 denominator2
    		numerator2 = toImproperFracNum(wholeNum2,numerator2,denominator2);//puts it into a method called toImproperFracNum
    	} 
    	else {
    		if (operand2.indexOf("/") > -1) { //when there is a "/" in operand2
    			numerator2 = Integer.parseInt((operand2.split("/")[0])); //splits by the "/" and puts the left side as numerator
    			denominator2 = Integer.parseInt((operand2.split("/")[1])); // puts the other side as denominator
    		} else {
    				numerator2 = Integer.parseInt(operand2);//when there is a whole number but no fraction
    			}
    			
    		}
    	if (operand1.indexOf("_") > -1) { //when there is an "_" in operand1
    		wholeNum1= Integer.parseInt(operand1.split("_")[0]); //splits operand1 by the "_"
    		numerator1 = Integer.parseInt((operand1.split("_")[1]).split("/")[0]); //splits other half of operand1 by "/" to indicate fraction
    		denominator1 = Integer.parseInt((operand1.split("_")[1]).split("/")[1]); //left of "/" is the numerator and other is denominator
    		numerator1 = toImproperFracNum(wholeNum1,numerator1,denominator1);//sends this into a different method to be turned into an improper fraction
    	} 
    	else {
    		if (operand1.indexOf("/") > -1) { //when there is a "/"
    			numerator1 = Integer.parseInt((operand1.split("/")[0])); //splits operand2 by "/"
    			denominator1 = Integer.parseInt((operand1.split("/")[1])); // creates a den + num
    		} else {
    				numerator1 = Integer.parseInt(operand1);//if there is nothing but a whole number and no fraction
    			}
    			
    		}
    	String answer = "";//creates two indexes in an array called answer
 //   if	(operator.equals("+")||operator.equals("-")||operator.equals("*")||operator.equals("/")) {//looks for operators to perform certain methods
    	if(operator.equals("+")) {//goes through the array and looks for "+"
    		answer =  mixedNum(reduceAnswer(addition(numerator1, numerator2, denominator1, denominator2)));//calls addition method
    	}
    	else if(operator.equals("-")) {//goes through the array and looks for "-"
    		answer = mixedNum(reduceAnswer(subtraction(numerator1, numerator2, denominator1, denominator2)));//calls subtraction method
    	}
    	else if(operator.equals("/")) {//goes through the array and looks for "/"
    		answer =  mixedNum(reduceAnswer(divide(numerator1, numerator2, denominator1, denominator2)));//calls divide method
    	}
    	else if(operator.equals("*")) {//goes through the array and looks for "*"
    		answer = mixedNum(reduceAnswer(multiply(numerator1, numerator2, denominator1, denominator2)));//calls multiply method
    	//answer = toMixedNumber(answer);
    	}
   // }
 	   return answer;
    	
   }
    public static int[] addition(int numerator1, int numerator2, int denominator1, int denominator2) {//method to add fractions
    	int addNum = (denominator1 * numerator2) + (numerator1 * denominator2);//bow tie method
    	int addDenom = denominator1 * denominator2;//makes common denominator
    	int[] answer = {0,1};//assigns addNum and addDenom into elements of array answer
    	answer[0] = addNum;
    	answer[1] = addDenom;
    	System.out.println(Arrays.toString(answer));
    	return (answer);
    }
    public static int[] subtraction(int numerator1, int numerator2, int denominator1, int denominator2) {//method to subtract fractions
    	int subNum = (denominator2 * numerator1) - (numerator2 * denominator1);
    	int subDenom = denominator1 * denominator2;//to make simpler answer[0] = ....
    	int[] answer = {0,1};//formats answer array so that numerator is in 0 and denominator is 1
    	answer[0] = subNum;
    	answer[1] = subDenom;
    	return (answer);
    }
    public static int[] multiply(int numerator1, int numerator2, int denominator1, int denominator2) {//method to multiply fractions
    	//multiply straight across
    	int[] answer =  {0,1};
    	answer[0] = numerator1 * numerator2;
    	answer[1] = denominator1 * denominator2;
    	return (answer);
    }
    public static int[] divide(int numerator1, int numerator2, int denominator1, int denominator2) {//method to divide fractions        
    	int[] answer =  {0,1};
    	answer[0] = numerator1 * denominator2;
    	answer[1] = denominator1 * numerator2;
    	return (answer); // return answer
    	}
    public static int toImproperFracNum(int wholeNum, int numerator, int denominator) {//converts mixed numbers into improper fractions so easier to operate with
    	int improperNum = 0;
    	if(wholeNum < 0) {//check to see if whole number is negative
    		improperNum = (wholeNum * denominator) - numerator;
    		
    	}
    	else {//regular improper fraction
    		improperNum = (wholeNum * denominator) + numerator;
    	}
    	return improperNum;
    			
    } 
    public static int gcf(int numerator, int denominator) {//uses euclid's method to find the greatest common factor
    	if(denominator == 0) {//tests is denominator is 0 which cannot work
    		return numerator;//if so, returns only the numerator
    	}
    	return gcf(denominator, numerator % denominator);//needed in reduceAnswer
    }
    public static int[] reduceAnswer(int[] fraction) {//simplifies answer ex. 8/16 --> 1/2
    	int[] answer = {0,1};
    	int gcf = gcf(fraction[0], fraction[1]);
    	answer[0] = fraction[0] / gcf;
    	answer[1] = fraction[1] / gcf;	
    	return answer;
    }
    
    public static String mixedNum(int[] input) {//turns improper fraction into a mixed number
    	int wholenum = input[0] / input[1];
    	int remainder = input[0] % input[1];
    	int denominator = input[1];
    	String answer = "";
    	
    	if(denominator < 0) {//if there is a negative denominator then change it to positive
    		denominator *= -1;
    		remainder *= -1;//and make the numerator negative instead
    	
    	}
    	
    	if(wholenum == 0) {//if there is no whole number print out just the fraction
    		if(remainder==0) {
    			answer = "0";
    		}
    		else {
    			answer= remainder + "/" + denominator;
    			}
    		}
    	else {
    		if(remainder==0) {//if there is no fraction print out whole number
    			answer = Integer.toString(wholenum);
    		}else {
    			if(remainder < 0) {//if the numerator is negative
    				remainder *= -1;//change to positive
    			}
    			answer = wholenum + "_" + remainder + "/" + denominator; //formats answer ex. 2_1/2
    		}
    	}
    	return answer;
    }
}	