public class UsefulItem {

	private int quality = 0;
	private int sellIn = 0;

	public UsefulItem(Item item) {
		this.quality = item.getQuality();
		this.sellIn = item.getSellIn();
	}

	int quality() {
		return quality;
	}

	int sellIn() {
		return sellIn;
	}

	protected void decreaseSellinDate() {
		this.sellIn = sellIn - 1;
	}

	protected void setQuality(int quality) {
		this.quality = quality;
	}

	void decrementQuality() {
		this.quality -= 1;
	}

	public void incrementQuality() {
		if (belowMaxQuality()) {
			this.quality += 1;
		}
	}

	protected void adjustQuality() {
		decrementQualityAboveZero();
		decreaseSellinDate();
		decrementQualityBasedOnSellinDate();
	}

	protected boolean qualityAboveZero() {
		return quality() > 0;
	}

	protected void decrementQualityBasedOnSellinDate() {
		if (sellIn() < 0) {
			decrementQualityAboveZero();
		}

	}
	
	private boolean belowMaxQuality() {
		return quality < 50;
	}

	private void decrementQualityAboveZero() {
		if (qualityAboveZero()) {
			decrementQuality();
		}
	}

}
