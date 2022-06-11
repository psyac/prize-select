import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

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
		
		Scanner cnt = new Scanner(new File(file));
		int count = 0;
		cnt.useDelimiter(",");
		while(cnt.hasNext())
		{
			 count++;
		}
		cnt.close();
		
		String[] arr = new String[count];
		Scanner sc = new Scanner(new File(file));
		sc.useDelimiter(",");
		int i = 0;
		while(cnt.hasNext())
		{
			//arr[i] = sc.next();
			i++;
			System.out.println(sc.next());
		}
		sc.close();
		return arr;
	}
	
}
