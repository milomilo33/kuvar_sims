package main;

import utility.DataSerialization;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		DataSerialization data = new DataSerialization();
		data.write();
		data = data.read();

	}

}
