package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import Persistence.UsuariosDAO;
import entities.Usuarios;

public class FileReaderController {
    public void readFilesAndInsertData(String directoryPath) throws Exception {
        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".csv"));

        if (listOfFiles != null && listOfFiles.length > 0) {
            UsuariosDAO usuariosDAO = new UsuariosDAO();

            for (File file : listOfFiles) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String lineString;
                    br.readLine(); 

                    while ((lineString = br.readLine()) != null) {
                        String[] vect = lineString.split(",");
                        if (vect.length == 4) {
                            try {
                                
                                String name = vect[0];
                                Integer idade = Integer.parseInt(vect[1]); 
                                String email = vect[2];
                                String number = vect[3];

                                // Criar o objeto de usuário e inserir no banco de dados
                                Usuarios usuario = new Usuarios(name, idade, email, number);
                                usuariosDAO.insertUsuario(usuario);

                                System.out.println("Arquivo: " + file.getName() + " - " + lineString);
                            } catch (NumberFormatException e) {
                               
                                System.out.println("ERRO: Idade inválida na linha: " + lineString);
                            }
                        } else {
                            System.out.println("ERRO: linha com mais ou menos campos do que o esperado. Linha: " + lineString);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Erro ao ler o arquivo: " + file.getName());
                }
            }
        } else {
            System.out.println("Nenhum arquivo CSV encontrado no diretório.");
        }
    }
}
