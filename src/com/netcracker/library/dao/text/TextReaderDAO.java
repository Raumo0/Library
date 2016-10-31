package com.netcracker.library.dao.text;

import com.netcracker.library.dao.ReaderDAO;
import com.netcracker.library.entities.Reader;
import com.netcracker.library.tools.TextFileWorker;

import java.io.FileNotFoundException;
import java.util.Collection;

/**
 * Created by raumo0 on 31.10.16.
 */
public class TextReaderDAO implements ReaderDAO {
    private static String text;
    private static String fileName = "readers.txt";

    public TextReaderDAO(){
        try {
            this.text = TextFileWorker.read(fileName);
        } catch (FileNotFoundException e) {
            text = "";
        }
    }

    @Override
    public boolean saveAll(Collection<Reader> readers) {
        text = text.concat(readers.toString());
        try {
            TextFileWorker.write(fileName, text);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    @Override
    public Collection<Reader> readAll() {
        return null;
    }
}
