public class BackStagePass extends UsefulItem {

	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

	public BackStagePass(Item item) {
		super(item);
	}

	@Override
	public String name() {
		return BACKSTAGE_PASSES;
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
	public void adjustQualityForItem() {
		incrementQuality();
		decreaseSellinDate();
		decrementQualityBasedOnSellinDate();
	}

}
