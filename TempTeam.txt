package de.DevinBingham.DHQuidditchPlugin;

import java.util.UUID;


/**
 * Ein Temporäres Quidditch Team. Dieses wird nach dem spiel wieder geleert.
 * @author Devin
 *
 */

public class TempTeam {
	
	protected String Name;
	
	protected UUID seeker = null;
	
	protected UUID keeper = null;
	
	protected UUID beater1 = null;
	protected UUID beater2 = null;
	
	protected UUID chaser1 = null;
	protected UUID chaser2 = null;
	protected UUID chaser3 = null;
	
	public TempTeam(String pName, String pSeeker, String pKeeper, String pBeater1, String pBeater2, String pChaser1, String pChaser2, String pChaser3)
	{
		
		Name = pName;
		
		if(pSeeker != null)
			seeker = UUID.fromString(pSeeker);
		else
			seeker = null;
		
		if(pKeeper != null)
			keeper = UUID.fromString(pKeeper);
		else
			keeper = null;
		
		
		
		if(pBeater1 != null)
			beater1 = UUID.fromString(pBeater1);
		else
			beater1 = null;
		
		if(pBeater2 != null)
			beater2 = UUID.fromString(pBeater2);
		else
			beater2 = null;
		
		
		
		if(pChaser1 != null)
			chaser1 = UUID.fromString(pChaser1);
		else
			chaser1 = null;
		
		if(pChaser2 != null)
			chaser2 = UUID.fromString(pChaser2);
		else
			chaser2 = null;
		
		if(pChaser3 != null)
			chaser3 = UUID.fromString(pChaser3);	
		else
			chaser3 = null;
		
	}
	
	//-------Setter-------//
	
	/**
	 * Setzt die Seeker Position.
	 * @param pSeeker
	 */
	public void setSeeker(UUID pSeeker)
	{
		
		seeker = pSeeker;
		
	}
	
	
	
	/**
	 * Setzt die Keeper Position.
	 * @param pKeeper
	 */
	public void setKeeper(UUID pKeeper)
	{
		
		keeper = pKeeper;
		
	}
	
	
	
	/**
	 * Setzt die erste Beater Position.
	 * @param pBeater1
	 */
	public void setBeater1(UUID pBeater1)
	{
		
		beater1 = pBeater1;
		
	}
	
	/**
	 * Setzt die zweite Beater Position.
	 * @param pBeater2
	 */
	public void setBeater2(UUID pBeater2)
	{
		
		beater2 = pBeater2;
		
	}
	
	
	
	/**
	 * Setzt die erste Chaser Position.
	 * @param pChaser1
	 */
	public void setChaser1(UUID pChaser1)
	{
		
		chaser1 = pChaser1;
		
	}
	
	/**
	 * Setzt die zweite Caser Position.
	 * @param pChaser2
	 */
	public void setChaser2(UUID pChaser2)
	{
		
		chaser2 = pChaser2;
		
	}
	
	/**
	 * Setzt die dritte Chaser Position.
	 * @param pChaser3
	 */
	public void setChaser3(UUID pChaser3)
	{
		
		chaser3 = pChaser3;
		
	}
	
	
	
	//-----Getter-----//
	
	/**
	 * Gibt den Namen des Teams zurück.
	 * @return
	 */
	public String getName()
	{
		
		return Name;
		
	}
	
	
	
	/**
	 * Gibt die Seeker Position zurück.
	 * @return
	 */
	public UUID getSeeker()
	{
		
		return seeker;
		
	}
	
	/**
	 * Gibt die Keeper Position zurück.
	 * @return
	 */
	public UUID getKeeper()
	{
		
		return keeper;
		
	}
	
	
	
	/**
	 * Gibt die erste Beater Position zurück.
	 * @return
	 */
	public UUID getBeater1()
	{
		
		return beater1;
		
	}
	
	/**
	 * Gibt die zweite Beater Position zurück.
	 * @return
	 */
	public UUID getBeater2()
	{
		
		return beater2;
		
	}
	
	
	
	/**
	 * Gibt die erste Chaser Position zurück.
	 * @return
	 */
	public UUID getChaser1()
	{
		
		return chaser1;
		
	}
	
	/**
	 * Gibt die zweite Chaser Position zurück.
	 * @return
	 */
	public UUID getChaser2()
	{
		
		return chaser2;
		
	}
	
	/**
	 * Gibt die dritte Chaser Position zurück.
	 * @return
	 */
	public UUID getChaser3()
	{
		
		return chaser3;
		
	}
	
	
	
	//-----Sonstige Methoden-----//
	
	/**
	 * Überprüft, ob das Team komplett ist.
	 * @return
	 */
	public boolean isComplete()
	{
		
		if(seeker != null && keeper != null && beater1 != null && beater2 != null && chaser1 != null && chaser2 != null && chaser3 != null)
			
			return true;

		else
			
			return false;
			
		
	}

}
