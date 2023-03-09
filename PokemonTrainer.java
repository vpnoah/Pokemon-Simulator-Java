import java.io.*;
import java.util.*;

public class PokemonTrainer{

   private ArrayList<Pokemon> team;
   
   public PokemonTrainer(){
      team = new ArrayList<Pokemon>();
   }
   
   public Pokemon getFirstPokemon(){
      return team.get(0);
   }
   
   public ArrayList<Pokemon> getTeam(){
      return team;
   }
   
   public void swap(int index){
   
      Pokemon first = team.get(0);
      Pokemon toSwap = team.get(index);
      
      team.set(0, toSwap);
      team.set(index, first);
   
   }
   
   public void printTeam(){
   
      System.out.println("Current Pokemon: " + getFirstPokemon());
      
      for(int i=1; i<team.size(); i++){
         System.out.println(i + ". " + team.get(i));

      }
   
   
   }
   

}