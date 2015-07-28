import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class ScraperFrame
{
	public static void main(String[] args)
	{
		Scraper s = new Scraper("cat");

		s.getList();
	}
}