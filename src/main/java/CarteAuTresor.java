import io.GameBuilder;
import map.WorldMap;

import java.io.IOException;

public class CarteAuTresor {


	public static void main(String... args) throws IOException {
		System.out.println("Hello ");

		GameBuilder gm = new GameBuilder("src/main/resources/carte_input.txt");

		WorldMap wm = gm.build();

		wm.printMap();
	}
}
