import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nume jucator: ");
        String player1Name = scanner.nextLine();
        Player player = new Player(player1Name);

        //construim jocul
        Planes planes = new Planes(player);
        planes.playGame();

        boolean newGame = true;
        while (newGame) {
            System.out.println("Doriti sa incepeti un nou joc?");
            System.out.println("Pentru DA apasati tasta y iar pentru nu apasati orice alta tasta");
            String d = scanner.nextLine();
            String str = d.toLowerCase();

            if (str.equals("y")) {
                planes.playGame();
            } else {
                System.out.println("Jocul s-a incheiat! ");
                System.out.println("Va multumim!");
                newGame = false;
            }
        }

    }
}
