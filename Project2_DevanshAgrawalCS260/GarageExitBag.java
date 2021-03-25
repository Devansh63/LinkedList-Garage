
package Project2_DevanshAgrawalCS260;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//import sun.jvm.hotspot.opto.CallJavaNode;

public class GarageExitBag {
	CarDataNode head; 
	
	public void addNodeAfter(CarDataNode node) {
		if(head == null) {
			node.setPrev(null);
			node.setNext(null);
			head = node;
		} else {
			node.setPrev(null);
			node.setNext(head);
			node.getNext().setPrev(node);
			head = node;
		}
	} 
	
	public String toString() {
		String string = "";
		CarDataNode cursor = head;
		
		while(cursor!=null) {
			string += cursor.toString();
			cursor=cursor.getNext();
		}
		return string; 
	}
	
	public static void dumpOutPutFile(GarageExitBag garageexitbag) throws FileNotFoundException {
		
			Date CD = new Date();
			DateFormat dtf = new SimpleDateFormat("MM-dd-yyyy");
			
			File file = new File("Car Record" + dtf.format(CD) +".txt");
			PrintWriter saveFile = new PrintWriter(file); 	
			saveFile.print(garageexitbag.toString());
			saveFile.close();
		}
	
}

//Error: Unable to initialize main class Project2_DevanshAgrawalCS260.Driver
//Caused by: java.lang.NoClassDefFoundError: ClassNotFoundException

