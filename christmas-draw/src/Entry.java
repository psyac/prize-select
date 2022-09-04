import java.io.File;
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;
import java.util.Random;

public class Entry {
	
	Random ran = new Random();
	String name = null;
	String prize = null;
	int id = 0;
	
	public Entry()
	{
		try {
			prize = prize();
			name = name();
		} catch (Exception e) {
			prize = "fail";
			name = "fail";
		}

	}
	
	public String prize() throws Exception {
		
		String file = "Prizes.csv";
		String[] arr = scan(file);
		System.out.println("*****");
		int x = ran.nextInt(arr.length);
		return arr[x];
		
	}

	public String name() throws Exception
	{	
		String file = "Names.csv";
		String[] arr = scan(file);
		int x = ran.nextInt(arr.length);
		return arr[x];
	}
	
	public String[] scan(String file) throws Exception
	{
		String[] outList = null;
		String line = "";  
		String splitBy = ",";  
		int place = 0;
		try   
		{  
		//parsing a CSV file into BufferedReader class constructor  
		BufferedReader br = new BufferedReader(new FileReader(file));  
		while ((line = br.readLine()) != null)   //returns a Boolean value  
		{  
			String[] list = line.split(splitBy);    // use comma as separato
			if(list[1] == null)
				break;
			System.out.println("Number=" + list[0] + ", Name=" + list[1]); 
			outList[place] = list[1];
		}  
		}   
		catch (IOException e)   
		{  
		e.printStackTrace();  
		}
		return outList;
	}
	
}
