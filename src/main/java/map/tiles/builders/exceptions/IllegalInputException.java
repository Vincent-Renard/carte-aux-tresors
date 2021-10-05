package map.tiles.builders.exceptions;

public class IllegalInputException extends RuntimeException {
	private static final String PATERN = "Input like %s was expected";

	public IllegalInputException(String inputPatern) {
		super(String.format(PATERN, inputPatern));
	}
}
