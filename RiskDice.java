public class RiskDice {
	private int m1, m2;

	public RiskDice(int numDice) {
		m1 = m2 = 0;
		for (int i = 0; i < numDice; i++) {
			int value = (int)(Math.random() * 6) + 1;
			if (value > m1) {
				int temp = m1;
				m1 = value;
				m2 = temp;
			} else if (value > m2) {
				m2 = value;
			}
		}
	}

	public int getM1() { return m1; }
	public int getM2() { return m2; }
}