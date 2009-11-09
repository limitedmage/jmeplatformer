package challenge;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import main.MainMidlet;

public class Challenge extends Form implements CommandListener {

	MainMidlet midlet;

	public Challenge(MainMidlet midlet) {
		super("Retar a un amigo");

		this.midlet = midlet;

		TextField number = new TextField("Numero de tu amigo", null, 10, TextField.PHONENUMBER);

		Command send = new Command("Enviar", Command.OK, 1);
	}

	public void commandAction(Command c, Displayable d) {
	}
}
