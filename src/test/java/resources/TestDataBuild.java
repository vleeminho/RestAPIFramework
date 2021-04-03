package resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public static AddPlace addPlacePayload(String name, String language,String address) {
		AddPlace pl=new AddPlace();
		pl.setAccuracy(50);
		pl.setName(name);
		pl.setPhone_number("(+91) 983 893 3937");
		pl.setAddress(address);
		pl.setWebsite("http://google.com");
		pl.setLanguage(language);
		List<String> listTypes=new ArrayList<String>(Arrays.asList("shoe park","shop"));
		pl.setTypes(listTypes);
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		pl.setLocation(l);
		
		return pl;
	}

	public static String deletePlacePayload(String placeId)
	{
		return "{\\r\\n    \\\"place_id\\\":\\\""+placeId+"\\\"\\r\\n}";
	}
}
