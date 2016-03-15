package org.wso2.carbon.jndi.internal.osgi;

import javax.naming.CompositeName;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import java.util.Enumeration;

/**
 * A composite name to represent Osgi url scheme.
 */
public class OSGiName extends CompositeName {

    public OSGiName(String name) throws InvalidNameException {
        super(name);
    }

    public OSGiName(Name name) throws InvalidNameException {
        this(name.toString());
    }

    public boolean hasInterface() {
        return size() > 1;
    }

    public boolean hasFilter() {
        return size() > 2;
    }

    public String getJNDIServiceName() {
        StringBuilder builder = new StringBuilder();
        Enumeration<String> parts = getAll();
        parts.nextElement();  //we need to skip the first component (eg:osgi:service)

        if (parts.hasMoreElements()) {
            while (parts.hasMoreElements()) {
                builder.append(parts.nextElement());
                builder.append('/');
            }

            builder.deleteCharAt(builder.length() - 1);  //remove last "/" character
        }

        return builder.toString();
    }
}
