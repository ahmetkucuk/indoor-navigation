package core.tinyos;

import core.NavigationManager;
import model.NavigationNotification;
import model.NotificationResponse;
import net.tinyos.message.Message;
import net.tinyos.message.MessageListener;
import net.tinyos.message.MoteIF;
import net.tinyos.packet.BuildSource;
import net.tinyos.packet.PhoenixSource;
import net.tinyos.util.PrintStreamMessenger;

import java.util.ArrayList;
import java.util.List;

public class Listen implements MessageListener{

	private final List<NavigationNotification> values = new ArrayList<>();


	public Listen() {
		try {
			PhoenixSource reader = BuildSource.makePhoenix("serial@/dev/ttyUSB0:telos", PrintStreamMessenger.err);
			MoteIF mote = new MoteIF(reader);
			mote.registerListener(new NavigationMsg(), this);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void messageReceived(int i, Message message) {
		if(message instanceof NavigationMsg) {
			NavigationMsg oMessage = (NavigationMsg)message;
			values.add(oMessage.toNavigationNotification());
			System.out.println(oMessage.toString());
			NavigationManager.getManager().updatePosition(oMessage.toNavigationNotification());
		}
	}

	public NotificationResponse getNotificationResponse() {
		return new NotificationResponse(values);
	}

	public void resetValues() {
		values.clear();
	}
}

