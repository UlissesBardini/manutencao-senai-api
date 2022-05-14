package br.com.senai.manutencaosenaiapi.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.senai.manutencaosenaiapi.entity.Peca;
import br.com.senai.manutencaosenaiapi.service.PecaService;
import javax.swing.JTextArea;
import java.awt.Color;

@Component
public class TelaCadastroDePeca extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtId;
	private JLabel lblDescricao;
	private JTextField edtDescricao;
	private JTextField edtQtde;
	private JLabel lblEspecificacoes;
	private JTextArea jtaEspecificacoes;
	@Autowired
	private PecaService service;

	/**
	 * Launch the application.
	 *
	 *
	 * Create the frame.
	 */
	public TelaCadastroDePeca() {
		setTitle("Cadastro de Peça");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblId = new JLabel("ID");

		edtId = new JTextField();
		edtId.setEnabled(false);
		edtId.setColumns(10);

		lblDescricao = new JLabel("Descrição");

		edtDescricao = new JTextField();
		edtDescricao.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (edtId.getText() != null && edtId.getText().length() > 0) {
						Peca pecaSalva = new Peca();
						pecaSalva.setDescricao(edtDescricao.getText());
						pecaSalva.setQtdeEmEstoque(Integer.parseInt(edtQtde.getText()));
						pecaSalva.setEspecificacoes(jtaEspecificacoes.getText());
						pecaSalva.setId(Integer.parseInt(edtId.getText()));
						service.alterar(pecaSalva);
						JOptionPane.showMessageDialog(contentPane, "Peça alterada com sucesso!");
					} else {
						Peca novaPeca = new Peca();
						novaPeca.setDescricao(edtDescricao.getText());
						novaPeca.setQtdeEmEstoque(Integer.parseInt(edtQtde.getText()));
						novaPeca.setEspecificacoes(jtaEspecificacoes.getText());
						Peca pecaSalva = service.inserir(novaPeca);
						edtId.setText(pecaSalva.getId().toString());
						JOptionPane.showMessageDialog(contentPane, "Peça inserida com sucesso!");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}
			}
		});

		JLabel lblQtde = new JLabel("Quantidade");

		edtQtde = new JTextField();
		edtQtde.setColumns(10);

		lblEspecificacoes = new JLabel("Especificações");

		jtaEspecificacoes = new JTextArea();
		jtaEspecificacoes.setForeground(new Color(51, 51, 51));
		jtaEspecificacoes.setBackground(new Color(255, 255, 255));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblId).addComponent(edtId,
						GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(edtDescricao, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescricao))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblQtde).addGap(17))
						.addComponent(edtQtde, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
				.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblEspecificacoes).addContainerGap())
				.addComponent(jtaEspecificacoes, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup().addContainerGap().addComponent(btnSalvar)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblId)
						.addComponent(lblDescricao).addComponent(lblQtde))
				.addGap(6)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(edtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(edtDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(edtQtde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblEspecificacoes)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(jtaEspecificacoes, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE).addGap(18)
				.addComponent(btnSalvar)));
		contentPane.setLayout(gl_contentPane);
	}

	public void colocarEmEdicao(Peca pecaSalva) {
		edtId.setText(pecaSalva.getId().toString());
		edtDescricao.setText(pecaSalva.getDescricao());
		jtaEspecificacoes.setText(pecaSalva.getQtdeEmEstoque().toString());
		edtQtde.setText(pecaSalva.getQtdeEmEstoque().toString());
	}

}
