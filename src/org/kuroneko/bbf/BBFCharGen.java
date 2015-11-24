/*
* Licensing
* This software is licensed under the Creative Commons Attribution-Noncommercial-ShareAlike 3.0 Unported License; (CC BY-NC-SA 3.0); Some Rights Reserved.
* BareBones Fantasy™ and Keranak Kingdoms™ are copyright 2012, and are trademarks of DwD Studios. 
* These trademarks are used under the Creative Commons Attribution-Noncommercial-ShareAlike 3.0 Unported License; (CC BY-NC-SA 3.0); Some Rights Reserved.
* To view a copy of this license, visit: http://creativecommons.org/licenses/by-nc-sa/3.0
* All data is (CC BY-NC-SA 3.0) and used with permission.
* Basic game setup, races, and Decahedron Descriptors are from DwDStudios http://dwdstudios.com/ (They also make Covert Ops)
* Available 1000 Descriptors by Mark Hassman http://mithrilandmages.com (check out his cool NPC/character generators)
*/

package org.kuroneko.bbf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kuroneko.bbf.model.BBFCharacter;
import org.kuroneko.bbf.view.AspectView;

/**
 * BBFCharGen is the application class--it creates the scene and sets up the application window.
 * @author Tod Casasent
 */
public class BBFCharGen extends Application
{

	/**
	 * Used to store current character being created
	 */
	static public BBFCharacter mPC = null;

	/**
	 * stores the stage
	 */
	static public Stage M_STAGE = null;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		M_STAGE = stage;
		mPC = new BBFCharacter();
		//
		Parent root = FXMLLoader.load(AspectView.class.getResource("BBFView.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("BareBones Fantasy Character Generator");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
	
}
