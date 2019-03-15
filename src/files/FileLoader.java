package files;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class FileLoader {
	
	public static Data getData() {
		try {
			String s_data = readFile("data/data.json", StandardCharsets.UTF_8);
			Gson gson = new Gson();
			
			Data data = gson.fromJson(s_data, Data.class);
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	static String readFile(String path, Charset encoding) throws IOException 
	{
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return new String(encoded, encoding);
	}
	
}
