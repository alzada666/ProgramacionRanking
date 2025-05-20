import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase AddGameDialog.
 * Ventana de diálogo para añadir un nuevo juego Pokémon al ranking.
 * Proporciona campos de texto para ingresar los datos del juego y botones para añadir o cancelar.
 */
class AddGameDialog extends JDialog {
    private JTextField nombreField;
    private JTextField genField;
    private JTextField plataformaField;
    private JTextField puntuacionField;
    
    public AddGameDialog(Frame owner) {
        super(owner, "Añadir Juego Pokémon", true);
        this.setLayout(new BorderLayout());
        
        // Panel de formulario con etiqueta y campo para cada dato.
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField(15);
        formPanel.add(nombreField);
        
        formPanel.add(new JLabel("Generación:"));
        genField = new JTextField(5);
        formPanel.add(genField);
        
        formPanel.add(new JLabel("Plataforma:"));
        plataformaField = new JTextField(10);
        formPanel.add(plataformaField);
        
        formPanel.add(new JLabel("Puntuación (1-5):"));
        puntuacionField = new JTextField(2);
        formPanel.add(puntuacionField);
        
        // Panel de botones Añadir/Cancelar.
        JPanel buttonsPanel = new JPanel();
        JButton addBtn = new JButton("Añadir");
        JButton cancelBtn = new JButton("Cancelar");
        buttonsPanel.add(addBtn);
        buttonsPanel.add(cancelBtn);
        
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(owner);
        
        // Listener para el botón Añadir.
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String genStr = genField.getText();
                String plataforma = plataformaField.getText();
                String puntStr = puntuacionField.getText();
                
                try {
                    // Validar y crear juego.
                    JuegoPokemon juego = JuegoPokemon.crearJuegoDesdeDatos(nombre, genStr, plataforma, puntStr);
                    PokemonManager.addGame(juego);
                    JOptionPane.showMessageDialog(AddGameDialog.this,
                        "Juego añadido correctamente.", "Añadir", JOptionPane.INFORMATION_MESSAGE);
                    AddGameDialog.this.dispose();
                } catch (DatosInvalidos ex) {
                    // Mostrar mensaje de error si los datos son inválidos.
                    JOptionPane.showMessageDialog(AddGameDialog.this,
                        ex.getMessage(), "Datos inválidos", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Listener para el botón Cancelar.
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddGameDialog.this.dispose();
            }
        });
    }
}
