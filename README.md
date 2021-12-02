# Airplane Seating Algorithm

Use static function AirplaneSeating.seatPassengers(int[][] seatArray, int noOfPassengers) to seat the passengers in the below order 

1. Passengers are seated from the front row to back , starting from left to right
2. Aisle seats are filled first followed by window seats followed by center seats


Input 

- int[][] seatArray  - a 2D array representing the rows and columns
- int noOfPassengers - total number of passengers to be seated

Output:

A seat map of the passengers in the airplane, with 0 marked where the seats are empty , will be printed in the console.


Example Input:

- int[][] seatArray  = {{3,2},{4,3},{2,3},{3,4},{2,2}};
- int noOfPassengers = 30;

Example Output:

![Couldn't be displayed ](/output.png)  
