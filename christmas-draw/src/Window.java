import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window {
	
	Random ran = new Random();
	
	JFrame guiFrame = new JFrame();
	Dimension size = new Dimension();
	
	String[] record = new String[256];
	int y = 0;
	
	public Window()
	{
		String winner = createArr();
		
		//Set the frame up
		size.setSize(500, 500);
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Sets close to exit button
		guiFrame.setTitle("Christmas Draw");//Sets window title
		guiFrame.setLayout(new BorderLayout());
		guiFrame.setPreferredSize(size);
		
		//Set the panel
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		pan.setSize(size);
		pan.setBackground(Color.red);
		
		//Set the label
		JLabel lab = new JLabel(winner.toUpperCase());
		lab.setFont(new Font("Veranada", Font.BOLD, 50));
		pan.add(lab);
		
		//Set the button
		JButton refresh = new JButton("REROLL");
		refresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String win = createArr();
				lab.setText(win.toUpperCase());
			}
			
		});
		
		//Set record button
		JButton reveal = new JButton("Output");
		reveal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new Output(record);
			}
			
		});
		
		
		//Finalise the frame
		refresh.setPreferredSize(new Dimension (500, 100));
		guiFrame.add(refresh, BorderLayout.SOUTH);
		guiFrame.add(reveal, BorderLayout.NORTH);
		guiFrame.add(pan, BorderLayout.CENTER);
		guiFrame.pack();//Relative to setting up the window
		guiFrame.setLocationRelativeTo(null);
		guiFrame.setVisible(true);
		
	}
	
	public int randomGen()
	{
		int i = ran.nextInt(100);
		return i;
	}
	
	public String createArr()
	{
		int len = 5;
		Entry[] arr = new Entry[len];
		
		for(int i = 0; i<len; i++)
		{
			arr[i] = new Entry();
		}
		
		int x = ran.nextInt(len);
		String winner = arr[x].name + " " + arr[x].prize;
		record[y] = winner;
		y++;
		
		return winner;
	}

}

