package de.nebalus;

public class SpiralAlgorithm {

	final int unitid;
	
	int xcord;
	int ycord;
	
	public SpiralAlgorithm(int unitid) throws IllegalArgumentException {
		if(unitid <= 0) {
			throw new IllegalArgumentException("You cannot use a 0 or a negative number!");
		}
		
		this.unitid = unitid;
		
		
		generateCords();
	}
	
	private void generateCords() {
		int level = getLevel();
		
		if(level == 0) {
			xcord = 0;
			ycord = 0;
			return;
		}
		
		int cachex = 0;
		int cachey = 0;
		
		
	}
	
	//Überprüft ob die variable unitid eine ungrade zahl ist!
	private boolean isOdd() {
		if((unitid % 2) == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	//Gebt die ebene wo sich die unitid, sich befindet zurück!
	private int getLevel() {				
		if(unitid == 1) 
			return 0;
		
		int currentmax = 1;
		int level = 0;
		
		while(currentmax < unitid) {
			currentmax = currentmax + getAmountOfUnitsOfLevel(level);
			level++;
		}
		return level - 1;
	}
	
	private int getAmountOfUnitsOfLevel(int level) {
		return ((1 + (level * 2))*4)-4;
	}
		
	public int getYCord() {
		return ycord;
	}
	
	public int getXCord() {
		return xcord;
	}

	private enum Direction{
		RIGHT,
		DOWN,
		LEFT,
		UP;
		
		public Direction changeDirection() {
			switch(this) {
				case RIGHT:
					return DOWN;
				case DOWN:
					return LEFT;
				case LEFT:
					return UP;
				case UP:
					return RIGHT;
			}
			throw new IllegalStateException("There is no direction called :" + this.name());
		}
	}
	
}
