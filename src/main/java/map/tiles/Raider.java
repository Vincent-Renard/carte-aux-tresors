package map.tiles;


import core.Direction;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import map.Orientation;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class Raider extends Tile implements Printable {

	private static final String REPRESENTATION = "A";

	@Getter
	String name;

	@Getter
	Orientation orientation;

	int treasures;


	Deque<Direction> directions;

	public Raider(int coordX, int coordY, String name, Orientation orientation, List<Direction> dirs) {
		super(coordX, coordY);
		this.name = name;
		this.directions = new ArrayDeque<>();
		this.directions.addAll(dirs);
		this.orientation = orientation;
	}


	@Override
	public boolean isFranchissable() {
		return false;
	}

	@Override
	public String printFinalState() {
		return String.format("%s - %s - %d - %d - %s - %d %s",
				REPRESENTATION, name, coordX, coordY, orientation.getCode(), this.treasures, Arrays.toString(this.directions.toArray()));
	}

	public boolean hasMoreWalks() {
		return !directions.isEmpty();
	}

	public void takeTreasure() {
		treasures++;
	}

	public Direction nextMove() {
		return directions.pop();
	}

	public void turn(Direction direction) {

		int idx = 0;
		if (direction.equals(Direction.TURN_LEFT)) {
			idx = -1;
		}
		if (direction.equals(Direction.TURN_RIGHT)) {
			idx = 1;
		}

		orientation = Orientation.values()[(List.of(Orientation.values()).indexOf(orientation) + idx + 4) % 4];


	}
}
