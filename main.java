/*TODO:
	 * - Spieler, die im spiel sind haben Leder armor an, dessen Farbe dem des jeweiligen Teams entspricht.
	 * - 
	 */

package de.DevinBingham.DHQuidditchPlugin;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{
	
	private Quidditch quidditch;
	
	public void onEnable() {
		
		this.getLogger().info("Loading Config...");
		quidditch = new Quidditch(this);
		this.getConfig().options().copyDefaults(true);
		
		this.saveDefaultConfig();
		this.getLogger().info("Plugin enabled!");
		
	}
	
	public void onDisable() {
		
		this.getLogger().info("Plugin disabled!");		
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player)
		{
			
			Player player = (Player)sender;
			
			
			//quid
			if(command.getName().equalsIgnoreCase("quid"))
			{
				
				if(args.length >= 1)
				{
					
					switch(args[0].toLowerCase())
					{
					
					//quid teams
					case "teams":
						
						//quid teams <team>
						if(args.length >= 2)
						{
							
							Object team = quidditch.getTeam(args[1]);
							
							if(team != null)
							{
								
								TempTeam tempTeam = null;
								PermTeam permTeam = null;
								
								if(team.getClass().equals(TempTeam.class))
								{
									
									tempTeam = (TempTeam) team;
									permTeam = null;
									
								}
								else
								if(team.getClass().equals(PermTeam.class))
								{
									
									tempTeam = null;
									permTeam = (PermTeam) team;
									
								}
								
								//quid teams <team> add/poslist <value>
								if(args.length >= 4)
								{
									
									switch(args[2].toLowerCase())
									{
									
									case "add":
										
										//quid teams <team> add <player> <position>
										if(args.length >= 5)
										{
											
											if(permTeam != null)
											{
												
												@SuppressWarnings("deprecation")
												OfflinePlayer targetPlayer = this.getServer().getOfflinePlayer(args[3]);
												
												if(targetPlayer != null)
												{
												
													quidditch.addPlayerToTeam(player, permTeam, args[4], targetPlayer.getUniqueId());
													return true;
													
												}												
												else
												{
													
													player.sendMessage(ChatColor.RED + "Player not found!");
													return true;
													
												}
												
											}
											else
											{
												
												player.sendMessage(ChatColor.RED + "You tried to add a Player to a temporary Team. Did you want to use:\n/quid teams team1/team2 select <Position1>:<Player1> <Position2>:<Player2> ...?");
												return true;
												
											}
											
										}
										else
										{
											
											player.sendMessage(ChatColor.RED + "Not enough arguments for /quid teams <team> add ! Try:\n/quid teams <team> add <player> <position>");
											return true;
											
										}
										
										
									//quid teams <team> poslist <position>
									case "positionlist":
									case "poslist":
										
										if(permTeam != null)
										{
										
											quidditch.sendPositionList(player, ((PermTeam) team).getName(), args[3]);
											return true;
											
										}										
										else
										{
											
											player.sendMessage(ChatColor.RED + "There are no Position-Lists in Temp. Groups!");
											return true;
											
										}

										
									//case
										
									}
									
								}
								else
								//quid teams <team>
								if(args.length == 2)
								{
									
									quidditch.sendStatus(player, ((TempTeam) team));
									return true;
									
								}
								else
								{
									
									//TODO: sendTeamHelp <- Sendet alle Befehle, die mit /quid teams benutzt werden können
									return true;
									
								}
								
							}
							else
							{
								
								player.sendMessage(ChatColor.RED + "Unknown Team!");
								return true;
								
							}
							
						}
						
					//break;
					
					case "help":
						
						sendHelp(player, 1);
						return true;
						
					//break;
					
					}
					
				}
				else
				{
					
					player.sendMessage(ChatColor.DARK_AQUA + "Use /quid help for a list of all Commands.");
					return true;
					
				}
				
			}
			
		}
		
		return true;
				
	}
	
	
	
	
	//----------Sonstige Methoden-----------//

	public void sendHelp(Player pPlayer, int pPage)
	{
		
		/*/tellraw @a [{"text":"------------ Quidditch Help 1 of 2 ------------\\n","color":"dark_aqua","bold":"true"},{"text":" <> = Required [] = Optional\\n\\n","color":"dark_aqua"},{"text":"- /quid help [page]\\n","color":"gold","hoverEvent":{"action":"show_text","value":{"text":"","extra":[{"text":"Usage for /quid help [page]:\\n","color":"dark_aqua"},{"text":"- Gives you a list of all Quidditch-Commands.\\n","color":"gold"},{"text":"- Hover over the Commands to get more Information","color":"gold"}]}}},{"text":"- /quid teams\\n","color":"gold","hoverEvent":{"action":"show_text","value":{"text":"","extra":[{"text":"Usage for /quid teams:\\n","color":"dark_aqua"},{"text":"- Gives you a List of all Team-Commands\\n","color":"gold"},{"text":"- Hover over the commands to get more Information.","color":"gold"}]}}},{"text":"- /quid teams \\n","color":"gold","hoverEvent":{"action":"show_text","value":{"text":"","extra":[{"text":"Usage for /quid teams :\\n","color":"dark_aqua"},{"text":"- Gives you a list of all positions in the team.\\n","color":"gold"},{"text":"- Tells you if a position is ready to play or not.\\n","color":"gold"},{"text":"- Hover over a position to see who's playing on that position.\\n","color":"gold"},{"text":"- Hover over a position to see, if the player is online.","color":"gold"}]}}},{"text":"- /quid teams add \\n","color":"gold","hoverEvent":{"action":"show_text","value":{"text":"","extra":[{"text":"Usage for /quid teams add :\\n","color":"dark_aqua"},{"text":"- Adds a Player to a Position-List in the team.\\n","color":"gold"},{"text":"- This is NOT selecting a Player for the match!!!","color":"gold"}]}}}]*/
		
		if(pPage == 1)
		{
		
			this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "tellraw " + pPlayer.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\"---------- Quidditch Help 1 of 2 ------------\",\"color\":\"dark_aqua\",\"bold\":\"true\"},{\"text\":\"                      <> = Required     [] = Optional\",\"color\":\"dark_aqua\"}]}");
			pPlayer.sendMessage(""); //<--- Leere Zeile!
			this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "tellraw " + pPlayer.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\"- /quid help [page]\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Usage for /quid help [page]:\n\",\"color\":\"dark_aqua\"},{\"text\":\"- Gives a list with all Quidditch commands.\n\",\"color\":\"gold\"},{\"text\":\"- Hover over each command for more information.\",\"color\":\"gold\"}]}}}]}");
			this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "tellraw " + pPlayer.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\"- /quid teams\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Usage for /quid teams:\n\",\"color\":\"dark_aqua\"},{\"text\":\"- Gives a list of all teams-related commands.\n\",\"color\":\"gold\"},{\"text\":\"- Hover over each command for more information.\",\"color\":\"gold\"}]}}}]}"); //TeamHelp TODO!
			this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "tellraw " + pPlayer.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\"- /quid teams <team>\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Usage for /quid teams <team>:\n\",\"color\":\"dark_aqua\"},{\"text\":\"- See if the positions in the team are ready for the next match.\n\",\"color\":\"gold\"},{\"text\":\"- Hover over each position for more information.\",\"color\":\"gold\"}]}}}]}");
			this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "tellraw " + pPlayer.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\"- /quid teams <team> add <player> <position>\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Usage for /quid teams <team> add <player> <position>:\n\",\"color\":\"dark_aqua\"},{\"text\":\"- Adds a player to a Position-List in the team.\n\",\"color\":\"gold\"},{\"text\":\"- Does NOT select the player for the next match!!!\",\"color\":\"gold\"}]}}}]}");
			this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "tellraw " + pPlayer.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\"- /quid teams <team> poslist <position>\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Usage for /quid teams <team> poslist <position>:\n\",\"color\":\"dark_aqua\"},{\"text\":\"- Gives you all players in the Position-List of that position.\n\",\"color\":\"gold\"},{\"text\":\"- This are the players you can select for the next match.\",\"color\":\"gold\"}]}}}]}");
			
			
			pPlayer.sendMessage(ChatColor.GRAY + "Hover over the commands for more information.");
			
		}
		else
		if(pPage == 2)
		{
			
			//...
			
		}
		
	}

}
