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

package org.kuroneko.bbf.model.skills;

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
import org.kuroneko.bbf.model.BBFCharacter;

/**
 *
 * @author Tod Casasent
 */
@XmlRootElement(name = "SkillFactory")
@XmlAccessorType(XmlAccessType.FIELD)
public class SkillFactory
{

	/**
	 *
	 */
	@XmlElementWrapper(name="SkillList")
	@XmlElement(name="Skill")
	protected TreeSet<Skill> mSkills = null;
	
	/**
	 *
	 */
	public SkillFactory()
	{
		mSkills = new TreeSet<>();
	}

	/**
	 *
	 * @param thePC
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	static public TreeSet<Skill> loadSkills(BBFCharacter thePC) throws IOException, JAXBException
	{
		SkillFactory result = null;
		try(InputStream is = SkillFactory.class.getResourceAsStream("/org/kuroneko/bbf/model/skills/SkillFactory.xml"))
		{
			JAXBContext jc = JAXBContext.newInstance(SkillFactory.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			result = (SkillFactory) unmarshaller.unmarshal(is);
		}
		for(Skill skill : result.mSkills)
		{
			skill.init(thePC);
			//System.out.println("skill=" + skill.mName);
		}
		return result.mSkills;
	}
}
