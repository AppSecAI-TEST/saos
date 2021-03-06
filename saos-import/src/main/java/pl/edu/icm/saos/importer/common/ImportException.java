package pl.edu.icm.saos.importer.common;

/**
 * @author Łukasz Dumiszewski
 */

public class ImportException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ImportException(String message) {
        super(message);
    }
    
    public ImportException(Throwable t) {
        super(t);
    }
    
    public ImportException(String message, Throwable t) {
        super(message, t);
    }
}
