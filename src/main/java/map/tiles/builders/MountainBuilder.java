package map.tiles.builders;

import map.tiles.Mountain;
import map.tiles.builders.exceptions.IllegalInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MountainBuilder extends TileBuilder<Mountain> {

	private static final String INPUT_PATERN = "M - ([0-9]+) - ([0-9]+)";
	private final Pattern pattern = Pattern.compile(INPUT_PATERN);

	@Override
	public Mountain from(String strInput) {
		Matcher matcher = pattern.matcher(strInput);


		if (matcher.groupCount() == 2) {
			matcher.find();
			return new Mountain(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
		} else {
			throw new IllegalInputException(INPUT_PATERN);
		}
	}
}
