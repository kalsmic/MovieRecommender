package helpers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

public class FileHelper
{
    String fileName;

    public FileHelper( String fileName )
    {
        this.fileName = fileName;
    }

    public Closeable getCSVParser() throws IOException
    {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File( Objects.requireNonNull( classLoader.getResource( fileName ) ).getFile() );
        Reader reader = new BufferedReader( new FileReader( file ) );
        return new CSVParser( reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim() );

    }
}
