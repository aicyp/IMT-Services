package rest.jaxb;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import rest.ImplemResultat;
import rest.ImplemSession;

@Provider
public class FournisseurTraduction implements ContextResolver<JAXBContext> {
    private JAXBContext context = null;
 
    public FournisseurTraduction(){
    	System.out.println("Initialisation d'un fournisseur de type " + this.getClass());
    }
    
    public JAXBContext getContext(Class<?> type) {
    	System.out.println("Récupération du contexte JAXB pour : " + type);

        if (context == null) {
            try {
                context = JAXBContext.newInstance(ImplemSession.class, ImplemResultat.class);
            } catch (JAXBException e) {
                // log warning/error; null will be returned which indicates that this
                // provider won't/can't be used.
            }
        }
        // System.out.println("Contexte JAXB: " + context);
        return context;
    }
}