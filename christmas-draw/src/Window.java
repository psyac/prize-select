import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window {
	
	Random ran = new Random();
	
	JFrame guiFrame = new JFrame();
	Dimension size = new Dimension();
	
	ArrayList<String> names = Selector.name(); //Imports the lists of people & prizes
	ArrayList<String> prizes = Selector.prize();
	
	String[] doneNames = new String[names.size()]; // Creates list for all chosen numbers
	String[] donePrizes = new String[prizes.size()];
	
	
	String[] record = new String[prizes.size()];
	int y = 0;
	
	
	public Window()
	{
		String[] win = createArr();
		
		//Set the frame up
		size.setSize(1300, 1300);
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Sets close to exit button
		guiFrame.setTitle("Christmas Draw");//Sets window title
		guiFrame.setLayout(new BorderLayout());
		guiFrame.setPreferredSize(size);
		
		//Set the panel
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		pan.setSize(size);
		pan.setBackground(Color.black);
		
		GridBagConstraints c = new GridBagConstraints();
		
//		//Set the label
//		JLabel lab = new JLabel(win[0].toUpperCase() + " " + win[1].toUpperCase());
//		lab.setFont(new Font("Veranada", Font.BOLD, 50));
//		lab.setForeground(Color.white);
//		pan.add(lab);
		
		//Set the label
		JLabel labUp = new JLabel(win[0].toUpperCase());
		labUp.setFont(new Font("Veranada", Font.BOLD, 90));
		labUp.setForeground(Color.white);
		c.fill = GridBagConstraints.HORIZONTAL; //Allows one label to be placed above the other
		c.gridx = 0;
		c.gridy = 0;
		pan.add(labUp, c);
		
		//Set the label
		JLabel labDn = new JLabel(win[1].toUpperCase());
		labDn.setFont(new Font("Veranada", Font.BOLD, 90));
		labDn.setForeground(Color.white);
		pan.add(labDn);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		pan.add(labUp, c);

		
		//Set the button
		JButton refresh = new JButton("REROLL");
		refresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String[] win = createArr();
				//lab.setText(win[0].toUpperCase() + " " + win[1].toUpperCase());
				labUp.setText(win[0].toUpperCase());
				labDn.setText(win[1].toUpperCase());
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
		guiFrame.add(pan, BorderLayout.CENTER);
		guiFrame.add(reveal, BorderLayout.NORTH);
		guiFrame.pack();//Relative to setting up the window
		guiFrame.setLocationRelativeTo(null);
		guiFrame.setVisible(true);
		
	}
	
	
	public String[] createArr()
	{		
		String[] msg = new String[2]; //Adds to array to output to screen
		int x = ran.nextInt(names.size()); //Generates random name & prize
		while(doneNames[x] == "1") //Checks number hasnt already been drawn
		{
			x = ran.nextInt(names.size());
		}
		String winName = names.get(x);
		doneNames[x] = "1";
		
		x = ran.nextInt(prizes.size());
		int loopCheck = 0;
		while(donePrizes[x] == "1") //Checks number hasnt already been drawn
		{
			x = ran.nextInt(prizes.size());
			if(loopCheck == prizes.size()) //Displays message once all prizes have been drawn
			{
				msg[0] = "Prizes";
				msg[1] = "Out Of";
				return msg;		
			}
			loopCheck++;
		}
		String winPrize = prizes.get(x);
		donePrizes[x] = "1";
				
		String winner = winName + ", " + winPrize; //Adds to notepad file
		record[y] = winner;
		
		msg[0] = winName;
		msg[1] = winPrize;
		y++;
		
		return msg;
	}
	
}

