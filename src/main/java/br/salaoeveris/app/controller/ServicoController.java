package br.salaoeveris.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.salaoeveris.app.request.ServicoRequest;
import br.salaoeveris.app.response.BaseResponse;
import br.salaoeveris.app.service.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoController extends BaseController {
	
	private final ServicoService _servicoService;
	
	public ServicoController(ServicoService servicoService) {
		_servicoService = servicoService;
	}
	
	// POST - INSERIR SERVIÇO
	@PostMapping
	public ResponseEntity<BaseResponse> inserir(@RequestBody ServicoRequest servicoRequest) {
		try {
			BaseResponse response = _servicoService.inserir(servicoRequest);
			return ResponseEntity.status(errorBase.statusCode).body(response);
		} 
		catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}
	
	// GET - SERVICO POR ID
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<BaseResponse> obter(@PathVariable Long id) {
		try {
			BaseResponse response = _servicoService.obter(id);
			return ResponseEntity.status(errorBase.statusCode).body(response);
		} 
		catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}
	
	//GET TODOS SERVIÇOS
	
	@GetMapping
	public ResponseEntity<BaseResponse> listar() {
		try {
			BaseResponse servicos = _servicoService.listar();
			return ResponseEntity.status(servicos.statusCode).body(servicos);
		} 
		catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}

}
