package map.tiles;

public class Plain extends Tile {

	public Plain(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isFranchissable() {
		return true;
	}


}
