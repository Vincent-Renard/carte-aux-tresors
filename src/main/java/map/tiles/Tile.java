package map.tiles;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public abstract class Tile implements Franchisable, Printable {

	int coordX;
	int coordY;


}
