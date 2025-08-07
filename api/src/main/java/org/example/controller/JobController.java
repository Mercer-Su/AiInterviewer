package org.example.controller;

import jakarta.annotation.Resource;
import org.example.grace.result.GraceJSONResult;
import org.example.pojo.bo.JobBO;
import org.example.serive.JobService;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName JobController
 * @Version 1.0
 * @Description JobController
 **/
@RestController
@RequestMapping("job")
public class JobController {

    @Resource
    private JobService jobService;

    /**
     * @Description: 创建或者更新职位信息
     * @param jobBO
     * @return GraceJSONResult
     */
    @PostMapping("createOrUpdate")
    public GraceJSONResult createOrUpdate(@RequestBody JobBO jobBO) {
        jobService.createOrUpdate(jobBO);
        return GraceJSONResult.ok();
    }


}
