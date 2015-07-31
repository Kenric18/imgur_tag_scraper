import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.TextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ScraperFrame extends JFrame implements ActionListener {

	private BorderLayout layout;
	private JButton button1;
	private JLabel instruction;
	private TextField tf;

	public ScraperFrame() {
		super("Imgur scraper");

		layout = new BorderLayout();
		button1 = new JButton("Download");
		instruction = new JLabel("Please enter query terms");
		tf = new TextField(50);

		this.setSize(800, 100);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(instruction, BorderLayout.NORTH);
		add(tf, BorderLayout.WEST);
		add(button1, BorderLayout.EAST);

		button1.addActionListener(this);
		this.add(button1);
	} 

	public static void main(String[] args) {
		ScraperFrame window = new ScraperFrame();

	}

	public void actionPerformed(ActionEvent e) {
		Scraper scraper = new Scraper(tf.getText());
		scraper.getList();

	}


}