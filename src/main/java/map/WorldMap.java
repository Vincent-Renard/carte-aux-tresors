package map;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import map.tiles.Printable;
import map.tiles.Raider;
import map.tiles.Tile;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorldMap {
	final int height;
	final int width;

	Stack<Printable>[][] map;

	List<Raider> raiders;

	public WorldMap(int width, int height) {
		this.width = width;
		this.height = height;

		this.map = new Stack[this.width][this.height];
	}

	private void fillInitialMap() {

		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				this.map[x][y] = new Stack<>();
			}
		}

	}

	public void fillMap(List<Tile> gameInput) {
		this.fillInitialMap();
		gameInput.forEach(tile -> map[tile.getCoordX()][tile.getCoordY()].push(tile));

		raiders = gameInput.stream().filter(tile -> tile instanceof Raider).map(raider -> (Raider) raider).collect(Collectors.toList());

	}

	public void printMap() {
		System.out.println(String.format("C - %d - %d", width, height));


		for(var tilesArray : map) {
			for(var tile : tilesArray) {
				if (!tile.isEmpty()) {

					System.out.println(tile.peek().printFinalState());
				}
			}
		}
	}

	public void play() {
		int tour = 0;

		while (raiders.stream().anyMatch(Raider::hasMoreWalks)) {
			var currentraider = raiders.get(tour % raiders.size());


			tour++;
		}

	}


}
