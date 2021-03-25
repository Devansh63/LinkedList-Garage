package Project2_DevanshAgrawalCS260;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

public class GarageSet implements Serializable{
	
	private CarDataNode head;
	private CarDataNode tail;
	private int size;
	private GarageExitBag garageExitbag;
	
	public GarageSet() {
		head = null;
		tail = null;
		size = 0;
		garageExitbag = new GarageExitBag();
	}
	
	public boolean chechkin(String licensePlate) {
		if(head == null) {
			size++;
			head = new CarDataNode(licensePlate,size,null,null);
			head.checkIn();
		}
		else if(CarDataNode.searchFromNode(head,licensePlate) == null) {
			if(tail==null) {
				tail =head;
			
			while(tail.getNext() != null) {
				tail =tail.getNext();
			}
			
			size++;
			tail =tail.addNodeAfter(licensePlate, size);
			tail.checkIn();
		}
		else {
			size++;
			tail =tail.addNodeAfter(licensePlate, size);
			tail.checkIn();
		}
	}
	else {
		return false;
	}
	return true;
	
}

	public boolean checkOut(String licensePlate) {
		CarDataNode targetCar = CarDataNode.searchFromNode(head, licensePlate);
		if(targetCar != null) {
			if(targetCar.getNext() == null) {
				size--;
			
			if(targetCar == head) {
				targetCar.checkOut();
				head = null;
				
				garageExitbag.addNodeAfter(targetCar);
			}
			else {
				targetCar.checkOut();
				targetCar.getPrev().setIndex(size);
				targetCar.getPrev().setNext(null);
				tail = targetCar.getPrev();
				
				garageExitbag.addNodeAfter(targetCar);
			}
		}
			else {
				size--;
				
				CarDataNode cursor = targetCar;
				if(targetCar == head) {
					while(cursor.getNext()!=null) {
						cursor = cursor.getNext();
						cursor.setIndex(cursor.getIndex()-1);
					}
					
					targetCar.checkOut();
					targetCar.getNext().setPrev(null);
					head = targetCar.getNext();
				
					garageExitbag.addNodeAfter(targetCar);
				
				}
				else {
					while(cursor.getNext() != null) {
						cursor = cursor.getNext();
						cursor.setIndex(cursor.getIndex()-1);
					}
					
					targetCar.checkOut();
					targetCar.removeNode();
					
					garageExitbag.addNodeAfter(targetCar);
				}
			}
		}
		else {
			return false;
			
		}
		return true;
	}
	
	
	
	public String toString() {
		String string = " ";
		CarDataNode cursor = head;
		while(cursor!=null) {
			string += cursor.toString();
			cursor = cursor.getNext();
		}
		return string;
	}

	
	public static void saveGsData(GarageSet garageSet) throws IOException {
		PrintWriter saveFile = new PrintWriter("Garage Set.txt");
		saveFile.print(garageSet.toString());
		
		FileOutputStream fileOutPutStream = new FileOutputStream("Garage Set.txt");
		ObjectOutputStream objectOutputStram = new ObjectOutputStream(fileOutPutStream);
		
		objectOutputStram.writeObject(garageSet);
		
		objectOutputStram.flush();
		objectOutputStram.close();
		saveFile.flush();
		saveFile.close();
	}
	
//	public static GarageSet loadGCDate() throws IOException, ClassNotFoundException {
//		GarageSet garageSet = null;
//		FileInputStream fileInputStream = new FileInputStream("Garage Set.txt");
//		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//		
//		garageSet = (GarageSet) objectInputStream.readObject();
//		
//		fileInputStream.close();
//		objectInputStream.close();
//		
//
//		return garageSet;
//	}

	public GarageExitBag getGarageExitbag() {
		return garageExitbag;
	}

	public void setGarageExitbag(GarageExitBag garageExitbag) {
		this.garageExitbag = garageExitbag;
	}
	
	
}
