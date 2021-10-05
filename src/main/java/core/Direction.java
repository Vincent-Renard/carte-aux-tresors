package core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Direction {
	GO_HEAD('A'),
	TURN_RIGHT('D'),
	TURN_LEFT('G');

	private char code;

	public static Direction fromCode(char code) {
		switch (code) {
			case 'A':
				return Direction.GO_HEAD;
			case 'D':
				return Direction.TURN_RIGHT;
			case 'G':
				return Direction.TURN_LEFT;
			default:
				throw new UnsupportedOperationException("The code " + code + " is not supported!");

		}

	}

}
