package map.tiles.builders;

import map.tiles.Treasure;
import map.tiles.builders.exceptions.IllegalInputException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TreasureBuilder extends TileBuilder<Treasure> {
	private static final String INPUT_PATERN = "T - ([0-9]+) - ([0-9]+) - ([0-9]+)";

	private final Pattern pattern = Pattern.compile(INPUT_PATERN);

	@Override
	public Treasure from(String strInput) {
		Matcher matcher = pattern.matcher(strInput);


		if (matcher.groupCount() == 3) {
			matcher.find();
			return new Treasure(Integer.parseInt(matcher.group(1)),
					Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
		} else {
			throw new IllegalInputException(INPUT_PATERN);
		}
	}
}
