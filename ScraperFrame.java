import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.StringBuffer;
import java.lang.Character;

/**
*
* ScrapeFrame creates a GUI
* using swing to take input from
* the user and send to Scraper class
*
* @author ShaunGeorge
*/


public class ScraperFrame extends JFrame implements ActionListener {

	private JPanel searchArea;
	private JButton downloadButton;
	private JButton stopDownload;
	private JPanel queryInfo;
	private JTextField searchQuery;
	private JLabel numberOfImages;

	/**
	* The default constructor which
	* is used to initalize a window.
	* 
	*/
	public ScraperFrame() {

		// this. keyword add

		super("imgur scraper class");

		setLayout(new BorderLayout());

		searchArea = new JPanel(new GridLayout(2, 1));
		queryInfo = new JPanel(new GridLayout(2, 2));

		downloadButton = new JButton("Download");
		stopDownload = new JButton("Stop");
		searchQuery = new JTextField();


		searchArea.add(downloadButton);
		searchArea.add(stopDownload);
		queryInfo.add(searchQuery);

		downloadButton.addActionListener(this);
		stopDownload.addActionListener(this);

		this.add(searchArea, BorderLayout.EAST);
		this.add(queryInfo, BorderLayout.CENTER);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 100);
		this.setVisible(true);


	} 

	public static void main(String[] args) {
		ScraperFrame window = new ScraperFrame();
		window.setVisible(true);

	}

	/**
	* When JButton is triggered
	* the events are handled in this method
	* Input from the JTextField are sent to the Scraper class
	* after a new Thread is created.
	* @param e is the event that was triggered.
	*/
	public void actionPerformed(ActionEvent e) {
		
		if (searchQuery.getText().length() > 0) {
			
			Scraper scraperTool = new Scraper(normalizeText(searchQuery.getText()));
			
			if (e.getSource() == downloadButton) {
				
				numberOfImages = new JLabel(scraperTool.getTotalImages() + " related images found.");
				
				queryInfo.add(numberOfImages);
				queryInfo.revalidate();
				queryInfo.repaint();

				Thread scraperClass = new Thread(new Runnable(){
					public void run() {
						
						System.out.println("Download started");
						scraperTool.startDownload();

					}});
				scraperClass.start();

			} else if (e.getSource() == stopDownload) {

				scraperTool.setRunningMode(false);

				queryInfo.remove(numberOfImages);
				queryInfo.revalidate();
				queryInfo.repaint();

				System.out.println("download has stopped");


			}
			
		} else {
			JLabel emptyInvalidQuery = new JLabel("No query entered");
			queryInfo.add(emptyInvalidQuery);
			queryInfo.revalidate();
			queryInfo.repaint();
		}
		
		
	}
	
	private String normalizeText(String input) {
		
		StringBuffer normalized_text = new StringBuffer();
		
		for (int i = 0; i < input.length(); i++) {
			if (Character.isLetter(input.charAt(i)) || (Character.isDigit(input.charAt(i))) || Character.isWhitespace(input.charAt(i))) {
				normalized_text.append(input.charAt(i));
			}
		}		
		
		return normalized_text.toString();
	}



}


