class Battle {
	private int attack, defense;
	private DefenseTactic tactic;

	public static interface DefenseTactic {
		public int numDice(int high1, int high2);
	}

	public Battle(int attack, int defense, DefenseTactic tactic) {
		this.attack = attack;
		this.defense = defense;
		this.tactic = tactic;
	}

	public int simulateBattle() {
		int result = battleResult();
		while (result == 0) {
			attack();
			result = battleResult();
		}
		return result;
	}

	public void attack() { // assumes battleOver returns false
		RiskDice attackingDice = new RiskDice(Math.min(3, attack - 1));

		int lesser = defense < attack - 1 ? defense:attack - 1;

		RiskDice defendingDice = new RiskDice(Math.min(lesser,
			tactic.numDice(attackingDice.getM1(), attackingDice.getM2())));

		if (attackingDice.getM1() > defendingDice.getM1())
			defense--;
		else attack--;

		if (defendingDice.getM2() != 0)
			if (attackingDice.getM2() > defendingDice.getM2())
				defense--;
			else attack--;
	}

	public int battleResult() {
		if (defense == 0)
			return 1;
		else if (attack == 1)
			return -1;
		else return 0;
	}

	public boolean battleOver() {
		return battleResult() != 0;
	}

	public int getNumAttackers() { return attack; }
	public int getNumDefenders() { return defense; }
}