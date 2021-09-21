public class Main {
    Game game;
    Menu menu;
    public Main(){
        game = new Game();
        menu = new Menu();
        game.add(menu);
        while(!menu.Play()){
            game.setVisible(true);
        }
        game.remove(menu);
        int i=10;
        while(i<=10){
            game.setLevel(i++);
        }
    }
}
