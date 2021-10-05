package map.tiles.builders;

import map.tiles.Tile;

public abstract class TileBuilder<T extends Tile> {

	abstract T from(String strInput);

}
