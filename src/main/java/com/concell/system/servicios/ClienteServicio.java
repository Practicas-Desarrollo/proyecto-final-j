package com.concell.system.servicios;

import com.concell.system.mapeadores.requests.ClienteRequest;
import com.concell.system.mapeadores.responses.ClienteResponse;
import com.concell.system.modelos.Cliente;
import com.concell.system.repositorios.ClienteRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicio {

  private final ClienteRepositorio clienteRepositorio;

  public ClienteServicio(ClienteRepositorio clienteRepositorio) {
    this.clienteRepositorio = clienteRepositorio;
  }

  public List<Cliente> obtenerClientes() {
    return clienteRepositorio.findAll();
  }

  public Cliente obtenerClientePorId(Integer id) {
    return clienteRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
  }

  public List<Cliente> buscarClientesPorNombre(String nombre) {
    return clienteRepositorio.findByNombreContainingIgnoreCase(nombre);
  }

  public ClienteResponse crearCliente(ClienteRequest request) {
    Cliente cliente = new Cliente();
    cliente.setNit(request.nit());
    cliente.setNombre(request.nombre());
    cliente.setApellidoPaterno(request.apellidoPaterno());
    cliente.setApellidoMaterno(request.apellidoMaterno());
    cliente.setDireccion(request.direccion());
    cliente.setTelefono(request.telefono());
    cliente.setEmail(request.email());

    Cliente savedCliente = clienteRepositorio.save(cliente);
    return new ClienteResponse(
            savedCliente.getIdCliente(),
            savedCliente.getNit(),
            savedCliente.getNombre(),
            savedCliente.getApellidoPaterno(),
            savedCliente.getApellidoMaterno(),
            savedCliente.getDireccion(),
            savedCliente.getTelefono(),
            savedCliente.getEmail()
    );
  }

  public ClienteResponse actualizarCliente(Integer id, ClienteRequest request) {
    Cliente cliente = clienteRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

    cliente.setNit(request.nit());
    cliente.setNombre(request.nombre());
    cliente.setApellidoPaterno(request.apellidoPaterno());
    cliente.setApellidoMaterno(request.apellidoMaterno());
    cliente.setDireccion(request.direccion());
    cliente.setTelefono(request.telefono());
    cliente.setEmail(request.email());

    Cliente updatedCliente = clienteRepositorio.save(cliente);
    return new ClienteResponse(
            updatedCliente.getIdCliente(),
            updatedCliente.getNit(),
            updatedCliente.getNombre(),
            updatedCliente.getApellidoPaterno(),
            updatedCliente.getApellidoMaterno(),
            updatedCliente.getDireccion(),
            updatedCliente.getTelefono(),
            updatedCliente.getEmail()
    );
  }

  public void eliminarCliente(Integer id) {
    if (!clienteRepositorio.existsById(id)) {
      throw new RuntimeException("Cliente no encontrado");
    }

    clienteRepositorio.deleteById(id);
  }
}
