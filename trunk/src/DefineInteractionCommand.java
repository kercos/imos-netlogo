
import org.nlogo.api.*;

public class DefineInteractionCommand extends DefaultCommand
{
	
    // take three numbers as input, report a string
    public Syntax getSyntax() 
    {
        return Syntax.reporterSyntax(new int[] {Syntax.TYPE_STRING, Syntax.TYPE_STRING, Syntax.TYPE_NUMBER}, Syntax.TYPE_VOID);
    }
    
    /**
     * Record the interaction if it does not yet exist. 
     * @param args[0] The action (string).
     * @param args[1] The stimuli (string).
     * @param args[2] The status (boolean).
     * @param args[3] The satisfaction (int).
     */
	public void perform(Argument[] arg0, Context arg1)
			throws ExtensionException, LogoException 
	{
    	String action;
    	String stimuli;
    	Boolean status;
    	int satisfaction;
    	
        try
        {
        	action = arg0[0].getString();
        	stimuli = arg0[1].getString();
        	status = arg0[2].getBoolean();
        	satisfaction = arg0[3].getIntValue();
        }
        catch( LogoException e )
        {   throw new ExtensionException( e.getMessage() ) ;}
        
        // record this interaction
        ImosExtension.m_imos.addInteraction(action, stimuli, satisfaction);
		
	}

}
