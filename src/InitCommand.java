
import org.nlogo.api.*;

import imos.*;

public class InitCommand extends DefaultCommand
{

    // take three numbers as input, report a string
    public Syntax getSyntax() 
    {
        return Syntax.reporterSyntax(new int[] {Syntax.TYPE_NUMBER, Syntax.TYPE_NUMBER}, Syntax.TYPE_VOID);
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
        ImosExtension.m_imos = new Imos(regThreshold,maxSchemaLength);

//        ImosExtension.m_imos.addInteraction(">", "", true,  -10); // Move forward
//        ImosExtension.m_imos.addInteraction(">", "", false, -80); // Bump
//		
//        ImosExtension.m_imos.addInteraction("^", "", true,    0); // Left toward adjacent empty
//        ImosExtension.m_imos.addInteraction("^", "", false, -50); // Left toward adjacent wall
//
//        ImosExtension.m_imos.addInteraction("v", "", true,    0); // Right toward adjacent empty
//        ImosExtension.m_imos.addInteraction("v", "", false, -50); // Right toward adjacent wall
        
    }


}
