package com.concell.system.controladores;

import com.concell.system.mapeadores.requests.ClienteRequest;
import com.concell.system.mapeadores.responses.ClienteResponse;
import com.concell.system.modelos.Cliente;
import com.concell.system.servicios.ClienteServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  private final ClienteServicio clienteServicio;

  public ClienteController(ClienteServicio clienteServicio) {
    this.clienteServicio = clienteServicio;
  }

  @GetMapping
  public ResponseEntity<List<ClienteResponse>> obtenerClientes() {
    List<ClienteResponse> clientes = clienteServicio
            .obtenerClientes()
            .stream()
            .map(cliente -> new ClienteResponse(
                    cliente.getIdCliente(),
                    cliente.getNit(),
                    cliente.getNombre(),
                    cliente.getApellidoPaterno(),
                    cliente.getApellidoMaterno(),
                    cliente.getDireccion(),
                    cliente.getTelefono(),
                    cliente.getEmail()
            ))
            .toList();
    return ResponseEntity.ok(clientes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClienteResponse> obtenerClientePorId(
          @PathVariable("id") Integer id) {
    Cliente cliente = clienteServicio.obtenerClientePorId(id);

    ClienteResponse clienteResponse = new ClienteResponse(
            cliente.getIdCliente(),
            cliente.getNit(),
            cliente.getNombre(),
            cliente.getApellidoPaterno(),
            cliente.getApellidoMaterno(),
            cliente.getDireccion(),
            cliente.getTelefono(),
            cliente.getEmail()
    );

    return ResponseEntity.ok(clienteResponse);
  }

  @PostMapping
  public ResponseEntity<ClienteResponse> crearCliente(@RequestBody ClienteRequest request) {
    ClienteResponse response = clienteServicio.crearCliente(request);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ClienteResponse> actualizarCliente(
          @PathVariable("id") Integer id,
          @RequestBody ClienteRequest request) {
    ClienteResponse response = clienteServicio.actualizarCliente(id, request);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminarCliente(@PathVariable("id") Integer id) {
    clienteServicio.eliminarCliente(id);
    return ResponseEntity.noContent().build();
  }
}
