package aemora;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Item {

	private String myName;
	private int myId;
	private String myDesc;
	private int myType; //0 = consumable, 1 = boost, 2 = questItem
	private String[] myInfoArray;
	
	public Item(int id) {
		
		//myName = name;
		myId = id;
		//find description & type from xml or text file with all the things
		//maybe only pass in id?  name can also be found in txt/xml
		
		try {
			String line_id = Files.readAllLines(Paths.get("resources/item_stuff/items.txt")).get(id+1);
			myInfoArray = line_id.split("/ "); 
			myType = Integer.parseInt(myInfoArray[1]);
			myName = myInfoArray[2].substring(1, myInfoArray[2].length()-1);
			//myName = "toodles";
			myDesc = myInfoArray[3].substring(1, myInfoArray[3].length()-1);
			
		} catch (IOException e) {
			// uhh
			System.out.println("Item file not found.. oof");
		}
	}
	
	public String[] getInfoArray() {
		return myInfoArray;
	}
	public String getName() {
		return myName;
	}
	
	public int getType() {
		return myType;
	}
	
	public String getDescription(){
		return myDesc;
	}
	
	public int getId() {
		return myId;
	}
}
