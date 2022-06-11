
public class Output {

	public Output(String[] record) {
		
		PGPFile file = new PGPFile();
		//PGPText text = new PGPText();
			
		file.openWriteFile("Winners.txt");
		for(int i=0; i < record.length; i++)
		{
			file.writeLine(record[i]);
		}
		file.closeWriteFile();
		
	}


	public void output(String[] record) {
			
		PGPFile file = new PGPFile();
		//PGPText text = new PGPText();
			
		file.openWriteFile("Winners.txt");
		for(int i=0; i < record.length; i++)
		{
			file.writeLine(record[i]);
		}
		file.closeWriteFile();

	}

}
