package com.alpha.blue_monster;

public class Testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Monster monster=new Monster("Blue Monster", null, 150, 200, 2,3);
		System.out.println(((Float)monster.get("hp")).floatValue());
	}

}
