package com.jasperserver.input;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.fill.JRFillParameter;

/**
 * 
 * @author Paulo P.
 *
 */
public class Criteria extends JRDefaultScriptlet{
	private String listToCsv(List<Object> listOfStrings, char separator) {
	    StringBuilder sb = new StringBuilder();
	    // all but last
	    for(int i = 0; i < listOfStrings.size() - 1 ; i++) {
	    	// Verify style of Object
	        sb.append(listOfStrings.get(i).toString());
	        sb.append(separator);
	    }
	    // last string, no separator
	    if(listOfStrings.size() > 0){
	        sb.append(listOfStrings.get(listOfStrings.size()-1));
	    }
	    return sb.toString();
	}
	
	private String getParameterText(JRFillParameter param) {
		if (param.getValue() == null) {
			return "";
		}
		String result = "";
		String className = param.getValueClassName();
		if (className == "java.util.List") {
				result = listToCsv((List<Object>) param.getValue(), ',');
		}
		else {
			
			result = param.getValue().toString();
		}
    	return result;
    }
    
    public String hello() throws JRScriptletException
    {
    	StringBuilder sbResult = new StringBuilder("");

    	for (Map.Entry<String, JRFillParameter> entry : this.parametersMap.entrySet())
    	{
        	sbResult.append(entry.getKey() + "\n");
    	}
    	return sbResult.toString();
    }
    
    public String show() throws JRScriptletException
    {
    	StringBuilder sbResult = new StringBuilder("");
    	for (Map.Entry<String, JRFillParameter> entry : this.parametersMap.entrySet())
    	{
            sbResult.append(entry.getKey()+ " : " + getParameterText(entry.getValue())  + "\n");
    	}
    	return sbResult.toString();
    }
    
    public String showSystemDefined() throws JRScriptletException
    {
    	StringBuilder sbResult = new StringBuilder("");
    	for (Map.Entry<String, JRFillParameter> entry : this.parametersMap.entrySet())
    	{
    		if (entry.getValue().isSystemDefined()) {
            	sbResult.append(entry.getKey() + "\n");
    		}
    	}
    	return sbResult.toString();
    }

    public String showParams() throws JRScriptletException
    {
    	StringBuilder sbResult = new StringBuilder("");
    	for (Map.Entry<String, JRFillParameter> entry : this.parametersMap.entrySet())
    	{
    		if (!entry.getValue().isSystemDefined()) {
    			 sbResult.append(entry.getKey()+ " : " + getParameterText(entry.getValue())  + "\n");
    		}
    	}
    	return sbResult.toString();
    }
    
    
    public String info() throws JRScriptletException
    {
    	return "Paulo P. \n"
    		+ "BI Team";
    }    
}