package newtest;

public class Main{

public static void main(String [] args){
	
   String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";

   try
   {
	Class.forName(driverName);
	System.out.println("���������ɹ���");
    }catch(Exception e)
   {
	e.printStackTrace();
	System.out.println("��������ʧ�ܣ�");
   }
 
   
   new login();
 //  new testone("12346789","123");
 }
}
 
