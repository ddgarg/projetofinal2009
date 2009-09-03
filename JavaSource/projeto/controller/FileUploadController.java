package projeto.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.optimus.config.Scope;
import org.primefaces.optimus.config.annotations.Controller;
import org.primefaces.ui.event.fileupload.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller(name="fileUploadController", scope=Scope.REQUEST)
public class FileUploadController {
        
        private Logger logger = LoggerFactory.getLogger(FileUploadController.class);

        public void handleFileUpload(FileUploadEvent event) {
                logger.info("Uploaded: {}", event.getFile().getFileName());

                FacesMessage msg = new FacesMessage("Successfully uploaded");
                FacesContext.getCurrentInstance().addMessage(null, msg);
        }
}