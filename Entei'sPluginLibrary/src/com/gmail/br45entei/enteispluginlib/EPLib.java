package com.gmail.br45entei.enteispluginlib;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.Spring;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.server.RemoteServerCommandEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class EPLib extends JavaPlugin implements Listener {
	public EPLib plugin;
	public static final String rwhite=ChatColor.RESET+""+ChatColor.WHITE;public static final ChatColor aqua=ChatColor.AQUA;public static final ChatColor black=ChatColor.BLACK;public static final ChatColor blue=ChatColor.BLUE;public static final ChatColor bold=ChatColor.BOLD;public static final ChatColor daqua=ChatColor.DARK_AQUA;public static final ChatColor dblue=ChatColor.DARK_BLUE;public static final ChatColor dgray=ChatColor.DARK_GRAY;public static final ChatColor dgreen=ChatColor.DARK_GREEN;public static final ChatColor dpurple=ChatColor.DARK_PURPLE;public static final ChatColor dred=ChatColor.DARK_RED;public static final ChatColor gold=ChatColor.GOLD;public static final ChatColor gray=ChatColor.GRAY;public static final ChatColor green=ChatColor.GREEN;public static final ChatColor italic=ChatColor.ITALIC;public static final ChatColor lpurple=ChatColor.LIGHT_PURPLE;public static final ChatColor magic=ChatColor.MAGIC;public static final ChatColor red=ChatColor.RED;public static final ChatColor reset=ChatColor.RESET;public static final ChatColor striken=ChatColor.STRIKETHROUGH;public static final ChatColor underline=ChatColor.UNDERLINE;public static final ChatColor white=ChatColor.WHITE;public static final ChatColor yellow=ChatColor.YELLOW;
	public static String pluginName = white + "[" + gold + "Entei's Plugin Library" + white + "] ";
	/**@param str
	 * @return
	 * The given string with ChatColor.*/
	public static String formatColorCodes(String str){return str.replaceAll("(?i)&w",white+"").replaceAll("(?i)&_",rwhite).replaceAll("(?i)&b",aqua+"").replaceAll("(?i)&0",black+"").replaceAll("(?i)&9",blue+"").replaceAll("(?i)&l",bold+"").replaceAll("(?i)&3",daqua+"").replaceAll("(?i)&1",dblue+"").replaceAll("(?i)&8",dgray+"").replaceAll("(?i)&2",dgreen+"").replaceAll("(?i)&5",dpurple+"").replaceAll("(?i)&4",dred+"").replaceAll("(?i)&6",gold+"").replaceAll("(?i)&7",gray+"").replaceAll("(?i)&a",green+"").replaceAll("(?i)&o",italic+"").replaceAll("(?i)&d",lpurple+"").replaceAll("(?i)&k",magic+"").replaceAll("(?i)&c",red+"").replaceAll("(?i)&m",striken+"").replaceAll("(?i)&n",underline+"").replaceAll("(?i)&f",white+"").replaceAll("(?i)&e",yellow+"").replaceAll("(?i)&r",reset+"");}
	/**@param str String
	 * @return The given string without ChatColor. Does not remove the ChatColor codes(i.e. "&f").*/
	public static String stripColorCodes(String str) {return str.replaceAll("(?i)&w","").replaceAll("(?i)\u00A7b","").replaceAll("\u00A70","").replaceAll("\u00A79","").replaceAll("(?i)\u00A7l","").replaceAll("\u00A73","").replaceAll("\u00A71","").replaceAll("\u00A78","").replaceAll("\u00A72","").replaceAll("\u00A75","").replaceAll("\u00A74","").replaceAll("\u00A76","").replaceAll("\u00A77","").replaceAll("(?i)\u00A7a","").replaceAll("(?i)\u00A7o","").replaceAll("(?i)\u00A7d","").replaceAll("(?i)\u00A7k","").replaceAll("(?i)\u00A7c","").replaceAll("(?i)\u00A7m","").replaceAll("(?i)\u00A7n","").replaceAll("(?i)\u00A7f","").replaceAll("(?i)\u00A7e","").replaceAll("(?i)\u00A7r","");/*return Pattern.compile("(?i)"+String.valueOf("\u00A7")+"[0-9A-FK-OR]+").matcher(str).replaceAll("");*/}
	private static final Logger logger = Logger.getLogger("Minecraft");
	public static PluginDescriptionFile pdffile;
	public static ConsoleCommandSender console = null;
	public static String consoleSayFormat = "";
	public static CommandSender rcon = null;
	public static boolean vaultIsAvailable = false;
	//private static boolean chatColorToHtmlDebounce = false;
	public static String ECMDataFolderName = "";
	public static String beingUsedBy = "";
	public static String ecmDateColor = "&a";
	public static String chatLogFileName = "";
	public static int chatLogFontSize = 0;
	private static int spanEndTagCount = 0;
	public static boolean fontEndsWithNextColor = false;
	private static String lastColorCode = "";
	private static String lastFontCode = "";
	public static final String getPunctuationChars = "\\p{Punct}+";
	public static final String getWhiteSpaceChars = "\\s+"/*"\\p{Space}+"*/;
	public static final String getAlphaNumericChars = "\\p{Alnum}+";
	public static final String getAlphabetChars = "\\p{Alpha}+";
	public static final String getNumberChars = "\\p{Digit}+";
	public static final String getUpperCaseChars = "\\p{Lower}+";
	public static final String getLowerCaseChars = "\\p{Upper}+";
	/**The variable used to store messages that are meant to be displayed only once.
	 * @see <a href="http://enteisislandsurvival.no-ip.org/javadoc/index.html">Main.sendOneTimeMessage()</a>
	 * @see <a href="http://enteisislandsurvival.no-ip.org/javadoc/index.html">Java Documentation for EnteisCommands</a>
	 */
	private static ArrayList<String> oneTimeMessageList = new ArrayList<String>();
	public static boolean logChats = false;
	public static ArrayList<String> ChatColorCharList = new ArrayList<String>();
	public static ArrayList<String> ChatColorHtmlList = new ArrayList<String>();
	public static ArrayList<String> ChatColorFontList = new ArrayList<String>();
	public static ArrayList<String> ChatColorHtmlFontStart = new ArrayList<String>();
	public static ArrayList<String> ChatColorHtmlFontEnd = new ArrayList<String>();
	public static String getSystemTime(boolean getTimeOnly) {
		String timeAndDate = "";
		if(getTimeOnly == false) {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			Date date = new Date();
			timeAndDate = dateFormat.format(date);
		} else {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			timeAndDate = dateFormat.format(date);
		}
		return formatColorCodes(ecmDateColor + timeAndDate + "&r ");
	}
	@Override
	public void onDisable() {
		sendConsoleMessage(pluginName + "&eVersion " + pdffile.getVersion() + " is now disabled!");
	}
	@Override
	public void onEnable() {pdffile = this.getDescription();
		console = getServer().getConsoleSender();
		consoleSayFormat = "&e" + console.getName() + "&r&f ";
		ChatColorCharList.add("0");ChatColorHtmlList.add("color: #000000");
		ChatColorCharList.add("1");ChatColorHtmlList.add("color: #000080");
		ChatColorCharList.add("2");ChatColorHtmlList.add("color: #008000");
		ChatColorCharList.add("3");ChatColorHtmlList.add("color: #008080");
		ChatColorCharList.add("4");ChatColorHtmlList.add("color: #B22222");
		ChatColorCharList.add("5");ChatColorHtmlList.add("color: #800080");
		ChatColorCharList.add("6");ChatColorHtmlList.add("color: #B09500");
		ChatColorCharList.add("7");ChatColorHtmlList.add("color: #C7C7C7");
		ChatColorCharList.add("8");ChatColorHtmlList.add("color: #696969");
		ChatColorCharList.add("9");ChatColorHtmlList.add("color: #0000FF");
		ChatColorCharList.add("a");ChatColorHtmlList.add("color: #00FF00");
		ChatColorCharList.add("b");ChatColorHtmlList.add("color: #00FFFF");
		ChatColorCharList.add("c");ChatColorHtmlList.add("color: #FF0000");
		ChatColorCharList.add("d");ChatColorHtmlList.add("color: #FF00F8");
		ChatColorCharList.add("e");ChatColorHtmlList.add("color: #FFFF00");
		ChatColorCharList.add("f");ChatColorHtmlList.add("color: #FFFFFF");
		ChatColorFontList.add("m");ChatColorHtmlFontStart.add("<strike>");ChatColorHtmlFontEnd.add("</strike>");
		ChatColorFontList.add("n");ChatColorHtmlFontStart.add("<u>");ChatColorHtmlFontEnd.add("</u>");
		ChatColorFontList.add("l");ChatColorHtmlFontStart.add("<strong>");ChatColorHtmlFontEnd.add("</strong>");
		ChatColorFontList.add("o");ChatColorHtmlFontStart.add("<em>");ChatColorHtmlFontEnd.add("</em>");
		ChatColorFontList.add("r");ChatColorHtmlFontStart.add("</span></strike></u></strong></em><span style=\"color: #FFFFFF\">");ChatColorHtmlFontEnd.add("");
		vaultIsAvailable = (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null);
		sendConsoleMessage(pluginName + "&aVersion " + pdffile.getVersion() + " is now enabled!");
	}
	/**Checks str1 against str2(and vice versa) to see if they either equal(case ignored), if str1 starts with str2 and str1 is less than 6 characters(and vice versa), and removes all non-alpha-numeric characters and performs the checks again.
	 * @param str1 String
	 * @param str2 String
	 * @return True if str1 is equal to, starts with, or ends with str1, and vice versa; false otherwise */
	public static boolean isSimilarTo(String str1, String str2) {
		if(str1 == null || str2 == null || str1.equals("") || str2.equals("")) return false;
		boolean rtrn = (str1.equals(str2)) || (str1.equalsIgnoreCase(str2))||
		(str1.startsWith(str2)&&str1.length()+1>=(str2.length()))||
		(str1.endsWith(str2)&&str1.length()+1>=str2.length()&&str2.length()<=6)||
		(str2.startsWith(str1)&&str2.length()+1>=(str1.length()))||
		(str2.endsWith(str1)&&str2.length()+1>=str1.length()&&str1.length()<=6);
		if(rtrn == true) {return true;}
		str1 = str1.replaceAll("[^\\p{Alnum}\\p{Space}]+", "");str2 = str2.replaceAll("[^\\p{Alnum}\\p{Space}]+", "");//Removes everything except AaBbCc, 123, and whitespace)
		if(str1.equals("") || str2.equals("")) return false;
		rtrn = (str1.equals(str2)) || (str1.equalsIgnoreCase(str2))||
		(str1.startsWith(str2)&&str1.length()+1>=(str2.length()))||
		(str1.endsWith(str2)&&str1.length()+1>=str2.length()&&str2.length()<=6)||
		(str2.startsWith(str1)&&str2.length()+1>=(str1.length()))||
		(str2.endsWith(str1)&&str2.length()+1>=str1.length()&&str1.length()<=6);
		return false;
	}
	public static String ignoreCase(String str) {return "(?i)" + Pattern.quote(str);}
	@SuppressWarnings("boxing")public static double toNumber(String str) {final String Digits = "(\\p{Digit}+)";final String HexDigits  = "(\\p{XDigit}+)";final String Exp = "[eE][+-]?"+Digits;final String fpRegex = ("[\\x00-\\x20]*[+-]?(NaN|Infinity|((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|(\\.("+Digits+")("+Exp+")?)|"+ "(((0[xX]" + HexDigits + "(\\.)?)|(0[xX]" + HexDigits + "?(\\.)" + HexDigits + "))[pP][+-]?" + Digits + "))[fFdD]?))[\\x00-\\x20]*");if(Pattern.matches(fpRegex, str)) {return Double.valueOf(str);}return Double.NaN;}
	public static boolean checkIsNumber(String str) {final String Digits = "(\\p{Digit}+)";final String HexDigits  = "(\\p{XDigit}+)";final String Exp = "[eE][+-]?"+Digits;final String fpRegex = ("[\\x00-\\x20]*[+-]?(NaN|Infinity|((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|(\\.("+Digits+")("+Exp+")?)|"+ "(((0[xX]" + HexDigits + "(\\.)?)|(0[xX]" + HexDigits + "?(\\.)" + HexDigits + "))[pP][+-]?" + Digits + "))[fFdD]?))[\\x00-\\x20]*");if(Pattern.matches(fpRegex, str)) {return true;}return false;}
	public static String log(String msg) {msg = stripColorCodes(pluginName) + msg;logger.log(Level.ALL, msg);return msg;}public static String log(Level level, String msg) {msg = stripColorCodes(msg);if(level != null) {logger.log(level, msg);return msg;}return log(msg);}public static String log(String msg, Level level) {msg = stripColorCodes(msg);if(level != null) {logger.log(level, msg);return msg;}return log(msg);}
	public static String replaceChatColorWithHtmlFont(String str) {
		String result = "";
		boolean made_a_change = false;
		//log("\"str\" string: \"" + str + "\"");
		int x = 0;
		for(String curFontCode : ChatColorFontList) {
			int y = 0;
			for(String htmlTagStart : ChatColorHtmlFontStart) {
				int z = 0;
				for(String htmlTagEnd : ChatColorHtmlFontEnd) {
					if(x == y && x == z && y == z && str.contains(String.valueOf("\u00A7") + curFontCode)) {
						result = "";
						String ptrn = "(?i)(\u00A7[" + curFontCode.toUpperCase() + "])+";//String ptrn = "(?i)\u00A7[" + curFontCode.toUpperCase() + "";
						if(Pattern.compile(ptrn).matcher(str).replaceFirst(htmlTagStart).equals(str) == false) {
							result = Pattern.compile(ptrn).matcher(str).replaceFirst(htmlTagStart) + htmlTagEnd;
							made_a_change = true;
							if(curFontCode.equalsIgnoreCase("r")) {
								spanEndTagCount++;
							}
						}
						//if(result.equals("") == false) {log("Debug: \"Result\" string: \"" + result + "\"");}
					}
					z++;
					if(made_a_change) {break;}
				}
				y++;
				if(made_a_change) {break;}
			}
			x++;
			if(made_a_change) {break;}
		}
		if(made_a_change == true) {return result;}
		return str;
	}
	public static String replaceChatColorWithHtmlColor(String str) {
		boolean made_a_change = false;
		String result = "";
		int x = 0;
		for(String curCharCode : ChatColorCharList) {
			int y = 0;
			for(String curHtmlCode : ChatColorHtmlList) {
				if(x == y && str.contains(String.valueOf("\u00A7") + curCharCode)) {
					result = "";
					String ptrn = "(?i)(\u00A7[" + curCharCode.toUpperCase() + "])+";//String ptrn = "(?i)\u00A7[" + curCharCode.toUpperCase() + "]+";
					if(Pattern.compile(ptrn).matcher(str).replaceFirst("<span style=\"" + curHtmlCode + "\">").equals(str) == false) {
						made_a_change = true;
						result = Pattern.compile(ptrn).matcher(str).replaceFirst("<span style=\"" + curHtmlCode + "\">");spanEndTagCount++;
					}
				}
				y++;
				if(made_a_change) {break;}
			}
			x++;
			if(made_a_change) {break;}
		}
		if(made_a_change) {return result;}
		return str;
	}
	public static String convertTextToHtmlFormat(String str) {
		String result = str;
		result = result.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		return result;
	}
	private static String initializeFontConversion(String result, String msg) {
		//log("DEBUG: START OF FONT CONVERSION");
		String pattern2 = "(?i)\u00A7[K-OR]";
		for(String param : result.split("\u00A7")) {
			//Place this in the section with the comment labled 'FONT' to have the fonts reset after every color code, like with vanilla minecraft server ChatColor.
			if(!(param.equals("")) && msg.length() >= 2) {
				if(msg.contains(String.valueOf("\u00A7"))) {param = "\u00A7" + param;/*log("Test: added \u00A7 to the front of the param.");*/}// else {log("Test: did NOT add \u00A7 to the front of the param.");}
				Pattern p2 = Pattern.compile(pattern2);
				Matcher m2 = p2.matcher(param.substring(0, 2));
				if(m2.matches()) {
					lastFontCode = m2.group();
					result = result.replace(param, replaceChatColorWithHtmlFont(param));
					//log("!!!!!! ----> \"result\" string from Font formatter: \"" + result + "\"");
				}
			}
		}
		//log("DEBUG: END OF FONT CONVERSION");
		return result;
	}
	public static String convertChatColorCodesToHtml(String msg, int fontSize) {
		//if(chatColorToHtmlDebounce == true) {return msg;}
		//chatColorToHtmlDebounce = true;
		spanEndTagCount = 0;
		String result = msg;
		lastColorCode = "";
		lastFontCode = "";
		//log("DEBUG: START OF COLOR CONVERSION");
		String pattern1 = "(?i)\u00A7[0-9A-F]";
		for(String param : msg.split("\u00A7")) {
			if(!(param.equals("")) && msg.length() >= 2) {
				if(msg.contains(String.valueOf("\u00A7"))) {param = "\u00A7" + param;/*log("Test: added \u00A7 to the front of the param.");*/}// else {log("Test: did NOT add \u00A7 to the front of the param.");}
				Pattern p1 = Pattern.compile(pattern1);
				Matcher m1 = p1.matcher(param.substring(0, 2));
				if(m1.matches()) {
					lastColorCode = m1.group();
					result = result.replace(param, replaceChatColorWithHtmlColor(param));
					//log("!!!!!! ----> \"result\" string from Color Code formatter: \"" + result + "\"");
				} else if(fontEndsWithNextColor == true) {result = initializeFontConversion(result, msg);}
			}
		}
		//log("DEBUG: END OF COLOR CONVERSION");
		if(fontEndsWithNextColor == false) {result = initializeFontConversion(result, msg);}
		//log("DEBUG: FINAL RESULTING STRING: \n" + result);
		//chatColorToHtmlDebounce = false;
		String addSpanEndTags = "";
		int spanIterator = 1;
		do{addSpanEndTags = addSpanEndTags + "</span>";spanIterator++;}while(spanIterator <= spanEndTagCount);
		return "<font size="+fontSize+">"+result.trim()+addSpanEndTags+"</font><br>";
	}
	public static String logChat(final String ChatMsg, final String dataFolderName, final String fileName, final int fontSize) {
		FileMgmt.WriteToFile(fileName, "		" + convertChatColorCodesToHtml(convertTextToHtmlFormat(getSystemTime(false) + ChatMsg).trim(), fontSize), false, "", dataFolderName);
		return ChatMsg;
	}
	public static void onRconEvent(RemoteServerCommandEvent evt) {rcon = evt.getSender();}
	public static boolean hasRconConnectedYet() {return (rcon != null);}
	public static CommandSender getRcon() {if(hasRconConnectedYet()) {return rcon;}return null;}
	public static String sendConsoleMessage(String message) {
		if(message == null || message.isEmpty()) return "";
		message = formatColorCodes(message);
		if(message.contains("&z") || message.contains("&Z")) {
			String[] msgs = message.split("(?i)&z");
			for (String msg: msgs){
				console.sendMessage(msg.replaceAll("(?i)&z", "").trim());
			}
			return message.trim();
		}
		console.sendMessage(message.trim());
		return message.trim();
	}
	public static String sendMessage(Player target, String message) {
		if(message == null || message.isEmpty() || target == null || !(target != null)) return "";
		message = formatColorCodes(message);
		if(message.contains("&z")) {
			String[] msgs = message.split("&z");
			for (String msg: msgs){
				target.sendMessage(msg.replaceAll("&z", "").trim());
			}
			return message.trim();
		}
		target.sendMessage(message.trim());
		return message.trim();
	}
	public static String sendMessage(CommandSender target, String message) {
		if(message == null || message.isEmpty() || target == null || !(target != null)) return "";
		message = formatColorCodes(message);
		if(message.contains("&z")) {
			String[] msgs = message.split("&z");
			for (String msg: msgs){
				target.sendMessage(msg.replaceAll("&z", "").trim());
			}
			return message;
		}
		target.sendMessage(message.trim());
		return message.trim();
	}
	public static String fixPluralWord(int number, String word) {
		// TODO Auto-generated method stub
		String newWord = "";
		if(number != 1) {
			if(word.equalsIgnoreCase("those") || word.equalsIgnoreCase("that")) {
				return "those";
			}
			if(word.length() <= 3 && word.equalsIgnoreCase("is")) {
				word = "are";
				return word;
			}
			if(word.length() >= 4) {
				String beginningOfWord = word.substring(0, word.length() - 3);
				String endOfWord = word.substring(word.length() - 3, word.length());
				List<String> suffixes = new ArrayList<String>();
				suffixes.add("x");
				suffixes.add("sh");
				suffixes.add("ch");
				suffixes.add("z");
				suffixes.add("ss");
				Iterator<String> it = suffixes.iterator();
				boolean replacedSuffix = false;
				while(it.hasNext()) {
					if(endOfWord.contains(it.next()) && replacedSuffix == false) {
						endOfWord = endOfWord + "es";
						replacedSuffix = true;
					}
				}
				if(replacedSuffix == true) {
					newWord = beginningOfWord + endOfWord;
					return newWord;
				}
				return word + "s";
			}
			return word;
		} else if(word.equalsIgnoreCase("are")) {
			word = "is";
			return word;
		} else if(word.equalsIgnoreCase("those") || word.equalsIgnoreCase("that")) {
			return "that";
		} else {
			if(word.endsWith("s") && word.length() >= 2) {
				return word.substring(0, word.length() - 1);
			}
			return word;
		}
	}
	public static boolean showDebugMsg(String str, boolean Override) {
		if(Override == true) {
			sendConsoleMessage(str);
			return true;
		}
		return false;
	}
	public static String replaceWord(String str, String regex, String replacement, boolean case_sensitive, String dataFolderName) {
		try {
			if(case_sensitive == true) {
				return str.replaceAll("\\b" + regex + "\\b", replacement);
			}
			return str.replaceAll("(?i)\\b" + regex + "\\b", replacement);
		} catch(PatternSyntaxException e) {
			FileMgmt.LogCrash(e, "replaceWord(\"" + str + "\", \"" + regex + "\", \"" + replacement + "\", " + case_sensitive + ")", "A bad regex was used.", false, dataFolderName);
			showDebugMsg("A bad regex was used when running function \"replaceWord()\". Check the server log or \"" + dataFolderName + "\\crash-reports.txt\" to solve the problem.", false);
		}
		return str;
	}
	public static String GrammarEnforcement(String msg, Player chatter, String dataFolderName) {
		try {
			if(msg.length() >= 1) {msg = msg.substring(0, 1).toUpperCase() + msg.substring(1, msg.length());}
			msg = replaceWord(msg, "(\\w+)(\\s+\\1)+", "$1", false, dataFolderName);
			msg = replaceWord(msg, "(\\w+)(\\.*\\4)+", "$1", false, dataFolderName);
			/**/msg = replaceWord(msg, "\\p{Alnum}\\1{4,}", "$1", false, dataFolderName);
			//msg = msg.replaceAll("\\w+(\\.\\w+)+", "");//This SHOULD get rid of spammy letters like helloooooooooooooooo, but I'm not sure...
			msg = replaceWord(msg, "lol", "*Laughs out loud*", false, dataFolderName);
			msg = replaceWord(msg, "Jk", "Just joking", true, dataFolderName);
			msg = replaceWord(msg, "jk", "just joking", false, dataFolderName);
			msg = replaceWord(msg, "Np", "No problem", true, dataFolderName);
			msg = replaceWord(msg, "np", "no problem", false, dataFolderName);
			msg = replaceWord(msg, "Wtf", "What the-", true, dataFolderName);
			msg = replaceWord(msg, "wtf", "what the-", false, dataFolderName);
			msg = replaceWord(msg, "Wtc", "What the heck", true, dataFolderName);
			msg = replaceWord(msg, "wtc", "what the heck", false, dataFolderName);
			msg = replaceWord(msg, "Wth", "What the heck", true, dataFolderName);
			msg = replaceWord(msg, "wth", "what the heck", false, dataFolderName);
			msg = replaceWord(msg, "lmao", "*Laughing my butt off*", false, dataFolderName);
			msg = replaceWord(msg, "lmfao", "*Laughing my frekin' butt off*", false, dataFolderName);
			msg = replaceWord(msg, "ikr", "I know, right", false, dataFolderName);
			msg = replaceWord(msg, "idk", "I don't know", false, dataFolderName);
			msg = replaceWord(msg, "U", "You", true, dataFolderName);
			msg = replaceWord(msg, "u", "you", false, dataFolderName);
			msg = replaceWord(msg, "i", "I", true, dataFolderName);
			msg = replaceWord(msg, "Budder", "Gold", true, dataFolderName);//XD
			msg = replaceWord(msg, "budder", "gold", false, dataFolderName);//XD
			msg = replaceWord(msg, "Buder", "Gold", true, dataFolderName);//XD
			msg = replaceWord(msg, "buder", "gold", false, dataFolderName);//XD
			msg = replaceWord(msg, "Brb", "Be right back", true, dataFolderName);
			msg = replaceWord(msg, "brb", "be right back", false, dataFolderName);
			msg = replaceWord(msg, "Gtg", "Got to go", true, dataFolderName);
			msg = replaceWord(msg, "gtg", "got to go", false, dataFolderName);
			msg = replaceWord(msg, "Liek", "Like", true, dataFolderName);
			msg = replaceWord(msg, "liek", "like", false, dataFolderName);
			msg = replaceWord(msg, "Y", "Why", true, dataFolderName);
			msg = replaceWord(msg, "y", "why", false, dataFolderName);
			msg = replaceWord(msg, "Plz", "Please", true, dataFolderName);
			msg = replaceWord(msg, "plz", "please", false, dataFolderName);
			msg = replaceWord(msg, "Pzl", "Please", true, dataFolderName);
			msg = replaceWord(msg, "pzl", "please", false, dataFolderName);
			msg = replaceWord(msg, "Yo", "Hey", true, dataFolderName);
			msg = replaceWord(msg, "yo", "hey", false, dataFolderName);
			msg = replaceWord(msg, "http://", "", false, dataFolderName);
			msg = replaceWord(msg, "https://", "", false, dataFolderName);
			//msg = replaceWord(msg, "(?i)(\\w+)(\\s+\\4)+", "$1", true, dataFolderName);
			msg = replaceWord(msg, "Gangsta", "Gang member", true, dataFolderName);
			msg = replaceWord(msg, "gangsta", "gang member", false, dataFolderName);
			msg = replaceWord(msg, "Gangster", "Gang member", true, dataFolderName);
			msg = replaceWord(msg, "gangster", "gang member", false, dataFolderName);
			msg = replaceWord(msg, "Wut", "What", true, dataFolderName);
			msg = replaceWord(msg, "wut", "what", false, dataFolderName);
			msg = replaceWord(msg, "Wat", "What", true, dataFolderName);
			msg = replaceWord(msg, "wat", "what", false, dataFolderName);
			msg = replaceWord(msg, "ik", "I know", false, dataFolderName);
			msg = replaceWord(msg, "gotta", "have to", false, dataFolderName);
			msg = replaceWord(msg, "K", " Okay", true, dataFolderName);
			msg = replaceWord(msg, "k", " okay", false, dataFolderName);
			msg = replaceWord(msg, "ill", "I'll", false, dataFolderName);
			msg = replaceWord(msg, "Shure", "Sure", true, dataFolderName);
			msg = replaceWord(msg, "shure", "sure", false, dataFolderName);
			msg = replaceWord(msg, "KK", "Okay, I understand", true, dataFolderName);
			msg = replaceWord(msg, "Kk", "Okay, I understand", true, dataFolderName);
			msg = replaceWord(msg, "kk", "okay, I understand", false, dataFolderName);
			msg = replaceWord(msg, "Nvm", "Nevermind", true, dataFolderName);
			msg = replaceWord(msg, "nvm", "nevermind", false, dataFolderName);
			msg = replaceWord(msg, "Wanna", "Want to", true, dataFolderName);
			msg = replaceWord(msg, "wanna", "want to", false, dataFolderName);
			msg = replaceWord(msg, "Gimme", "Give me", true, dataFolderName);
			msg = replaceWord(msg, "gimme", "give me", false, dataFolderName);
			msg = replaceWord(msg, "Sec", "Second", true, dataFolderName);
			msg = replaceWord(msg, "sec", "second", false, dataFolderName);
			msg = replaceWord(msg, "Promo", "Promotion", true, dataFolderName);
			msg = replaceWord(msg, "promo", "promotion", false, dataFolderName);
			msg = replaceWord(msg, "Yah", "Yes", true, dataFolderName);
			msg = replaceWord(msg, "yah", "yes", false, dataFolderName);
			msg = replaceWord(msg, "Ya", "You", true, dataFolderName);
			msg = replaceWord(msg, "ya", "you", false, dataFolderName);
			msg = replaceWord(msg, "im", "I'm", true, dataFolderName);
			msg = replaceWord(msg, "imma", "I'll", true, dataFolderName);
			msg = replaceWord(msg, "Yur", "Your", true, dataFolderName);
			msg = replaceWord(msg, "yur", "your", false, dataFolderName);
			msg = replaceWord(msg, "bc", "because", false, dataFolderName);
			msg = replaceWord(msg, "Dont", "Don't", true, dataFolderName);
			msg = replaceWord(msg, "dont", "don't", false, dataFolderName);
			msg = replaceWord(msg, "L8ter", "Later", true, dataFolderName);
			msg = replaceWord(msg, "l8ter", "later", false, dataFolderName);
			msg = replaceWord(msg, "Gg", "Good game", true, dataFolderName);
			msg = replaceWord(msg, "GG", "Good game", true, dataFolderName);
			msg = replaceWord(msg, "gg", "good game", false, dataFolderName);
			msg = replaceWord(msg, "Teh", "The", true, dataFolderName);
			msg = replaceWord(msg, "teh", "the", false, dataFolderName);
			msg = replaceWord(msg, "pussy cat", "^%GH*D@", true, dataFolderName);//Workaround
			msg = replaceWord(msg, "Pussy cat", "^%GH*D#", true, dataFolderName);//Workaround
			msg = replaceWord(msg, "Pussy", "Wimp", true, dataFolderName);
			msg = replaceWord(msg, "pussy", "wimp", false, dataFolderName);
			msg = replaceWord(msg, "^%GH*D@", "pussy cat", true, dataFolderName);//Workaround
			msg = replaceWord(msg, "^%GH*D#", "Pussy cat", true, dataFolderName);//Workaround
			msg = replaceWord(msg, "Rofl", "*Rolling on the floor*", true, dataFolderName);
			msg = replaceWord(msg, "rofl", "*rolling on the floor*", false, dataFolderName);
			msg = replaceWord(msg, "Roflol", "*Rolling on the floor laughing out loud*", true, dataFolderName);
			msg = replaceWord(msg, "roflol", "*rolling on the floor laughing out loud*", false, dataFolderName);
			msg = replaceWord(msg, "Omg", "Oh my gosh", true, dataFolderName);
			msg = replaceWord(msg, "omg", "oh my gosh", false, dataFolderName);
			msg = replaceWord(msg, "Wierd", "Weird", true, dataFolderName);
			msg = replaceWord(msg, "wierd", "weird", false, dataFolderName);
			msg = replaceWord(msg, "Nm", "Not much", true, dataFolderName);
			msg = replaceWord(msg, "nm", "not much", false, dataFolderName);
			msg = replaceWord(msg, "Min", "Minute", true, dataFolderName);
			msg = replaceWord(msg, "min", "minute", false, dataFolderName);
			msg = replaceWord(msg, "mc", "Minecraft", false, dataFolderName);
			msg = replaceWord(msg, "Tbh", "To be honest", true, dataFolderName);
			msg = replaceWord(msg, "tbh", "to be honest", false, dataFolderName);
			msg = replaceWord(msg, "Btw", "By the way", true, dataFolderName);
			msg = replaceWord(msg, "btw", "by the way", false, dataFolderName);
			msg = replaceWord(msg, "Cya", "See you",true, dataFolderName);
			msg = replaceWord(msg, "cya", "see you", false, dataFolderName);
			msg = replaceWord(msg, "Atm", "At the moment", true, dataFolderName);
			msg = replaceWord(msg, "atm", "at the moment", true, dataFolderName);
			msg = replaceWord(msg, "Imo", "In my opinion", true, dataFolderName);
			msg = replaceWord(msg, "imo", "in my opinion", false, dataFolderName);
			msg = replaceWord(msg, "Dood", "Dude", true, dataFolderName);
			msg = replaceWord(msg, "dood", "dude", false, dataFolderName);
			msg = replaceWord(msg, "Dude", "Man", true, dataFolderName);
			msg = replaceWord(msg, "dude", "man", false, dataFolderName);
			msg = replaceWord(msg, "Woot", "Hurrah", true, dataFolderName);
			msg = replaceWord(msg, "woot", "hurrah", false, dataFolderName);
			/*msg = replaceWord(msg, "", "", true, dataFolderName);
			msg = replaceWord(msg, "", "", false, dataFolderName);*/
			msg = msg.replaceAll("(?i)\\bbud der\\b", "gold");
		} catch (ArrayIndexOutOfBoundsException e) {FileMgmt.LogCrash(e, "GrammarEnforcement()", "A bad regex was used in the function \"replaceWord(String msg, String regex, String replacement, boolean case_sensitive)\"!", true, dataFolderName);}
		return msg.trim();
	}
	/**public static String unSpecifiedVarWarning(final String warningVar, final String pluginName) {<br>
	 * 	sendConsoleMessage(pluginName + "Warning! \"" + warningVar + "\" was not specified in the config.yml! Has the config.yml been updated from a past version?");<br>
	 * 	return warningVar;<br>
	 * }
	 * @param warningVar
	 * @param pluginName
	 * @return String warningVar*/
	public static String unSpecifiedVarWarning(final String warningVar, final String fileName, final String pluginName) {
		sendConsoleMessage(pluginName + "&eWarning! \"" + warningVar + "\" was not specified in the \"" + fileName + "\" file! Has the existing \"" + fileName + "\" file not been updated from a past version?");
		return warningVar;
	}
	/**Sends the specified target the message if the message hasn't been sent before during this server session. If no target is specified, or the target that is specified does not exist, then this function broadcasts the parameter str.
	 * @param str String
	 * @param target String
	 * @return True if a message was sent, false otherwise.*/
	public static boolean sendOneTimeMessage(String str, String target) {
		// TODO Auto-generated method stub
		if(str.equals("") || str.equals(null) || str == null) {return false;}
		boolean hasMessageBeenSentBefore = false;
		for(String curMsg : oneTimeMessageList) {
			if(str.equalsIgnoreCase(curMsg)) {
				hasMessageBeenSentBefore = true;
				break;
			}
		}
		if(hasMessageBeenSentBefore == false) {
			Player plyr = Bukkit.getServer().getPlayer(target);
			if(plyr != null) {
				sendMessage(plyr, str);
			} else if(target.equalsIgnoreCase("console") || target.equals("!")) {
				sendConsoleMessage(str);
			} else {
				Bukkit.getServer().broadcastMessage(formatColorCodes(str));
			}
			return true;
		}
		return false;
	}
	
}