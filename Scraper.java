import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class Scraper
{
	public static void main(String[] args) 
	{
		try {

			Document doc = Jsoup.connect("http://en.wikipedia.org/").get();

			System.out.println(doc);
			//Elements e = doc.select("a[href]");

		} catch (IOException e) {
			System.out.println(e);
		}

	}

}