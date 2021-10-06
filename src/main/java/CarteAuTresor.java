import io.GameBuilder;
import map.WorldMap;

import java.io.IOException;

public class CarteAuTresor {


	public static void main(String... args) throws IOException {

		GameBuilder gm = new GameBuilder("src/main/resources/input/carte_input.txt");

		WorldMap wm = gm.build();

		wm.play();
		wm.saveFinalOutput("src/main/resources/output/carte_output.txt");
	}
}
