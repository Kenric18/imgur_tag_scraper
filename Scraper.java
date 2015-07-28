import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Scraper
{
	private String tag;
	private String root;
	private Document doc;
	private Elements list;


	public Scraper(String tag)
	{
		this.tag = tag;
		root = "http://imgur.com/search/score/all/page/1?scrolled&q=";
		try {
			this.doc = Jsoup.connect(root + tag).get(); 
			this.list = doc.select(".image-list-link img[src$=.jpg]");
			System.out.println("List updated..");
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public void getList()
	{
		System.out.println("Printing list..");

		for (Element e : list)
		{
			System.out.println(e.attr("src").substring(2, e.attr("src").length()));
		}
	}

}