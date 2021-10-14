package map;

import io.GameBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class WorldMapTest {


	private WorldMap map;

	@BeforeEach
	private void init() throws IOException {
		String input = "src/test/resources/input/carte_input_tests.txt";
		var gm = new GameBuilder(input);
		map = gm.build();

	}

	@Test
	void fillMap() {


	}

	@Test
	void saveFinalOutput() {
	}

	@Test
	void play() {
	}
}