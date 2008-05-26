package hr.fer.zemris.java.tecaj_8.vjezba.controller;

import hr.fer.zemris.java.tecaj_8.vjezba.model.AbstractModel;
import hr.fer.zemris.java.tecaj_8.vjezba.view.DrawingView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Razred koji pruza osnovnu funkcionalnost za svaki kontroler. Omogucuje prosljedivanje
 * promjena u modelu raznim pogledima. Modeli i pogledi se moraju prijaviti u kontroler.
 * 
 * @author Hrvoje
 *
 */
public abstract class AbstractController implements PropertyChangeListener {

    protected ArrayList<DrawingView> registeredViews;
    protected ArrayList<AbstractModel> registeredModels;

    /**
     * Instancira novi kontroler
     */
    public AbstractController() {
        registeredViews = new ArrayList<DrawingView>();
        registeredModels = new ArrayList<AbstractModel>();
    }

	/**
	 * Veze novi model uz kontroler. 
	 * @param model Model koji se dodaje
	 */
    public void addModel(AbstractModel model) {
        registeredModels.add(model);
        model.addPropertyChangeListener(this);
    }
    
    /**
     * Veze novi pogled uz kontroler
     * @param view Pogled koji se veze uz kontroler
     */
    public void addView(DrawingView view) {
        registeredViews.add(view);
    }
    
    /**
     * Metoda koja pogledima prosljedjuje promjene u modelu
     */
    public void propertyChange(PropertyChangeEvent evt) {

        for (DrawingView view: registeredViews) {
            view.modelPropertyChange(evt);
        }
    }

    /**
     * Pomocna metoda koja omogucuje kontrolerima da pozivaju metode u svim modelima
     * prijavljenim na njih. Ako neki model nema tu metodu, hvata se NoSuchMethodException
     * i ignorira se, da bi se for petlja nastavila i metoda mogla pozvati u drugim modelima 
     * 
     * @param methodName ime metode
     * @param args argumenti metode
     */
    @SuppressWarnings("unchecked")
	protected void callModelMethod(String methodName, Object... args) {
    	Class[] argsClass;
    	if (args == null)
    		argsClass = new Class[0];
    	else
    		argsClass = new Class[args.length];
    	
    	for (int i = 0; i < argsClass.length; i++)
    		argsClass[i] = args[i].getClass();
    	for (AbstractModel model: registeredModels) {
            try {             
                Method method = model.getClass().
                    getMethod(methodName, argsClass);
                method.invoke(model, args);
                
            } catch (Exception ignorable) {}
        }
    }
}