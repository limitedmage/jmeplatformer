package main;

/**
 * Interface for all objects that should implement
 * the update() method
 */
public interface Updateable {

	/**
	 * Method that should update game physics, input, etc
	 * Should handle only game logic.
	 */
	public void update();
}
