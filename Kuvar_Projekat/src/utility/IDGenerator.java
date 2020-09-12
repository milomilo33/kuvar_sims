package utility;

import java.io.*;

public enum IDGenerator implements Serializable {
    INSTANCE;

    private int value = 1;

    public int requestID() {
        return value++;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void serialize() throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream("data/idgenerator.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(value);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void deserialize() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileIn = new FileInputStream("data/idgenerator.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            value = (int) in.readObject();
            fileIn.close();
            in.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
    }
}
