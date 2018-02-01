package cz.muni.fi.pb162.hw03.impl;

import cz.muni.fi.pb162.hw03.ReadWriteIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jakub Polacek
 */
public class ReadWriteIOImpl implements ReadWriteIO {
    @Override
    public String streamToString(InputStream inputStream) throws IOException {


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


        List<String> strings = new ArrayList<>();

        List<String> readlines = bufferedReader.lines().collect(Collectors.toList());


        for (String readString : readlines) {
            strings.add(readString);
            strings.add("\n");

        }


        return String.join("", strings);


    }

    @Override
    public String fileToString(File file) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            String result = streamToString(fileInputStream);

            return result;
        }
    }

    @Override
    public void stringToStream(String string, OutputStream outputStream) throws IOException {

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(string);
            bufferedWriter.flush();

    }

    @Override
    public void stringToFile(String string, File file) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            stringToStream(string, fileOutputStream);

        }

    }
}
