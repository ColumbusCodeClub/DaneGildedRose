public class Sulfuras extends UsefulItem {

	public Sulfuras(Item item) {
		super(item);
	}

	@Override
	void decrementQuality() {
	}

	@Override
	public void decreaseSellinDate() {
	}

	@Override
	public void adjustQualityForItem() {
		decreaseSellinDate();
		decrementQualityBasedOnSellinDate();
	}

}
