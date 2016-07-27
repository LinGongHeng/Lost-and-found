package newtest;

public class Main{

public static void main(String [] args){
	
   String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";

   try
   {
	Class.forName(driverName);
	System.out.println("加载驱动成功！");
    }catch(Exception e)
   {
	e.printStackTrace();
	System.out.println("加载驱动失败！");
   }
 
   
   new login();
 //  new testone("12346789","123");
 }
}
 
