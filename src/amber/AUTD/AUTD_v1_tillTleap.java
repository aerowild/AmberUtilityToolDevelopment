package amber.AUTD;
/*
 * >Get input file information
 * >Get parameters for tleap
 * >run tleap to generate *.lib of ligands
 * >run Tleap to generate topology and param files
 * 
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSplitPane;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.DropMode;
import javax.swing.AbstractListModel;
import javax.swing.JSlider;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollBar;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.CompoundBorder;


public class AUTD_v1_tillTleap extends JFrame {
	
	JTabbedPane mainTab;
	private JPanel contentPane;
	private JTextField txtSelectNumberOf;
	private JTextField txtBoxType;
	private JTextField txtWaterModel;
	private JTextField txtForceField;
	private JTextField txtSelectSecondForce;
	private JTextField txtSelectThirdForce;
	private JTextField txtCloseness;
	private JTextField txtPosition;
	private JTextField textField_3;
	private JTextField txtSetWorkingDirectory;
	private JTextField txtSetOutputFiles;
	private JTextField txtComplexsolv;
	private JTextField txtDistance;
	private JTextField textFieldSetIons;
	
	//Declarations of my variables, these will be accessed to all
	private int ligNumber;
	private String choosenDirectory = "Not yet selected"; //holds the working directory path
	private String choosenPDB = "Not yet selected"; //holds the PDB file path
	private ArrayList<String> saveLigPath = new ArrayList(); //holds the location of ligands
	private ArrayList<String> saveLigName = new ArrayList(); //holds the name of ligands
	private String getOutputFileName = "complexsolve"; //It holds the name of output files
	private String displayUpdates = "File Parameters are not yet set."; //This variable contains the information of "Information Area"
	private String setAMBERHOME = "Must select the folder, where Amber is installed";
	
	//Declaration of variables for Parameter tab
	private String boxType = "solvateoct";
	private String waterModel = "TIP3PBOX";
	private int distanceBox = 10;
	private double closenessBox = 1;
	private String positionSolvateCap = "Not Applicable for default Box Type";
	private String ff1 = "leaprc.gaff";
	private String ff2 = "leaprc.ff99SB";
	private String ff3 = "leaprc.gaff";
	private String ff4 = "leaprc.gaff";
	private String displayUpdates2;
	private String ionType = "Automatic";
	private int ionNumber;
	private String solventModelMode = "Default";
	private String neutralizationMethod = "Automatic";
	private JRadioButton rdbtnNeutralize;
	private JRadioButton rdbtnSolventModels;
	private int countNeutralize = 0;
	private int countSolvate = 0;
	private JSlider slider_1;
	private JSlider slider;
	private JEditorPane infoArea3;
	boolean isLigandPresent;
	boolean isBoxTypeCap = false;
	boolean isNeutralizeManual = false;
	
	String extraTleapCommands = "";//Store extra commands to tleap
	
	//Declaration of variables for tab3
	private String displayUpdates3;
	private JTextField textField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AUTD_v1_tillTleap frame = new AUTD_v1_tillTleap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public AUTD_v1_tillTleap() {
		saveLigPath.add("Not Yet Selected");
		
		setResizable(false);		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Shashank\\Pictures\\20140923_141154.jpg"));
		setBackground(new Color(0, 0, 0));
		setFont(new Font("Arial Black", Font.BOLD, 12));
		setTitle("Amber Utility Tool (AUT)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 627);
//Panel 1 of tab		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new MatteBorder(1, 1, 3, 3, (Color) UIManager.getColor("Button.darkShadow")));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		//Create Panel1 of tab1
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 28, 670, 255);
		//contentPane.add(panel);
		panel.setLayout(null);
		
//Creating Main tab: This is FIles tab
		mainTab = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(mainTab, BorderLayout.CENTER); //Add tab1 to the frame
		mainTab.addTab("Files",null,panel,"Hi"); //Adding panel1 in tab1
		mainTab.setBounds(0, 21, 675, -21);	

//Information Bar1: This is an information panel/update panel on left of the window
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBounds(10, 317, 737, 242);
		panel.add(toolBar_1);		
		final JTextArea textAreatmp = new JTextArea();
		textAreatmp.setLineWrap(true);
		textAreatmp.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		toolBar_1.add(textAreatmp);
		textAreatmp.setWrapStyleWord(true);
		textAreatmp.setDropMode(DropMode.INSERT);
		textAreatmp.setEditable(false);
		textAreatmp.setBackground(new Color(255, 250, 230));
		textAreatmp.setText("Welcome to Amber Utility Tool\nPlease Select input files.");	
				
				JPanel panel_2 = new JPanel();
				panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_2.setBounds(10, 11, 737, 295);
				panel.add(panel_2);
						panel_2.setLayout(null);
		

		
		
		
//Update information Bar Panel: To get the current status on information bar
				JButton btnUpdateInformationBar = new JButton("Update Information Area");
				btnUpdateInformationBar.setBounds(485, 261, 242, 23);
				panel_2.add(btnUpdateInformationBar);
				
				JPanel panel_4 = new JPanel();
				panel_4.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_4.setBounds(10, 219, 240, 65);
				panel_2.add(panel_4);
		panel_4.setLayout(null);
		txtSetOutputFiles = new JTextField();
		txtSetOutputFiles.setBounds(27, 6, 150, 20);
		panel_4.add(txtSetOutputFiles);
		txtSetOutputFiles.setFont(new Font("Arial", Font.BOLD, 11));
		txtSetOutputFiles.setBackground(SystemColor.controlHighlight);
		txtSetOutputFiles.setEditable(false);
		txtSetOutputFiles.setText("Set Output Files' Name");
		txtSetOutputFiles.setColumns(10);
		btnUpdateInformationBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayUpdates = "AMBERHOME:\n"+setAMBERHOME+"\nWorking Directory:\n  "+choosenDirectory+"\nSelected PDB File:\n  "+choosenPDB+"\nSelected Ligands:\n ";
				int count = 0;
				for(String index:saveLigPath){
					displayUpdates = displayUpdates+"  "+saveLigPath.get(count)+"\n";
					count++;
				}
				displayUpdates = displayUpdates+"Name of output files:\n  "+getOutputFileName;
				JToolBar toolBar = new JToolBar();
				toolBar.setBounds(10, 61, 455, 209);
				contentPane.add(toolBar);				
				textAreatmp.setBackground(new Color(255, 250, 250));
				textAreatmp.setText(displayUpdates);
			}
		});
				
//Set Output File Name Panel:
		JSplitPane splitPane_13 = new JSplitPane();
		splitPane_13.setBounds(27, 31, 184, 25);
		panel_4.add(splitPane_13);
		txtComplexsolv = new JTextField();
		splitPane_13.setRightComponent(txtComplexsolv);
		txtComplexsolv.setText("complexsolve");
		txtComplexsolv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {						
				getOutputFileName = txtComplexsolv.getText();
				displayUpdates = "AMBERHOME:\n"+setAMBERHOME+"\nWorking Directory:\n  "+choosenDirectory+"\nSelected PDB File:\n  "+choosenPDB+"\nSelected Ligands:\n ";
				int count = 0;
				for(String index:saveLigPath){
					displayUpdates = displayUpdates+"  "+saveLigPath.get(count)+"\n";
					count++;
				}
				displayUpdates = displayUpdates+"Name of output files:\n  "+getOutputFileName;
				textAreatmp.setBackground(new Color(255, 250, 250));
				textAreatmp.setText(displayUpdates);
			}
		});
		txtComplexsolv.setFont(new Font("Trebuchet MS", Font.ITALIC, 11));
		txtComplexsolv.setColumns(10);				
		JButton btnSetName = new JButton("Set Name"); //Updates the name of output files
		btnSetName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getOutputFileName = txtComplexsolv.getText();
				displayUpdates = "AMBERHOME:\n"+setAMBERHOME+"\nWorking Directory:\n  "+choosenDirectory+"\nSelected PDB File:\n  "+choosenPDB+"\nSelected Ligands:\n ";
				int count = 0;
				for(String index:saveLigPath){
					displayUpdates = displayUpdates+"  "+saveLigPath.get(count)+"\n";
					count++;
				}
				displayUpdates = displayUpdates+"Name of output files:\n  "+getOutputFileName;
				textAreatmp.setBackground(new Color(255, 250, 250));
				textAreatmp.setText(displayUpdates);
				
			}
		});
		splitPane_13.setLeftComponent(btnSetName);				
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_5.setBounds(10, 11, 717, 197);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
//Ligand selection Panel: Select the number of Ligands and files 		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setBounds(10, 162, 240, 24);
		panel_5.add(splitPane_1);
		//To select the number of ligands to be processed from dropbox list
		final JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		splitPane_1.setLeftComponent(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4"}));
		comboBox.setMaximumRowCount(12);		
		//TextBox: Text box of selection of number of ligands
		txtSelectNumberOf = new JTextField();
		txtSelectNumberOf.setFont(new Font("Trebuchet MS", Font.ITALIC, 11));
		splitPane_1.setRightComponent(txtSelectNumberOf);
		txtSelectNumberOf.setBackground(new Color(255, 140, 0));
		txtSelectNumberOf.setEditable(false);
		txtSelectNumberOf.setText("Select Number of Ligands");
		txtSelectNumberOf.setColumns(10);
		//This is what will happen when any number is selected from the list
		//I will have number of ligands and their location on disk
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args){
			ligNumber =  Integer.parseInt((String)comboBox.getSelectedItem());//This will get items from dropDown list and covert it into String and again into int
			String updateText = "null"; //Initializing updateText variable which will have different values depending on choice of ligands
			mainTab.setEnabledAt(1, true);
			if(ligNumber == 0){
				isLigandPresent = false;
			    updateText = "You mean there is no ligand in PDB?";
			    txtSelectNumberOf.setText(updateText);
			    txtSelectNumberOf.setBackground(new Color(255, 250, 250));
			    //This will update the Information bar
			    displayUpdates = "AMBERHOME:\n"+setAMBERHOME+"\nWorking Directory:\n  "+choosenDirectory+"\nSelected PDB File:\n  "+choosenPDB+"\nIt seems that you are running only Protein/DNA simulation, since you did not supply any ligand.";
				textAreatmp.setBackground(new Color(255, 250, 250));
				textAreatmp.setText(displayUpdates);
				saveLigPath = new ArrayList();
			    
			}else{
				isLigandPresent = true;
				updateText = "Select .mol2 files of "+ligNumber+" ligand/s.";
				txtSelectNumberOf.setText(updateText);
				txtSelectNumberOf.setBackground(new Color(255, 250, 250));
				//ArrayList<String> saveLigPath = new ArrayList();
				saveLigName = new ArrayList(); //empty previous list of ligand names
				saveLigPath = new ArrayList(); //Empty the previous list by re-initializing it
				for(int count=1;count <= ligNumber;count++){								
					
					String chooseWhichLig = "Please choose mol2 file of ligand No. "+count;
					JOptionPane showMessage = new JOptionPane();
					showMessage.showMessageDialog(null, chooseWhichLig);
					JFileChooser mol2Chooser = new JFileChooser(); //Ligand File chooser to select mol2 file
					FileNameExtensionFilter mol2filter = new FileNameExtensionFilter("Mol2 File","mol2"); //Mol2 Filter: To select only mol2 files				
					mol2Chooser.setFileFilter(mol2filter);
					int returnVal = mol2Chooser.showOpenDialog(null);
					if(returnVal == mol2Chooser.APPROVE_OPTION){//If user select a file
						File file = mol2Chooser.getSelectedFile();
						saveLigPath.add(file.getPath()); //file.getPath will hold the path of selected ligand
						saveLigName.add(file.getName());
						textAreatmp.setBackground(new Color(255, 250, 250));
						textAreatmp.setText("Welcome to Amber Utility Tool\nPlease Select input files.");
						/*
						 * //Update the information Bar:Following will update the Information bar
						 */
						displayUpdates = "AMBERHOME:\n"+setAMBERHOME+"\nWorking Directory:\n  "+choosenDirectory+"\nSelected PDB File:\n  "+choosenPDB+"\nSelected Ligands:\n ";
						count = 0;
						for(String index:saveLigPath){
							displayUpdates = displayUpdates+saveLigName.get(count)+"\n  ";
							count++;
						}
						JToolBar toolBar = new JToolBar();
						toolBar.setBounds(10, 61, 455, 209);
						contentPane.add(toolBar);				
						textAreatmp.setBackground(new Color(255, 235, 205));
						textAreatmp.setText(displayUpdates);
						
					}else{ //if user cancels the selection of the file
						txtSelectNumberOf.setText("User did not select all the ligands.");
						txtSelectNumberOf.setBackground(Color.RED);
						textAreatmp.setText("Error in file selection, Please check");
						textAreatmp.setBackground(Color.RED);
						JOptionPane showFile = new JOptionPane();
						showFile.showMessageDialog(null,"User canceled the ligand selection");
						break;
					}		
								
					
			}
				
			}
			
			
			
			}
			
		});

//Select PDB panel: A split panel, which will show option to select PDB file (on left) and text (on right)
				JSplitPane splitPane = new JSplitPane();
				splitPane.setBounds(10, 112, 697, 39);
				panel_5.add(splitPane);
				splitPane.setBackground(UIManager.getColor("info"));		
				//Text area which will be mounted on JPane on the right
				final JTextArea txtrSelectPdbFile = new JTextArea();
				txtrSelectPdbFile.setLineWrap(true);
				splitPane.setRightComponent(txtrSelectPdbFile);
				txtrSelectPdbFile.setFont(new Font("Trebuchet MS", Font.ITALIC, 14));
				txtrSelectPdbFile.setEditable(false);
				txtrSelectPdbFile.setBackground(new Color(255, 140, 0));
				txtrSelectPdbFile.setText("Select a PDB file of a Protein/Ligand/DNA or complex");		
				//Button to select PDB file and will be mounted in the left of JPane
				final JButton btnSelectPdb = new JButton("Select PDB");
				btnSelectPdb.setEnabled(false);
				splitPane.setLeftComponent(btnSelectPdb);
				//Adding action to the button, to selected a PDB file
						//Will have name and path of the PDB file
						btnSelectPdb.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								JFileChooser pdbChooser = new JFileChooser(); //This is to select a PDB file
								pdbChooser.setAcceptAllFileFilterUsed(true);
								FileNameExtensionFilter pdbfilter = new FileNameExtensionFilter("PDB File","pdb"); //PDB Filter: To select only PDB files				
								pdbChooser.setFileFilter(pdbfilter);
								int returnVal = pdbChooser.showOpenDialog(null);//it opens a file chooser and returnVal will hold 0 or 1, based on selection of file
								if(returnVal == pdbChooser.APPROVE_OPTION){//If user select a file
									File file = pdbChooser.getSelectedFile(); //File chooser used the File utility of java, hence initializing a file object
									String returnValStr = file.getPath(); //file.getName will hold the name of selected file
									txtrSelectPdbFile.setText(returnValStr);
									txtrSelectPdbFile.setBackground(new Color(255, 250, 250));
									choosenPDB = file.getPath(); //Storing the path in global variable choosenPDB, s that its accessible to all.
									//Updating the information bar
									displayUpdates = "AMBERHOME:\n"+setAMBERHOME+"\nWorking Directory:\n  "+choosenDirectory+"\nSelected PDB File:\n  "+choosenPDB+"\nPlease choose the ligands, if not already.";
									textAreatmp.setBackground(new Color(255, 250, 250));
									textAreatmp.setText(displayUpdates);
									comboBox.setEnabled(true);
														
								}else{ //if user cancels the selction oof the file
									txtrSelectPdbFile.setText("Select a PDB file of a Protein/Ligand/DNA or complex");
									txtrSelectPdbFile.setBackground(Color.RED);
									textAreatmp.setText("Error in file selection, Please check");
									textAreatmp.setBackground(Color.RED);
									JOptionPane showFile = new JOptionPane();
									showFile.showMessageDialog(null,"User canceled the PDB file selection");
									
								}
							}
						});
						
//Set Directory Panel: This is to set the Working directory		
				JSplitPane splitPane_12 = new JSplitPane();
				splitPane_12.setBounds(10, 62, 697, 40);
				panel_5.add(splitPane_12);
				final JButton btnBrowse = new JButton("Working DIR");
				btnBrowse.setEnabled(false);
				//Pressing set directory button to set a default directory
		btnBrowse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JFileChooser setDir = new JFileChooser();
						setDir.setCurrentDirectory(new File("."));
						setDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						setDir.setAcceptAllFileFilterUsed(false);
						int returnVal = setDir.showOpenDialog(null);//it opens a file chooser and returnVal will hold 0 or 1, based on selection of file
						if(returnVal == setDir.APPROVE_OPTION){//If user select a file
							File file = setDir.getSelectedFile(); //File chooser used the File utility of java, hence initializing a file object
							choosenDirectory = file.getPath(); //file.getPath will hold the Path of Selected Dir
							txtSetWorkingDirectory.setText(choosenDirectory);
							txtSetWorkingDirectory.setBackground(new Color(255, 250, 250));
							displayUpdates = "AMBERHOME:\n"+setAMBERHOME+"\nWorking Directory:\n  "+choosenDirectory+"\n\nPlease Select PDB file and ligands, if not already.";
							textAreatmp.setBackground(new Color(255, 250, 250));
							textAreatmp.setText(displayUpdates);
							btnSelectPdb.setEnabled(true);
												
						}else{ //if user cancels the selection of the file
							txtSetWorkingDirectory.setText("Please Set the working Directory");
							txtSetWorkingDirectory.setBackground(Color.RED);
							textAreatmp.setText("Error in file selection, Please check");
							textAreatmp.setBackground(Color.RED);
							JOptionPane showFile = new JOptionPane();
							showFile.showMessageDialog(null,"User canceled the Directory selection");
						}								
					}
				});
		splitPane_12.setLeftComponent(btnBrowse);		
		txtSetWorkingDirectory = new JTextField();
		txtSetWorkingDirectory.setBackground(new Color(255, 140, 0));
		txtSetWorkingDirectory.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		txtSetWorkingDirectory.setEditable(false);
		splitPane_12.setRightComponent(txtSetWorkingDirectory);
		txtSetWorkingDirectory.setText("Set Working Directory");
		txtSetWorkingDirectory.setColumns(10);

//set AMBERHOME directory		
		JSplitPane splitPane_8 = new JSplitPane();
		splitPane_8.setBounds(10, 11, 697, 40);
		panel_5.add(splitPane_8);
		
		textField = new JTextField();
		textField.setText(setAMBERHOME);
		textField.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(255, 140, 0));
		splitPane_8.setRightComponent(textField);
		
		JButton btnAmberhome = new JButton("AMBERHOME");
		btnAmberhome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser setAmberHome = new JFileChooser();
				setAmberHome.setCurrentDirectory(new File("."));
				setAmberHome.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				setAmberHome.setAcceptAllFileFilterUsed(false);
				int returnVal = setAmberHome.showOpenDialog(null);//it opens a file chooser and returnVal will hold 0 or 1, based on selection of file
				if(returnVal == setAmberHome.APPROVE_OPTION){//If user select a file
					File file = setAmberHome.getSelectedFile(); //File chooser used the File utility of java, hence initializing a file object
					setAMBERHOME = file.getPath(); //file.getPath will hold the Path of Selected Dir
					textField.setText(setAMBERHOME);
					textField.setBackground(new Color(255, 250, 250));
					displayUpdates = "AMBERHOME:\n"+setAMBERHOME + "\nWorking Directory:\n  " + choosenDirectory+"  \nPlease Select PDB file and ligands, if not already.";
					textAreatmp.setBackground(new Color(255, 250, 250));
					textAreatmp.setText(displayUpdates);
					//btnSelectPdb.setEnabled(true);
					btnBrowse.setEnabled(true);
										
				}else{ //if user cancels the selection of the file
					textField.setText("Please Set the AMBERHOME:");
					textField.setBackground(Color.RED);
					textAreatmp.setText("User must select directory in which amber is installed");
					textAreatmp.setBackground(Color.RED);
					JOptionPane showFile = new JOptionPane();
					showFile.showMessageDialog(null,"User canceled the Directory selection");
					btnBrowse.setEnabled(false);
				}
			}
		});
		splitPane_8.setLeftComponent(btnAmberhome);
		
		
		


				
				
				
				

    //So far, I have got the information about input files like location of PDB and ligand files.
    //Now is the time to set parameter files of Tleap, so that I can generate the topology files	
 
		//>import file handeling classes
		//>write an empty tleap input file
		//>ask user to set desired files
		//>write complete tleap file

    
//Tab 2, Tleap Parameters: Create Panel2 for Second Tab of main tab
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.GRAY);
		panel2.setBounds(10, 28, 670, 255);
		panel2.setLayout(null);		
		mainTab.addTab("Tleap Parameters", null, panel2, null); //Adding panel2 to Tleap Parameters tab
		mainTab.setEnabledAt(1, false);
			displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
			+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n "+extraTleapCommands;;

			
//Information Bar2: Information bar of second tab	
			JPanel panel_6 = new JPanel();
			panel_6.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel_6.setBounds(10, 307, 747, 252);
			panel2.add(panel_6);
			panel_6.setLayout(null);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(20, 11, 717, 194);
			panel_6.add(scrollPane);
			final JTextArea textArea = new JTextArea();
			scrollPane.setViewportView(textArea);
			textArea.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
			textArea.setEditable(false);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setBackground(new Color(255, 250, 240));
			textArea.setText(displayUpdates2);
			
//Panel for Box Type, Water Type, Distance, Position and Closeness grouping
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 11, 384, 285);
		panel2.add(panel_1);	

//Select Solvent Box Panel		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setResizeWeight(1.0);
		splitPane_3.setBounds(5, 12, 107, 62);		
			txtBoxType = new JTextField();
			txtBoxType.setFont(new Font("Arial", Font.BOLD, 11));
			txtBoxType.setToolTipText("Select the type of solvent box.");
			txtBoxType.setBackground(new Color(0, 153, 255));
			txtBoxType.setEditable(false);
			splitPane_3.setLeftComponent(txtBoxType);
			txtBoxType.setText("Box Type");
			txtBoxType.setColumns(10);		
			final JComboBox comboBox_1 = new JComboBox();
			comboBox_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					boxType = (String) comboBox_1.getSelectedItem();
					//Enable Position box if the box type is solvatecap
					if(boxType.equals("solvatecap") == true){
						isBoxTypeCap = true;
						txtPosition.setEnabled(true);
						textField_3.setEnabled(true);
						positionSolvateCap = textField_3.getText();
						displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
								+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): Enter the value in Position boox and must Hit Enter"+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n "+extraTleapCommands;;
						textArea.setText(displayUpdates2);
						}else{
						isBoxTypeCap = false;
						txtPosition.setEnabled(false);
						textField_3.setEnabled(false);
						positionSolvateCap = "Not Applicable for this box type";
						displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
								+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n "+extraTleapCommands;;
						textArea.setText(displayUpdates2);
						}
					
					
				}
			});
			comboBox_1.setBackground(new Color(255, 250, 250));
			comboBox_1.setFont(new Font("Trebuchet MS", Font.ITALIC, 11));
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"solvateoct", "solvatebox", "solvatecap", "solvateshell"}));
			comboBox_1.setToolTipText("Select the type of solvent box.");
		splitPane_3.setRightComponent(comboBox_1);
		
		JLabel label = new JLabel("");
		label.setBounds(198, 17, 0, 0);		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(203, 17, 0, 0);		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(208, 17, 0, 0);		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(213, 17, 0, 0);

//Water Model panel:
		JSplitPane splitPane_4 = new JSplitPane();
		splitPane_4.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_4.setResizeWeight(1.0);
		splitPane_4.setBounds(112, 12, 107, 62);		
			txtWaterModel = new JTextField();
			txtWaterModel.setFont(new Font("Arial", Font.BOLD, 11));
			txtWaterModel.setBackground(new Color(0, 153, 255));
			txtWaterModel.setEditable(false);
			splitPane_4.setLeftComponent(txtWaterModel);
			txtWaterModel.setText("Water models");
			txtWaterModel.setColumns(10);		
			final JComboBox comboBox_2 = new JComboBox();
			comboBox_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					waterModel = (String) comboBox_2.getSelectedItem();
					displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
							+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n "+extraTleapCommands;;
							textArea.setText(displayUpdates2);
				}
			});
			comboBox_2.setBackground(new Color(255, 250, 240));
			comboBox_2.setFont(new Font("Trebuchet MS", Font.ITALIC, 11));
			comboBox_2.setMaximumRowCount(25);
			comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"TIP3PBOX", "TIP3PFBOX", "TIP4PBOX", "TIP4PEWBOX", "TIP5PBOX", "SPC", "SPCBOX", "SPCFWBOX", "SPF", "SPG", "T4E", "TP3", "TP4", "TP5", "TPF", "CHCL3BOX", "DC4", "MEOHBOX", "NMABOX", "PL3", "POL3BOX", "QSPCFWBOX"}));
		splitPane_4.setRightComponent(comboBox_2);

//Closeness Panel: (Optional) How close solvent ATOMs may come to solute. Scale strats from 0.5 to 2 Angstron. Default is 1 angstron.
		JSplitPane splitPane_9 = new JSplitPane();
		splitPane_9.setBounds(5, 181, 372, 77);
		splitPane_9.setOrientation(JSplitPane.VERTICAL_SPLIT);		
			txtCloseness = new JTextField();
			txtCloseness.setForeground(new Color(255, 250, 240));
			txtCloseness.setFont(new Font("Arial", Font.BOLD, 11));
			txtCloseness.setToolTipText("(Optional) How close solvent ATOMs may come to solute. Scale strats from 0.5 to 2 Angstron. Default is 1 angstron.");
			txtCloseness.setBackground(new Color(51, 153, 255));
			txtCloseness.setEditable(false);
		splitPane_9.setLeftComponent(txtCloseness);
			txtCloseness.setText("Closeness");
			txtCloseness.setColumns(10);		
			slider = new JSlider();
			slider.setToolTipText("(Optional) How close solvent ATOMs may come to solute. Scale strats from 0.5 to 2 Angstron. Default is 1 angstron.");
			slider.setMajorTickSpacing(1);
			slider.setValue(10);
			slider.setPaintTicks(true);
			slider.setPaintLabels(false);
			slider.setBorder(new MatteBorder(1, 1, 3, 3, (Color) new Color(0, 0, 0)));
			slider.setMinimum(5);
			slider.setMaximum(20);
			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent scaleReader) {
					JSlider scaleValue = (JSlider)scaleReader.getSource();
					if(scaleValue.getValueIsAdjusting() == true) {
						closenessBox = (double) scaleValue.getValue()/10.0;
						displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
								+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n "+extraTleapCommands;;
								textArea.setText(displayUpdates2);
						}
				}
			});
		splitPane_9.setRightComponent(slider);
		
		panel_1.setLayout(null);
		panel_1.add(splitPane_3);
		panel_1.add(label);
		panel_1.add(label_1);
		panel_1.add(label_2);
		panel_1.add(label_3);
		panel_1.add(splitPane_4);
		
//Distance Panel: To set the size of the box		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setBounds(5, 90, 372, 77);
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);		
			slider_1 = new JSlider();
			slider_1.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent scaleReader) {
					JSlider scaleValue = (JSlider)scaleReader.getSource();
					if(scaleValue.getValueIsAdjusting() == true) {
						distanceBox = scaleValue.getValue();
						displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
								+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n "+extraTleapCommands;;
								textArea.setText(displayUpdates2);
						}
				}
			});
			slider_1.setValue(10);
			slider_1.setToolTipText("");
			slider_1.setPaintTicks(true);
			slider_1.setPaintLabels(true);
			slider_1.setMinimum(6);
			slider_1.setMaximum(30);
			slider_1.setMajorTickSpacing(2);
			slider_1.setBorder(new MatteBorder(1, 1, 3, 3, (Color) new Color(0, 0, 0)));
		splitPane_2.setRightComponent(slider_1);
		panel_1.add(splitPane_2);		
			txtDistance = new JTextField();
			txtDistance.setForeground(new Color(255, 250, 240));
			txtDistance.setToolTipText("This should be used to set box size/thickness");
			txtDistance.setText("Distance");
			txtDistance.setFont(new Font("Arial", Font.BOLD, 11));
			txtDistance.setEditable(false);
			txtDistance.setColumns(10);
			txtDistance.setBackground(new Color(0, 153, 255));
		splitPane_2.setLeftComponent(txtDistance);
		panel_1.add(splitPane_9);
		
//Position Panel: If solvatecap box type is selected, this will be enabled		
			txtPosition = new JTextField();
			txtPosition.setEnabled(false);
			txtPosition.setForeground(new Color(255, 250, 240));
			txtPosition.setFont(new Font("Arial", Font.BOLD, 11));
			txtPosition.setBackground(new Color(0, 153, 255));
			txtPosition.setBounds(226, 14, 95, 20);
		panel_1.add(txtPosition);
			txtPosition.setToolTipText("Enter the center of the solvent cap");
			txtPosition.setEditable(false);
			txtPosition.setText("Position");
			txtPosition.setColumns(10);		
			textField_3 = new JTextField();
			textField_3.setText("{0.0.0.0.0.0}");
			textField_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					positionSolvateCap = textField_3.getText();
					displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
							+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n "+extraTleapCommands;;
					textArea.setText(displayUpdates2);
				}
			});
			textField_3.setFont(new Font("Trebuchet MS", Font.ITALIC, 11));
			textField_3.setBounds(226, 36, 151, 20);
		panel_1.add(textField_3);
			textField_3.setEnabled(false);
			textField_3.setToolTipText("");
			textField_3.setColumns(10);

//Force Field Panel: New Panel for Force field selection		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(404, 11, 353, 285);
		panel2.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
//First force field panel		
		JSplitPane splitPane_6 = new JSplitPane();
		splitPane_6.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_3.add(splitPane_6);		
			txtForceField = new JTextField();
			txtForceField.setFont(new Font("Arial", Font.BOLD, 11));
			txtForceField.setBackground(new Color(0, 153, 255));
			txtForceField.setEditable(false);
		splitPane_6.setLeftComponent(txtForceField);
			txtForceField.setText("Select first Force Field:");
			txtForceField.setColumns(10);		
			final JComboBox comboBox_4 = new JComboBox();
			comboBox_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ff1 = (String) comboBox_4.getSelectedItem();
					displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
							+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n "+extraTleapCommands;;
					textArea.setText(displayUpdates2);
					
				}
			});
			comboBox_4.setBackground(new Color(255, 250, 240));
			comboBox_4.setFont(new Font("Trebuchet MS", Font.ITALIC, 11));
			comboBox_4.setMaximumRowCount(25);
			comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"leaprc.gaff", "leaprc.ff99SB", "leaprc.amoeba", "leaprc.ff13", "leaprc.GLYCAM_06EPb", "leaprc.constph", "leaprc.ff99bsc0", "leaprc.GLYCAM_06h", "leaprc.ff02polEP.r1", "leaprc.GLYCAM_06h-1", "leaprc.ff02pol.r1", "leaprc.ff99SBildn  leaprc.GLYCAM_06h-12SB", "leaprc.ff03.r1", "leaprc.ff99SBnmr   leaprc.lipid11", "leaprc.ff03ua", "leaprc.ffAM1", "leaprc.parmCHI_YIL.bsc", "leaprc.ff10", "leaprc.ffPM3", "leaprc.ff12SB"}));
		splitPane_6.setRightComponent(comboBox_4);

//Second force field panel		
		JSplitPane splitPane_7 = new JSplitPane();
		splitPane_7.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_3.add(splitPane_7);		
			txtSelectSecondForce = new JTextField();
			txtSelectSecondForce.setFont(new Font("Arial", Font.BOLD, 11));
			txtSelectSecondForce.setBackground(new Color(0, 153, 255));
			txtSelectSecondForce.setEditable(false);
			txtSelectSecondForce.setText("Select second Force Field:");
			txtSelectSecondForce.setColumns(10);
		splitPane_7.setLeftComponent(txtSelectSecondForce);		
			final JComboBox comboBox_5 = new JComboBox();
			comboBox_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ff2 = (String) comboBox_5.getSelectedItem();
					displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
							+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n "+extraTleapCommands;;
					textArea.setText(displayUpdates2);
				}
			});
			comboBox_5.setBackground(new Color(255, 250, 240));
			comboBox_5.setFont(new Font("Trebuchet MS", Font.ITALIC, 11));
			comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"leaprc.ff99SB", "leaprc.gaff", "leaprc.amoeba", "leaprc.ff13", "leaprc.GLYCAM_06EPb", "leaprc.constph", "leaprc.ff99bsc0", "leaprc.GLYCAM_06h", "leaprc.ff02polEP.r1", "leaprc.GLYCAM_06h-1", "leaprc.ff02pol.r1", "leaprc.ff99SBildn  leaprc.GLYCAM_06h-12SB", "leaprc.ff03.r1", "leaprc.ff99SBnmr   leaprc.lipid11", "leaprc.ff03ua", "leaprc.ffAM1", "leaprc.parmCHI_YIL.bsc", "leaprc.ff10", "leaprc.ffPM3", "leaprc.ff12SB"}));
			comboBox_5.setMaximumRowCount(25);
		splitPane_7.setRightComponent(comboBox_5);
		
//Third force field panel		
		JSplitPane splitPane_5 = new JSplitPane();
		splitPane_5.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_3.add(splitPane_5);		
			txtSelectThirdForce = new JTextField();
			txtSelectThirdForce.setFont(new Font("Arial", Font.BOLD, 11));
			txtSelectThirdForce.setBackground(new Color(0, 153, 255));
			txtSelectThirdForce.setEditable(false);
			txtSelectThirdForce.setText("Select third Force Field:");
			txtSelectThirdForce.setColumns(10);
		splitPane_5.setLeftComponent(txtSelectThirdForce);		
			final JComboBox comboBox_6 = new JComboBox();
			comboBox_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ff3 = (String) comboBox_6.getSelectedItem();
					displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
							+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n "+extraTleapCommands;;
					textArea.setText(displayUpdates2);
				}
			});
			comboBox_6.setBackground(new Color(255, 250, 240));
			comboBox_6.setFont(new Font("Trebuchet MS", Font.ITALIC, 11));
			comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"leaprc.gaff", "leaprc.ff99SB", "leaprc.amoeba", "leaprc.ff13", "leaprc.GLYCAM_06EPb", "leaprc.constph", "leaprc.ff99bsc0", "leaprc.GLYCAM_06h", "leaprc.ff02polEP.r1", "leaprc.GLYCAM_06h-1", "leaprc.ff02pol.r1", "leaprc.ff99SBildn  leaprc.GLYCAM_06h-12SB", "leaprc.ff03.r1", "leaprc.ff99SBnmr   leaprc.lipid11", "leaprc.ff03ua", "leaprc.ffAM1", "leaprc.parmCHI_YIL.bsc", "leaprc.ff10", "leaprc.ffPM3", "leaprc.ff12SB"}));
			comboBox_6.setMaximumRowCount(25);
		splitPane_5.setRightComponent(comboBox_6);		
		
//Solvent force field panel
		JSplitPane splitPane_11 = new JSplitPane();
		panel_3.add(splitPane_11);		
			final JComboBox comboBox_3 = new JComboBox();
			comboBox_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ff4 = (String) comboBox_3.getSelectedItem();
					solventModelMode = ff4;
					displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
							+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\t\t\tNew Solvent Model: "+ff4;
					textArea.setText(displayUpdates2);
				}
			});
			comboBox_3.setBackground(new Color(255, 250, 240));
			comboBox_3.setFont(new Font("Trebuchet MS", Font.ITALIC, 11));
			comboBox_3.setEnabled(false);
			comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"frcmod.chcl3", "frcmod.ff99SP", "frcmod.protonated_nucleic", "frcmod.constph", "frcmod.ionsff99_tip3p", "frcmod.qspcfw", "frcmod.dc4", "frcmod.ionsjc_spce", "frcmod.spce", "frcmod.ff02pol.r1", "frcmod.ionsjc_tip3p", "frcmod.spcfw", "frcmod.ff03", "frcmod.ionsjc_tip4pew", "frcmod.tip3pf", "frcmod.ff03ua", "frcmod.meoh", "frcmod.tip4p", "frcmod.ff12SB", "frcmod.nma", "frcmod.tip4pew", "frcmod.ff99SB", "frcmod.parmbsc0", "frcmod.tip5p", "frcmod.ff99SBildn", "frcmod.parmCHI_YIL", "frcmod.urea"}));
		splitPane_11.setLeftComponent(comboBox_3);
			comboBox_3.setMaximumRowCount(25);		
			//JRadio button to enable or disable selecttion of solvent type
			rdbtnSolventModels = new JRadioButton("Solvent Models", false);
		splitPane_11.setRightComponent(rdbtnSolventModels);
			rdbtnSolventModels.setFont(new Font("Arial", Font.BOLD, 11));
			rdbtnSolventModels.addActionListener(new ActionListener() {
			 //if value of count is even number, combobox3 (solvent model selection) is enabled, else false
				public void actionPerformed(ActionEvent e) {
					if (countSolvate%2 == 0){
						comboBox_3.setEnabled(true);
						displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
								+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tNew Solvent Model: Please select a solvent Model";
								textArea.setText(displayUpdates2);
						}else{
							solventModelMode = "Default";
							comboBox_3.setEnabled(false);
							ff4 = "leaprc.gaff";
							displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
									+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n "+extraTleapCommands;
									textArea.setText(displayUpdates2);
							}
					countSolvate++;
				}
			});		
			rdbtnSolventModels.setMnemonic(KeyEvent.VK_B);
			rdbtnSolventModels.setActionCommand("Solvent Models");	
		
		
//Neutralize ions manually panel
		JToolBar toolBar_2 = new JToolBar();
		toolBar_2.setEnabled(false);
		toolBar_2.setFloatable(false);
		panel_3.add(toolBar_2);		
		final JComboBox comboBox_7 = new JComboBox();
		comboBox_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ionType = (String) comboBox_7.getSelectedItem();
				displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
						+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\nNeutralization: Manual"+"\t\t\t\tSolvent Model: "+solventModelMode+"\n    Ion Type: "+ionType+"\n    Ion Numbers: "+"Please enter Number of ions in the box.";
						textArea.setText(displayUpdates2);
						neutralizationMethod = "Manual:\nIon Type: "+ionType;
			}
		});
		toolBar_2.add(comboBox_7);
		comboBox_7.setEnabled(false);
		comboBox_7.setModel(new DefaultComboBoxModel(new String[] {"Na+", "Cl-", "Cs+", "F-", "I-", "K+", "Li+", "Mg+", "Br-", "Rb+"}));	
		
			textFieldSetIons = new JTextField();
			textFieldSetIons.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ionNumber = Integer.parseInt(textFieldSetIons.getText());
					displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
							+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\nNeutralization: Manual"+"\t\t\t\tSolvent Model: "+solventModelMode+"\n    Ion Type: "+ionType+"\n    Ion Numbers: "+ionNumber;
							textArea.setText(displayUpdates2);
							neutralizationMethod = "Manual:\nIon Type: "+ionType+"\nIon Numbers: "+ionNumber;
				}
			});
			toolBar_2.add(textFieldSetIons);
			textFieldSetIons.setEnabled(false);
			textFieldSetIons.setColumns(10);		
			rdbtnNeutralize = new JRadioButton("Neutralize Manually",false); //Button to enable neutralization of ions manually
			
			rdbtnNeutralize.setFont(new Font("Arial", Font.BOLD, 11));
			toolBar_2.add(rdbtnNeutralize);
			rdbtnNeutralize.addActionListener(new ActionListener() {
				//countNeutralize = 0; //if value of count is even number, combobox_7 (Set ions type) and textFieldSetIons (enter number of ions) is enabled, else false
				public void actionPerformed(ActionEvent arg0) {
						if (countNeutralize%2 == 0){
							isNeutralizeManual = true;
							comboBox_7.setEnabled(true);
							textFieldSetIons.setEnabled(true);
							countNeutralize++;
							displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
									+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\nNeutralization: Manual"+"\t\t\t\tSolvent Model: "+solventModelMode+"\n    Ion Type: "+"Please choose an ion type"+"\n    Ion Numbers: "+"Please enter Number of ions in the box.";
									textArea.setText(displayUpdates2);
									neutralizationMethod = "Manual, Ions yet not selected";
							}else{
								isNeutralizeManual=false;
								comboBox_7.setEnabled(false);
								textFieldSetIons.setEnabled(false);
								neutralizationMethod = "Automatic";
								displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
										+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n"+extraTleapCommands;
								textArea.setText(displayUpdates2);
								countNeutralize++;
						}
				}
			});

//Update Information area2 Panel			
		JButton updateInfoArea2 = new JButton("Update Information Area");
		updateInfoArea2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
						+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n"+extraTleapCommands;
						textArea.setText(displayUpdates2);
				
			}
		});
		panel_3.add(updateInfoArea2);

//Reset Button		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boxType = "solvateoct";
				waterModel = "TIP3PBOX";
				distanceBox = 10;
				closenessBox = 1;
				positionSolvateCap = "Not Applicable for default Box Type";
				ff1 = "leaprc.gaff";
				ff2 = "leaprc.gaff";
				ff3 = "leaprc.gaff";
				ff4 = "leaprc.gaff";
				solventModelMode = "Default";
				neutralizationMethod = "Automatic";				
				rdbtnNeutralize.setSelected(false); //reset Neutralization
				comboBox_3.setEnabled(false);
				rdbtnSolventModels.setSelected(false);//reset Solvent model button
				comboBox_7.setEnabled(false);
				textFieldSetIons.setEnabled(false);
				countNeutralize = 0; //So that radio button of neutralization doen't behave abnormally
				countSolvate = 0;//So that radio button of sovalte model doen't behave abnormally
				slider_1.setValue(10); //Reset Slider for Distance
				slider.setValue(10); //reset Slider of closeness
				txtPosition.setEnabled(false); //Reset position
				textField_3.setEnabled(false); //reset position
				extraTleapCommands = ""; //rest Extra Tleap Commands
				
				//Reset all the combo boxes
				comboBox_1.setSelectedIndex(0);
				comboBox_2.setSelectedIndex(0);
				comboBox_3.setSelectedIndex(0);
				comboBox_4.setSelectedIndex(0);
				comboBox_5.setSelectedIndex(0);
				comboBox_6.setSelectedIndex(0);
				comboBox_7.setSelectedIndex(0);
				
				solventModelMode ="Default";
				displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
						+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n "+extraTleapCommands;;
						textArea.setText(displayUpdates2);
			}
		});
		panel_3.add(btnReset);
		
		
		
		final JButton btnNewButton = new JButton("Next >> Run Tleap");
		btnNewButton.setBounds(586, 216, 151, 25);
		panel_6.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setForeground(new Color(255, 250, 240));
		btnNewButton.setBackground(new Color(255, 0, 0));
		
		 //to hold/popup the textarea which will get Extra Tleap commands 
		
		final JButton btnAddExtraTleap = new JButton("Add Extra Tleap Command");//Extra Tleap COmmand
		btnAddExtraTleap.setToolTipText("This/these commands will be executed right after protein and ligands are loaded into Tleap");
		btnAddExtraTleap.addActionListener(new ActionListener() {//Press "Add Extra Tleap Command" button
			public void actionPerformed(ActionEvent arg0) {
				btnAddExtraTleap.setBackground(new Color(0, 153, 255));
								
				JTextArea getTleapCommands = new JTextArea(); //create an text area to get commands
				JScrollPane scroller = new JScrollPane(getTleapCommands); //Create an scrollable bar and paste textarea into it				
				JOptionPane.showMessageDialog(null,  scroller); //Finally, paste scroll are to an popup schow message dialog box
				
				extraTleapCommands = getTleapCommands.getText(); //Get Entered commands
				
				displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
						+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n"+extraTleapCommands;
						textArea.setText(displayUpdates2);
				
			}
		});
		btnAddExtraTleap.setBackground(Color.ORANGE);
		btnAddExtraTleap.setBounds(20, 216, 200, 23);
		panel_6.add(btnAddExtraTleap);
		
		final JButton btnDeleteAddedCommands = new JButton("Delete Added Commands"); //It will delete all the commands which are added extra
		btnDeleteAddedCommands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {//Press this button to delete extra added commands
				extraTleapCommands= ""; //Making variable empty
				btnAddExtraTleap.setBackground(Color.ORANGE);
				btnDeleteAddedCommands.setBackground(Color.ORANGE);
				displayUpdates2 = "Tleap Parameters are:\n    Box Type: "+boxType+"\t\t\t\tFirst force field: "+ff1+"\n    Water Model: "+waterModel+"\t\t\t\tSecond force field: "+ff2+"\n    Distance or Radius orThickness (Box Size): "
						+distanceBox+"\t\tThird force field: "+ff3+"\n    Closeness between atoms of Solvent: "+closenessBox+"\n    Position (Center of Solvent Cap): "+positionSolvateCap+"\n\n    Neutralization: "+neutralizationMethod+"\t\tSolvent Model: "+solventModelMode+"\n  Extra Tleap Commands:\n"+extraTleapCommands;
						textArea.setText(displayUpdates2);
			}
		});		
		btnDeleteAddedCommands.setBackground(new Color(0, 153, 255));
		btnDeleteAddedCommands.setBounds(230, 216, 200, 23);
		panel_6.add(btnDeleteAddedCommands);
		

		

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//updateInfoArea3();
				mainTab.setEnabledAt(2, true);
				mainTab.setSelectedIndex(2); //This will automatically switch the tab
				updateinfoArea3();
				btnNewButton.setBackground(new Color(50, 205, 50));
			}
		});
		
		runTleap3();
	}
		
			public void runTleap3(){//Tab 3: All File and tl"Run Tleap >> Next"rs are set and now time to run tleap
				//First we will used the name of ligands from saveLigPath array, and will generate a frc mod for it
				//Followed by, creating a tleap source file, which will create "lib" file for each ligand
				//Once, lib files are created, we have all the required files to run tleap
				//a source file will be created with filenames and parameters
				//Either directory will be changed to set directory (if possible) or tleap will run from set directory, so that all the output files are created in set directory

				//Declaring variables
				final ArrayList <String> holdAnteChamberCommand = new ArrayList<String>();
				
//This is tab 3
				JPanel panel_2 = new JPanel();
				panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_2.setBackground(Color.GRAY);
				mainTab.addTab("Run Tleap", null, panel_2, null);
				mainTab.setEnabledAt(2, false);
				panel_2.setLayout(null);				
				
//Update information on infoArea3				
				JPanel panel_4 = new JPanel();
				panel_4.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_4.setBounds(10, 11, 747, 58);
				panel_2.add(panel_4);
				
				JButton btnGetParameters = new JButton("Update Parameters");
				btnGetParameters.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						updateinfoArea3();
					}
				});
				panel_4.add(btnGetParameters);
				
				
//Run tleap button:				
				JButton btnRunTleap = new JButton("Generate Topology");
				btnRunTleap.addActionListener(new ActionListener() {
					/**
					 * This is where everything will happen, once user click on generate topology
					 * It will generate frcmods, libs, source files for tleap and finally topologies.
					 * So, following piece of code is soul of this code.
					 */
										
					public void actionPerformed(ActionEvent arg0) {
						int assignLigNum = 0;
												
							if (isLigandPresent == true){//if ligand is present, generate the frcmod and lib files for ligands
									holdAnteChamberCommand.clear();
									String watchActivityOnIF3 = "echo Follwing parmchk commands will be executed to generated topology:\n";
									String generateLigandLib = "source leaprc.gaff"+"\n"; //This will hold the commands to run Tleap for ligand library generation
									String newLine = null;
									String generateTopologySource = "source "+ff1+"\n"+
											"source "+ff2+"\n"+
											"source "+ff3+"\n"+
											"source "+ff4+"\n";
											
									for(String eachLigand:saveLigPath){//generate parmchk command list
										
										String getLigFullName = saveLigName.get(assignLigNum);	
										String getLigName = (String) getLigFullName.subSequence(0, (getLigFullName.length() - 5)); //Fot just name of the ligand without extension
										//JOptionPane.showMessageDialog(null, "Deciphering "+getLigFullName+"\n"+"Got\n"+getLigName);
										
										holdAnteChamberCommand.add("export AMBERHOME="+setAMBERHOME+"\n"+
												"export PATH=$PATH:AMBERHOME/bin:$AMBERHOME/AmberTools/bin"+"\n"+	
										"$AMBERHOME/bin/parmchk -i "+eachLigand+" -f mol2 -o "+ choosenDirectory+"/"+getLigName+".frcmod\n");
										
										watchActivityOnIF3 = watchActivityOnIF3 + holdAnteChamberCommand.get(assignLigNum); //This holds the commands to be written in runParmchk.sh
										
										generateLigandLib = generateLigandLib + 
															"loadamberparams "+getLigName+".frcmod"+"\n"+
															getLigName+" = loadmol2 "+eachLigand+"\n"+
															"saveoff "+getLigName+" "+getLigName+".lib"+"\n"; //Commands generated for each ligand, now need to write these to  ligandLib.source
										
										generateTopologySource = generateTopologySource+
																 "loadamberparams "+getLigName+".frcmod"+"\n"+
																 "loadoff "+getLigName+".lib"+"\n"; //Commands to load ligand's libraries and frcmod into tleap, will be written to generateTopology.source
										
										++assignLigNum;
										}
									
									generateLigandLib = generateLigandLib+"quit";//lets make tleap source file quitable...:P
									
									if(isBoxTypeCap == true){//add commands to load PDB and solvate it according to the boxtype in generateTopology.source
										generateTopologySource = generateTopologySource+
												 "complex=loadpdb "+choosenPDB+"\n"+
												 boxType+" complex "+waterModel+" "+positionSolvateCap+" "+distanceBox+" "+closenessBox+"\n";
										
									}else{
										generateTopologySource = generateTopologySource+
												 "complex=loadpdb "+choosenPDB+"\n"+
												 extraTleapCommands+"\n"+
												 boxType+" complex "+waterModel+" "+distanceBox+" "+closenessBox+"\n";
									}
									
									if(isNeutralizeManual == true){//Continue adding coomands to generateTopology.source, to nutralize the complex according to selection
										generateTopologySource = generateTopologySource+
												"addions complex "+ionType+" "+ionNumber+"\n";
												
									}else{
										generateTopologySource = generateTopologySource+
												"addions complex Cl- 0"+"\n"+
												"addions complex Na+ 0"+"\n";
									}
									
									generateTopologySource = generateTopologySource+
															"saveamberparm complex "+getOutputFileName+".prmtop "+getOutputFileName+".inpcrd"+"\n"+
															"savepdb complex "+getOutputFileName+".pdb"+"\n"+
															"quit"; //This is the final command to tleap source file to generate the topologies.
																									
									PrintStream sourceTleapLigLib = null; 
									try {//Creating source file for tleap to generate Ligand library files
										sourceTleapLigLib = new PrintStream(new FileOutputStream(choosenDirectory+"/ligandLib.source"),true);								
									} catch (FileNotFoundException e) {
										e.printStackTrace();
									}
									
										try{ //Write the file
											sourceTleapLigLib.println(generateLigandLib);
											}finally{ //This is extremely important to close the file stream
												sourceTleapLigLib.close();
											}
										
									PrintStream sourceTleapTopology = null; 
									try {//Creating source file for tleap to generate final topology files
										sourceTleapTopology = new PrintStream(new FileOutputStream(choosenDirectory+"/generateTopology.source"),true);								
									} catch (FileNotFoundException e) {
										e.printStackTrace();
									}
									
										try{ //Write the file
											sourceTleapTopology.println(generateTopologySource);
											}finally{ //This is extremely important to close the file stream
												sourceTleapTopology.close();
											}		
										
									PrintStream fileWriter = null; 
									try {//Creating a shell file to run parmchk
											fileWriter = new PrintStream(new FileOutputStream(choosenDirectory+"/runParmchk.sh"),true);								
									} catch (FileNotFoundException e) {
										e.printStackTrace();
									}
									
										try{ //Write the file
											fileWriter.println(watchActivityOnIF3+
															"cd "+choosenDirectory+"\n"+
															"tleap -f "+choosenDirectory+"/ligandLib.source"+"\n"+
															"tleap -f "+choosenDirectory+"/generateTopology.source");
											}finally{ //This is extremely important to close the file stream
												fileWriter.close();
											}	
									
									PrintStream logWriter = null;
									try {//Creating a shell file to write log file
										logWriter = new PrintStream(new FileOutputStream(choosenDirectory+"/AUTD.log"),true);								
									}catch (FileNotFoundException e) {
										e.printStackTrace();
									} 							
																	
									try {//Set permission and execute	runParmchk.sh script							
											//Help is taken from internet:http://stackoverflow.com/questions/10422869/java-execute-a-sh-file
										Runtime.getRuntime().exec("chmod +x "+choosenDirectory+"/runParmchk.sh");
										 Process execParmchk = Runtime.getRuntime().exec(choosenDirectory+"/runParmchk.sh");
										 try {
											 String line;
											BufferedReader execParmchkInputBuffer = new BufferedReader(new InputStreamReader(execParmchk.getInputStream()));
											while ((line = execParmchkInputBuffer.readLine()) != null){
												logWriter.println(line+"\n");
												newLine = newLine + line+"\n";	
											}
											
											execParmchkInputBuffer.close();
											
											BufferedReader execParmchkErrorBuffer = new BufferedReader(new InputStreamReader(execParmchk.getErrorStream()));
											while ((line = execParmchkErrorBuffer.readLine()) != null){
												logWriter.println("Letz see, what error do we get during frcmod generation:\n"+line+"\n");
												newLine = newLine +  line+"\n";		
												
											}
											
											execParmchkErrorBuffer.close();
											
											execParmchk.waitFor();											
											//logWriter.println("execParmchk, exitValue() after waitfor:\n"+execParmchk.exitValue());
										} catch (InterruptedException e) {
											JOptionPane.showMessageDialog(null, e.getMessage());
										}
										 																				 
									}catch(IOException e){//this will tell you the exact error									
										JOptionPane.showMessageDialog(null, e.getMessage()); //Display the error
										try{
											//write the log
												 logWriter.println("Following error occured when Parmchk was being executed:\n"+e.getMessage());
											 }finally{//close the file stream
												 logWriter.close();
											 }
									}
									//Update the activity on Info Area3
									infoArea3.setText(newLine);
															
							}else{//if There is no ligand provided by the user.
								//infoArea3.setText("No ligand is present.");
								String newLine = null;
								String generateTopologySource = "source "+ff1+"\n"+
										"source "+ff2+"\n"+
										"source "+ff3+"\n"+
										"source "+ff4+"\n";
														
								if(isBoxTypeCap == true){//add commands to load PDB and solvate it according to the boxtype in generateTopology.source
									generateTopologySource = generateTopologySource+
											 "complex=loadpdb "+choosenPDB+"\n"+
											 extraTleapCommands+"\n"+
											 boxType+" complex "+waterModel+" "+positionSolvateCap+" "+distanceBox+" "+closenessBox+"\n";
								
								}else{
									generateTopologySource = generateTopologySource+
											 "complex=loadpdb "+choosenPDB+"\n"+
											 boxType+" complex "+waterModel+" "+distanceBox+" "+closenessBox+"\n";
								}
								
								if(isNeutralizeManual == true){//Continue adding coomands to generateTopology.source, to nutralize the complex according to selection
									generateTopologySource = generateTopologySource+
											"addions complex "+ionType+" "+ionNumber+"\n";
											
								}else{
									generateTopologySource = generateTopologySource+
											"addions complex Cl- 0"+"\n"+
											"addions complex Na+ 0"+"\n";
								}
								
								generateTopologySource = generateTopologySource+
														"saveamberparm complex "+getOutputFileName+".prmtop "+getOutputFileName+".inpcrd"+"\n"+
														"savepdb complex "+getOutputFileName+".pdb"+"\n"+
														"quit"; //This is the final command to tleap source file to generate the topologies.
																								
																	
								PrintStream sourceTleapTopology = null; 
								try {//Creating source file for tleap to generate final topology files
									sourceTleapTopology = new PrintStream(new FileOutputStream(choosenDirectory+"/generateTopology.source"),true);								
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}
								
									try{ //Write the file
										sourceTleapTopology.println(generateTopologySource);
										}finally{ //This is extremely important to close the file stream
											sourceTleapTopology.close();
										}		
									
								PrintStream fileWriter = null; 
								try {//Creating a shell file to run parmchk
										fileWriter = new PrintStream(new FileOutputStream(choosenDirectory+"/runParmchk.sh"),true);								
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}
								
									try{ //Write the file
										fileWriter.println("export AMBERHOME="+setAMBERHOME+"\n"+
														  "export PATH=$PATH:AMBERHOME/bin:$AMBERHOME/AmberTools/bin"+"\n"+
														  "cd "+choosenDirectory+"\n"+
														  "tleap -f "+choosenDirectory+"/generateTopology.source");
										}finally{ //This is extremely important to close the file stream
											fileWriter.close();
										}	
								
								PrintStream logWriter = null;
								try {//Creating a shell file to write log file
									logWriter = new PrintStream(new FileOutputStream(choosenDirectory+"/AUTD.log"),true);								
								}catch (FileNotFoundException e) {
									e.printStackTrace();
								} 					
																															
								try {//Set permission and execute	runParmchk.sh script							
										//Help is taken from internet:http://stackoverflow.com/questions/10422869/java-execute-a-sh-file
									Runtime.getRuntime().exec("chmod +x "+choosenDirectory+"/runParmchk.sh");
									 Process execParmchk = Runtime.getRuntime().exec(choosenDirectory+"/runParmchk.sh");
									 try {
										 String line;
										 
										BufferedReader execParmchkInputBuffer = new BufferedReader(new InputStreamReader(execParmchk.getInputStream()));
										while ((line = execParmchkInputBuffer.readLine()) != null){
											logWriter.println(line+"\n");
											newLine = newLine +  line+"\n";																	
										}
																				
										execParmchkInputBuffer.close();
										
										BufferedReader execParmchkErrorBuffer = new BufferedReader(new InputStreamReader(execParmchk.getErrorStream()));
										while ((line = execParmchkErrorBuffer.readLine()) != null){
											logWriter.println("Letz see, what error do we get during frcmod generation:\n"+line+"\n");
											newLine = newLine +  line+"\n";											
										}
										
										
										execParmchkErrorBuffer.close();
										
										execParmchk.waitFor();											
										//logWriter.println("execParmchk, exitValue() after waitfor:\n"+execParmchk.exitValue());
									} catch (InterruptedException e) {
										JOptionPane.showMessageDialog(null, e.getMessage());
									}
									 																				 
								}catch(IOException e){//this will tell you the exact error									
									JOptionPane.showMessageDialog(null, e.getMessage()); //Display the error
									try{
										//write the log
											 logWriter.println("Following error occured when Parmchk was being executed:\n"+e.getMessage());
										 }finally{//close the file stream
											 logWriter.close();
										 }
								}							
								//Update the activity on Info Area3
								infoArea3.setText(newLine);							
						}							
					}
				});
				panel_4.add(btnRunTleap);
				

//Panel of infoArea3				
				JPanel panel = new JPanel();
				panel.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel.setBounds(10, 80, 747, 479);
				panel_2.add(panel);
				panel.setLayout(null);
				
//Information Area3		
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 5, 727, 463);
				panel.add(scrollPane);
				infoArea3 = new JEditorPane();
				infoArea3.setEditable(false);
				scrollPane.setViewportView(infoArea3);				
								
			}
			
		
			public void updateinfoArea3(){ //method, to display updates of combined infoArea1 and inforArea2
				displayUpdates3 = "File parameters:\n"+displayUpdates+"\n\n"+displayUpdates2;
				infoArea3.setText(displayUpdates3);
			}
}
