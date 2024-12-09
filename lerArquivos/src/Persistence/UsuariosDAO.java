package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConexaoBD;
import entities.Usuarios;
import interfaces.CRUD;

public class UsuariosDAO implements CRUD {
	private Connection conexao = null;

	public UsuariosDAO() throws Exception {
		conexao = ConexaoBD.getConexao();
		if (conexao == null) {
			throw new Exception("ERRO DE CONEXAO");
		}
	}

@Override
public void insertUsuario(Usuarios usuarios) throws Exception {
	PreparedStatement preparedStatement = null;
	ResultSet generatedKeys = null;
	try {

		String sql = "insert into Usuarios (nome,idade,email,numero)" + "values(?,?,?,?);";
		preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, usuarios.getName());
		preparedStatement.setInt(2, usuarios.getIdade());
		preparedStatement.setString(3, usuarios.getEmail());
		preparedStatement.setString(4, usuarios.getNumber());
		preparedStatement.executeUpdate();
		System.out.println("Usuarios do CSV adicionados no banco de dados");
		int idcliente = 0;

		generatedKeys = preparedStatement.getGeneratedKeys(); // Obter as chaves geradas
		if (generatedKeys.next()) {
			idcliente = generatedKeys.getInt(1);
			System.out.println("Id do usuario inserido " + idcliente);
		} else {
			throw new Exception("Erro ao obter o ID do cliente.");

		}
	} catch (SQLException erro) {
		throw new Exception("SQL Erro: " + erro.getMessage());
	} catch (Exception erro) {
		throw new Exception("Incluir Persistencia: " + erro);
	} finally {
		if (generatedKeys != null) {
			try {
				generatedKeys.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

@Override
public List<Usuarios> listarTodosUsuarios() throws Exception {
        List<Usuarios> usuarios = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM Usuarios";
            preparedStatement = conexao.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setName(resultSet.getString("nome"));
                usuario.setIdade(resultSet.getInt("idade"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setNumber(resultSet.getString("numero"));
                usuarios.add(usuario);
            }
        } catch (SQLException erro) {
            throw new Exception("Erro ao listar usuários: " + erro.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return usuarios;
    }
}