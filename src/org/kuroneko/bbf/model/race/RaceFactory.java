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

package org.kuroneko.bbf.model.race;

import java.io.IOException;
import java.io.InputStream;
import java.util.TreeSet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tod Casasent
 */
@XmlRootElement(name = "RaceFactory")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceFactory
{

	/**
	 *
	 */
	@XmlElementWrapper(name="RaceList")
	@XmlElement(name="Race")
	protected TreeSet<Race> mRaces = null;
	
	/**
	 *
	 */
	public RaceFactory()
	{
		mRaces = new TreeSet<>();
	}

	/**
	 *
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	static public TreeSet<Race> loadRaces() throws IOException, JAXBException
	{
		RaceFactory result = null;
		try(InputStream is = RaceFactory.class.getResourceAsStream("/org/kuroneko/bbf/model/race/RaceFactory.xml"))
		{
			JAXBContext jc = JAXBContext.newInstance(RaceFactory.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			result = (RaceFactory) unmarshaller.unmarshal(is);
		}
		for(Race race : result.mRaces)
		{
			//System.out.println("race=" + race.getName());
		}
		return result.mRaces;
	}
}
