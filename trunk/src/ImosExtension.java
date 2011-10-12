
import imos.IImos;
import imos.Imos;

import org.nlogo.api.*;

public class ImosExtension extends DefaultClassManager 
{
	/** The intrinsic motivation system. */
	public static IImos m_imos;

    public void load(PrimitiveManager primitiveManager) 
    {
    	// Initialize the reporters.
        primitiveManager.addPrimitive("init", new InitCommand());        
        primitiveManager.addPrimitive("interaction", new DefineInteractionCommand());
        primitiveManager.addPrimitive("step", new StepReporter());
        //primitiveManager.addPrimitive("step82", new StepErnest82Reporter());
    }    
}