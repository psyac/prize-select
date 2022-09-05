import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;
import java.util.ArrayList; 

public class Selector {

	public static void main(String[] args) {
		
		ArrayList<String> names = name();
		ArrayList<String> prizes = prize();
		
//		for(int i = 1; i < names.size(); i++)
//		{
//			System.out.print(names.get(i) + "\n");
//		}
		for(int i = 1; i < prizes.size(); i++)
		{
			System.out.print(prizes.get(i) + "\n");
		}
	}

	public static ArrayList<String> name()
	{
		String line = "";  
		String splitBy = ",";
		ArrayList<String> list = new ArrayList<String>();
		int i = 0;
		try   
		{  
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader("Names.csv"));  
			while ((line = br.readLine()) != null)   //returns a Boolean value  
			{  
				String[] cell = line.split(splitBy);    // use comma as separato
				//System.out.println("Name= " + cell[0]);
				i++;
				list.add(i + " " + cell[0]);
			}  
		}   
		catch (IOException e)   
		{  
			e.printStackTrace();  
		}
		return list;
	}
	
	public static ArrayList<String> prize()
	{
		String line = "";  
		String splitBy = ",";
		ArrayList<String> list = new ArrayList<String>();
		int i = 0;
		try   
		{  
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader("Prizes.csv"));  
			while ((line = br.readLine()) != null)   //returns a Boolean value  
			{  
				String[] cell = line.split(splitBy);    // use comma as separato
				//System.out.println("Name= " + cell[0]);
				list.add(cell[0]);
			}  
		}   
		catch (IOException e)   
		{  
			e.printStackTrace();  
		}
		return list;
		
	}
}
