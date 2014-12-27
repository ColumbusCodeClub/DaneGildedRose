public class Sulfuras extends UsefulItem {

	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

	public Sulfuras(Item item) {
		super(item);
	}

	@Override
	public String name() {
		return SULFURAS;
	}

	@Override
	public void decrementQuality() {
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
