package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	
	public Data retreiveData() {
		InputStream in= this.getClass().getResourceAsStream("data.json");

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Gson gson = new Gson();		
		return gson.fromJson(sb.toString(), Data.class);
	}
	
	static String readFile(String path, Charset encoding) throws IOException 
	{
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return new String(encoded, encoding);
	}
	
}
