/**
 *
 * @author armandorivera
 */
public class GameRules {
    
    /** 
     * En esta clase checamos si en el tablero ya existe una condición de victoria
     */

    // Alguien ha ganado?
    private boolean didSomeoneWin = false;
    // Quien es el ganador?
    private String winner;
    
    
    
    /**
     * Funciones
     */
    
    // Getter para saber al ganador
    public String getWinner(){
        return winner;
    }
    
    /**
     * Checamos si los 3 iguales que detecto son X o O y no empty
     * Puede ser que tengamos tres iguales: [empty][empty][empty] pero esto no significa que alguien gano
     * @param position - Posición de algun elemento del tablero
     * @return - regresa si lo que hay en la posición es un jugador o no
     */
    public boolean isPlayer(String position){
        // La función 'someonewin()' hace uso de esta función, si existe un jugador entonces retorna true
        return !"empty".equals(position);
    }
    
    /**
     * Checamos el tablero que recibimos y vamos a regresar el valor booleano para decir si alguien gano o no
     * @param board
     * @return 
     */
    public boolean someonewin(String[][] board){
        if((board[0][0].equals(board[0][1]))&&(board[0][1].equals(board[0][2]))){
            /**
             * Checamos si existe la secuencia:
             * [*][*][*]
             * [ ][ ][ ]
             * [ ][ ][ ]
             */
            
            // Si son iguales checamos, esto es un personaje o son todas empty?
            if(isPlayer(board[0][0])){
                // Si se trata de un jugador entonces tenemos un ganador
                didSomeoneWin = true;
                // como las tres son ijguales no importa si escogemos el valor de:
                // [0][0] o [0][1] o [0][2], basta con tomar uno para saber quien es el ganador
                winner = board[0][0];
            }
            //System.out.println("Cheque fase 1");
        }
        if((board[1][0].equals(board[1][1]))&&(board[1][1].equals(board[1][2]))){
            /**
             * Checamos si existe la secuencia:
             * [ ][ ][ ]
             * [*][*][*]
             * [ ][ ][ ]
             */
            
            // Si son iguales checamos, esto es un personaje o son todas empty?
            if(isPlayer(board[1][0])){
                didSomeoneWin = true;
                winner = board[1][0];
            }
            //System.out.println("Cheque fase 2");
        }
        if((board[2][0].equals(board[2][1]))&&(board[2][1].equals(board[2][2]))){
            /**
             * Checamos si existe la secuencia:
             * [ ][ ][ ]
             * [ ][ ][ ]
             * [*][*][*]
             */
            
            // Si son iguales checamos, esto es un personaje o son todas empty?
            if(isPlayer(board[2][0])){
                didSomeoneWin = true;
                winner = board[2][0];
            }
            //System.out.println("Cheque fase 3");
        }
        if((board[0][0].equals(board[1][0]))&&(board[1][0].equals(board[2][0]))){
            /**
             * Checamos si existe la secuencia:
             * [*][ ][ ]
             * [*][ ][ ]
             * [*][ ][ ]
             */
            
            // Si son iguales checamos, esto es un personaje o son todas empty?
            if(isPlayer(board[0][0])){
                didSomeoneWin = true;
                winner = board[0][0];
            }
            //System.out.println("Cheque fase 4");
        }
        if((board[0][1].equals(board[1][1]))&&(board[1][1].equals(board[2][1]))){
            /**
             * Checamos si existe la secuencia:
             * [ ][*][ ]
             * [ ][*][ ]
             * [ ][*][ ]
             */
            
            // Si son iguales checamos, esto es un personaje o son todas empty?
            if(isPlayer(board[0][1])){
                didSomeoneWin = true;
                winner = board[0][1];
            }
            //System.out.println("Cheque fase 5");
        }
        if((board[0][2].equals(board[1][2]))&&(board[1][2].equals(board[2][2]))){
            /**
             * Checamos si existe la secuencia:
             * [ ][ ][*]
             * [ ][ ][*]
             * [ ][ ][*]
             */
            
            // Si son iguales checamos, esto es un personaje o son todas empty?
            if(isPlayer(board[0][2])){
                didSomeoneWin = true;
                winner = board[0][2];
            }
            //System.out.println("Cheque fase 6");
        }
        if((board[0][0].equals(board[1][1]))&&(board[1][1].equals(board[2][2]))){
            /**
             * Checamos si existe la secuencia:
             * [*][ ][ ]
             * [ ][*][ ]
             * [ ][ ][*]
             */
            
            // Si son iguales checamos, esto es un personaje o son todas empty?
            if(isPlayer(board[1][1])){
                didSomeoneWin = true;
                winner = board[1][1];
            }
            //System.out.println("Cheque fase 7");
        }
        if((board[2][0].equals(board[1][1]))&&(board[1][1].equals(board[0][2]))){
            /**
             * Checamos si existe la secuencia:
             * [ ][ ][*]
             * [ ][*][ ]
             * [*][ ][ ]
             */
            
            // Si son iguales checamos, esto es un personaje o son todas empty?
            if(isPlayer(board[1][1])){
                didSomeoneWin = true;
                winner = board[1][1];
            }
            //System.out.println("Cheque fase 8");
        }
        // Decimos si hubo o no ganador
        return didSomeoneWin;
    }
    
    // Checamos si hay un empate, nos ayuda para minimax
    public boolean itsATie(String[][] currentBoard){
        int movesLeft = 0;
        // Recorremos el tablero para ver si hay algun lugar vacio
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                if(currentBoard[i][j].equals("empty"))
                    movesLeft++;
            }
        }
        // Si movesLeft == 0 regresamos que true, es decir, si hay empate, de lo contrario false, no hay empate
        return movesLeft == 0;
        
    }
    
    
    // para checar si hay jugadas disposnibles
    public boolean noMovesLeft(String[][] currentBoard){
        int movesLeft = 0;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                // Si hay espacios vacios si hay jugadas
                if(currentBoard[i][j].equals("empty"))
                    movesLeft++;
            }
        }
        return movesLeft == 0;
    }
    
}
