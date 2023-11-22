/**
 *
 * @author armandorivera
 */

public class Minimax {
    
    /**
     * En esta clase vamos a hacer uso del algoritmo minimax para poder obtener la mejor jugada.
     * Para entender este algoritmo en español: https://youtu.be/QJjM7EKDRuc
     */
    
    /**
     * Declaramos nuestro constructor
     * @param board - El tablero actual
     * @param iaPlayer - Para conocer si el sistema es X u O
     */
    public Minimax(String[][] board, String iaPlayer){
        this.board = board;
        // Decimmos quien es cada personaje
        this.iaPlayer = iaPlayer;
        if(iaPlayer.equals("X"))
            humanPlayer = "O";
        else
            humanPlayer = "X";
        
    }
    // Declaramos nuestras variables
    private final String[][] board;
    private final String iaPlayer;
    private final String humanPlayer;
    
    // Este nodo va a almacenar la posición del mejor movimiento (x,y)
    public NodeMove bestMove = new NodeMove();
    
    /**
     * Esta función se va a encargar de hacer la jugada y sera la jugada que mas convenga para ganar
     */
    public void makeBestMove(){
        int topValue = -100;
        /**
         * Vamos a hacer la evaluacion de cada una de las celdas disponibles en el tablero
         * Lo que va a pasar es que vamos a ir checando cada espacio posible y de ahi vamos a sacar las demas jugada
         * y mientras vamos avanzando vamos guardando la mejor posicion (x,y), para esto usamos topValue
         */
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                // si la celda del tablero esta vacia
                if(board[i][j].equals("empty")){
                    
                    // marcamos la posicion del jugador
                    board[i][j] = iaPlayer;
                    // evaluamos las siguientes jugadas posibles
                    // tablero, profundidad, isIA
                    int moveValue = minimax(board, 0, false);
                    // Ya que hicimos el analisis quitamos la jugada del jugador 
                    board[i][j] = "empty";
                    
                    // si el valor de la movida actual es mas que la mejor, actualizamos
                    if (moveValue > topValue){
                        // Lo que actualizamos es nuestro nodo
                        bestMove.setX(i);
                        bestMove.setY(j);
                        topValue = moveValue;
                    }
                    // Mientras no terminemos de recorrer la matriz vamos a seguir recorriendola
                }
            }
        }
        
    }
    
    /**
     * Este es el algoritmo de minimax, el cual funciona de manera recursiva
     * @param tempBoard - el tablero despues de que el jugador hizo una movida
     * @param depth - La profundidad en la que vamos, recordando que se va turtando entre max y min
     * @param isIA - Para saber si es la ia o el contrincante
     * @return 
     * 
     * 10 - GANA IA
     *  0 - EMPATE
     * -10- GANA CONTRINCANTE
     */
    public int minimax(String tempBoard[][], int depth, boolean isIA){
        int score;
        // Nos va a permitir hacer la evaluación del tablero
        GameRules gameRules = new GameRules();
        // Checamos si hay un ganador
        if (gameRules.someonewin(tempBoard)){
            // Si hay un ganador checamos quien es
            String theWinnerIs = gameRules.getWinner();
            // Si el ganador es IA asignamos 10, si no es -10
            if(theWinnerIs.equals(iaPlayer))
                score = 10;
            else
                score = -10;
            return score;
        }
        // En caso de que ya no existan movimientos disponibles
        if (gameRules.noMovesLeft(tempBoard) == true)
            return 0;
        
        // Si es el turno del maximizer
        if (isIA){
            int bestValue = -100;
            // Recorremos el tablero
            for (int i = 0 ; i < 3 ; i++){
                for (int j = 0 ; j< 3 ; j++){
                    if (tempBoard[i][j].equals("empty")){
                        // hacemos la jugada
                        tempBoard[i][j] = iaPlayer;
                        // llamamos a minimax de forma recursiva para escoger el maximo valor
                        bestValue = Math.max(bestValue, minimax(tempBoard, depth+1, !isIA));
                        // ya que tenemos el valor de best quitamos la jugada
                        tempBoard[i][j] = "empty";
                    }
                }
            }
            // regresamos el mejor
            return bestValue;
        }
        else{
            int bestValue = 100;
            // Recorremos el tablero
            for (int i = 0 ; i < 3 ; i++){
                for (int j = 0 ; j< 3 ; j++){
                    if (tempBoard[i][j].equals("empty")){
                        // hacemos la jugada
                        tempBoard[i][j] = humanPlayer;
                        // llamamos minimax de manera recursiva
                        bestValue = Math.min(bestValue, minimax(tempBoard, depth+1, !isIA));
                        // revertimos la jugada
                        tempBoard[i][j] = "empty";
                    }
                }
            }
            // regresamos el mejor
            return bestValue;
        }
        

    }
    
}
