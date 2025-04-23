package com.entity.model;

import com.entity.DaijiaTousuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 代驾投诉
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class DaijiaTousuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 投诉编号
     */
    private String daijiaTousuUuidNumber;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 代驾司机订单
     */
    private Integer daijiaOrderId;


    /**
     * 投诉名称
     */
    private String daijiaTousuName;


    /**
     * 投诉类型
     */
    private Integer daijiaTousuTypes;


    /**
     * 投诉详情
     */
    private String daijiaTousuContent;


    /**
     * 投诉时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 处理结果
     */
    private String daijiaTousuChuliText;


    /**
     * 回复时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date updateTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：投诉编号
	 */
    public String getDaijiaTousuUuidNumber() {
        return daijiaTousuUuidNumber;
    }


    /**
	 * 设置：投诉编号
	 */
    public void setDaijiaTousuUuidNumber(String daijiaTousuUuidNumber) {
        this.daijiaTousuUuidNumber = daijiaTousuUuidNumber;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：代驾司机订单
	 */
    public Integer getDaijiaOrderId() {
        return daijiaOrderId;
    }


    /**
	 * 设置：代驾司机订单
	 */
    public void setDaijiaOrderId(Integer daijiaOrderId) {
        this.daijiaOrderId = daijiaOrderId;
    }
    /**
	 * 获取：投诉名称
	 */
    public String getDaijiaTousuName() {
        return daijiaTousuName;
    }


    /**
	 * 设置：投诉名称
	 */
    public void setDaijiaTousuName(String daijiaTousuName) {
        this.daijiaTousuName = daijiaTousuName;
    }
    /**
	 * 获取：投诉类型
	 */
    public Integer getDaijiaTousuTypes() {
        return daijiaTousuTypes;
    }


    /**
	 * 设置：投诉类型
	 */
    public void setDaijiaTousuTypes(Integer daijiaTousuTypes) {
        this.daijiaTousuTypes = daijiaTousuTypes;
    }
    /**
	 * 获取：投诉详情
	 */
    public String getDaijiaTousuContent() {
        return daijiaTousuContent;
    }


    /**
	 * 设置：投诉详情
	 */
    public void setDaijiaTousuContent(String daijiaTousuContent) {
        this.daijiaTousuContent = daijiaTousuContent;
    }
    /**
	 * 获取：投诉时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：投诉时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：处理结果
	 */
    public String getDaijiaTousuChuliText() {
        return daijiaTousuChuliText;
    }


    /**
	 * 设置：处理结果
	 */
    public void setDaijiaTousuChuliText(String daijiaTousuChuliText) {
        this.daijiaTousuChuliText = daijiaTousuChuliText;
    }
    /**
	 * 获取：回复时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
	 * 设置：回复时间
	 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
