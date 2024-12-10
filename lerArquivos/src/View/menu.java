package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class menu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // Variável para manter a instância da janela Cadastro de Clientes
    private static cadastroClientes cadastroFrame;
    private static Uploud uploudframe;
    private static EnviarEmails enviarEmailsFrame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    menu frame = new menu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public menu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 434, 22);
        contentPane.add(menuBar);
        
        JMenu mnNewMenu = new JMenu("File");
        menuBar.add(mnNewMenu);
        
        JMenuItem cadastroUsuario = new JMenuItem("Cadastro Clientes");
        cadastroUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verifica se a janela já está aberta
                if (cadastroFrame == null || !cadastroFrame.isVisible()) {
                    cadastroFrame = new cadastroClientes();  // Cria a instância da classe cadastroClientes
                    cadastroFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Apenas esconde a janela
                    cadastroFrame.setLocationRelativeTo(null); // Centraliza a janela na tela
                    cadastroFrame.setVisible(true); // Torna a janela visível
                } else {
                    cadastroFrame.toFront(); // Caso a janela já esteja aberta, traz ela para frente
                }
            }
        });
        mnNewMenu.add(cadastroUsuario);
        
        JMenuItem enviarEmails = new JMenuItem("Enviar E-mails");
        enviarEmails.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(enviarEmailsFrame == null || !enviarEmailsFrame.isVisible()) {
        			enviarEmailsFrame = new EnviarEmails();
        			enviarEmailsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Apenas esconde a janela
        			enviarEmailsFrame.setLocationRelativeTo(null); // Centraliza a janela na tela
        			enviarEmailsFrame.setVisible(true); // Torna a janela visível
        		}else {
        			enviarEmailsFrame.toFront(); // Caso a janela já esteja aberta, traz ela para frente
                }
        		
        	}
        });
        mnNewMenu.add(enviarEmails);
        
        JSeparator separator = new JSeparator();
        mnNewMenu.add(separator);
        
        JMenuItem Uploud = new JMenuItem("Upload");
        Uploud.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(uploudframe == null || !uploudframe.isVisible()) { // Verifica se a janela está visível
        			uploudframe = new Uploud();
        			uploudframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Apenas esconde a janela
        			uploudframe.setLocationRelativeTo(null); // Centraliza a janela na tela
        			uploudframe.setVisible(true); // Torna a janela visível
        		} else {
        			uploudframe.toFront(); // Caso a janela já esteja aberta, traz ela para frente
                }
        	}
        });
        mnNewMenu.add(Uploud);
        
        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Close");
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Fecha o aplicativo

            }
        });
        mnNewMenu.add(mntmNewMenuItem_3);
        
        // Centraliza a janela principal na tela quando o programa é iniciado
        setLocationRelativeTo(null);
    }
}
