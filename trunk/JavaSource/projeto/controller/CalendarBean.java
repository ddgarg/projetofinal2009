package projeto.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.primefaces.optimus.config.Scope;
import org.primefaces.optimus.config.annotations.Controller;

@Controller(name="calendarBean", scope=Scope.REQUEST)
@org.springframework.stereotype.Controller("calendarBean")
@org.springframework.context.annotation.Scope("request")
public class CalendarBean {

        private Date date;
        
        private Date[] dates;

        public Date[] getDates() {
                return dates;
        }

        public void setDates(Date[] dates) {
                this.dates = dates;
        }

        public Date getDate() {
                return date;
        }

        public String getDateFormat() {
        	SimpleDateFormat df = new SimpleDateFormat("hh/MM/yyyy");
        	return df.format(date);
        }
        
        public void setDate(Date date) {
                this.date = date;
        }
        
        public List<Date> getSelectedDates() {
                if(dates != null)
                        return Arrays.asList(dates);
                else
                        return new ArrayList<Date>();
        }
}