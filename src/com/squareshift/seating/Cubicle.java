package com.squareshift.seating;

public class Cubicle {
	
	private int noOfRows;
	private int noOfColumns;
	
	private int[][] cubicleSeats;
	
	public Cubicle(int rows,int columns) {
		this.noOfRows = rows;
		this.noOfColumns = columns;
		this.cubicleSeats = new int[rows][columns];
	}

	public int getNoOfRows() {
		return noOfRows;
	}


	public int getNoOfColumns() {
		return noOfColumns;
	}


	public int[][] getCubicleSeats() {
		return cubicleSeats;
	}

	public void setCubicleSeats(int[][] cubicleSeats) {
		this.cubicleSeats = cubicleSeats;
	}
	
	

}
