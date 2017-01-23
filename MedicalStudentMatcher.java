package com.cerner.newdevelopment.medicalstudentmatcher;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Iterator;
import com.cerner.newdevelopment.matcher.Match;
import com.cerner.newdevelopment.medicalstudentmatcher.dataaccess.MatchWriter;
import com.cerner.newdevelopment.medicalstudentmatcher.dataaccess.PreferenceReader;
import com.cerner.newdevelopment.preference.Preference;
import com.cerner.newdevelopment.preference.parser.PreferenceCommaParser;
public class MedicalStudentMatcher {
		private int N, engagedCount;
	    private String[][] hospitalPreferences;
	    private String[][] studentPreferences;
	    private String[] hospitals;
	    private String[] students;
	    private String[] studentChoice;
	    private boolean[] hospitalAlloted;
	    public MedicalStudentMatcher(String[] m, String[] w, String[][] mp, String[][] wp){
	    	 N = mp.length;
	         engagedCount = 0;
	         hospitals = m;
	         students = w;
	         hospitalPreferences = mp;
	         studentPreferences = wp;
	         hospitalAlloted = new boolean[N];
	         studentChoice = new String[N];
	         calcMatches();
	    }
	enum Arguments {
		HospitalFile, ResidentFile, OutputFile
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//Retrieve file locations from command line arguments
		String hospitalFile = "";
		String residentFile = "";
		String outFile = "";
		if (args.length > 2) {
			hospitalFile = args[Arguments.HospitalFile.ordinal()];
			residentFile = args[Arguments.ResidentFile.ordinal()];
			outFile = args[Arguments.OutputFile.ordinal()];
		} else {
			System.out
					.println("Please include the names for the preference files and output file when running the application.\n "
							+ "Usage: \n\tjava MedicalStudentMatcher hospital.csv student.csv out.txt\n");
			return;
		}
		//Retrieve preferences from provided files
		List<Preference<String, String>> hospitalPrefs = ReadPreferences(hospitalFile);
		List<Preference<String, String>> studentPrefs = ReadPreferences(residentFile);
		List<Match<String, String>> matches = new ArrayList<Match<String, String>>();
		//TODO: Match students to hospitals
		//The hospital's preferences should be preferred since the hospital is taking most of the
		//risk in training the medical students.
		WriteMatches(matches, outFile);
        String[] m = {"St. Luke's","Olathe Medical Center"};
        String[] w = {"Martin Fowler","Alan Turing"};
        //array arranged in order of preferences
        String[][] mp = {{"Martin Fowler","Alan Turing"},
        				{"Alan Turing","Martin Fowler"}};
                        
        String[][] wp = {{"St. Luke's","Olathe Medical Center"},
        		{"Olathe Medical Center","St. Luke's"}};
 
        MedicalStudentMatcher gs = new MedicalStudentMatcher(m, w, mp, wp);                        

	}

	
	/**
	 * Writes the resulting matches to the output file
	 * @param matches The matches to write
	 * @param outFile The output file
	 */
	private static void WriteMatches(List<Match<String, String>> matches, String outFile) {
		MatchWriter<String,String> writer = new MatchWriter<String, String>(outFile);
		for (Match<String, String> match : matches) {
			writer.WriteMatch(match);
		}
	}

	/**
	 * Reads preferences from the preference file provided 
	 * @param file The preference file
	 * @return     The list of preferences read from the file
	 */
	private static List<Preference<String, String>> ReadPreferences(String file) {
		PreferenceReader<String,String> reader = new PreferenceReader<String,String>(new PreferenceCommaParser());
		List<Preference<String, String>> preferences = reader.readPreferences(file);
		return preferences;
	}

	
	
	private void calcMatches()
    {
        while (engagedCount < N)
        {
            int free;
            for (free = 0; free < N; free++)
                if (!hospitalAlloted[free])
                    break;
 
            for (int i = 0; i < N && !hospitalAlloted[free]; i++)
            {
                int index = studentsIndexOf(hospitalPreferences[free][i]);
                if (studentChoice[index] == null)
                {
                    studentChoice[index] = hospitals[free];
                    hospitalAlloted[free] = true;
                    engagedCount++;
                }
                else
                {
                    String currentPartner = studentChoice[index];
                    if (morePreference(currentPartner, hospitals[free], index))
                    {
                        studentChoice[index] = hospitals[free];
                        hospitalAlloted[free] = true;
                        hospitalAlloted[hospitalsIndexOf(currentPartner)] = false;
                    }
                }
            }            
        }
        printResults();
    }
	private boolean morePreference(String curPartner, String newPartner, int index)
    {
        for (int i = 0; i < N; i++)
        {
            if (studentPreferences[index][i].equals(newPartner))
                return true;
            if (studentPreferences[index][i].equals(curPartner))
                return false;
        }
        return false;
    }
    
    private int hospitalsIndexOf(String str)
    {
        for (int i = 0; i < N; i++)
            if (hospitals[i].equals(str))
                return i;
        return -1;
    }
    
    private int studentsIndexOf(String str)
    {
        for (int i = 0; i < N; i++)
            if (students[i].equals(str))
                return i;
        return -1;
    }
    
    public void printResults()
    {
    	BufferedWriter bw = null;
		FileWriter fw = null;
		final String FILENAME = "C:\\Users\\ss051114\\Desktop\\Java\\out.txt";
		try {
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write("Results:");
			bw.newLine();
			System.out.println("Results:");
	        for (int i = 0; i < N; i++)
	        {
	        	bw.write(studentChoice[i] +","+ students[i]);
	        	bw.newLine();
	        	
	            System.out.println(studentChoice[i] +"-"+ students[i]);
	        }
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
    }
	
}
