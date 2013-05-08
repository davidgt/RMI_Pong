package Common.Objects;

import java.io.Serializable;


public class Racket implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PosEntity position;
	
	//Constructors
	public Racket(){
		this.position = new PosEntity();
	}
	public Racket(int posx, int posy){
		this.position = new PosEntity();
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

	public Boolean Colision(Entity entity) {
		return this.position.Colision(entity);
	}
}