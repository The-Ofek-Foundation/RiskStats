public class RiskStats {

	private final double MAX_SCALE = 100;

	public static void main(String... pumpkins) {
		RiskStats rs = new RiskStats();
		rs.simulateBattles(1000000, Integer.parseInt(pumpkins[0]),
			Integer.parseInt(pumpkins[1]));
	}

	public void simulateBattles(int numBattles, int numAttack, int numDefense) {

		/* Here put your best strategy for how many dice to roll
		 * The following is an easy one; if the sum of the two highest
		 * offensive dice is greater than 9, roll one die. Otherwise, roll
		 * two dice (if possible).
		 *
		 * Note that m1 is the highest attacking die, and m2 is the second
		 * highest. Don't worry about situations where only 1 die is possible.
		 */

		// simulateBattles(numBattles, numAttack, numDefense,
		// 	(m1, m2) -> m1 + m2 > 9 ? 1:2);

		simulateBattles(numBattles, numAttack, numDefense, (m1, m2) -> {
			// enter your own function here:

			if (m2 < 4)
				return 2;
			return 1;
		});

	}

	private void simulateBattles(int numBattles, int numAttack, int numDefense,
		Battle.DefenseTactic tactic) {
		System.out.println();
		int[] attackersLeft = new int[numAttack + 1];
		int[] defendersLeft = new int[numDefense + 1];
		for (int i = 0; i < numBattles; i++) {
			Battle battle = new Battle(numAttack, numDefense, tactic);
			battle.simulateBattle();
			attackersLeft[battle.getNumAttackers()]++;
			defendersLeft[battle.getNumDefenders()]++;
		}

		System.out.printf("%d attack wins.\n%d defense wins.\n",
			defendersLeft[0], attackersLeft[1]);
		System.out.printf("%.2f%% chance of winning.\n\n",
			(defendersLeft[0] * 100f / numBattles));

		int maxVal = attackersLeft[0] = -1;
		for (int i : attackersLeft) maxVal = Math.max(maxVal, i);
		for (int i : defendersLeft) maxVal = Math.max(maxVal, i);

		System.out.println("ATTACKERS LEFT HISTOGRAM\n");
		outputHistogram(attackersLeft, numBattles, maxVal);

		System.out.println("\n\nDEFENDERS LEFT HISTOGRAM\n");
		outputHistogram(defendersLeft, numBattles, maxVal);
	}

	private void outputHistogram(int[] numLeft, int total, int maxVal) {
		for (int i = 0; i < numLeft.length; i++) {
			if (numLeft[i] < 0) continue; // for 0 attackers
			System.out.printf("%3d | %5.2f%% | ", i, numLeft[i] * 100f / total);
			for (int a = 0; a < numLeft[i] * MAX_SCALE / maxVal; a++)
				System.out.print("X");
			System.out.println();
		}
	}
}
