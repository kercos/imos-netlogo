
import org.nlogo.api.*;

import imos.*;

public class InitCommand extends DefaultCommand
{

    // take two numbers as input.
    public Syntax getSyntax() 
    {
        return Syntax.commandSyntax(new int[] {Syntax.TYPE_NUMBER, Syntax.TYPE_NUMBER});
    }
    
    /**
     * Initialize the Intrinsic Motivation System. 
     * @param args[0] The regularity sensibility threshold.
     * @param args[1] The maximum length of schemas.
     */
    public void perform(Argument args[], Context context)
        throws ExtensionException
    {
    	int regThreshold;
    	int maxSchemaLength;
        try
        {
        	regThreshold = args[0].getIntValue();
        	maxSchemaLength = args[1].getIntValue();
        }
        catch( LogoException e )
        {   throw new ExtensionException( e.getMessage() ) ;}

        // Initialize the Intrinsic Motivation System.
        IImos imos =  new Imos(regThreshold,maxSchemaLength);
    	ImosExtension.m_imoss.put(context.getAgent(), imos);
    }


}
