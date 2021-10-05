package map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Orientation {
	NORTH('N'), SOUTH('S'), EAST('E'), WEST('O');

	@Getter
	private char code;

	public static Orientation fromCode(String code) {

		switch (code.charAt(0)) {
			case 'N':
				return Orientation.NORTH;
			case 'S':
				return Orientation.SOUTH;
			case 'E':
				return Orientation.EAST;
			case 'O':
			case 'W':
				return Orientation.WEST;
			default:
				throw new UnsupportedOperationException("The code " + code + " is not supported!");
		}

	}

}
