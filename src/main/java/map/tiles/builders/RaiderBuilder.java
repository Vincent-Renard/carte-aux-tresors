package map.tiles.builders;

import core.Direction;
import map.Orientation;
import map.tiles.Raider;
import map.tiles.builders.exceptions.IllegalInputException;

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
			var directions = Stream.of(matcher.group(5).toCharArray()).map(c -> c[0]).map(Direction::fromCode).collect(Collectors.toList());
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
