package application;


import javafx.scene.control.TextField;

public class Handling {
	

//make user try again
public static void nullPointerException_handler( TextField[] arr) {
	for(int i=0;i<arr.length;i++)
	{arr[i].clear();}	
}



//make user try again

public static void illegalArgumentException_handler(TextField a) {
	a.clear();}
	
}
