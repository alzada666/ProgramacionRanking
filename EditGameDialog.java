import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Clase EditGameDialog.
 * Ventana de diálogo para editar los datos de un juego Pokémon existente.
 * Permite seleccionar un juego de la lista y modificar sus atributos.
 */
class EditGameDialog extends JDialog {
    private JComboBox<JuegoPokemon> gameCombo;
    private JTextField nombreField;
    private JTextField genField;
    private JTextField plataformaField;
    private JTextField puntuacionField;
    
    private JuegoPokemon juegoSeleccionado;
    
    public EditGameDialog(Frame owner) {
        super(owner, "Editar Juego Pokémon", true);
        this.setLayout(new BorderLayout());
        
        // Panel superior para seleccionar el juego a editar.
        JPanel selectPanel = new JPanel(new FlowLayout());
        selectPanel.add(new JLabel("Seleccionar juego:"));
        gameCombo = new JComboBox<>(PokemonManager.getGames().toArray(new JuegoPokemon[0]));
        selectPanel.add(gameCombo);
        
        // Panel de formulario con campos para editar los datos.
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
        
        // Panel de botones Guardar/Cancelar.
        JPanel buttonsPanel = new JPanel();
        JButton saveBtn = new JButton("Guardar");
        JButton cancelBtn = new JButton("Cancelar");
        buttonsPanel.add(saveBtn);
        buttonsPanel.add(cancelBtn);
        
        this.add(selectPanel, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(owner);
        
        // Inicializar campos con los datos del primer juego seleccionado por defecto.
        if (PokemonManager.getGames().size() > 0) {
            juegoSeleccionado = (JuegoPokemon) gameCombo.getSelectedItem();
            fillFieldsWithGame(juegoSeleccionado);
        }
        
        // Actualizar campos cuando se selecciona otro juego del combo.
        gameCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                juegoSeleccionado = (JuegoPokemon) gameCombo.getSelectedItem();
                if (juegoSeleccionado != null) {
                    fillFieldsWithGame(juegoSeleccionado);
                }
            }
        });
        
        // Listener para guardar cambios.
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (juegoSeleccionado == null) {
                    return;
                }
                String nombre = nombreField.getText();
                String genStr = genField.getText();
                String plataforma = plataformaField.getText();
                String puntStr = puntuacionField.getText();
                try {
                    // Reutilizar la validación intentando crear un nuevo objeto temporal.
                    JuegoPokemon temp = JuegoPokemon.crearJuegoDesdeDatos(nombre, genStr, plataforma, puntStr);
                    // Aplicar los cambios al objeto seleccionado existente.
                    juegoSeleccionado.setNombre(temp.getNombre());
                    juegoSeleccionado.setGeneracion(temp.getGeneracion());
                    juegoSeleccionado.setPlataforma(temp.getPlataforma());
                    juegoSeleccionado.setPuntuacion(temp.getPuntuacion());
                    JOptionPane.showMessageDialog(EditGameDialog.this,
                        "Juego actualizado correctamente.", "Editar", JOptionPane.INFORMATION_MESSAGE);
                    EditGameDialog.this.dispose();
                } catch (DatosInvalidos ex) {
                    JOptionPane.showMessageDialog(EditGameDialog.this,
                        ex.getMessage(), "Datos inválidos", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Listener para cancelar la edición.
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditGameDialog.this.dispose();
            }
        });
    }
    
    /**
     * Rellena los campos de texto con los datos de un juego dado.
     * @param juego El juego cuyos datos se van a mostrar en el formulario.
     */
    private void fillFieldsWithGame(JuegoPokemon juego) {
        nombreField.setText(juego.getNombre());
        genField.setText(String.valueOf(juego.getGeneracion()));
        plataformaField.setText(juego.getPlataforma());
        puntuacionField.setText(String.valueOf(juego.getPuntuacion()));
    }
}
