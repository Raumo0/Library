package com.netcracker.library.dao.binary;

import com.netcracker.library.dao.ReaderDAO;
import com.netcracker.library.entities.Reader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

/**
 * Created by raumo0 on 31.10.16.
 */
public class BinaryReaderDAO implements ReaderDAO {
    @Override
    public boolean saveAll(Collection<Reader> readers) {
        try {
            FileOutputStream fos = new FileOutputStream("readers.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(readers);
            oos.flush();
            oos.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Collection<Reader> readAll() {
        Collection<Reader> readers;
        try {
            FileInputStream fis = new FileInputStream("readers.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            readers = (Collection<Reader>) oin.readObject();
            oin.close();
        } catch (Exception e) {
            return null;
        }
        return readers;
    }
}
