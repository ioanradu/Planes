import java.util.Scanner;

public class Planes {

    public static final char SYMBOL_X = 'X';
    public static final char SYMBOL_AVION = '@';
    public static final char SYMBOL_AVION_ATINS = '!';
    public static final int GAME_SIZE = 10;

    char[][] game = new char[GAME_SIZE][GAME_SIZE];
    Player player;

    public Planes(Player player) {
        this.player = player;
    }

    public void showGame() {
        System.out.print(" ");
        for (int k = 0; k < GAME_SIZE; k++) {
            System.out.print(" " + k);
        }

        System.out.print("\n");
        for (int i = 0; i < GAME_SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < GAME_SIZE; j++) {
                System.out.print(game[i][j] + " ");
            }
            System.out.println();
        }

    }

    public void initBoard() {
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                game[i][j] = '.';
            }
        }
    }

    public Plane planeConstruction() {
        Move[] avion = {
                // construim mai intai capul avionului pentru a fi mai usor cand testam daca avionul a fost lovit
                new Move(3, 6),
                // apoi construim celelalte parti al avionului
                new Move(4, 4),
                new Move(4, 5),
                new Move(4, 6),
                new Move(4, 7),
                new Move(4, 8),
                new Move(5, 6),
                new Move(6, 5),
                new Move(6, 6),
                new Move(6, 7)
        };
        // creem un obiect care contine coordonatele capului avionului
        Move head = new Move(3, 6);
        // creem un alt obiect care contine coocrdonatele avionului si coordonatele capului avionului
        Plane plane = new Plane(avion, head);
        // returnam obiectul plane
        return plane;

    }

    public Move readMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introducem mutarea: ");
        String myMove = scanner.nextLine();
        String[] myString = myMove.split("-");
        int myLine = Integer.valueOf(myString[0]);
        int myCol = Integer.valueOf(myString[1]);

        Move move = new Move(myLine, myCol);
        return move; // returneaza myLine si myCol
    }

    public boolean validateInput(Move move) {
        boolean isValidate = true;
        if (move.line >= GAME_SIZE || move.col >= GAME_SIZE) {
            isValidate = false;
        }
        return isValidate;
    }

    public boolean validateMove(Move move) {
        boolean isValidate = true;
        if (game[move.line][move.col] == SYMBOL_X || game[move.line][move.col] == SYMBOL_AVION_ATINS) {
            isValidate = false;
        }
        return isValidate;
    }

    public void makeMove(Move move, char symbol) {
        game[move.line][move.col] = symbol;

    }

    public void showPlane(Plane plane, char symbol) {
        for (int i = 0; i < plane.avion.length; i++) {
            makeMove(plane.avion[i], symbol);
        }
        showGame();
    }

    public void showDamagedPlane(Move move, char symbol) {
        makeMove(move, symbol);
        showGame();
    }

    public void playGame() {
        System.out.println("Incepe jocul");
        initBoard();
        showGame();
        Plane plane = planeConstruction();

        char symbol = SYMBOL_X;
        int index = 0;

        boolean isWin = false; // la inceput nu avem un castigator
        while (isWin == false) {

            // citesc mutarea
            Move move = readMove();

            if (!validateInput(move)) {
                System.out.print("Reluam mutarea. ");
                System.out.println("Introdu valori mai mici decat " + GAME_SIZE + " !");
                showGame();

            } else if (!validateMove(move)) {
                System.out.println("Pozitia este deja ocupata, introdu o alta pozitie!");
                showGame();

            } else {
                boolean damaged = false;

                int i = 0;
                while (i < plane.avion.length && isWin == false){
                    if (plane.avion[i].returnLine() == move.line && plane.avion[i].returnColumn() == move.col) {
                        if ((move.returnLine() == plane.head.returnLine() && move.returnColumn() == plane.head.returnColumn())) {
                            System.out.println("Avionul a fost distrus!");
                            showPlane(plane, SYMBOL_AVION);
                            damaged = true;
                            isWin = true;

                        } else {
                            System.out.println("Avionul a fost avariat");
                            index++;
                            showDamagedPlane(plane.avion[i], SYMBOL_AVION_ATINS);
                            damaged = true;

                            if (index == 9) {
                                System.out.println("Avionul a fost distrus");
                                showPlane(plane, SYMBOL_AVION);
                                isWin = true;
                            }
                        }
                    }
                    i++;
                }
                if (!damaged) {
                    System.out.println("Mai incearca !");
                    makeMove(move, SYMBOL_X);
                    showGame();
                }

              }

            }
        }
    }



