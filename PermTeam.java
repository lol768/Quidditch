package de.DevinBingham.DHQuidditchPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PermTeam extends TempTeam{
	
	List<UUID> seekers = null;
	
	List<UUID> keepers = null;
	
	List<UUID> beaters = null;
	
	List<UUID> chasers = null;
	
	public PermTeam(String pName, List<String> pSeekers, List<String> pKeepers, List<String> pBeaters, List<String> pChasers, String pSeeker, String pKeeper, String pBeater1, String pBeater2, String pChaser1, String pChaser2, String pChaser3)
	{
		
		super(pName, pSeeker, pKeeper, pBeater1, pBeater2, pChaser1, pChaser2, pChaser3);
	
		List<UUID> tempList = new ArrayList<UUID>();
		
		for(String s : pSeekers)
		{
			
			tempList.add(UUID.fromString(s));
			
		}
		
		seekers = tempList;
		
		tempList.clear();
		
		for(String s : pKeepers)
		{
			
			tempList.add(UUID.fromString(s));
			
		}
		
		keepers = tempList;
		
		tempList.clear();
		
		for(String s : pBeaters)
		{
			
			tempList.add(UUID.fromString(s));
			
		}
		
		beaters = tempList;
		
		tempList.clear();
		
		for(String s : pChasers)
		{
			
			tempList.add(UUID.fromString(s));
			
		}
		
		chasers = tempList;
		
	}
	
	//-----Setter-----//
	
	public List<UUID> getSeekers()
	{
		
		return seekers;
		
	}
	
	public List<UUID> getKeepers()
	{
		
		return keepers;
		
	}
	
	public List<UUID> getBeaters()
	{
		
		return beaters;
		
	}
	
	public List<UUID> getChasers()
	{
		
		return chasers;
		
	}
	
	
	
	public List<String> getSeekersString()
	{
		
		List<String> stringList = new ArrayList<String>();
		
		for(UUID id : seekers)
		{
			
			stringList.add(id.toString());
			
		}
		
		return stringList;
		
	}
	
	public List<String> getKeepersString()
	{
		
		List<String> stringList = new ArrayList<String>();
		
		for(UUID id : keepers)
		{
			
			stringList.add(id.toString());
			
		}
		
		return stringList;
		
	}
	
	public List<String> getBeatersString()
	{
		
		List<String> stringList = new ArrayList<String>();
		
		for(UUID id : beaters)
		{
			
			stringList.add(id.toString());
			
		}
		
		return stringList;
		
	}
	
	public List<String> getChasersString()
	{
		
		List<String> stringList = new ArrayList<String>();
		
		for(UUID id : chasers)
		{
			
			stringList.add(id.toString());
			
		}
		
		return stringList;
		
	}
	
	
	/**
	 * Fügt einen Spieler zur Position hinzu. Wenn Es die Position nicht gibt wird false zurückgegeben.
	 * @param pPosition
	 * @param pPlayer
	 * @return
	 */
	public boolean addPlayerToPosition(String pPosition, UUID pPlayer)
	{
		
		String position = pPosition.toLowerCase();
		
		switch(position)
		{
		
		case "seeker":
			
			if(seekers == null)
			{
				
				seekers = new ArrayList<UUID>();
				
			}
			
			seekers.add(pPlayer);
			return true;
			
		case "keeper":
			
			if(keepers == null)
			{
				
				keepers = new ArrayList<UUID>();
				
			}
			
			keepers.add(pPlayer);
			return true;
			
		case "beater":
			
			if(beaters == null)
			{
				
				beaters = new ArrayList<UUID>();
				
			}
			
			beaters.add(pPlayer);
			return true;
			
		case "chaser":
			
			if(chasers == null)
			{
				
				chasers = new ArrayList<UUID>();
				
			}
			
			chasers.add(pPlayer);
			return true;
		
		}
		
		return false;
		
	}

}
