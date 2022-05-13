package de.nebalus;

public class Core {

	public static void main(String[] args) {
		SpiralAlgorithm sa = new SpiralAlgorithm(-1, 1);
		System.out.println("X:" + sa.getXCord() + " Y:"+sa.getYCord() + " Unit:" +sa.getUnitId() );
	}
	
}
