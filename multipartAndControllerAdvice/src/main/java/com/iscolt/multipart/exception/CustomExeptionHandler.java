package com.iscolt.multipart.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * 全局异常处理
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/25
 * @Description: com.iscolt.multipart
 * @version: 1.0
 */
@RestControllerAdvice
public class CustomExeptionHandler {

    // TODO 全局异常有 bug
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView uploadException(MaxUploadSizeExceededException e) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "上传文件大小超过限制");
        mv.setViewName("error");
        return mv;
    }
}
