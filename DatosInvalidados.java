/**
 * Excepción personalizada para indicar errores de validación de datos de un juego Pokémon.
 */
class DatosInvalidosException extends Exception {
    public DatosInvalidosException(String message) {
        super(message);
    }
}

 