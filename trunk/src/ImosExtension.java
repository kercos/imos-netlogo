
import java.util.Hashtable;
import java.util.Map;
import imos.IImos;
import org.nlogo.api.*;
import ernest.IErnest;

public class ImosExtension extends DefaultClassManager 
{
	/** The table of the Intrinsic Motivation Systems (1 per agent). */
	public static Map<Agent,IImos> m_imoss = new Hashtable<Agent,IImos>();
	
	/** The table of the Intrinsic Motivation Systems (1 per agent). */
	public static Map<Agent,IErnest> m_ernests = new Hashtable<Agent,IErnest>();

	public void load(PrimitiveManager primitiveManager) 
    {
    	// Initialize the reporters.
        primitiveManager.addPrimitive("init", new InitCommand());        
        primitiveManager.addPrimitive("interaction", new DefineInteractionCommand());
        primitiveManager.addPrimitive("step", new StepReporter());

        primitiveManager.addPrimitive("initE", new InitErnestCommand());        
        primitiveManager.addPrimitive("interactionE", new DefineInteractionErnestCommand());
        primitiveManager.addPrimitive("stepE", new StepErnestReporter());
       
        //primitiveManager.addPrimitive("step82", new StepErnest82Reporter());
    }    
}