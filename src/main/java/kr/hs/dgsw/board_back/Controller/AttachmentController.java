package kr.hs.dgsw.board_back.Controller;

import kr.hs.dgsw.board_back.Domain.Attachment;
import kr.hs.dgsw.board_back.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/attachment/upload")
    public Attachment upload(@RequestPart MultipartFile srcFile) {
        try {
            String destFilename
                    = "D:/PORTFOLIO/DGSW/3Grade/WebPractice/Board/Upload/"
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS_"))
                    + srcFile.getOriginalFilename();
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            srcFile.transferTo(destFile);
            Attachment attach = Attachment.builder()
                    .filename(srcFile.getOriginalFilename())
                    .filepath(destFilename)
                    .build();
            return attachmentService.add(attach);
        } catch(Exception ex) {
            return null;
        }
    }

    @GetMapping("/attachment/image/{id}")
    public HttpServletResponse image(@PathVariable Long id, HttpServletResponse response) {
        try {
            Attachment attachment = this.attachmentService.findById(id);
            String filename = attachment.getFilename();
            String filepath = attachment.getFilepath();

            File file = new File(filepath);
            if (!file.exists()) return null;

            String mimeType = URLConnection.guessContentTypeFromName(filepath);
            if (mimeType == null) mimeType = "application/octet-stream";

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename='" + filename + "'");
            response.setContentLength((int) file.length());

            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, response.getOutputStream());
            return response;
        } catch (Exception ex) {
            return null;
        }
    }
}
