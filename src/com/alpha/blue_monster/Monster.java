package com.alpha.blue_monster;

import java.util.HashMap;

public class Monster{

	/**
	 * 
	 */
	private HashMap<String, Object> monsterTable = new HashMap<String, Object>();
	public static String HP = "hp";
	public static String HAPPINESS = "happiness";
	public static String MAX_HP = "maxhp";
	public static String MAX_HAPPINESS = "maxhappiness";
	public static String LEVEL = "level";
	public static String EXP = "exp";
	public static String NAME = "name";
	public static String NICKNAME = "nickname";
	
	public Monster(String name, String nickname, int hp, int happiness,  int level, int exp){
		monsterTable.put(HP, new Float(hp));
		monsterTable.put(HAPPINESS, new Float(happiness));
		monsterTable.put(MAX_HP, new Float(hp));
		monsterTable.put(MAX_HAPPINESS, new Float(happiness));
		monsterTable.put(LEVEL, new Float(level));
		monsterTable.put(EXP, new Float(exp));
		monsterTable.put(NAME, new String(name));
		monsterTable.put(NICKNAME, new String(nickname));
	}
	
	public void set(String attr, Object value){
		monsterTable.put(attr, value);
	}
	
	public Object get(String attr){
		return monsterTable.get(attr);
	}

}
