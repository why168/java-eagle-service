package com.github.eagle.domain;

import javax.validation.constraints.*;
import java.sql.Time;
import java.util.StringJoiner;

/**
 * Notify
 *
 * @author Edwin Wu
 */
public class Notify {

    public interface Save {
    }

    public interface Update {
    }

    @Min(value = 1)
    private Long id;

    @Min(value = 1)
    private Long msg_id;

    @Min(value = 1)
    private Long uid;

    private Integer role;
    @NotNull
    private String msg_type;
    private Integer read_status;
    @NotNull
    private String msg_entity;
    private Long insert_time;
    private Integer del;
    private String extra;
    private Time created_time;
    private Time update_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Long msg_id) {
        this.msg_id = msg_id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public Integer getRead_status() {
        return read_status;
    }

    public void setRead_status(Integer read_status) {
        this.read_status = read_status;
    }

    public String getMsg_entity() {
        return msg_entity;
    }

    public void setMsg_entity(String msg_entity) {
        this.msg_entity = msg_entity;
    }

    public Long getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Long insert_time) {
        this.insert_time = insert_time;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Time getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Time created_time) {
        this.created_time = created_time;
    }

    public Time getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Time update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Notify.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("msg_id=" + msg_id)
                .add("uid=" + uid)
                .add("role=" + role)
                .add("msg_type='" + msg_type + "'")
                .add("read_status=" + read_status)
                .add("msg_entity='" + msg_entity + "'")
                .add("insert_time=" + insert_time)
                .add("del=" + del)
                .add("extra='" + extra + "'")
                .add("created_time=" + created_time)
                .add("update_time=" + update_time)
                .toString();
    }
}
