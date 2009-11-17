package highscores;

import java.io.IOException;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;
import main.MainMidlet;

/**
 * Class that sends challenges via text message
 */
public class Challenge extends Form implements CommandListener {

	private MainMidlet midlet;
	private TextField number;
	private String message;

	/**
	 * Create the Challenge form
	 * @param midlet Parent midlet
	 * @param name Name of the player
	 * @param points Points the player made in the game
	 */
	public Challenge(MainMidlet midlet, String name, int points) {
		super("Callenge a friend");

		this.midlet = midlet;

		this.number = new TextField("Phone number", null, 10, TextField.PHONENUMBER);
		this.append(this.number);

		this.message = name + " has made " + points + "playing Metal Vs Pop!! " +
				"Play and try to beat his score!";
		this.append("Message: " + this.message);

		this.addCommand(new Command("Send", Command.OK, 1));
		this.addCommand(new Command("Cancel", Command.CANCEL, 1));
		this.setCommandListener(this);
	}

	/**
	 * Handle commands
	 * @param c Command issued
	 * @param d Displayable command was sent through
	 */
	public void commandAction(Command c, Displayable d) {
		switch (c.getCommandType()) {
			case Command.OK:
				try {
					Challenge.sendSms(this.number.getString(), this.message);
					this.midlet.startHighScores();
				}
				catch (Exception e ) {
					Alert a = new Alert("Error sending message: " + e.getMessage());
					Display.getDisplay(this.midlet).setCurrent(a, this);
				}
				break;

			case Command.CANCEL:
				this.midlet.startHighScores();
				break;
		}
	}
	
	/**
	 * Sends a text message.
	 * Adapted from http://wiki.forum.nokia.com/index.php/How_to_Send_Text_SMS_in_Java_ME
	 * 
	 * @param number Number to send the message to
	 * @param message Contents of the message
	 * @throws ConnectionNotFoundException If the target of the number could not be found
	 * @throws SecurityException If program does not have permision to send message
	 * @throws IOException If message delivery was unsuccessful
	 */
	public static void sendSms(String number, String message) throws ConnectionNotFoundException, SecurityException, IOException {
		//sets address to send message
		String addr = "sms://" + number;
		// opens connection
		MessageConnection conn = (MessageConnection) Connector.open(addr);
		// prepares text message
		TextMessage msg = (TextMessage) conn.newMessage(MessageConnection.TEXT_MESSAGE);
		//set text
		msg.setPayloadText(message);
		// send message
		conn.send(msg);
		conn.close();
	}
}
