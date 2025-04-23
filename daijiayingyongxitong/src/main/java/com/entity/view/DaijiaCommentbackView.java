package com.entity.view;

import com.entity.DaijiaCommentbackEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 代驾司机评价
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("daijia_commentback")
public class DaijiaCommentbackView extends DaijiaCommentbackEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;
			/**
			* 余额
			*/
			private Double newMoney;

		//级联表 daijia
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

	public DaijiaCommentbackView() {

	}

	public DaijiaCommentbackView(DaijiaCommentbackEntity daijiaCommentbackEntity) {
		try {
			BeanUtils.copyProperties(this, daijiaCommentbackEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





























				//级联表的get和set yonghu

					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}

					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}

					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}

					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}

					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}


				//级联表的get和set daijia

					/**
					* 获取： 工号
					*/
					public String getDaijiaUuidNumber() {
						return daijiaUuidNumber;
					}
					/**
					* 设置： 工号
					*/
					public void setDaijiaUuidNumber(String daijiaUuidNumber) {
						this.daijiaUuidNumber = daijiaUuidNumber;
					}

					/**
					* 获取： 代驾司机姓名
					*/
					public String getDaijiaName() {
						return daijiaName;
					}
					/**
					* 设置： 代驾司机姓名
					*/
					public void setDaijiaName(String daijiaName) {
						this.daijiaName = daijiaName;
					}

					/**
					* 获取： 代驾司机手机号
					*/
					public String getDaijiaPhone() {
						return daijiaPhone;
					}
					/**
					* 设置： 代驾司机手机号
					*/
					public void setDaijiaPhone(String daijiaPhone) {
						this.daijiaPhone = daijiaPhone;
					}

					/**
					* 获取： 代驾司机身份证号
					*/
					public String getDaijiaIdNumber() {
						return daijiaIdNumber;
					}
					/**
					* 设置： 代驾司机身份证号
					*/
					public void setDaijiaIdNumber(String daijiaIdNumber) {
						this.daijiaIdNumber = daijiaIdNumber;
					}

					/**
					* 获取： 电子邮箱
					*/
					public String getDaijiaEmail() {
						return daijiaEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setDaijiaEmail(String daijiaEmail) {
						this.daijiaEmail = daijiaEmail;
					}

					/**
					* 获取： 代驾司机头像
					*/
					public String getDaijiaPhoto() {
						return daijiaPhoto;
					}
					/**
					* 设置： 代驾司机头像
					*/
					public void setDaijiaPhoto(String daijiaPhoto) {
						this.daijiaPhoto = daijiaPhoto;
					}

					/**
					* 获取： 司机驾龄
					*/
					public String getDaijiaJialing() {
						return daijiaJialing;
					}
					/**
					* 设置： 司机驾龄
					*/
					public void setDaijiaJialing(String daijiaJialing) {
						this.daijiaJialing = daijiaJialing;
					}

					/**
					* 获取： 司机原价/每公里
					*/
					public Double getDaijiaOldMoney() {
						return daijiaOldMoney;
					}
					/**
					* 设置： 司机原价/每公里
					*/
					public void setDaijiaOldMoney(Double daijiaOldMoney) {
						this.daijiaOldMoney = daijiaOldMoney;
					}

					/**
					* 获取： 现价/每公里
					*/
					public Double getDaijiaNewMoney() {
						return daijiaNewMoney;
					}
					/**
					* 设置： 现价/每公里
					*/
					public void setDaijiaNewMoney(Double daijiaNewMoney) {
						this.daijiaNewMoney = daijiaNewMoney;
					}

					/**
					* 获取： 司机热度
					*/
					public Integer getDaijiaClicknum() {
						return daijiaClicknum;
					}
					/**
					* 设置： 司机热度
					*/
					public void setDaijiaClicknum(Integer daijiaClicknum) {
						this.daijiaClicknum = daijiaClicknum;
					}

					/**
					* 获取： 代价司机详细介绍
					*/
					public String getDaijiaContent() {
						return daijiaContent;
					}
					/**
					* 设置： 代价司机详细介绍
					*/
					public void setDaijiaContent(String daijiaContent) {
						this.daijiaContent = daijiaContent;
					}




}
