package br.com.senai.manutencaosenaiapi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.senai.manutencaosenaiapi.entity.Cliente;
import br.com.senai.manutencaosenaiapi.entity.OrdemDeServico;
import br.com.senai.manutencaosenaiapi.entity.Peca;
import br.com.senai.manutencaosenaiapi.entity.Tecnico;
import br.com.senai.manutencaosenaiapi.service.ClienteService;
import br.com.senai.manutencaosenaiapi.service.OrdemDeServicoService;
import br.com.senai.manutencaosenaiapi.service.PecaService;
import br.com.senai.manutencaosenaiapi.service.TecnicoService;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);
	}

	@Autowired
	private ClienteService clienteService;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private PecaService pecaService;
	@Autowired
	private OrdemDeServicoService ordemService;

	@Transactional
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ac) {
		return args -> {
			try {
				OrdemDeServico ordemSalva = ordemService.buscarPor(1);
				ordemSalva.getPecasDoReparo().add(ordemSalva.getPecasDoReparo().get(0));
				ordemSalva.setDescricaoDoReparo("peça suja");
				ordemSalva.setDataDeEncerramento(LocalDate.of(2022, 5, 6));
				this.ordemService.fechar(ordemSalva);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		};
	}

}
