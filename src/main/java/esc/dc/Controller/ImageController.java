package esc.dc.Controller;

import esc.dc.Utils.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

@RequestMapping("uploads")
@RestController
public class ImageController {

    @Autowired
    private FileService fileService;

    @GetMapping("/{imageName}")
    public ResponseEntity<byte[]> uploads(@PathVariable("imageName") String imageName) throws Exception {
//        File imageFile = new File(getImageLocation(imageName));
        File imageFile = new File(fileService.getImageLocation(imageName));

        System.out.println(imageFile);
        System.out.println(imageFile.getAbsolutePath());
        if ((!imageFile.exists()) || imageFile.isDirectory()) {
            return null;
//            throw new EntityNotFoundException("Image not found. check 1");
        }else{
            String extension = imageName.substring(imageName.lastIndexOf(".") + 1, imageName.length());

            BufferedImage imagefile = ImageIO.read(imageFile);
            ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
            ImageIO.write(imagefile, extension, BAOS);
            byte[] imageByteArray = BAOS.toByteArray();

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageByteArray);
        }


    }
}
