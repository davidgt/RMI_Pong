package Common.Objects;

import java.io.Serializable;

public interface Entity extends Serializable{
	
	public int getX();
	public void setX(int posx);
	public int getY();
	public void setY(int posy);
	public boolean Colision (Entity entity);
	
}
