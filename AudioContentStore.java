
//Name: Sindi Gurakuqi
//ID: 501197737

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
	private ArrayList<AudioContent> contents; 
	private Map<String, Integer> titleMap;
	private Map<String, ArrayList<Integer>> artistMap;
	private Map<String, ArrayList<Integer>> genreMap;
			
	// comment constructor 
	public AudioContentStore()
	{
		
		contents = readFile();

		titleMap = new HashMap<>();

		// Title map

		for (int i = 0; i < contents.size(); i++) 
		{
			AudioContent content = contents.get(i);
			String title = content.getTitle();
			titleMap.put(title, i);
		}

		// Create some songs audiobooks and podcasts and to store
		/* 
		String file = "Yesterday, all my troubles";
		contents.add(new Song("Yesterday", 1965, "123", Song.TYPENAME, file, 2, "The Beatles", "Paul McCartney", Song.Genre.POP, file));
		
		file = "I'm sorry if I seem uninterested\n"
				+ "Or I'm not listenin' or I'm indifferent\n"
				+ "Truly, I ain't got no business here\n"
				+ "But since my friends are here, I just came to kick it\n"
				+ "But really I would rather be at home all by myself not in this room\n"
				+ "With people who don't even care about my well being";
		contents.add(new Song("Here", 2015, "391", Song.TYPENAME, file, 3, "Alessia Cara", "Alessia Cara", Song.Genre.POP, file));
		
		file = "Yo, Big Shaq, the one and only\n"
				+ "Man's not hot, never hot\n"
				+ "Skrrat (GottiOnEm), skidi-kat-kat\n"
				+ "Boom\n"
				+ "Two plus two is four\n"
				+ "Minus one that's three, quick maths\n"
				+ "Everyday man's on the block\n"
				+ "Smoke trees (Ah)";
		contents.add(new Song("Man's Not Hot", 2017, "374", Song.TYPENAME, file, 2, "Michael Dapaah", "Michael Dapaah", Song.Genre.RAP, file));
		
		file = "The world was on fire and no one could save me but you\n"
				+ "It's strange what desire will make foolish people do\n"
				+ "I never dreamed that I'd meet somebody like you\n"
				+ "And I never dreamed that I'd lose somebody like you";
		contents.add(new Song("Wicked Game", 1989, "185", Song.TYPENAME, file, 4, "Chris Isaak", "Chris Isaak", Song.Genre.ROCK, file));
		
		file = "The lights go out and I can't be saved\n"
				+ "Tides that I tried to swim against\n"
				+ "Have brought me down upon my knees\n"
				+ "Oh, I beg, I beg and plead\n"
				+ "Singin' come out of things un said";
		contents.add(new Song("Clocks", 2002, "875", Song.TYPENAME, file, 5, "Coldplay", "Guy Berryman, Chris Martin", Song.Genre.ROCK, file));
		
		file = "I'm waking up to ash and dust\n"
				+ "I wipe my brow and I sweat my rust\n"
				+ "I'm breathing in the chemicals";
		contents.add(new Song("Radioactive", 2012, "823", Song.TYPENAME, file, 3, "Imagine Dragons", "Josh Mosser, A. Grant, Dan Reynolds, Wayne Sermon, Ben McKee", Song.Genre.ROCK, file));
		
		file = "Birds flying high\n"
				+ "You know how I feel\n"
				+ "Sun in the sky\n"
				+ "You know how I feel\n"
				+ "Breeze driftin' on by\n"
				+ "You know how I feel\n"
				+ "It's a new dawn\n"
				+ "It's a new day\n"
				+ "It's a new life\n"
				+ "For me";
		contents.add(new Song("Feelin' Good", 1965, "875", Song.TYPENAME, file, 3, "Nina Simone", 
				"Anthony Newley, Leslie Bricusse",Song.Genre.JAZZ, file));
		
		file = "Find table spaces, say your social graces\n"
				+ "Bow your head, they're pious here\n"
				+ "But you and I, we're pioneers, we make our own rules\n"
				+ "Our own room, no bias here";
		contents.add(new Song("Wild Things", 2015, "443", Song.TYPENAME, file, 4, "Alessia Cara", "Alessia Cara", Song.Genre.POP, file));
		
		AudioBook book = new AudioBook("Harry Potter and the Goblet of Fire", 2015, "894", AudioBook.TYPENAME,  "", 1236,
				"J.K. Rowling", "Jim Dale", makeHPChapterTitles(), makeHPChapters());
		contents.add(book);
		
		book = new AudioBook("Moby Dick", 2018, "376", AudioBook.TYPENAME,  "", 1422,
				"Herman Melville", "Pete Cross", makeMDChapterTitles(), makeMDChapters());
		contents.add(book);
		
		book = new AudioBook("Shogun", 2018, "284", AudioBook.TYPENAME,  "", 3213,
				"James Clavel", "Ralph Lister", makeSHChapterTitles(), makeSHChapters());
		contents.add(book);

		*/
	}





	// ASSIGNMENT 2

	public ArrayList<AudioContent> readFile()
	{
		try {

			// Examples of the parameters needed so that they could be filled in order 

			// (String title, int year, String id, String type, String audioFile, int length, String artist, String composer, Song.Genre genre, String lyrics)
			//contents.add(new Song("Yesterday", 1965, "123", Song.TYPENAME, file, 2, "The Beatles", "Paul McCartney", Song.Genre.POP, file));
			

			// Read file 

			File file = new File("store.txt"); 
			Scanner scanner = new Scanner(file);

			// Create new contents list 
			contents = new ArrayList<AudioContent>();

			while (scanner.hasNextLine()) {

				String type = scanner.nextLine().trim(); // consume next line 

				if (type.equals("SONG"))  // if this is a song 
				{

					// adds variables for all the required parameters 
					String id = scanner.nextLine().trim();
					String title = scanner.nextLine().trim();
					int year = Integer.parseInt(scanner.nextLine().trim());
					int length = Integer.parseInt(scanner.nextLine().trim());
					String artist = scanner.nextLine().trim();
					String composer = scanner.nextLine().trim();
					String genre = scanner.nextLine().trim();
					Song.Genre songGenre = Song.Genre.valueOf(genre);
					int numLines = Integer.parseInt(scanner.nextLine().trim());

					// adds all the lyrics to one variable 
					String lyrics = "";

					for (int i = 0; i < numLines; i++)
					{
						String line = scanner.nextLine().trim();
						lyrics += line + "\n";
					}

					// creates new object and adds it to the content
					AudioContent song = new Song(title, year, id, Song.TYPENAME, lyrics, length, artist, composer, songGenre, lyrics);
					contents.add(song);
				}

				// Examples of the parameters needed so that they could be filled in order 

				// ("Harry Potter and the Goblet of Fire", 2015, "894", AudioBook.TYPENAME,  "", 1236,
				//	"J.K. Rowling", "Jim Dale", makeHPChapterTitles(), makeHPChapters())

				// String title, int year, String id, String type, String audioFile, int length,
				// String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters)
				
				else if (type.equals("AUDIOBOOK")) // if this is an audiobook
				{

					// adds variables for all the required parameters 
					String id = scanner.nextLine().trim();
					String title = scanner.nextLine().trim();
					int year = Integer.parseInt(scanner.nextLine().trim());
					int length = Integer.parseInt(scanner.nextLine().trim());
					String author = scanner.nextLine().trim();
					String narrator = scanner.nextLine().trim();
					int numChapters = Integer.parseInt(scanner.nextLine().trim());

					ArrayList<String> chapterTitles = new ArrayList<String>();
					ArrayList<String> chapterLines = new ArrayList<String>();

					// adds all the chapter titles to an array
					for (int i = 0; i < numChapters; i++) 
					{
						String chapterTitle = scanner.nextLine().trim();
						chapterTitles.add(chapterTitle);
					}
					
					// adds all the chapters to an array
					for (int i = 0; i < numChapters; i++) 
					{
						int numChapterLines = Integer.parseInt(scanner.nextLine().trim());
						
						for (int j = 0; j < numChapterLines; j++) {
							String line = scanner.nextLine().trim();
							chapterLines.add(line);
						}
					}

					// creates new object and adds it to the content
					AudioContent audiobook = new AudioBook(title, year, id, AudioBook.TYPENAME,"", length, author, narrator, chapterTitles, chapterLines);
					contents.add(audiobook);
				}
			
			}

			

			scanner.close();

			return contents;

		}

		catch (FileNotFoundException e) 
		{
			System.err.println("File not found: " + e.getMessage());
			System.exit(1);
		}
		
		return null;

	}


	// ASSIGNMENT 2

	public void search(String title)
	{

		// Check if the title exists in the title map
		if (titleMap.containsKey(title)) 
		{
			int index = titleMap.get(title); // Get the index of the content in the contents list
			System.out.print("\n" + (index + 1) + ": "); // Print the index and the information of the content
			contents.get(index).printInfo();
		}
		else
		{
			// If the title does not exist in the title map, print a message 
			System.out.println("No matches for: " + title);
		}
	}

	// ASSINMENT 2

	public void searchA(String artist)
	{
		boolean found = false;

		// Get the list of content indices that match the given artist
		ArrayList<Integer> songIndices = getArtistMap().getOrDefault(artist, new ArrayList<>());
		
		// Loop through each content index and print the info for that content
		for (int index : songIndices) 
		{
			System.out.print("\n" + (index+1) + ": ");
			contents.get(index).printInfo();
			found = true;
		}

		// If no content is found for the given artist, print a message
		if(!found)
		{
			System.out.println("Content not found");
		}

	}

	// ASSIGNMENT 2

	public ArrayList<AudioContent> downloadA(String artist)
	{
		
		// Create an empty ArrayList to store AudioContent objects to download
		ArrayList<AudioContent> toDownload = new ArrayList<AudioContent>();
		
		// Get the indices of all the songs by the artist
		ArrayList<Integer> songIndices = getArtistMap().getOrDefault(artist, new ArrayList<>());
		
		// Add each song to the toDownload ArrayList
		for (int index : songIndices) 
		{
			toDownload.add(contents.get(index));
		}

		return toDownload;

	}


	// ASSIGNMENT 2

	public void searchG(String genre)
	{

		// Get the indices of all the songs with the given genre.
		ArrayList<Integer> genreIndices = getGenreMap().getOrDefault(genre, new ArrayList<Integer>());

		// If there are no songs with the given genre, print a message to the user.
		if (genreIndices.size() == 0) 
		{
			System.out.println("No songs found with genre " + genre);
		} 
		
		// Otherwise, print the information of each song with the given genre.
		else 
		{
			System.out.println("\nSongs with genre " + genre + ": ");
			for (int i : genreIndices) 
			{
				System.out.print("\n" + (i + 1) + ": ");
				contents.get(i).printInfo();
			}
    	}
	}

	// ASSIGNMENT 2

	public ArrayList<AudioContent> downloadG(String genre)
	{
		ArrayList<AudioContent> toDownload = new ArrayList<AudioContent>();
		// Get the indices of all the songs with the given genre. 
		ArrayList<Integer> genreIndices = getGenreMap().getOrDefault(genre, new ArrayList<Integer>());

		// Check if there are songs with the specified genre
		if (genreIndices.size() == 0) 
		{
			System.out.println("No songs found with genre " + genre);
		} 
		
		// If there are songs with the specified genre, add them to the list to download
		
		else 
		{
			for (int i : genreIndices) 
			{
				toDownload.add(contents.get(i));
			}
    	}

		return toDownload;
	}

	// ASSIGNMENT 2

	public Map<String,ArrayList<Integer>> getArtistMap()
	{
		
		artistMap = new HashMap<>();

		for (int i = 0; i < contents.size(); i++)  // loop through the content
		{

			String artist = "";

			// Check if the current content is a Song and get its artist
			if (contents.get(i) instanceof Song) {
				Song song = (Song) contents.get(i);
				artist = song.getArtist();
			}

			// Check if the current content is an AudioBook and get its author
			else if (contents.get(i) instanceof AudioBook) {
				AudioBook book = (AudioBook) contents.get(i);
				artist = book.getAuthor();
			}

	  
			// If the artist is not already in the HashMap, create a new ArrayList for its indices
			if (!artistMap.containsKey(artist)) {
			  artistMap.put(artist, new ArrayList<>());
			  
			}

			// Add the index of the current content to the ArrayList of the corresponding artist in the HashMap
			artistMap.get(artist).add(i);
			
		}

		

		return artistMap;

	}

	// ASSIGNMENT 2

	public Map<String, ArrayList<Integer>> getGenreMap() {

		genreMap = new HashMap<>();

		for (int i = 0; i < contents.size(); i++) // loop through the content 
		{
			// Check if the content at index i is a Song
			if (contents.get(i) instanceof Song) 
			{
				// Cast the content to a Song
				Song song = (Song) contents.get(i);
				// Get the genre of the Song as a String
				String genre = song.getGenre().name();

				// Check if the genre is already in the map
				if (!genreMap.containsKey(genre)) 
				{
					// If the genre is not in the map, add it with an empty ArrayList
					genreMap.put(genre, new ArrayList<>());
				}
				// Add the index of the song to the list of indices for the genre
				genreMap.get(genre).add(i);
			}
		}
		return genreMap;
	}


	// ASSIGNMENT 2 BONUS

	public void searchP(String target) {
		for (int i = 0; i < contents.size(); i++) // loop through the content
		{
			AudioContent audioContent = contents.get(i); // Get the current AudioContent object

			if (audioContent instanceof Song) { // Check if it is a Song
				Song song = (Song) audioContent;
				
				// Check if the information contains the target keyword
				if(song.songContains(target))
				{
					// If it does, print out the song information
					System.out.print("\n" + (i + 1) + ": ");
					audioContent.printInfo();
				}
				
			}

			else if (audioContent instanceof AudioBook) { // Check if it is an audiobook
				AudioBook book = (AudioBook) audioContent;

				// Check if the information contains the target keyword
				if(book.audioBookContains(target))
				{
					// If it does, print out the audiobook information
					System.out.print("\n" + (i + 1) + ": ");
					audioContent.printInfo();
				}

			}

		}
	}

	
	public AudioContent getContent(int index)
	{
		if (index < 1 || index > contents.size())
		{
			return null;
		}
		return contents.get(index-1);
	}
	
	public void listAll()
	{
		for (int i = 0; i < contents.size(); i++)
		{
			int index = i + 1;
			System.out.print(index + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
	}

	
	

	/*
		private ArrayList<String> makeHPChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("The Riddle House");
			titles.add("The Scar");
			titles.add("The Invitation");
			titles.add("Back to The Burrow");
			return titles;
		}
		
		private ArrayList<String> makeHPChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("In which we learn of the mysterious murders\n"
					+ "in the Riddle House fifty years ago, \n"
					+ "how Frank Bryce was accused but released for lack of evidence, \n"
					+ "and how the Riddle House fell into disrepair. ");
			chapters.add("In which Harry awakens from a bad dream, \n"
					+ "his scar burning, we recap Harry�s previous adventures, \n"
					+ "and he writes a letter to his godfather.");
			chapters.add("In which Dudley and the rest of the Dursleys are on a diet,\n"
					+ "and the Dursleys get letter from Mrs. Weasley inviting Harry to stay\n"
					+ "with her family and attend the World Quidditch Cup finals.");
			chapters.add("In which Harry awaits the arrival of the Weasleys, \n"
					+ "who come by Floo Powder and get trapped in the blocked-off fireplace,\n"
					+ "blast it open, send Fred and George after Harry�s trunk,\n"
					+ "then Floo back to the Burrow. Just as Harry is about to leave, \n"
					+ "Dudley eats a magical toffee dropped by Fred and grows a huge purple tongue. ");
			return chapters;
		}
		
		private ArrayList<String> makeMDChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("Loomings.");
			titles.add("The Carpet-Bag.");
			titles.add("The Spouter-Inn.");
			return titles;
		}
		private ArrayList<String> makeMDChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("Call me Ishmael. Some years ago�never mind how long precisely�having little\n"
					+ "or no money in my purse, and nothing particular to interest me on shore,\n"
					+ "I thought I would sail about a little and see the watery part of the world.");
			chapters.add("stuffed a shirt or two into my old carpet-bag, tucked it under my arm, \n"
					+ "and started for Cape Horn and the Pacific. Quitting the good city of old Manhatto, \n"
					+ "I duly arrived in New Bedford. It was a Saturday night in December.");
			chapters.add("Entering that gable-ended Spouter-Inn, you found yourself in a wide, \n"
					+ "low, straggling entry with old-fashioned wainscots, \n"
					+ "reminding one of the bulwarks of some condemned old craft.");
			return chapters;
		}
		
		private ArrayList<String> makeSHChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("");
			titles.add("");
			titles.add("");
			titles.add("");
			return titles;
		}
		
		private ArrayList<String> makeSHChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("The gale tore at him and he felt its bite deep within\n"
					+ "and he knew that if they did not make landfall in three days they would all be dead");
			chapters.add("Blackthorne was suddenly awake. For a moment he thought he was dreaming\n"
					+ "because he was ashore and the room unbelieveable");
			chapters.add("The daimyo, Kasigi Yabu, Lord of Izu, wants to know who you are,\n"
					+ "where you come from, how ou got here, and what acts of piracy you have committed.");
			chapters.add("Yabu lay in the hot bath, more content, more confident than he had ever been in his life.");
			return chapters;
		}
		
		// Podcast Seasons
		private ArrayList<Season> makeSeasons()
		{
			ArrayList<Season> seasons = new ArrayList<Season>();
		  Season s1 = new Season();
		  s1.episodeTitles.add("Bay Blanket");
		  s1.episodeTitles.add("You Don't Want to Sleep Here");
		  s1.episodeTitles.add("The Gold Rush");
		  s1.episodeFiles.add("The Bay Blanket. These warm blankets are as iconic as Mariah Carey's \n"
		  		+ "lip-syncing, but some people believe they were used to spread\n"
		  		+ "smallpox and decimate entire Indigenous communities. \n"
		  		+ "We dive into the history of The Hudson's Bay Company and unpack the\n"
		  		+ "very complicated story of the iconic striped blanket.");
		  s1.episodeFiles.add("There is no doubt that the Klondike Gold Rush was an iconic event. \n"
		  		+ "But what did the mining industry cost the original people of the territory? \n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.episodeFiles.add("here is no doubt that the Klondike Gold Rush was an iconic event. \n"
		  		+ "But what did the mining industry cost the original people of the territory? \n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.episodeLengths.add(31);
		  s1.episodeLengths.add(32);
		  s1.episodeLengths.add(45);
		  seasons.add(s1);
		  Season s2 = new Season();
		  s2.episodeTitles.add("Toronto vs Everyone");
		  s2.episodeTitles.add("Water");
		  s2.episodeFiles.add("There is no doubt that the Klondike Gold Rush was an iconic event. \n"
		  		+ "But what did the mining industry cost the original people of the territory? \n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s2.episodeFiles.add("Can the foundation of Canada be traced back to Indigenous trade routes?\n"
		  		+ "In this episode Falen and Leah take a trip across the Great Lakes, they talk corn\n"
		  		+ "and vampires, and discuss some big concerns currently facing Canada's water."); 
		  s2.episodeLengths.add(45);
		  s2.episodeLengths.add(50);
		 
		  seasons.add(s2);
		  return seasons;
		}

		*/
}
