A)	What is AUT?
AUT stands for AMBER Utility Tool. AMBER is a package of molecular simulation programs which includes source code and demos (http://ambermd.org/). AUT is a Graphical User Interface toolkit to assist the set-up and automate the Molecular Dynamics Simulation steps using AMBER. Please note that you need to have installed AMBER separately in your system. This was an independent fun project for me (to balance my laziness) and has nothing to do with official AMBER. At present, AMBER is running at version 16 but AUT is tested only on AMBER12. Right now I have stalled its development as I no more have access to the latest version of AMBER. If someone finds it useful and is interested, please mail me at shashankbic@gmail.com and we can revive the development of the project.

B)	What do you need to run AUT?
1)	Java SE Runtime Environment (JRE): Get JRE7 from: http://www.oracle.com/technetwork/java/javase/downloads/jre7-downloads-1880261.html
2)	Installing java on any Operating System should run the AUT, however, you cannot use AUT on Windows (it should work on Mac OS too, but I couldn’t test it) as AMBER can only be installed and used on Linux OS and Mac OS.

C)	What you do need to use AUT
1)	LINUX or Mac OS
2)	Java JRE installed
3)	Properly installed AMBER
4)	Molecular structure files in PDB (Protein/DNA/RNA/Protein-ligand/Complexes) and mol2 (ligand) format 

D)	What you do need to use AUT
AUT has been developed by keeping in mind that user doesn’t get confused by tabs and buttons. Hence, initially only those options are unlocked that are absolutely necessary to define in order to proceed. Once these options are set, other options will automatically be unlocked.
1)	AMBERHOME: First thing first, set the AMBERHOME directory. AMBERHOME is where AMBER is installed on your OS. Click on ‘AMBERHOME’, browse to the directory where AMBER is installed and open it. If you are successful, next option ‘Working DIR’ should be unlocked.
2)	Working DIR: This is the directory where you want to run MD simulations. Set it just like the last step to wherever you want to but its good practice to keep organize directories. This should unlock the ‘Select PDB’ option.
3)	Select PDB: Select the structure file of macromolecule in PDB format. Next option is for ligand selection. If PDB file contains any ligand, set the number to number of ligands and choose the ligand structure files in mol2 format. If there is no ligand in the PDB file, please choose 0.
4)	Set Name: Set the name of output files that will be generated during the simulation set-up and run.
5)	Update Information Area: Click here to display the most updated parameters. 

Successful completion of the above options should unlock ‘Tleap Parameters’, ‘Minimization’ and ‘MD’ tabs. Choose ‘Tleap Parameters’ to set-up the simulation or if you already have the parameter files, you can directly jump onto ‘Minimization’ or ‘MD’ tabs. To use rest of the tabs, you will need to know about AMBER simulation parameters, however, default parameters should also work. Use of default parameters is good for practice sessions but not recommended for practical use.
