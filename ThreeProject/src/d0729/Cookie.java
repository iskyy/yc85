package d0729;

public class Cookie {
	private String name;
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public int getMaxAge() {
		return maxAge;
	}

	private String value;
	private int maxAge=-1;

	public Cookie(String name,String value) {
		this.name=name;
		this.value=value;
	}
	
	public String toString() {
		String s= "Set-Cookie: %s=%s;";
		s=String.format(s, name,value);
		if(maxAge > -1) {
			s +="Max-Age="+ maxAge +";";
		}
		s+="\n";
		return s;
	}

	public void setMax(int maxAge) {
		this.maxAge=maxAge;
	}

}
