package src;

/** Exception qui indique qu'une action d'un joueur est incorrecte.
 */
public class ActionInvalideException extends RuntimeException {

	/** Initaliser une ActionInvalideException avec le message précisé.
	  * @param message le message explicatif
	  */
	public ActionInvalideException(String message) {
		super(message);
	}

}
