package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

import Persistence.UsuariosDAO;
import entities.Usuarios;

public class EnviarEmails extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private List<Usuarios> listaUsuarios;
    private JCheckBox[] checkBoxes;
    private JTextArea textArea = new JTextArea();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EnviarEmails frame = new EnviarEmails();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public EnviarEmails() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 613, 406);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Selecione..."});
        comboBox.setBounds(226, 11, 150, 25);
        contentPane.add(comboBox);

        JButton btnNewButton = new JButton("Enviar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String meuEmailString = "carlosgarcianeto229@gmail.com";
                String minhaSenhaString = "cpfeqocmraokwjkx";

                HtmlEmail email = new HtmlEmail();
                email.setHostName("smtp.gmail.com");
                email.setSmtpPort(465);
                email.setAuthenticator(new DefaultAuthenticator(meuEmailString, minhaSenhaString));
                email.setSSLOnConnect(true);

                try {
                    String mensagem = textArea.getText().trim();
                    if (mensagem.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "A mensagem não pode estar vazia.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    email.setFrom(meuEmailString);
                    email.setSubject("Vendemos minoxidill");
                    email.setMsg(mensagem);

                    boolean algumSelecionado = false;
                    for (JCheckBox checkBox : checkBoxes) {
                        if (checkBox.isSelected()) {
                            algumSelecionado = true;
                            for (Usuarios usuario : listaUsuarios) {
                                if (usuario.getName().equals(checkBox.getText())) {
                                    email.addTo(usuario.getEmail());
                                    break;
                                }
                            }
                        }
                    }

                    if (!algumSelecionado) {
                        JOptionPane.showMessageDialog(null, "Selecione pelo menos um destinatário.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    email.send();
                    JOptionPane.showMessageDialog(null, "E-mails enviados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erro ao enviar e-mails: " + e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnNewButton.setBounds(481, 323, 89, 33);
        contentPane.add(btnNewButton);

        textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        textArea.setBounds(22, 58, 548, 253);
        contentPane.add(textArea);

        JPopupMenu popupMenu = new JPopupMenu();

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));

        try {
            UsuariosDAO dao = new UsuariosDAO();
            listaUsuarios = dao.listarTodosUsuarios();

            checkBoxes = new JCheckBox[listaUsuarios.size()];
            for (int i = 0; i < listaUsuarios.size(); i++) {
                Usuarios usuario = listaUsuarios.get(i);
                checkBoxes[i] = new JCheckBox(usuario.getName());
                checkBoxPanel.add(checkBoxes[i]);
            }

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
            popupMenu.add(selectAllCheckBox, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(checkBoxPanel);
        scrollPane.setPreferredSize(new java.awt.Dimension(200, 150));
        popupMenu.add(scrollPane);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(comboBox, 0, comboBox.getHeight());
            }
        });
    }
}
