package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.Post;

public class TestDataBuild {
	
	public Post add_place_payload(String name,String language,String address)
	{
		Post p = new Post();
		Location l= new Location();
		List<String> ls= new ArrayList<String>();
		ls.add("geethu");
		ls.add("abinaya");
		l.setLat(500);
		l.setLng(-567);
		
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setLocation(l);
		p.setName(name);
		p.setPhone_number("asd12345");
		p.setTypes(ls);
		p.setWebsite("http://google.com");
		
		return p;
	}

}
