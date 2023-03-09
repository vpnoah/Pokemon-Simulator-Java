import java.io.*;
import java.util.*;

public class BattleManager{

   int turn = 1;
   PokemonTrainer p1;
   PokemonTrainer p2;
   
   int win = 0;
   
   public BattleManager(String file1, String file2){
   
    p1 = new PokemonTrainer();
    p2 = new PokemonTrainer();
   
   try{
      Scanner scan1 = new Scanner(new File(file1));
      Scanner scan2 = new Scanner(new File(file2));
      
      boolean done = false;
      int counter = 0;
      
      while(!done){
         String name = scan1.next();
         int MaxHP = scan1.nextInt();
         int currentHP = scan1.nextInt();
         int attack = scan1.nextInt();
         int defense = scan1.nextInt();
         
         if(attack>MaxHP){
            System.out.println("Attack is greater than MaxHP, adjusting attack value");
            attack = MaxHP;
         }
         if(currentHP>MaxHP){
            System.out.println("CurrentHP is greater than MaxHP, adjusting CurrentHP value");
            currentHP = MaxHP;
         }
         
         Pokemon p = new Pokemon(name,MaxHP, currentHP, attack, defense);
         p1.getTeam().add(p);
         counter++;
         
         if(!scan1.hasNext()){
            done = true;
         }
         if(scan1.hasNext()){
            if(counter==6){
              System.out.println("You are capped at 6 Pokemon, the team is now closed");
              done = true;
            }
         }

           
      }
      
      
      boolean done2 = false;
      int counter2 = 0;
      
      while(!done2){
         String name = scan2.next();
         int MaxHP = scan2.nextInt();
         int currentHP = scan2.nextInt();
         int attack = scan2.nextInt();
         int defense = scan2.nextInt();
         
         if(attack>MaxHP){
            System.out.println("Attack is greater than MaxHP, adjusting attack value");
            attack = MaxHP;
         }
         if(currentHP>MaxHP){
            System.out.println("CurrentHP is greater than MaxHP, adjusting CurrentHP value");
            currentHP = MaxHP;
         }
         
         Pokemon p = new Pokemon(name,MaxHP, currentHP, attack, defense);
         p2.getTeam().add(p);
         counter2++;
         
         if(!scan2.hasNext()){
            done2 = true;
         }
         if(scan2.hasNext()){
            if(counter2==6){
              System.out.println("You are capped at 6 Pokemon, the team is now closed");
              done2 = true;
            }
         }

           
      }

      
     }
   catch(FileNotFoundException fnfe){
      System.out.println("File not found error!");
   }
   
   }
   
   public void printMenu(){
      System.out.println("Player1            Player2");
      System.out.println(p1.getFirstPokemon() + "           " + p2.getFirstPokemon());
      System.out.println("Player " + turn + " please select an option");
      System.out.println("1. Attack");
      System.out.println("2. Swap");
      System.out.println("3. Forfeit");
   
   }
   
   public void autoSwap(PokemonTrainer p3){
      
      String s = "";
      
      boolean print = false;
      
      if(p3.getFirstPokemon().getFainted()){
         
         s+= p3.getFirstPokemon().getName();
         s+= " has fainted, it will swap with ";
         
         for(int i=1; i<p3.getTeam().size(); i++){
            if(!p3.getTeam().get(i).getFainted()){
               s += p3.getTeam().get(i).getName();
               p3.swap(i);
               print = true;
               break;
            }  
         }
         
      }
      
      if(print)System.out.println(s);
   
   
   }
   
   public String getWinningTeam(){
      
      String s = "";
      
      if(win == 1){
         for(int i=0; i<p1.getTeam().size(); i++){
            s+=p1.getTeam().get(i).formatted();
            s+="\n";
         }
      }
      else if(win == 2){
         for(int i=0; i<p2.getTeam().size(); i++){
            s+=p2.getTeam().get(i).formatted();
            s+="\n";
         }
      }
      
      return s;
   
   
   }
   
   
   public int checkVictory(){
   
      boolean dead1 = true;
      
      for(int i=0; i < p1.getTeam().size(); i++){
         if(!p1.getTeam().get(i).getFainted()){
            dead1 = false;
         }
      }
      if(dead1){
         return 2;
      }
      
      boolean dead2 = true;
      
      for(int i=0; i < p2.getTeam().size(); i++){
         if(!p2.getTeam().get(i).getFainted()){
            dead2 = false;
         }
      }
      if(dead2){
         return 1;
      }
      
      return 0;

   
   
   }
   
   public PokemonTrainer getCurrentPlayer(){
      if(turn == 1){
         return p1;
      }
      else{
         return p2;
      }
   
   }
   
   public void printSwap(){
      
      if(turn==1){
         p1.printTeam();
      }
      else{
         p2.printTeam();
      }

   
   }
   
   
   public void executeBattle(){
   
      if(turn == 1){
      
         int damage = p1.getFirstPokemon().getAttack() - p2.getFirstPokemon().getDefense();
         
         p2.getFirstPokemon().setCurrentHP(p2.getFirstPokemon().getCurrentHP() - damage);
         
         System.out.println(p1.getFirstPokemon().getName() + " attacked, dealing " + damage + " damage!");
         
         p2.getFirstPokemon().fainted();
         
      }
      else if(turn == 2){
      
         int damage = p2.getFirstPokemon().getAttack() - p1.getFirstPokemon().getDefense();
         
         p1.getFirstPokemon().setCurrentHP(p1.getFirstPokemon().getCurrentHP() - damage);
         
         System.out.println(p2.getFirstPokemon().getName() + " attacked, dealing " + damage + " damage!");
         
         p1.getFirstPokemon().fainted();
      }

      switchTurn();
   
   }
   
   
   public void switchTurn(){
      if(turn == 1){
         turn =2;
      }
      else{
         turn = 1;
      }
   }
   


}