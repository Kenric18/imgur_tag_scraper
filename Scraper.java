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
	private int searchResults;


	public Scraper(String tag)
	{
		this.tag = tag;
		this.root = "http://imgur.com/search/score/all/page/1?scrolled&q=";
		try {
			this.doc = Jsoup.connect(root + tag).get(); 
			this.list = doc.select(".image-list-link img[src$=.jpg]");
			this.searchResults = 0;
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public void getList()
	{

		String numberOfImagesSearch = doc.getElementById("searching").text();
		String[] numberOfImagesSearchList = numberOfImagesSearch.split(" ");

		int numberOfImages = Integer.parseInt(numberOfImagesSearchList[7].substring(0, numberOfImagesSearchList[7].length() - 1));
		int imagesFound = 0;	

		int pageCount = 1;	

		while (numberOfImages > 0) 
		{
			for (Element e : list)
			{
				//System.out.println(e.attr("src").substring(2, e.attr("src").length()));
				imagesFound++;
			}

			this.root = "http://imgur.com/search/score/all/page/" + Integer.toString(++pageCount) + "?scrolled&q=";
			try {
				this.doc = Jsoup.connect(root + tag).get(); 
			} catch (IOException e) {
				System.out.println(e);
			}

			numberOfImagesSearch = doc.getElementById("searching").text();
			numberOfImagesSearchList = numberOfImagesSearch.split(" ");

			System.out.println(root + tag);
			System.out.println(numberOfImages);
			System.out.println("Subtracting.." + imagesFound);

			numberOfImages -= imagesFound;

			imagesFound = 0;

		}

	}

}