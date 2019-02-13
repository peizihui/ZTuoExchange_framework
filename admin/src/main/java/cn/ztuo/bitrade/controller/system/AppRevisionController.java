package cn.ztuo.bitrade.controller.system;

import cn.ztuo.bitrade.constant.PageModel;
import cn.ztuo.bitrade.entity.AppRevision;
import cn.ztuo.bitrade.service.AppRevisionService;
import cn.ztuo.bitrade.util.BindingResultUtil;
import cn.ztuo.bitrade.util.MessageResult;

import cn.ztuo.bitrade.controller.common.BaseAdminController;
import cn.ztuo.bitrade.model.create.AppRevisionCreate;
import cn.ztuo.bitrade.model.screen.AppRevisionScreen;
import cn.ztuo.bitrade.model.update.AppRevisionUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author GS
 * @Title: ${file_name}
 * @Description:
 * @date 2018/4/2416:31
 */
@RestController
@RequestMapping("system/app-revision")
public class AppRevisionController extends BaseAdminController {
    @Autowired
    private AppRevisionService service;

    //新增
    @PostMapping
    public MessageResult create(@Valid AppRevisionCreate model, BindingResult bindingResult) {
        MessageResult result = BindingResultUtil.validate(bindingResult);
        if (result != null) {
            return result;
        }
        service.save(model);
        return success();
    }

    //更新
    @PutMapping("{id}")
    public MessageResult put(@PathVariable("id") Long id, AppRevisionUpdate model) {
        AppRevision appRevision = service.findById(id);
        Assert.notNull(appRevision, "validate appRevision id!");
        service.update(model, appRevision);
        return success();
    }

    //详情
    @GetMapping("{id}")
    public MessageResult get(@PathVariable("id") Long id) {
        AppRevision appRevision = service.findById(id);
        Assert.notNull(appRevision, "validate appRevision id!");
        return success(appRevision);
    }

    //分页
    @GetMapping("page-query")
    public MessageResult get(PageModel pageModel, AppRevisionScreen screen) {
        return success(service.findAllScreen(screen, pageModel));
    }
}
