import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {

	private static final int MAX_QUALITY_INCREASE = 50;

	private static final String AGED_BRIE = "Aged Brie";
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

	private static final int MAX_QUALITY = 50;

	private GildedRose underTest;

	private String genericItemName = "name";
	
	@Before
	public void setup() {
		underTest = new GildedRose();
	}

	@Test
	public void ifQualityBelowFiftyThenAddQualityofOne() {
		Item item = createGenericItemWithQuality(1);
		UsefulItem usefulItem = new UsefulItem(item);
		
		
		underTest.incrementQuality(usefulItem);

		assertEquals(2, usefulItem.quality());
	}

	@Test
	public void ifQualityIsFiftyThenQualityDoesNotIncrement() {
		Item item = createGenericItemWithQuality(MAX_QUALITY);
		UsefulItem usefulItem = new UsefulItem(item);
		
		
		underTest.incrementQuality(usefulItem);

		assertEquals(MAX_QUALITY, usefulItem.quality());
	}

	@Test
	public void updateQualityForGeneralItem() {
		Item item = createGenericItemWithQuality(MAX_QUALITY);
		UsefulItem usefulItem = new UsefulItem(item);

		underTest.adjustQualityForItem(usefulItem);

		assertEquals(49, usefulItem.quality());
	}

	@Test
	public void qualityNeverNegative() {
		Item item = new ItemBuilder().setName(genericItemName).setQuality(0)
				.setSellIn(1).build();
		UsefulItem usefulItem = new UsefulItem(item);

		underTest.adjustQualityForItem(usefulItem);

		assertEquals(0, item.quality);
	}
	
	@Test
	public void qualityForBrieIncreasesUntilFifty() {
		Item item = new ItemBuilder().setName(AGED_BRIE).setQuality(1).setSellIn(1).build();
		UsefulItem usefulItem = new UsefulItem(item);
		checkQualityTimes(usefulItem, MAX_QUALITY_INCREASE);
		
		assertEquals(50, usefulItem.quality());
	}
	
	@Test
	public void sellInDateContinuesToDecreaseBelowZero() {
		Item item = new ItemBuilder().setName(AGED_BRIE).setQuality(1).setSellIn(1).build();
		UsefulItem usefulItem = new UsefulItem(item);
		checkQualityTimes(usefulItem, MAX_QUALITY_INCREASE);
		
		assertEquals(-49, usefulItem.sellIn());
	}
	
	@Test
	public void sellInDateDoesNotChangeForSulfuras() {
		Item item = new ItemBuilder().setName(SULFURAS).setQuality(1).setSellIn(1).build();
		UsefulItem usefulItem = new UsefulItem(item);
		checkQualityTimes(usefulItem, MAX_QUALITY_INCREASE);
		
		assertEquals(1, usefulItem.sellIn());
	}
	
	@Test
	public void qualityForBackStagePassesIncreaseByThreeIfSellInUnderSix() {
		Item item = new ItemBuilder().setName(BACKSTAGE_PASSES).setQuality(1).setSellIn(1).build();
		UsefulItem usefulItem = new UsefulItem(item);
		checkQualityTimes(usefulItem, 1);
		
		assertEquals(4, usefulItem.quality());
	}
	
	@Test
	public void qualityForBackStagePassesIncreaseByTwoIfSellinOver11() {
		Item item = new ItemBuilder().setName(BACKSTAGE_PASSES).setQuality(1).setSellIn(12).build();
		UsefulItem usefulItem = new UsefulItem(item);
		checkQualityTimes(usefulItem, 1);
		
		assertEquals(3, usefulItem.quality());
	}
	
	@Test
	public void  sellInUnderZeroForGeneralItemReducesQuality() {
		Item item = new ItemBuilder().setName(genericItemName).setQuality(3).setSellIn(-1).build();
		UsefulItem usefulItem = new UsefulItem(item);
		checkQualityTimes(usefulItem, 1);
		
		assertEquals(1, usefulItem.quality());
	}

	@Test
	public void  sellInUnderZeroForGeneralItemWithQualityAlreadyZero() {
		Item item = new ItemBuilder().setName(genericItemName).setQuality(0).setSellIn(-1).build();
		UsefulItem usefulItem = new UsefulItem(item);
		checkQualityTimes(usefulItem, 1);
		
		assertEquals(0, usefulItem.quality());
	}
	
	@Test
	public void  sellInUnderZeroForSulfarsDoesNotDecrementQuality() {
		Item item = new ItemBuilder().setName(SULFURAS).setQuality(1).setSellIn(-1).build();
		UsefulItem usefulItem = new UsefulItem(item);
		checkQualityTimes(usefulItem, 1);
		
		assertEquals(1, usefulItem.quality());
	}
	
	@Test
	public void  sellInUnderZeroForBackStagePassReduceQualityToZero() {
		Item item = new ItemBuilder().setName(BACKSTAGE_PASSES).setQuality(10).setSellIn(-1).build();
		UsefulItem usefulItem = new UsefulItem(item);
		checkQualityTimes(usefulItem, 1);
		
		assertEquals(0, usefulItem.quality());
	}
	
	@Test
	public void shouldTakeListOfItemsAndCallAdjustQualityForAll() {
		Item item = new ItemBuilder().setName(BACKSTAGE_PASSES).setQuality(10).setSellIn(-1).build();
		Item item2 = new ItemBuilder().setName(genericItemName).setQuality(3).setSellIn(-1).build();
		UsefulItem usefulItem = new UsefulItem(item);
		UsefulItem usefulItem2 = new UsefulItem(item2);
		
		underTest.addItem(usefulItem);
		underTest.addItem(usefulItem2);
		underTest.updateQuality();

		assertEquals(0, usefulItem.quality());
		assertEquals(1, usefulItem2.quality());
	}
	
	@Test
	public void usefulItemShouldTakeAndReturnPartsOfIt() {
		Item item = new Item("Some Item", 1, 2);
		
		UsefulItem useful = new UsefulItem(item);
		assertEquals(2, useful.quality());
		assertEquals(1, useful.sellIn());
		assertEquals("Some Item", useful.name());
	}

	private void checkQualityTimes(UsefulItem item, int timesToCheck) {
		for(int qualityChecks = 1; qualityChecks <=timesToCheck; qualityChecks++) {
			underTest.adjustQualityForItem(item);
		}
	}
	
	private Item createGenericItemWithQuality(int quality) {
		return new ItemBuilder().setName(genericItemName).setQuality(quality).setSellIn(1).build();
	}
}
