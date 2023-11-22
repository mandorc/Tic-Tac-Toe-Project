
import javax.swing.JFrame;      // Nos permite crear las nuevas ventanas que apareceran
import javax.swing.ImageIcon;   // para agregar un icono a neustra app
import java.util.Random;        // Para poder generar números aleatorios

/**
 *
 * @author armandorivera
 * 
 */
public class GameFrame extends javax.swing.JFrame {

    /**
     * Constructor del JFrame 'GameFrame'
     */
    public GameFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        iniBoard();
    }
    /**
     * Cuando creamos el frame por primera vez
     * @param playerName - El personaje que escogio el usuario
     * @param iaPlaying  - El usuario va a jugar con otro humano o con la computadora?
     */
    public GameFrame(String playerName, boolean iaPlaying) {
        // Inicializamos el frame
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        // Ajustamos nuestras variables
        this.iaPlaying = iaPlaying;
        this.playerName = playerName;
        /**
         * Iniciamos nuestro tablero de la forma:
         * [empty][empty][empty]
         * [empty][empty][empty]
         * [empty][empty][empty]
         */
        iniBoard();
        // Si IA juega, entonces su primera jugada es aleatoria
        // quise que fuera aleatoria para que cada juego fuera "diferente"
        if(iaPlaying && playerName.equals("O")){
            playRandomIA();
            changePlayer();
        }
    }
    /**
     * Cuando es nuestra segunda partida en adelante
     * @param xScore        - Score del personaje X
     * @param oScore        - Score del personaje O
     * @param iaPlaying     - Estamos jugando con la computadora?
     * @param playerName    - Que personaje es el usuario
     */
    public GameFrame(int xScore, int oScore, boolean iaPlaying, String playerName){
        // Hacemos la asignación de nuestras variables
        this.xScore = xScore;
        this.oScore = oScore;
        this.playerName = playerName;
        this.iaPlaying = iaPlaying;
        /**
         * Iniciamos nuestro tablero de la forma:
         * [empty][empty][empty]
         * [empty][empty][empty]
         * [empty][empty][empty]
         */
        iniBoard();
        // Configuracion de la ventana frame
        initComponents();
        // La ventana aparece en el centro
        this.setLocationRelativeTo(null);
        // La ventana no puede cambiar de tamaño - Esto para no andar ajustando los contrains
        setResizable(false);
        // Como no es la primera vez que jugamos, debemos actualizar el score label del frame
        xScoreLabel.setText(Integer.toString(xScore));
        oScoreLabel.setText(Integer.toString(oScore));
        // Si el usuario escoge ser O, entonces la computadora es X y siempre empieza X
        // para hacer el juego diferente se empieza con una posición aleatoria.
        if(iaPlaying && playerName.equals("O")){
            playRandomIA();
            changePlayer();
        }
        
        
    }
    
    /**
     * Variables que vamos a estar utilizando
     */
    // Personaje seleccionado
    private String playerName;
    private String currentPlayer = "X";
    // Entre los dos solo se pueden hacer 9 porque la matriz es de 3x3
    private int playerMoves = 0;
    // Vamos a jugar contra una persona o contra el sistema
    private boolean iaPlaying;
    // El tablero donde se registra el juego
    private String board[][] = new String[3][3];
    // Creamos este objeto porque tiene los metodos para ver si hay ganador y quien es el ganador
    GameRules gameRules = new GameRules();
    // Alguien ya gano?
    boolean isThereAWinner = false;
    // Los score de los jugadores
    private int xScore = 0;
    private int oScore = 0;
    
    /**
     * FUNCIONES
     */
    
    // Da valores E (empty) al tablero
    private void iniBoard(){
        /**
         * Iniciamos nuestro tablero de la forma:
         * [empty][empty][empty]
         * [empty][empty][empty]
         * [empty][empty][empty]
         */
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                board[i][j] = "empty";
            }
        }
    }
    
    /**
     * Cuando un jugador ha tirado un  movimiento se cambia el jugador
     */
    private void changePlayer(){
        // Muestra el estado actual del tablero
        // Esto nos permite unicamente ver como va registrando los movimientos
        // si llega a haber un error aqui podemos ver que pasó (durante la programación del programa)
        printBoard();
        // Aumenta el numero de movimientos
        playerMoves++;
        if(playerMoves>3){
            // Enviamos el tablero para ver si hay un ganador, no importa quien sea
            if(gameRules.someonewin(board)){
                // Si hay un ganador entonces checamos quien es el ganador para aumentar su score
                if(gameRules.getWinner().equals("X")){
                    xScore++;
                } else {
                    oScore++;
                }
                /**
                 * Como hay ganador, el juego ha terminado
                 * Creamos la el frame de juego terminado donde enviamos los dos score, el ganador
                 * también enviamos la configuración que el usuario ingreso cuando empezó el juego
                 * por ultimo, cerramos esta ventana del juego para solo mostrar la ventana de fin de juego
                 */
                JFrame gameFinished = new GameFinishedFrame(xScore, oScore, gameRules.getWinner(),iaPlaying, playerName);
                isThereAWinner = true;
                gameFinished.setVisible(true);
                this.setVisible(false);
            }
        }
        /**
         * Si ya no hay espacios libres en el tablero y no hay ganador, entonces creamos nuestra ventana de empate
         */
        if(playerMoves==9 && isThereAWinner != true){
            boolean empate = true;
            // Mandamos a crear la ventana junto con la configuración seleccionada por el usuario
            JFrame gameFinished = new GameFinishedFrame(empate, xScore, oScore, iaPlaying, playerName);
            gameFinished.setVisible(true);
            this.setVisible(false);
        }
        // Cambiamos el jugador de X a O o de O a X.
        if (currentPlayer.equals("X")){
            currentPlayer = "O";
        }
        else
            currentPlayer = "X";
        /**
         * IA  esta jugando?
         * Si esta jugando entonces dejamos que haga un movimiento usando el algoritmo minimax
        */
        if(iaPlaying && currentPlayer!=playerName){
            // Función para que el sistema haga su movimiento
            iaMakeMove();
        }
        
    }
    
    /**
     * Esta función permite que el sistema pueda realizar el movimiento que mejor le convenga
     */
    private void iaMakeMove(){
        //System.out.println("Hace movida");
        if(playerMoves<9){ 
            /**
             * creamos un objeto de clase Minimax
             * Vamos a mandar los parametros:
             * @param board - el tablero actual
             * @param currentPlayer - el jugador del sistema, X/O dependiendo de la elección del usuario
             */
            Minimax minimaxAlgorithm = new Minimax(board, currentPlayer);
            minimaxAlgorithm.makeBestMove();
            /**
             * Una vez que ha encontrado el mejor movimiento posible, haciendo uso de minimax
             * entonces vamos a acceder a X y Y y y marcamos el nombre del jugador en el tablero
             */
            board[minimaxAlgorithm.bestMove.getX()][minimaxAlgorithm.bestMove.getY()] = currentPlayer;
            // Tambien debemos marcar la selección del sistema en la interfaz del usuario
            markComputerDecition(minimaxAlgorithm.bestMove.getX(), minimaxAlgorithm.bestMove.getY());
            // Ya que su turno termino, realizamos a cambiar para que juegue el humano
            changePlayer();
        }
        
    }
    
    /**
     * Cuando el juego comienza y el primer turno es del sistema, el primero movimiento lo realiza de manera aleatoria porque asi queria hacerlo
     */
    private void playRandomIA(){
        Random rand = new Random();
        // obtenemos la posicion aleatoria para el primer juego
        int x = rand.nextInt(3);
        int y = rand.nextInt(3);
        // Asignamos la jugada a nuestro tablero
        board[x][y] = currentPlayer;
        //System.out.println("Los valores obtenidos son: " +x +" y " +y);
        // Marcamos la posición en el tablero
        markComputerDecition(x, y);
    }
    
    /**
     * Cuando el sistema ha realizado algun movimiento, tambien debe de mostrarlo en el frame para que el usuario lo pueda visualizar
     * En esta función, dependiendo de la condición, es donde editamos los botones del frame
     * @param x
     * @param y 
     */
    public void markComputerDecition(int x, int y){
        if(x == 0){
            if(y == 0){
                pos00Button.setEnabled(false);
                pos00Button.setText(currentPlayer);
            }
            else if(y==1){
                pos01Button.setEnabled(false);
                pos01Button.setText(currentPlayer); 
            }
            else{
                pos02Button.setEnabled(false);
                pos02Button.setText(currentPlayer);
            }
        } else if(x == 1){
            if(y == 0){
                pos10Button.setEnabled(false);
                pos10Button.setText(currentPlayer);
            }
            else if(y==1){
                pos11Button.setEnabled(false);
                pos11Button.setText(currentPlayer); 
            }
            else{
                pos12Button.setEnabled(false);
                pos12Button.setText(currentPlayer);
            }
        } else {
            if(y == 0){
                pos20Button.setEnabled(false);
                pos20Button.setText(currentPlayer);
            }
            else if(y==1){
                pos21Button.setEnabled(false);
                pos21Button.setText(currentPlayer); 
            }
            else{
                pos22Button.setEnabled(false);
                pos22Button.setText(currentPlayer);
            }
        }
        
    }
    
    /**
     * Funciones solo para consola
     */
    // imprime el tablero cada que el jugador realiza un movimiento
    private void printBoard(){
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%% Print board %%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("Movimiento: " +(playerMoves+1));
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                System.out.print("["+board[i][j]+"]");
            }
            System.out.println();
        }
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%% FIN Print board %%%%%%%%%%%%%%%%%%%%");
    }
    
    /**
     * FUNCIONES CREADAS POR NETBEANS AL MOMENTO DE TRABAJAR CON JFRAME
     */
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pos00Button = new javax.swing.JButton();
        pos02Button = new javax.swing.JButton();
        pos01Button = new javax.swing.JButton();
        pos12Button = new javax.swing.JButton();
        pos11Button = new javax.swing.JButton();
        pos10Button = new javax.swing.JButton();
        pos21Button = new javax.swing.JButton();
        pos22Button = new javax.swing.JButton();
        pos20Button = new javax.swing.JButton();
        scorePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        xScoreLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        oScoreLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        fnewGameButton = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        aboutButton = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        pos00Button.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        pos00Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pos00ButtonActionPerformed(evt);
            }
        });

        pos02Button.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        pos02Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pos02ButtonActionPerformed(evt);
            }
        });

        pos01Button.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        pos01Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pos01ButtonActionPerformed(evt);
            }
        });

        pos12Button.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        pos12Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pos12ButtonActionPerformed(evt);
            }
        });

        pos11Button.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        pos11Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pos11ButtonActionPerformed(evt);
            }
        });

        pos10Button.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        pos10Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pos10ButtonActionPerformed(evt);
            }
        });

        pos21Button.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        pos21Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pos21ButtonActionPerformed(evt);
            }
        });

        pos22Button.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        pos22Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pos22ButtonActionPerformed(evt);
            }
        });

        pos20Button.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        pos20Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pos20ButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setText("Score");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 135, 156));
        jLabel2.setText("X:");

        xScoreLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        xScoreLabel.setForeground(new java.awt.Color(0, 135, 156));
        xScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xScoreLabel.setText("0");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(186, 51, 5));
        jLabel4.setText("O:");

        oScoreLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        oScoreLabel.setForeground(new java.awt.Color(186, 51, 5));
        oScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        oScoreLabel.setText("0");

        javax.swing.GroupLayout scorePanelLayout = new javax.swing.GroupLayout(scorePanel);
        scorePanel.setLayout(scorePanelLayout);
        scorePanelLayout.setHorizontalGroup(
            scorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scorePanelLayout.createSequentialGroup()
                .addGroup(scorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(scorePanelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1))
                    .addGroup(scorePanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(scorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(scorePanelLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(oScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(scorePanelLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        scorePanelLayout.setVerticalGroup(
            scorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scorePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(scorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xScoreLabel)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(scorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(oScoreLabel))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pos20Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pos21Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pos22Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pos00Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pos01Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pos02Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pos10Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pos11Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pos12Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scorePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pos00Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos02Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos01Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pos10Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos12Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos11Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pos20Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos22Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos21Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(scorePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Archivo");

        fnewGameButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        fnewGameButton.setText("Nuevo juego");
        fnewGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnewGameButtonActionPerformed(evt);
            }
        });
        jMenu1.add(fnewGameButton);
        jMenu1.add(jSeparator1);

        jMenuItem3.setText("Salir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");

        aboutButton.setText("Acerca de");
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });
        jMenu2.add(aboutButton);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fnewGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnewGameButtonActionPerformed
        // El usuario quiere empezar un nuevo juego
        this.setVisible(false);
        JFrame startFrame = new StartFrame();
        startFrame.setVisible(true);
    }//GEN-LAST:event_fnewGameButtonActionPerformed

    private void pos00ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pos00ButtonActionPerformed
        /**
         * Boton:
         * [*][ ][ ]
         * [ ][ ][ ]
         * [ ][ ][ ]
         */
        // Desactivamos el boton
        pos00Button.setEnabled(false);
        // Escribimos el nombre del jugador en el boton
        pos00Button.setText(currentPlayer);
        // Debemos marcar en nuestro tablero la selección del usuario
        board[0][0] = currentPlayer;
        // Una vez que el usuario ha jugado, entonces cambiamos de jugador
        changePlayer();
    }//GEN-LAST:event_pos00ButtonActionPerformed

    private void pos02ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pos02ButtonActionPerformed
        /**
         * Boton:
         * [ ][ ][*]
         * [ ][ ][ ]
         * [ ][ ][ ]
         */
        // Desactivamos el boton
        pos02Button.setEnabled(false);
        // Escribimos el nombre del jugador en el boton
        pos02Button.setText(currentPlayer);
        // Debemos marcar en nuestro tablero la selección del usuario
        board[0][2] = currentPlayer;
        // Una vez que el usuario ha jugado, entonces cambiamos de jugador
        changePlayer();
        
    }//GEN-LAST:event_pos02ButtonActionPerformed

    private void pos01ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pos01ButtonActionPerformed
        /**
         * Boton:
         * [ ][*][ ]
         * [ ][ ][ ]
         * [ ][ ][ ]
         */
        // Desactivamos el boton
        pos01Button.setEnabled(false);
        // Escribimos el nombre del jugador en el boton
        pos01Button.setText(currentPlayer);
        // Debemos marcar en nuestro tablero la selección del usuario
        board[0][1] = currentPlayer;
        // Una vez que el usuario ha jugado, entonces cambiamos de jugador
        changePlayer();
    }//GEN-LAST:event_pos01ButtonActionPerformed

    private void pos12ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pos12ButtonActionPerformed
        /**
         * Boton:
         * [ ][ ][ ]
         * [ ][ ][*]
         * [ ][ ][ ]
         */
        // Desactivamos el boton
        pos12Button.setEnabled(false);
        // Escribimos el nombre del jugador en el boton
        pos12Button.setText(currentPlayer);
        // Debemos marcar en nuestro tablero la selección del usuario
        board[1][2] = currentPlayer;
        // Una vez que el usuario ha jugado, entonces cambiamos de jugador
        changePlayer();
    }//GEN-LAST:event_pos12ButtonActionPerformed

    private void pos11ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pos11ButtonActionPerformed
        /**
         * Boton:
         * [ ][ ][ ]
         * [ ][1][ ]
         * [ ][ ][ ]
         */
        // Desactivamos el boton
        pos11Button.setEnabled(false);
        // Escribimos el nombre del jugador en el boton
        pos11Button.setText(currentPlayer);
        // Debemos marcar en nuestro tablero la selección del usuario
        board[1][1] = currentPlayer;
        // Una vez que el usuario ha jugado, entonces cambiamos de jugador
        changePlayer();
    }//GEN-LAST:event_pos11ButtonActionPerformed

    private void pos10ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pos10ButtonActionPerformed
        /**
         * Boton:
         * [ ][ ][ ]
         * [*][ ][ ]
         * [ ][ ][ ]
         */
        // Desactivamos el boton
        pos10Button.setEnabled(false);
        // Escribimos el nombre del jugador en el boton
        pos10Button.setText(currentPlayer);
        // Debemos marcar en nuestro tablero la selección del usuario
        board[1][0] = currentPlayer;
        // Una vez que el usuario ha jugado, entonces cambiamos de jugador
        changePlayer();
    }//GEN-LAST:event_pos10ButtonActionPerformed

    private void pos21ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pos21ButtonActionPerformed
        // TODO add your handling code here:
        /**
         * Boton:
         * [ ][ ][ ]
         * [ ][ ][ ]
         * [ ][*][ ]
         */
        // Desactivamos el boton
        pos21Button.setEnabled(false);
        // Escribimos el nombre del jugador en el boton
        pos21Button.setText(currentPlayer);
        // Debemos marcar en nuestro tablero la selección del usuario
        board[2][1] = currentPlayer;
        // Una vez que el usuario ha jugado, entonces cambiamos de jugador
        changePlayer();
    }//GEN-LAST:event_pos21ButtonActionPerformed

    private void pos22ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pos22ButtonActionPerformed
        /**
         * Boton:
         * [ ][ ][ ]
         * [ ][ ][ ]
         * [ ][ ][*]
         */
        // Desactivamos el boton
        pos22Button.setEnabled(false);
        // Escribimos el nombre del jugador en el boton
        pos22Button.setText(currentPlayer);
        // Debemos marcar en nuestro tablero la selección del usuario
        board[2][2] = currentPlayer;
        // Una vez que el usuario ha jugado, entonces cambiamos de jugador
        changePlayer();
    }//GEN-LAST:event_pos22ButtonActionPerformed

    private void pos20ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pos20ButtonActionPerformed
        /**
         * Boton:
         * [ ][ ][ ]
         * [ ][ ][ ]
         * [*][ ][ ]
         */
        // Desactivamos el boton
        pos20Button.setEnabled(false);
        // Escribimos el nombre del jugador en el boton
        pos20Button.setText(currentPlayer);
        // Debemos marcar en nuestro tablero la selección del usuario
        board[2][0] = currentPlayer;
        // Una vez que el usuario ha jugado, entonces cambiamos de jugador
        changePlayer();
    }//GEN-LAST:event_pos20ButtonActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // Si el usuario quiere quitar el juego
        System.exit(0);
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        ImageIcon icon = new ImageIcon("src/logo.png");
        setIconImage(icon.getImage());
    }//GEN-LAST:event_formWindowActivated

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        // El usuario quiere ver información sobre este proyecto
        JFrame aboutFrame = new AboutFrame();
        aboutFrame.setVisible(true);
    }//GEN-LAST:event_aboutButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutButton;
    private javax.swing.JMenuItem fnewGameButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel oScoreLabel;
    private javax.swing.JButton pos00Button;
    private javax.swing.JButton pos01Button;
    private javax.swing.JButton pos02Button;
    private javax.swing.JButton pos10Button;
    private javax.swing.JButton pos11Button;
    private javax.swing.JButton pos12Button;
    private javax.swing.JButton pos20Button;
    private javax.swing.JButton pos21Button;
    private javax.swing.JButton pos22Button;
    private javax.swing.JPanel scorePanel;
    private javax.swing.JLabel xScoreLabel;
    // End of variables declaration//GEN-END:variables
}
