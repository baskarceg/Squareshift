package com.squareshift.seating;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AirplaneSeating {
	
	public static void seatPassengers(int[][] seatArray, int noOfPassengers) {
		int passenger=1;
		
		TreeMap<Integer,Cubicle> seatMap = new TreeMap<>(); 
		
		TreeMap<Integer,List<String>> aisleSeatMap = new TreeMap<>();
		TreeMap<Integer,List<String>> windowSeatMap = new TreeMap<>();
		TreeMap<Integer,List<String>> centerSeatMap = new TreeMap<>();
		
		
		for(int i=0;i < seatArray.length ; i++){
			Cubicle newCubicle = new Cubicle(seatArray[i][1], seatArray[i][0]);
			seatMap.put(i, newCubicle);
		}
		
		for(Map.Entry<Integer, Cubicle> entry : seatMap.entrySet()) {
			Cubicle cubicle = entry.getValue();
			Integer cubicleId = entry.getKey();
			
			for(int i=0;i<cubicle.getNoOfRows();i++) {
				for(int j=0;j<cubicle.getNoOfColumns();j++) {
					String seatName = cubicleId+"_"+i+"_"+j;
					if(cubicleId == 0) {
						if(j == 0) {
							//Window Seat
							populateSeatMap(windowSeatMap, i, seatName);
						}
						else if(j == cubicle.getNoOfColumns()-1){
							//Aisle Seat
							populateSeatMap(aisleSeatMap, i, seatName);
						}
						else {
							//Center Seat
							populateSeatMap(centerSeatMap, i, seatName);
						}
					}
					else if(cubicleId==seatArray.length-1) {
						if(j == cubicle.getNoOfColumns()-1) {
							//Window Seat
							populateSeatMap(windowSeatMap, i, seatName);
							
						}
						else if(j == 0){
							//Aisle Seat
							populateSeatMap(aisleSeatMap, i, seatName);
						}
						else {
							//Center Seat
							populateSeatMap(centerSeatMap, i, seatName);
						}
					}
					else {
						if(j==0||j==cubicle.getNoOfColumns()-1) {
							//Aisle Seat
							populateSeatMap(aisleSeatMap, i, seatName);
						} 
						else {
							//Center Seat
							populateSeatMap(centerSeatMap, i, seatName);
						}
					}
				}
			}
		}
		
		if(passenger <= noOfPassengers) {
			for(Map.Entry<Integer, List<String>> entry : aisleSeatMap.entrySet()) {
				passenger = populateCubicle(passenger, seatMap, entry, noOfPassengers);
				if(passenger > noOfPassengers)
					break;
			}
		}
		
		if(passenger <= noOfPassengers) {
			for(Map.Entry<Integer, List<String>> entry : windowSeatMap.entrySet()) {
				passenger = populateCubicle(passenger, seatMap, entry, noOfPassengers);
				if(passenger > noOfPassengers)
					break;
			}
		}
		
		if(passenger <= noOfPassengers) {
			for(Map.Entry<Integer, List<String>> entry : centerSeatMap.entrySet()) {
				passenger = populateCubicle(passenger, seatMap, entry, noOfPassengers);
				if(passenger > noOfPassengers)
					break;
			}
		}
		
		printSeating(seatMap, seatArray);
		
	}
	
	private static void populateSeatMap(TreeMap<Integer, List<String>> seatMap, int i, String seatName) {
		if(!seatMap.containsKey(i)) {
			List<String> list = new ArrayList<>();
			list.add(seatName);
			seatMap.put(i, list);
		}
		else {
			List<String> list = seatMap.get(i);
			list.add(seatName);
			seatMap.put(i, list);
		}
	}
	
	private static int populateCubicle(int passenger, TreeMap<Integer, Cubicle> seatMap,
			Map.Entry<Integer, List<String>> entry, int noOfPassengers) {
		List<String> seatNameList = entry.getValue();
		for(String seatName: seatNameList) {
			String[] seatNameArray = seatName.split("_");
			Cubicle cubicle = seatMap.get(Integer.parseInt(seatNameArray[0]));
			int row = Integer.parseInt(seatNameArray[1]);
			int column = Integer.parseInt(seatNameArray[2]);
			int[][] cubicleSeats = cubicle.getCubicleSeats();
			cubicleSeats[row][column]=passenger++;
			cubicle.setCubicleSeats(cubicleSeats);
			seatMap.put(Integer.parseInt(seatNameArray[0]), cubicle);
			if(passenger > noOfPassengers)
				break;
		}
		return passenger;
	}
	
	private static int getMaxRows(int[][] seatArray) {
		int max = 0;
		for(int i=0;i<seatArray.length;i++) {
			max = Math.max(max, seatArray[i][1]);
		}
		return max;
	}
	
	private static void printSeating(TreeMap<Integer,Cubicle> seatMap, int[][] seatArray) {
		int maxRows = getMaxRows(seatArray);
		int[] maxLengthArray = new int[seatArray.length];
		int index=0;
		while(index < maxRows) {
			Iterator<Map.Entry<Integer, Cubicle>> iterator = seatMap.entrySet().iterator();
			int loopIndex = 0;
			while(iterator.hasNext()) {
				Map.Entry<Integer, Cubicle> entry = (Entry<Integer, Cubicle>) iterator.next();
				Cubicle cubicle = entry.getValue();
				if(index < cubicle.getNoOfRows()) {
					for(int i=0; i < cubicle.getCubicleSeats()[index].length; i++) {
						System.out.printf("%5d", cubicle.getCubicleSeats()[index][i]);
					}
					maxLengthArray[loopIndex] = cubicle.getCubicleSeats()[index].length * 5;
				} else {
					System.out.format("%1$"+maxLengthArray[loopIndex]+"s", " ");
				}
				loopIndex++;
				System.out.format("%1$5s", " ");
			}
			index++;
			System.out.println();
		}		
	}

}
