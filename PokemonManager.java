/**
 * Clase PokemonManager.
 * Gestiona la lista de juegos Pokémon almacenados en la aplicación.
 * Proporciona métodos estáticos para añadir, obtener y eliminar juegos de la lista.
 */
class PokemonManager {
    // Lista estática de juegos Pokémon (almacenamiento en memoria temporal).
    private static java.util.List<JuegoPokemon> juegos = new java.util.ArrayList<>();
    
    /**
     * Añade un juego a la lista de juegos.
     * @param juego Objeto JuegoPokemon a añadir.
     */
    public static void addGame(JuegoPokemon juego) {
        juegos.add(juego);
    }
    
    /**
     * Devuelve la lista de todos los juegos almacenados.
     * @return Lista de JuegoPokemon.
     */
    public static java.util.List<JuegoPokemon> getGames() {
        return juegos;
    }
    
    /**
     * Elimina un juego de la lista.
     * @param juego Objeto JuegoPokemon a eliminar.
     */
    public static void removeGame(JuegoPokemon juego) {
        juegos.remove(juego);
    }
}
