package at.kaindorf.io;

import at.kaindorf.pojo.ClassTeacher;
import at.kaindorf.pojo.Classname;
import at.kaindorf.pojo.Room;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class IOAccess {

    public static List<Classname> importClassname () {
        try {
            return Files.lines(Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "schooldata.csv"), StandardCharsets.UTF_8)
                    .skip(1)
                    .map(Classname::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
