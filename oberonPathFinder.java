// Recursive Pathfinding Engine for OBERON 2 AI
// Based on a very simple pathfinder I found online, but I need to make quite a few changes to this to make it usable.

public class oberonPathFinder {
   public int counter = 0;
	
   public char[][] maze =
   	   {{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
   		{'#', ' ', ' ', ' ', '#', ' ', '#', ' ', ' ', '#'},
   		{'#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', '#'},
   		{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
   		{'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
   		{'#', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
   		{'#', ' ', ' ', ' ', ' ', '#', '#', '#', '#', '#'},
   		{'#', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', '#'},
   		{'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
   		{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},};

	// Get the start location (x,y) and try to solve the maze
   public void solve(int x, int y) {
      if (step(x,y)) {
         maze[x][y] = 'S';
      }
   }
	
	// Backtracking method
   public boolean step (int x, int y) {
      counter++;
   	
   	// System.out.println(this.toString());
   	
   	/** Accept case - we found the exit **/
      if (maze[x][y] == 'X') {
         return true;
      }
   	
   	/** Reject case - we hit a wall or our path **/
      if (maze[x][y] == '#' || maze[x][y] == '*') {
         return false;
      }
   	
   	/** Backtracking Step **/
   	
   	// Mark this location as part of our path
      maze[x][y] = '*';
      boolean result;	
   	
   	// Try to go Right
      result = step(x, y+1);
      if (result) { 
         return true;}
   	
   	// Try to go Up
      result = step(x-1, y);
      if (result) { 
         return true;}
   	
   	// Try to go Left
      result = step(x, y-1);
      if (result) { 
         return true;}		
   	
   	// Try to go Down
      result = step(x+1, y);
      if (result) { 
         return true;}		
   	
   	
   	/** Deadend - this location can't be part of the solution **/
   	
   	// Unmark this location
      maze[x][y] = ' ';
   	
   	// Go back
      return false;
   }
	
   public String toString() {
      String output = "";
      for (int x=0; x<10; x++) {
         for (int y=0; y<10; y++) {
            output += maze[x][y] + " ";
         }
         output += "\n";
      }
      return output;
   }
	
   public static void main(String[] args) {
      oberonPathFinder m = new oberonPathFinder();
   	// Locate the exit
      m.maze[1][1] = 'X';
   	
   	// Start solving the maze from (8, 1)
      m.solve(8, 1);
      System.out.println(m);
      System.out.println("Total calls: " + m.counter);
   }
}