package de.DevinBingham.DHQuidditchPlugin;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class Quidditch {
	
	private Plugin plugin;
	
	private TempTeam team1;
	private TempTeam team2;
	
	private PermTeam gryffindor;
	private PermTeam hufflepuff;
	private PermTeam ravenclaw;
	private PermTeam slytherin;
	
	public Quidditch(Plugin pPlugin)
	{
		
		plugin = pPlugin;
		
		LoadConfig();
		
	}
	
	
	
	/**
	 * Lädt die Konfiguration und erstellt die Teams
	 */
	private void LoadConfig()
	{
		
		team1 = new TempTeam("team1", null, null, null, null, null, null, null);
		team2 = new TempTeam("team2", null, null, null, null, null, null, null);
		
		gryffindor = new PermTeam("gryffindor", plugin.getConfig().getStringList("teams.gryffindor.Seekers"), plugin.getConfig().getStringList("teams.gryffindor.Keepers"), plugin.getConfig().getStringList("teams.gryffindor.Beaters"), plugin.getConfig().getStringList("teams.gryffindor.Chasers"), plugin.getConfig().getString("teams.gryffindor.Seeker"), plugin.getConfig().getString("teams.gryffindor.Keeper"), plugin.getConfig().getString("teams.gryffindor.Beater1"), plugin.getConfig().getString("teams.gryffindor.Beater2"), plugin.getConfig().getString("teams.gryffindor.Chaser1"), plugin.getConfig().getString("teams.gryffindor.Chaser2"), plugin.getConfig().getString("teams.gryffindor.Chaser3"));
		
		hufflepuff = new PermTeam("hufflepuff", plugin.getConfig().getStringList("teams.hufflepuff.Seekers"), plugin.getConfig().getStringList("teams.hufflepuff.Keepers"), plugin.getConfig().getStringList("teams.hufflepuff.Beaters"), plugin.getConfig().getStringList("teams.hufflepuff.Chasers"), plugin.getConfig().getString("teams.hufflepuff.Seeker"), plugin.getConfig().getString("teams.hufflepuff.Keeper"), plugin.getConfig().getString("teams.hufflepuff.Beater1"), plugin.getConfig().getString("teams.hufflepuff.Beater2"), plugin.getConfig().getString("teams.hufflepuff.Chaser1"), plugin.getConfig().getString("teams.hufflepuff.Chaser2"), plugin.getConfig().getString("teams.hufflepuff.Chaser3"));
		
		ravenclaw = new PermTeam("ravenclaw", plugin.getConfig().getStringList("teams.ravenclaw.Seekers"), plugin.getConfig().getStringList("teams.ravenclaw.Keepers"), plugin.getConfig().getStringList("teams.ravenclaw.Beaters"), plugin.getConfig().getStringList("teams.ravenclaw.Chasers"), plugin.getConfig().getString("teams.ravenclaw.Seeker"), plugin.getConfig().getString("teams.ravenclaw.Keeper"), plugin.getConfig().getString("teams.ravenclaw.Beater1"), plugin.getConfig().getString("teams.ravenclaw.Beater2"), plugin.getConfig().getString("teams.ravenclaw.Chaser1"), plugin.getConfig().getString("teams.ravenclaw.Chaser2"), plugin.getConfig().getString("teams.ravenclaw.Chaser3"));
		
		slytherin = new PermTeam("slytherin", plugin.getConfig().getStringList("teams.slytherin.Seekers"), plugin.getConfig().getStringList("teams.slytherin.Keepers"), plugin.getConfig().getStringList("teams.slytherin.Beaters"), plugin.getConfig().getStringList("teams.slytherin.Chasers"), plugin.getConfig().getString("teams.slytherin.Seeker"), plugin.getConfig().getString("teams.slytherin.Keeper"), plugin.getConfig().getString("teams.slytherin.Beater1"), plugin.getConfig().getString("teams.slytherin.Beater2"), plugin.getConfig().getString("teams.slytherin.Chaser1"), plugin.getConfig().getString("teams.slytherin.Chaser2"), plugin.getConfig().getString("teams.slytherin.Chaser3"));
			
	}
	
	
	
	/**
	 * Speichert alle Teams in die Config.
	 */
	private void saveConfig()
	{
		
		saveTeam("gryffindor");
		saveTeam("hufflepuff");
		saveTeam("ravenclaw");
		saveTeam("slytherin");
		
	}
	
	
	
	/**
	 * Speichert ein einzelnes Team.
	 * @param pTeamName
	 */
	private void saveTeam(String pTeamName)
	{
		
		PermTeam team = (PermTeam)getTeam(pTeamName);
		
		try{
		
		plugin.getConfig().set("teams." + pTeamName + ".Seekers", team.getSeekersString());
		plugin.getConfig().set("teams." + pTeamName + ".Keepers", team.getKeepersString());
		plugin.getConfig().set("teams." + pTeamName + ".Beaters", team.getBeatersString());
		plugin.getConfig().set("teams." + pTeamName + ".Chasers", team.getChasersString());
		
		plugin.getConfig().set("teams." + pTeamName + ".Seeker", team.getSeeker().toString());
		plugin.getConfig().set("teams." + pTeamName + ".Keeper", team.getKeeper().toString());
		plugin.getConfig().set("teams." + pTeamName + ".Beater1", team.getBeater1().toString());
		plugin.getConfig().set("teams." + pTeamName + ".Beater2", team.getBeater2().toString());
		plugin.getConfig().set("teams." + pTeamName + ".Chaser1", team.getChaser1().toString());
		plugin.getConfig().set("teams." + pTeamName + ".Chaser2", team.getChaser2().toString());
		plugin.getConfig().set("teams." + pTeamName + ".Chaser3", team.getChaser3().toString());
		
		}catch(NullPointerException e)
		{
			
			plugin.getLogger().info("NullPointerException!");
			
		}
		
		plugin.saveConfig();
		plugin.reloadConfig();
		
	}
	
	
	//---------Sonstige Methoden-------//
	
	/**
	 * Überprüft, ob ein Spieler, oder eine Position vorhanden ist.
	 * @param pPlayer
	 * @return boolean
	 */
	private boolean notNull(UUID pPlayer)
	{
		
		return pPlayer != null;
		
	}
	
	
	
	/**
	 * Überprüft, ob spieler online ist.
	 * @param pPlayer
	 * @return boolean
	 */
	private boolean playerOnline(UUID pPlayer)
	{
		
		if(notNull(pPlayer))
		
			return plugin.getServer().getPlayer(pPlayer).isOnline();
		
		else
		
			return false;
		
	}
	
	
	
	/**
	 * Überprüft, ob ein Spieler (z.B. eine Position) nicht null und online ist.
	 * @param pPlayer
	 * @return boolean
	 */
	private boolean playerValid(UUID pPlayer)
	{
		
		return (notNull(pPlayer) && playerOnline(pPlayer));
		
	}
	
	
	
	/**
	 * Liefert das Ergebnis von playerValid(UUID pPlayer) als farbigen string.
	 * @param pPlayer
	 * @return
	 */
	private String playerValidMessage(UUID pPlayer)
	{
		
		boolean valid = playerValid(pPlayer);
		
		if(valid)
		{
			
			return ChatColor.GREEN + "" + valid;
			
		}
		else
		{
			
			return ChatColor.RED + "" + valid;
			
		}
		
	}
	
	
	
	/**
	 * Liefert die "- Player: " Nachricht für den Team-Status.
	 * @param pPlayer
	 * @return
	 */
	private String getMessagePlayer(UUID pPlayer)
	{
		
		if(notNull(pPlayer))
			
			return "- Player: " + ChatColor.GREEN + plugin.getServer().getPlayer(pPlayer).getName();
			
		else
			
			return "- Player: " + ChatColor.RED + "Not set!";
		
	}
	
	
	
	/**
	 * Liefert die "- Online: " Nachricht für den Team-Status.
	 * @param pPlayer
	 * @return
	 */
	private String getMessageOnline(UUID pPlayer)
	{
		
		if(playerOnline(pPlayer))
		
			return "- Online: " + ChatColor.GREEN + "true";
					
		else
			
			return "- Online: " + ChatColor.RED + "false";
		
	}
	
	
	
	/**
	 * Überprüft, ob das Team komplett ist.
	 * @param pTeam
	 * @return boolean
	 */
	private boolean teamComplete(TempTeam pTeam)
	{
		
		return pTeam.isComplete();
		
	}
	
	
	
	/**
	 * Überprüft, ob alle Spieler vom Team online sind.
	 * @param pTeam
	 * @return boolean
	 */
	private boolean teamOnline(TempTeam pTeam)
	{
		
		return (playerOnline(pTeam.getSeeker()) && playerOnline(pTeam.getKeeper()) && playerOnline(pTeam.getBeater1()) && playerOnline(pTeam.getBeater2()) && playerOnline(pTeam.getChaser1()) && playerOnline(pTeam.getChaser2()) && playerOnline(pTeam.getChaser3()));
		
	}
	
	
	
	/**
	 * Schickt dem Player eine Liste mit Positionen, die eine genauere Information liefern,
	 * wenn man mit der maus darüber hovert.
	 * @param pPlayer
	 * @param pTeam
	 */
	public void sendStatus(Player pPlayer, TempTeam pTeam)
	{
		
		pPlayer.sendMessage(ChatColor.DARK_AQUA + "Here are the Positions of Team " + pTeam.getName() + ":");
		plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "tellraw " + pPlayer.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\"Seeker: " + playerValidMessage(pTeam.getSeeker()) + "\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + ChatColor.BOLD + "Information of the Seeker Position:\n\",\"color\":\"dark_aqua\"},{\"text\":\"" + getMessagePlayer(pTeam.getSeeker()) + "\n\",\"color\":\"gold\"},{\"text\":\"" + getMessageOnline(pTeam.getSeeker()) + "\",\"color\":\"gold\"}]}}}]}");
		plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "tellraw " + pPlayer.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\"Keeper: " + playerValidMessage(pTeam.getKeeper()) + "\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + ChatColor.BOLD + "Information of the Keeper Position:\n\",\"color\":\"dark_aqua\"},{\"text\":\"" + getMessagePlayer(pTeam.getKeeper()) + "\n\",\"color\":\"gold\"},{\"text\":\"" + getMessageOnline(pTeam.getKeeper()) + "\",\"color\":\"gold\"}]}}}]}");
		plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "tellraw " + pPlayer.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\"Beater1: " + playerValidMessage(pTeam.getBeater1()) + "\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + ChatColor.BOLD + "Information of the Beater1 Position:\n\",\"color\":\"dark_aqua\"},{\"text\":\"" + getMessagePlayer(pTeam.getBeater1()) + "\n\",\"color\":\"gold\"},{\"text\":\"" + getMessageOnline(pTeam.getBeater1()) + "\",\"color\":\"gold\"}]}}}]}");
		plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "tellraw " + pPlayer.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\"Beater2: " + playerValidMessage(pTeam.getBeater2()) + "\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + ChatColor.BOLD + "Information of the Beater2 Position:\n\",\"color\":\"dark_aqua\"},{\"text\":\"" + getMessagePlayer(pTeam.getBeater2()) + "\n\",\"color\":\"gold\"},{\"text\":\"" + getMessageOnline(pTeam.getBeater2()) + "\",\"color\":\"gold\"}]}}}]}");
		plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "tellraw " + pPlayer.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\"Chaser1: " + playerValidMessage(pTeam.getChaser1()) + "\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + ChatColor.BOLD + "Information of the Chaser1 Position:\n\",\"color\":\"dark_aqua\"},{\"text\":\"" + getMessagePlayer(pTeam.getChaser1()) + "\n\",\"color\":\"gold\"},{\"text\":\"" + getMessageOnline(pTeam.getChaser1()) + "\",\"color\":\"gold\"}]}}}]}");
		plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "tellraw " + pPlayer.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\"Chaser2: " + playerValidMessage(pTeam.getChaser2()) + "\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + ChatColor.BOLD + "Information of the Chaser2 Position:\n\",\"color\":\"dark_aqua\"},{\"text\":\"" + getMessagePlayer(pTeam.getChaser2()) + "\n\",\"color\":\"gold\"},{\"text\":\"" + getMessageOnline(pTeam.getChaser2()) + "\",\"color\":\"gold\"}]}}}]}");
		plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "tellraw " + pPlayer.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\"Chaser3: " + playerValidMessage(pTeam.getChaser3()) + "\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + ChatColor.BOLD + "Information of the Chaser3 Position:\n\",\"color\":\"dark_aqua\"},{\"text\":\"" + getMessagePlayer(pTeam.getChaser3()) + "\n\",\"color\":\"gold\"},{\"text\":\"" + getMessageOnline(pTeam.getChaser3()) + "\",\"color\":\"gold\"}]}}}]}");
		pPlayer.sendMessage(ChatColor.GRAY + "Hover over each Position for more information!");
		
	}
	
	
	
	/**
	 * Überprüft, ob das Team gültig ist.
	 * Wenn ja, sendet es eine dementsprechende Nachricht an den Spieler.
	 * Wenn nein, sendet er den Status des teams.
	 * @param pTeam
	 * @return boolean
	 */
	public boolean teamValid(Player pPlayer, TempTeam pTeam)
	{
		
		if(teamComplete(pTeam) && teamOnline(pTeam))
		{
			
			pPlayer.sendMessage(ChatColor.GOLD + plugin.getName() + ChatColor.DARK_AQUA + " Team is valid. You may play!");
			
			return true;
			
		}
		else
		{
			
			sendStatus(pPlayer, pTeam);
			
			return false;
			
		}
		
	}
	
	
	
	/**
	 * Gibt das Team mit dem zugehörigen Namen zurück.
	 * Null, wenn Team nicht gefunden.
	 * @param pTeamName
	 * @return
	 */
	public Object getTeam(String pTeamName)
	{
		
		String teamName = pTeamName.toLowerCase();
		
		switch(teamName)
		{
		
		case "team1":
			
			return team1;
		
		case "team2":
			
			return team2;
		
		case "gryffindor":
			
			return gryffindor;
		
		case "hufflepuff":
			
			return hufflepuff;
		
		case "ravenclaw":
			
			return ravenclaw;
		
		case "slytherin":
			
			return slytherin;
			
		default:
			
			return null;
		
		}
		
	}
	
	
	
	/**
	 * Fügt einen Spieler zu einer Position im Team hinzu. Wenn das Hinzufügen fehlschlägt dann wird False zurückgegeben.
	 * @param pSender
	 * @param pTeamName
	 * @param pPosition
	 * @param pPlayer
	 * @return
	 */
	public boolean addPlayerToTeam(Player pSender, PermTeam pTeam, String pPosition, UUID pPlayer)
	{
		
		//mit factions überprüfen, ob der spieler auch in der zugehörigen faction ist.
		
		if(pTeam != null)
		{
			
			if(pTeam.addPlayerToPosition(pPosition, pPlayer))
			{
				
				saveConfig();
				pSender.sendMessage(ChatColor.GREEN + "Player successfully added!");
				return true;
				
			}
			else
			{
				
				pSender.sendMessage(ChatColor.RED + "Unknown Position!");
				return false;
				
			}
			
		}
		else
		{
			
			pSender.sendMessage(ChatColor.RED + "Unknown Team!");
			return false;
			
		}
		
	}
	
	
	
	/**
	 * Sendet eine Liste aller Spieler auf der Position. Wenn nicht dann gibt es false zurück, und schick dem spieler die zugehörige nachricht.
	 * @param pPlayer
	 * @param pPermTeamName
	 * @param pPosition
	 * @return
	 */
	public boolean sendPositionList(Player pPlayer, String pPermTeamName, String pPosition)
	{
		
		PermTeam team = (PermTeam) getTeam(pPermTeamName);
		
		if(team != null)
		{
			
			switch(pPosition.toLowerCase())
			{
			
			case "seeker":
			case "seekers":
				
				pPlayer.sendMessage(ChatColor.DARK_AQUA + "List of all players, which are set for the seeker position:");
				for(UUID id : team.getSeekers())
				{
					
					pPlayer.sendMessage(ChatColor.GOLD + plugin.getServer().getPlayer(id).getName());
					
				}
				return true;
				
			case "keeper":
			case "keepers":
				
				pPlayer.sendMessage(ChatColor.DARK_AQUA + "List of all keepers in the team:");
				for(UUID id : team.getKeepers())
				{
					
					pPlayer.sendMessage(ChatColor.GOLD + plugin.getServer().getPlayer(id).getName());
					
				}
				return true;
			
			case "beater":
			case "beaters":
				
				pPlayer.sendMessage(ChatColor.DARK_AQUA + "List of all beaters in the team:");
				for(UUID id : team.getBeaters())
				{
					
					pPlayer.sendMessage(ChatColor.GOLD + plugin.getServer().getPlayer(id).getName());
					
				}
				return true;
				
			case "chaser":
			case "chasers":
				
				pPlayer.sendMessage(ChatColor.DARK_AQUA + "List of all chasers in the team:");
				for(UUID id : team.getChasers())
				{
					
					pPlayer.sendMessage(ChatColor.GOLD + plugin.getServer().getPlayer(id).getName());
					
				}
				return true;
			
			default:
				
				pPlayer.sendMessage(ChatColor.RED + "Unknown Position!");
				return false;
				
			}
			
		}
		else
		{
			
			pPlayer.sendMessage(ChatColor.RED + "Unknown Team!");
			return false;
			
		}
		
	}
	
	
	//---------------------------------Game-----------------------------------//
	
	/**
	 * Startet ein Quidditch Spiel.
	 * @param pPlayer
	 * @param pTeam1
	 * @param pTeam2
	 */
	public void startGame(Player pPlayer, TempTeam pTeam1, TempTeam pTeam2)
	{
		
		if(teamValid(pPlayer, pTeam1) && teamValid(pPlayer, pTeam2))
		{
			
			
			
		}
		
	}
	

}
