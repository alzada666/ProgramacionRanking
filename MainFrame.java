// Import de librerías Swing y AWT para la interfaz gráfica.
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase MainFrame.
 * Ventana principal de la aplicación que muestra el menú principal con las opciones CRUD (Añadir, Editar, Mostrar, Eliminar) y Salir.
 * Gestiona las interacciones del usuario en el menú principal.
 */
class MainFrame extends JFrame {
    public MainFrame() {
        super("Ranking de Juegos Pokémon");
        // Configuración básica de la ventana principal.
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Evitar cierre directo de la ventana con la 'X', para que solo se pueda salir por el menú.
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Al intentar cerrar, mostrar mensaje indicando la forma correcta de salir.
                JOptionPane.showMessageDialog(MainFrame.this,
                    "Por favor, use la opción 'Salir' del menú para cerrar la aplicación.",
                    "Salir",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // Crear componentes de la interfaz.
        JLabel titleLabel = new JLabel("Sistema de Rankings Pokémon", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        
        JButton addButton = new JButton("Añadir juego");
        JButton editButton = new JButton("Editar juego");
        JButton showButton = new JButton("Mostrar juegos");
        JButton deleteButton = new JButton("Eliminar juego");
        JButton exitButton = new JButton("Salir");
        
        // Establecer texto emergente (tooltip) en los botones.
        addButton.setToolTipText("Añadir un nuevo juego Pokémon al ranking");
        editButton.setToolTipText("Editar un juego Pokémon existente");
        showButton.setToolTipText("Mostrar todos los juegos en el ranking");
        deleteButton.setToolTipText("Eliminar un juego del ranking");
        exitButton.setToolTipText("Salir de la aplicación");
        
        // Disposición de los componentes en la ventana (vertical).
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        // Añadir título y botones al panel principal con separadores para espaciar.
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(addButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(editButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        showButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(showButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(deleteButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(exitButton);
        
        // Añadir relleno alrededor del panel principal.
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Agregar el panel principal a la ventana.
        this.getContentPane().add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null); // centrar ventana
        this.setVisible(true);
        
        // Registrar listeners para las acciones de los botones.
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddGameDialog(MainFrame.this).setVisible(true);
            }
        });
        
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (PokemonManager.getGames().isEmpty()) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                        "No hay juegos para editar.", "Editar", JOptionPane.WARNING_MESSAGE);
                } else {
                    new EditGameDialog(MainFrame.this).setVisible(true);
                }
            }
        });
        
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (PokemonManager.getGames().isEmpty()) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                        "No hay juegos para mostrar.", "Mostrar", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    new ShowGamesDialog(MainFrame.this).setVisible(true);
                }
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (PokemonManager.getGames().isEmpty()) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                        "No hay juegos para eliminar.", "Eliminar", JOptionPane.WARNING_MESSAGE);
                } else {
                    new DeleteGameDialog(MainFrame.this).setVisible(true);
                }
            }
        });
        
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Confirmar salida antes de cerrar.
                int confirm = JOptionPane.showConfirmDialog(MainFrame.this,
                        "¿Seguro que desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
}
