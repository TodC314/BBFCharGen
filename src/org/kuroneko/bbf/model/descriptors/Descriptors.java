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

package org.kuroneko.bbf.model.descriptors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.kuroneko.bbf.model.skills.SkillFactory;

/**
 *
 * @author Tod Casasent
 */
public class Descriptors
{

	/**
	 *
	 */
	public ArrayList<String> m1000 = null;

	/**
	 *
	 */
	public ArrayList<String> mDeca = null;
	//1000
	//Mark Hassman
	//mithrilandmages.com
	//esp BareBones NPC Generator
	
	//decahedron issue 1
	
	/**
	 *
	 */
		
	public Descriptors()
	{
		m1000 = new ArrayList<>();
		mDeca = new ArrayList<>();
	}
	
	/**
	 *
	 * @throws IOException
	 */
	public void load() throws IOException
	{
		try(InputStream is = SkillFactory.class.getResourceAsStream("/org/kuroneko/bbf/model/descriptors/1000.tsv");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr))
		{
			String line = br.readLine();
			while(null!=line)
			{
				m1000.add(line);
				line = br.readLine();
			}
		}
		try(InputStream is = SkillFactory.class.getResourceAsStream("/org/kuroneko/bbf/model/descriptors/decahedron.tsv");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr))
		{
			String line = br.readLine();
			while(null!=line)
			{
				mDeca.add(line);
				line = br.readLine();
			}
		}
	}
}
