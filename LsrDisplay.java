package cs542.lsr.project.lsrGui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import cs542.lsr.project.lsrAlgo.*;
import cs542.lsr.project.rTable.*;

import java.io.*;

public class LsrDisplay extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private Container cp;
	
	int[][] nextHop;
	int[][] tempNextHop;
	
	private String filePth;
	
	public String src;
	public String ssc;
	
	public String dest;

// MJ	
 	public StringBuffer bufferForConnectionTable;


	private LsrRoutingAlgo lsrAlgo;
	    
	//private JFrame j1;
	//Text Area
	private JTextArea txtar;
	private JTextArea txtar1;
	//Menu Bar
	private JMenuBar jmbr;
	
	//Menu
	private JMenu jmFile;
	private JMenu jmEdit;
	
	//Menu Item
	private JMenuItem jitmNew;
	private JMenuItem jitmOpen;
	private JMenuItem jitmExit;
	
	//Labels
	
	private JLabel lblRouterInput;
	private JLabel lblWelcomeTxt1;
	private JLabel lblWelcomeTxt2;
	private JLabel lblWelcomeTxt3;
	private JLabel lblWelcomeTxt4;
	private JLabel lblWelcomeTxt5;
	private JLabel lblWelcomeTxt6;
	private JLabel lblOutText;
	private JLabel lblRouterOutput;
	private JLabel lblModify1;
	private JLabel lblModify2;
	private JLabel lblLinkWeight;
	
	
	//buttons
	private JButton btnContinue;
	private JButton btnNext;
	private JButton btnOutView;
	private JButton btnNext1;
	private JButton btnExit;
	private JButton btnReset;
	private JButton btnDisplayConnectionTable;
	private JButton btnModify;
	private JButton btnBack;
	private JButton btnDisplay;
	
	//textfields
	private JTextField inputRouterValue;
	private JTextField outputRouterValue;
	private JTextField inputRouterValue1;
	private JTextField inputModify1;
	private JTextField inputModify2;
	private JTextField inputLinkWeight;
	
	//panel
	private JPanel panel;
	private JPanel panellbl;
	private JPanel btnPanel;
	
	public LsrRoutingTable lrt;
	
public LsrDisplay()
	{
	}
	

public LsrDisplay(int choice)
	{
		switch(choice)
		{
			case 1:
					//initial window
					setTitle("Link State Route Simulator");
					setBounds(375, 250, 550, 310);
					setResizable(true);
					addWindowListener(new MyWindowAdapter());
					
					cp= getContentPane();
					cp.setBackground(Color.PINK);
					cp.setLayout(new BorderLayout());
					
					panel = new JPanel();
					panellbl = new JPanel(new GridLayout(8, 1, 5, 6));
					btnPanel = new JPanel();
					
					
					
					lblWelcomeTxt1 = new JLabel("Welcome To Link State Routing Simulator!!");
					lblWelcomeTxt2 = new JLabel("1) Creates a network topology");
					lblWelcomeTxt3 = new JLabel("2) Builds Connection Table for each router");
					lblWelcomeTxt4 = new JLabel("3) Finds Shortest Path to destination router");
					lblWelcomeTxt5 = new JLabel("4) Modifies the topology");
					lblWelcomeTxt6 = new JLabel("5) Exit");
					
					lblWelcomeTxt1.setFont(new Font("Georgia", Font.BOLD, 17));
					lblWelcomeTxt2.setFont(new Font("Georgia", Font.PLAIN, 15));
					lblWelcomeTxt3.setFont(new Font("Georgia", Font.PLAIN, 15));
					lblWelcomeTxt4.setFont(new Font("Georgia", Font.PLAIN, 15));
					lblWelcomeTxt5.setFont(new Font("Georgia", Font.PLAIN, 15));
					lblWelcomeTxt6.setFont(new Font("Georgia", Font.PLAIN, 15));
					
					btnContinue = new JButton("Continue");
					
					btnContinue.setFont(new Font("Georgia", Font.PLAIN, 15));
					
					btnPanel.add(btnContinue);
					btnPanel.setBackground(Color.PINK);
					
					panel.add(lblWelcomeTxt1);
					panel.setBackground(Color.PINK);
					
					panellbl.add(lblWelcomeTxt2);
					panellbl.setBackground(Color.PINK);
					
					panellbl.add(lblWelcomeTxt3);
					panellbl.setBackground(Color.PINK);
					
					panellbl.add(lblWelcomeTxt4);
					panellbl.setBackground(Color.PINK);
					
					panellbl.add(lblWelcomeTxt5);
					panellbl.setBackground(Color.PINK);
					
					panellbl.add(lblWelcomeTxt6);
					panellbl.setBackground(Color.PINK);
					
					cp.add(panel,BorderLayout.NORTH);
					cp.add(panellbl,BorderLayout.WEST);
					cp.add(btnPanel,BorderLayout.SOUTH);
					
					btnContinue.addActionListener(this);
					
	 				break;
					
				case 2:
					
					setTitle("Link State Route Simulator");
					setBounds(375,90,500,600);
					setResizable(false);
				
					cp = getContentPane();
					cp.setLayout(null);
					
					jmbr = new JMenuBar(); //Initialize object of JMenuBar.
					jmbr.add(Box.createRigidArea(new Dimension(0,30)));
					setJMenuBar(jmbr);
					
					
					jmFile = new JMenu("File");
					
					jitmNew = new JMenuItem("New");
					jitmOpen = new JMenuItem("Open");
					jitmExit = new JMenuItem("Exit");
					
					jmFile.add(jitmNew);
					jmFile.add(jitmOpen);
					jmFile.add(jitmExit);
					
					jmbr.add(jmFile);
				
                    txtar = new JTextArea(15,30); //A text area for showing content of files.
					txtar.setBorder(BorderFactory.createLineBorder(Color.black));
					txtar.setEditable(false);
					
					JScrollPane jsp = new JScrollPane();//To add scroll bars
					jsp.setViewportView(txtar);
					jsp.setBounds(0,0,490,300);
					cp.add(jsp);
					
					btnNext = new JButton("Next");
					btnNext.setBounds(400,310,90,40);
					cp.add(btnNext);
					
					btnOutView = new JButton("Path");
					btnOutView.setBounds(400,380,90,40);
					cp.add(btnOutView);
					
					btnReset = new JButton("Reset");
					btnReset.setBounds(400,450,90,40);
					//btnReset.setBackground(Color.GREEN);
					cp.add(btnReset);
					
					btnDisplayConnectionTable = new JButton("Default Connection Table");
					btnDisplayConnectionTable.setBounds(1,450,180,40);
					cp.add(btnDisplayConnectionTable);
					
					btnModify = new JButton("Modify");
					btnModify.setBounds(300,450,90,40);
					cp.add(btnModify);
					
					btnBack = new JButton("Previous");
					btnBack.setBounds(140,450,90,40);
					cp.add(btnBack);
					
					btnDisplay = new JButton("Display");
					btnDisplay.setBounds(300,450,90,40);
					cp.add(btnDisplay);
					
					lblRouterInput = new JLabel("Source Router Value:R");
					lblRouterInput.setBounds(1, 285, 200, 100);
					cp.add(lblRouterInput);
					
					lblRouterOutput = new JLabel("Destination Router Value:R");
					lblRouterOutput.setBounds(1, 350, 200, 100);
					cp.add(lblRouterOutput);
					
					lblModify1 = new JLabel("Modify Link From:R");
					lblModify1.setBounds(150, 285, 200, 100);
					cp.add(lblModify1);
					
					lblModify2 = new JLabel("Modify Link To:R");
					lblModify2.setBounds(150, 330, 200, 100);
					cp.add(lblModify2);
					
					lblLinkWeight = new JLabel("Modify Link Weight:");
					lblLinkWeight.setBounds(150, 370, 200, 100);
					cp.add(lblLinkWeight);
					
					inputRouterValue = new JTextField();
					inputRouterValue.setBounds(130, 325, 20, 20);
					cp.add(inputRouterValue);
					
					inputModify1 = new JTextField();
					inputModify1.setBounds(260, 325, 20, 20);
					cp.add(inputModify1);
					
					inputModify2 = new JTextField();
					inputModify2.setBounds(245, 370, 20, 20);
					cp.add(inputModify2);
					
					inputLinkWeight = new JTextField();
					inputLinkWeight.setBounds(260, 412, 20, 20);
					cp.add(inputLinkWeight);
					
					outputRouterValue = new JTextField();
					outputRouterValue.setBounds(153, 392, 20, 20);
					cp.add(outputRouterValue);
					
					jitmNew.addActionListener(this);
					
					jitmOpen.addActionListener(this);
					
					btnOutView.setVisible(false);
					btnOutView.setEnabled(false);
					lblRouterOutput.setVisible(false);	
					lblRouterOutput.setEnabled(false);
					outputRouterValue.setVisible(false);
					outputRouterValue.setEnabled(false); 
					
					
					inputModify1.setVisible(false);
					inputModify2.setVisible(false);
					inputLinkWeight.setVisible(false);
					
					lblModify1.setVisible(false);
					lblModify2.setVisible(false);
					lblLinkWeight.setVisible(false);
					
					btnBack.setEnabled(false);
					btnBack.setVisible(false);
					
					btnDisplay.setVisible(false);
					
					
					btnNext.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent arg) 
						{
							setTitle("Link State Route Simulator");
							src = inputRouterValue.getText();
							
							if(txtar.getText().equals("")) 
							{
								JOptionPane.showMessageDialog(null,
										"Please Enter Topology",
										"Error",
										JOptionPane.ERROR_MESSAGE);
								inputRouterValue.setText("");
							}
							else if(inputRouterValue.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null,
										"Please Enter Source Router",
										"Error",
										JOptionPane.ERROR_MESSAGE);
							}
						
							try
							{
								
								if(Integer.parseInt(src)>0 && Integer.parseInt(src)<= lsrAlgo.routerCount)
								{
									lrt = new LsrRoutingTable(lsrAlgo.routerCount,
											Integer.parseInt(src), lsrAlgo.costLSR,1);
									lrt.createRoutingTable();
									
									txtar.setText("");
									txtar.setText(lrt.pathToString());
									nextHop = lrt.nextHop;
									lblRouterInput.setVisible(false);
									inputRouterValue.setVisible(false);
									btnNext.setEnabled(false);
									btnNext.setVisible(false);
									
									btnOutView.setVisible(true);
									btnOutView.setEnabled(true);
									lblRouterOutput.setVisible(true);	
									lblRouterOutput.setEnabled(true);
									outputRouterValue.setVisible(true);
									outputRouterValue.setEnabled(true); 
									btnDisplayConnectionTable.setVisible(false);
									btnDisplayConnectionTable.setEnabled(false);
									btnModify.setVisible(false);
									btnModify.setEnabled(false);
							}

								else
								{
									JOptionPane.showMessageDialog(null,
											"Please Enter Valid Source Router",
											"Validation Error",
											JOptionPane.ERROR_MESSAGE);
									inputRouterValue.setText("");
								}
							}catch(Exception e)
							{
								
							}
							
						}
						});
					
					btnOutView.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent arg) 
						{
							int i;
							
							setTitle("Link State Route Simulator");
							dest = outputRouterValue.getText();
							
							if(Integer.parseInt(dest) > lrt.routerCount)
							{
								JOptionPane.showMessageDialog(null,
										"Destination Router is out of bound",
										"Error",
										JOptionPane.ERROR_MESSAGE);
								
							}
							
							if(txtar.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null,
										"Please Enter Topology",
										"Error",
										JOptionPane.ERROR_MESSAGE);
							}
							
							else if(outputRouterValue.getText().equals("") && inputRouterValue.getText().equals("") )
							{
								JOptionPane.showMessageDialog(null,
										"Please Enter Source Router & Destination Router",
										"Error",
										JOptionPane.ERROR_MESSAGE);
							}
							
							else if(outputRouterValue.getText().equals("")) {
								
								JOptionPane.showMessageDialog(null,
										"Please Enter Destination Router",
										"Error",
										JOptionPane.ERROR_MESSAGE);
							}
							try
							{
							int countForNodesVisitedFirst = 0;
							if (Integer.parseInt(dest) > 0) {
								if (Integer.parseInt(dest) <= lrt.routerCount) {
									if(lrt.distance[Integer.parseInt(dest)-1]==99999){
										txtar.setText("Destination host unreachable");
							}else{
								int minimumCost = 0;
								
									txtar.setText("");
									txtar.setText("The Least Cost Path from "+ "R"+ src+ " to "+ "R"+ dest+ " is:\n"+ lsrAlgo.getMinimumPath(Integer.parseInt(src),
													Integer.parseInt(dest), nextHop)+ "\n\nMinimum Cost to reach Destination: "+ lrt.distance[Integer.parseInt(dest)-1]);		

									int tempCost = lrt.distance[Integer.parseInt(dest)-1];
									for( i= 0; i<lrt.routerCount; i++)
									{
										if(lsrAlgo.checkAgainNodes[i] != -1)
										{
											countForNodesVisitedFirst++;
										}
									}
									
//									System.out.println("count = "+countForNodesVisitedFirst);
									
//									for( i = 0; i<countForNodesVisitedFirst; i++)
	//								{
		//								txtar.append("\n "+lsrAlgo.checkAgainNodes[i]);
			//						}
									
//									System.out.println("count = "+lsrAlgo.costLSR[3][6]+" 1 = "+lrt.costLSR[3][6]);

									i =0;
									int whileLoopCount = 0;
									int[][] localCostLSR;
									localCostLSR = new int[lrt.routerCount][lrt.routerCount];
									tempNextHop = lrt.nextHop;
									
									int j = 0;
									if(countForNodesVisitedFirst == 2)
									{	
											int index1 = lsrAlgo.checkAgainNodes[j];
											int index2 = lsrAlgo.checkAgainNodes[j+1];
					/*						for(i = 0; i< countForNodesVisitedFirst; i++)
											{
												lsrAlgo.checkAgainNodes[i] = lsrAlgo.checkAgainNodes[i+1];
												System.out.println("Checked inside q ->"+countForNodesVisitedFirst);
											}
											lsrAlgo.checkAgainNodes[i] = -1;
											
											System.out.println(countForNodesVisitedFirst+" is the count now...");
											System.out.println(index1+", "+index2);
//											System.out.println(lsrAlgo.checkAgainNodes[index1-1] +" hey mayuresh...check me "+ lsrAlgo.checkAgainNodes[index2-1]+" -MJ");
		//									lrt.costLSR[lsrAlgo.checkAgainNodes[i]][lsrAlgo.checkAgainNodes[i+1]] = -1;
//Trial											lsrAlgo.costLSR = lsrAlgo.backupCostLSR;
											if(whileLoopCount > 0)
											{
												lsrAlgo.costLSR = localCostLSR;
												lrt.costLSR = lsrAlgo.costLSR;
												lsrAlgo.costLSR[index1-1][index2-1] = -1;
												lrt.costLSR[index1-1][index2-1] = -1;
												localCostLSR = lrt.costLSR;
						*/						//localCostLSR = lsrAlgo.costLSR;											
							//					System.out.println("Entered while loop > 0");
												
								//			}else
									//		{
												localCostLSR = lrt.costLSR;
												lsrAlgo.backupCostLSR[index1-1][index2-1] = -1;
												lrt.costLSR[index1-1][index2-1] = -1;
												localCostLSR = lrt.costLSR;
												//localCostLSR = lsrAlgo.costLSR;											
									//			System.out.println("Entered while loop = 0");
										//	}
		//									System.out.println("count = "+lsrAlgo.costLSR[3][6]+" 1 = "+lrt.costLSR[3][6]);
					
		//									System.out.println("\n I AM MJ \n");
	//										lsrAlgo.row = 0;
//											txtar.append("\n"+lsrAlgo.costLSRToString());
											lrt = new LsrRoutingTable(lsrAlgo.routerCount,
													Integer.parseInt(src), lsrAlgo.backupCostLSR,1);
											lrt.createRoutingTable();
											
//											txtar.append("\n");
//											txtar.append(lrt.pathToString());
											nextHop = lrt.nextHop;
											int flag = 0;
										/*
											for(int k=0; k<lrt.routerCount; k++){
												if(nextHop[Integer.parseInt(dest)][k] != tempNextHop[Integer.parseInt(dest)][k])
												{	flag = 1;
													break;
												}else{
													flag = 2;
												}
												System.out.println("\tNew : "+nextHop[Integer.parseInt(dest)][k]);
												System.out.println("\tOld : "+tempNextHop[Integer.parseInt(dest)][k]);
											}
										*/
											
										//	lsrAlgo.getMinimumPath(Integer.parseInt(src),Integer.parseInt(dest), nextHop);											
											
											System.out.println("I am the new cost : \n"+tempCost+"\n Im earlier : \n"+lrt.distance[Integer.parseInt(dest)-1]+" !!!");
											if(tempCost == lrt.distance[Integer.parseInt(dest)-1])
											{		
												txtar.append("\n");
												txtar.append("\nAnother Path is available (With Same Cost):\n"+ lsrAlgo.getMinimumPath(Integer.parseInt(src),
															Integer.parseInt(dest), nextHop));		
											}
												/*
											for( i= 0; i<lrt.routerCount; i++)
											{
												if(lsrAlgo.checkAgainNodes[i] != -1)
												{
													countForNodesVisitedFirst++;
												}
											}
										*/
//											i = 0;
										//	j++;
											//countForNodesVisitedFirst--;
										//	whileLoopCount++;
		//									txtar.setText("");
			//								txtar.setText("The Least Cost Path from "+ "R"+ src+ " to "+ "R"+ dest+ " is:\n"+ lsrAlgo.getMinimumPath(Integer.parseInt(src),
				//											Integer.parseInt(dest), nextHop)+ "\n\n Minimum Cost to reach Destination: "+ lrt.distance[Integer.parseInt(dest)-1]);		
									}
									//lsrAlgo.costLSR = lsrAlgo.backupCostLSR;
							}
							}
								}
								}
						catch(Exception e)
						{
							
						}
							lblRouterInput.setVisible(false);
							inputRouterValue.setVisible(false);
							outputRouterValue.setText("");
							btnNext.setEnabled(false);
							btnNext.setVisible(false);
							
						
							}});
					
					btnReset.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent arg) 
						{
							//btnReset.setToolTipText("See Input Matrix Again");
							setTitle("Link State Route Simulator");
							if(txtar.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null,
										"Please Enter Topology",
										"Error",
										JOptionPane.ERROR_MESSAGE);
							}else{
								setVisible(false);
								btnOutView.setVisible(false);
								btnOutView.setEnabled(false);
								lblRouterOutput.setVisible(false);	
								lblRouterOutput.setEnabled(false);
								outputRouterValue.setVisible(false);
								outputRouterValue.setEnabled(false); 
								
								
								inputModify1.setVisible(false);
								inputModify2.setVisible(false);
								inputLinkWeight.setVisible(false);
								
								lblModify1.setVisible(false);
								lblModify2.setVisible(false);
								lblLinkWeight.setVisible(false);
								
								btnBack.setEnabled(false);
								btnBack.setVisible(false);
								
								btnDisplay.setVisible(false);
								
								
								new LsrDisplay(1);
							}
						}});
							
					btnDisplayConnectionTable.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent arg) 
						{
							setTitle("Link State Route Simulator");
							if(txtar.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null,
										"Please Enter Topology",
										"Error",
										JOptionPane.ERROR_MESSAGE);
							}
							bufferForConnectionTable = new StringBuffer();
				
							try
							{
								for(int i = 1; i<=lsrAlgo.routerCount;i++)
								{
									src = String.valueOf(i);
									lrt = new LsrRoutingTable(lsrAlgo.routerCount,i, lsrAlgo.tempCostLSR, 1);
									lrt.createRoutingTable();
									txtar.setText("");
									bufferForConnectionTable.append(lrt.pathToString()); 
									nextHop = lrt.nextHop;
								}
								
								txtar.setText(bufferForConnectionTable.toString());
						
							}
							catch(Exception e)
							{
								
							}
							lblRouterInput.setVisible(true);
							inputRouterValue.setVisible(true);
							btnNext.setEnabled(true);
							btnNext.setVisible(true);
						}
						});		
					
					btnModify.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent arg) 
						{
							setTitle("Link State Route Simulator");
							if(txtar.getText().equals(""))
							{
								JOptionPane.showMessageDialog(null,
										"Please Enter Topology",
										"Error",
										JOptionPane.ERROR_MESSAGE);
							}
							else
							{
							lsrAlgo.row = 0;
							btnDisplay.setVisible(true);
							btnDisplay.setEnabled(true);
							btnModify.setVisible(false);
							btnModify.setEnabled(false);
							//btnReset.setToolTipText("See Input Matrix Again");
							setTitle("Link State Route Simulator");
							//txtar.setText(ssc);
							lblRouterInput.setVisible(false);
							inputRouterValue.setVisible(false);
							btnNext.setEnabled(false);
							btnNext.setVisible(false);
							outputRouterValue.setVisible(false);
							lblRouterOutput.setVisible(false);
							
							btnDisplayConnectionTable.setVisible(false);
							btnDisplayConnectionTable.setEnabled(false);
							
							btnOutView.setVisible(false);
							btnOutView.setEnabled(false);
							
							btnReset.setVisible(false);
							btnReset.setEnabled(false);
							
							
							inputModify1.setVisible(true);
							inputModify2.setVisible(true);
							inputLinkWeight.setVisible(true);
							
							lblModify1.setVisible(true);
							lblModify2.setVisible(true);
							lblLinkWeight.setVisible(true);
							
							btnBack.setEnabled(true);
							btnBack.setVisible(true);
							txtar.setText("");
							txtar.setText(lsrAlgo.costLSRToString());
							ssc = txtar.getText();
							
							}

							//coding here
							
							}});
					
					btnBack.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent arg) 
						{
							btnDisplay.setVisible(false);
							btnDisplay.setEnabled(false);
							btnModify.setVisible(true);
							btnModify.setEnabled(true);
							btnDisplayConnectionTable.setVisible(false);
							btnDisplayConnectionTable.setEnabled(false);
							//btnReset.setToolTipText("See Input Matrix Again");
							setTitle("Link State Route Simulator");
							//txtar.setText(ssc);
							lblRouterInput.setVisible(true);
							inputRouterValue.setVisible(true);
							btnNext.setEnabled(true);
							btnNext.setVisible(true);
							outputRouterValue.setVisible(true);
							lblRouterOutput.setVisible(true);
							
							btnOutView.setVisible(true);
							btnOutView.setEnabled(true);
							
							btnReset.setVisible(true);
							btnReset.setEnabled(true);
							
							
							inputModify1.setVisible(false);
							inputModify2.setVisible(false);
							inputLinkWeight.setVisible(false);
							
							lblModify1.setVisible(false);
							lblModify2.setVisible(false);
							lblLinkWeight.setVisible(false);
							
							outputRouterValue.setVisible(false);
							lblRouterOutput.setVisible(false);
							
							btnOutView.setVisible(false);
							btnOutView.setEnabled(false);
							
							
							btnBack.setEnabled(false);
							btnBack.setVisible(false);
							
													
							
							//coding here
							
							}});
					
					btnDisplay.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent arg) 
						{
							
							int router1, router2, weight;
							router1 = Integer.parseInt(inputModify1.getText());
							router2 = Integer.parseInt(inputModify2.getText());
							weight = Integer.parseInt(inputLinkWeight.getText());
							try
							{
								lsrAlgo.row = 0;
								lsrAlgo.costLSR[router1-1][router2-1] = weight;
								txtar.setText("");
								txtar.setText(lsrAlgo.costLSRToString());
								ssc = txtar.getText();
							}
							catch(Exception e)
							{
								
							}
						}
						});		
					

					jitmExit.addActionListener(this);
					
					break;
		}
		setVisible(true);
		
	}
	class MyWindowAdapter extends WindowAdapter 
		{
			public void windowClosing(WindowEvent we) 
			{
				JOptionPane.showConfirmDialog(null,"Would you like to exit?","Exit", JOptionPane.YES_NO_OPTION);
				System.exit(0);
			}
		}
		
		public void actionPerformed(ActionEvent ae)
		{
			if(ae.getSource()==btnContinue)
			{
				new LsrDisplay(2);
				setVisible(false);
			}
			if(ae.getSource()==jitmNew)
			{
				this.setTitle("New File");
				
			}
			if(ae.getSource()==jitmOpen)
			{
				//To Choose and Read File From The System.
				
				JFileChooser fChoose = new JFileChooser();
				fChoose.setCurrentDirectory(new java.io.File("."));
				fChoose.setDialogTitle("Input File:");
				fChoose.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fChoose.setAcceptAllFileFilterUsed(false);

				if (fChoose.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fChoose.getSelectedFile();
					filePth = file.getAbsolutePath();

				} else {
					JOptionPane.showMessageDialog(null,
							"Please Select File");
				}
					
					if (filePth == null) {
						JOptionPane.showMessageDialog(null,
								"Input the Topology file");
					} else {

						try {
				
					lsrAlgo = new LsrRoutingAlgo(filePth);
					txtar.setText(lsrAlgo.costLSRToString());
					txtar.setEditable(false);
					ssc = txtar.getText();
						}
						
						catch(Exception e)
						{
							JOptionPane.showMessageDialog(null,
									"Error in Loading File");
						}
					}
			}
			
			if(ae.getSource()==jitmExit)
			{
				System.exit(0);
			}
			
		}
		
public static void main(String args[])
	{
		LsrProgressBar b = new LsrProgressBar();
		try
		{
			Thread.sleep(1500);
			LsrDisplay d = new LsrDisplay(1);
		}
		catch(Exception e)
		{}
		
	}
}
