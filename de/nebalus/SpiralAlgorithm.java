package de.nebalus;

public final class SpiralAlgorithm {

	int unitid = -1;

	int xcord = 0;
	int ycord = 0;

	public SpiralAlgorithm(int unitid) throws IllegalArgumentException {
		if (unitid <= 0) {
			throw new IllegalArgumentException("You cannot use a 0 or a negative number!");
		}

		this.unitid = unitid;

		// Speichert bei welche schicht wo sich die unitid sich befindet!
		final int level = getLevel(unitid);

		// Speichert die anzahl von units die sich an einer seite sich befinden
		final int levelsideunits = getAmountOfUnitsOfALevelSide(level);

		// Speichert wie viele units sich in jetzigen schicht sich befinden
		final int unitsoflevel = getAmountOfUnitsOfLevel(level);

		// Speichert wie viele Units ingesammt exestieren
		final int allunits = levelsideunits * levelsideunits;

		// Speichert wie viele Units ingesammt exestieren, wenn man die units weg
		// lässt die sich inder jetzigen schicht sich befinden
		final int allunitstolevel = allunits - unitsoflevel;

		Direction direction = Direction.RIGHT;

		if (level == 0) {
			xcord = 0;
			ycord = 0;
			return;
		}

		int cachex = (level * -1) + 1;
		int cachey = level;

		for (int currentunit = allunitstolevel + 1; currentunit <= allunits; currentunit++) {
			if (currentunit < unitid) {
				switch (direction) {
				case RIGHT:
					cachex++;
					if (cachex >= level) {
						direction = Direction.DOWN;
					}
					break;
				case DOWN:
					cachey--;
					if (cachey <= level * -1) {
						direction = Direction.LEFT;
					}
					break;
				case LEFT:
					cachex--;
					if (cachex <= level * -1) {
						direction = Direction.UP;
					}
					break;
				case UP:
					cachey++;
					if (cachey >= level) {
						direction = Direction.RIGHT;
					}
					break;
				}
			}else {
				this.xcord = cachex;
				this.ycord = cachey;
			}
		}
	}
	
	public SpiralAlgorithm(int xcord, int ycord) {

		final int positivx; 
		final int positivy; 
		
		if(xcord < 0) {
			positivx = xcord * -1;
		}else {
			positivx = xcord;
		}
		
		if(ycord < 0) {
			positivy = ycord * -1;
		}else {
			positivy = ycord;
		}
			
		final int level;

		if(positivx >= positivy) {
			level = positivx;
		}else {
			level = positivy;
		}
		
		// Speichert die anzahl von units die sich an einer seite sich befinden
		final int levelsideunits = getAmountOfUnitsOfALevelSide(level);

		// Speichert wie viele units sich in jetzigen schicht sich befinden
		final int unitsoflevel = getAmountOfUnitsOfLevel(level);

		// Speichert wie viele Units ingesammt exestieren
		final int allunits = levelsideunits * levelsideunits;

		// Speichert wie viele Units ingesammt exestieren, wenn man die units weg
		// lässt die sich inder jetzigen schicht sich befinden
		final int allunitstolevel = allunits - unitsoflevel;

		int cachex = (level * -1) + 1;
		int cachey = level;

		Direction direction = Direction.RIGHT;
		
		this.unitid = allunitstolevel + 1;
		
		for (int currentunit = this.unitid; currentunit <= allunits; currentunit++) {
			
			if (cachex != xcord || cachey != ycord) {
				this.unitid++;
				switch (direction) {
				case RIGHT:
					cachex++;
					if (cachex >= level) {
						direction = Direction.DOWN;
					}
					break;
				case DOWN:
					cachey--;
					if (cachey <= level * -1) {
						direction = Direction.LEFT;
					}
					break;
				case LEFT:
					cachex--;
					if (cachex <= level * -1) {
						direction = Direction.UP;
					}
					break;
				case UP:
					cachey++;
					if (cachey >= level) {
						direction = Direction.RIGHT;
					}
					break;
				}
			}else {
				this.xcord = xcord;
				this.ycord = ycord;
			}
		}
	}

	// Gebt die ebene wo sich die unitid, sich befindet zurück!
	private final int getLevel(int unitid) {
		if (unitid == 1)
			return 0;

		int currentmax = 1;
		int level = 0;

		while (currentmax < unitid) {
			currentmax = currentmax + getAmountOfUnitsOfLevel(level);
			level++;
		}
		return level - 1;
	}

	private final int getAmountOfUnitsOfLevel(int level) {
		return (getAmountOfUnitsOfALevelSide(level) * 4) - 4;
	}

	private final int getAmountOfUnitsOfALevelSide(int level) {
		return 1 + (level * 2);
	}

	public final int getYCord() {
		return ycord;
	}

	public final int getXCord() {
		return xcord;
	}

	public final int getUnit() {
		return unitid;
	}
	
	private enum Direction {
		RIGHT, DOWN, LEFT, UP;
	}

}
