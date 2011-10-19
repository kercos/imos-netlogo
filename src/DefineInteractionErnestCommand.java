
import imos.IImos;

import org.nlogo.api.*;

import ernest.IErnest;

public class DefineInteractionErnestCommand extends DefaultCommand
{
	
    // take two strings and a numbers as input.
    public Syntax getSyntax() 
    {
        return Syntax.commandSyntax(new int[] {Syntax.TYPE_STRING, Syntax.TYPE_STRING, Syntax.TYPE_NUMBER});
    }
    
    /**
     * Record the interaction if it does not yet exist. 
     * @param args[0] The action (string).
     * @param args[1] The stimuli (string).
     * @param args[2] The satisfaction (int).
     */
	public void perform(Argument[] arg0, Context context)
			throws ExtensionException, LogoException 
	{
    	String action;
    	String stimuli;
    	int satisfaction;
    	
        try
        {
        	action = arg0[0].getString();
        	stimuli = arg0[1].getString();
        	satisfaction = arg0[2].getIntValue();
        }
        catch( LogoException e )
        {   throw new ExtensionException( e.getMessage() ) ;}
        
        // record this interaction
        IErnest ernest = ImosExtension.m_ernests.get(context.getAgent());
        ernest.addInteraction(action, stimuli, satisfaction);
        
	}

}
