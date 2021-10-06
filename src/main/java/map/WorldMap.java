package map;


import core.Direction;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import map.tiles.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorldMap {
	final int width;
	final int height;

	Deque<Tile>[][] map;

	List<Raider> raiders;

	public WorldMap(int width, int height) {
		this.width = width;
		this.height = height;

		this.map = new Deque[this.width][this.height];
	}

	private void fillInitialMap() {

		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				this.map[x][y] = new ArrayDeque<>();
				this.map[x][y].push(new Plain(x, y));
			}
		}

	}

	public void fillMap(List<Tile> gameInput) {
		this.fillInitialMap();

		raiders = gameInput.stream().filter(tile -> tile instanceof Raider)
				.map(raider -> (Raider) raider)
				.collect(Collectors.toList());
		gameInput.forEach(tile -> map[tile.getCoordX()][tile.getCoordY()].push(tile));

	}

	public void printMap() {
		System.out.printf("C - %d - %d%n", width, height);

		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				var tile = map[x][y].peek();
				if (tile instanceof Printable) {
					var printable = (Printable) tile;

					System.out.println(printable.printFinalState());
				}

			}
		}
	}


	public void play() {
		int tour = 0;

		while (raiders.stream().anyMatch(Raider::hasMoreWalks)) {
			var raiderIdx = tour % raiders.size();
			var currentRaider = raiders.get(raiderIdx);
			var currentDirection = currentRaider.nextMove();


			currentRaider = (Raider) map[currentRaider.getCoordX()][currentRaider.getCoordY()].peek();
			System.out.println(currentDirection);
			if (currentDirection.equals(Direction.GO_HEAD)) {

				var nextY = currentRaider.getCoordY();
				var nextX = currentRaider.getCoordX();
				if (currentRaider.getOrientation().equals(Orientation.NORTH) && nextY - 1 != -1) {
					nextY--;
				}
				if (currentRaider.getOrientation().equals(Orientation.SOUTH) && nextY + 1 != height) {
					nextY++;
				}
				if (currentRaider.getOrientation().equals(Orientation.WEST) && nextX - 1 != 0) {
					nextX--;
				}
				if (currentRaider.getOrientation().equals(Orientation.EAST) && nextX + 1 != width) {
					nextX++;
				}
				if (map[nextX][nextY].peek().isFranchissable()) {
					map[currentRaider.getCoordX()][currentRaider.getCoordY()].pop();
					currentRaider.setCoordX(nextX);
					currentRaider.setCoordY(nextY);
					map[nextX][nextY].push(currentRaider);
				}
			} else {
				currentRaider.turn(currentDirection);
			}

			currentRaider = (Raider) map[currentRaider.getCoordX()][currentRaider.getCoordY()].pop();

			if ( map[currentRaider.getCoordX()][currentRaider.getCoordY()].peek() instanceof Treasure) {
				var treasure = (Treasure) map[currentRaider.getCoordX()][currentRaider.getCoordY()].pop();
				if (treasure.pickTreasure())
					currentRaider.takeTreasure();
				if (treasure.getQuantity()>0)
					map[currentRaider.getCoordX()][currentRaider.getCoordY()].push(treasure);
			}
			map[currentRaider.getCoordX()][currentRaider.getCoordY()].push(currentRaider);

			raiders.set(raiderIdx, currentRaider);
			tour++;
			System.out.printf("Tour n°%d %n", tour);

			printMap();
		}

	}


}
