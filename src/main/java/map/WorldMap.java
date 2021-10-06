package map;


import core.Direction;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import map.tiles.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
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


	public void saveFinalOutput(String pathOuputFile) {

		List<String> lines = new ArrayList<>();
		lines.add(String.format("C - %d - %d", width, height));

		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				var tile = map[x][y].peek();
				if (tile instanceof Printable) {
					var printable = (Printable) tile;

					lines.add(printable.printFinalState());
				}

			}
		}
		try (FileWriter writer = new FileWriter(pathOuputFile)) {

			for(String line : lines) {
				writer.write(line + "\n");
			}

		} catch (IOException e) {
			System.err.println("Oups");
		}
	}

	public void printMap() {

	}


	public void play() {
		int tour = 0;

		while (raiders.stream().anyMatch(Raider::hasMoreWalks)) {
			var raiderIdx = tour % raiders.size();
			var currentRaider = raiders.get(raiderIdx);
			var currentDirection = currentRaider.nextMove();


			currentRaider = (Raider) map[currentRaider.getCoordX()][currentRaider.getCoordY()].peek();
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

			printMap();
		}

	}


}
