import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {

	private static final int MAX_QUALITY = 50;

	private GildedRose subject;

	private String genericItemName = "name";
	
	@Before
	public void setup() {
		subject = new GildedRose();
	}

	@Test
	public void ifQualityBelowFiftyThenAddQualityofOne() {
		Item item = createGenericItemWithQuality(1);

		subject.incrementQuality(item);

		assertEquals(2, item.quality);
	}

	@Test
	public void ifQualityIsFiftyThenQualityDoesNotIncrement() {
		Item item = createGenericItemWithQuality(MAX_QUALITY);

		subject.incrementQuality(item);

		assertEquals(MAX_QUALITY, item.quality);
	}

	@Test
	public void updateQualityForGeneralItem() {
		Item item = createGenericItemWithQuality(MAX_QUALITY);

		subject.adjustQualityForItem(item);

		assertEquals(49, item.quality);
	}

	@Test
	public void qualityNeverNegative() {
		Item item = new ItemBuilder().setName(genericItemName).setQuality(0)
				.setSellIn(1).build();

		subject.adjustQualityForItem(item);

		assertEquals(0, item.quality);
	}
	
	private Item createGenericItemWithQuality(int quality) {
		return new ItemBuilder().setName(genericItemName).setQuality(quality).setSellIn(1).build();
	}
}
