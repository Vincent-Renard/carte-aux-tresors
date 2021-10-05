package map.builders;

import map.WorldMap;
import map.tiles.builders.exceptions.IllegalInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MapBuilder {

	private static final String INPUT_PATERN = "C - ([0-9]+) - ([0-9]+)";
	private final Pattern pattern = Pattern.compile(INPUT_PATERN);

	public WorldMap from(String strInput) {
		Matcher matcher = pattern.matcher(strInput);

		if (matcher.groupCount() == 2) {
			matcher.find();
			return new WorldMap(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
		} else {
			throw new IllegalInputException(INPUT_PATERN);
		}


	}
}
