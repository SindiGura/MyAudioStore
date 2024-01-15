
//Name: Sindi Gurakuqi
//ID: 501197737

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.lang.model.util.ElementScanner6;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your library
		AudioContentStore store = new AudioContentStore();
		
		// Create my music library
		Library library = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				library.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				library.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				library.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				library.listAllPlaylists(); 
			}


			// ASSIGNMENT 2

			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{
				int toIndex = 0; // to and from indexes 
				int fromIndex = 0;
				
				System.out.print("From Store Content #: "); // asks for index range

				if (scanner.hasNextInt())
				{
					fromIndex = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}

				
				System.out.print("To Store Content #: ");

				if (scanner.hasNextInt())
				{
					toIndex = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}

				if ((toIndex < 1 || toIndex > 12) && (fromIndex < 1 || fromIndex > 12))  // checks range 
				{
					System.out.println("Index out of Range");
				}

				else
				{
					for(int i = fromIndex; i < (toIndex+1); i++) // downloads all content within the range 
					{
						AudioContent content = store.getContent(i);

						if (content == null)
							System.out.println("Content Not Found in Store");
						else {
							library.download(content);
						}
					}
				}
				
				
									
			}
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				int index = 0;

				System.out.print("Song Number: ");

				try
				{
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						// consume the nl character since nextInt() does not
						scanner.nextLine(); 
					}

					library.playSong(index);
				}

				catch (AudioContentNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
				
				
			}
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				int index = 0;

				System.out.print("Audio Book Number: ");

				try 
				{
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine();
					}

					library.printAudioBookTOC(index);
				}
				
				catch (AudioContentNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
				
			}
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				int index = 0;

				try
				{
					System.out.print("Audio Book Number: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
					}
					int chapter = 0;
					System.out.print("Chapter: ");
					if (scanner.hasNextInt())
					{
						chapter = scanner.nextInt();
						scanner.nextLine();
					}

					library.playAudioBook(index, chapter);
				}

				catch (AudioContentNotFoundException e)
				{
					System.out.println(e.getMessage());
				}

				
					
			}
			
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				String title = "";


				try
				{
					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
					{
						title = scanner.nextLine();
					}

					library.playPlaylist(title);
				}

				catch (AudioContentNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
				

				
			}
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				String title = "";
        		int index = 0;

				try
				{
					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
					{
						title = scanner.nextLine();
					}
					System.out.print("Content Number: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine();
					}

					library.playPlaylist(title, index);
				}

				catch (AudioContentNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
        
				
				
			}
			// Delete a song from the library and any play lists it belongs to
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				int songNum = 0;


				try
				{
					System.out.print("Library Song #: ");
					if (scanner.hasNextInt())
					{
						songNum = scanner.nextInt();
						scanner.nextLine();
					}

					library.deleteSong(songNum);
				}
				
				catch (InvalidIndexException e)
				{
					System.out.println(e.getMessage());
				}
			}
			
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				String title = "";

				try
				{	
					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
					{
						title = scanner.nextLine();
					}

					library.makePlaylist(title);
				}
				
				catch (AudioContentAlreadyFound e)
				{
					System.out.println(e.getMessage());	
				}
					
			}
			
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				String title = "";

				try
				{
					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
					{
						title = scanner.nextLine();
					}
					library.printPlaylist(title);
				
				}

				catch (AudioContentNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
				

			}
			// Add content from library (via index) to a playlist
			
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				int contentIndex = 0;
				String contentType = "";
        		String playlist = "";
        
				try
				{
					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
						playlist = scanner.nextLine();
			
					System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
					if (scanner.hasNextLine())
						contentType = scanner.nextLine();
					
					System.out.print("Library Content #: ");
					if (scanner.hasNextInt())
					{
						contentIndex = scanner.nextInt();
						scanner.nextLine(); // consume nl
					}

					library.addContentToPlaylist(contentType, contentIndex, playlist);
				}

				catch (AudioContentNotFoundException e)
				{
					System.out.println(e.getMessage());
				}	
        		
				
				
			}
			// Delete content from play list
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				int contentIndex = 0;
				String playlist = "";

				try
				{
					System.out.print("Playlist Title: ");
					if (scanner.hasNextLine())
						playlist = scanner.nextLine();
					
					System.out.print("Playlist Content #: ");
					if (scanner.hasNextInt())
					{
						contentIndex = scanner.nextInt();
						scanner.nextLine(); // consume nl
					}

					library.delContentFromPlaylist(contentIndex, playlist);
				}

				catch (AudioContentNotFoundException e)
				{
					System.out.println(e.getMessage());
				}	

			
			}
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				library.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				library.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				library.sortSongsByLength();
			}
			

			// ASSIGNMENT 2

			else if(action.equalsIgnoreCase("SEARCH")) 
			{

				String title = ""; 
  
				System.out.print("Title: "); // Asks user for title 
				if (scanner.hasNextLine()) // Consumes nl
				{
					title = scanner.nextLine();
				}

				store.search(title); // calls method 

			}

			// ASSIGNMENT 2

			else if(action.equalsIgnoreCase("SEARCHA"))
			{

				String artist = ""; 

				System.out.print("Artist: "); // asks for artist 
				if (scanner.hasNextLine()) // consumes nl
				{
					artist = scanner.nextLine();
				}

				store.searchA(artist); // calls method 

			}

			// ASSIGNMENT 2

			else if(action.equalsIgnoreCase("SEARCHG"))
			{

				String genre = "";

				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: "); // asks for genre
				if (scanner.hasNextLine()) // consumes nl 
				{
					genre = scanner.nextLine();
				}

				store.searchG(genre); // calls method 

			}


			// ASSIGNMENT 2

			else if(action.equalsIgnoreCase("DOWNLOADA"))
			{

				String artist = ""; 

				System.out.print("Artist:  "); // asks for artist 
				
				if (scanner.hasNextLine()) // consumes nl 
				{
					artist = scanner.nextLine();
				}

				
				ArrayList<AudioContent> toDownload = store.downloadA(artist); // Calls method to find the appropriate content to download
				
				for(int i = 0; i<toDownload.size(); i++) // downloads content 
				{
					library.download(toDownload.get(i));
				}

			}

			// ASSIGNMENT 2

			else if(action.equalsIgnoreCase("DOWNLOADG"))
			{

				String genre = "";

				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");  // asks for genre 
				
				if (scanner.hasNextLine())
				{
					genre = scanner.nextLine(); // consumes nl
				}

				
				ArrayList<AudioContent> toDownload = store.downloadG(genre); // Calls method to find the appropriate content to download
				
				for(int i = 0; i<toDownload.size(); i++) // downloads content 
				{
					library.download(toDownload.get(i));
				}

			}

			// ASSIGNMENT 2 BONUS

			else if(action.equalsIgnoreCase("SEARCHP"))
			{

				String target = "";

				System.out.print("Enter target: "); // asks for target 
				
				if (scanner.hasNextLine()) // consumes next line 
				{
					target = scanner.nextLine();
				}

				store.searchP(target); // calls method 
			}

		
			System.out.print("\n>");
		}
	}
}
