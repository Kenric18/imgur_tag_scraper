import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.TextField;
import javax.swing.JButton;


public class ScraperFrame {

	public static void main(String[] args) {


	
		JFrame jf = new JFrame("Imgur image scraper");
		JLabel instruction = new JLabel("Enter search query");
		TextField tf = new TextField(5);
		JButton download = new JButton("Download");

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(400, 100);
		jf.add(instruction, BorderLayout.NORTH);
		jf.add(tf, BorderLayout.SOUTH);
		jf.add(download, BorderLayout.EAST);
		jf.setVisible(true);




	}
}