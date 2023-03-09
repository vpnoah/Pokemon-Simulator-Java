import java.util.*;
import java.io.*;

public class PokemonClient{

   public static void main(String[] args){
   
      BattleManager bm = new BattleManager("player1.txt", "player2.txt");
      Scanner scan = new Scanner(System.in);
      boolean battleOn = true;
      int winningPlayer = 0;
      
      while(battleOn){
        
         if(bm.checkVictory() == 1){
            winningPlayer = 1;
            bm.win = 1;
            battleOn = false;
            break;
         }
         
         if(bm.checkVictory() == 2){
            winningPlayer = 2;
            bm.win = 2;
            battleOn = false;
            break;
         }
         
         if(bm.checkVictory() == 0){
            bm.autoSwap(bm.p1);
            bm.autoSwap(bm.p2);
         
         }
      
      
      
         bm.printMenu();
         int choice = 0;
         
         while(scan.hasNext()){
            if(scan.hasNextInt()){
               choice = scan.nextInt();
               break;
            }
            else{
               String trash = scan.next();
               System.out.println("Please select one of the options");
            }
         }
         
         if(choice == 1){
            bm.executeBattle();
         
         }
         else if(choice == 2){
            bm.printSwap();
            
            System.out.println("Please select which Pokemon to swap with");
            
            boolean correct = false;
            int choice2 = 0;
            
            while(!correct){
               if(scan.hasNextInt()){
                  choice2 = scan.nextInt();
                  
                  if(choice2<0 || choice2 > bm.getCurrentPlayer().getTeam().size()-1){
                     System.out.println("Please select a correct number");
                  }
                  else if(bm.getCurrentPlayer().getTeam().get(choice2).getFainted()){
                     System.out.println("Please select a non fainted Pokemon");
                  }
                  else{
                     correct = true;
                  }                
                  
               }
               else{
                  String trash = scan.next();
                  System.out.println("Please select one of the options");
               }
            }
            
            bm.getCurrentPlayer().swap(choice2);

            bm.switchTurn();
         
         }
         else if(choice == 3){
            battleOn = false;
            bm.switchTurn();
            bm.win = bm.turn;

         
         }
      
      
      }
      
      System.out.println("Winning Player is player " + bm.win);
      
      try{
         FileWriter fw = new FileWriter("WinningTeam.txt");
         fw.write(bm.getWinningTeam());
         fw.close();
      }
      catch(Exception e){
      
      
      }
   
   }


}