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

package org.kuroneko.bbf.model.moralcode;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
@XmlRootElement(name = "MoralCodeFactory")
@XmlAccessorType(XmlAccessType.FIELD)
public class MoralCodeFactory
{

	/**
	 *
	 */
	@XmlElementWrapper(name="MoralCodeDegree")
	@XmlElement(name="entry")
	public ArrayList<String> mDegree = null;
	
	/**
	 *
	 */
	@XmlElementWrapper(name="MoralCodeA")
	@XmlElement(name="entry")
	public ArrayList<String> mMoralCodeA = null;
	
	/**
	 *
	 */
	@XmlElementWrapper(name="MoralCodeB")
	@XmlElement(name="entry")
	public ArrayList<String> mMoralCodeB = null;
	
	/**
	 *
	 */
	@XmlElementWrapper(name="MoralCodeC")
	@XmlElement(name="entry")
	public ArrayList<String> mMoralCodeC = null;
	
	/**
	 *
	 */
	@XmlElementWrapper(name="MoralCodeD")
	@XmlElement(name="entry")
	public ArrayList<String> mMoralCodeD = null;
	
	/**
	 *
	 */
	@XmlElementWrapper(name="MoralCodeE")
	@XmlElement(name="entry")
	public ArrayList<String> mMoralCodeE = null;
	
	/**
	 *
	 */
	public MoralCodeFactory()
	{
		mDegree = new ArrayList<>();
		mMoralCodeA = new ArrayList<>();
		mMoralCodeB = new ArrayList<>();
		mMoralCodeC = new ArrayList<>();
		mMoralCodeD = new ArrayList<>();
		mMoralCodeE = new ArrayList<>();
	}

	/**
	 *
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	static public MoralCodeFactory loadMoralCode() throws IOException, JAXBException
	{
		MoralCodeFactory result = null;
		try(InputStream is = MoralCodeFactory.class.getResourceAsStream("/org/kuroneko/bbf/model/moralcode/MoralCodeFactory.xml"))
		{
			JAXBContext jc = JAXBContext.newInstance(MoralCodeFactory.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			result = (MoralCodeFactory) unmarshaller.unmarshal(is);
		}
		return result;
	}
}
