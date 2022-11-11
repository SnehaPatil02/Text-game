import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    int health;
    ArrayList<Food> foodinventory ;
    Tiles currentTile ;
    int power;

    public Player(Tiles currentTiles){
        this.health = 100;
        this.foodinventory = new ArrayList<Food>();
        this.currentTile = currentTiles;
        this.power = 25;
    }

    public void store(Food food){
        this.foodinventory.add(food);

    }

    public void restoreHealth(Scanner scan){
        if(this.foodinventory.size() == 0){
            return;
        }
        System.out.println("Which food you want to eat ");
        for (int i = 0; i < this.foodinventory.size(); i++) {
            System.out.println(this.foodinventory.get(i).getClass().getSimpleName());
        }
            String storedFood = scan.next();
            for (int i = 0; i < this.foodinventory.size(); i++) {
                if(storedFood.equalsIgnoreCase(this.foodinventory.get(i).getClass().getSimpleName())){

                    this.health = this.health + this.foodinventory.get(i).energy;
                    this.foodinventory.remove(i);
                    return ;
                }
            }
        
    }

    public int Attack(){
        return this.power;
    }
    public Boolean isAlive(){
        if(this.health>0)
        {
            return true;
        }
        return false;
    }



}
