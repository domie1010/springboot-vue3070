package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 代驾投诉
 *
 * @author 
 * @email
 */
@TableName("daijia_tousu")
public class DaijiaTousuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public DaijiaTousuEntity() {

	}

	public DaijiaTousuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 投诉编号
     */
    @TableField(value = "daijia_tousu_uuid_number")

    private String daijiaTousuUuidNumber;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 代驾司机订单
     */
    @TableField(value = "daijia_order_id")

    private Integer daijiaOrderId;


    /**
     * 投诉名称
     */
    @TableField(value = "daijia_tousu_name")

    private String daijiaTousuName;


    /**
     * 投诉类型
     */
    @TableField(value = "daijia_tousu_types")

    private Integer daijiaTousuTypes;


    /**
     * 投诉详情
     */
    @TableField(value = "daijia_tousu_content")

    private String daijiaTousuContent;


    /**
     * 投诉时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 处理结果
     */
    @TableField(value = "daijia_tousu_chuli_text")

    private String daijiaTousuChuliText;


    /**
     * 回复时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "update_time",fill = FieldFill.UPDATE)

    private Date updateTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：投诉编号
	 */
    public String getDaijiaTousuUuidNumber() {
        return daijiaTousuUuidNumber;
    }
    /**
	 * 获取：投诉编号
	 */

    public void setDaijiaTousuUuidNumber(String daijiaTousuUuidNumber) {
        this.daijiaTousuUuidNumber = daijiaTousuUuidNumber;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：代驾司机订单
	 */
    public Integer getDaijiaOrderId() {
        return daijiaOrderId;
    }
    /**
	 * 获取：代驾司机订单
	 */

    public void setDaijiaOrderId(Integer daijiaOrderId) {
        this.daijiaOrderId = daijiaOrderId;
    }
    /**
	 * 设置：投诉名称
	 */
    public String getDaijiaTousuName() {
        return daijiaTousuName;
    }
    /**
	 * 获取：投诉名称
	 */

    public void setDaijiaTousuName(String daijiaTousuName) {
        this.daijiaTousuName = daijiaTousuName;
    }
    /**
	 * 设置：投诉类型
	 */
    public Integer getDaijiaTousuTypes() {
        return daijiaTousuTypes;
    }
    /**
	 * 获取：投诉类型
	 */

    public void setDaijiaTousuTypes(Integer daijiaTousuTypes) {
        this.daijiaTousuTypes = daijiaTousuTypes;
    }
    /**
	 * 设置：投诉详情
	 */
    public String getDaijiaTousuContent() {
        return daijiaTousuContent;
    }
    /**
	 * 获取：投诉详情
	 */

    public void setDaijiaTousuContent(String daijiaTousuContent) {
        this.daijiaTousuContent = daijiaTousuContent;
    }
    /**
	 * 设置：投诉时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 获取：投诉时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：处理结果
	 */
    public String getDaijiaTousuChuliText() {
        return daijiaTousuChuliText;
    }
    /**
	 * 获取：处理结果
	 */

    public void setDaijiaTousuChuliText(String daijiaTousuChuliText) {
        this.daijiaTousuChuliText = daijiaTousuChuliText;
    }
    /**
	 * 设置：回复时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }
    /**
	 * 获取：回复时间
	 */

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DaijiaTousu{" +
            "id=" + id +
            ", daijiaTousuUuidNumber=" + daijiaTousuUuidNumber +
            ", yonghuId=" + yonghuId +
            ", daijiaOrderId=" + daijiaOrderId +
            ", daijiaTousuName=" + daijiaTousuName +
            ", daijiaTousuTypes=" + daijiaTousuTypes +
            ", daijiaTousuContent=" + daijiaTousuContent +
            ", insertTime=" + insertTime +
            ", daijiaTousuChuliText=" + daijiaTousuChuliText +
            ", updateTime=" + updateTime +
            ", createTime=" + createTime +
        "}";
    }
}
