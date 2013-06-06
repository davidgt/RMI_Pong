package Common;

import java.io.Serializable;

import Common.Objects.*;


public class GameData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Racket racket1;
	private Racket racket2;
	private Ball ball;
	
	private int[] score;
	private int speed;
	private Boolean Y_sig,X_sig;
	
	public static int ballSize=20;
	public static int racketHeigth=75;
	public static int racketWidth=20;
	public static int frameHeigth=550;
	public static int frameWidth=800;
	
	protected GameData(){
		racket1=new Racket(10,(frameHeigth/2+racketHeigth/2)-100);
		racket2=new Racket(frameWidth-30,(frameHeigth/2+racketHeigth/2)-100);
		ball=new Ball();
			
		score=new int[2];
		score[0]=0; score[1]=0;
		speed=5;
		X_sig=true;
		Y_sig=true;
	}

	public Boolean getY_sig() {
		return Y_sig;
	}

	public void setPosY_sig(Boolean y_sig) {
		Y_sig = y_sig;
	}

	public Boolean getX_sig() {
		return X_sig;
	}

	public void setPosX_sig(Boolean x_sig) {
		X_sig = x_sig;
	}

	public int getPos(int user) {
		switch(user){
		case 1: 
			return racket1.getPosY();
		case 2:
			return racket2.getPosY();
		default:
			return 0;
		}
	}

	public void setPos(int user, int pos) {
		switch(user){
		case 1:
			this.racket1.setPosY(pos);
			break;
		case 2:
			this.racket2.setPosY(pos);
			break;
		}
	}

	public int getPosBallx() {
		return ball.getPosX();
	}

	public void setPosBally(int posBally){
		this.ball.setPosY(posBally);
	}
	
	public void setPosBallx(int posBallx) {
		this.ball.setPosX(posBallx);
	}
	public int getPosBally() {
		return ball.getPosY();
	}

	public void setPosBally(int posBally) {
		this.ball.setPosY(posBally);
	}

	public int[] getScore() {
		return score;
	}

	public void setScore(int[] score) {
		this.score = score;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setSpeed(int speed){
		this.speed=speed;
	}
	public void resetGame(){
		ball.setPosX(390);
		ball.setPosY(290);		
		speed=5;		
	}

	
	
	
	
	
}
