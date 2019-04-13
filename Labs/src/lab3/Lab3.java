package lab3;

public class Lab3 {

	
		/*
		 * The purpose of reverseString, as you may have guessed is to reverse the characters of a stirng. 
		 * For instance if you call the method reverseString with the input Hello, then the output will be
		 * olleH. 
		 * 
		 * Here are some suggestions for you to complete this method. 
		 * 1. Find the length of the string str first. Hint: Use the length() method on str. 
		 * 
		 * 2. Run a loop starting from length-1 and pull each character out from str. Hint: Use a for loop or
		 * a while loop and look at the method charAt(...). 
		 * 
		 * 3. Use string concatenation to build a new string inside a loop character by character starting from the 
		 * end of str to the start of str. 
		 * 
		 * 4. Return back str. 
		 * 
		 * You can easily test this out, by going into your main function, and writing test cases for
		 * reverseString similar to what I have done already for reverseSentence. 
		 */
		
		/**
		 * Takes in a string as input and reverses it
		 * @param String str The inputted string
		 * @return The reversed version of str
		 */
		public static String reverseString(String str)
		{
			int len = str.length(); //find length of string
			String newstr = ""; //create new str
			for (int i = len-1; i >= 0; i--) { //run for loop from end of string to beginning
				newstr += str.charAt(i); // concatenate from end of string to start of string
			}
			str = newstr; 
			return str; //return the string
		}
		
		
		/*
		 * The purpose of this method is to reverse a sentence. You do not reverse the characters in each word. 
		 * I ask you first to read the three testcases that we have provided before you complete this method. 
		 * You should only start working on this method, after you have completed the method reverseString. 
		 * You must have guessed by now, that reveseString is acting as a helper method. It is a method that 
		 * is used by the reverseSentence method.
		 * 
		 *  Here are some suggestions to help you get started. You may need two loops to get this completed.
		 *  Make sure you understand the following pieces first by sketching your idea on a piece of paper.  
		 *  
		 *  1. Call reverseString first on str. And call the reverse of str as r
		 *  
		 *  2. Find the length of r. 
		 *  
		 *  3. Go across every character of r starting from index 0 towards the end of r. 
		 *  
		 *  4. Now when you hit a white space in r. Stop. Extract everything from some `startIndex'
		 *  to `currentIndex' and call this sub string as s. 
		 *  
		 *  5. Now call reverseString on s. If it helps, you may want to print debugging messages to make sure what the 
		 *  value of s is at this point. 
		 *  
		 *  6. Now think carefully how should you update startIndex and currentIndex inside your loops. 
		 *  
		 *  7. Take the s and append it into some other string such as ret. The string ret is used to represent
		 *  the reverseSentence version of str. 
		 */
		
		/**
		 * Takes in a String sentence as input and reverses the order of the words in the sentence.
		 * @param str The inputted string
		 * @return str, with words in reverse order
		 */
		public String reverseSentence(String str)
		{
			String r = reverseString(str); //reverseString called on str
			int rLength = r.length(); //length of r
			int startIndex = 0; //starting index
			String ret = ""; //str we will append the words to
			
			for (int currentIndex = 0; currentIndex < rLength; currentIndex++) { //run for loop from start of 'r'
				if (r.charAt(currentIndex) == ' ') { //if whitespace in r
					String s = r.substring(startIndex, currentIndex); //create a substring s (startIndex to char before whitespace)
					ret += reverseString(s) + " "; //append the reverse of s to 'ret'
					startIndex = currentIndex + 1; //move the startIndex
				}
				
				else if (currentIndex == rLength - 1) { //if at last index
					String s = r.substring(startIndex, currentIndex + 1); //create a substring s (startIndex to last char)
					ret += reverseString(s); //append the reverse of 's' to 'ret'
				}
			}
			
			return ret; //return the reverse word sentence
		}
		
		public static void main(String[] args)
		{
			/*
			 * TestCase1. 
			 * Input String: Hello welcome to CSCB07 Lab3
			 * Output String: Lab3 CSCB07 to welcome Hello
			 */
			Lab3 lab3Reference=new Lab3();
			System.out.println(lab3Reference.reverseSentence("Hello welcome to CSCB07 Lab3"));
			
			
			/*
			 * TestCase2. 
			 * Input String: Hello there, my name is CSCB07
			 * Output String: CSCB07 is name my there, Hello
			 */
			System.out.println(lab3Reference.reverseSentence("Hello there, my name is CSCB07"));
			
			/*
			 * TestCase3. 
			 * Input String: Hello
			 * Output String: Hello
			 */
			System.out.println(lab3Reference.reverseSentence("Hello"));
			
			/*
			 * You can add any of your other test cases here starting as TestCase4
			 */
			
			/*
			 * TestCase4
			 * Input String: Bananas
			 * Output String: sananaB
			 */
			System.out.println(reverseString("Bananas"));
			
			/*
			 * TestCase5
			 * Input String: CSCB07
			 * Output String: 70BCSC
			 */
			System.out.println(reverseString("CSCB07"));
			
			/*
			 * TestCase6
			 * Input String: 123456
			 * Output String: 654321
			 */
			System.out.println(reverseString("123456"));
		}

}