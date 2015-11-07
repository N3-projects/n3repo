package n3.home.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	public UserDetails loadUserByUsername(String userName)
	        throws UsernameNotFoundException, DataAccessException {
		
			System.out.println("*****************");
			System.out.println("登录信息："+userName);
			System.out.println("*****************");
			System.out.println();
			//角色名称(数组)
	        GrantedAuthority grantedAuths[] = obtainGrantedAuthorities("ROLE_INDEX,ROLE_ALL");
	        boolean enabled = true;
	        boolean accountNonExpired = true;
	        boolean credentialsNonExpired = true;
	        boolean accountNonLocked = true;
	        User userdetail = null;
	        if("yemao".equals(userName)) {
	        	userdetail = new User("yemao", "123456", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
	        } else if("test".equals(userName)) {
	        	userdetail = new User("test", "test", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
	        }
	        return userdetail;
	    }

	    private GrantedAuthority[] obtainGrantedAuthorities(String user) {
	    	String[] authorities = user.split(",");
	    	GrantedAuthority[] result = new GrantedAuthority[authorities.length];
	    	for(int i=0; i<authorities.length; i++) {
	    		result[i] = new GrantedAuthorityImpl(authorities[i]);
	    	}
	        return result;
	    }


}
