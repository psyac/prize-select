import java.util.Random;

public class Entry {
	
	Random ran = new Random();
	String name = null;
	String prize = null;
	int id = 0;
	
	public Entry()
	{
		name = name();
		prize = prize();
	}
	
	public String prize() {
		
		String[] arr = {"Spoon", "Clock", "Laptop", "Ipad", "Fork"};
		int x = ran.nextInt(arr.length);
		return arr[x];
	}

	public String name()
	{
		String[] arr = {"John", "Dave", "Phil", "Steve", "Gladys"}; 
		int x = ran.nextInt(arr.length);
		return arr[x];
	}

}
