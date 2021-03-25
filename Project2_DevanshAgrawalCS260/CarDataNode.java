package Project2_DevanshAgrawalCS260;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.text.DateFormatter;

//import com.sun.tools.sjavac.Package;


public class CarDataNode {
	
	private  String data;
	private CarDataNode next;
	private CarDataNode prev;
	private int index;
	//private CarDataNode head; 
	//private CarDataNode tail;
	private Date checkin = new Date();
	private Date checkout;
	
	public CarDataNode(String data,int index, CarDataNode next, CarDataNode prev)
	{
		this.index=index;
		this.prev = prev;
		this.next = next;
		this.data = data;
		
	}
	
	public CarDataNode addNodeAfter(String element, int i) {
		return next = new CarDataNode(element,i, this, next);
	}
	
	
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String toString() {
		
		if(checkout==null) {
		String print;
		DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
	    // LocalDateTime now = LocalDateTime.now();  
		print = "Number "+ index +" The license plate of entered vehicle is: " + data + 
				"\nThe check in time is: " + dtf.format(checkin) + "\n";
		return print;
		}
		else
		{
			String print;
			DateFormat dtf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
		    // LocalDateTime now = LocalDateTime.now();  
			print = "Number "+ index +"The license plate of entered vehicle is: " + data + 
					"\nThe check out time is: " + dtf.format(checkout) + "\n" ;
			return print;
		}		  	
	}
	
	public CarDataNode removeNode() {
//		if(this.head == null)
//		{
//			return;
//		}
//		else if(prev == null) {
//			head =next;
//		}
//		else if ( tail == null) {
//			
//		}
		prev.next = next;
		next.prev = prev; 
		return next; 
	}
	
	public void checkIn() {
		checkin = new Date();
	}
	public void checkOut() {
		checkout = new Date();
	}
	
	public boolean equals(CarDataNode node) {
		if(data.equals(node.data)) {
			return true;
		}
			return false;
	}
	public static CarDataNode searchFromNode(CarDataNode initialNode, String initialString){

	      while(initialNode != null){
	        if(initialNode.data.equals(initialString)){
	            return initialNode;
	        }
	        initialNode = initialNode.getNext();
	    }
	    return null;
	}

	public CarDataNode searchFromIndexNode(CarDataNode initialNode, int index){
	
	    while(initialNode!=null){
	        if(initialNode.index==index)
	           return initialNode;	
	        	initialNode = initialNode.next;
	       
	    }
	  return null;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public CarDataNode getNext() {
		return next;
	}

	public void setNext(CarDataNode next) {
		this.next = next;
	}

	public CarDataNode getPrev() {
		return prev;
	}

	public  void setPrev(CarDataNode prev) {
		this.prev = prev;
	}

	public Date getCheckin() {
		return checkin;
	}

	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}
	
	
	
}
