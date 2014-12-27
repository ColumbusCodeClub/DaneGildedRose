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

	@Override
	public void decrementQualityBasedOnSellinDate() {
		if (sellIn() < 0) {
			setQuality(0);
		}
	}

	@Override
	public void adjustQuality() {
		incrementQuality();
		decreaseSellinDate();
		decrementQualityBasedOnSellinDate();
	}

}
