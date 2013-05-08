package Common.Objects;

import java.io.Serializable;


public class Ball implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PosEntity position;
	
	//Constructors
	public Ball(){
		this.position = new PosEntity(50,50);
	}
	public Ball(int posx, int posy){
		this.position.setX(posx);
		this.position.setY(posy);
	}

	public int getPosx() {
		return this.position.getX();
	}
	
	public void setPosx(int posx) {
		this.position.setX(posx);
	}

	public int getPosy() {
		return position.getY();
	}

	public void setPosy(int posy) {
		this.position.setY(posy);
	}

	public boolean Colision(Entity entity) {
		return this.position.Colision(entity);
	}
}
