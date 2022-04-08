package br.com.senai.manutencaosenaiapi.service;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.senai.manutencaosenaiapi.entity.Tecnico;

@Service
@Validated
public class TecnicoService {

	public Tecnico inserir(@Valid Tecnico novoTecnico) {
		return novoTecnico;
	}

}
