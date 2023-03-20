package com.future.controller;

import com.future.entity.Servers;
import com.future.entity.resp.RestBean;
import com.future.mapper.ServersMapper;
import com.future.service.ServerService;
import io.jsonwebtoken.io.IOException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * 服务器控制器
 */
@RestController
@RequestMapping("/server")
public class ServersController {

    @Resource
    private ServerService serverService;
    @Resource
    private ServersMapper serversMapper;

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
            @RequestParam(value = "img",required = false) String img,
            @RequestParam(value = "member_id",required = false) String memberId
    ){
        Boolean aBoolean = serverService.addServer(
                new Servers().setName(name)
                        .setOwnerId(ownerId)
                        .setCreatedAt(createdAt)
                        .setUpdatedAt(updatedAt)
                        .setCategory(category)
                        .setImg(img)
                        .setMemberId(memberId)
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

    /**
     * 根据用户id修改服务器类型
     * @param id 用户id
     * @param category 服务器类型
     * @return 返回修改结果
     */
    @PostMapping("/set_category")
    public RestBean<String> setCategory(@RequestParam("id") Integer id,@RequestParam(value = "category",defaultValue = "private") String category){
        Boolean aBoolean = serverService.updateCategory(id, category);
        return aBoolean ?
                new RestBean<>(200,"设置成功")
                :
                new RestBean<>(500,"设置失败");
    }

    /**
     * 加入服务器
     * @param request 请求
     * @param response 响应
     * @param id 服务器id
     * @return 返回加入结果
     * @throws IOException IO异常
     */
    @PostMapping("/{id}/join")
    public RestBean<Servers> joinServer(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable("id") Integer id) throws IOException {
        // 获取成员 ID
        String memberId = request.getParameter("memberId");

        // 加入服务器
        Boolean aBoolean = serverService.joinServer(id, memberId);

        // 查询服务器
        Servers servers = serversMapper.selectServerById(id);

        return aBoolean ?
                new RestBean<>(200,"加入成功",servers)
                :
                new RestBean<>(500,"加入失败");
    }

    /**
     * 退出服务器
     * @param request 请求
     * @param response 响应
     * @param id 服务器id
     * @return 返回退出结果
     * @throws IOException IO异常
     */
    @PostMapping("/{id}/quit")
    public RestBean<Servers> quitServer(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("id") Integer id) throws IOException {
        // 获取成员 ID
        String memberId = request.getParameter("memberId");

        // 退出服务器
        Boolean aBoolean = serverService.quitServer(id, memberId);

        // 查询服务器
        Servers servers = serversMapper.selectServerById(id);

        return aBoolean ?
                new RestBean<>(200,"退出成功",servers)
                :
                new RestBean<>(500,"退出失败");
    }
}
