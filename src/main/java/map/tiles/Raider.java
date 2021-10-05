package map.tiles;


import core.Direction;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import map.Orientation;

import java.util.List;
import java.util.Stack;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class Raider extends Tile {

	private static String REPRSENTATION = "A";

	String name;

	@Getter
	Orientation orientation;

	int treasures;


	Stack<Direction> directions;

	public Raider(int coordX, int coordY, String name, Orientation orientation, List<Direction> dirs) {
		super(coordX, coordY);
		this.name = name;
		this.directions = new Stack<>();
		this.directions.addAll(dirs);
		this.orientation = orientation;
	}


	@Override
	public boolean isFranchissable() {
		return true;
	}

	@Override
	public String printFinalState() {
		return String.format("%s - %s - %d - %d - %s - %d",
				REPRSENTATION, name, coordX, coordY, orientation.getCode(), this.treasures);
	}

	public boolean hasMoreWalks() {
		return !directions.empty();
	}

	public void tackTreasure() {
		treasures++;
	}

	public Direction nextMove() {
		return directions.pop();
	}
}
