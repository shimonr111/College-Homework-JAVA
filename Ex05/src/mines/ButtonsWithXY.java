package mines;

import javafx.scene.control.Button;

public class ButtonsWithXY extends Button{//this class is a special button that tells his location in the grid we will create 
	private int x,y;
	
	public ButtonsWithXY(int x,int y,String stringForButton) {
		super(stringForButton);
		this.x=x;
		this.y=y;
	}
	public int getValueX() {
		return x;
	}
	
	public int  getValueY() {
		return y;
	}
}
