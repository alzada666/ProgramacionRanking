import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Clase DeleteGameDialog.
 * Ventana de diálogo para eliminar un juego Pokémon de la lista.
 * Permite seleccionar un juego y confirmar su eliminación.
 */
class DeleteGameDialog extends JDialog {
    private JComboBox<JuegoPokemon> gameCombo;
    
    public DeleteGameDialog(Frame owner) {
        super(owner, "Eliminar Juego Pokémon", true);
        this.setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel(new FlowLayout());
        mainPanel.add(new JLabel("Seleccionar juego a eliminar:"));
        gameCombo = new JComboBox<>(PokemonManager.getGames().toArray(new JuegoPokemon[0]));
        mainPanel.add(gameCombo);
        
        JPanel buttonsPanel = new JPanel();
        JButton deleteBtn = new JButton("Eliminar");
        JButton cancelBtn = new JButton("Cancelar");
        buttonsPanel.add(deleteBtn);
        buttonsPanel.add(cancelBtn);
        
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(owner);
        
        // Listener para realizar la eliminación.
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JuegoPokemon selected = (JuegoPokemon) gameCombo.getSelectedItem();
                if (selected != null) {
                    // Confirmación antes de eliminar.
                    int confirm = JOptionPane.showConfirmDialog(DeleteGameDialog.this,
                        "¿Seguro que desea eliminar \"" + selected.getNombre() + "\"?",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        PokemonManager.removeGame(selected);
                        JOptionPane.showMessageDialog(DeleteGameDialog.this,
                            "Juego eliminado.", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
                        DeleteGameDialog.this.dispose();
                    }
                }
            }
        });
        
        // Listener para cancelar la eliminación.
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteGameDialog.this.dispose();
            }
        });
    }
}
