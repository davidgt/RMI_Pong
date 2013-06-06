package Common.Objects;


public class PosEntity implements Entity{
	private static final long serialVersionUID = 1L;
	private int x,y;

	public PosEntity(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public PosEntity() { new PosEntity(0,0); }

	@Override
	public int getX() 		{		return x;	}
	@Override
	public void setX(int x) {		this.x = x;	}

	@Override
	public int getY() 		{		return y;	}

	@Override
	public void setY(int y) {		this.y = y;	}
	
	@Override
	public boolean Colision (Entity entity){
		return (entity.getX() == this.getX()) || (entity.getY() == this.getY()) ;
	}
}