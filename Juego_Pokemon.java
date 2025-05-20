/**
 * Clase JuegoPokemon.
 * Representa un juego de Pokémon con nombre, generación, plataforma y puntuación (ranking) de 1 a 5.
 * Sirve como modelo de datos del sistema de ranking de juegos.
 * Incluye métodos estáticos para validar datos y crear instancias de JuegoPokemon a partir de entradas de texto.
 */
class JuegoPokemon {
    private String nombre;
    private int generacion;
    private String plataforma;
    private int puntuacion;
    
    /**
     * Constructor de JuegoPokemon.
     * @param nombre Nombre del juego Pokémon.
     * @param generacion Generación del juego (número entero positivo).
     * @param plataforma Plataforma del juego (por ejemplo, Game Boy, Nintendo Switch).
     * @param puntuacion Puntuación del juego (entre 1 y 5).
     */
    public JuegoPokemon(String nombre, int generacion, String plataforma, int puntuacion) {
        this.nombre = nombre;
        this.generacion = generacion;
        this.plataforma = plataforma;
        this.puntuacion = puntuacion;
    }
    
    // Métodos getter y setter para los atributos.
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public int getGeneracion() { return generacion; }
    public void setGeneracion(int generacion) { this.generacion = generacion; }
    
    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }
    
    public int getPuntuacion() { return puntuacion; }
    public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }
    
    /**
     * Método estático para validar los datos de entrada y crear un objeto JuegoPokemon si son válidos.
     * @param nombre Nombre del juego.
     * @param genStr Generación del juego (como cadena, se convertirá a int).
     * @param plataforma Plataforma del juego.
     * @param puntStr Puntuación del juego (como cadena, se convertirá a int).
     * @return Un objeto JuegoPokemon creado con los datos proporcionados si son válidos.
     * @throws DatosInvalidos Si algún dato es inválido (cadena vacía, formato numérico incorrecto o valores fuera de rango).
     */
    public static JuegoPokemon crearJuegoDesdeDatos(String nombre, String genStr, String plataforma, String puntStr) throws DatosInvalidos {
        // Validar nombre
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatosInvalidos("El nombre no puede estar vacío.");
        }
        // Validar plataforma
        if (plataforma == null || plataforma.trim().isEmpty()) {
            throw new DatosInvalidos("La plataforma no puede estar vacía.");
        }
        // Validar generación (entero)
        int generacion;
        try {
            generacion = Integer.parseInt(genStr);
        } catch (NumberFormat e) {
            throw new DatosInvalidos("La generación debe ser un número entero.");
        }
        if (generacion <= 0) {
            throw new DatosInvalidos("La generación debe ser un número positivo.");
        }
        // Validar puntuación (entero 1-5)
        int puntuacion;
        try {
            puntuacion = Integer.parseInt(puntStr);
        } catch (NumberFormat e) {
            throw new DatosInvalidos("La puntuación debe ser un número entero.");
        }
        if (puntuacion < 1 || puntuacion > 5) {
            throw new DatosInvalidos("La puntuación debe estar entre 1 y 5.");
        }
        // Si todos los datos son válidos, crear y devolver el objeto JuegoPokemon
        return new JuegoPokemon(nombre.trim(), generacion, plataforma.trim(), puntuacion);
    }
    
    /**
     * Representación en cadena del juego (utilizada para mostrar en listas desplegables).
     * @return El nombre del juego.
     */
    @Override
    public String toString() {
        return nombre;
    }
    
    /**
     * Obtiene una descripción detallada del juego, incluyendo todos sus atributos.
     * @return Cadena con nombre, generación, plataforma y puntuación del juego.
     */
    public String getDetalle() {
        return String.format("%s (Gen %d, %s) - Puntuación: %d", nombre, generacion, plataforma, puntuacion);
    }
}
