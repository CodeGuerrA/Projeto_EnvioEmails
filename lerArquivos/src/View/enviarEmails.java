package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import Persistence.UsuariosDAO;
import entities.Usuarios;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

public class enviarEmails extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    enviarEmails frame = new enviarEmails();
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
    public enviarEmails() {
        // Configuração da janela principal
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 613, 406);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); // Layout absoluto para posicionamento manual

        // ComboBox que serve como gatilho para mostrar o menu de opções
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Selecione..."});
        comboBox.setBounds(226, 11, 150, 25);
        contentPane.add(comboBox);

        JButton btnNewButton = new JButton("Enviar");
        btnNewButton.setBounds(481, 323, 89, 33);
        contentPane.add(btnNewButton);
        
        JTextArea textArea = new JTextArea();
        textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        textArea.setBounds(22, 58, 548, 253);
        contentPane.add(textArea);

        // PopupMenu que conterá o painel de rolagem
        JPopupMenu popupMenu = new JPopupMenu();

        // Painel que conterá os checkboxes
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        try {
            // Obter a lista de usuários
            UsuariosDAO dao = new UsuariosDAO();
            List<Usuarios> listaUsuarios = dao.listarTodosUsuarios();

          

            // Criar checkboxes para cada usuário
            JCheckBox[] checkBoxes = new JCheckBox[listaUsuarios.size()];
            for (int i = 0; i < listaUsuarios.size(); i++) {
                Usuarios usuario = listaUsuarios.get(i);
                checkBoxes[i] = new JCheckBox(usuario.getName());
                checkBoxPanel.add(checkBoxes[i]);
            }

            // Opção "Selecionar Todos"
            JCheckBox selectAllCheckBox = new JCheckBox("Selecionar Todos");
            selectAllCheckBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean selected = selectAllCheckBox.isSelected();
                    for (JCheckBox checkBox : checkBoxes) {
                        checkBox.setSelected(selected);
                    }
                }
            });
            popupMenu.add(selectAllCheckBox, 0); // Adiciona "Selecionar Todos" no topo do menu

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Adicionando barra de rolagem ao painel
        JScrollPane scrollPane = new JScrollPane(checkBoxPanel);
        scrollPane.setPreferredSize(new java.awt.Dimension(200, 150)); // Define o tamanho da área visível
        popupMenu.add(scrollPane);

        // Ação do ComboBox para mostrar o PopupMenu
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exibe o PopupMenu na posição abaixo do ComboBox
                popupMenu.show(comboBox, 0, comboBox.getHeight());
            }
        });
    }
}
