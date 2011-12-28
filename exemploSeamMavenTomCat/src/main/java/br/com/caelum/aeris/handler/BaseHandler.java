package br.com.caelum.aeris.handler;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.core.Conversation;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

public abstract class BaseHandler implements Serializable {

	private static final long serialVersionUID = 999800330246578359L;

	@Logger
	Log log;
	
	@In
	EntityManager entityManager;
	
	@In
	Events events;
	
	@In
	FacesMessages facesMessages;
	
	@In
	Conversation conversation;
	
}
