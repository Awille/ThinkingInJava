package io;

import java.io.*;
import java.util.Properties;

public class StreamTest {
    public static void main(String[] args) {
        String a = "Will";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(a.getBytes());
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String b = "";
        int temp;
        try {
            while ( (temp = dataInputStream.read()) != -1) {
                b += (char) temp;
            }
        } catch (IOException e) {
            System.out.println("异常");
        } finally {
            try {
                dataInputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        try {
            dataOutputStream.writeChars(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String out = new String(outputStream.toByteArray());
        System.out.print(out);
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(FileDescriptor.out);
        Properties myPro = new Properties();
        PrintStream myOut = myOut = new PrintStream(new BufferedOutputStream(fileOutputStream, 128), true);;
        myOut.println("Test Test");

    }
}
