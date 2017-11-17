package blockchain1.mulvey.eoin;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class ClientGUI implements ActionListener {
	JFrame frame;
	JPanel panel;
	ImageIcon browseIcon;
	JButton browseButton;
	JFileChooser fileChoice;
	Color white;
	ArrayList<Block> blockchain;
	
	public ClientGUI() {
		frame = new JFrame("ChainDev v0.1");
		frame.setSize(800,640);
		white = new Color(255,255,255);
		fileChoice = new JFileChooser();
		browseIcon = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
		browseButton = new JButton(browseIcon);
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(white);

		panel.add(browseButton);
		
		frame.add(panel);
		
		//Set window to close properly
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set visible
		frame.setVisible(true);
		
		browseButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == browseButton) {
			int fileChoiceOption = fileChoice.showOpenDialog(frame);
			if(fileChoiceOption == JFileChooser.APPROVE_OPTION){
				File file = fileChoice.getSelectedFile();
				try(FileInputStream fileInput = new FileInputStream(file)){
					ObjectInputStream objectFileInput = new ObjectInputStream(fileInput);
					blockchain = new ArrayList<Block>();
					blockchain.addAll((ArrayList<Block>) objectFileInput.readObject());
										
					objectFileInput.close();
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}//end if
			else{
				
			}//end else
			for(int i = 0; i < blockchain.size(); i++) {
				System.out.println("Previous hash " + blockchain.get(i).getPreviousHash());
	        	System.out.println("Commands: " + blockchain.get(i).getHashCommands());
	        	System.out.println("Hash of block " + i + ":");
	        	System.out.println(blockchain.get(i).getBlockHash());
			}
		}
		
	}
}
