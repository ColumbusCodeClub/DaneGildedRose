public class AgedBrie extends UsefulItem {

	private static final String AGED_BRIE = "Aged Brie";

	public AgedBrie(Item item) {
		super(item);
	}

	@Override
	public String name() {
		return AGED_BRIE;
	}

	@Override
	public void decrementQualityBasedOnSellinDate() {
		if (sellIn() < 0) {
			{
				incrementQuality();
			}
		}
	}

}
