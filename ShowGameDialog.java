import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Clase ShowGamesDialog.
 * Ventana de diálogo para mostrar la lista completa de juegos Pokémon con sus detalles.
 * Muestra los juegos ordenados por puntuación de forma descendente (mejor puntuación primero).
 */
class ShowGamesDialog extends JDialog {
    public ShowGamesDialog(Frame owner) {
        super(owner, "Lista de Juegos Pokémon", true);
        this.setLayout(new BorderLayout());
        
        // Obtener una copia de la lista de juegos y ordenarla por puntuación descendente.
        java.util.List<JuegoPokemon> sortedList = new java.util.ArrayList<>(PokemonManager.getGames());
        sortedList.sort(new java.util.Comparator<JuegoPokemon>() {
            public int compare(JuegoPokemon j1, JuegoPokemon j2) {
                return Integer.compare(j2.getPuntuacion(), j1.getPuntuacion());
            }
        });
        
        // Construir la cadena de texto con la información de todos los juegos.
        StringBuilder sb = new StringBuilder();
        java.util.Iterator<JuegoPokemon> it = sortedList.iterator();
        int rank = 1;
        while (it.hasNext()) {
            JuegoPokemon juego = it.next();
            sb.append(rank).append(". ").append(juego.getDetalle()).append("\n");
            rank++;
        }
        
        // Área de texto para mostrar la lista de juegos.
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        this.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        // Botón para cerrar la ventana de lista.
        JButton okBtn = new JButton("Cerrar");
        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ShowGamesDialog.this.dispose();
            }
        });
        JPanel southPanel = new JPanel();
        southPanel.add(okBtn);
        this.add(southPanel, BorderLayout.SOUTH);
        
        this.setSize(400, 300);
        this.setLocationRelativeTo(owner);
    }
}
