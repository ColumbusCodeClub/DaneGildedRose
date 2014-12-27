public class BackStagePass extends UsefulItem {

	public BackStagePass(Item item) {
		super(item);
	}

	@Override
	public void incrementQuality() {
		super.incrementQuality();
		if (quality() < 11) {
			super.incrementQuality();
		}

		if (sellIn() < 6) {
			super.incrementQuality();
		}
	}

}
