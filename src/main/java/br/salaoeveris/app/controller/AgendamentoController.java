package br.salaoeveris.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.salaoeveris.app.request.AgendamentoRequest;
import br.salaoeveris.app.response.BaseResponse;
import br.salaoeveris.app.service.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController extends BaseController {
	
	// PROPRIEDADES
	private final AgendamentoService _agendamentoService;
	
	// CONSTRUTOR
	public AgendamentoController(AgendamentoService agendamentoService) {
		_agendamentoService = agendamentoService;
	}
	
	// POST - INSERIR AGENDAMENTO
	@PostMapping
	public ResponseEntity inserir(@RequestBody AgendamentoRequest agendamentoRequest) {
		try {
			BaseResponse response = _agendamentoService.inserir(agendamentoRequest);
			return ResponseEntity.status(response.statusCode).body(response);
			} catch (Exception e) {
				return ResponseEntity.status(errorBase.statusCode).body(errorBase);
			}
		
	}

}
