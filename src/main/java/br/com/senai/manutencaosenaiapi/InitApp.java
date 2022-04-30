package br.com.senai.manutencaosenaiapi;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.senai.manutencaosenaiapi.entity.Cliente;
import br.com.senai.manutencaosenaiapi.enums.Sexo;
import br.com.senai.manutencaosenaiapi.service.ClienteService;

@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);
	}

	@Autowired
	private ClienteService clienteService;

	@Transactional
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ac) {
		return args -> {
			try {
				Cliente novoCliente = new Cliente();
				novoCliente.setNome("Amber");
				novoCliente.setDataDeNascimento(LocalDate.of(1986, 4, 22));
				novoCliente.setSobrenome("Heard");
				novoCliente.setCpf("005.900.289-11");
				novoCliente.setSexo(Sexo.F);
				novoCliente.setEndereco("R. Hollywood");
				this.clienteService.inserir(novoCliente);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		};
	}

}
