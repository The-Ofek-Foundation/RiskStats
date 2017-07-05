public class RiskStats {
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

	public void simulateBattles(int numBattles, int numAttack, int numDefense,
		Battle.DefenseTactic tactic) {
		System.out.println();
		int attackWins = 0, defenseWins = 0;
		for (int i = 0; i < numBattles; i++)
			if (new Battle(numAttack, numDefense, tactic).simulateBattle() == 1)
				attackWins++;
			else defenseWins++;

		System.out.printf("%d attack wins.\n%d defense wins.\n",
			attackWins, defenseWins);
		System.out.printf("%.2f%% chance of winning.\n\n",
			(attackWins * 100f / numBattles));
	}
}
