public class Pokemon{

   private String name;
   private int maxHP;
   private int currentHP;
   private int attack;
   private int defense;
   private boolean fainted = false;

   public Pokemon(String n, int mh, int ch, int a, int d){
      name = n;
      maxHP = mh;
      currentHP = ch;
      attack = a;
      defense = d;
   }
   
   public void fainted(){
      if(currentHP <=0){
         fainted = true;
      }
   }
   
   public String formatted(){
     
     return name + " " + maxHP + " " + currentHP + " " + attack + " " + defense;
   
   }
   
   public boolean getFainted(){
      return fainted;
   }
   
   public String getName(){
      return name;
   }
   
   public int getMaxHP(){
      return maxHP;
   }
   
   public int getCurrentHP(){
      return currentHP;
   }
   
   public int getAttack(){
      return attack;
   }
   
   public int getDefense(){
      return defense;
   }
   
   public void setCurrentHP(int ch){
      currentHP = ch;
      if(currentHP<0){
         currentHP = 0;
      }
      
   }
   
   public String toString(){
      String s = "";
      
      if(currentHP>0){
      s+=name + " " + currentHP + "/" + maxHP;
      }
      else{
      s+=name + " FAINTED";
      }
      
      return s;
   
   }


}