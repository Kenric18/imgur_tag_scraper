import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
*
* ScrapeFrame creates a GUI
* using swing to take input from
* the user and send to Scraper class
*
* @author ShaunGeorge
*/


public class ScraperFrame extends JFrame implements ActionListener {

	private BorderLayout layout;
	private JPanel searchArea;
	private JButton downloadButton;
	private JButton stopDownload;

	/**
	* The default constructor which
	* is used to initalize a window.
	* 
	*/
	public ScraperFrame() {

		// this. keyword add

		super("imgur scraper class");

		searchArea = new JPanel(new GridLayout(2, 1));
		downloadButton = new JButton("Download");
		stopDownload = new JButton("Stop");


		searchArea.add(downloadButton);
		searchArea.add(stopDownload);

		downloadButton.addActionListener(this);
		stopDownload.addActionListener(this);

		this.add(searchArea, BorderLayout.EAST);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 100);
		this.setVisible(true);


	} 

	public static void main(String[] args) {
		ScraperFrame window = new ScraperFrame();

	}

	/**
	* When JButton is triggered
	* the events are handled in this method
	* Input from the JTextField are sent to the Scraper class
	* after a new Thread is created.
	* @param e is the event that was triggered.
	*/
	public void actionPerformed(ActionEvent e) {

		Scraper scraperTool = new Scraper("cat");

		if (e.getSource() == downloadButton) {

			Thread scraperClass = new Thread(new Runnable(){

				public void run() {
					
					System.out.println("Download started");
					scraperTool.startDownload();

				}});
			scraperClass.start();

		} else if (e.getSource() == stopDownload) {

			scraperTool.setRunningMode(false);
			System.out.println("download has stopped");


		}
		
	}



}


