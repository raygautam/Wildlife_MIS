//package in.gov.wildlife.mis.helper;
//
//import com.gov.fisheries.fish.farmer.portal.notification.dto.NotificationDTO;
//import com.gov.fisheries.fish.farmer.portal.successstories.dto.SuccessStoriesDTO;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//@Component
//public class FileUploadHelper {
//    @Value("${fileLocation}")
//    private  String UPLOAD_DIR ;
//
//
//    public String uploadFileSuccessStories(MultipartFile file, SuccessStoriesDTO successStoriesDTO) {
//
//        Boolean b = false;
//
//
//        try {
//            String contentType = file.getContentType();
////            System.out.println(contentType+"Content Type");
////            System.out.println(file.getSize());
//            InputStream inputStream = file.getInputStream();
//
//
//            byte data[] = new byte[inputStream.available()];
//            inputStream.read(data);
////            System.out.println("After reading");
//            FileOutputStream fileOutputStream = new FileOutputStream(UPLOAD_DIR+"/successStories" + File.separator + file.getOriginalFilename());
//            String loc = UPLOAD_DIR+"/successStories" + File.separator + file.getOriginalFilename();
////            System.out.println("Before Writing");
//            fileOutputStream.write(data);
//
//
////            User user= userService.getUserByid(id);
////            userService.updateLocation(user,UPLOAD_DIR+File.separator+file.getOriginalFilename());
//            fileOutputStream.flush();
//            fileOutputStream.close();
//            inputStream.close();
//
//            b = true;
//            return loc;
//
//            // this below line is for the same as above lines but using nio
//            /// Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+file.getOriginalFilename()) , StandardCopyOption.REPLACE_EXISTING);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        return " ";
//    }
//
//    public String fileUploadNotification(MultipartFile file, NotificationDTO notificationDTO){
//
//        Boolean b = false;
//
//
//        try {
//            String contentType = file.getContentType();
////            System.out.println(contentType+"Content Type");
////            System.out.println(file.getSize());
//            InputStream inputStream = file.getInputStream();
//
//
//            byte data[] = new byte[inputStream.available()];
//            inputStream.read(data);
////            System.out.println("After reading");
//            FileOutputStream fileOutputStream = new FileOutputStream(UPLOAD_DIR+"/notifications"+ File.separator+ file.getOriginalFilename());
//            String loc = UPLOAD_DIR+"/notifications"+ File.separator+ file.getOriginalFilename();
////            System.out.println("Before Writing");
//            fileOutputStream.write(data);
//
//
////            User user= userService.getUserByid(id);
////            userService.updateLocation(user,UPLOAD_DIR+File.separator+file.getOriginalFilename());
//
//            fileOutputStream.flush();
//            fileOutputStream.close();
//            inputStream.close();
//
//            b = true;
//            return loc;
//
//            // this below line is for the same as above lines but using nio
//            /// Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+file.getOriginalFilename()) , StandardCopyOption.REPLACE_EXISTING);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//}
