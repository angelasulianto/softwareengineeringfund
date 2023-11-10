import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ArtistTest {

	@Test
	void testValidID() {
		assertTrue(Artist.isValidArtistID("589ACMSZ@#"));
		assertTrue(Artist.isValidArtistID("679BDNYZ#_"));
	    }
	// ArtistID is valid with the two data because it is exactly 10 characters long; the first three characters should be numbers between 5 to 9, the characters 4th to 8th should be upper case letters (A - Z) and the last two characters should be a special character. 
	
	@Test 
	void testInvalidID() {
		assertFalse(Artist.isValidArtistID("589ACMSZ#")); //Artist ID is invalid because it is missing a special character at the end
		assertFalse(Artist.isValidArtistID("479BDNYZ#_")); // Artist iD is invalid because the first character is not between 5-9
	}
	
	

	@Test
	void testValidBirthDate() {
	        assertTrue(Artist.isValidBirthDate("18-01-2001")); 
	        assertTrue(Artist.isValidBirthDate("23-04-1999"));
	    }
	 // Birthdate is valid because follows  the following format: DD-MM-YYYY. 
	@Test
	    public void testInvalidBirthDate() {
	        assertFalse(Artist.isValidBirthDate("18-12-01"));// Birthdate is invalid because year does not follow correct format
	        assertFalse(Artist.isValidBirthDate("3-04-1999")); // Birthdate is invalid because day does not follow correct format
	    }

	@Test
    public void testValidAddress() {
        assertTrue(Artist.isValidAddress("LosAngeles|California|US")); // Address is valid because it follows the correct format: City| State|Country
        assertTrue(Artist.isValidAddress("NewYork|NewYork|US")); // Address is valid because it follows the correct format: City| State|Country
    }

    @Test
    public void testInvalidAddress() {
        assertFalse(Artist.isValidAddress("LosAngeles,California,US"));// Address is invalid because it uses ',' instead of '|'
        assertFalse(Artist.isValidAddress("NewYork-NewYork-US")); // Address is invalid because it uses '-' instead of '|'
    }

	    @Test
	    public void testValidBio() {
	        assertTrue(Artist.isValidBioLength("Laufey, a rising artist, mesmerizes with soulful melodies, crafting emotive stories through her enchanting music. "));
	        assertTrue(Artist.isValidBioLength("Billie Eilish, a Grammy-winning artist, redefines music with her unique style, powerful voice, and creative storytelling.")); 
	    } // Bio is valid because it is between 10 to 30 words. 
	    

	    @Test
	    public void testInvalidBio() {
	        assertFalse(Artist.isValidBioLength("Billie Eilish, a Grammy-winning artist")); // invalid because less than 10 words
	        assertFalse(Artist.isValidBioLength("Laufey, the Icelandic enchantress of sound, weaves a musical tapestry that transcends boundaries. With her soulful, jazzy vocals and intricate songwriting, she paints vivid stories of life, love, and human experience. Drawing from jazz, pop, and R&B, Laufey's music is a journey into the heart of emotion, where every note and lyric resonate with deep resonance. Her music is a fusion of beauty and complexity, a sonic landscape where listeners can lose themselves and find the profound. With her unique voice and talent, Laufey stands as a rising star in the music world, captivating audiences and establishing herself as a force to be reckoned with.")); 
	    } // invalid because more than 30 words




	   @Test
	   public void testUpdateArtistCondition2_OccupationChangeBefore2000() {
	            // Creating an artist object born before 2000.
	            Artist artist = new Artist("679BDNYZ#_", "23-04-1999", 
	                createOccupations("Singer,Songwriter"), createAwards("2021, Best artist in Jazz and Blues”"), 
	                "Laufey, a rising artist, mesmerizes with soulful melodies, crafting emotive stories through her enchanting music.");

	            // Attempt to change the occupation, which is not allowed for an artist born before 2000.
	            boolean result = artist.updateArtist("679BDNYZ#_", "23-04-1999", 
		                createOccupations("	Composer,Musician"), createAwards("2021, Best artist in Jazz and Blues”"), 
		                "Laufey, a rising artist, mesmerizes with soulful melodies, crafting emotive stories through her enchanting music.");
	            assertFalse(result);
	            
	            Artist artist1 = new Artist("589ACMSZ@#", "18-12-1998", 
		                createOccupations("Singer,Songwriter"), createAwards("2020, Song of the Year"), 
		                "Billie Eilish, a Grammy-winning artist, redefines music with her unique style, powerful voice, and creative storytelling., ");
	            boolean result1 = artist1.updateArtist("589ACMSZ@#", "18-12-1998", 
		                createOccupations("Vocalist,Performer"), createAwards("2020, Song of the Year"), 
		                "Billie Eilish, a Grammy-winning artist, redefines music with her unique style, powerful voice, and creative storytelling., ");
	            
	            assertFalse(result1);
	            
	   }
	   
	   
	   
	   
	   @Test
	   public void testUpdateArtistCondition2_OccupationChangeAfter2000() {
	            // Creating an artist object born after 2000.
	            Artist artist = new Artist("679BDNYZ#_", "23-04-2002", 
	                createOccupations("Singer,Songwriter"), createAwards("2021, Best artist in Jazz and Blues”"), 
	                "Laufey, a rising artist, mesmerizes with soulful melodies, crafting emotive stories through her enchanting music.");

	            // Attempt to change the occupation, which is allowed for an artist born after 2000.
	            boolean result = artist.updateArtist("679BDNYZ#_", "23-04-2002", 
		                createOccupations("	Composer,Musician"), createAwards("2021, Best artist in Jazz and Blues”"), 
		                "Laufey, a rising artist, mesmerizes with soulful melodies, crafting emotive stories through her enchanting music.");
	            assertTrue(result);
	            
	            Artist artist1 = new Artist("589ACMSZ@#", "18-12-2001", 
		                createOccupations("Singer,Songwriter"), createAwards("2020, Song of the Year"), 
		                "Billie Eilish, a Grammy-winning artist, redefines music with her unique style, powerful voice, and creative storytelling., ");
	            boolean result1 = artist1.updateArtist("589ACMSZ@#", "18-12-2001", 
		                createOccupations("Vocalist,Performer"), createAwards("2020, Song of the Year"), 
		                "Billie Eilish, a Grammy-winning artist, redefines music with her unique style, powerful voice, and creative storytelling., ");
	            
	            assertTrue(result1);}
	            
	            
	   
	            
	            
	   
	        // Helper methods to create new (update) occupations and awards.
	        private ArrayList<String> createOccupations(String... occupations) {
	            ArrayList<String> list = new ArrayList<>();
	            for (String occupation : occupations) {
	                list.add(occupation);
	            }
	            return list;
	        }

	        private ArrayList<String> createAwards(String... awards) {
	            ArrayList<String> list = new ArrayList<>();
	            for (String award : awards) {
	                list.add(award);
	            }
	            return list;
	        }
	    }
