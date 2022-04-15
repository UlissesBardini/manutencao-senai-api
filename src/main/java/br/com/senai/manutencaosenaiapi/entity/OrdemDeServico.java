package br.com.senai.manutencaosenaiapi.entity;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.Data;

@Data
public class OrdemDeServico {

	private Integer id;
	@NotNull(message = "O cliente da ordem não pode ser nulo")
	private Cliente cliente;
	@NotNull(message = "O técnico da ordem não pode ser nulo")
	private Tecnico tecnico;
	@NotNull(message = "A data de abertura é obrigatória!")
	@PastOrPresent(message = "A data de abertura da ordem não pode ser posterior à data atual")
	private LocalDate dataDeAbertura;
	@PastOrPresent(message = "A data de encerramento da ordem não pode ser posterior à data atual")
	private LocalDate dataDeEncerramento;
	@NotNull(message = "A descrição do problema é obrigatória")
	@NotBlank(message = "A descrição do problema não foi informada")
	private String descricaoDoProblema;
	private String descricaoDoReparo;
	@NotEmpty(message = "Deve haver ao menos uma peça de reparo")
	private List<Peca> pecasDoReparo;
	
}
