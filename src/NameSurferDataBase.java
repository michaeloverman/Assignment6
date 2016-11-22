/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NameSurferDataBase implements NameSurferConstants {

	private Map<String, NameSurferEntry> names;

    /* Constructor: NameSurferDataBase(filename) */
    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
	public NameSurferDataBase(String filename) {
        // You fill this in //
        names = new HashMap<>();

        BufferedReader s;
        // read file
        try {
            s = new BufferedReader(new FileReader("C:\\Users\\Michael\\IntelliJProjects\\Assignment6\\data\\names-data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        // add name to map
        while (true) {
            String str = null;
            try {
                str = s.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (str == null) {
                break;
            }

            NameSurferEntry name = new NameSurferEntry(str);
            names.put(name.getName(), name);
        }
    }
	
    /* Method: findEntry(name) */
    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		return names.get(name);
	}
}

