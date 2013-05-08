package Common.Objects;


public class PosEntity implements Entity{
	private int x,y;

	public PosEntity(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public PosEntity() { new PosEntity(0,0); }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean Colision (Entity entity){
		return (entity.getX() == this.getX()) || (entity.getY() == this.getY()) ;
	}
}