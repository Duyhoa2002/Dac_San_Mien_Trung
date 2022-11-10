package dsmt.util;

import java.util.Date;

public class HTMLUtil {

	public static enum BGR {

		BLACK("bg-black"), DARK("bg-dark"),
		GRAY("bg-secondary"),
		LIGHT("bg-light"), WHITE("bg-white"),
		NONE("bg-transparent"),
		INFO("bg-info"), PRIMARY("bg-primary"),
		WARN("bg-warning"), DANGER("bg-danger"),
		SUCCESS("bg-success");

		private String value;

		private BGR(String value) {
			this.value = value;
		}
	}

	public static String alert(BGR background, String title, String alert, int miliseconds) {
		StringBuilder str = new StringBuilder();
		return str.append("<div class='toast-container position-fixed bottom-0 end-0 p-3' style='z-index: 1100;'>")
				.append("<div id='liveToast' class='toast show bg-gradient bg-opacity-75 ").append(background.value)
				.append("' role='alert' aria-live='assertive' aria-atomic='true' data-bs-delay='").append(miliseconds)
				.append("'><div class='toast-header'><strong class='me-auto'>").append(title)
				.append("</strong><button type='button' class='btn-close' data-bs-dismiss='toast' aria-label='Close'></button></div>")
				.append("<div class='toast-body fs-5'>").append(alert).append("</div></div></div>").toString();
	}

	public static String getCode(String title, String code, String email) {
		StringBuilder str = new StringBuilder();
		return str.append("<div style='font-size: 1.5rem;'><h3 style='text-transform: uppercase;'>").append(title)
				.append("</h3><p><u>ĐẶC SẢN MIỀN TRUNG</u> nhận được yêu cầu <em>cung cấp mã xác thực</em> thời gian: ")
				.append(new Date())
				.append("<br>Mã xác thực tài khoản của bạn là:<h3 style='text-align: center;color: red;'>").append(code)
				.append("</h3></p><p><em>Cảm ơn bạn đã đồng hành và sử dụng dịch vụ của chúng tôi.</em><br>")
				.append("Mọi thắc mắc xin liên hệ tới <a href='").append(email).append("'>").append(email)
				.append("</a>.<br>Xin cảm ơn!</p></div>").toString();
	}
}
