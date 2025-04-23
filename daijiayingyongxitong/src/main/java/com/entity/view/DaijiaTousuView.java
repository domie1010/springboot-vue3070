package com.entity.view;

import com.entity.DaijiaTousuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 代驾投诉
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("daijia_tousu")
public class DaijiaTousuView extends DaijiaTousuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 投诉类型的值
		*/
		private String daijiaTousuValue;



		//级联表 daijia_order
			/**
			* 订单号
			*/
			private String daijiaOrderUuidNumber;
			/**
			* 代驾司机订单 的 代驾司机
			*/
			private Integer daijiaOrderDaijiaId;
			/**
			* 代驾司机订单 的 用户
			*/
			private Integer daijiaOrderYonghuId;
			/**
			* 所需里程
			*/
			private Double buyLicheng;
			/**
			* 实付价格
			*/
			private Double daijiaOrderTruePrice;
			/**
			* 订单类型
			*/
			private Integer daijiaOrderTypes;
				/**
				* 订单类型的值
				*/
				private String daijiaOrderValue;
			/**
			* 支付类型
			*/
			private Integer daijiaOrderPaymentTypes;
				/**
				* 支付类型的值
				*/
				private String daijiaOrderPaymentValue;

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

	public DaijiaTousuView() {

	}

	public DaijiaTousuView(DaijiaTousuEntity daijiaTousuEntity) {
		try {
			BeanUtils.copyProperties(this, daijiaTousuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 投诉类型的值
			*/
			public String getDaijiaTousuValue() {
				return daijiaTousuValue;
			}
			/**
			* 设置： 投诉类型的值
			*/
			public void setDaijiaTousuValue(String daijiaTousuValue) {
				this.daijiaTousuValue = daijiaTousuValue;
			}














				//级联表的get和set daijia_order

					/**
					* 获取： 订单号
					*/
					public String getDaijiaOrderUuidNumber() {
						return daijiaOrderUuidNumber;
					}
					/**
					* 设置： 订单号
					*/
					public void setDaijiaOrderUuidNumber(String daijiaOrderUuidNumber) {
						this.daijiaOrderUuidNumber = daijiaOrderUuidNumber;
					}

					/**
					* 获取：代驾司机订单 的 代驾司机
					*/
					public Integer getDaijiaOrderDaijiaId() {
						return daijiaOrderDaijiaId;
					}
					/**
					* 设置：代驾司机订单 的 代驾司机
					*/
					public void setDaijiaOrderDaijiaId(Integer daijiaOrderDaijiaId) {
						this.daijiaOrderDaijiaId = daijiaOrderDaijiaId;
					}


					/**
					* 获取：代驾司机订单 的 用户
					*/
					public Integer getDaijiaOrderYonghuId() {
						return daijiaOrderYonghuId;
					}
					/**
					* 设置：代驾司机订单 的 用户
					*/
					public void setDaijiaOrderYonghuId(Integer daijiaOrderYonghuId) {
						this.daijiaOrderYonghuId = daijiaOrderYonghuId;
					}


					/**
					* 获取： 所需里程
					*/
					public Double getBuyLicheng() {
						return buyLicheng;
					}
					/**
					* 设置： 所需里程
					*/
					public void setBuyLicheng(Double buyLicheng) {
						this.buyLicheng = buyLicheng;
					}

					/**
					* 获取： 实付价格
					*/
					public Double getDaijiaOrderTruePrice() {
						return daijiaOrderTruePrice;
					}
					/**
					* 设置： 实付价格
					*/
					public void setDaijiaOrderTruePrice(Double daijiaOrderTruePrice) {
						this.daijiaOrderTruePrice = daijiaOrderTruePrice;
					}

					/**
					* 获取： 订单类型
					*/
					public Integer getDaijiaOrderTypes() {
						return daijiaOrderTypes;
					}
					/**
					* 设置： 订单类型
					*/
					public void setDaijiaOrderTypes(Integer daijiaOrderTypes) {
						this.daijiaOrderTypes = daijiaOrderTypes;
					}


						/**
						* 获取： 订单类型的值
						*/
						public String getDaijiaOrderValue() {
							return daijiaOrderValue;
						}
						/**
						* 设置： 订单类型的值
						*/
						public void setDaijiaOrderValue(String daijiaOrderValue) {
							this.daijiaOrderValue = daijiaOrderValue;
						}

					/**
					* 获取： 支付类型
					*/
					public Integer getDaijiaOrderPaymentTypes() {
						return daijiaOrderPaymentTypes;
					}
					/**
					* 设置： 支付类型
					*/
					public void setDaijiaOrderPaymentTypes(Integer daijiaOrderPaymentTypes) {
						this.daijiaOrderPaymentTypes = daijiaOrderPaymentTypes;
					}


						/**
						* 获取： 支付类型的值
						*/
						public String getDaijiaOrderPaymentValue() {
							return daijiaOrderPaymentValue;
						}
						/**
						* 设置： 支付类型的值
						*/
						public void setDaijiaOrderPaymentValue(String daijiaOrderPaymentValue) {
							this.daijiaOrderPaymentValue = daijiaOrderPaymentValue;
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







}
