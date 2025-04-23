package com.entity.model;

import com.entity.DaijiaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 代驾司机
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class DaijiaModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 工号
     */
    private String daijiaUuidNumber;


    /**
     * 代驾司机姓名
     */
    private String daijiaName;


    /**
     * 代驾司机手机号
     */
    private String daijiaPhone;


    /**
     * 代驾司机身份证号
     */
    private String daijiaIdNumber;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    private String daijiaEmail;


    /**
     * 代驾司机头像
     */
    private String daijiaPhoto;


    /**
     * 司机驾龄
     */
    private String daijiaJialing;


    /**
     * 司机原价/每公里
     */
    private Double daijiaOldMoney;


    /**
     * 现价/每公里
     */
    private Double daijiaNewMoney;


    /**
     * 司机热度
     */
    private Integer daijiaClicknum;


    /**
     * 代价司机详细介绍
     */
    private String daijiaContent;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：工号
	 */
    public String getDaijiaUuidNumber() {
        return daijiaUuidNumber;
    }


    /**
	 * 设置：工号
	 */
    public void setDaijiaUuidNumber(String daijiaUuidNumber) {
        this.daijiaUuidNumber = daijiaUuidNumber;
    }
    /**
	 * 获取：代驾司机姓名
	 */
    public String getDaijiaName() {
        return daijiaName;
    }


    /**
	 * 设置：代驾司机姓名
	 */
    public void setDaijiaName(String daijiaName) {
        this.daijiaName = daijiaName;
    }
    /**
	 * 获取：代驾司机手机号
	 */
    public String getDaijiaPhone() {
        return daijiaPhone;
    }


    /**
	 * 设置：代驾司机手机号
	 */
    public void setDaijiaPhone(String daijiaPhone) {
        this.daijiaPhone = daijiaPhone;
    }
    /**
	 * 获取：代驾司机身份证号
	 */
    public String getDaijiaIdNumber() {
        return daijiaIdNumber;
    }


    /**
	 * 设置：代驾司机身份证号
	 */
    public void setDaijiaIdNumber(String daijiaIdNumber) {
        this.daijiaIdNumber = daijiaIdNumber;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：电子邮箱
	 */
    public String getDaijiaEmail() {
        return daijiaEmail;
    }


    /**
	 * 设置：电子邮箱
	 */
    public void setDaijiaEmail(String daijiaEmail) {
        this.daijiaEmail = daijiaEmail;
    }
    /**
	 * 获取：代驾司机头像
	 */
    public String getDaijiaPhoto() {
        return daijiaPhoto;
    }


    /**
	 * 设置：代驾司机头像
	 */
    public void setDaijiaPhoto(String daijiaPhoto) {
        this.daijiaPhoto = daijiaPhoto;
    }
    /**
	 * 获取：司机驾龄
	 */
    public String getDaijiaJialing() {
        return daijiaJialing;
    }


    /**
	 * 设置：司机驾龄
	 */
    public void setDaijiaJialing(String daijiaJialing) {
        this.daijiaJialing = daijiaJialing;
    }
    /**
	 * 获取：司机原价/每公里
	 */
    public Double getDaijiaOldMoney() {
        return daijiaOldMoney;
    }


    /**
	 * 设置：司机原价/每公里
	 */
    public void setDaijiaOldMoney(Double daijiaOldMoney) {
        this.daijiaOldMoney = daijiaOldMoney;
    }
    /**
	 * 获取：现价/每公里
	 */
    public Double getDaijiaNewMoney() {
        return daijiaNewMoney;
    }


    /**
	 * 设置：现价/每公里
	 */
    public void setDaijiaNewMoney(Double daijiaNewMoney) {
        this.daijiaNewMoney = daijiaNewMoney;
    }
    /**
	 * 获取：司机热度
	 */
    public Integer getDaijiaClicknum() {
        return daijiaClicknum;
    }


    /**
	 * 设置：司机热度
	 */
    public void setDaijiaClicknum(Integer daijiaClicknum) {
        this.daijiaClicknum = daijiaClicknum;
    }
    /**
	 * 获取：代价司机详细介绍
	 */
    public String getDaijiaContent() {
        return daijiaContent;
    }


    /**
	 * 设置：代价司机详细介绍
	 */
    public void setDaijiaContent(String daijiaContent) {
        this.daijiaContent = daijiaContent;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
