package interfaces;

import java.util.List;

import entities.Usuarios;

public interface CRUD {
	public void insertUsuario(Usuarios usuarios) throws Exception;
	public List<Usuarios> listarTodosUsuarios() throws Exception;

}
