// This source code is UTF-8 coded - see https://stackoverflow.com/questions/9180981/how-to-support-utf-8-encoding-in-eclipse
package version;


import java.io.Serializable;

import supportStuff.utility.Version;


/**
 * The single purpose of this class is to hold the &quot;project version&quot;.
 * Since the code/project is an exercise, it's named {@link GivenCodeVersion}.
 * It's not expected or wanted that there are different release branches.
 * There has to be only one single release branch (for the "given code")!!!
 * Hence, there is a single/central project version
 * that is stored in this class.
 * 
 * @version {@value #encodedVersion}
 * @author  Michael Schäfers ;  Px@Hamburg-UAS.eu
 */
public class GivenCodeVersion implements Serializable {
    //
    //--VERSION:-------------------------------#---vvvvvvvvv---vvvv-vv-vv--vv
    //  ========                               #___~version~___YYYY_MM_DD__dd_
    final static private long encodedVersion = 2___00001_014___2023_11_28__01L;
    //-----------------------------------------#---^^^^^-^^^---^^^^-^^-^^--^^
    final static private Version version = new Version( encodedVersion );
    //
    final static private long serialVersionUID = version.getVersionNumber();
    
    
    
    
    
    /**
     * The method {@link #getVersionNumber()} delivers the project version.
     * 
     * @return version
     */
    static public long getVersionNumber(){
        return version.getVersionNumber();
    }//method()
    
    /**
     * The method {@link #getDecodedVersion()} delivers the given project version
     * as reground/readable String.
     * 
     * @return version as decoded/readable String.
     */
    static public String getDecodedVersion(){
        return version.getDecodedVersion();
    }//method()
    
}//class
