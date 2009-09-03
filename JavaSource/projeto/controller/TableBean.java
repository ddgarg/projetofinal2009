package projeto.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.optimus.config.Scope;
import org.primefaces.optimus.config.annotations.Controller;

import projeto.model.Car;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;



@Controller(name="tableBean", scope=Scope.REQUEST)
public class TableBean {

        private List<Car> cars;
        
        public TableBean() {
                cars = new ArrayList<Car>();
                for(int i = 0 ; i < 100 ; i++) {
                        cars.add(new Car("Model_" + i, getRandomYear(), "Brand_" + i, "Color_" + i));
                }
        }

        public List<Car> getCars() {
                return cars;
        }

        public void setCars(List<Car> cars) {
                this.cars = cars;
        }
        
        private int getRandomYear() {
                return (int)(Math.random()*50 + 1960);
        }
        
        public void postProcessXLS(Object document) {
                HSSFWorkbook wb = (HSSFWorkbook) document;
                HSSFSheet sheet = wb.getSheetAt(0);
                HSSFRow header = sheet.getRow(0);
                
                HSSFCellStyle cellStyle = wb.createCellStyle();  
                cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
                cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                
                for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
                        HSSFCell cell = header.getCell((short) i);
                        
                        cell.setCellStyle(cellStyle);
                }
        }
        
        public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
                Document pdf = (Document) document;
                ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String logo = servletContext.getRealPath("") + File.separator + "images" + File.separator + "prime_logo.png";
                
                pdf.add(Image.getInstance(logo));
        }
}