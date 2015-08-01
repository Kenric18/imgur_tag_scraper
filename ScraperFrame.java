import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.TextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar; 
import javax.swing.ProgressMonitorInputStream;
import javax.swing.SwingUtilities;


public class ScraperFrame extends JFrame implements ActionListener {

	private BorderLayout layout;
	private JButton downloadButton;
	private JLabel instruction;
	private TextField searchQuery;
	private JProgressBar progressBar;
	private Scraper scraperTool;


	static final int MINIMUM = 0;
	static final int MAXIMUM = 100;



	public ScraperFrame() {
		super("Imgur scraper");

		layout = new BorderLayout();
		downloadButton = new JButton("Download");
		instruction = new JLabel("Please enter query terms");
		searchQuery = new TextField(85);
		progressBar = new JProgressBar();


		this.setSize(800, 100);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(instruction, BorderLayout.NORTH);
		add(searchQuery, BorderLayout.WEST);
		add(downloadButton, BorderLayout.EAST);
		add(progressBar, BorderLayout.SOUTH);

		downloadButton.addActionListener(this);
	} 

	public static void main(String[] args) {
		ScraperFrame window = new ScraperFrame();

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == downloadButton) {
				Scraper scraper = new Scraper(searchQuery.getText());

				JLabel numberOfImages = new JLabel(Integer.toString(scraper.getTotalImages()));

				this.add(numberOfImages, BorderLayout.NORTH);

				//scraper.getList();

				System.out.println("test");
		}


	}


}