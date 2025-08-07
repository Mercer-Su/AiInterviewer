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

    /**
     * @Description: 分页查询职位信息列表
     * @param page
     * @param pageSize
     * @return GraceJSONResult
     */
    @GetMapping("list")
    public GraceJSONResult list(@RequestParam(defaultValue = "1", name = "page") Integer page,
                                @RequestParam(defaultValue = "10", name = "pageSize") Integer pageSize) {
        return GraceJSONResult.ok(jobService.queryList(page, pageSize));
    }

    /**
     * @Description: 查询职位详情
     * @param jobId
     * @return GraceJSONResult
     */
    @GetMapping("detail")
    public GraceJSONResult detail(String jobId) {
        return GraceJSONResult.ok(jobService.getDetail(jobId));
    }

    /**
     * @Description: 删除职位详情
     * @param jobId
     * @return GraceJSONResult
     */
    @PostMapping("delete")
    public GraceJSONResult delete(String jobId) {
        jobService.delete(jobId);
        return GraceJSONResult.ok();
    }



}
