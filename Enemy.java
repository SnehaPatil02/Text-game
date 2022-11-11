public class Enemy extends Item{
    int power = 25;
    int health = 50;

    public Enemy(){
        this.interactionType = "FIGHT";
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
