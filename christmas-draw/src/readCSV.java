import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  

public class readCSV
{
	public static void main(String[] args)   
	{  
	String line = "";  
	String splitBy = ",";  
	try   
	{  
	//parsing a CSV file into BufferedReader class constructor  
	BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Aidan\\Desktop\\code\\Eclipse\\ExcelTests\\names.csv"));  
	while ((line = br.readLine()) != null)   //returns a Boolean value  
	{  
	String[] employee = line.split(splitBy);    // use comma as separato
	if(employee[1] == null)
		break;
	System.out.println("Number=" + employee[0] + ", Name=" + employee[1]);  
	}  
	}   
	catch (IOException e)   
	{  
	e.printStackTrace();  
	}  
	}  
	
}
