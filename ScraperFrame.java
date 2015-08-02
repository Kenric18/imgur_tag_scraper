import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ScraperFrame extends JFrame implements ActionListener {

	private BorderLayout layout;
	private JPanel searchArea;




	public ScraperFrame() {
		super("imgur scraper class");

		searchArea = new JPanel(new GridLayout(1, 1));
		JButton downloadButton = new JButton("Download");
		JButton stopDownload = new JButton("Stop")

		searchArea.add(downloadButton);
		downloadButton.addActionListener(this);

		this.add(searchArea, BorderLayout.EAST);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 100);
		this.setVisible(true);


	} 

	public static void main(String[] args) {
		ScraperFrame window = new ScraperFrame();

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == downloadButton) {

			;

			searchArea.remove(downloadButton);
			searchArea.add(stopDownload);
			searchArea.revalidate();
			searchArea.repaint();


		}
		
	}


	}


