package com.throwit.speedreader;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by andreasmosti on 07.04.14.
 */
public class TextFileReader implements IFileBasedTextService {




    public String getTextFromSource(Context context, String fileName) {
        try {

            InputStream fIn = context.getAssets().open(fileName);

            StringBuilder fileData = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(fIn));
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }
            reader.close();
            return fileData.toString();

        } catch (IOException e) {
            System.out.println("Error while reading from file " + e.getMessage());
            return "Coult not read file!";
        }
    }
}
