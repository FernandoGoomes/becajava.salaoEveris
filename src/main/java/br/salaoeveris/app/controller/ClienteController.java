package br.salaoeveris.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.salaoeveris.app.request.ClienteRequest;
import br.salaoeveris.app.response.BaseResponse;
import br.salaoeveris.app.response.ClienteListResponse;
import br.salaoeveris.app.response.ClienteResponse;
import br.salaoeveris.app.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController extends BaseController {

	private final ClienteService _clienteService;

	public ClienteController(ClienteService clienteService) {
		_clienteService = clienteService;
	}

	@PostMapping
	public ResponseEntity inserir(@RequestBody ClienteRequest clienteRequest) {
		try {
			BaseResponse response = _clienteService.inserir(clienteRequest);
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity obter(@PathVariable Long id) {
		try {
			ClienteResponse clienteResponse = _clienteService.obter(id);
			return ResponseEntity.status(clienteResponse.statusCode).body(clienteResponse);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}
	@GetMapping
	public ResponseEntity listar() {
		try {
			ClienteListResponse cliente = _clienteService.listar();
			return ResponseEntity.status(cliente.statusCode).body(cliente);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}
}
