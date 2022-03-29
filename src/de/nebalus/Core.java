package de.nebalus;

public class Core {

	public static void main(String[] args) {
		SpiralAlgorithm sa = new SpiralAlgorithm(-3, -4);
		System.out.println("X: "+sa.getXCord()+ " Y: "+sa.getYCord()+" ID: "+sa.getUnit());
	}
	
}
