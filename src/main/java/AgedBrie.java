public class AgedBrie extends UsefulItem {

	public AgedBrie(Item item) {
		super(item);
	}

	@Override
	public void decrementQualityBasedOnSellinDate() {
		if (sellIn() < 0) {
			{
				incrementQuality();
			}
		}
	}

	@Override
	public void adjustQualityForItem() {
		incrementQuality();
		decreaseSellinDate();
		decrementQualityBasedOnSellinDate();
	}

}
