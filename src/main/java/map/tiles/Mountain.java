package map.tiles;

public class Mountain extends Tile implements Printable {

	private static final String REPRESENTATION = "M";

	public Mountain(int coordX, int coordY) {
		super(coordX, coordY);
	}

	@Override
	public boolean isFranchissable() {
		return false;
	}

	@Override
	public String printFinalState() {
		return String.format("%s - %d - %d", REPRESENTATION, coordX, coordY);
	}
}
