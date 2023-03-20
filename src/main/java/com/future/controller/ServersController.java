package com.future.controller;

import com.future.entity.Servers;
import com.future.entity.resp.RestBean;
import com.future.service.ServerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;

@RestController
@RequestMapping("/server")
public class ServersController {

    @Resource
    private ServerService serverService;

    /**
     * 添加服务器
     * @param name 服务器名称
     * @param ownerId 所属用户id
     * @param createdAt 创建时间
     * @param updatedAt 更新时间
     * @param category 服务器状态
     * @param img 服务器图片
     * @return 返回添加结果
     */
    @PostMapping("/add")
    public RestBean<String> addServer(
            @RequestParam("name") String name,
            @RequestParam("owner_id") Integer ownerId,
            @RequestParam("created_at") Timestamp createdAt,
            @RequestParam("updated_at") Timestamp updatedAt,
            @RequestParam(value = "category",required = false) String category,
            @RequestParam(value = "img",required = false) String img
    ){
        Boolean aBoolean = serverService.addServer(
                new Servers().setName(name)
                        .setOwnerId(ownerId)
                        .setCreatedAt(createdAt)
                        .setUpdatedAt(updatedAt)
                        .setCategory(category)
                        .setImage(img)
        );
        return aBoolean ?
                new RestBean<>(200,"添加成功")
                : new RestBean<>(500,"添加失败");
    }

    /**
     * 根据用户id查询服务器
     * @param ownerId 用户id
     * @return 返回服务器对象
     */
    @PostMapping("/getByOwnerId")
    public RestBean<Servers> getServerByOwnerId(@RequestParam("owner_id") Integer ownerId){
        Servers servers = serverService.getServerByOwnerId(ownerId);
        return servers != null ?
                new RestBean<>(200,"查询成功",servers)
                :
                new RestBean<>(500,"查询失败");
    }
}
