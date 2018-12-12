package myweb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class First extends Thread {

	static Map<String, Integer> kiekiai = new TreeMap<String, Integer>();

	@Override
	public void run() {

		FileWriter fstream1, fstream2, fstream3, fstream4;
		BufferedWriter out1 = null;
		BufferedWriter out2 = null;
		BufferedWriter out3 = null;
		BufferedWriter out4 = null;

		File folder = new File("C://temp");

		File[] folderlist = folder.listFiles();

		List<String> all = new ArrayList<String>();

		for (File folder1 : folderlist) {

			if (folder1.isFile()) {
				
				System.out.println("\n" + folder1.toString());
				
				String st = folder1.toString();

				
				try (FileInputStream reader = new FileInputStream(new File(st))) {

					byte[] fileData = FileUtils.readFileToByteArray(folder1);


					// byte[] fileData = reader.readAllBytes();
					// reader.read(fileData);

					
					String d = new String(fileData);

					System.out.println(d);

					String[] splitted = d.split("\\W+");

					List<String> occ = new ArrayList<>();

					for (String word : splitted) {

						if (!stringContainsNumber(word)) {
							occ.add(word.toLowerCase(Locale.getDefault()));
						}

					}

					all.addAll(occ);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		System.out.println(all);

		kiekiai = new TreeMap<String, Integer>();

		for (int i = 0; i < all.size(); i++) {
			String z = (String) all.get(i);

			Integer kiekis = (Integer) kiekiai.get(z);

			if (kiekis == null) {
				kiekis = 0;
			}

			kiekis++;
			kiekiai.put(z, kiekis);

		}

		try {
			fstream1 = new FileWriter("C://temp/fileAG.txt");
			fstream2 = new FileWriter("C://temp/fileHN.txt");
			fstream3 = new FileWriter("C://temp/fileOU.txt");
			fstream4 = new FileWriter("C://temp/fileVZ.txt");

			out1 = new BufferedWriter(fstream1);
			out2 = new BufferedWriter(fstream2);
			out3 = new BufferedWriter(fstream3);
			out4 = new BufferedWriter(fstream4);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Iterator<Entry<String, Integer>> it = kiekiai.entrySet().stream().iterator();

		while (it.hasNext()) {

			Map.Entry<String, Integer> pairs = it.next();

			System.out.println(pairs.getKey() + " " + pairs.getValue());

			if (stringAG(pairs.getKey())) {

				try {
					out1.write(pairs.getKey() + " " + pairs.getValue() + "\n ");
					out1.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			else if (stringHN(pairs.getKey())) {

				try {
					out2.write(pairs.getKey() + " " + pairs.getValue() + "\n ");
					out2.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			else if (stringOU(pairs.getKey())) {

				try {
					out3.write(pairs.getKey() + " " + pairs.getValue() + "\n ");
					out3.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			else if (stringVZ(pairs.getKey())) {

				try {
					out4.write(pairs.getKey() + " " + pairs.getValue() + "\n ");
					out4.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		try {
			out1.close();
			out2.close();
			out3.close();
			out4.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	public static boolean stringContainsNumber(String s) {
		return Pattern.compile("[0-9]").matcher(s).find();
	}

	public static boolean stringAG(String s) {
		return Pattern.compile("^[a-h]").matcher(s).find();
	}

	public static boolean stringHN(String s) {
		return Pattern.compile("^[h-n]").matcher(s).find();
	}

	public static boolean stringOU(String s) {
		return Pattern.compile("^[o-u]").matcher(s).find();
	}

	public static boolean stringVZ(String s) {
		return Pattern.compile("^[v-z]").matcher(s).find();
	}

}
