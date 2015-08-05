import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
* Takes query from user and
* crawls imgur for related images and then
* downloads the images into a folder.
* @author ShaunGeorge
*/


public class Scraper {
	private String tag;
	private String root;
	private Document doc;
	private Elements list;
	private int numberOfImagesTotal;
	private static volatile boolean running = true;



	/**
	* Constructor which does basic crawling
	* and retrieves basic information such as
	* total number of images found.
	*
	* @param tag Is the search query provided by the user.
	*/
	public Scraper(String tag) {


		this.tag = tag;
		this.root = "http://imgur.com/search/score/all/page/1?scrolled&q=";
		this.numberOfImagesTotal = 0;

		try {
			this.doc = Jsoup.connect(root + tag).get(); 
			this.list = doc.select(".image-list-link img[src$=.jpg]");

			String numberOfImagesSearch = doc.getElementById("searching").text();
			String[] numberOfImagesSearchList = numberOfImagesSearch.split(" ");
			
			
			this.numberOfImagesTotal = Integer.parseInt(numberOfImagesSearchList[7].substring(0, numberOfImagesSearchList[7].length() - 1));

		} catch (IOException e) {
			System.out.println(e);
		}

	}	

	/*
	* This method iterates through the search result
	* pages and parses it for image urls. It also creates a folder
	* for the images.
	*/

	public void startDownload() {

		String numberOfImagesSearch = doc.getElementById("searching").text();
		String[] numberOfImagesSearchList = numberOfImagesSearch.split(" ");

		this.numberOfImagesTotal = Integer.parseInt(numberOfImagesSearchList[7].substring(0, numberOfImagesSearchList[7].length() - 1));
		int numberOfImagesRemaining = this.numberOfImagesTotal;
		int currentImagesFound = Integer.parseInt(numberOfImagesSearchList[5]);
		int imagesFound = 0;
		int imageId = 0;	

		int pageCount = 1;	

		File dir = new File(System.getProperty("user.dir") + "/" + tag);
		dir.mkdir();


		while (!(currentImagesFound == numberOfImagesTotal) && Scraper.running) {

			System.out.print("Downloading page ");

			System.out.println(pageCount);

			System.out.println(Scraper.running);

			
			for (Element e : list) {

				if (Scraper.running) {

					String url = "http://" + e.attr("src").substring(2, e.attr("src").length() - 5) + ".jpg";

					System.out.println("Downloading image " + url);

					System.out.println(System.getProperty("user.dir"));

					fileDownloader(url, Integer.toString(imageId));

					imageId++;
					imagesFound++;
					
				}
			}

			this.root = "http://imgur.com/search/score/all/page/" + Integer.toString(++pageCount) + "?scrolled&q=";

			try {
				this.doc = Jsoup.connect(root + tag).get();
				this.list = doc.select(".image-list-link img[src$=.jpg]");
			} catch (IOException e) {
				System.out.println(e);
			}

			numberOfImagesSearch = doc.getElementById("searching").text();
			numberOfImagesSearchList = numberOfImagesSearch.split(" ");
			numberOfImagesRemaining -= imagesFound;
			imagesFound = 0;

			currentImagesFound = Integer.parseInt(numberOfImagesSearchList[5]);
			

		}

	}

	/**
	* This method recieves the image url from startDownload
	* and then downloads the images to the folder created earlier.
	*
	* @param url_id is the image url.
	* @param imageId is what the image name is going to be when stored.
	*/
	private void fileDownloader(String url_id, String imageId) {
		try {
			URL url = new URL(url_id);
			BufferedImage image = ImageIO.read(url);
	        ImageIO.write(image, "jpg", new File(System.getProperty("user.dir") + "/" + tag + "/image" + imageId + ".jpg"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	* Returns total images found related to query.
	* @return number of images found.
	*/
	public String getTotalImages() {
		return Integer.toString(numberOfImagesTotal);
	}

	/**
	* Used to stop the crawler before it finishes.
	*/
	public void setRunningMode(boolean b) {
		Scraper.running = b;
	}

}