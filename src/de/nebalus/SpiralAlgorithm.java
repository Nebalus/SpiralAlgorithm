package de.nebalus;

public class SpiralAlgorithm {

	final int unitid;

	int xcord;
	int ycord;

	public SpiralAlgorithm(int unitid) throws IllegalArgumentException {
		if (unitid <= 0) {
			throw new IllegalArgumentException("You cannot use a 0 or a negative number!");
		}

		this.unitid = unitid;

		// Speichert bei welche schicht wo sich die unitid sich befindet!
		final int level = getLevel();

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

	// Gebt die ebene wo sich die unitid, sich befindet zurück!
	private int getLevel() {
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

	private int getAmountOfUnitsOfLevel(int level) {
		return (getAmountOfUnitsOfALevelSide(level) * 4) - 4;
	}

	private int getAmountOfUnitsOfALevelSide(int level) {
		return 1 + (level * 2);
	}

	public int getYCord() {
		return ycord;
	}

	public int getXCord() {
		return xcord;
	}

	private enum Direction {
		RIGHT, DOWN, LEFT, UP;
	}

}
