package projeto.model;


import java.util.Date;

public class Player {

        private String name;

        private String photo;
        
        private String position;
        
        private String nationality;
        
        private String height;
        
        private String weight;
        
        private Date birth;
        
        public String getHeight() {
                return height;
        }

        public void setHeight(String height) {
                this.height = height;
        }

        public String getWeight() {
                return weight;
        }

        public void setWeight(String weight) {
                this.weight = weight;
        }

        public Date getBirth() {
                return birth;
        }

        public void setBirth(Date birth) {
                this.birth = birth;
        }

        public Player() {
                
        }

        public Player(String name, String photo) {
                this.name = name;
                this.photo = photo;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getPhoto() {
                return photo;
        }

        public void setPhoto(String photo) {
                this.photo = photo;
        }
        
        public String getPosition() {
                return position;
        }

        public void setPosition(String position) {
                this.position = position;
        }

        public String getNationality() {
                return nationality;
        }

        public void setNationality(String nationality) {
                this.nationality = nationality;
        }

}