package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import controller.FileMoverController;
import controller.FileReaderController;

public class Uploud extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private FileMoverController fileMoverController;
    private FileReaderController fileReaderController;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Uploud frame = new Uploud();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Uploud() {
        fileMoverController = new FileMoverController();
        fileReaderController = new FileReaderController();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnUpload = new JButton("Upload");
        btnUpload.setBounds(0, 11, 123, 30);
        contentPane.add(btnUpload);

        btnUpload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(true);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int result = fileChooser.showOpenDialog(Uploud.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File[] selectedFiles = fileChooser.getSelectedFiles();
                    String destinationDirectory = "C:\\Users\\Guerra\\Documents\\arquivos_lidos";
                    File destinationDir = new File(destinationDirectory);
                    if (!destinationDir.exists()) {
                        if (destinationDir.mkdirs()) {
                            System.out.println("Diretório de destino criado com sucesso.");
                        } else {
                            System.out.println("Falha ao criar o diretório de destino.");
                        }
                    }
                    
                    //Diretorio que vai processar os dados que forem escolhidos ser ao C:\\txt e oq recebe e o C:\\Users\\Guerra\\Documents\\arquivos_lidos
                    String directoryPath = "C:\\txt"; // Diretório de destino
                    try {
                        fileReaderController.readFilesAndInsertData(directoryPath);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Uploud.this, "Erro ao processar os arquivos CSV.");
                        return; // Interrompe o processo caso haja erro na leitura
                    }

                    // Depois de processar os arquivos, move-os
                    boolean allFilesMoved = true;
                    for (File file : selectedFiles) {
                        String sourcePath = file.getAbsolutePath();
                        File destinationFile = new File(destinationDirectory, file.getName());
                        System.out.println("Movendo arquivo: " + sourcePath + " para " + destinationFile.getAbsolutePath());
                        boolean moveResult = fileMoverController.moveFiles(sourcePath, destinationDirectory);
                        if (!moveResult) {
                            allFilesMoved = false;
                        }
                    }

                    if (allFilesMoved) {
                        JOptionPane.showMessageDialog(Uploud.this, "Arquivos movidos com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(Uploud.this, "Houve um erro ao mover alguns arquivos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(Uploud.this, "Nenhum arquivo selecionado.");
                }
            }
        });
    }
}
