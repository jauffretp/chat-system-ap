package communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ComunicaTCPClient extends Comunica {

	public ComunicaTCPClient(BufferedReader reader, BufferedWriter writer)throws IOException {
		super(reader, writer);
	}

}
