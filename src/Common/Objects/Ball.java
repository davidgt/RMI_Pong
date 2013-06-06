package Common.Objects;

import java.io.Serializable;


public class Ball extends PosEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Constructors
	public Ball(){
		super(50,50);
	}
	public Ball(int posx, int posy){
		super(posx,posy);
	}
	
	public int getPosX(){return this.getX();}
	public int getPosY(){return this.getY();}
	public void setPosX(int x){this.setX(x);}
	public void setPosY(int y){this.setY(y);}
	
	
}
