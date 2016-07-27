package newtest;

import javax.swing.text.*;

public class NumberLenghtLimitedDmt extends PlainDocument { 
  static final int inlimit= 1;  
  static final int nolimit= 2;  
  
  int  limitstyle=0;
  
	   private int limit; 
	   public NumberLenghtLimitedDmt(int limit,int limitstyle) 
	   {
	      super();
	      this.limit = limit;
	      this.limitstyle=limitstyle;
	   }
	   
	   public void insertString(int offset, String  str, AttributeSet attr)
	       throws BadLocationException {   
	       if (str == null){
	        return;
	       }
	       if ((getLength() + str.length()) <= limit) 
	      {	       
	       char[] upper = str.toCharArray();
	       int length=0;
	        for (int i = 0; i < upper.length; i++) 
	        {  
	 	       if(limitstyle==inlimit)
		       {
	             if (upper[i]>='0'&&upper[i]<='9')          
	              upper[length++] = upper[i];
	            }
	 	       else
	 	          upper[length++] = upper[i];
	        }
	        super.insertString(offset, new String(upper,0,length), attr);
	      }
	    }
	}