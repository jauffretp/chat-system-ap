package communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ComunicaTCPServer extends Comunica {

	public ComunicaTCPServer(BufferedReader reader, BufferedWriter writer) throws IOException {
		super(reader, writer);
	}

}
