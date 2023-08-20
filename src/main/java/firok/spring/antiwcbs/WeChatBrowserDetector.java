package firok.spring.antiwcbs;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * 用于检测用户当前所用浏览器环境是否为微信 WebView 或移动端浏览器
 * @since 1.0.0
 * @author Firok
 * */
@ConditionalOnExpression("${firok.spring.antiwcbs.enable-detector:false}")
@Component
public class WeChatBrowserDetector
{
	@Autowired
	HttpServletRequest request;

	private boolean isHeaderInclude(String... values)
	{
		var header = request != null ? request.getHeader(HttpHeaders.USER_AGENT) : null;
		if(header == null) return false;
		for(var value : values) if(header.contains(value)) return true;
		return false;
	}

	private static final String HeaderWeChatPC = "WindowsWechat";
	private static final String HeaderWeChatMac = "MacWechat";
	private static final String HeaderWeChatPhone = "MicroMessenger";
	private static final String HeaderAndroid = "Android";
	private static final String HeaderIPhone = "iPhone OS";
	private static final String HeaderWindowsPhone = "Windows Phone";
	private static final String HeaderMobile = "Mobile";

	public boolean isWeChatPC() { return isHeaderInclude(HeaderWeChatPC); }
	public boolean isWeChatMac() { return isHeaderInclude(HeaderWeChatMac); }
	public boolean isWeChatPhone() { return isHeaderInclude(HeaderWeChatPhone); }

	public boolean isWeChat() { return isHeaderInclude(HeaderWeChatPC, HeaderWeChatMac, HeaderWeChatPhone); }
	public boolean isPhone() { return isHeaderInclude(HeaderAndroid, HeaderIPhone, HeaderWindowsPhone, HeaderMobile); }
}
