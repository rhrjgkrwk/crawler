import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	static private JFileChooser chooser = new JFileChooser();
	static private File path = chooser.getCurrentDirectory();
	static HashMap<String, String> nation = new HashMap<String, String>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		
		//map을 초기화합니다. 일 중 영 칠 싱 미 아르헨 호주 오스트리아
		nation.put("Japan", "jp");
		nation.put("USA", "us"); 
		nation.put("UK", "gb");
		nation.put("Chile", "cl");
		nation.put("Singapore", "sg");
		nation.put("Hongkong", "hk");
		nation.put("Argentina", "ar");
		nation.put("Austrailia", "au");
		nation.put("Austria", "at");
		nation.put("VietNam", "vn");
		nation.put("Malaysia", "my");
		nation.put("India", "in");
		
		
		
		setTitle("iTunes Charts Crawler");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 99);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 222, 179));
		panel.setToolTipText("Path");
		panel.setBorder(new LineBorder(Color.GRAY, 0, true));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JLabel lblNewLabel = new JLabel("Path : ");
		lblNewLabel.setFont(new Font("Apple LiGothic", Font.PLAIN, 14));
		panel.add(lblNewLabel);

		textField = new JTextField(path.toString());
		panel.add(textField);
		textField.setColumns(20);

		final JButton brouseBtn = new JButton("Brouse");

		brouseBtn.setFont(new Font("Apple LiGothic", Font.PLAIN, 13));
		brouseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(brouseBtn);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// 열기 버튼을 누르면
					path = chooser.getSelectedFile();
					textField.setText(path.toString());

				} else {
					// 취소 버튼을 누르면
					textField.setText(path.toString());
					System.out.println("취소합니다");
				}
				// textField.setText(chooser.().toString());
			}
		});
		panel.add(brouseBtn);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		panel_1.setBackground(new Color(245, 222, 179));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		
		JButton saveBtn = new JButton("Save");
		saveBtn.setForeground(new Color(0, 0, 0));
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Crawler cr = new Crawler();
				cr.makeExcel(nation, path.toString());
			}
		});
		saveBtn.setFont(new Font("Apple LiGothic", Font.PLAIN, 13));
		saveBtn.setBackground(new Color(245, 222, 179));
		panel_1.add(saveBtn, BorderLayout.SOUTH);
		
	}

}