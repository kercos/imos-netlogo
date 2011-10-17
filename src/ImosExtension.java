
import java.util.Hashtable;
import java.util.Map;

import imos.IImos;

import org.nlogo.api.*;

public class ImosExtension extends DefaultClassManager 
{
	/** The table of the Intrinsic Motivation Systems instantiated for each agent. */
	public static Map<Agent,IImos> m_imoss = new Hashtable<Agent,IImos>();
	
    public void load(PrimitiveManager primitiveManager) 
    {
    	// Initialize the reporters.
        primitiveManager.addPrimitive("init", new InitCommand());        
        primitiveManager.addPrimitive("interaction", new DefineInteractionCommand());
        primitiveManager.addPrimitive("step", new StepReporter());

        //primitiveManager.addPrimitive("step82", new StepErnest82Reporter());
    }    
}