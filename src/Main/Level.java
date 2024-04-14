package Main;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Level extends JPanel{
	
	Main main;
	BufferedImage bg;
	CardLayout cl;
	
	public Level(Main main) {
		this.main = main;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{106, 38, 93, 30, 105, 0, 97, 122, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 155, 37, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnNewButton = new JButton("Quay lại");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (main.main_panel.getLayout());
		        cl.show(main.main_panel, "game_mode");
			}
		});
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Chọn độ khó");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Dễ");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 3;
		add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Trung bình");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 4;
		gbc_btnNewButton_2.gridy = 3;
		add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Khó");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_3.gridx = 6;
		gbc_btnNewButton_3.gridy = 3;
		add(btnNewButton_3, gbc_btnNewButton_3);
		
	
		
		
		URL path = this.getClass().getResource("/images/begin_panel_background.jpg");
		try {
			bg = ImageIO.read(new File(path.toString().replace("file:/", "")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, 737, 640, null);
	}
}
