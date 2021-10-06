package map.tiles;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public abstract class Tile implements Franchisable {

	int coordX;
	int coordY;


}
