package event;

import java.util.EventListener;


public interface Observer extends EventListener{
	public void updatePerformed(UpdateEvent e);
}