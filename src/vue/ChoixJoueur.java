package vue;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Cursor;
import java.awt.Dimension;

import controleur.Controle;

/**
 * Frame du choix du joueur
 * 
 * @author emds
 *
 */
public class ChoixJoueur extends JFrame {

	/**
	 * Panel général
	 */
	private JPanel contentPane;
	/**
	 * Zone de saisie du pseudo
	 */
	private JTextField txtPseudo;
	/**
	 * Instance du contrôleur pour communiquer avec lui
	 */
	private Controle controle;
	/**
	 * Label d'affichage du personnage
	 */
	private JLabel lblPersonnage;
	/**
	 * Numéro du personnage sélectionné
	 */
	private static final int NBPERSOS = 3;
	/**
	 * Nombre de personnages différents
	 */
	private int numPerso;

	
	/**
	 * Clic sur la flèche "précédent" pour afficher le personnage précédent
	 */
	private void lblPrecedent_clic() {
		numPerso = ((numPerso+1)%NBPERSOS)+1;
		affichePerso();
	}

	/**
	 * Clic sur la flèche "suivant" pour afficher le personnage suivant
	 */
	private void lblSuivant_clic() {
		numPerso = (numPerso%NBPERSOS)+1;
		affichePerso();
	}

	/**
	 * Clic sur GO pour envoyer les informations
	 */
	private void lblGo_clic() {
		if(txtPseudo.getText()=="") {
		JOptionPane.showMessageDialog(null, "La saisie du pseudo est obligatoire");	
		txtPseudo.requestFocus();
		}
		else {
		this.controle.evenementChoixJoueur(this.txtPseudo.getText(), numPerso);
		}
	}

	private void affichePerso() {

		String chemin = "personnages/perso" + this.numPerso + "marche" + 1 + "d" + 1 + ".gif";
		URL resource = getClass().getClassLoader().getResource(chemin);
		this.lblPersonnage.setIcon(new ImageIcon(resource));
	}
	private void sourisNormale() {
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	private void sourisDoigt() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	/**
	 * Create the frame.
	 */
	public ChoixJoueur() {
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
		this.pack();
		// interdiction de changer la taille
		this.setResizable(false);

		setTitle("Choice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblPrecedent_clic();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});

		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSuivant_clic();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});

		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic();
			}
		});

		txtPseudo = new JTextField();
		txtPseudo.setBounds(142, 245, 120, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);

		lblGo.setBounds(311, 202, 65, 61);
		contentPane.add(lblGo);
		lblSuivant.setBounds(301, 145, 25, 46);
		contentPane.add(lblSuivant);
		lblPrecedent.setBounds(65, 146, 31, 45);
		contentPane.add(lblPrecedent);

		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		String chemin = "fonds/fondchoix.jpg";
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(resource));
		contentPane.add(lblFond);

		lblPersonnage = new JLabel("");
		lblPersonnage.setBounds(139, 114, 123, 121);
		lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblPersonnage);

		// positionnement sur la zone de saisie
		txtPseudo.requestFocus();
		
		// affichage du premier personnage
		this.numPerso = 1;
		this.affichePerso();

	}
}
