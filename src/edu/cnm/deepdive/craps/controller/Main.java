package edu.cnm.deepdive.craps.controller;

import edu.cnm.deepdive.craps.model.Game;
import edu.cnm.deepdive.craps.model.Game.State;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class Main {

  private Random rng;
  private Game game;

  public static void main(String[] args) {
    Main main = new Main();

    if(args.length>0){
      main.autoRun(Integer.parseInt(args[0]));
    }else{
      main.run();
    }
  }

  private Main() {
    rng = new SecureRandom();
    game = new Game(rng);
  }

  private void play() {
    Game.State state = game.play();
    for (Game.Roll roll : game.getRolls()) {
      System.out.print(roll);
    }

  }

  public void run() {
    try (Scanner scanner = new Scanner(System.in)) {
      do {
        play();
        System.out.printf("Wins: %d; Losses: %d.%nPlay again?%n", game.getWins(), game.getLosses());
        String input = scanner.next().trim();
        if (!input.isEmpty() && input.toLowerCase().charAt(0) != 'y') {
          break;
        }
      } while (true);
    }
  }

  public void autoRun(int trials) {
    for (int i = 0; i < trials; i++) {
      game.play();

    }
    int wins = game.getWins();
    int losses = game.getLosses();
    double winPercent = 100.0 * wins / (wins+losses);
    System.out.printf("Wins: %d; Losses: %d.%n; Winning Percentage = %f%%.%n", game.getWins(), game.getLosses(), winPercent);
  }

}
