package baekhwa.configuration.auth.dto;

import java.util.Map;

import baekhwa.domain.dto.Role;
import baekhwa.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
//DTO객체입니다.
@Getter
public class OAuthAttributes {
	
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;
	
	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
			String picture) {
		super();
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}
	public User toEntity() {
		return User.builder()
				.name(name)
				.email(email)
				.picture(picture)
				.pw("oauthuser")
				.role(Role.GUEST)
				.build();
	}
	
	public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String,Object> attributes) {
		
		if( registrationId.equals("google") ){
			return ofGoogle(userNameAttributeName, attributes);
		}else if(registrationId.equals("naver")) {
			return ofNaver("id", attributes);
		}
		return null;
	}
	////////////네이버 정보 갖고오기////////////
	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> response=(Map<String, Object>) attributes.get("response");
		return OAuthAttributes.builder()
				.name( (String) response.get("name") )
				.email( (String) response.get("email") )
				.picture( (String) response.get("profile_image") )
				.attributes(response)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}
	///////////구글정보 갖고오기///////////
	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes) {
		return OAuthAttributes.builder()
				.name( (String) attributes.get("name") )
				.email( (String) attributes.get("email") )
				.picture( (String) attributes.get("picture") )
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}

}
