package projeto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.optimus.config.Scope;
import org.primefaces.optimus.config.annotations.Controller;
import org.primefaces.ui.event.chart.ItemSelectEvent;

import projeto.model.Birth;
import projeto.model.Sale;
import projeto.model.Vote;

@Controller(name="chartBean", scope=Scope.REQUEST)
public class ChartBean implements Serializable {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private List<Sale> sales;
        
        private List<Birth> births;
        
        private List<Vote> votes;
        
        private String message;

        public ChartBean() {
                sales = new ArrayList<Sale>();
                sales.add(new Sale("Brand 1", 540));
                sales.add(new Sale("Brand 2", 325));
                sales.add(new Sale("Brand 3", 702));
                sales.add(new Sale("Brand 4", 421));
                
                births = new ArrayList<Birth>();
                births.add(new Birth(2004, 120, 52));
                births.add(new Birth(2005, 100, 60));
                births.add(new Birth(2006, 44, 110));
                births.add(new Birth(2007, 150, 135));
                births.add(new Birth(2008, 125, 120));
                
                votes = new ArrayList<Vote>();
                votes.add(new Vote("Candidate 1", 100));
                votes.add(new Vote("Candidate 2", 100));
        }

        public List<Sale> getSales() {
                return sales;
        }
        
        public void setSales(List<Sale> sales) {
			this.sales = sales;
		}

		public List<Birth> getBirths() {
                return births;
        }
        
        public List<Vote> getVotes() {
                int random1 = (int)(Math.random() * 1000);
                int random2 = (int)(Math.random() * 1000);
        
                votes.get(0).add(random1);
                votes.get(1).add(random2);
                
                return votes;
        }
        
        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public void itemSelect(ItemSelectEvent event) {
                message = "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex();
        }
}