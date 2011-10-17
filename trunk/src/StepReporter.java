
import org.nlogo.api.*;
import imos.*;

public class StepReporter extends DefaultReporter
{
	
    private String m_action = ">";
	
    // take a string, a boolean, and a number as input. Returns a list.
    public Syntax getSyntax() 
    {
        return Syntax.reporterSyntax(new int[] {Syntax.TYPE_STRING, Syntax.TYPE_NUMBER}, Syntax.TYPE_LIST);
    }
    
    /**
     * Record the actually enacted interaction and return the intended action 
     * @param args[0] The stimuli.
     * @param args[1] The enaction strue=succeed, false=fail.
     * @param args[2] The satisfaction.
     * @return The intended action (and the enacted interaction for debug).
     */
    public Object report(Argument args[], Context context)
        throws ExtensionException
    {
        String stimuli;
        int satisfaction;

        // Retrieve the enacted interaction.
        try
        {
            stimuli = args[0].getString();
            satisfaction = args[1].getIntValue();
        }
        catch( LogoException e )
        {   throw new ExtensionException( e.getMessage() ) ;}
        
        // Get the corresponding interaction and construct it if it does not yet exist. 
        IImos imos = ImosExtension.m_imoss.get(context.getAgent());
    	IAct a = imos.addInteraction(m_action, stimuli, satisfaction);
        
    	// Get the next intention
        m_action = imos.step(a).getSchema().getLabel();
        
        LogoList list = new LogoList();   
        
        list.add(m_action);
        list.add(a.getLabel()); // The act label is only for debug.
        
        return list;
    }
}