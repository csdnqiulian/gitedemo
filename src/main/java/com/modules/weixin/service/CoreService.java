package com.modules.weixin.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.common.util.StringUtil;
import com.modules.weixin.bean.message.resp.Article;
import com.modules.weixin.util.HttpClient;
import com.modules.weixin.util.HttpURLConnectionUtil;
import com.modules.weixin.util.MessageUtil;


/**
 * @Description:核心服务类
 */
public class CoreService {

	/**
	 * 项目地址
	 */
	public static String PROJECT_PATH = "";
	/**
	 * 微信公众号用户名
	 */
	public static String WECHAT_USERNAME = "483916485@qq.com"; // 正式帐号
	/**
	 * 微信公众号密码
	 */
	public static String WECHAT_PASSWORD = "csdn405254033"; // 正式帐号密码

	/**
	 * 微信公众号TOKEN标识位：与接口配置信息中的 token 要一致，这里赋予什么值，在接口配置信息中的Token就要填写什么值
	 */
	public static String TOKEN = "csdnqiulian";

	/**
	 * 微信公众号原始ID
	 */
	public static String WECHAT_TOUSERNAME_ID = "gh_8df4ccec168a";// 正式的帐号

	/**
	 * 微信公众号原始ID
	 */
	public static String APPID = "wx16258ad9457460a3"; // 正式的帐号

	/**
	 * 微信公众号原始ID
	 */
	public static String APPSECRET = "2d81e303ef234a7361de97abe28c7462";// 正式的帐号

	/**
	 * 网页授权模式
	 * 1、以snsapi_base为scope发起的网页授权，是用来获取进入页面的用户的openid的，并且是静默授权并自动跳转到回调页的。
	 * 用户感知的就是直接进入了回调页（往往是业务页面）
	 * 2、以snsapi_userinfo为scope发起的网页授权，是用来获取用户的基本信息的。但这种授权需要用户手动同意
	 * ，并且由于用户同意过，所以无须关注，就可在授权后获取该用户的基本信息。
	 * 3、用户管理类接口中的“获取用户基本信息接口”，是在用户和公众号产生消息交互或关注后事件推送后
	 * ，才能根据用户OpenID来获取用户基本信息。这个接口，包括其他微信接口，都是需要该用户（即openid）关注了公众号后，才能调用成功的。
	 * 
	 * 关于特殊场景下的静默授权 1、上面已经提到，对于以snsapi_base为scope的网页授权，就静默授权的，用户无感知；
	 * 2、对于已关注公众号的用户
	 * ，如果用户从公众号的会话或者自定义菜单进入本公众号的网页授权页，即使是scope为snsapi_userinfo，也是静默授权，用户无感知。
	 */
	public static String SCOPE = "snsapi_userinfo";

	/**
	 * 保存所有expires_in有值的数据 expires_in 接口调用凭证超时时间，单位（秒） access_token_no_empower
	 * 基础access_token的map jsapi_ticket 页面js所需要的ticket
	 * 
	 * */
	public static Map<String, Map<String, String>> ALL_CAHCE_MAP = new HashMap<String, Map<String, String>>();

	/**
	 * 获取access接口
	 */
	public static String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/**
	 * 上传图片接口
	 */
	public static String UPLOAD_PIC_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
	/**
	 * 创建菜单接口
	 */
	public static String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	/**
	 * 删除菜单接口
	 */
	public static String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	/**
	 * 群发接口
	 */
	public static String GROUP_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	/**
	 * 群发接口参数
	 */
	public static String GROUP_SEND_URL_PARAM = "{\"filter\":{\"is_to_all\":true\"group_id\":\"group_id_value\"},\"mpnews\":{\"media_id\":\"media_id_value\"},\"msgtype\":\"mpnews\"}";
	/**
	 * 图文接口
	 */
	public static String UPLOAD_NEWS_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
	/**
	 * 新增临时素材
	 */
	public static String UPLOAD_TEMPMEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=MEDIATYPE";

	/**
	 * 关注用户列表
	 */
	public static String GET_USER_OPENIDS = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";

	/**
	 * 用户的基本信息
	 */
	public static String GET_USER_BASE_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	/**
	 * 获取用户授权code
	 */
	public static String GET_USER_OPENID_BY_WX = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

	/**
	 * 授权获取时候的state
	 */
	public static String STATE = "111";

	/**
	 * 获取授权access_token
	 */
	public static String GET_ACCESS_TOKEN_BY_WX = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	/**
	 * 获取授权jsapi_ticket
	 */
	public static String GET_JSAPI_TICKET_BY_WX = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	/**
	 * 下载微信服务器图片
	 */
	public static String DOWNLOADIMAGE_BY_WX = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	/**
	 * 处理微信发来的请求：这里统一回复图文消息
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml 请求解析：调用消息工具类MessageUtil 解析微信发来的xml 格式的消息，解析的结果放在HashMap 里
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息内容
			String msgcontent = requestMap.get("Content");
			// 相对路径
			String path = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath()
					+ "/";
			System.out.println("======================" + path);

		/*	// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 回复图文消息
			List<Article> articleList = new ArrayList<Article>();
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);
			String jszx_url = "http://wcwzw.wangcheng.gov.cn/wx/instantMessaging_zxorglist.do";
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)
					&& !StringUtil.isBlank(msgcontent)) {
				articleList = buildArticleList("znjqr", msgcontent, path);
				if (articleList.size() > 0) {
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml 字符串
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
					return respMessage;
				} else {
					textMessage.setContent("很抱歉，没有找到“" + msgcontent
							+ "”的相关信息，请点击&lta href='" + jszx_url
							+ "'&gt“即时咨询”&lt/a&gt，望城区政务服务中心各单位专业客服将竭诚为您服务。");

					respMessage = MessageUtil.textMessageToXml(textMessage);
					return respMessage;
				}

			} // 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {

					*//**
					 * 订阅之后自动注册帐号 
					 *//*
					BackwechatUserAllInfoService backwechatUserAllInfoService = (BackwechatUserAllInfoService) SpringContextUtil
							.getBean("backwechatUserAllInfoService");

					BackwechatUserAllInfo backwechatUserAllInfo = new BackwechatUserAllInfo();

					String user_all_wx_no = fromUserName; // 微信号

					Map<String, String> userbaseinfo_map = new HashMap<String, String>();

					userbaseinfo_map = getUserBaseInfo(user_all_wx_no);

					String user_all_tx_img = StringUtil.deNull(userbaseinfo_map
							.get("headimgurl")); // 头像
					String user_all_wx_nc = StringUtil.deNull(userbaseinfo_map
							.get("nickname")); // 昵称
					String user_all_user_sex = StringUtil
							.deNull(userbaseinfo_map.get("sex")); // 性别
																	// 值为1时是男性，值为2时是女性，值为0时是未知
					String user_all_user_id = StringUtil
							.deNull(userbaseinfo_map.get("unionid")); // 用户微信id

					backwechatUserAllInfo.setUser_all_wx_no(user_all_wx_no);
					backwechatUserAllInfo.setUser_all_user_id(user_all_user_id);
					backwechatUserAllInfo.setUser_all_tx_img(user_all_tx_img);
					backwechatUserAllInfo.setUser_all_wx_nc(user_all_wx_nc);
					backwechatUserAllInfo
							.setUser_all_user_sex(user_all_user_sex);

					backwechatUserAllInfoService
							.regiestOrReplceWXNoToUserAllInfo(backwechatUserAllInfo);

					*//**
					 * 订阅之后自动注册帐号 add by jia.tong 2016-10-12 end
					 *//*


					Article article = new Article();
					article.setTitle("您好！欢迎关注智慧望城坡公众服务号。");
					article.setDescription(null);
					article.setPicUrl(path + "res/wechat/bmfw/dttp2.jpg");
					article.setUrl("http://www.yuelu.gov.cn/wcpjd/");
					articleList.add(article);

					article = new Article();
					article.setTitle("望城坡街道办事处简介");
					article.setDescription(null);
					article.setPicUrl(path + "res/wechat/images/gh.png");
					article.setUrl(path
							+ "wechatContent_detail.do?id=14730404697400");
					articleList.add(article);
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml 字符串
					respMessage = MessageUtil.newsMessageToXml(newsMessage);

					return respMessage;
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息

				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 自定义菜单权没有开放，暂不处理该类消息
					System.out.println("自定义菜单事件...");
					String eventKey = requestMap.get("EventKey");

					if ("WCXC".equals(eventKey)) { // 望城宣传
						articleList = buildArticleList("WCXC", msgcontent, path);
						respContent = "今日暂时没有\"望城宣传\"的相关信息！";
					} else if ("WCXW".equals(eventKey)) {
						articleList = buildArticleList("WCXW", msgcontent, path);
						respContent = "今日暂时没有\"政务资讯\"的相关信息！";
					} else if ("TZGG".equals(eventKey)) {
						articleList = buildArticleList("TZGG", msgcontent, path);
						respContent = "今日暂时没有\"望城坡动态\"的相关信息！";
					} else if ("ZNDB".equals(eventKey)) {
						respContent = "很抱歉，智能导办还在开发当中...";
						articleList = buildArticleList("ZNDB", msgcontent, path);
					} else if ("znjqr".equals(eventKey)) {
						Article article = new Article();
						article.setTitle("智能问答");
						article.setDescription("您好！请在微信对话框输入您要查询或办理业务的关键字，客服“小微”将在第一时间进行回复。如不能查到您需要的信息，请在工作日上班时间点击&lta href='"
								+ jszx_url
								+ "'&gt“即时咨询”&lt/a&gt，望城区政务服务中心各单位专业客服将竭诚为您服务。");
						article.setPicUrl(path
								+ "res/wechat/images/xiaowei.png");
						articleList.add(article);
					}
					// 判断list为null的情况;直接返回文本信息
					if (articleList == null || articleList.size() == 0) {
						textMessage.setContent(respContent);
						respMessage = MessageUtil.textMessageToXml(textMessage);
						return respMessage;
					}
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml 字符串
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
					System.out
							.println("respMessage============================"
									+ respMessage);
					return respMessage;
				}
			} else {
				// 其他类型的消息均回复“很抱歉，没有与您提问的关键字""相关的信息”
				respContent = "很抱歉，没有找到的相关信息。";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
				return respMessage;
			}*/
			return respMessage;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;

	}

	/**
	 * 封装List<Article>
	 * 
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public static List<Article> buildArticleList(String flag,
			String msgcontent, String path) throws Exception {
		List<Article> articleList = new ArrayList<Article>();
		/*// 获取信息
		BackwechatContentService backwechatService = (BackwechatContentService) SpringContextUtil
				.getBean("backwechatContentService");
		BackwechatContentVo backwechatVo = new BackwechatContentVo();
		List<BackwechatContentVo> bList = new ArrayList<BackwechatContentVo>();
		if ("ZNDB".equals(flag)) {
			// 借用字段传递条件
			Article article = new Article();
			article.setTitle("智能导办");
			article.setDescription("智能导办是智慧望城坡微信公众平台为您提供的政务信息咨询服务功能，以全新的方式为您提供更加贴心的便捷服务。");
			article.setPicUrl(path + "uplodefile/wechatuploadfile/zndb.jpg");
			article.setUrl(null);
			articleList.add(article);
		} else if ("SY".equals(flag)) {

		} else if ("znjqr".equals(flag)) { // 文本消息回复响应 回复事项机构查询信息
			// 借用字段传递条件
			backwechatVo.setTitle(msgcontent);
			bList = backwechatService.getBaseOrgInfoList(backwechatVo);
		} else if ("WCXC".equals(flag)) {
			// 最多取10条
			backwechatVo.setType("1");
			backwechatVo.setLastupdatetimeString("5");
			bList = backwechatService.selectAllWxjzList(backwechatVo);
		} else if ("TZGG".equals(flag)) {
			// 最多取10条
			backwechatVo.setType("67"); // 通知公告
			backwechatVo.setLastupdatetimeString("5");
			bList = backwechatService.selectAllWxjzList(backwechatVo);
		} else if ("WCXW".equals(flag)) {
			backwechatVo.setType("17");
			backwechatVo.setLastupdatetimeString("5");
			bList = backwechatService.selectAllWxjzList(backwechatVo);
		}
		if (bList != null && bList.size() > 0) {
			for (int i = 0; i < bList.size(); i++) {

				if (articleList.size() > 8) {
					break;
				}

				BackwechatContentVo temp = bList.get(i);
				Article article = new Article();

				if (i != 0 && ("WCXW".equals(flag) || "TZGG".equals(flag))
						&& !StringUtil.isBlank(temp.getPublishtimeStr())) {
					article.setTitle(temp.getTitle() + "\n  "
							+ temp.getPublishtimeStr());
				} else {
					article.setTitle(temp.getTitle());
				}
				article.setDescription(temp.getIntroduction());

				// 图片处理
				String picurl = temp.getPicurl();
				if (!StringUtil.isBlank(picurl)) {
					if (picurl.startsWith("http")) {
					} else {
						if (picurl.indexOf("/wx/") > -1) {
							picurl = path + picurl.split("/wx/")[1];
						} else {
							picurl = path + picurl;
						}
					}
					
					 * // 第一个是事项的话，设置默认大图吧 if ("TZGG".equals(flag)){//
					 * article.setPicUrl(null); }else if (i==0 &&
					 * "S".equals(temp.getType())) { article.setPicUrl(path +
					 * "res/wechat/images/dt1.jpg"); }else {
					 * article.setPicUrl(picurl); }
					 
					if (i == 0) {
						// 通知公告和事项用大图
						// || "TZGG".equals(flag) 通知公告有图片的时候用图片 没有图片的时候用死的大图 add
						// by jia.tong 2016-10-18
						if ("S".equals(temp.getType())) {
							article.setPicUrl(path
									+ "res/wechat/images/dt1.jpg");
						} else {
							article.setPicUrl(picurl);
						}
					} else if ("TZGG".equals(flag)) {
						*//**
						 * 为什么要写死用喇叭 add by jia.tong 2016-10-18
						 *//*
						// article.setPicUrl(path +
						// "res/wechat/images/laba.jpg");

						article.setPicUrl(picurl);
					} else {
						article.setPicUrl(picurl);
					}
				} else {
					if (i == 0) {

						*//**
						 * 没有图片的时候第一条数据默认的大图路径 不是第一条则用喇叭 add by jia.tong
						 * 2016-10-18
						 *//*
						if ("TZGG".equals(flag)) {
							article.setPicUrl(path
									+ "res/wechat/bmfw/dttp2.jpg");
						} else {
							article.setPicUrl(path
									+ "res/wechat/images/dt1.jpg");
						}

					} else if ("TZGG".equals(flag)) {
						article.setPicUrl(path + "res/wechat/images/laba.jpg");
					} else {
						article.setPicUrl(path + "res/wechat/images/gh.png");
					}
				}
				// url处理
				System.out.println("temp.getType()======================"
						+ temp.getType());
				if ("S".equals(temp.getType())) {
					if (!StringUtil.isBlank(temp.getUrl())) {
						article.setUrl(temp.getUrl());
					} else {
						article.setUrl(path + "wechatBuss_workDetail.do?ecId="
								+ temp.getId());
					}
				} else {
					if (!StringUtil.isBlank(temp.getUrl())) {
						article.setUrl(temp.getUrl());
					} else {
						article.setUrl(path + "wechatContent_detail.do?id="
								+ temp.getId());
					}
				}
				articleList.add(article);
			}

			// 查看更多
			if ("WCXC".equals(flag)) {
				Article article = new Article();
				article.setTitle("查看更多望城宣传");
				article.setUrl(path + "wechatContent_publicity.do");
				articleList.add(article);
			} else if ("WCXW".equals(flag)) {
				Article article = new Article();
				article.setTitle("查看更多政务资讯");
				article.setUrl(path + "wechatContent_zwmessage.do");
				articleList.add(article);
			} else if ("TZGG".equals(flag)) {
				Article article = new Article();
				article.setTitle("查看更多望城坡动态");
				article.setUrl(path + "wechatContent_typeList.do?type=67");
				articleList.add(article);
			}
		}*/
		return articleList;
	}

	/**
	 * 
	 1 上传图文消息内的图片获取URL【订阅号与服务号认证后均可用】 2 上传图文消息素材【订阅号与服务号认证后均可用】 3
	 * 根据分组进行群发【订阅号与服务号认证后均可用】 4 根据OpenID列表群发【订阅号不可用，服务号认证后可用】 5
	 * 删除群发【订阅号与服务号认证后均可用】 6 预览接口【订阅号与服务号认证后均可用】 7 查询群发消息发送状态【订阅号与服务号认证后均可用】 8
	 * 事件推送群发结果
	 * 
	 * @throws Exception
	 */
	public static void groupSending() throws Exception {/*
														 * BackwechatContentService
														 * backwechatService =
														 * (BackwechatContentService
														 * )
														 * SpringContextUtil.getBean
														 * (
														 * "backwechatContentService"
														 * );
														 * BackwechatContentVo
														 * backwechatVo = new
														 * BackwechatContentVo
														 * ();
														 * List<BackwechatContentVo
														 * > bList =
														 * backwechatService
														 * .getBackList
														 * (backwechatVo); if
														 * (bList
														 * !=null&&bList.size
														 * ()>0) { // 上传图片
														 * String access_token =
														 * getAccess_token(); //
														 * String url =
														 * UPLOAD_PIC_URL
														 * .replace
														 * ("ACCESS_TOKEN"
														 * ,access_token);
														 * String
														 * upload_tempmedia_url_
														 * =
														 * UPLOAD_TEMPMEDIA_URL
														 * .replace
														 * ("ACCESS_TOKEN"
														 * ,access_token
														 * ).replace
														 * ("MEDIATYPE",
														 * "image"); String url
														 * =
														 * upload_tempmedia_url_
														 * ;
														 * 
														 * String path
														 * =CoreService
														 * .class.getResource
														 * ("/").getPath(); path
														 * =
														 * path.split("WEB-INF"
														 * )[0]+
														 * "/uplodefile/wechatuploadfile/"
														 * ; //File file = new
														 * File
														 * (path,"more02.png");
														 * 
														 * // 先把已经定好的图片传上去 File
														 * file = new
														 * File(path,"more.png"
														 * );
														 * HttpURLConnectionUtil
														 * httpURLConnectionUtil
														 * = new
														 * HttpURLConnectionUtil
														 * (url,
														 * null,file.getPath());
														 * String
														 * more_pic_re_url =
														 * httpURLConnectionUtil
														 * .executeForFile();
														 * 
														 * file = new
														 * File(path,"more02.png"
														 * );
														 * httpURLConnectionUtil
														 * = new
														 * HttpURLConnectionUtil
														 * (url,
														 * null,file.getPath());
														 * String
														 * more02_pic_re_url =
														 * httpURLConnectionUtil
														 * .executeForFile();
														 * 
														 * file = new
														 * File(path,"zwtp.jpg"
														 * );
														 * httpURLConnectionUtil
														 * = new
														 * HttpURLConnectionUtil
														 * (url,
														 * null,file.getPath());
														 * String
														 * zanwutupian_pic_re_url
														 * =
														 * httpURLConnectionUtil
														 * .executeForFile();
														 * 
														 * List<Map> list = new
														 * ArrayList<Map>(); for
														 * (int i = 0; i <
														 * bList.size(); i++) {
														 * BackwechatContentVo
														 * temp = bList.get(i);
														 * // 构造数据 Map map = new
														 * HashMap(); String
														 * picurl =
														 * temp.getPicurl(); //
														 * 第一条如果没有图片的话，就设置为空 if
														 * (
														 * StringUtil.isBlank(picurl
														 * )) { if (i == 0) {
														 * map
														 * .put("thumb_media_id"
														 * ,
														 * zanwutupian_pic_re_url
														 * ); } else {
														 * map.put("thumb_media_id"
														 * , more_pic_re_url); }
														 * } else { String
														 * picname =
														 * picurl.substring
														 * (picurl
														 * .lastIndexOf("\\") +
														 * 1); picurl =
														 * PROJECT_PATH +
														 * "uplodefile/wechatuploadfile/"
														 * + picname; file = new
														 * File(path,picname);
														 * httpURLConnectionUtil
														 * = new
														 * HttpURLConnectionUtil
														 * (url,
														 * null,file.getPath());
														 * String
														 * temp_pic_re_url =
														 * httpURLConnectionUtil
														 * .executeForFile();
														 * map
														 * .put("thumb_media_id"
														 * , temp_pic_re_url); }
														 * /
														 * /map.put("thumb_media_id"
														 * , id);
														 * map.put("author",
														 * "DMKFZHFW");
														 * map.put("title",
														 * temp.getTitle());
														 * map.put("content",
														 * temp
														 * .getTitle()+"...");
														 * map.put("digest",
														 * temp
														 * .getTitle()+"......."
														 * );
														 * map.put("show_cover_pic"
														 * , "0"); if
														 * ("sxxx".equals
														 * (temp.getType())) {
														 * map
														 * .put("content_source_url"
														 * , PROJECT_PATH +
														 * "wechat_bszndetail.do?ecId="
														 * + temp.getId()); }
														 * else { map.put(
														 * "content_source_url",
														 * PROJECT_PATH +
														 * "backwechat_detail.do?id="
														 * + temp.getId()); }
														 * list.add(map); }
														 * 
														 * Map map3 = new
														 * HashMap();
														 * map3.put("articles",
														 * list);
														 * 
														 * Gson gson = new
														 * Gson(); String
														 * articles_text =
														 * gson.toJson(map3);//
														 * 转换成json数据格式
														 * System.out
														 * .println(articles_text
														 * );
														 * 
														 * // 上传图文 String
														 * uploadnewsurl =
														 * UPLOAD_NEWS_URL
														 * .replace
														 * ("ACCESS_TOKEN"
														 * ,access_token);
														 * httpURLConnectionUtil
														 * = new
														 * HttpURLConnectionUtil
														 * (uploadnewsurl,
														 * articles_text); //
														 * {"type"
														 * :"news","media_id":
														 * "GM3HsLVdZa88beNacj0g2PR2sYcSkVhAuEOkxj78sCbArWXIlTPXuktrk2DWirVD"
														 * ,
														 * "created_at":1447466168
														 * } String
														 * uploadnews_text =
														 * httpURLConnectionUtil
														 * .execute();
														 * System.out
														 * .println(uploadnews_text
														 * ); JSONObject jsonObj
														 * =
														 * JSONObject.fromObject
														 * (uploadnews_text);
														 * String media_id =
														 * jsonObj
														 * .getString("media_id"
														 * );
														 * 
														 * // 群发消息 String
														 * group_send_url_ =
														 * GROUP_SEND_URL
														 * .replace
														 * ("ACCESS_TOKEN"
														 * ,access_token);
														 * //String
														 * group_send_url_param_
														 * =
														 * GROUP_SEND_URL_PARAM
														 * .replace
														 * ("group_id_value",
														 * "")
														 * .replace("media_id_value"
														 * , media_id);
														 * 
														 * JsonObject jObj = new
														 * JsonObject();
														 * JsonObject filter =
														 * new JsonObject();
														 * filter
														 * .addProperty("is_to_all"
														 * , true);
														 * filter.addProperty
														 * ("group_id", "");
														 * jObj.add("filter",
														 * filter);
														 * 
														 * JsonObject mpnews =
														 * new JsonObject();
														 * mpnews
														 * .addProperty("media_id"
														 * , media_id);
														 * jObj.add("mpnews",
														 * mpnews);
														 * 
														 * jObj.addProperty(
														 * "msgtype", "mpnews");
														 * String
														 * group_send_url_param_
														 * = jObj.toString();
														 * 
														 * httpURLConnectionUtil
														 * = new
														 * HttpURLConnectionUtil
														 * (group_send_url_,
														 * group_send_url_param_
														 * ); String
														 * group_send_url_returntext
														 * =
														 * httpURLConnectionUtil
														 * .execute();
														 * System.out.println(
														 * "【==============每日主动推送开始==============】"
														 * );
														 * System.out.println(
														 * "【推送时间：】"
														 * +DateUtil.getDateString
														 * ());
														 * System.out.println
														 * ("【推送url：】"
														 * +group_send_url_);
														 * System
														 * .out.println("【推送参数：】"
														 * +
														 * group_send_url_param_
														 * );
														 * System.out.println(
														 * "【反馈结果：】"+
														 * group_send_url_returntext
														 * );
														 * System.out.println(
														 * "【==============每日主动推送结束==============】"
														 * ); }
														 */
	}

	private static boolean checkTime(Map<String, String> map) {
		long expires_in = Long.valueOf(map.get("expires_in"));
		long newDate = System.currentTimeMillis();
		long oldDate = Long.valueOf(map.get("date"));

		if ((newDate - oldDate) > expires_in * 1000) {
			return true;
		}
		return false;
	}

	/**
	 * 获取Access_token
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getAccess_token() {
		String url = GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET",
				APPSECRET);
		Map<String, String> map = ALL_CAHCE_MAP.get("access_token_no_empower");
		if (map != null && map.size() > 0) {

			if (checkTime(map)) {
				map = saveMap(url);
			}

		} else {
			map = saveMap(url);
		}
		if (!StringUtil.isBlank(map.get("access_token"))) {
			ALL_CAHCE_MAP.put("access_token_no_empower", map);
		}

		return map.get("access_token");
	}

	private static Map<String, String> saveMap(String url) {
		Map<String,String> map = new HashMap<String,String>();
		HttpClient client = new HttpClient(url);
		String re = client.get();
		JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
		JsonObject json = jsonparer.parse(re).getAsJsonObject();
		Set<Entry<String, JsonElement>> set = json.entrySet();
		
		for(Entry<String, JsonElement> en : set){
			map.put(en.getKey(), en.getValue().getAsString());
		}
		map.put("date", String.valueOf(System.currentTimeMillis()));
		
		return map;
	}

	/**
	 * 创建自定义菜单
	 * @return
	 * @throws Exception
	 */
	public static String createMenu(String path) throws Exception {
		// 先删除菜单
		deleteMenu();

		File file = new File(CoreService.class.getResource("/menu.json").getPath());
		String content = FileUtils.readFileToString(file, "utf-8");
		System.out.println("======================================" + content);
		if (!StringUtil.isBlank(content)) {
			// 暂时写死
			content = content.replaceAll("http://path", path);
		}
		System.out.println("===========菜单内容 ↓===========");
		System.out.println(content);
		System.out.println("===========菜单内容↑===========");

		String access_token = getAccess_token();
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", access_token);
		HttpURLConnectionUtil httpURLConnectionUtil = new HttpURLConnectionUtil(
				url, content);
		String re = httpURLConnectionUtil.execute();
		return re;
	}

	/**
	 * 获取关注的用户列表 add by jia.tong 2016-10-10
	 * 
	 * @return
	 * @throws Exception
	 */
	public static List<String> getUserList() throws Exception {
		List<String> user_list = new ArrayList<String>();

		/*String access_token = getAccess_token();
		String url = GET_USER_OPENIDS.replace("ACCESS_TOKEN", access_token);
		HttpURLConnectionUtil httpURLConnectionUtil = new HttpURLConnectionUtil(
				url);
		String re = httpURLConnectionUtil.execute("GET");

		JSONObject jsonObj = new JSONObject(re);
		System.out.println("users" + jsonObj.get("data"));
		JSONObject datajson = new JSONObject(jsonObj.get("data").toString());
		System.out.println(datajson.toString());
		JSONArray openidjson = new JSONArray(datajson.get("openid").toString());

		for (int i = 0; i < openidjson.length(); i++) {
			user_list.add(i, openidjson.getString(i));
		}*/
		return user_list;
	}

	/**
	 * 根据授权code获取授权信息
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getAccess_token_by_code(String code)
			throws Exception {

		String url = GET_ACCESS_TOKEN_BY_WX.replace("APPID", APPID)
				.replace("SECRET", APPSECRET).replace("CODE", code);

		Map<String, String> access_token_map = saveMap(url);

		return access_token_map;
	}

	/**
	 * 根据用户openid获取用户信息（非网页授权）
	 * 
	 * @param openid
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> getUserBaseInfo(String openid)
			throws Exception {
		Map<String, String> user_baseinfo = new HashMap<String, String>();

		/*String access_token = getAccess_token();
		String url = GET_USER_BASE_INFO.replace("ACCESS_TOKEN", access_token);

		url = url.replace("OPENID", openid);

		HttpURLConnectionUtil httpURLConnectionUtil = new HttpURLConnectionUtil(
				url);
		String re = httpURLConnectionUtil.execute("GET");

		JSONObject jsonObj = new JSONObject(re);
		System.out.println("userbaseinfo" + jsonObj.toString());

		Iterator<String> it = jsonObj.keys();
		while (it.hasNext()) {
			String key = it.next();
			user_baseinfo.put(key, jsonObj.getString(key));
		}*/

		return user_baseinfo;
	}

	public static String deleteMenu() throws Exception {
		String access_token = getAccess_token();
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", access_token);
		HttpURLConnectionUtil httpURLConnectionUtil = new HttpURLConnectionUtil(
				url);
		String re = httpURLConnectionUtil.execute();
		return re;
	}

	public static void main(String[] args) {
		try {
			// System.out.println(getAccess_token());
			// createMenu();
			// deleteMenu();
			// groupSending();
			/*
			 * File file = new
			 * File(CoreService.class.getResource("/menu.json").getPath());
			 * String content = FileUtils.readFileToString(file, "utf-8");
			 * System
			 * .out.println("======================================"+content);
			 * if (content.startsWith("?")) { content = content.substring(1); }
			 * 
			 * String a =
			 * "{\"button\":[ {      \"name\":\"认证上网\",      \"sub_button\":[      {          \"type\":\"view\",          \"name\":\"我要上网\",          \"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx343a4c2aef3e9349&redirect_uri=http%3A%2F%2Fwww.qq.com%2Foauth2.html&response_type=code&scope=snsapi_userinfo&state=wx343a4c2aef3e9349#wechat_redirect\"       }]  },{      \"name\":\"走进望城\",      \"sub_button\":[      {          \"type\":\"click\",          \"name\":\"望城宣传\",          \"key\":\"WCXC\"       },       {          \"type\":\"click\",          \"name\":\"望城新闻\",          \"key\":\"WCXW\"       },        {          \"type\":\"view\",          \"name\":\"我要办事\",          \"url\":\"path/wechatBuss_genneral.do\"       },      {	          \"type\":\"click\",          \"name\":\"办事查询\",          \"key\":\"path/wechatBuss_queryIndex.do\"       }]  },{      \"name\":\"望城生活\",      \"sub_button\":[       {          \"type\":\"view\",          \"name\":\"便民查询\",          \"url\":\"path/wechatBuss_appointment.do\"       }]  },{          \"type\":\"view\",          \"name\":\"便民查询\",          \"url\":\"path/wechatBuss_userIndex.do\"       }  ]}"
			 * ;
			 * 
			 * System.out.println("=====================================---"+content
			 * ); if (content.startsWith("?")) { content = content.substring(1);
			 * }
			 */

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 授权并且跳转指定地址
	 * 
	 * @param jumpUrl
	 * @return
	 * @throws Exception
	 */
	public static void authorization_jump(String jumpUrl) throws Exception {
		/*HttpServletRequest request = ServletActionContext.getRequest();

		HttpServletResponse resp = ServletActionContext.getResponse();

		String server_url = ServletActionContext.getRequest().getScheme()
				+ "://" + ServletActionContext.getRequest().getServerName()
				+ ServletActionContext.getRequest().getContextPath();
		String redirect_uri = server_url + jumpUrl;

		redirect_uri = URLEncoder.encode(redirect_uri, "UTF-8");// 授权后重定向的回调链接地址，请使用urlencode对链接进行处理（文档要求）

		System.out.println(redirect_uri);

		// 按照文档要求拼接访问地址
		String url = GET_USER_OPENID_BY_WX
				.replace("REDIRECT_URI", redirect_uri);

		url = url.replace("APPID", APPID);

		url = url.replace("SCOPE", SCOPE);

		url = url.replace("STATE", STATE);

		// 设置编码
		request.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");

		resp.sendRedirect(url);// 跳转到要访问的地址
		 */	
	}

	// 获取微信js中的config中signature签名所需要的jsapi_ticket
	public static String getJsapi_ticket() {
		String access_token = getAccess_token();
		String url = GET_JSAPI_TICKET_BY_WX.replace("ACCESS_TOKEN",
				access_token);

		Map<String, String> map = ALL_CAHCE_MAP.get("jsapi_ticket");
		if (map != null && map.size() > 0) {

			if (checkTime(map)) {
				map = saveMap(url);
			}

		} else {
			map = saveMap(url);
		}
		if (!StringUtil.isBlank(map.get("ticket"))) {
			ALL_CAHCE_MAP.put("jsapi_ticket", map);
		}

		return map.get("ticket");
	}

	/*public static String getSignature(JSONObject obj, String url)
			throws Exception {
		String nonceStr = obj.getString("nonceStr");
		String timestamp = obj.getString("timestamp");
		String ticket = getJsapi_ticket();

		StringBuffer signatureStr = new StringBuffer();
		signatureStr.append("jsapi_ticket=" + ticket);
		signatureStr.append("&noncestr=" + nonceStr);
		signatureStr.append("&timestamp=" + timestamp);
		signatureStr.append("&url=" + url);

		return SHA1(signatureStr.toString());
	}*/

	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	// 下载微信服务器上媒体文件
	public static String downloadMedia(String mediaId, String savePath)
			throws Exception {
		String access_token = getAccess_token();
		String requestUrl = DOWNLOADIMAGE_BY_WX.replace("ACCESS_TOKEN",
				access_token).replace("MEDIA_ID", mediaId);
		String fileName = null;
		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setRequestMethod("GET");

		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		if (!savePath.endsWith("/")) {
			savePath += "/";
		}

		// 根据内容类型获取扩展名
		String fileExt = getFileEndWitsh(conn.getHeaderField("Content-Type"));
		// 将mediaId作为文件名
		fileName = mediaId + fileExt;
		String filePath = savePath + mediaId + fileExt;

		BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		FileOutputStream fos = new FileOutputStream(new File(filePath));
		byte[] buf = new byte[8096];
		int size = 0;
		while ((size = bis.read(buf)) != -1)
			fos.write(buf, 0, size);
		fos.close();
		bis.close();

		conn.disconnect();
		return fileName;
	}

	private static String getFileEndWitsh(String ext) {
		return "." + ext.substring(ext.lastIndexOf("/") + 1, ext.length());
	}
}
