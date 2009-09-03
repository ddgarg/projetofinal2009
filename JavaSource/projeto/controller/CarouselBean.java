package projeto.controller;


import java.util.ArrayList;
import java.util.List;

import org.primefaces.optimus.config.Scope;
import org.primefaces.optimus.config.annotations.Controller;

import projeto.model.Player;

@Controller(name="carouselBean", scope=Scope.REQUEST)
public class CarouselBean {

        private List<Player> players;
        
        public CarouselBean() {
                players = new ArrayList<Player>();
                players.add(new Player("DK1", "../images/g1.jpg"));
                players.add(new Player("DH2", "../images/g2.jpg"));
                players.add(new Player("DK3", "../images/g3.JPG"));
                players.add(new Player("DK4", "../images/g4.jpg"));
                players.add(new Player("DK5", "../images/g5.jpg"));
                players.add(new Player("DK6", "../images/g6.jpg"));
        }

        public List<Player> getPlayers() {
                return players;
        }

        public void setPlayers(List<Player> players) {
                this.players = players;
        } 
}