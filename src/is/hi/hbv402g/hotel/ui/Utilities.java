package is.hi.hbv402g.hotel.ui;

import java.awt.Component;
import java.awt.Container;

public class Utilities
{
	// Finds the component parent of type cls
	// http://stackoverflow.com/questions/8695259/accessing-pre-parent-class-with-actionlistener
	public static <T extends Container> T findParent(Component comp, Class<T> cls)
	{
		if (comp == null)
			return null;
		if (cls.isInstance(comp))
			return (cls.cast(comp));
		else
			return findParent(comp.getParent(), cls);
	}
}
