package blockchain1.mulvey.eoin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import blockchain.mulvey.eoin.Block;

public class DevGUI implements ActionListener{

	JFrame frame;
	JPanel basePanel, updatePanel1, updatePanel2;
	int whichPanel, commandNumber, chainSize;
	JLabel label;
	JTextField commandNumberTextField, chainSizeTextField;
	ArrayList<JTextField> command;
	ArrayList<JLabel> commandLabel;
	ImageIcon browseIcon;
	JButton browseButton, createButton, cancelButton;
	JFileChooser fileChoice;
	Color white;
	
	public DevGUI() {
		frame = new JFrame("ChainDev v0.1");
		frame.setSize(300,150);
		white = new Color(255,255,255);
		fileChoice = new JFileChooser();
		whichPanel = 0;
		commandNumber = 0;
		chainSize = 0;
		command = new ArrayList<JTextField>();
		commandNumberTextField = new JTextField(2);
		chainSizeTextField = new JTextField(10);
		/*
		browseIcon = (ImageIcon) UIManager.getIcon("FileView.directoryIcon");
		browseButton = new JButton(browseIcon);
		*/
		createButton = new JButton("Create Blockchain");
		cancelButton = new JButton("Cancel");
		
		basePanel = configurePanel(basePanel);
		
		frame.add(basePanel);
		
		//Set window to close properly
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set visible
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		/*
		if (source == cancelButton) {
			if (whichPanel == 1){
				frame.remove(updatePanel1);
			}
			if (whichPanel == 2) {
				frame.remove(updatePanel2);
			}
			whichPanel = 0;
			commandNumber = 0;
			chainSize = 0;
			basePanel = configurePanel(basePanel);
			frame.add(basePanel);
			frame.validate();
		}
		*/
		if (source == createButton) {
			if(chainSize > 0) {
				ArrayList<Block> blockchain = new InitialiseBlockchain(new GenesisBlock().getGenesisBlock()).getBlockchain();
		        
		        for (int i = 0; i < chainSize; i++) {
		        	blockchain.add(new NextBlock(blockchain).getNextBlock());
		        }
				int fileChoiceOption = fileChoice.showSaveDialog(frame);
				if(fileChoiceOption == JFileChooser.APPROVE_OPTION){
					File file = fileChoice.getSelectedFile();
					try(FileOutputStream fileOutput = new FileOutputStream(file)){
						ObjectOutputStream objectFileOutput = new ObjectOutputStream(fileOutput);
						
						objectFileOutput.writeObject(blockchain);
						objectFileOutput.close();
						fileOutput.close();
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				}//end if
				else {
					
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Chain size must be greater than 0");
			}
		}
		
	}
	
	public void updatePanel() {
		JPanel updatedPanel = new JPanel();
		if(commandNumber == 0) {
			updatedPanel = configurePanel(updatedPanel);
		}
		else {
			updatedPanel = configurePanel(updatedPanel);
			for(int i = 0; i< commandNumber; i++) {
				if(command.size() <= i) {
					command.add(new JTextField(25));
				}
				updatedPanel.add(new JLabel("command"));
				updatedPanel.add(command.get(i));
				
			}
		}
		
		switch(whichPanel) {
		case(0):
			updatePanel1 = updatedPanel;
			frame.remove(basePanel);
			frame.add(updatePanel1);
			frame.validate();
			whichPanel = 1;
			break;
		case(1):
			updatePanel2 = updatedPanel;
			frame.remove(updatePanel1);
			frame.add(updatePanel2);
			frame.validate();
			whichPanel = 2;
			break;
		case(2):
			updatePanel1 = updatedPanel;
			frame.remove(updatePanel2);
			frame.add(updatePanel1);
			frame.validate();
			whichPanel = 1;
			break;
		default:
			break;
		}
		
	}
	
	public JPanel configurePanel(JPanel panel) {
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(white);
		//panel.add(new JLabel("Number of Commands"));
		//panel.add(commandNumberTextField);
		panel.add(new JLabel("Size of Chain"));
		panel.add(chainSizeTextField);
		//panel.add(browseButton);
		//panel.add(cancelButton);
		panel.add(createButton);
		
		commandNumberTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                try{
                	commandNumber = Integer.parseInt(commandNumberTextField.getText());
                	updatePanel();
                }
                catch(Exception e) {
                	JOptionPane.showMessageDialog(null, "Must enter a number.");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
            	try{
                	commandNumber = Integer.parseInt(commandNumberTextField.getText());
                	updatePanel();
                }
                catch(Exception e) {
                	
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
            	try{
                	commandNumber = Integer.parseInt(commandNumberTextField.getText());
                	updatePanel();
                }
                catch(Exception e) {
                	JOptionPane.showMessageDialog(null, "Must enter a number.");
                }
            }
        });
		
		chainSizeTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                try{
                	chainSize = Integer.parseInt(chainSizeTextField.getText());
                }
                catch(Exception e) {
                	JOptionPane.showMessageDialog(null, "Must enter a number.");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
            	try{
            		chainSize = Integer.parseInt(chainSizeTextField.getText());
            		}
                catch(Exception e) {
                	
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
            	try{
            		chainSize = Integer.parseInt(chainSizeTextField.getText());
                }
                catch(Exception e) {
                	JOptionPane.showMessageDialog(null, "Must enter a number.");
                }
            }
        });
		
		//cancelButton.addActionListener(this);
		createButton.addActionListener(this);
		
		return panel;
	}
}
	
		

