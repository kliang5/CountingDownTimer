
//Name: Kexin Nicole Liang
//Student Number:  260828103

// do NOT touch these import statements 
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

public class CountdownDays {
    
    // the method returns a String representing the current date in the following format: dd/mm/yyyy
    // you can use it, but do NOT modify it!
    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        return dtf.format(now);
    }
    
    
    //the method takes a String and two integers as input and  
    //returns a String composed by all the characters from the input String
    //that are within the positions i and j (including both)
    public static String getSubstring(String s, int i, int j) {
    	
    	//check the condition whether the second input is larger than the first one
    	if (j<i) {
    		throw new IllegalArgumentException("The second input should be greater than the first one");
    	}
    	//use for loops to get the new subString composed by characters 
    	//from String s from index i to index j
    	String subString="";
    	for(int index=i;index<=j; index++) {
    		subString+= s.charAt(index);
    	}
    	return subString;
    }
    
    // a method takes a String (represents a date) as input and
    //returns the integers at the first two index (represents the day of the date)
    public static int getDay(String a) {
    	int day;
    	
    	//check if the first index is 0, only returns the second index
    	
    	//use method getSubString to get the integers
    	//from index 0 to 1 or from index 1 to 1
    	if(a.charAt(0)==0) {
    		day= Integer.parseInt(getSubstring(a,1,1));
    		return day;
    	}
    	day=Integer.parseInt(getSubstring(a,0,1));
    	return day;
    }
    
    // a method takes a String (represents a date) as input and
    //returns the integers from the index 3 to 4 (represents the month of the date)
    public static int getMonth(String a) {
    	int month;
    	
    	//check if the index 3 is 0, only returns the index 4
    	
    	//use method getSubString to get the integers
    	//from index 3 to 4 or from index 4 to 4
    	if(a.charAt(3)==0) {
    		month= Integer.parseInt(getSubstring(a,4,4));
    		return month;
    	}
    	month=Integer.parseInt(getSubstring(a,3,4));
    	return month;
    }
    
    // a method takes a String (represents a date) as input and
    //returns the integers from the index 6 to 9 (represents the year of the date)
    public static int getYear(String a) {
    	int year;
    	
    	//use method getSubString to get the integers with valid numbers(no 0 in the front)
    	if(a.charAt(6)==0&&a.charAt(7)==0&& a.charAt(8)==0) {
    		year=Integer.parseInt(getSubstring(a,9,9));
    	}else if(a.charAt(6)==0&&a.charAt(7)==0) {
    		year=Integer.parseInt(getSubstring(a,8,9));
    	}else if(a.charAt(6)==0) {
    		year=Integer.parseInt(getSubstring(a,7,9));
    	}else {
    		year=Integer.parseInt(getSubstring(a,6,9));
    	}
    	return year;
    }
    
    // a method that takes an integer(represents a year)as input and 
    //returns true if it is divisible by 4 (represents leap year) and false otherwise
    //* for century years, returns true if it is divisible by 400 and false otherwise
    public static boolean isLeapYear(int year) {
    	//check whether it is a century year
    	if (year%100==0) {
    		if(year%400==0) {
    			return true;
    		}else {
    			return false;
    		}
    	//for non-century years
    	}else if(year%4==0) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    //a method that takes two integers (represents month and year respectively) and
    //returns an integer (represents days) in the given month
    public static int getDaysInAMonth(int month, int year) {
    	int days;
    	//for the February in leap years, there are 29 days
    	//use if statements to give the days in a given month
    	//for the month January,March,May,July,August,October,December, there are 31 days
    	//for the month April,June,September,November, there are 30 days 
    	//and 28 days for the February in usual years
    	if(isLeapYear(year)&& month==2) {
    			days=29;
    	}else if (month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
    		days=31;
    	}else if(month==4||month==6||month==9||month==11) {
    		days=30;
    	}else {
    		days=28;
    	}
    	
    	return days;
    }
    
    //a method that takes two Strings represented the current date and due date respectively
    //and returns true if the due date comes earlier or is equal to the current date
   public static boolean dueDateHasPassed(String current,String due) {
	   //use if statement to compare the date in a order from years to days
	   if (getYear(due)<getYear(current)) {
		   return true;
	   }else if((getYear(due)==getYear(current)) && (getMonth(due)< getMonth(current))) {
		   return true;
	   }else if((getYear(due)==getYear(current)) && (getMonth(due)==getMonth(current))
			   && (getDay(due)<=getDay(current))) {
		   return true;
	   }else {
		   return false;
	   }
   }
   
   //a method that takes two Strings represented the current date and due date respectively
   //and returns an integer that counts the number of days between the current date and due date
   public static int countDaysLeft(String current,String due) {
	   int days=0;
	   //if the due date already passed, return 0
	   if(dueDateHasPassed( current, due)) {
		   return 0;
	   }
	   //use if statement and for loops to add all days in the difference years
	   if (getYear(due)>getYear(current)) {
		   for(int i= getYear(current);i< getYear(due);i++) {
			   if(isLeapYear(i+1)) {
				   days += 366;
			   }else {
				   days += 365;
			   }
		   }
	   }
	   //use if statement and for loop to add all days in the difference months, when 
	   //the month of due date is larger than the month of current date
	   if(getMonth(due)>getMonth(current)) {
		  for(int j=getMonth(current);j<getMonth(due);j++) {
			  days+= getDaysInAMonth(j,getYear(due));
		  }
	   }
	   // and minus all days in the difference months from the days in difference years calculated before, 
	   //when the month of due date is smaller than the month of current date
	   if(getMonth(due)<getMonth(current)) {
		  for(int j=getMonth(due);j<getMonth(current);j++) {
				  days-= getDaysInAMonth(j,getYear(due));
			  }
		   }
	   //add all days in difference days when the day of due date 
	   //is larger than the day of current date
	   if(getDay(due)>getDay(current)) {
		   days+=getDay(due)-getDay(current);
	   }
	 //minus all days in difference days when the day of due date 
	   //is smaller than the day of current date
	   if(getDay(due)<getDay(current)) {
		   days-=getDay(current)-getDay(due);
	   }
	   return days;
   }
   
   //a method that takes a String (represents due date) as input 
   //and displays all the information: current date, due date and the count-down time
   public static void displayCountdown(String due) {
	   System.out.println("Today is: "+getCurrentDate());
	   System.out.println("Due date: "+due);
	   //use if statement and method dueDateHasPassed to check whether the due date has been passed
	   if(dueDateHasPassed(getCurrentDate(),due)) {
		   System.out.println("The due date has passed! :( Better luck next time!");
		   // 如果一样呢
		//use the method countDaysLeft to display the count-down dates
	   }else {
		   System.out.println("You have "+ countDaysLeft(getCurrentDate(),due)+" days left. You can do it!");
	   }
	   
   }
    
    public static void main(String[] args) {
    	// check the method getSubString whether work
    	//System.out.println(getSubstring("monday",-6,4));
    	
    	// check the method getDay whether work
    	//System.out.println(getDay("02/03/1029"));
    	 
    	// check the method getMonth whether work
    	//System.out.println(getMonth("02/03/1029")); 
    	
    	// check the method getYear whether work
    	//System.out.println(getYear("02/03/0009"));
    	
    	// check the method isLeapYear whether work
    	//System.out.println(isLeapYear(2100));
    	
    	// check the method getDaysInAMonth whether work
    	//System.out.println(getDaysInAMonth(2,1853));
    	
    	// check the method dueDateHasPassed whether work
    	//System.out.println(dueDateHasPassed("13/02/2002","09/07/2002"));
    	
    	// check the method countDaysLeft whether work
    	//System.out.println(countDaysLeft("13/10/2018","26/02/2019"));
    	//System.out.println(countDaysLeft("13/10/2000","08/05/2029"));
    	
    	//display the info
    	String dueDate=args[0];
    	displayCountdown(dueDate);
    }
    
    
}
