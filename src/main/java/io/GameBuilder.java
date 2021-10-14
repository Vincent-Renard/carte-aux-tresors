package io;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import map.WorldMap;
import map.builders.MapBuilder;
import map.tiles.Tile;
import map.tiles.builders.MountainBuilder;
import map.tiles.builders.RaiderBuilder;
import map.tiles.builders.TreasureBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FieldDefaults(level = AccessLevel.PRIVATE)
public final class GameBuilder {


	public static final char COMMENT_CHAR = '#';
	static final RaiderBuilder raiderBuilder = new RaiderBuilder();
	static final TreasureBuilder treasureBuilder = new TreasureBuilder();
	static final MountainBuilder mountainBuilder = new MountainBuilder();
	static final MapBuilder mapBuilder = new MapBuilder();
	private static final char MAP_HEADER_CHAR = 'C';
	final String pathFilename;


	public GameBuilder(String pathIntputMapFile) {
		this.pathFilename = pathIntputMapFile;

	}

	public WorldMap build() throws IOException {
		List<String> inputs;
		try (Stream<String> lines = Files.lines(Paths.get(this.pathFilename))) {
			inputs = lines.collect(Collectors.toList());
		}

		String mapLine = inputs.stream().filter(input -> input.charAt(0) == MAP_HEADER_CHAR).findAny().orElseThrow(() -> new IllegalArgumentException("No map header found"));
		inputs.remove(mapLine);
		var map = mapBuilder.from(mapLine);

		var tiles = inputs.stream().filter(input -> input.charAt(0) != COMMENT_CHAR)
				.map(this::buildTile)
				.collect(Collectors.toList());

		map.fillMap(tiles);
		return map;
	}


	private Tile buildTile(String line) {

		var tileCode = line.charAt(0);
		switch (tileCode) {
			case 'A':
				return raiderBuilder.from(line);
			case 'T':
				return treasureBuilder.from(line);
			case 'M':
				return mountainBuilder.from(line);
			default:
				throw new IllegalArgumentException();
		}

	}


}
