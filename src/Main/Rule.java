package Main;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Rule extends JPanel{
	
	Main main;
	BufferedImage bg;
	
	
	public Rule(Main main) {
		this.main = main;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{106, 86, 99, 0, 116, 0, 104, 95, 0};
		gridBagLayout.rowHeights = new int[]{0, 46, 356, 34, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnNewButton = new JButton("Quay lại");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (main.main_panel.getLayout());
				cl.show(main.main_panel, "begin_panel");
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Luật chơi");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		StringBuilder builder = new StringBuilder();
		String line1 = "1. Người chơi lần lượt bốc từng quân cờ từ ô của mình sang ô tiếp theo.";
		String line2 = "Dải quân ô đó lần lượt mỗi ô 1 quân theo chiều trái hoặc phải.";
		String line3 = "Nếu ô tiếp theo đã có quân cờ thì bốc tiếp quân cờ ở ô đó sang ô tiếp theo nữa";
		String line4 = "2. Khi rải hết quân cuối cùng, tùy tình huống mà người chơi sẽ phải xử lý tiếp " + "\n "
				+ " như sau:";
		String line5 = "  - Nếu liền sau đó là một ô vuông có chứa quân thì tiếp tục dùng tất cả số quân " + "\n "
				+ " đó để rải tiếp theo chiều đã chọn." + "\n"
				+ " Tuy nhiên nếu ô đó là một ô Quan thì chỉ được phép lấy 1 quân để rải";
		String line6 = "   - Nếu liền sau đó là một ô trống (không phân biệt ô quan hay ô dân) rồi đến một " + "\n"
				+ " ô có chứa quân thì người chơi sẽ được ăn tất cả số quân trong ô đó" + "\n"
				+ " ( lưu ý là quan phải có đủ 5 dân thì mới được phép ăn). " + "\n"
				+ " Nếu chưa đủ thì nước đi đó sẽ mất lượt.)";
		String line7 = "3. Khi dải quân mà ô tiếp theo và các ô tiếp sau đó không có quân để dải thì lượt " + "\n"
				+ " của người chơi đó dừng";
		String line8 = "4. Người chơi chỉ được phép bốc quân ở bên phía của mình. " + "\n"
				+ " Nếu bên mình hết quân thì sẽ phải lấy 5 quân đã ăn dải lần lượt vào 5 ô ở bên mình";
		String line9 = "5. Trò chơi kêt thúc khi 2 ô quan không còn quan và dân";
		String line10 = "6. Cách tính điểm là Quan: 10 điểm, dân 1 điểm. " + "\n"
				+ " Người nào được nhiều điểm hơn người đó sẽ thắng";

		// Xây dựng mỗi dòng
		builder.append(line1).append("\n");
		builder.append(line2).append("\n");
		builder.append(line3).append("\n");
		builder.append(line4).append("\n");
		builder.append(line5).append("\n");
		builder.append(line6).append("\n");
		builder.append(line7).append("\n");
		builder.append(line8).append("\n");
		builder.append(line9).append("\n");
		builder.append(line10).append("\n");
		String text = builder.toString();

		textArea.setText(text);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 6;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 2;
		add(textArea, gbc_textArea);
		
		
		
		URL path = this.getClass().getResource("/images/begin_panel_background.jpg");
		try {
			bg = ImageIO.read(new File(path.toString().replace("file:/", "")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, 640, 480, null);
	}
}
