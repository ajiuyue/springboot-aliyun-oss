package top.snailclimb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.snailclimb.common.CommonResult;
import top.snailclimb.service.AliyunOssPicStorageService;

/**
 * @author shuang.kou
 */
@Controller
@RequestMapping("/oss/pictures")
public class AliyunOssPicStorageController {

    private final AliyunOssPicStorageService aliyunOssService;

    public AliyunOssPicStorageController(AliyunOssPicStorageService aliyunOssService) {
        this.aliyunOssService = aliyunOssService;
    }

    @GetMapping("/upload")
    public String goToPicUpload() {
        return "picUpload";
    }

    @GetMapping("/management")
    public String goToPicMannagement() {
        return "picManagement";
    }

    @PostMapping
    @ResponseBody
    public CommonResult handleUploadImage(@RequestParam("file") MultipartFile file) {
        return aliyunOssService.handleUploadImage(file);
    }

    @DeleteMapping
    @ResponseBody
    public void deletePicByKey(@RequestParam("picKey") String picKey) {
        aliyunOssService.deletePicByKey(picKey);
    }

    @GetMapping
    @ResponseBody
    public CommonResult listPicturesByFileHost() {
        return aliyunOssService.listPicturesByFileHost("test/");
    }

}
