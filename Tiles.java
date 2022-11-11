public class Tiles {
    int row;
    int colomn;
    int entry = 0;
    int exit = 0;
    Item item;

    public Tiles(int row, int colomn){
        this.row = row;
        this.colomn = colomn;
        this.item = new Item();
    }

    

    public void setDirections(String directions){
        this.item = new Directions(directions);
    }

    public void setEnemy(Enemy enemy){
        this.item = enemy;
    }

    public void setFood(Food food){
        this.item = food; 
    }

    public void setWeapon(Weapon weapon){
        this.item = weapon;
    }

    public String interactionType(){
        return this.item.interactionType;
        
    }

    public void setEntry() {
        this.entry = 1;
    }

    public void setExit() {
        this.exit = 1;
    }

    public boolean isExit(){
        if(this.exit == 1){
            return true;
        }
        return false;
    }
}
