package dsmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import dsmt.model.services.AuthService;

@Configuration
@SuppressWarnings("deprecation")
public class AuthConfig extends WebSecurityConfigurerAdapter {
	
	private final String[] PERMIT = {
		"/css/**", "/js/**", "/data/**", "/rest/**", "/security/**",
		"/dsmt/pages/home", "/dsmt/pages/product_detail"
	};	
	private final String[] FOR_ADMIN = {
			"/dsmt/pages/statistic_order",
			"/dsmt/pages/account_management",
			"/dsmt/pages/progress_management",
			"/rest/accounts","/rest/accounts/**",
	};
	private final String[] FOR_SHIPPER = {
		"/dsmt/pages/shipper_order"
	};
	private final String[] FOR_SELLER = {
		"/dsmt/pages/product_seller"
	};
	private final String[] FOR_BUYER = {
		"/dsmt/pages/product_buyer"
	};
		
	@Autowired private AuthService user;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(user);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable(); // _csrf -> code... so we need to disabled to make easier login

		// ____________________________________________________________ page accessibility
		http // allow request page authenticated
			.authorizeRequests()
        	.antMatchers(PERMIT).permitAll()
        	.antMatchers(FOR_ADMIN).hasAnyRole("OWNER","ADMIN")
        	.antMatchers(FOR_SELLER).hasAnyRole("OWNER","SELLER")
			.antMatchers(FOR_SHIPPER).hasAnyRole("OWNER","SHIPPER")
			.antMatchers(FOR_BUYER).hasAnyRole("OWNER","BUYER")
        	.anyRequest().authenticated(); // have login
		http.exceptionHandling().accessDeniedPage("/security/deniedPage");
		
		// ____________________________________________________________ form configuration
		http // form login
	 		.formLogin()
			.loginProcessingUrl("/security/login") // default [/login] => process the submitted credential
			.loginPage("/security/loginForm") // form display to login - this post method
	 		.defaultSuccessUrl("/security/loginSuccess", true)
			.failureForwardUrl("/security/loginFailed"); // login failed

        http// logout
			.logout()
			.logoutUrl("/security/logout") // default [/logout]
			.logoutSuccessUrl("/security/logoutSuccess");

	}
	
}
