
import org.nlogo.api.*;

import ernest.Ernest;
import ernest.IErnest;
import imos.*;

public class StepErnestReporter extends DefaultReporter
{
	
    private String m_action = ">";
	
    // take two lists as input. Returns a string.
    public Syntax getSyntax() 
    {
        return Syntax.reporterSyntax(new int[] 
               {Syntax.TYPE_LIST,   // Vision [0,11]
        		Syntax.TYPE_LIST,   // Touch  []
        		Syntax.TYPE_NUMBER, // Taste
        		Syntax.TYPE_NUMBER, // Kinematic
        		}, Syntax.TYPE_STRING);
    }
    
    /**
     * Run Ernest one step 
     * @param args[0] The vision.
     * @param args[1] The touch.
     * @return The intended action (and the enacted interaction for debug).
     */
    public Object report(Argument args[], Context context)
        throws ExtensionException
    {
    	LogoList vision;
    	LogoList touch;
        int taste;
        int kinematic;

        // Retrieve the enacted interaction.
        try
        {
        	vision = args[0].getList();
        	touch = args[1].getList();
        	taste = args[2].getIntValue();
        	kinematic = args[3].getIntValue();
        }
        catch( LogoException e )
        {   throw new ExtensionException( e.getMessage() ) ;}
        
        
		// See the environment
		int [][] matrix = new int[Ernest.RESOLUTION_RETINA][8 + 1 + 3];
		
		String toto = vision.get(0).toString();
		
		for (int i = 0; i < Ernest.RESOLUTION_RETINA; i++)
		{
			matrix[i][0] = 0;
			matrix[i][1] = Integer.parseInt(vision.get(i).toString());
			matrix[i][2] = 0;
			matrix[i][3] = 0;
			// The second row is the place where Ernest is standing
			matrix[i][4] = 0;
			matrix[i][5] = 0;
			matrix[i][6] = 0;
			matrix[i][7] = 0;
		}
		
		// Taste 
		
		matrix[0][8] = taste;
		
		// Kinematic (simulates sensors of body action) 
		
		matrix[1][8] = kinematic;
		
		// Tactile
		
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				matrix[i][9 + j] = Integer.parseInt(touch.get(i + 3*j).toString());
		
		// Circadian (information on day or night)
		
		matrix[2][8] = 0;		

        // Get the corresponding interaction and construct it if it does not yet exist. 
        IErnest ernest = ImosExtension.m_ernests.get(context.getAgent());
        
		String intention = ernest.step(matrix);
               
        return intention;
    }
}