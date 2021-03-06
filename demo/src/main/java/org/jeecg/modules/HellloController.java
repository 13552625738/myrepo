package org.jeecg.modules;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags="descs")
@RestController
@RequestMapping("/demo")
public class HellloController {

    @GetMapping("/s")
    @ApiOperation("demo")
    public String getNote() {

        return "是一所学校";
    }
}
