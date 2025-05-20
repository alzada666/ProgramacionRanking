/**
 * Excepción personalizada para indicar errores de validación de datos de un juego Pokémon.
 */
class DatosInvalidos extends Exception {
    public DatosInvalidos(String message) {
        super(message);
    }
}

 