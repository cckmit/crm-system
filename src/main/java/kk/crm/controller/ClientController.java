package kk.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kk.crm.entity.Client;
import kk.crm.entity.ResEntity;
import kk.crm.service.impl.ClientServiceImpl;
import kk.crm.util.ResGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author maosi
 * @since 2021-05-24
 */
@RestController
@Api(tags = "客户接口")
public class ClientController {

    @Autowired
    ClientServiceImpl clientService;

    @ApiOperation(value = "查询客户列表")
    @GetMapping("/client")
    public ResEntity getClient(@RequestParam Integer page, @RequestParam Integer limit, @RequestParam(required = false) String status,@RequestParam(required = false) String name){
        Page iPage = new Page<>(page,limit,true);
        QueryWrapper<Client> queryWrapper = new QueryWrapper<>();
        if (status != null && !status.isEmpty()){
            queryWrapper.eq("status",status);
        }
        if (name != null && !name.isEmpty()){
            queryWrapper.like("name",name);
        }
        final Page list = clientService.page(iPage,queryWrapper);
        return ResGenerator.success(list);
    }

    @ApiOperation(value = "查询客户信息")
    @GetMapping("/clientone")
    public ResEntity getClient(@RequestParam Integer id){
        final Client client = clientService.getById(id);
        return ResGenerator.success(client);
    }

    @ApiOperation(value = "添加客户")
    @PostMapping("/client")
    public ResEntity postClient(@RequestBody Client client){
        try {
            clientService.save(client);
            return ResGenerator.success("添加成功");
        }catch (Exception e){
            return ResGenerator.fail("添加失败");
        }
    }

    @ApiOperation(value = "修改客户信息")
    @PutMapping("/client")
    public ResEntity putClient(@RequestBody Client client){
        try {
            clientService.updateById(client);
            return ResGenerator.success("修改成功");
        }catch (Exception e){
            return ResGenerator.fail("修改失败");
        }
    }

    @ApiOperation(value = "修改到新的销售")
    @GetMapping("/clientseller")
    public ResEntity getClientById(@RequestParam Integer id,@RequestParam String seller){
        try {
            final Client client = new Client();
            client.setId(id);
            client.setSeller(seller);
            clientService.updateById(client);
            return ResGenerator.success("添加成功");
        }catch (Exception e){
            return ResGenerator.fail("添加失败");
        }
    }

}
