import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
/**
 * HashTest is the driver class that tests the HashTable.
 * User selects input type, load factor, and an optional debug level.
 *
 * @author samjackson
 */
public class HashTest {

	public static <E> void main(String[] args) {

		String usageMessage = "Usage: java HashTest <input type(1-3)> <load factor(0-1)> [<debug level(0-1)>]";
		int debugLvl = 0;
		int inputType = 0;
		double loadFactor = 0;
		final int SIZE = 95791;
		
		//Arguement parsing
		if(args.length < 2) {
			System.out.println(usageMessage);
			System.exit(1);
		}
		if(Integer.parseInt(args[0]) < 1 || Integer.parseInt(args[0]) > 3) {
			System.out.println(usageMessage);
		}
		else {
			inputType = Integer.parseInt(args[0]);
		}
		if (Double.parseDouble(args[1]) <= 0 || Double.parseDouble(args[1]) >= 1) {
			System.out.println(usageMessage);
		}
		else {
			loadFactor = Double.parseDouble(args[1]);
		}
		if(args.length == 3) {
			if (Integer.parseInt(args[2]) != 0 && Integer.parseInt(args[2]) != 1) {
				System.out.println(usageMessage);
			}
			else {
				debugLvl = Integer.parseInt(args[2]);
			}
		}
		
		int limit = (int) Math.round(loadFactor * SIZE);
		
		//Integer test
		if(inputType == 1) {
			
			if(debugLvl == 0) {
			System.out.println("A good table size is found: " + SIZE + " \n" +
					"Data source type: java.util.Random()\n");
			}
			
			HashTable<Integer> linProbeHash = new HashTable<Integer>(SIZE, loadFactor, HashTable.OpenAddressType.LinearProbing);
			HashTable<Integer> dubHash = new HashTable<Integer>(SIZE, loadFactor, HashTable.OpenAddressType.DoubleHashing);
			Random randomGen = new Random();
			for(int i = 0; i < limit; i++) { //fills hash tables with random ints
				int inputNum = randomGen.nextInt();
				linProbeHash.put(inputNum);
				dubHash.put(inputNum);
			}
			
			
			System.out.println("Using Linear Hashing.... \n"+
					"Input " + limit + " elements, of which " + linProbeHash.totalDuplicates() + " duplicates\n" +
					"load factor = " + loadFactor + ", Avg. no. of probes " + linProbeHash.averageProbs() + "\n\n" +
					"Using Double Hashing....\n" +
					"Input " + limit + " elements, of which " + dubHash.totalDuplicates() + " duplicates \n"+
					"load factor = "+ loadFactor +", Avg. no. of probes "+ dubHash.averageProbs() +  "\n");

			if(debugLvl == 1) {
				try {
					BufferedWriter dubWriter = new BufferedWriter(new FileWriter("double-dump"));
					dubWriter.write(dubHash.toString());
					BufferedWriter linWriter = new BufferedWriter(new FileWriter("linear-dump"));
					linWriter.write(linProbeHash.toString());
					dubWriter.close();
					linWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		//Double tests
		else if(inputType == 2) {
			
			if(debugLvl == 0) {
				System.out.println("A good table size is found: " + SIZE + " \n" +
						"Data source type: System.currentTimeMillis()\n");
			}
			
			HashTable<Double> linProbeHash = new HashTable<Double>(95791, loadFactor, HashTable.OpenAddressType.LinearProbing);
			HashTable<Double> dubHash = new HashTable<Double>(95791, loadFactor, HashTable.OpenAddressType.DoubleHashing);
			
			for(int i = 0; i < limit; i++) { //fills hash tables with unique doubles
				double inputNum = System.currentTimeMillis();
				linProbeHash.put(inputNum);
				dubHash.put(inputNum);
			}
			
			System.out.println("Using Linear Hashing.... \n"+
					"Input " + limit + " elements, of which " + linProbeHash.totalDuplicates() + " duplicates\n" +
					"load factor = " + loadFactor + ", Avg. no. of probes " + linProbeHash.averageProbs() + "\n\n" +
					"Using Double Hashing....\n" +
					"Input " + limit + " elements, of which " + dubHash.totalDuplicates() + " duplicates \n"+
					"load factor = "+ loadFactor +", Avg. no. of probes "+ dubHash.averageProbs() +  "\n");
			
			if(debugLvl == 1) {
				try {
					BufferedWriter dubWriter = new BufferedWriter(new FileWriter("double-dump"));
					dubWriter.write(dubHash.toString());
					BufferedWriter linWriter = new BufferedWriter(new FileWriter("linear-dump"));
					linWriter.write(linProbeHash.toString());
					dubWriter.close();
					linWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		//String tests
		else if(inputType == 3) {
			
			System.out.println("A good table size is found: " + SIZE + " \n" +
					"Data source type: word-list\n");
			
			
			HashTable<String> linProbeHash = new HashTable<String>(95791, loadFactor, HashTable.OpenAddressType.LinearProbing);
			HashTable<String> dubHash = new HashTable<String>(95791, loadFactor, HashTable.OpenAddressType.DoubleHashing);
			File textFile = new File("word-list");
			Scanner sc = null;
			if(!textFile.isFile()) {
				System.out.println("Not a valid file.");
				System.exit(1);
			}
			try {
				sc = new Scanner(textFile);
			}
			catch(Exception e) {
				System.out.println("File not found.");
				System.exit(1);
			}
			while(linProbeHash.getSize() < limit) { //fills hash tables with strings from word-list
				String inputStr = sc.nextLine();
				linProbeHash.put(inputStr);
				dubHash.put(inputStr);
			}
			
			System.out.println("Using Linear Hashing.... \n"+
					"Input " + linProbeHash.getElements() + " elements, of which " + linProbeHash.totalDuplicates() + " duplicates\n" +
					"load factor = " + loadFactor + ", Avg. no. of probes " + linProbeHash.averageProbs() + "\n\n" +
					"Using Double Hashing....\n" +
					"Input " + dubHash.getElements() + " elements, of which " + dubHash.totalDuplicates() + " duplicates \n"+
					"load factor = "+ loadFactor +", Avg. no. of probes "+ dubHash.averageProbs() +  "\n");
			
			if(debugLvl == 1) {
				try {
					BufferedWriter dubWriter = new BufferedWriter(new FileWriter("double-dump"));
					dubWriter.write(dubHash.toString());
					BufferedWriter linWriter = new BufferedWriter(new FileWriter("linear-dump"));
					linWriter.write(linProbeHash.toString());
					dubWriter.close();
					linWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			sc.close();
		}
		
	}

}
