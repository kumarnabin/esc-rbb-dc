package esc.dc.Utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    public static final String WHITE_SPACE = "\\s+";
    public static final String EMPTY_STRING = "";

    @Value("${app.upload.dir:${user.dir}}")
    public String uploadDir;

    public String uploadFile(MultipartFile file) {

        String OrgFileName = file.getOriginalFilename();

        if (OrgFileName != null && OrgFileName.isEmpty()) {
            return "";
        }
        String fileName = "";
        String uniqName = RandomStringUtils.randomAlphabetic(8) + "-";

        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + "Uploads" + File.separator + uniqName + StringUtils.cleanPath(file.getOriginalFilename()).replaceAll(WHITE_SPACE, EMPTY_STRING));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }

        fileName = uniqName + file.getOriginalFilename().replaceAll(WHITE_SPACE, EMPTY_STRING);
        return fileName;
    }

    public Path getImagePath(String fileName) throws Exception {
        try {

            Path path = Paths.get(
                    getTomcatBasePath() +
                            File.separator +
                            fileName);
            System.out.println(path.toString());
            return path;
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }

    public String getImageLocation(String fileName) throws Exception {
        return getTomcatBasePath()
                + File.separator
                + fileName;

    }

    public String getTomcatBasePath() {
        File file = new File("");
        String path = file.getAbsolutePath();
        path = path + File.separator + "Uploads";
        return path;
    }
}
