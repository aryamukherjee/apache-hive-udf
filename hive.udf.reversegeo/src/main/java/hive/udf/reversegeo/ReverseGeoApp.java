package hive.udf.reversegeo;

import org.apache.hadoop.hive.ql.exec.UDF;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

/**
 * @author arya mukherjee 
 *
 * The UDF will take lat and lang and return the corresponding Google address
 */
public class ReverseGeoApp extends UDF
{
    public String evaluate(double lat, double lng)
    {
    	try
    	{
	    	LatLng latLng = new LatLng(lat, lng);
	    	
	    	GeoApiContext context = new GeoApiContext.Builder()
		    	.apiKey("mysecret")
		    	.build();
	    	
	    	GeocodingResult[] results = GeocodingApi.newRequest(context).latlng(latLng).await();
	    	
	    	
	    	return results[0].formattedAddress;
    	}
    	catch(Exception e)
    	{
    		return null;
    	}
    }
}
