package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileMoverController {
    public boolean moveFiles(String sourcePath, String destinationDirectory) {
        File file = new File(sourcePath);
        File destinationFile = new File(destinationDirectory, file.getName());

        try {
            // Mover o arquivo para o diretório de destino
            Path source = file.toPath();
            Path destination = destinationFile.toPath();
            
            // Mover o arquivo, substituindo o existente
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Arquivo movido com sucesso: " + file.getName());
            return true; // Retorna verdadeiro se o arquivo for movido com sucesso
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao mover o arquivo: " + file.getName());
            return false; // Retorna falso se houver erro
        }
    }
}
