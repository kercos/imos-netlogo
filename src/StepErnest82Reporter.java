
import org.nlogo.api.*;
import imos.*;

public class StepErnest82Reporter extends DefaultReporter
{
	
    /** A big value that works as infinite */
	private static final int INFINITE = 1000;
	
    /** The values of the pixels */
    private int m_currentLeftPixel   = INFINITE;
    private int m_currentRightPixel  = INFINITE;
    private int m_previousLeftPixel  = INFINITE;
    private int m_previousRightPixel = INFINITE;
    
    /** The features that are sensed by the distal system. */
    private String m_leftFeature = " ";
    private String m_rightFeature = " ";
    
    /** The intrinsic satisfaction of sensing the current features */
    private int m_satisfaction = 0;
    
    private String m_action = ">";
	
	// take three numbers as input, report a string
    public Syntax getSyntax() 
    {
        return Syntax.reporterSyntax(new int[] {Syntax.TYPE_BOOLEAN, Syntax.TYPE_NUMBER, Syntax.TYPE_NUMBER}, Syntax.TYPE_LIST);
    }
    
    /**
     * Record the actually enacted interaction and return the intended action.
     * @param args[0] The enaction status true=succeed, false=fail.
     * @param args[1] The distance to target on left eye.
     * @param args[2] The distance to target on right eye.
     * @return The intended action.
     */
    public Object report(Argument args[], Context context)
        throws ExtensionException
    {
        boolean status ;
        String stimuli = "";

        m_previousLeftPixel  = m_currentLeftPixel;
        m_previousRightPixel = m_currentRightPixel;
        
        // Retrieve the visual pixels from the environment.
        try
        {
            status = args[0].getBoolean();
            m_currentLeftPixel = args[1].getIntValue();
            m_currentRightPixel = args[2].getIntValue();
        }
        catch( LogoException e )
        {   throw new ExtensionException( e.getMessage() ) ;}
        
        IImos imos = ImosExtension.m_imoss.get(context.getAgent());

        m_satisfaction = 0;
        
        // The stimuli correspond to changes in the visual pixels.
        m_leftFeature  = sensePixel(m_previousLeftPixel, m_currentLeftPixel);
        m_rightFeature = sensePixel(m_previousRightPixel, m_currentRightPixel);         
        
        // Specific satisfaction when disappear on both eyes
        if (m_leftFeature.equals("o") && m_rightFeature.equals("o"))
                m_satisfaction = -100;

        // The visual stimuli is the concatenation of both eyes if any.
        if (!m_leftFeature.equals(" ") || !m_rightFeature.equals(" ") )
        	stimuli =  m_leftFeature + "|" + m_rightFeature;

        // Get the corresponding interaction and construct it if it does not yet exist. 
        stimuli = (status ? "t" : "f") + stimuli;
    	IAct a = imos.addInteraction(m_action, stimuli, m_satisfaction);
        
    	// Get the next intention
        m_action = imos.step(a).getSchema().getLabel();
        
        LogoList list = new LogoList();   
        
        list.add(m_action);
        list.add(a.getLabel()); // The act label is only for debug.
        
        return list;
    }
    
    /**
     * Sense the feature based on a pixel change 
     * @param previousPixel The pixel's previous value.
     * @param currentPixel The pixel's current value.
     * @return The sensed feature
     */
    private String sensePixel(int previousPixel, int currentPixel) 
    {
            String feature = " ";
            int satisfaction = 0;
            
            // arrived
            if (previousPixel > currentPixel && currentPixel == 0)
            {
                    feature = "x";
                    satisfaction = 150;
            }
            
            // closer
            else if (previousPixel < INFINITE && currentPixel < previousPixel)
            {
                    feature = "+";
                    satisfaction = 100;
            }

            // appear
            else if (previousPixel == INFINITE && currentPixel < INFINITE)
            {
                    feature = "*";
                    satisfaction = 150;
            }
            
            // disappear
            else if (previousPixel < INFINITE && currentPixel == INFINITE)
            {
                    feature = "o";
                    satisfaction = -150;
            }

            System.out.println("Sensed " + feature);
            
            m_satisfaction += satisfaction;

            return feature;
    }

}