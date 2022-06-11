package br.com.senai.manutencaosenaiapi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.Login;
import br.com.senai.manutencaosenaiapi.service.LoginService;

@Component
public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtLogin;
	private JPasswordField pfSenha;
	private JComboBox<String> cbPerfil;
	@Autowired
	LoginService service;
	@Autowired
	private TelaOrdemDeServico telaDeOrdem;
	
	private void carregarOpcoes() {
		this.cbPerfil.addItem("Atendente");
		this.cbPerfil.addItem("Técnico");
	}

	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Login");
		
		edtLogin = new JTextField();
		edtLogin.setColumns(10);
		
		pfSenha = new JPasswordField();
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String login = edtLogin.getText();
					String senha = new String(pfSenha.getPassword());
					Login loginEncontrado = service.logar(login, senha, cbPerfil.getSelectedItem().toString());
					if (loginEncontrado.getPerfil().equals(cbPerfil.getItemAt(0))) {
						JOptionPane.showMessageDialog(contentPane, "Seja bem vindo, " + loginEncontrado.getLogin() + "\nPerfil: " + cbPerfil.getSelectedItem());
						telaDeOrdem.apresentarTela();
					} else {
						JOptionPane.showMessageDialog(contentPane, "Técnico acessando");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Perfil");
		
		cbPerfil = new JComboBox<String>();
		this.carregarOpcoes();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(pfSenha, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
						.addComponent(edtLogin, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(100)
							.addComponent(btnLogar))
						.addComponent(lblNewLabel_2)
						.addComponent(cbPerfil, 0, 262, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(edtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pfSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbPerfil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(btnLogar))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
