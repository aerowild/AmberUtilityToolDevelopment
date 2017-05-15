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
//import java.awt.SystemColor;
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
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import java.lang.Character;


public class AUTD extends JFrame {
	
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
	private JTextField textField_1;
	private JTextField txtModifyMinimization;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	
	//Declarations and initializations for tab3; the minimization tab
	private String getMaxMinIter = "1"; //This will hold the values of maximum serial iterations of minimization
	private String getCurrentIterator = "1"; //To tell for which iterator to set parametrs
	private ArrayList<String> parseNames =new ArrayList<String>(); //to parse the name parameters into minInCreator()
	private ArrayList<String> parseValues = new ArrayList<String>(); //to parse the value the parameters into minInCreator()
	private boolean isConstrainsOn = false;
	private String setConstrains = "";
	private int countSetConstraints = 0;
	private JTextField txtParameterizationCompletion;
	private JPanel panel_7 = new JPanel(); //a new label will be added to this panel according to minimization iteration for which minimization is being set and has been completed
	private String updateIterator = ""; //This will hold the values to display in Panel_7
	private String selectTopologyForMin;
	private String selectCoordinatesForMin;
	private String selectMDengine = "sander.MPI";  //will hold the name of program to run the MD simulations  like sander, pmemd
	private String updateInfoArea4 = "Minimization parameters:\n"; //will hold the text to be displayed on infoArea4
	private String updateParamsOnIF4= "";
	private Process execMinimization; //Here the minimization script will start
	private boolean isTopologyLoaded = false;
	private boolean isCrdLoaded = false;
	private int hasAllParamLoaded[];
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField txtParameterizationCompletedFor;
	private JTextField textField_13;
	private JTextField textField_9;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_26;
	private JTextField textField_29;
	private JTextField textField_24;
	private JTextField textField_27;
	private JTextField textField_28;
	private JTextField textField_31;
	private JTextField textField_32;
	private JTextField textField_33;
	private JTextField textField_34;
	private JTextField textField_35;
	private JTextField textField_36;
	private JTextField textField_37;
	private JTextField textField_39;
	private JTextField textField_40;
	private JTextField textField_41;
	private JTextField textField_42;
	private JTextField textField_43;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AUTD frame = new AUTD();
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
	public AUTD() {
		
			
		saveLigPath.add("Not Yet Selected");
		
		setResizable(false);		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Shashank\\Pictures\\20140923_141154.jpg"));
		setBackground(new Color(0, 0, 0));
		setFont(new Font("Arial Black", Font.BOLD, 12));
		setTitle("Amber Utility Tool (AUT)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 686);
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
//		txtSetOutputFiles.setBackground(SystemColor.controlHighlight);
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
									String returnValStr = file.getName(); //file.getName will hold the name of selected file
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
						
						mainTab.setEnabledAt(3, true); //Now when working directory is set, enable the minimization tab
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
		minization();//calling minimization tab
		mainTab.setEnabledAt(3, false); //disable the minimization tab by default
		
		moleulcarDynamicd(); //calling the MD tab/method
		//mainTab.setEnabledAt(4, false); //disabling the MD tab by default
		
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
										String getLigName = (String) getLigFullName.subSequence(0, (getLigFullName.length() - 5)); //For just name of the ligand without extension
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
				panel.setLayout(new BorderLayout(0, 0));
				
//Information Area3		
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane);
				infoArea3 = new JEditorPane();
				infoArea3.setEditable(false);
				scrollPane.setViewportView(infoArea3);					
				
				
			}
			

						
			public void minization(){//Here Starts the parameterization and execution of minimization
					selectTopologyForMin = choosenDirectory+"/"+getOutputFileName+".prmtop";
					selectCoordinatesForMin = choosenDirectory+"/"+getOutputFileName+".inpcrd";	
					
					
					
					JPanel panel_1 = new JPanel(); //Fourth tab (Minimization) tab of JPanel
					panel_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
					mainTab.addTab("Minimization", null, panel_1, null);
					panel_1.setLayout(null);
					
					JPanel panel = new JPanel();
					panel.setBackground(Color.GRAY);
					panel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
					panel.setBounds(360, 11, 397, 494);
					panel_1.add(panel);
					panel.setLayout(new BorderLayout(0, 0));
					
					JScrollPane scrollPane = new JScrollPane();
					panel.add(scrollPane);
					
//InfoArea4 of tab 4 or minimization tab
					final JTextArea infoArea4 = new JTextArea();
					infoArea4.setEditable(false);
					scrollPane.setViewportView(infoArea4);
					
					final JPanel panel_2 = new JPanel();
					panel_2.setBackground(Color.GRAY);
					panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
					panel_2.setBounds(10, 516, 747, 43);
					panel_1.add(panel_2);
					panel_2.setLayout(null);
					
//Add parameter Panel Button Action:
					final JButton btnAddParameters = new JButton("Add Parameters");
					btnAddParameters.setEnabled(false);
					
//The Kill Min button
					final JButton btnKill = new JButton("kill Min");
					btnKill.setEnabled(false);
					btnKill.setBounds(219, 8, 117, 23);
					panel_2.add(btnKill);
					btnKill.addActionListener(new ActionListener() {//This is to set fucntion of kill button
						public void actionPerformed(ActionEvent arg0) {
						execMinimization.destroy();
						}
					});
										
//run minimization button					
					final JButton btnRunMinimization = new JButton("Run Minimization");
					btnRunMinimization.setEnabled(false);
					btnRunMinimization.addActionListener(new ActionListener() {//When everything is set, this is to run the minimizaton
						//Sould of minimization tab
						
						
						public void actionPerformed(ActionEvent arg0) {
							
							PrintStream writeRunMin = null;
														
							try {//Write runMinimization.sh into minimization directory
								writeRunMin = new PrintStream(new FileOutputStream(choosenDirectory+"/minimization/runMinimization.sh"),true);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,"runMinimization.sh could not be written, may be you did not set the working directory:\n"+e.getMessage());
								e.printStackTrace();
							}
							
							String getRunMinCommands = "export AMBERHOME="+setAMBERHOME+"\n"+
									"export PATH=$PATH:AMBERHOME/bin:$AMBERHOME/AmberTools/bin"+"\n"+
									"cd "+choosenDirectory+"/minimization";//Set the environment variables and also All minimization stuff should run in a seperate folder.
							
							int howManyTimes = Integer.parseInt(getMaxMinIter);
							for(int count = 1; howManyTimes >= count;count++){//This is to store all the commands required to run minimization for all Iterations in one single string, getRunMinCommands
								if(count == 1){//For first minimization use input topology and coordinates
								getRunMinCommands = getRunMinCommands+"\necho Minimization "+count+"\n"+
										selectMDengine+" -O -i Min_"+count+".in -o Min_"+count+".out -p "+selectTopologyForMin+" -c "+selectCoordinatesForMin+" -r Min_"+count+".rst -x Min_"+count+".mdcrd -ref "+selectCoordinatesForMin;
								}else{//For rest, use previous outputs
									getRunMinCommands = getRunMinCommands+"\necho Minimization "+count+"\n"+
										selectMDengine+" -O -i Min_"+count+".in -o Min_"+count+".out -p "+selectTopologyForMin+" -c Min_"+(count-1)+".rst -r Min_"+count+".rst -x Min_"+count+".mdcrd -ref Min_"+(count-1)+".rst";
								}
							}
							
							try{//write the comaands holded in getRunMinCommands String into runMinimization.sh
								writeRunMin.println(getRunMinCommands);							
							}finally{//and close its PrintStream
								writeRunMin.close();
							}
							
							try {//Make runMinimization.sh executable
								Process makeExec = Runtime.getRuntime().exec("chmod +x "+choosenDirectory+"/minimization/runMinimization.sh");
								makeExec.waitFor();
							} catch (IOException | InterruptedException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,"Could not make runMinimization.sh executable:\n"+e.getMessage());
								e.printStackTrace();
							}
							
							//PrintStream writeRunMinLog = null;
							//try {//Write runMinimization.sh log file into minimization directory
								//writeRunMinLog = new PrintStream(new FileOutputStream(choosenDirectory+"/minimization/runMinimization.log"),true);
							//} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								//JOptionPane.showMessageDialog(null,"Log file for minimization could not be written, may be you did not set the working directory:\n"+e.getMessage());
								//e.printStackTrace();
							//}
							
														
										try {//Executerun runMinimization.sh script												
																				
										 execMinimization = Runtime.getRuntime().exec(choosenDirectory+"/minimization/runMinimization.sh");
										 String newLine = "";
										 String line ;
										 //BufferedReader execMinInputBuffer = new BufferedReader(new InputStreamReader(execMinimization.getInputStream()));
										 //while ((line = execMinInputBuffer.readLine()) != null){
										//	 writeRunMinLog.println(line+"\n");
										//	newLine = newLine + line+"\n";	
										//}
										
										// execMinInputBuffer.close();											 
										
										//BufferedReader execMinErrorBuffer = new BufferedReader(new InputStreamReader(execMinimization.getErrorStream()));
										//while ((line = execMinErrorBuffer.readLine()) != null){
										//	writeRunMinLog.println("Letz see, what error do we get during frcmod generation:\n"+line+"\n");
										////	newLine = newLine +  line+"\n";													
										//}
										
										//execMinErrorBuffer.close();
																			
										//logWriter.println("execParmchk, exitValue() after waitfor:\n"+execParmchk.exitValue());
										 			 
									}catch(IOException e){//this will tell you the exact error									
										JOptionPane.showMessageDialog(null, e.getMessage()); //Display the error
										//try{
											//write the log
											//	writeRunMinLog.println("Following error occured when runMinimization.sh was being executed:\n"+e.getMessage());
											 //}finally{//close the file stream
												// writeRunMinLog.close();
											 //}
										}

										do{//enable kill switch when process is running
											btnKill.setEnabled(true);													
								}while (execMinimization.exitValue() != 0);
										btnKill.setEnabled(false);
							JOptionPane.showMessageDialog(null, "Minimization has been started. You can check the minimization directory.\nAlso, information will be updated on Informaton area once process is finished");				
						}
					});
					
					btnRunMinimization.setBounds(10, 8, 199, 23);
					panel_2.add(btnRunMinimization);
					
					JButton btnProceed = new JButton("Proceed");
					btnProceed.setBounds(609, 8, 117, 23);
					panel_2.add(btnProceed);
					
					
					
					JPanel panel_3 = new JPanel();
					panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
					panel_3.setBackground(Color.GRAY);
					panel_3.setBounds(10, 11, 340, 43);
					panel_1.add(panel_3);
					panel_3.setLayout(null);
					
					JButton btnLoadTopology = new JButton("Load Topology");
					btnLoadTopology.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {//Select the topology file
							JFileChooser topolChooser = new JFileChooser(); //This is to select a PDB file
							topolChooser.setAcceptAllFileFilterUsed(true);
							FileNameExtensionFilter topolfilter = new FileNameExtensionFilter("AMBER Topology file","prmtop"); //AMBER Topology Filter: To select only AMBER Topology files				
							topolChooser.setFileFilter(topolfilter);
							int returnVal = topolChooser.showOpenDialog(null);//it opens a file chooser and returnVal will hold 0 or 1, based on selection of file
							if(returnVal == topolChooser.APPROVE_OPTION){//If user select a file
								File topolFile = topolChooser.getSelectedFile(); //File chooser used the File utility of java, hence initializing a file object
								selectTopologyForMin = topolFile.getPath();		
								
								updateInfoArea4 = updateInfoArea4+"\nTopology File: "+selectTopologyForMin;
								infoArea4.setText(updateInfoArea4); //update the inforArea4
								
								isTopologyLoaded = true;
								
								{//enable "Add parameters" button if both the topology and coordinates are loaded
									if(isTopologyLoaded == true &&	isCrdLoaded == true){
										btnAddParameters.setEnabled(true);
									}
									
								}
													
							}else{ //if user cancels the selction oof the file
								JOptionPane.showMessageDialog(null,"User did not select any Topology file");		
								isTopologyLoaded = false;
							}
						}
					});
					btnLoadTopology.setBounds(10, 11, 150, 23);
					panel_3.add(btnLoadTopology);
					
					JButton btnLoadCoordinates = new JButton("Load Coordinates");
					btnLoadCoordinates.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							JFileChooser coordChooser = new JFileChooser(); //This is to select a PDB file
							coordChooser.setAcceptAllFileFilterUsed(true);
							FileNameExtensionFilter coordfilter = new FileNameExtensionFilter("AMBER Coordinates file","inpcrd","crd"); //AMBER Coordinate file Filter: To select only AMBER Topology files				
							coordChooser.setFileFilter(coordfilter);
							int returnVal = coordChooser.showOpenDialog(null);//it opens a file chooser and returnVal will hold 0 or 1, based on selection of file
							if(returnVal == coordChooser.APPROVE_OPTION){//If user select a file
								File coordFile = coordChooser.getSelectedFile(); //File chooser used the File utility of java, hence initializing a file object
								selectCoordinatesForMin = coordFile.getPath();	
								
								updateInfoArea4 = updateInfoArea4+"\nCoordinate File: "+selectCoordinatesForMin;
								infoArea4.setText(updateInfoArea4); //update the inforArea4
								isCrdLoaded = true;
								
								{//enable "Add parameters" button if both the topology and coordinates are loaded
									if(isTopologyLoaded == true &&	isCrdLoaded == true){
										btnAddParameters.setEnabled(true);
									}									
								}
													
							}else{ //if user cancels the selction oof the file
								JOptionPane.showMessageDialog(null,"User did not select any Coordinate file file");			
								isCrdLoaded = false;
							}
						}
					});
					btnLoadCoordinates.setBounds(166, 11, 164, 23);
					panel_3.add(btnLoadCoordinates);
					
									
					JPanel panel_4 = new JPanel();
					panel_4.setBackground(Color.GRAY);
					panel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
					panel_4.setBounds(10, 65, 340, 167);
					panel_1.add(panel_4);
					panel_4.setLayout(null);
					
					JSplitPane splitPane = new JSplitPane();
					splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
					splitPane.setBounds(10, 8, 150, 54);
					panel_4.add(splitPane);
	
					JSplitPane splitPane_1 = new JSplitPane();
					splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
					splitPane_1.setBounds(165, 8, 165, 54);
					panel_4.add(splitPane_1);
					panel_7.setBackground(new Color(255, 255, 204));
	
	//Panel To Display how many iterators have been parameterized:	It has been initialized already at the class level.			
					//a new label will be added to this panel according to minimization iteration for which minimization is being set and has been completed
					panel_7.setForeground(Color.WHITE);
					panel_7.setBounds(10, 102, 320, 54);
					panel_4.add(panel_7);
					panel_7.setLayout(new BorderLayout(0, 0));
					
					JScrollPane scrollPane_1 = new JScrollPane();
					panel_7.add(scrollPane_1);
					final JTextArea label = new JTextArea();
					scrollPane_1.setViewportView(label);
					label.setForeground(new Color(0, 0, 153));
					label.setFont(new Font("Garamond", Font.BOLD, 13));
					label.setBackground(new Color(255, 255, 255));
					label.setEditable(false);
					label.setText("<Pramerized Iterations will be displayed here>");
											
	
	//Drop down menu to select current iterator				
					final JComboBox comboBox = new JComboBox();
					comboBox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							getCurrentIterator = (String) comboBox.getSelectedItem(); //Choose for which iterator of Minimization you want to mess up with	
																												
						}
					});
					splitPane_1.setRightComponent(comboBox);
					
					txtModifyMinimization = new JTextField();
					txtModifyMinimization.setEditable(false);
					txtModifyMinimization.setText("Modify Which?"); 
					splitPane_1.setLeftComponent(txtModifyMinimization);
					txtModifyMinimization.setColumns(10);
	
	
					
					
	//Set max minimization Iterations Panel				
					JButton btnSerializeMinimizations = new JButton("How many times?");
					btnSerializeMinimizations.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {//Ok, I messed up here somewhat. This is a dropDown menu from which user can choose the minimization iteration to modify
							//but choices in the drop down list must be exactly the same as the maximum number of iteractions user wants
							//Hence, this is what I did. I converted the maximum iteration into Int and for those int, created a string which will suite the dropdown menu style
							getMaxMinIter = textField_1.getText();
							int getMaxMinIterLocal = Integer.parseInt(getMaxMinIter);
							String[] setDroplist = new String[getMaxMinIterLocal];
							for(int counter = 0; counter < getMaxMinIterLocal;counter++){
								setDroplist[counter] = ""+(counter+1);							
							}
							comboBox.setModel(new DefaultComboBoxModel(setDroplist));
							comboBox.setSelectedIndex(0);
							
							label.setText("Add Parameters");//When you set maximum number of iterations again, reset the panel_7 to reset the progress display
							updateIterator = "";
							
							updateParamsOnIF4 = ""; //Reset previous parameters
													
							updateInfoArea4 = updateInfoArea4+"\nSystem will be minimized in "+getMaxMinIter+" Iterations.\n";
							infoArea4.setText(updateInfoArea4); //update the inforArea4
							
							String createMinDIR = "mkdir "+choosenDirectory+"/minimization";
							String deleteMinDIR = "rm -rf "+choosenDirectory+"/minimization";
							
							int deleteOldMinFOlder = JOptionPane.showConfirmDialog(null,"Delete previous minimization folders. You might want to take backup of some files?", "Delete minimization folder?",JOptionPane.YES_NO_OPTION);
							String gotWhatTest = ""+deleteOldMinFOlder;
							//JOptionPane.showMessageDialog(null, "You actually got this:\n"+gotWhatTest);
							
							if(deleteOldMinFOlder == 0){
								try{
								Process p = Runtime.getRuntime().exec(deleteMinDIR);	
								p.waitFor();
								}catch(IOException | InterruptedException e1){
									JOptionPane.showMessageDialog(null,"There is no minimization folder it seems"+e1.getMessage());
								}
							}
														
							try {//create new directory to run minimization and move all *.min files into it
								Process mkdir = Runtime.getRuntime().exec(createMinDIR);
								mkdir.waitFor();
							} catch (IOException | InterruptedException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,"Falied to create minimization directory.\nMay be user did not added any parameter for minimization:\n"+e1.getMessage());
								e1.printStackTrace();
							}
							
							hasAllParamLoaded = new int[getMaxMinIterLocal]; //hasAllParamLoaded its length is equals to maximum iterations of min
							for(int count = 0; count >= getMaxMinIterLocal;count++){//This loop will create elements of hasAllParamLoaded ArrayList with value 0 in each, equals to maximum mumber of iterations
								hasAllParamLoaded[count] = 0; //all index of this array has value 0;
							}
						}	
					});
					splitPane.setLeftComponent(btnSerializeMinimizations);
					
					textField_1 = new JTextField();
					textField_1.addActionListener(new ActionListener() {
						//Hence, this is what I did. I converted the maximum iteration into Int and for those int, created a string which will suite the dropdown menu style
						public void actionPerformed(ActionEvent arg0) {//Ok, I messed up here somewhat. This is a dropDown menu from which user can choose the minimization iteration to modify
							//but choices in the drop down list must be exactly the same as the maximum number of iteractions user wants
							//Hence, this is what I did. I converted the maximum iteration into Int and for those int, created a string which will suite the dropdown menu style
							getMaxMinIter = textField_1.getText();
							int getMaxMinIterLocal = Integer.parseInt(getMaxMinIter);
							String[] setDroplist = new String[getMaxMinIterLocal];
							for(int counter = 0; counter < getMaxMinIterLocal;counter++){
								setDroplist[counter] = ""+(counter+1);							
							}
							comboBox.setModel(new DefaultComboBoxModel(setDroplist));
							comboBox.setSelectedIndex(0);
							
							label.setText("Add Parameters"); //= new JPanel();//When you set maximum number of iterations again, reset the panel_7 to reset the progress display
							updateIterator = "";
							
							updateParamsOnIF4 = ""; //Reset previous parameters
							
							updateInfoArea4 = updateInfoArea4+"\nSystem will be minimized in "+getMaxMinIter+" Iterations.\n";
							infoArea4.setText(updateInfoArea4); //update the inforArea4
							
							try {//create new directory to run minimization and move all *.min files into it
								String createMinDIR = "mkdir "+choosenDirectory+"/minimization";
								String deleteMinDIR = "rm -rf "+choosenDirectory+"/minimization";
								int deleteOldMinFOlder = JOptionPane.showConfirmDialog(null,"Delete previous minimization folders. You might want to take backup of some files?", "Delete minimization folder?",JOptionPane.YES_NO_OPTION);
								
								if(deleteOldMinFOlder == 0){
									try{
									Runtime.getRuntime().exec(deleteMinDIR);
									}catch(IOException e1){
										JOptionPane.showMessageDialog(null,"There is no minimization folder it seems"+e1.getMessage());
									}
								}
								
								Runtime.getRuntime().exec(createMinDIR);							
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,"Falied to create minimization directory.\nMay be user did not added any parameter for minimization:\n"+e1.getMessage());
								e1.printStackTrace();
							}
							
							hasAllParamLoaded = new int[getMaxMinIterLocal]; //hasAllParamLoaded its length is equals to maximum iterations of min
							for(int count = 0; count > getMaxMinIterLocal;count++){//This loop will create elements of hasAllParamLoaded ArrayList with value 0 in each, equals to maximum mumber of iterations
								hasAllParamLoaded[count] = 0; //all index of this array has value 0;
							}
							
						}
					});
					splitPane.setRightComponent(textField_1);
					textField_1.setToolTipText("Enter Number of Cycles of Minimizations");
					textField_1.setText("1");
					textField_1.setColumns(10);
					
					txtParameterizationCompletion = new JTextField();
					txtParameterizationCompletion.setBounds(10, 80, 320, 20);
					panel_4.add(txtParameterizationCompletion);
					txtParameterizationCompletion.setEditable(false);
					txtParameterizationCompletion.setText("Parameterization Completed for:");
					txtParameterizationCompletion.setColumns(10);
					
					JPanel panel_5 = new JPanel();
					panel_5.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
					panel_5.setBackground(Color.GRAY);
					panel_5.setBounds(10, 243, 340, 262);
					panel_1.add(panel_5);
					panel_5.setLayout(null);
					
					JPanel panel_6 = new JPanel();
					panel_6.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
					panel_6.setBounds(10, 11, 320, 200);
					panel_5.add(panel_6);
					panel_6.setLayout(null);
					
					JSplitPane splitPane_3 = new JSplitPane();
					splitPane_3.setBounds(10, 11, 183, 25);
					splitPane_3.setToolTipText("The maximum number of cycles of minimization");
					panel_6.add(splitPane_3);
					
					JButton btnMaxcyc = new JButton("maxcyc");
					
					btnMaxcyc.setToolTipText("The maximum number of cycles of minimization");
					splitPane_3.setLeftComponent(btnMaxcyc);
					
					textField_2 = new JTextField();
					textField_2.setText("3000");
					splitPane_3.setRightComponent(textField_2);
					textField_2.setColumns(10);
					
					JSplitPane splitPane_2 = new JSplitPane();
					splitPane_2.setBounds(10, 36, 183, 25);
					splitPane_2.setToolTipText("Swith from steepest descent to conjugate gragient after NCYC cycles");
					panel_6.add(splitPane_2);
					
					textField_4 = new JTextField();
					textField_4.setToolTipText("Swith from steepest descent to conjugate gragient after NCYC cycles");
					textField_4.setText("2000");
					splitPane_2.setRightComponent(textField_4);
					textField_4.setColumns(10);
					
					JButton btnNcyc = new JButton("ncyc");
					btnNcyc.setToolTipText("Swith from steepest descent to conjugate gragient after NCYC cycles");
					splitPane_2.setLeftComponent(btnNcyc);
					
					JSplitPane splitPane_4 = new JSplitPane();
					splitPane_4.setToolTipText("Methhod of Minimization:\r\n0: Full conjugate gradient minimization.\r\n1: For NCYC cycles the steepest descent method is used then conjugate gradient is switched on (default).\r\n2: Full steepest descent methods.\r\n3: The XMIN method is used (Not implemented here.)\r\n4: The LMOD method is used (not implemented here.)");
					splitPane_4.setBounds(10, 62, 183, 25);
					panel_6.add(splitPane_4);
					
					JButton btnNtmin = new JButton("ntmin");
					btnNtmin.setToolTipText("Methhod of Minimization:");
					splitPane_4.setLeftComponent(btnNtmin);
					
					final JComboBox comboBox_1 = new JComboBox();
					comboBox_1.setToolTipText("Methhod of Minimization:");
					comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "0", "2"}));
					splitPane_4.setRightComponent(comboBox_1);
					
					JSplitPane splitPane_5 = new JSplitPane();
					splitPane_5.setBounds(10, 86, 183, 25);
					panel_6.add(splitPane_5);
					
					JButton btnDrms = new JButton("drms");
					btnDrms.setToolTipText("The convergence criterion for the energy gradient: \r\nMinimization will halt when RMS of the Cartesian elements of the gradient is less than DRMS. Default is 1.0E-4kcal/mol-Angstrom");
					splitPane_5.setLeftComponent(btnDrms);
					
					textField_5 = new JTextField();
					textField_5.setText("0.0001");
					splitPane_5.setRightComponent(textField_5);
					textField_5.setColumns(10);
					
					JRadioButton rdbtnEnableConstraints = new JRadioButton("Enable Restraints");
					rdbtnEnableConstraints.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {						
							if(countSetConstraints%2 == 0){//swith is on and will launch a popup window to set the constrains
								isConstrainsOn = true;
								JTextArea getConstrains = new JTextArea("Minimization:Don Not Delete this line\n<Delete this line and write restrained conditions in AMBER FORMAT>"); //create an text area to get commands
								JScrollPane scroller = new JScrollPane(getConstrains); //Create an scrollable bar and paste textarea into it				
								JOptionPane.showMessageDialog(null,  scroller); //Finally, paste scroll are to an popup show message dialog box
							setConstrains = getConstrains.getText();						
							countSetConstraints++;		
							}else{//switch is off
								isConstrainsOn = false;							
								countSetConstraints++;	
							}
	
						}
					});
					rdbtnEnableConstraints.setBounds(10, 118, 183, 23);
					panel_6.add(rdbtnEnableConstraints);

//MD engine selector panel starts here	
					JSplitPane splitPane_6 = new JSplitPane();
					splitPane_6.setBounds(10, 222, 320, 30);
					panel_5.add(splitPane_6);
					
					textField_6 = new JTextField();
					textField_6.setText("Run With:");
					textField_6.setEditable(false);
					textField_6.setColumns(10);
					splitPane_6.setLeftComponent(textField_6);
					
					final JComboBox comboBox_2 = new JComboBox();
					
//Add parameter Panel Button Action:
						btnAddParameters.addActionListener(new ActionListener() {//This will call minInCreator() and supply parameters to it and create a min file
						public void actionPerformed(ActionEvent arg0) {
							
							parseNames.clear(); // that so values are not added contineously into array
							parseValues.clear(); // that so values are not added contineously into array
							parseNames.add(getCurrentIterator); //This is to set the name of min file and will be chaning based on the selection of Min Itrerator number by user, hence for each iterator there will be a different min file
							parseValues.add(textField_2.getText()); //This holds the value for maxcyc
							parseValues.add(textField_4.getText()); //This holds the value for ncyc
							parseValues.add((String) comboBox_1.getSelectedItem()); //Method of minimization
							parseValues.add(textField_5.getText()); //This holds the value for drms
							
							//Note: from here, values in parseNames and parseValues become equal. From now, you will have to define the items for both
							minInCreator(parseNames, parseValues,setConstrains);
													
							updateIterator = updateIterator+" "+getCurrentIterator;
							label.setText(updateIterator);
																					
							updateParamsOnIF4 = "\nMin.in written for "+getCurrentIterator+" Iteration and parameters are:\n"+
										"maxcyc = "+textField_2.getText()+"\n"+
										"ncyc = "+textField_4.getText()+"\n"+
										"ntmin = "+comboBox_1.getSelectedItem()+"\n"+
										"drms = "+textField_5.getText()+"\n"+
												"Constrains: "+setConstrains+"\n"; 
							
							updateInfoArea4 = updateInfoArea4+updateParamsOnIF4;
							infoArea4.setText(updateInfoArea4); //update the inforArea4
							
							//Follwoing logic is to check that parameters has been set for all iteration, and if so, enable the "run With" botton
							
							int getCurrentIteratorInt = Integer.parseInt(getCurrentIterator); //change the iterator number into int
							hasAllParamLoaded[getCurrentIteratorInt-1]=1; //set value 1 for iterator number index of hasAllParamLoaded array
														
							boolean truFalse = true; //set a variable with true value
							for(int curIndValue:hasAllParamLoaded){								
								if(curIndValue == 1){ //if value of current index is 1, truFalse will become oldValue+true
									truFalse = truFalse && true;									
								}else{//else, old value + flase, means if any of the value of array "hasAllParamLoaded" is 0, final value of truFalse will be 0
									truFalse = truFalse && false;
								}
							}
							if(truFalse == true){//if final value of truFalse is true, enable the run with button
								comboBox_2.setEnabled(true); //enable the engine selector
							}else{
								comboBox_2.setEnabled(false); //enable the engine selector
							}
							
						}
					});
					btnAddParameters.setBounds(158, 164, 150, 23);
					panel_6.add(btnAddParameters);
											
//MD engine selector panel continuation					
					comboBox_2.setEnabled(false);
					comboBox_2.addActionListener(new ActionListener() {//This will set the MPI engine, number of processors and MD engine
						String mpiString = "";
						public void actionPerformed(ActionEvent arg0) {
							String getEngine = (String) comboBox_2.getSelectedItem();
							if(getEngine.equals("sander.MPI") || getEngine.equals("pmemd.MPI") || getEngine.equals("sander.LES.MPI")){
								JOptionPane getMPIEngine = new JOptionPane();
								mpiString =getMPIEngine.showInputDialog("Enter MPI command of your system:","mpiexec -n 2");						
							}else{
								mpiString = "";
							}
							selectMDengine = mpiString+" "+getEngine;	
							
							btnRunMinimization.setEnabled(true);//now when everything seems to be set, enable run minimization tab.
						}
					});
					comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"sander.MPI", "sander", "pmemd", "pmemd.cuda", "pmemd.MPI", "pmemd.cuda.MPI", "sander.LES", "sander.LES.MPI", "pmemd.amoeba", "pmemd.amoeba.MPI", "pmemd.cuda_SPFP"}));
					splitPane_6.setRightComponent(comboBox_2);	
				}

			
			public void updateinfoArea3(){ //method, to display updates of combined infoArea1 and inforArea2
				displayUpdates3 = "File parameters:\n"+displayUpdates+"\n\n"+displayUpdates2;
				infoArea3.setText(displayUpdates3);
			}
			
			public void minInCreator(ArrayList<String> parametersName,ArrayList<String> parametersValue,String setConstrains){ //parameters_name will hold the names of parameters while parameters_value will hold respective values. parametersValue will get values from outside while parametersName has some default names
				PrintStream writeMinIn = null;
				try{//Write a min_<dynamic>.in file. <dynamic> will be changed according to user input from "modify which" button". Value will fo to parametersName.get(0).
					writeMinIn = new PrintStream(new FileOutputStream(choosenDirectory+"/minimization/Min_"+parametersName.get(0)+".in"));
				}catch(FileNotFoundException e)
				{
					JOptionPane.showMessageDialog(null,"May be you forgot to set working directory? See the error following:\n"+ e.getMessage()); //This will display the error to end user
					e.printStackTrace();
				}
				
				parametersName.add("maxcyc"); //1st index of parameters_name	
				parametersName.add("ncyc"); //2st index of parameters_name		
				parametersName.add("ntmin"); //3st index of parameters_name		
				parametersName.add("drms"); //4st index of parameters_name		
				
				writeMinIn.println("minimization:"+"\n"+"&cntrl"+"\n"+"imin = 1,");
				int nameIndex = 1;
				for  (String setParamValue: parametersValue){
					writeMinIn.println(parametersName.get(nameIndex)+"="+setParamValue+",");
					nameIndex++;
				}
				writeMinIn.println("/");
				if(isConstrainsOn == true){
					writeMinIn.println(setConstrains);
				}
				{//at last close the PrintStream
				writeMinIn.close();
				}
			}

			public void moleulcarDynamicd(){//Parameterization and execution of Molecular Dynamics//heating, equilibration, MD at NVE and NPT
				//////////////////////////////////////////////////////////////////////Deefine GUI elements from here///////////////////////////////////////////////////////
				JPanel panel_8 = new JPanel(); //Panel of MD tab
				mainTab.addTab("MD", null, panel_8, null);
				panel_8.setLayout(new BorderLayout(0, 0));
				
				JPanel panel = new JPanel();
				panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_8.add(panel);
				panel.setLayout(null);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.GRAY);
				panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_1.setBounds(452, 13, 303, 489);
				panel.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane = new JScrollPane();
				panel_1.add(scrollPane);
				
				JTextArea textArea = new JTextArea();
				scrollPane.setViewportView(textArea);
				
				JPanel panel_2 = new JPanel();
				panel_2.setLayout(null);
				panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_2.setBackground(Color.GRAY);
				panel_2.setBounds(16, 13, 426, 43);
				panel.add(panel_2);
				
				JButton button = new JButton("Load Topology");				
				button.setBounds(12, 11, 173, 23);
				panel_2.add(button);
				
				JButton button_1 = new JButton("Load Coordinates");
				button_1.setBounds(232, 11, 184, 23);
				panel_2.add(button_1);
				
				JPanel panel_4 = new JPanel();
				panel_4.setLayout(null);
				panel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_4.setBackground(Color.GRAY);
				panel_4.setBounds(16, 59, 426, 148);
				panel.add(panel_4);
				
				JSplitPane splitPane = new JSplitPane();
				splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
				splitPane.setBounds(10, 8, 171, 52);
				panel_4.add(splitPane);
				
				JButton button_5 = new JButton("How many times?");
				splitPane.setLeftComponent(button_5);
				
				textField_7 = new JTextField();
				textField_7.setToolTipText("Enter Number of Cycles of Minimizations");
				textField_7.setText("1");
				textField_7.setColumns(10);
				splitPane.setRightComponent(textField_7);
				
				JSplitPane splitPane_1 = new JSplitPane();
				splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
				splitPane_1.setBounds(233, 8, 183, 52);
				panel_4.add(splitPane_1);
				
				textField_8 = new JTextField();
				textField_8.setText("Modify Which?");
				textField_8.setEditable(false);
				textField_8.setColumns(10);
				splitPane_1.setLeftComponent(textField_8);
				
				JComboBox comboBox = new JComboBox();
				splitPane_1.setRightComponent(comboBox);
				
				JPanel panel_5 = new JPanel();
				panel_5.setForeground(Color.WHITE);
				panel_5.setBackground(new Color(255, 255, 204));
				panel_5.setBounds(10, 91, 406, 52);
				panel_4.add(panel_5);
				panel_5.setLayout(new BorderLayout(0, 0));
				
				JScrollPane scrollPane_1 = new JScrollPane();
				panel_5.add(scrollPane_1);
				
				JTextArea textArea_1 = new JTextArea();
				textArea_1.setEditable(false);
				scrollPane_1.setViewportView(textArea_1);
				
				txtParameterizationCompletedFor = new JTextField();
				txtParameterizationCompletedFor.setText("Parameterization Completed for:");
				txtParameterizationCompletedFor.setEditable(false);
				txtParameterizationCompletedFor.setColumns(10);
				txtParameterizationCompletedFor.setBounds(10, 69, 406, 20);
				panel_4.add(txtParameterizationCompletedFor);
				
				JPanel panel_6 = new JPanel();
				panel_6.setLayout(null);
				panel_6.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_6.setBackground(Color.GRAY);
				panel_6.setBounds(16, 212, 426, 403);
				panel.add(panel_6);
				
				JPanel panel_9 = new JPanel();
				panel_9.setToolTipText("");
				panel_9.setLayout(null);
				panel_9.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_9.setBounds(10, 13, 406, 379);
				panel_6.add(panel_9);
				
				JSplitPane splitPane_2 = new JSplitPane();
				splitPane_2.setBounds(10, 13, 160, 27);
				panel_9.add(splitPane_2);
				
				JLabel lblPresets = new JLabel("Presets");
				splitPane_2.setLeftComponent(lblPresets);
				
				JComboBox comboBox_1 = new JComboBox();
				comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Manual", "Heating", "Equillibration at NPT", "MD at NPT", "MD at NVE", "QM/MM", "GB/SA"}));
				splitPane_2.setRightComponent(comboBox_1);
				
				JRadioButton rdbtnIrest = new JRadioButton("irest");
				rdbtnIrest.setToolTipText("Flag to restart a simulation.\r\n= 0 (default) Do not restart the simulation; instead, run as a new simulation. Ve-\r\nlocities in the input coordinate file, if any, will be ignored, and the time step\r\ncount will be set to 0 (unless overridden by t; see below).\r\n= 1 Restart the simulation, reading coordinates and velocities from a previously\r\nsaved restart file. The velocity information is necessary when restarting, so\r\nntx (see above) must be 4 or higher if irest = 1.\r\n(Enabled is 1 and disabled is 0)");
				rdbtnIrest.setBounds(178, 13, 63, 25);
				panel_9.add(rdbtnIrest);
				
				JSplitPane splitPane_4 = new JSplitPane();
				splitPane_4.setBounds(249, 13, 116, 27);
				panel_9.add(splitPane_4);
				
				JLabel lblNtx = new JLabel("ntx");
				lblNtx.setToolTipText("Option to read the initial coordinates, velocities and box size from the inpcrd file.\r\nEither 1 or 2 must be used when one is starting from minimized or model-built\r\ncoordinates. If an MD restrt file is used as inpcrd, then options 4-7 may be used.\r\nOnly options 1 and 5 are in common use.\r\n= 1 (default) Coordinates, but no velocities, will be read; a formatted (ASCII) co-\r\nordinate file is expected.\r\n= 2 Coordinates, but no velocities, will be read; an unformatted (binary) coordi-\r\nnate file is expected.\r\n= 4 Coordinates and velocities will be read; an unformatted coordinate file is ex-\r\npected.\r\n= 5 Coordinates and velocities will be read; a formatted (ASCII) coordinate file is\r\nexpected. Box information will be read if ntb > 0. The velocity information\r\nwill only be used if irest = 1 (see below).\r\n= 6 Coordinates, velocities and box information will be read; an unformatted co-\r\nordinate file is expected.\r\n");
				splitPane_4.setLeftComponent(lblNtx);
				
				JComboBox comboBox_4 = new JComboBox();
				comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
				splitPane_4.setRightComponent(comboBox_4);
				
				JPanel panel_3 = new JPanel();
				panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Output Controls", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_3.setBounds(274, 171, 122, 197);
				panel_9.add(panel_3);
				panel_3.setLayout(null);
				
				JSplitPane splitPane_9 = new JSplitPane();
				splitPane_9.setBounds(12, 157, 101, 27);
				panel_3.add(splitPane_9);
				
				textField_15 = new JTextField();
				textField_15.setText("2000");
				splitPane_9.setRightComponent(textField_15);
				textField_15.setColumns(10);
				
				JLabel lblNtwe = new JLabel("ntwe");
				splitPane_9.setLeftComponent(lblNtwe);
				
				JSplitPane splitPane_8 = new JSplitPane();
				splitPane_8.setBounds(12, 130, 101, 27);
				panel_3.add(splitPane_8);
				
				textField_14 = new JTextField();
				textField_14.setText("5000");
				splitPane_8.setRightComponent(textField_14);
				textField_14.setColumns(10);
				
				JLabel lblNtwx = new JLabel("ntwx");
				lblNtwx.setToolTipText("Every ntwx steps, the coordinates will be written to the mdcrd file");
				splitPane_8.setLeftComponent(lblNtwx);
				
				JSplitPane splitPane_7 = new JSplitPane();
				splitPane_7.setBounds(12, 101, 101, 27);
				panel_3.add(splitPane_7);
				
				textField_12 = new JTextField();
				textField_12.setText("500");
				splitPane_7.setRightComponent(textField_12);
				textField_12.setColumns(10);
				
				JLabel lblNtwr = new JLabel("ntwr");
				lblNtwr.setToolTipText("Every ntwr steps during dynamics, the \u201Crestrt\u201D file will be written");
				splitPane_7.setLeftComponent(lblNtwr);
				
				JSplitPane splitPane_5 = new JSplitPane();
				splitPane_5.setBounds(12, 73, 101, 27);
				panel_3.add(splitPane_5);
				
				textField_11 = new JTextField();
				textField_11.setText("50");
				splitPane_5.setRightComponent(textField_11);
				textField_11.setColumns(10);
				
				JLabel lblNtpr = new JLabel("ntpr");
				lblNtpr.setToolTipText("very ntpr steps, energy information will be printed in human-readable form to");
				splitPane_5.setLeftComponent(lblNtpr);
				
				JSplitPane splitPane_11 = new JSplitPane();
				splitPane_11.setBounds(12, 47, 101, 27);
				panel_3.add(splitPane_11);
				
				JLabel lblDt = new JLabel("dt");
				lblDt.setToolTipText("The time step (psec). Recommended MAXIMUM is .002 if SHAKE is used, or");
				splitPane_11.setLeftComponent(lblDt);
				
				textField_17 = new JTextField();
				textField_17.setText(".002");
				splitPane_11.setRightComponent(textField_17);
				textField_17.setColumns(10);
				
				JSplitPane splitPane_10 = new JSplitPane();
				splitPane_10.setBounds(12, 20, 101, 27);
				panel_3.add(splitPane_10);
				
				JLabel lblNstlim = new JLabel("nstlim");
				lblNstlim.setToolTipText("Number of MD-steps to be performed");
				splitPane_10.setLeftComponent(lblNstlim);
				
				textField_16 = new JTextField();
				textField_16.setText("10000");
				splitPane_10.setRightComponent(textField_16);
				textField_16.setColumns(10);
				
				JPanel panel_10 = new JPanel();
				panel_10.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Temperature", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_10.setBounds(153, 171, 122, 197);
				panel_9.add(panel_10);
				panel_10.setLayout(null);
				
				JSplitPane splitPane_16 = new JSplitPane();
				splitPane_16.setBounds(10, 138, 104, 25);
				panel_10.add(splitPane_16);
				
				JLabel lblGammaln = new JLabel("gamma_ln");
				lblGammaln.setToolTipText("The collision frequency \u03B3, in ps\u22121 , when ntt = 3");
				splitPane_16.setLeftComponent(lblGammaln);
				
				textField_22 = new JTextField();
				textField_22.setText("3");
				splitPane_16.setRightComponent(textField_22);
				textField_22.setColumns(10);
				
				JSplitPane splitPane_17 = new JSplitPane();
				splitPane_17.setBounds(10, 161, 104, 25);
				panel_10.add(splitPane_17);
				
				JLabel lblVlimit = new JLabel("vlimit");
				lblVlimit.setToolTipText("If not equal to 0.0, then any component of the velocity that is greater than abs(VLIMIT)");
				splitPane_17.setLeftComponent(lblVlimit);
				
				textField_23 = new JTextField();
				textField_23.setText("20");
				splitPane_17.setRightComponent(textField_23);
				textField_23.setColumns(10);
				
				JSplitPane splitPane_15 = new JSplitPane();
				splitPane_15.setBounds(10, 115, 104, 25);
				panel_10.add(splitPane_15);
				
				JLabel lblTautp = new JLabel("tautp");
				lblTautp.setToolTipText("Time constant, in ps, for heat bath coupling for the system, if ntt = 1");
				splitPane_15.setLeftComponent(lblTautp);
				
				textField_21 = new JTextField();
				textField_21.setText("1");
				splitPane_15.setRightComponent(textField_21);
				textField_21.setColumns(10);
				
				JSplitPane splitPane_3 = new JSplitPane();
				splitPane_3.setBounds(10, 89, 104, 27);
				panel_10.add(splitPane_3);
				
				JLabel lblIg = new JLabel("ig");
				lblIg.setToolTipText("The seed for the pseudo-random number generator. The MD starting velocity is dependent on the random number generator seed if NTX .lt. 3 .and. TEMPI .ne.\r\n0.0. The value of this seed also affects the set of pseudo-random values used for\r\nLangevin dynamics or Andersen coupling, and hence should be set to a different\r\nvalue on each restart if ntt = 2 or 3. Default 71277. If ig=-1, the random seed will\r\nbe based on the current date and time, and hence will be different for every run.\r\nIt is recommended that, unless you specifically desire reproducibility, that you set\r\n ig=-1 for all runs involving ntt=2 or 3.");
				splitPane_3.setLeftComponent(lblIg);
				
				textField_9 = new JTextField();
				textField_9.setText("-1");
				splitPane_3.setRightComponent(textField_9);
				textField_9.setColumns(10);
				
				JSplitPane splitPane_14 = new JSplitPane();
				splitPane_14.setBounds(10, 65, 104, 25);
				panel_10.add(splitPane_14);
				
				JLabel lblTemp = new JLabel("temp0");
				lblTemp.setToolTipText("Initial temperature");
				splitPane_14.setLeftComponent(lblTemp);
				
				textField_20 = new JTextField();
				textField_20.setText("300");
				splitPane_14.setRightComponent(textField_20);
				textField_20.setColumns(10);
				
				JSplitPane splitPane_13 = new JSplitPane();
				splitPane_13.setBounds(10, 40, 104, 25);
				panel_10.add(splitPane_13);
				
				JLabel lblTempi = new JLabel("tempi");
				lblTempi.setToolTipText("Reference temperature at which the system is to be kept, if ntt > 0.");
				splitPane_13.setLeftComponent(lblTempi);
				
				textField_19 = new JTextField();
				textField_19.setText("300");
				splitPane_13.setRightComponent(textField_19);
				textField_19.setColumns(10);
				
				JSplitPane splitPane_12 = new JSplitPane();
				splitPane_12.setBounds(10, 17, 104, 25);
				panel_10.add(splitPane_12);
				
				JLabel lblNtt = new JLabel("ntt");
				lblNtt.setToolTipText("Switch for temperature scaling. ntt=0 corresponds to the micro-canonical (NVE)");
				splitPane_12.setLeftComponent(lblNtt);
				
				JComboBox comboBox_11 = new JComboBox();
				comboBox_11.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3"}));
				splitPane_12.setRightComponent(comboBox_11);
				
				JPanel panel_11 = new JPanel();
				panel_11.setLayout(null);
				panel_11.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pressure", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_11.setBounds(274, 53, 122, 122);
				panel_9.add(panel_11);
				
				JSplitPane splitPane_20 = new JSplitPane();
				splitPane_20.setBounds(10, 65, 104, 25);
				panel_11.add(splitPane_20);
				
				JLabel lblTaup = new JLabel("taup");
				lblTaup.setToolTipText("Pressure relaxation time (in ps), when NTP > 0. The recommended value is be-");
				splitPane_20.setLeftComponent(lblTaup);
				
				textField_26 = new JTextField();
				textField_26.setText("1");
				textField_26.setColumns(10);
				splitPane_20.setRightComponent(textField_26);
				
				JSplitPane splitPane_23 = new JSplitPane();
				splitPane_23.setBounds(10, 40, 104, 25);
				panel_11.add(splitPane_23);
				
				JLabel lblPres = new JLabel("pres0");
				lblPres.setToolTipText("Reference pressure (in units of bars, where 1 bar \u2248 0.987 atm) at which the system");
				splitPane_23.setLeftComponent(lblPres);
				
				textField_29 = new JTextField();
				textField_29.setText("1");
				textField_29.setColumns(10);
				splitPane_23.setRightComponent(textField_29);
				
				JSplitPane splitPane_24 = new JSplitPane();
				splitPane_24.setBounds(10, 17, 104, 25);
				panel_11.add(splitPane_24);
				
				JLabel lblNtp = new JLabel("ntp");
				lblNtp.setToolTipText("Flag for constant pressure dynamics.");
				splitPane_24.setLeftComponent(lblNtp);
				
				JComboBox comboBox_12 = new JComboBox();
				comboBox_12.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3"}));
				splitPane_24.setRightComponent(comboBox_12);
				
				JSplitPane splitPane_18 = new JSplitPane();
				splitPane_18.setBounds(10, 88, 102, 25);
				panel_11.add(splitPane_18);
				
				JLabel lblComp = new JLabel("comp");
				lblComp.setToolTipText("comp ");
				splitPane_18.setLeftComponent(lblComp);
				
				textField_24 = new JTextField();
				textField_24.setText("44.6");
				splitPane_18.setRightComponent(textField_24);
				textField_24.setColumns(10);
				
				JPanel panel_12 = new JPanel();
				panel_12.setBorder(new TitledBorder(null, "Shake", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_12.setBounds(10, 53, 97, 82);
				panel_9.add(panel_12);
				panel_12.setLayout(null);
				
				JSplitPane splitPane_19 = new JSplitPane();
				splitPane_19.setBounds(10, 21, 77, 25);
				panel_12.add(splitPane_19);
				
				JLabel lblNtc = new JLabel("ntc");
				lblNtc.setToolTipText("Flag for SHAKE to perform bond length constraints");
				splitPane_19.setLeftComponent(lblNtc);
				
				JComboBox comboBox_13 = new JComboBox();
				comboBox_13.setModel(new DefaultComboBoxModel(new String[] {"2", "1", "3"}));
				splitPane_19.setRightComponent(comboBox_13);
				
				JSplitPane splitPane_21 = new JSplitPane();
				splitPane_21.setEnabled(false);
				splitPane_21.setBounds(10, 51, 70, 25);
				panel_12.add(splitPane_21);
				
				JLabel lblJfastw = new JLabel("jfastw");
				lblJfastw.setEnabled(false);
				splitPane_21.setLeftComponent(lblJfastw);
				
				textField_27 = new JTextField();
				textField_27.setEnabled(false);
				splitPane_21.setRightComponent(textField_27);
				textField_27.setColumns(10);
				
				JPanel panel_13 = new JPanel();
				panel_13.setToolTipText("Surface tension regulation");
				panel_13.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Surface Tension", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_13.setBounds(117, 51, 158, 109);
				panel_9.add(panel_13);
				panel_13.setLayout(null);
				
				JSplitPane splitPane_26 = new JSplitPane();
				splitPane_26.setBounds(10, 73, 138, 25);
				panel_13.add(splitPane_26);
				
				JLabel lblNinterface = new JLabel("ninterface");
				lblNinterface.setToolTipText("Number of interfaces in the periodic box. There must be at least two interfaces in");
				splitPane_26.setLeftComponent(lblNinterface);
				
				textField_31 = new JTextField();
				textField_31.setText("2");
				splitPane_26.setRightComponent(textField_31);
				textField_31.setColumns(10);
				
				JSplitPane splitPane_25 = new JSplitPane();
				splitPane_25.setBounds(10, 48, 138, 25);
				panel_13.add(splitPane_25);
				
				JLabel lblGammaten = new JLabel("gamma_ten");
				lblGammaten.setToolTipText("Surface tension value in units of dyne/cm. Default value is 0.0 dyne/cm.");
				splitPane_25.setLeftComponent(lblGammaten);
				
				textField_28 = new JTextField();
				textField_28.setText("0");
				splitPane_25.setRightComponent(textField_28);
				textField_28.setColumns(10);
				
				JSplitPane splitPane_22 = new JSplitPane();
				splitPane_22.setBounds(10, 23, 138, 25);
				panel_13.add(splitPane_22);
				
				JLabel lblCsurften = new JLabel("csurften");
				lblCsurften.setToolTipText("Flag for constant surface tension dynamics.");
				splitPane_22.setLeftComponent(lblCsurften);
				
				JComboBox comboBox_3 = new JComboBox();
				comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3"}));
				splitPane_22.setRightComponent(comboBox_3);
				
				JPanel panel_18 = new JPanel();
				panel_18.setLayout(null);
				panel_18.setBorder(new TitledBorder(null, "Potential Function", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_18.setBounds(10, 167, 146, 201);
				panel_9.add(panel_18);
				
				JRadioButton radioButton = new JRadioButton("amoeba");
				radioButton.setToolTipText("Disabled: \"iamoeba\":");
				radioButton.setEnabled(false);
				radioButton.setBounds(10, 176, 132, 23);
				panel_18.add(radioButton);
				
				JRadioButton radioButton_1 = new JRadioButton("RISM");
				radioButton_1.setToolTipText("\"irism\". Flag for 3d -reference interaction site model molecular solvation. Disabled for now");
				radioButton_1.setEnabled(false);
				radioButton_1.setBounds(10, 150, 132, 23);
				panel_18.add(radioButton_1);
				
				JRadioButton radioButton_2 = new JRadioButton("GB/SA");
				radioButton_2.setBounds(10, 124, 132, 23);
				panel_18.add(radioButton_2);
				
				JRadioButton radioButton_3 = new JRadioButton("QM/MM");
				radioButton_3.setToolTipText("\"ifqnt\" you must also include a &qmmm namelist");
				radioButton_3.setBounds(10, 98, 132, 23);
				panel_18.add(radioButton_3);
				
				JRadioButton radioButton_4 = new JRadioButton("Polarized Potential");
				radioButton_4.setToolTipText("\"ipol\" to use a polarizable force field");
				radioButton_4.setBounds(10, 72, 132, 23);
				panel_18.add(radioButton_4);
				
				JSplitPane splitPane_44 = new JSplitPane();
				splitPane_44.setBounds(10, 44, 132, 25);
				panel_18.add(splitPane_44);
				
				JLabel label = new JLabel("cut");
				label.setToolTipText("This is used to specify the nonbonded cutoff, in Angstroms");
				splitPane_44.setLeftComponent(label);
				
				textField_43 = new JTextField();
				textField_43.setText("9");
				textField_43.setColumns(10);
				splitPane_44.setRightComponent(textField_43);
				
				JSplitPane splitPane_45 = new JSplitPane();
				splitPane_45.setBounds(10, 21, 132, 25);
				panel_18.add(splitPane_45);
				
				JLabel label_1 = new JLabel("ntf");
				label_1.setToolTipText("Force evaluation");
				splitPane_45.setLeftComponent(label_1);
				
				JComboBox comboBox_10 = new JComboBox();
				comboBox_10.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
				splitPane_45.setRightComponent(comboBox_10);
				
				JButton btnAddExtraParameters = new JButton("Add Extra Parameters");
								btnAddExtraParameters.setBounds(602, 515, 153, 23);
				panel.add(btnAddExtraParameters);
				btnAddExtraParameters.setEnabled(false);
				
				JSplitPane splitPane_6 = new JSplitPane();
				splitPane_6.setOrientation(JSplitPane.VERTICAL_SPLIT);
				splitPane_6.setBounds(452, 551, 153, 55);
				panel.add(splitPane_6);
				
				textField_13 = new JTextField();
				textField_13.setText("Run With:");
				textField_13.setEditable(false);
				textField_13.setColumns(10);
				splitPane_6.setLeftComponent(textField_13);
				
				JComboBox comboBox_2 = new JComboBox();
				comboBox_2.setEnabled(false);
				splitPane_6.setRightComponent(comboBox_2);
				
				JButton btnRunMd = new JButton("Run MD");
				btnRunMd.setBounds(614, 557, 141, 23);
				panel.add(btnRunMd);
				btnRunMd.setEnabled(false);
				
				JButton btnKillMd = new JButton("kill MD");
				btnKillMd.setBounds(614, 583, 141, 23);
				panel.add(btnKillMd);
				btnKillMd.setEnabled(false);
				
				JRadioButton rdbtnEnableRestraints = new JRadioButton("Enable Restraints");
				rdbtnEnableRestraints.setBounds(452, 515, 140, 23);
				panel.add(rdbtnEnableRestraints);
				
				JPanel panel_14 = new JPanel();
				panel_14.setToolTipText("For iterative methods (indmeth<3), this is the maximum number of iterations al-");
				mainTab.addTab("New tab", null, panel_14, null);
				panel_14.setLayout(null);
				
				JPanel panel_16 = new JPanel();
				panel_16.setBorder(new TitledBorder(null, "GB/SA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_16.setBounds(10, 368, 104, 231);
				panel_14.add(panel_16);
				panel_16.setLayout(null);
				
				JSplitPane splitPane_28 = new JSplitPane();
				splitPane_28.setBounds(10, 21, 87, 25);
				panel_16.add(splitPane_28);
				
				JLabel lblIgb = new JLabel("igb");
				lblIgb.setToolTipText("generalized Born ");
				splitPane_28.setLeftComponent(lblIgb);
				
				JComboBox comboBox_5 = new JComboBox();
				comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
				splitPane_28.setRightComponent(comboBox_5);
				
				JSplitPane splitPane_29 = new JSplitPane();
				splitPane_29.setBounds(10, 47, 87, 25);
				panel_16.add(splitPane_29);
				
				JLabel lblExtdiel = new JLabel("extdiel");
				lblExtdiel.setToolTipText("Sets the exterior or solvent dielectric constant. Default is 78.5.");
				splitPane_29.setLeftComponent(lblExtdiel);
				
				textField_32 = new JTextField();
				textField_32.setText("78.5");
				splitPane_29.setRightComponent(textField_32);
				textField_32.setColumns(10);
				
				JSplitPane splitPane_30 = new JSplitPane();
				splitPane_30.setBounds(10, 72, 87, 25);
				panel_16.add(splitPane_30);
				
				JLabel lblSaltcon = new JLabel("saltcon");
				lblSaltcon.setToolTipText("Sets the concentration (M) of 1-1 mobile counterions in solution, using a modified");
				splitPane_30.setLeftComponent(lblSaltcon);
				
				textField_33 = new JTextField();
				textField_33.setText("0.0");
				splitPane_30.setRightComponent(textField_33);
				textField_33.setColumns(10);
				
				JSplitPane splitPane_31 = new JSplitPane();
				splitPane_31.setBounds(10, 98, 87, 25);
				panel_16.add(splitPane_31);
				
				JLabel lblRgbmax = new JLabel("rgbmax");
				lblRgbmax.setToolTipText("This parameter controls the maximum distance between atom pairs that will be");
				splitPane_31.setLeftComponent(lblRgbmax);
				
				textField_34 = new JTextField();
				textField_34.setText("25");
				splitPane_31.setRightComponent(textField_34);
				textField_34.setColumns(10);
				
				JSplitPane splitPane_32 = new JSplitPane();
				splitPane_32.setBounds(10, 122, 87, 25);
				panel_16.add(splitPane_32);
				
				JLabel lblOffset = new JLabel("offset");
				lblOffset.setToolTipText("The dielectric radii for generalized Born calculations are decreased by a uniform");
				splitPane_32.setLeftComponent(lblOffset);
				
				textField_35 = new JTextField();
				textField_35.setText("0.09");
				splitPane_32.setRightComponent(textField_35);
				textField_35.setColumns(10);
				
				JSplitPane splitPane_33 = new JSplitPane();
				splitPane_33.setBounds(10, 147, 87, 25);
				panel_16.add(splitPane_33);
				
				JLabel lblGbsa = new JLabel("gbsa");
				lblGbsa.setToolTipText("GB/SA (generalized Born/surface area) simulations.");
				splitPane_33.setLeftComponent(lblGbsa);
				
				JComboBox comboBox_6 = new JComboBox();
				comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2"}));
				splitPane_33.setRightComponent(comboBox_6);
				
				JSplitPane splitPane_34 = new JSplitPane();
				splitPane_34.setBounds(10, 171, 87, 25);
				panel_16.add(splitPane_34);
				
				JLabel lblSurften = new JLabel("surften");
				lblSurften.setToolTipText("Surface tension used to calculate the nonpolar contribution to the free energy of sol-");
				splitPane_34.setLeftComponent(lblSurften);
				
				textField_36 = new JTextField();
				textField_36.setText("0.005");
				splitPane_34.setRightComponent(textField_36);
				textField_36.setColumns(10);
				
				JSplitPane splitPane_35 = new JSplitPane();
				splitPane_35.setBounds(10, 195, 87, 25);
				panel_16.add(splitPane_35);
				
				JLabel lblRdt = new JLabel("rdt");
				lblRdt.setToolTipText("This parameter is only used for GB simulations with LES (Locally Enhanced Sam-");
				splitPane_35.setLeftComponent(lblRdt);
				
				textField_37 = new JTextField();
				textField_37.setText("0.0");
				splitPane_35.setRightComponent(textField_37);
				textField_37.setColumns(10);
				
				JPanel panel_17 = new JPanel();
				panel_17.setLayout(null);
				panel_17.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Polarizable Potentials", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_17.setBounds(124, 378, 125, 209);
				panel_14.add(panel_17);
				
				JSplitPane splitPane_37 = new JSplitPane();
				splitPane_37.setBounds(10, 21, 90, 25);
				panel_17.add(splitPane_37);
				
				JLabel lblIndmeth = new JLabel("indmeth");
				splitPane_37.setLeftComponent(lblIndmeth);
				
				JComboBox comboBox_7 = new JComboBox();
				comboBox_7.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3"}));
				splitPane_37.setRightComponent(comboBox_7);
				
				JSplitPane splitPane_38 = new JSplitPane();
				splitPane_38.setBounds(10, 47, 90, 25);
				panel_17.add(splitPane_38);
				
				JLabel lblDiptol = new JLabel("diptol");
				lblDiptol.setToolTipText("Convergence criterion for dipoles in the iterative methods.");
				splitPane_38.setLeftComponent(lblDiptol);
				
				textField_39 = new JTextField();
				textField_39.setText("0.0001");
				splitPane_38.setRightComponent(textField_39);
				textField_39.setColumns(10);
				
				JSplitPane splitPane_39 = new JSplitPane();
				splitPane_39.setBounds(10, 74, 90, 25);
				panel_17.add(splitPane_39);
				
				JLabel lblMaxiter = new JLabel("maxiter");
				splitPane_39.setLeftComponent(lblMaxiter);
				
				textField_40 = new JTextField();
				textField_40.setText("20");
				splitPane_39.setRightComponent(textField_40);
				textField_40.setColumns(10);
				
				JSplitPane splitPane_40 = new JSplitPane();
				splitPane_40.setBounds(10, 100, 90, 25);
				panel_17.add(splitPane_40);
				
				JLabel lblDipmass = new JLabel("dipmass");
				lblDipmass.setToolTipText("The fictitious mass assigned to dipoles.");
				splitPane_40.setLeftComponent(lblDipmass);
				
				textField_41 = new JTextField();
				textField_41.setText("0.33");
				splitPane_40.setRightComponent(textField_41);
				textField_41.setColumns(10);
				
				JSplitPane splitPane_41 = new JSplitPane();
				splitPane_41.setBounds(10, 125, 90, 25);
				panel_17.add(splitPane_41);
				
				JLabel lblDiptau = new JLabel("diptau");
				lblDiptau.setToolTipText("This is used for temperature control of the dipoles (for indmeth=3)");
				splitPane_41.setLeftComponent(lblDiptau);
				
				textField_42 = new JTextField();
				textField_42.setText("11");
				splitPane_41.setRightComponent(textField_42);
				textField_42.setColumns(10);
				
				JSplitPane splitPane_42 = new JSplitPane();
				splitPane_42.setBounds(10, 150, 90, 25);
				panel_17.add(splitPane_42);
				
				JLabel lblIrstdip = new JLabel("irstdip");
				lblIrstdip.setToolTipText("For indmeth > 3.");
				splitPane_42.setLeftComponent(lblIrstdip);
				
				JComboBox comboBox_8 = new JComboBox();
				comboBox_8.setModel(new DefaultComboBoxModel(new String[] {"0", "1"}));
				splitPane_42.setRightComponent(comboBox_8);
				
				JSplitPane splitPane_43 = new JSplitPane();
				splitPane_43.setBounds(10, 177, 90, 25);
				panel_17.add(splitPane_43);
				
				JLabel lblScaldip = new JLabel("scaldip");
				lblScaldip.setToolTipText("To scale 1-4 charge-dipole and dipole-dipole interactions the same as 1-4 charge-");
				splitPane_43.setLeftComponent(lblScaldip);
				
				JComboBox comboBox_9 = new JComboBox();
				comboBox_9.setModel(new DefaultComboBoxModel(new String[] {"0", "1"}));
				splitPane_43.setRightComponent(comboBox_9);
				
				
				
				
				///////////////////////////////////////////////////////////////////Define Functions to GUI from here///////////////////////////////////////////////////////
				
			}
}
