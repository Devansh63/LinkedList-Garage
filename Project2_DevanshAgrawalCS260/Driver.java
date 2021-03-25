package Project2_DevanshAgrawalCS260;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class Driver extends JFrame {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        Driver gui = new Driver();
        gui.setVisible(true);


    }

    private JScrollPane scroll;
    private JTextField input;
    private JTextArea output;
    private JButton addCar;
    private JButton removeCar;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenuItem exit;
    private JMenuItem clear;
    private GarageSet garageSet;


    private JPanel panel1;
    private JPanel panel2;
    private JPanel  panel3;



    public Driver() throws ClassNotFoundException, IOException {
        setSize(450, 400);
        setTitle("Garage GUI");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        getContentPane().setLayout(new GridLayout(3,1));


        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        file = new JMenu("File");
        menuBar.add(file);
        clear = new JMenuItem("Clear");
        file.add(clear);
        exit = new JMenuItem("Exit");
        file.add(exit);

        //Top Section of the GUI
        panel1 = new JPanel();
        panel1.setSize(40,5);
        getContentPane().add(panel1);


        input = new JTextField();
        input.setColumns(40);
        input.setBorder(BorderFactory.createTitledBorder("Input License Plate Number"));
        panel1.add(input);

        //The Middle Section of the GUI
        panel2 = new JPanel();
        getContentPane().add(panel2);

        addCar = new JButton("Add Car To Garage");
        panel2.add(addCar);
        removeCar = new JButton("Remove Car From Garage");
        panel2.add(removeCar);




        //The bottom section of the GUI
        panel3 = new JPanel();
        getContentPane().add(panel3);

     //   garageSet = GarageSet.loadGCDate();
        
       // if(garageSet == null) {
       // 	garageSet = new GarageSet();
       // }
        //Must wrap the text field in a scroll pane so that it may work
        output = new JTextArea();

       scroll = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       panel3.add(scroll);

        output.setColumns(40);
        output.setBorder(BorderFactory.createTitledBorder("Garage Display"));
        output.setRows(5);





        ButtonListener bl = new ButtonListener();
        addCar.addActionListener(bl);
        removeCar.addActionListener(bl);

        JMenuItemListener ib = new JMenuItemListener();
        clear.addActionListener(ib);
        exit.addActionListener(ib);



    }

    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(addCar == e.getSource()){
            	String licensePate = input.getText();
            	
            	if(!input.equals("")) {
            		input.setText("");
            	
            	
					if(!garageSet.chechkin(licensePate)) {
            			JOptionPane.showMessageDialog(null,"This car is already in the parking lot. ");
            			
            			}
					else {	
						output.setText(garageSet.toString());
						
					}
            	
            	}
            	
            }
            else if(removeCar == e.getSource()){
            	String licensePate = input.getText();
            	output.setText("");
            	
            	if(!licensePate.equals("")) {
            		if(!garageSet.checkOut(licensePate)) {
            			JOptionPane.showMessageDialog(null,"This Car is not in the parking lot");
            		}
            		else {
            			output.setText(garageSet.toString());
            		}
            	}

            }

        }
    }

    private class JMenuItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(exit == e.getSource()){
                try {
					GarageSet.saveGsData(garageSet);
					GarageExitBag.dumpOutPutFile(garageSet.getGarageExitbag());
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
                
            	System.exit(0);
            }
            else if(clear == e.getSource()){
            
            	output.setText("");
            }
        }
    }



}
