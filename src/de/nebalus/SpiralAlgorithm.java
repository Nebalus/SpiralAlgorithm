package de.nebalus;

public class SpiralAlgorithm implements Cloneable {

	private int unitid = -1;

	private int x = 0;
	private int y = 0;

	public SpiralAlgorithm(final int unitid) throws IllegalArgumentException 
	{
		if (unitid <= 0) 
		{
			throw new IllegalArgumentException("You cannot use a 0 or a negative number!");
		}

		this.unitid = unitid;

		// Speichert bei welche schicht wo sich die unitid sich befindet!
		final int level = getLevel(unitid);

		if (level == 0) 
		{
			x = 0;
			y = 0;
			return;
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

		Direction direction = Direction.RIGHT;

		int cachex = (level * -1) + 1;
		int cachey = level;

		for (int currentunit = allunitstolevel + 1; currentunit <= allunits; currentunit++) 
		{
			if (currentunit < unitid) 
			{
				switch (direction) 
				{
				case RIGHT:
					cachex++;
					if (cachex >= level) 
					{
						direction = Direction.DOWN;
					}
					break;
				case DOWN:
					cachey--;
					if (cachey <= level * -1) 
					{
						direction = Direction.LEFT;
					}
					break;
				case LEFT:
					cachex--;
					if (cachex <= level * -1) 
					{
						direction = Direction.UP;
					}
					break;
				case UP:
					cachey++;
					if (cachey >= level) 
					{
						direction = Direction.RIGHT;
					}
					break;
				}
			}
			else 
			{
				this.x = cachex;
				this.y = cachey;
				break;
			}
		}
	}
	
	public SpiralAlgorithm(final int xcord, final int ycord) {

		final int level = getLevel(xcord, ycord);
		
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
		
		for (int currentunit = this.unitid; currentunit <= allunits; currentunit++) 
		{
			
			if (cachex != xcord || cachey != ycord) 
			{
				this.unitid++;
				switch (direction) 
				{
				case RIGHT:
					cachex++;
					if (cachex >= level) 
					{
						direction = Direction.DOWN;
					}
					break;
				case DOWN:
					cachey--;
					if (cachey <= level * -1) 
					{
						direction = Direction.LEFT;
					}
					break;
				case LEFT:
					cachex--;
					if (cachex <= level * -1) 
					{
						direction = Direction.UP;
					}
					break;
				case UP:
					cachey++;
					if (cachey >= level) 
					{
						direction = Direction.RIGHT;
					}
					break;
				}
			}
			else 
			{
				this.x = xcord;
				this.y = ycord;
				break;
			}
		}
	}

	// Gebt die ebene wo sich die unitid, sich befindet zurück!
	private final int getLevel(final int unitid) 
	{
		if (unitid == 1)
			return 0;

		int currentmax = 1;
		int level = 0;

		while (currentmax < unitid) 
		{
			currentmax = currentmax + getAmountOfUnitsOfLevel(level);
			level++;
		}
		return level - 1;
	}
	
	private final int getLevel(final int xcord, final int ycord) 
	{		
		int positivx = xcord; 
		int positivy = ycord; 
		
		if(positivx < 0) 
		{
			positivx = xcord * 1;
		}
		
		if(positivy < 0) 
		{
			positivy = ycord * 1;
		}

		if(positivx > positivy) 
		{
			return positivx;
		}
		else 
		{
			return positivy;
		}
	}

	private final int getAmountOfUnitsOfLevel(final int level) 
	{
		return (getAmountOfUnitsOfALevelSide(level) * 4) - 4;
	}

	private final int getAmountOfUnitsOfALevelSide(final int level) 
	{
		return 1 + (level * 2);
	}

	public final int getYCord() 
	{
		return y;
	}

	public final int getXCord() 
	{
		return x;
	}

	public final int getUnitId() 
	{
		return unitid;
	}
	
	private enum Direction 
	{
		RIGHT, DOWN, LEFT, UP;
	}

	
//	@Override
//	public boolean equals(SpiralAlgorithm othersa) {
//		if(othersa.getXCord() == xcord)
//	}

}
