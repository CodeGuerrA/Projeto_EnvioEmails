package controller;

public class ExecuteFileOperations {
    public static void main(String[] args) throws Exception {
        String sourceDirectory = "C:\\txt";
        String destinationDirectory = "C:\\txt\\arquivos_lidos";

        // Ler e processar os arquivos CSV
        FileReaderController fileReaderController = new FileReaderController();
        fileReaderController.readFilesAndInsertData(sourceDirectory);

        // Mover os arquivos processados para a pasta de destino
        FileMoverController fileMoverController = new FileMoverController();
        fileMoverController.moveFiles(sourceDirectory, destinationDirectory);
    }
}
