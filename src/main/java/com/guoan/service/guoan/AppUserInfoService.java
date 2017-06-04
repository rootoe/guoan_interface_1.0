package com.guoan.service.guoan;

import com.guoan.entity.base.common.Result;
/**
 * @Title: AppUserInfoService.java
 * @Package com.guoan.service.guoan
 * @Description: AppUserInfoService接口
 * @author 赵彤 
 * @date 2015年4月29日 下午4:07:05
 * @version V1.0
 */
public interface AppUserInfoService {
	/**
	  * @Description : 注册
	  * @创建人：赵彤  
	  * @创建时间：2015年4月29日 下午4:07:15  
	  * @return Result
	  * @throws
	 */
	public Result register(String jsonString);
	/**
	  * @Description : 短信验证码.
	  * @创建人：赵彤  
	  * @创建时间：2015年4月30日 下午3:30:29  
	  * @return Result
	  * @throws
	 */
	public Result identifyingCode(String jsonString);

	/**
	 * 找回密码
	 * @author gaochao
	 * @param jsonString
	 * @return Result
	 */
	public Result findPassword( String jsonString );
	
	/**
	 * 退出登录
	 * @author gaochao
	 * @param jsonString
	 * @return Result
	 */
	public Result logout( String jsonString );
	
	/**
	  * @Description : 登陆
	  * @创建人：赵彤  
	  * @创建时间：2015年5月4日 下午5:21:29  
	  * @return Result
	  * @throws
	 */
	public Result login(String jsonString);
	
	/**
	 * 修改密码
	 * @author gaochao
	 * @param jsonString
	 * @return
	 */
	public Result changePassword( String jsonString );
	
	/**
	 * 修改
	 * @author gaochao
	 * @param jsonString
	 * @return
	 */
	public Result updateProfile( String jsonString );
	
	/**
	 * 修改性别
	 * @author gaochao
	 * @param jsonString
	 * @return
	 */
	public Result updateSex( String jsonString );
	
	/**
	 * 修改昵称
	 * @author gaochao
	 * @param jsonString
	 * @return
	 */
	public Result updateNickname( String jsonString );
	
	/**
	 * 修改头像
	 * @author gaochao
	 * @param jsonString
	 * @return
	 */
	public Result updateAvatar( String jsonString );
	
	/**
	 * 取得用户优惠券
	 * @author gaochao
	 * @param jsonString
	 * @return
	 */
	public Result getCouponListByUser( String jsonString );

	/**
	 * 我的消息
	 * @param jsonString
	 * @return
	 */
	Result messageList(String jsonString);
}
