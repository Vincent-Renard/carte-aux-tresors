package map.tiles.builders;

import core.Direction;
import map.Orientation;
import map.tiles.Raider;
import map.tiles.builders.exceptions.IllegalInputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class RaiderBuilder extends TileBuilder<Raider> {

	private static final String INPUT_PATERN = "A - ([A-z]+) - ([0-9]+) - ([0-9]+) - ([NSEO]) - ([AGD]+)";
	private final Pattern pattern = Pattern.compile(INPUT_PATERN);

	@Override
	public Raider from(String strInput) {
		Matcher matcher = pattern.matcher(strInput);


		if (matcher.groupCount() == 5) {
			matcher.find();
			//char[] dirs =
			String lineDirections = matcher.group(5);

			List<Direction> directions = new ArrayList<>();
			for (char c :lineDirections.toCharArray() ){
				directions.add(Direction.fromCode(c));
			}



			return new Raider(Integer.parseInt(matcher.group(2)),
					Integer.parseInt(matcher.group(3)),
					matcher.group(1),
					Orientation.fromCode(matcher.group(4)),
					directions);
		} else {
			throw new IllegalInputException(INPUT_PATERN);
		}

	}
}
