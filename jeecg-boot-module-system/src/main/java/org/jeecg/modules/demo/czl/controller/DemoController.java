package org.jeecg.modules.demo.czl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.czl.entity.Demo;
import org.jeecg.modules.demo.czl.service.IDemoService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.system.service.ISysDictService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: desc
 * @Author: jeecg-boot
 * @Date:   2019-11-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags="desc")
@RestController
@RequestMapping("/czl/demo")
public class DemoController extends JeecgController<Demo, IDemoService> {
	@Autowired
	private IDemoService demoService;
	



	@AutoLog(value = "desc-分页列表查询")
	@ApiOperation(value="desc-分页列表查询", notes="desc-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Demo demo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Demo> queryWrapper = QueryGenerator.initQueryWrapper(demo, req.getParameterMap());
		Page<Demo> page = new Page<Demo>(pageNo, pageSize);
		IPage<Demo> pageList = demoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param demo
	 * @return
	 */
	@AutoLog(value = "desc-添加")
	@ApiOperation(value="desc-添加", notes="desc-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Demo demo) {
		demoService.save(demo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param demo
	 * @return
	 */
	@AutoLog(value = "desc-编辑")
	@ApiOperation(value="desc-编辑", notes="desc-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Demo demo) {
		demoService.updateById(demo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "desc-通过id删除")
	@ApiOperation(value="desc-通过id删除", notes="desc-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		demoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "desc-批量删除")
	@ApiOperation(value="desc-批量删除", notes="desc-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.demoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "desc-通过id查询")
	@ApiOperation(value="desc-通过id查询", notes="desc-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Demo demo = demoService.getById(id);
		return Result.ok(demo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param demo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Demo demo) {
      return super.exportXls(request, demo, Demo.class, "desc");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, Demo.class);
  }

	 @GetMapping(value = "/hello")
	 public Result<String> hello() {
		 Result<String> result = new Result<String>();
		 result.setResult("Hello World!");
		 result.setSuccess(true);
		 return result;
	 }
     @Autowired
     private ISysDictService sysDictService;

     @GetMapping("/getNote")
     public String getNote(@RequestParam(name="school") String school) {
         //TODO 业务自定义 此处下拉框的value是字典项的编码需要将其转换成名称

         return school+"是一所学校";
     }
}
