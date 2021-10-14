package io;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.*;

class GameBuilderTest {

	private static final int MAP_HEIGHT = 4;
	private static final int MAP_WIDTH = 3;
	private static final String INPUT_WITHOUT_MAP_SIZE = "src/test/resources/input/carte_input_tests_without_map_header.txt";
	private static final String NO_INPUT_FILE = "src/test/resources/input/nofile.txt";
	private static final String TRUE_INPUT_FILE = "src/test/resources/input/carte_input_tests.txt";
	private GameBuilder gameBuilder;

	@Test
	void build_KO_map_header_not_found() {
		gameBuilder = new GameBuilder(INPUT_WITHOUT_MAP_SIZE);
		Exception exception = assertThrows(IllegalArgumentException.class, () ->
				gameBuilder.build());
		assertEquals("No map header found", exception.getMessage());
	}

	@Test
	void build_KO_file_not_found() {
		gameBuilder = new GameBuilder(NO_INPUT_FILE);
		assertThrows(NoSuchFileException.class, () -> gameBuilder.build());
	}

	@Test
	void build_OK() throws IOException {
		gameBuilder = new GameBuilder(TRUE_INPUT_FILE);

		var worldMap = gameBuilder.build();
		assertNotNull(worldMap);
		assertEquals(MAP_HEIGHT, worldMap.getHeight());
		assertEquals(MAP_WIDTH, worldMap.getWidth());

	}
}