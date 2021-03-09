package executaveis;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JanelaMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	int posi = 0;

	private String[] banners = { "banner0.jpg", "banner1.jpg", "banner2.jpg", "banner3.jpg", "banner4.jpg",
			"banner5.jpg", };

	private JLabel lblBanner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaMenu frame = new JanelaMenu();
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
	public JanelaMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 483);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Concessionaria");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Marcas");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JanelaGerenciarMarca m = new JanelaGerenciarMarca();
				m.setVisible(true);
			}
		});
		mntmNewMenuItem.setMnemonic('m');
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Carros");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JanelaGerenciarCarro m = new JanelaGerenciarCarro();
				m.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setMnemonic('c');
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBack = new JLabel("");
		lblBack.setIcon(new ImageIcon(JanelaMenu.class.getResource("/icons/back.png")));
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (posi <= 0)
					posi = banners.length - 1;
				else {
					posi--;
				}

				lblBanner.setIcon(new ImageIcon(JanelaMenu.class.getResource("/icons/" + banners[posi])));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblBack.setIcon(new ImageIcon(JanelaMenu.class.getResource("/icons/backSel.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblBack.setIcon(new ImageIcon(JanelaMenu.class.getResource("/icons/back.png")));

			}
		});
		

		JLabel lblNext = new JLabel("");
		lblNext.setIcon(new ImageIcon(JanelaMenu.class.getResource("/icons/next.png")));
		lblNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (posi >= banners.length - 1) {
					posi = 0;
				} else {
					posi++;
				}

				lblBanner.setIcon(new ImageIcon(JanelaMenu.class.getResource("/icons/" + banners[posi])));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNext.setIcon(new ImageIcon(JanelaMenu.class.getResource("/icons/nextSel.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNext.setIcon(new ImageIcon(JanelaMenu.class.getResource("/icons/next.png")));
			}
		});
		lblNext.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNext.setHorizontalAlignment(SwingConstants.CENTER);
		lblNext.setBounds(627, 178, 57, 49);
		contentPane.add(lblNext);
		lblBack.setHorizontalAlignment(SwingConstants.CENTER);
		lblBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBack.setBounds(10, 173, 50, 58);
		contentPane.add(lblBack);

		lblBanner = new JLabel("");
		lblBanner.setIcon(new ImageIcon(JanelaMenu.class.getResource("/icons/banner0.jpg")));
		lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanner.setBounds(39, 11, 609, 400);
		contentPane.add(lblBanner);
	}
}
