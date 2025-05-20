import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase PokemonApp.
 * Contiene el método main para iniciar la aplicación de Rankings de Juegos Pokémon.
 * Inicializa el frame principal dentro del hilo de eventos de Swing.
 */
public class PokemonApp {
    public static void main(String[] args) {
        // Iniciar la interfaz gráfica en el hilo de distribución de eventos (EDT) de Swing.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
