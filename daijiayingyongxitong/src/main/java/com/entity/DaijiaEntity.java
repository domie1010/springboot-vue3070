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
 * 代驾司机
 *
 * @author 
 * @email
 */
@TableName("daijia")
public class DaijiaEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public DaijiaEntity() {

	}

	public DaijiaEntity(T t) {
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
     * 账户
     */
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @TableField(value = "password")

    private String password;


    /**
     * 工号
     */
    @TableField(value = "daijia_uuid_number")

    private String daijiaUuidNumber;


    /**
     * 代驾司机姓名
     */
    @TableField(value = "daijia_name")

    private String daijiaName;


    /**
     * 代驾司机手机号
     */
    @TableField(value = "daijia_phone")

    private String daijiaPhone;


    /**
     * 代驾司机身份证号
     */
    @TableField(value = "daijia_id_number")

    private String daijiaIdNumber;


    /**
     * 性别
     */
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    @TableField(value = "daijia_email")

    private String daijiaEmail;


    /**
     * 代驾司机头像
     */
    @TableField(value = "daijia_photo")

    private String daijiaPhoto;


    /**
     * 司机驾龄
     */
    @TableField(value = "daijia_jialing")

    private String daijiaJialing;


    /**
     * 司机原价/每公里
     */
    @TableField(value = "daijia_old_money")

    private Double daijiaOldMoney;


    /**
     * 现价/每公里
     */
    @TableField(value = "daijia_new_money")

    private Double daijiaNewMoney;


    /**
     * 司机热度
     */
    @TableField(value = "daijia_clicknum")

    private Integer daijiaClicknum;


    /**
     * 代价司机详细介绍
     */
    @TableField(value = "daijia_content")

    private String daijiaContent;


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
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }
    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }
    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：工号
	 */
    public String getDaijiaUuidNumber() {
        return daijiaUuidNumber;
    }
    /**
	 * 获取：工号
	 */

    public void setDaijiaUuidNumber(String daijiaUuidNumber) {
        this.daijiaUuidNumber = daijiaUuidNumber;
    }
    /**
	 * 设置：代驾司机姓名
	 */
    public String getDaijiaName() {
        return daijiaName;
    }
    /**
	 * 获取：代驾司机姓名
	 */

    public void setDaijiaName(String daijiaName) {
        this.daijiaName = daijiaName;
    }
    /**
	 * 设置：代驾司机手机号
	 */
    public String getDaijiaPhone() {
        return daijiaPhone;
    }
    /**
	 * 获取：代驾司机手机号
	 */

    public void setDaijiaPhone(String daijiaPhone) {
        this.daijiaPhone = daijiaPhone;
    }
    /**
	 * 设置：代驾司机身份证号
	 */
    public String getDaijiaIdNumber() {
        return daijiaIdNumber;
    }
    /**
	 * 获取：代驾司机身份证号
	 */

    public void setDaijiaIdNumber(String daijiaIdNumber) {
        this.daijiaIdNumber = daijiaIdNumber;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }
    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：电子邮箱
	 */
    public String getDaijiaEmail() {
        return daijiaEmail;
    }
    /**
	 * 获取：电子邮箱
	 */

    public void setDaijiaEmail(String daijiaEmail) {
        this.daijiaEmail = daijiaEmail;
    }
    /**
	 * 设置：代驾司机头像
	 */
    public String getDaijiaPhoto() {
        return daijiaPhoto;
    }
    /**
	 * 获取：代驾司机头像
	 */

    public void setDaijiaPhoto(String daijiaPhoto) {
        this.daijiaPhoto = daijiaPhoto;
    }
    /**
	 * 设置：司机驾龄
	 */
    public String getDaijiaJialing() {
        return daijiaJialing;
    }
    /**
	 * 获取：司机驾龄
	 */

    public void setDaijiaJialing(String daijiaJialing) {
        this.daijiaJialing = daijiaJialing;
    }
    /**
	 * 设置：司机原价/每公里
	 */
    public Double getDaijiaOldMoney() {
        return daijiaOldMoney;
    }
    /**
	 * 获取：司机原价/每公里
	 */

    public void setDaijiaOldMoney(Double daijiaOldMoney) {
        this.daijiaOldMoney = daijiaOldMoney;
    }
    /**
	 * 设置：现价/每公里
	 */
    public Double getDaijiaNewMoney() {
        return daijiaNewMoney;
    }
    /**
	 * 获取：现价/每公里
	 */

    public void setDaijiaNewMoney(Double daijiaNewMoney) {
        this.daijiaNewMoney = daijiaNewMoney;
    }
    /**
	 * 设置：司机热度
	 */
    public Integer getDaijiaClicknum() {
        return daijiaClicknum;
    }
    /**
	 * 获取：司机热度
	 */

    public void setDaijiaClicknum(Integer daijiaClicknum) {
        this.daijiaClicknum = daijiaClicknum;
    }
    /**
	 * 设置：代价司机详细介绍
	 */
    public String getDaijiaContent() {
        return daijiaContent;
    }
    /**
	 * 获取：代价司机详细介绍
	 */

    public void setDaijiaContent(String daijiaContent) {
        this.daijiaContent = daijiaContent;
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
        return "Daijia{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", daijiaUuidNumber=" + daijiaUuidNumber +
            ", daijiaName=" + daijiaName +
            ", daijiaPhone=" + daijiaPhone +
            ", daijiaIdNumber=" + daijiaIdNumber +
            ", sexTypes=" + sexTypes +
            ", daijiaEmail=" + daijiaEmail +
            ", daijiaPhoto=" + daijiaPhoto +
            ", daijiaJialing=" + daijiaJialing +
            ", daijiaOldMoney=" + daijiaOldMoney +
            ", daijiaNewMoney=" + daijiaNewMoney +
            ", daijiaClicknum=" + daijiaClicknum +
            ", daijiaContent=" + daijiaContent +
            ", createTime=" + createTime +
        "}";
    }
}
