import java.text.DecimalFormat;
import java.util.Random;

public class RockPaperScissors {
	public static void main(String[] args) {
		Player zhangSan = new Player("ZhangSan");
		Player liSi = new Player("LiSi");
		Player wangWu = new Player("WangWu");
		Game game = new Game();
		System.out.println("Game.run Test");
		game.setFirstPlayer(zhangSan);
		game.setSecondPlayer(liSi);
		game.run(5);
		System.out.println("\nPlayer.reset test");
		System.out.println("before reset:");
		System.out.printf("victoryTimes : %d\n", liSi.getVictoryTimes());
		System.out.printf("gameTimes : %d\n", liSi.getGameTimes());
		liSi.reset();
		System.out.println("after reset:");
		System.out.printf("victoryTimes : %d\n", liSi.getVictoryTimes());
		System.out.printf("gameTimes : %d\n", liSi.getGameTimes());
		System.out.println("\nGame.setFirstPlayer test");
		game.setFirstPlayer(wangWu);
		game.run(5);
	}
}

class Player{
	private String name;
	private int victoryTimes = 0;
	private int gameTimes = 0;
	public Player(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public int getVictoryTimes() {
		return victoryTimes;
	}
	public int getGameTimes() {
		return gameTimes;
	}
	public void reset() {
		victoryTimes = 0;
		gameTimes = 0;
	}
	public void recordGame(boolean isVictory) {
		++gameTimes;
		if (isVictory) {
			++victoryTimes;
		}
	}
	public int chooseShape() {
		return new Random().nextInt(3);
	}
	public void showMetrics() {
		double winning_rate = (double)victoryTimes / (double)gameTimes;
		System.out.println(name + ":" + new DecimalFormat("0.000000").format(winning_rate));
	}
}

class Game{
	private Player firstPlayer;
	private Player secondPlayer;
	private String [] shapes = {"rock" , "paper" , "scissor"};
	public void setFirstPlayer(Player player) {
		firstPlayer = player;
	}
	public void setSecondPlayer(Player player) {
		secondPlayer = player;
	}
	public String getFirstPlayer() {
		return firstPlayer.getName();
	}
	public String getSecondPlayer() {
		return secondPlayer.getName();
	}
	public void run(int n) {
		for (int i = 0; i < n; ++i) {
			int firstPlayer_shape = firstPlayer.chooseShape();
			int secondPlayer_shape = secondPlayer.chooseShape();
			String result = null;
			if (firstPlayer_shape == secondPlayer_shape) {
				firstPlayer.recordGame(false);
				secondPlayer.recordGame(false);
				result = "Draw";
			}
			else if (firstPlayer_shape == 0 && secondPlayer_shape == 2 || 
					firstPlayer_shape == 1 && secondPlayer_shape == 0 || 
					firstPlayer_shape == 2 && secondPlayer_shape == 1) {
				firstPlayer.recordGame(true);
				secondPlayer.recordGame(false);
				result = firstPlayer.getName();
			}
			else {
				firstPlayer.recordGame(false);
				secondPlayer.recordGame(true);
				result = secondPlayer.getName();
			}
			System.out.println(firstPlayer.getName() + ":" + shapes[firstPlayer_shape] + " " +
					secondPlayer.getName() + ":" + shapes[secondPlayer_shape] + " " + 
					"result:" + result);
		}
		firstPlayer.showMetrics();
		secondPlayer.showMetrics();
	}
}
