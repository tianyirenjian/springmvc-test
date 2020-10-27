package com.tianyisoft.controller;

import com.tianyisoft.po.User;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tianyi
 */
@Controller
public class FileController {
    private final String dir = "e:/tmp/";

    @GetMapping("/")
    public String main(HttpSession session, Model model, @RequestParam(value = "msg", required = false) String msg) {
        User user = (User) session.getAttribute("USER_SESSION");
        model.addAttribute("user", user.getName());
        if (msg != null) {
            model.addAttribute("msg", msg);
        }
        return "main";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("filename") String name, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        if (name == null || file.isEmpty()) {
            return "redirect:/?msg=" + "请填写完整~";
        }
        Path path = Paths.get(this.dir + name);
        System.out.println(path.toString());
        file.transferTo(path);
        return "redirect:files";
    }

    @GetMapping("/files")
    public String files(Model model) {
        File dir = new File(this.dir);
        List<String> files = new ArrayList<>();
        for (File file : dir.listFiles()) {
            files.add(file.getName());
        }
        model.addAttribute("files", files);
        return "files";
    }

    @GetMapping("download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam("file")String name) throws IOException {
        File file = new File(this.dir + name);
        if (file.exists() && file.isFile()) {
            name = this.getFilename(request, name);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", name);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        }
        return null;
    }

    private String getFilename(HttpServletRequest request, String name) throws UnsupportedEncodingException {
        return URLEncoder.encode(name, "UTF-8");
    }
}
