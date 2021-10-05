package map.tiles;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.concurrent.atomic.AtomicInteger;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Treasure extends Tile {
	private static final String REPRSENTATION = "T";
	AtomicInteger quantity;

	public Treasure(int coordX, int coordY, int qty) {
		super(coordX, coordY);
		quantity = new AtomicInteger(qty);
	}


	@Override
	public boolean isFranchissable() {
		return true;
	}

	public int getQuantity() {
		return quantity.get();
	}

	public boolean pickTreasure() {
		if (this.quantity.get() > 0) {
			this.quantity.decrementAndGet();
			return true;
		}
		return false;
	}

	@Override
	public String printFinalState() {
		return String.format("%s - %s - %d - %d", REPRSENTATION, getQuantity(), coordX, coordY);
	}
}
