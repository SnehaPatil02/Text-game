public class Food extends Item {
   
    int energy;

    public Food(){
        this.interactionType = "EAT";
    }

    public int eat(){
        return this.energy;
    }


   
}
