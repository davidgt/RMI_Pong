package Server.test;

import static org.junit.Assert.*;

import org.junit.Test;

import Common.GameData;
import Server.Igu;
import Server.Server;

public class Colisiones_test {

	@Test
	public void test_colision_right(){
		Igu i = new Igu("Junit_test");
		i.setVisible(false);
		
		Server S = new Server(i);
		GameData g = S.getGD();

		g.setPosBallx(10000);
		S.colision_right();
		
		int[] score = g.getScore();
		System.out.println(g.getPosBallx());
		assertTrue(score[0] == 1 &&  g.getPosBallx() == 390);
	}
	
	@Test
	public void test_colision_left(){
		Igu i = new Igu("Junit_test");
		i.setVisible(false);
		
		Server S = new Server(i);
		GameData g = S.getGD();

		assertFalse(g == null);
		
		g.setPosBallx(-3);
		S.colision_left();
		
		int[] score = g.getScore();
		assertTrue(score[1] == 1 && g.getPosBallx() == 390);
	}
	
	@Test
	public void test_colision_top(){
		Igu i = new Igu("Junit_test");
		i.setVisible(false);
		
		Server S = new Server(i);
		GameData g = S.getGD();

		assertFalse(g == null);
		
		g.setPosBally(-3);
		S.colision_top();
		
		assertTrue(g.getPosBally() == 0 && g.getY_sig());
	}
	@Test
	public void test_colision_bottom(){
		Igu i = new Igu("Junit_test");
		i.setVisible(false);
		
		Server S = new Server(i);
		GameData g = S.getGD();

		assertFalse(g == null);
		
		g.setPosBally(10000);
		S.colision_bottom();
		
		int max_y = GameData.frameHeigth+(GameData.ballSize)-102;
		
		System.err.println(String.valueOf(g.getPosBally() + "--"+String.valueOf(max_y)));
		assertTrue(g.getPosBally() == max_y  && !g.getY_sig());
	}
}
