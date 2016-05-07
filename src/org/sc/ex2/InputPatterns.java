package org.sc.ex2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.sc.commons.Utils;

public class InputPatterns {

	private File patternFiles;
	private int[] structure;

	@SuppressWarnings("resource")
	public InputPatterns(String pathToPatterns) {

		int height = 0;
		int width = 0;
		this.structure = new int[2];
		this.patternFiles = new File(pathToPatterns);

		if (patternFiles.listFiles().length > 0) {
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(patternFiles.listFiles()[0]));

				while (bufferedReader.readLine() != null) {
					height++;
				}

				bufferedReader = new BufferedReader(new FileReader(patternFiles.listFiles()[0]));
				width = bufferedReader.readLine().length();
				bufferedReader.close();

				structure[0] = height * width;
				Utils.LENGHT_OF_PATTERN = structure[0];
				structure[1] = patternFiles.listFiles().length;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("resource")
	public int[][] getPattern(File patternFile) {

		int height = 0;
		String readLine;

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(patternFile));

			while ((readLine = bufferedReader.readLine()) != null) {
				height++;
			}

			bufferedReader = new BufferedReader(new FileReader(patternFile));

			int[][] matrix = new int[height][bufferedReader.readLine().toCharArray().length];
			height = 0;

			bufferedReader = new BufferedReader(new FileReader(patternFile));

			while ((readLine = bufferedReader.readLine()) != null) {
				char[] chars = readLine.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					matrix[height][i] = chars[i] != ' ' ? 1 : 0;
				}
				height++;
			}

			bufferedReader.close();
			return matrix;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public double[] convertPatternToArray(File file) {
		int[][] matrix = getPattern(file);

		double[] result = new double[Utils.LENGHT_OF_PATTERN];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				result[i * matrix[i].length + j] = matrix[i][j];
			}
		}
		return result;
	}

	public double[][] getInputValues() {
		double[][] result = new double[Utils.NUMBER_OF_PATTERNS][Utils.LENGHT_OF_PATTERN];
		int iterator = 0;
		for (File file : patternFiles.listFiles()) {
			result[iterator] = convertPatternToArray(file);
			iterator++;
		}

		return result;
	}
	
	public int getFileCount(){
		return patternFiles.listFiles().length;
	}
}
