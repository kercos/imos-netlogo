
import org.nlogo.api.*;

import ernest.Ernest;
import ernest.IErnest;
import ernest.Visual100SensorymotorSystem;

public class InitErnestCommand extends DefaultCommand
{

    // take two numbers as input.
    public Syntax getSyntax() 
    {
        return Syntax.commandSyntax(new int[] {Syntax.TYPE_NUMBER, Syntax.TYPE_NUMBER});
    }
    
    /**
     * Initialize the Ernest. 
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

        // Initialize the Ernest.
        
		IErnest ernest = new Ernest();
		ernest.setParameters(regThreshold, maxSchemaLength);
		Visual100SensorymotorSystem sms = new Visual100SensorymotorSystem();
		ernest.setSensorymotorSystem(sms);

		//ImosExtension.m_ernests.put(context.getAgent(), ernest);
		ImosExtension.m_ernests.put(context.getAgent(), ernest);
    }


}
