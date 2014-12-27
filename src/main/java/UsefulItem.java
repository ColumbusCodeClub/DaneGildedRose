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

	void decreaseSellinDate() {
		setSellIn(sellIn() - 1);
	}

	void setQuality(int quality) {
		this.quality = quality;
	}

	private void setSellIn(int sellIn) {
		this.sellIn = sellIn;
	}

	void decrementQuality() {
		this.quality -= 1;
	}

	public void incrementQuality() {
		if (quality < 50) {
			this.quality += 1;
		}
	}

	void adjustQualityForItem() {
		if (qualityAboveZero()) {
			decrementQuality();
		}

		decreaseSellinDate();
		decrementQualityBasedOnSellinDate();
	}

	protected boolean qualityAboveZero() {
		return quality() > 0;
	}

	public void decrementQualityBasedOnSellinDate() {
		if (sellIn() < 0) {
			if (qualityAboveZero()) {
				decrementQuality();
			}
		}

	}

}
