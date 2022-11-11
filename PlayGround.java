import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;

public class PlayGround {
    Tiles[][] ground;
    int height;
    int width;
    Tiles entry;
    Tiles exit;
    Player hero;

    Scanner scan ;
    public void randomize(Scanner scan){
        this.scan = scan;
        int groundLength = (int)Math.floor(Math.random()*(1)+4);
        System.out.println("Ground length : " + groundLength);
        this.height = groundLength;
        this.width = groundLength + 1;
        this.ground = new Tiles[this.height][this.width];
      for (int i = 0; i <  this.height; i++) {                                                    
        for (int j = 0; j < this.width; j++) {
            this.ground[i][j] = new Tiles(i, j); 
        }
    }   
         

        int [] randloc = this.twoNumbers(groundLength);
        this.entry = this.ground[randloc[0]][randloc[1]];

        randloc = this.twoNumbers(groundLength);
        this.exit = this.ground[randloc[0]][randloc[1]];

          this.entry.setEntry();
          this.exit.setExit();


        this.ground[1][0].setDirections("Go right");
          
        for(int i=0; i<3; i++){
            Rabbit rabbit = new Rabbit();
            Apple apple = new Apple();
            Enemy enemy = new Enemy();

            randloc = this.twoNumbers(groundLength);
            this.ground[randloc[0]][randloc[1]].setFood(apple);
            
            randloc = this.twoNumbers(groundLength);
            this.ground[randloc[0]][randloc[1]].setEnemy(enemy);

            randloc = this.twoNumbers(groundLength);
            this.ground[randloc[0]][randloc[1]].setFood(rabbit);

        }

          Knife knife = new Knife();
          randloc = this.twoNumbers(groundLength);
          this.ground[randloc[0]][randloc[1]].setWeapon(knife);

          this.hero = new Player(this.entry);
          System.out.println("welcome to the Battle Field");
    }

    private int[] twoNumbers(int num){
        int row1 = (int)Math.floor(Math.random()*(num)); 
        int colomn1 = (int)Math.floor(Math.random()*(num)); 
        return new int[]{row1, colomn1};
    }  

    public boolean inthevalley(int newloc, int maxloc)
    {
        System.out.println("New Location : " + newloc);
        if(newloc>=0 && newloc<maxloc)
        {
            return true;
        }
        return false;
    }

    private void display(){
        
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                Tiles playertile = this.hero.currentTile;
                String tileName = this.ground[i][j].item.getClass().getSimpleName();
                int enemyHealth = -1;
                if(tileName.equals("Enemy")){
                Enemy enemy = (Enemy) this.ground[i][j].item;
                enemyHealth = enemy.health;
                }
                if(playertile.row == i && playertile.colomn == j && !tileName.equals("Item")){
                    if(tileName.equals("Enemy")){
                    System.out.print("Player+" + tileName + "_" + enemyHealth + "\t");
                }else{
                    System.out.print("Player+" + tileName + "\t");
                }
            }
                else if(playertile.row == i && playertile.colomn == j && tileName.equals("Item") ) {
                        System.out.print("Player"  + "\t");
                 }
            else if(tileName.equals("Item")){
                
                System.out.print("_" + "\t");
            }
            else if(tileName.equals("Enemy")){
                System.out.print(tileName + "_" + enemyHealth + "\t");
            }
            else {
                
                System.out.print(tileName + "\t");
            }
        }
            System.out.println();
        }
    }

    public void start(){
        
        Tiles currentTile = this.entry;
        while(currentTile.isExit() == false){
            this.display();
            int row = currentTile.row;
            int colomn = currentTile.colomn;
          
            System.out.println("Where do you wanna go?");
            String move = this.scan.next();
            

            switch(move){
                case "w" : 
                System.out.println("Current location : " + row);
                if(this.inthevalley(row-1, this.height)){
                    row--;
                }
                else {
                    System.out.println("You have reached north point of the valley, find the exit else die");
                }
                break;
                case "s" : 
                if(this.inthevalley(row+1, this.height)){
                    row++;
                }else{
                System.out.println("South Point");
                }
                 break;
                case "a" : 
                if(this.inthevalley(colomn-1, this.width)){
                    colomn--;
                 }
                 else {
                    System.out.println("You have reached west point");
                 } break;
                case "d" : 
                if(this.inthevalley(colomn + 1, this.width)){
                    colomn++;
                 }
                 else{
                    System.out.println("East Point");
                 } break;
                default : System.out.println("Enter Proper directions");
            }

            String itype = this.ground[row][colomn].interactionType();
           
          
           switch(itype){
            case "INFO" : this.info(this.ground[row][colomn].item);break;
            case "FIGHT": this.fight(this.hero, this.ground[row][colomn].item);break;
            case "EAT" : this.eating(this.ground[row][colomn].item, this.hero);break;
            case "PICK" : this.pick(this.ground[row][colomn].item, this.hero); break;
            default: break;
        }
        currentTile = this.ground[row][colomn];
        this.hero.currentTile = currentTile;
        }
        if(currentTile.isExit()){
            System.out.println("*****You Won*******");
        }

    }
    
    public void fight(Player hero, Item item){
        
        Enemy enemy = (Enemy) item;
        
        while(hero.isAlive() && enemy.isAlive()){
            System.out.println("Attack or eat food or run");
           
            String start = this.scan.next(); 
          
            if(start.equalsIgnoreCase("run")){
                hero.health = hero.health - 25;
                break;
            }else if(start.equalsIgnoreCase("eat")){
                hero.restoreHealth(this.scan);
            }

            enemy.health = enemy.health - hero.power;
            hero.health = hero.health - enemy.power;

            System.out.println("Player health: " + hero.health);
            System.out.println("Enemy Health: " + enemy.health);

       
        }
        
        
    }
    public void info(Item item){
        Directions d = (Directions)item;
        System.out.println(d.infoData);
    }

    public void eating(Item item, Player hero){
        
        Food food = (Food) item;

        
            System.out.println(" eat or store or leave");
            String input = this.scan.next();
           
            if(input.equalsIgnoreCase("eat")){
                hero.health = hero.health + food.energy;
            }else if(input.equalsIgnoreCase("store")){
               hero.store(food);
            }

            System.out.println("Player Health after food: " + hero.health);
        
    }

    public void pick(Item item, Player hero){
        Weapon weapon = (Weapon) item;

        System.out.println("Pick or Leave ");
        String input = this.scan.next();

        if(input.equalsIgnoreCase("Pick")){
            hero.power = weapon.power;
        }
        System.out.println("Hero Power:" + hero.power);
    }
}
