import java.util.Scanner;

public class Play{
    public static void main(String[] args) {
       Scanner scan = new Scanner(System.in);
       System.out.println("Do you want to start the game ");
       
       String start = scan.next(); 
  
       if(start.equalsIgnoreCase("yes")){
        PlayGround playGround = new PlayGround();
        playGround.randomize(scan); 
        playGround.start();
           
           
       }
       scan.close();
    }
    
}