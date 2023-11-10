import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Artist {
	private String ID; 
	private String Name; 
	private String Address; 
	private String Birthdate; 
	private String Bio; 
	private ArrayList <String> Occupations; 
	private ArrayList <String> Genres;
	private ArrayList <String> Awards;

	public Artist (String id, String name, String address, String birthdate, String bio, 
		ArrayList <String> occupations,  ArrayList <String> genres, ArrayList <String> awards)
{
	ID=id;
	Name=name;
	Birthdate=birthdate;
	Occupations=occupations;
	Genres=genres;
	Awards=awards;
	
}
	public static boolean isValidArtist(String ID, String birthdate, String address, String bio, ArrayList<String> occupations, ArrayList<String> awards, ArrayList<String> genres) {
	        return isValidArtistID(ID) &&
	               isValidBirthDate(birthdate) &&
	               isValidAddress(address) &&
	               isValidBioLength(bio) &&
	               isValidOccupations(occupations) &&
	               isValidAwards(awards) &&
	               isValidMusicGenres(genres);
	    } // Checking if Artist meets all conditions 



	public boolean addArtist(String id, String name, String address, String birthdate, String bio, 
			ArrayList <String> occupations,  ArrayList <String> genres, ArrayList <String> awards){
		//addArtist if meets all conditions 
		if (isValidArtistID(ID) &&
            isValidBirthDate(Birthdate) &&
            isValidAddress(Address) &&
            isValidBioLength(Bio) &&
            isValidOccupations(Occupations) &&
            isValidAwards(Awards) &&
            isValidMusicGenres(Genres)){
			
		 try {
	            FileWriter fileWriter = new FileWriter("artist_info.txt", true); // Append mode
	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	            bufferedWriter.write(id + "," + name+ ","+ address + "," + birthdate + "," + bio + "," + occupations + "," + genres + "," + awards);
	            bufferedWriter.newLine();
	            bufferedWriter.close();
	            return true; // Add to the text file if it meets all condition
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false; // Error while writing to file
	        }
	    } else {
	        return false; // Validation failed
	    }
	}	
	
    public Artist(String ID, String birthdate, ArrayList<String> occupations, ArrayList<String> awards, String bio) {
        this.ID = ID;
        this.Birthdate = birthdate;
        this.Occupations = occupations;
        this.Awards = awards;
        this.Bio = bio;
    }


	
	public static boolean isValidArtistID(String id) {
	        // Checking if Artist ID is valid
		// Artist ID should be exactly 10 characters long; 
		//the first three characters should be numbers between 5 to 9, 
		// the characters 4th to 8th should be upper case letters (A - Z) 
		// and the last two characters should be a special character.
	        String artistIDPattern = "^[5-9]{3}[A-Z]{5}[!@#$%^&*()_+\\-=<>?]{2}$";
	        return id.matches(artistIDPattern);
	    }

	public static boolean isValidBirthDate(String birthdate) {
		// Checking if Birthdate is Valid 
		//The format of the birth date of the artist should follow the following format: DD-MM-YYYY
	        String birthDatePattern = "^\\d{2}-\\d{2}-\\d{4}$";
	        return birthdate.matches(birthDatePattern);
	    }

	public static boolean isValidAddress(String address) {
		// Checking if Address is valid 
		// The address of the artist should follow the following format: City|State|Country. 
	        String addressPattern = "^[A-Za-z]+\\|[A-Za-z]+\\|[A-Za-z]+$";
	        return address.matches(addressPattern);
	    }

	public static boolean isValidBioLength(String bio) {
		// Checking if Biolength is valid 
		// The bio of the artist should be between 10 to 30 words. 
			int wordCount = bio.split("\\s+").length;
			return wordCount >= 10 && wordCount <= 30;
	}
	

	public static boolean isValidOccupations(ArrayList<String> occupations) {
	        return occupations.size() >= 1 && occupations.size() <= 5;
	      // Checking if Occupation is valid
	        // An artist should have at least one occupation or a maximum of five occupations. 
	        
	    }

	public static boolean isValidAwards(ArrayList<String> awards) {
		// Checking if awards is valid 
			if (awards.size() > 3) {
				return false; // Artist can have a maximum of three awards.
			
        }

        for (String award : awards) {
            String[] parts = award.split(", ");
            if (parts.length != 2) {
                return false; // Award format should be "Year, Title".
            }

            String title = parts[1];
            int titleWordCount = title.split("\\s+").length;
            if (titleWordCount < 4 || titleWordCount > 10) {
                return false; // Title should be between 4 to 10 words.
            }
        }

        return true;
	    }

	public static boolean isValidMusicGenres(ArrayList<String> genres) {
		// Checking if music genre is valid 
	        if (genres.size() < 2 || genres.size() > 5) { // An artist should be active in at least two music genres and a maximum of five genres. 
	            return false;
	        }

	        boolean hasPop = false;
	        boolean hasRock = false;

	        for (String genre : genres) {
	            if (genre.equalsIgnoreCase("pop")) {
	                hasPop = true;
	            } else if (genre.equalsIgnoreCase("rock")) {
	                hasRock = true;
	                // cannot be active in 'pop' and 'rock' genres at the same time. 
	            }
	        }

	        return !(hasPop && hasRock);}
	
	 
	public boolean updateArtist(String updatedID, String updatedBirthdate, ArrayList<String> updatedOccupations, ArrayList<String> updatedAwards, String updatedBio) {
	            // Condition 1: Check constraints discussed for addArtist function.
	            if (isValidArtistID(updatedID) && isValidBirthDate(updatedBirthdate) &&
	                isValidOccupations(updatedOccupations) && isValidAwards(updatedAwards) && isValidBioLength(updatedBio)) {
	                
	                // Condition 2: If an artist was born before 2000, their occupation cannot be changed.
	                int birthYear = Integer.parseInt(Birthdate.split("-")[2]);
	                if (birthYear < 2000 && !updatedOccupations.equals(Occupations)) {
	                    return false;
	                }
	                
	                // Condition 3: Awards that were given to an artist before 2000 cannot be changed.
	                if (birthYear < 2000) {
	                    ArrayList<String> originalAwardsBefore2000 = getAwardsBefore2000(Awards);
	                    ArrayList<String> updatedAwardsBefore2000 = getAwardsBefore2000(updatedAwards);
	                    if (!originalAwardsBefore2000.equals(updatedAwardsBefore2000)) {
	                        return false;
	                    }
	                }
	                
	                String artistInfo = updatedID + "|" + updatedBirthdate + "|" +
                            String.join(",", updatedOccupations) + "|" +
                            String.join(",", updatedAwards) + "|" + updatedBio;
         
	                // Update the artist's information.
	                this.ID = updatedID;
	                this.Birthdate = updatedBirthdate;
	                this.Occupations = updatedOccupations;
	                this.Awards = updatedAwards;
	                this.Bio = updatedBio;
	                
	                
	                
	                // Save the updated artist to the TXT file 
	                try (BufferedWriter writer = new BufferedWriter(new FileWriter("artists.txt", true))) {
	                    writer.write(artistInfo);
	                    writer.newLine();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                

	                }
	                
	                return true;
	            }
	            return false;
	        }


	        public ArrayList<String> getAwardsBefore2000(ArrayList<String> awards) {
	            ArrayList<String> awardsBefore2000 = new ArrayList<>();
	            for (String award : awards) {
	                int year = Integer.parseInt(award.split(", ")[0]);
	                if (year < 2000) {
	                    awardsBefore2000.add(award);
	                }
	            }
	            return awardsBefore2000;
	            
	            
	        }
	    }


