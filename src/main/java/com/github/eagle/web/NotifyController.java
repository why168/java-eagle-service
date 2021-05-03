package com.github.eagle.web;

import com.github.eagle.domain.Notify;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * NotifyController
 *
 * @author Edwin Wu
 */
@RestController
@RequestMapping("/api/v1.0/notify")
public class NotifyController {

    /**
     * 查询消息列表
     *
     * @return 消息列表
     */
    @GetMapping("/list")
    public List<Notify> getNotifyListByUid() {
        return null;
    }

    /**
     * 添加消息通知
     *
     * @param notify 消息
     */
    @PostMapping("/add")
    public Object saveNotify(@RequestBody @Validated Notify notify, BindingResult bindingResult) {
        return "";
    }

    /**
     * 删除消息通知
     */
    @PostMapping("/del")
    public Object removeNotify(@RequestAttribute("msg_id") Long msg_id) {
        return "";
    }

    /**
     * 标记已读
     */
    @PostMapping("/mark_read")
    public Object markReadNotify(@RequestAttribute("msg_id") Long msg_id) {
        return "";
    }

    /**
     * 未读数
     */
    @GetMapping("/unread_count")
    public Object unreadCountNotify(@RequestAttribute("uid") Long uid,
                                    @RequestAttribute("role") Integer role,
                                    @RequestAttribute("msg_type") String msg_type) {
        return "";
    }


    /**
     * 获取已读时间
     */
    @GetMapping("/read_time")
    public Object readTimeNotify(@RequestAttribute("uid") Long uid,
                                 @RequestAttribute("role") Integer role,
                                 @RequestAttribute("msg_type") String msg_type) {
        return "";
    }

}
