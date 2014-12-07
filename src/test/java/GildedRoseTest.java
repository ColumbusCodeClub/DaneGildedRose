import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {

	private static final int MAX_QUALITY_INCREASE = 50;

	private static final String AGED_BRIE = "Aged Brie";
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

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
	
	@Test
	public void qualityForBrieIncreasesUntilFifty() {
		Item item = new ItemBuilder().setName(AGED_BRIE).setQuality(1).setSellIn(1).build();
		checkQualityTimes(item, MAX_QUALITY_INCREASE);
		
		assertEquals(50, item.quality);
	}
	
	@Test
	public void sellInDateContinuesToDecreaseBelowZero() {
		Item item = new ItemBuilder().setName(AGED_BRIE).setQuality(1).setSellIn(1).build();
		checkQualityTimes(item, MAX_QUALITY_INCREASE);
		
		assertEquals(-49, item.sellIn);
	}
	
	@Test
	public void sellInDateDoesNotChangeForSulfuras() {
		Item item = new ItemBuilder().setName(SULFURAS).setQuality(1).setSellIn(1).build();
		checkQualityTimes(item, MAX_QUALITY_INCREASE);
		
		assertEquals(1, item.sellIn);
	}
	
	@Test
	public void qualityForBackStagePassesIncreaseByThreeIfUnderSix() {
		Item item = new ItemBuilder().setName(BACKSTAGE_PASSES).setQuality(1).setSellIn(1).build();
		checkQualityTimes(item, 1);
		
		assertEquals(4, item.quality);
	}

	private void checkQualityTimes(Item item, int timesToCheck) {
		for(int qualityChecks = 1; qualityChecks <=timesToCheck; qualityChecks++) {
			subject.adjustQualityForItem(item);
		}
	}
	
	private Item createGenericItemWithQuality(int quality) {
		return new ItemBuilder().setName(genericItemName).setQuality(quality).setSellIn(1).build();
	}
}
