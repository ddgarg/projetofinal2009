package projeto.model;

public class Birth {

    private int year;
    
    private int boys;
    
    private int girls;
    
    public Birth() {
            
    }
    
    public Birth(int year, int boys, int girls) {
            this.year = year;
            this.boys = boys;
            this.girls = girls;
    }

    public int getYear() {
            return year;
    }

    public void setYear(int year) {
            this.year = year;
    }

    public int getBoys() {
            return boys;
    }

    public void setBoys(int boys) {
            this.boys = boys;
    }

    public int getGirls() {
            return girls;
    }

    public void setGirls(int girls) {
            this.girls = girls;
    }
}