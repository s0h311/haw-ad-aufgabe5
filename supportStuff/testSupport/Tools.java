// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package supportStuff.testSupport;


import java.io.Serializable;

import supportStuff.applicationSupport.Coordinate;
import supportStuff.utility.Version;


/**
 * Task: For information see ReadMe.txt resp. task
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schäfers ;  Px@Hamburg-UAS.eu
 */
public class Tools implements Serializable {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00001_004___2021_11_11__03L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    
    static final private long serialVersionUID = version.getVersionNumber();
    
    
    
    static public int delta( final Coordinate cA,  final Coordinate cB ){
        return delta( cA.getX(), cB.getX() )  +  delta( cA.getY(), cB.getY() );
    }//method()
    //
    static private int delta( final int iA,  final int iB ){
        return  (iA<iB)  ?  iB-iA : iA-iB;
    }//method()
    
}//class
