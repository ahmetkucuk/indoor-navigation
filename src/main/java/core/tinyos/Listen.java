package core.tinyos;

import core.model.LightIntensityMeasurement;
import core.model.LightIntensityResponse;
import net.tinyos.message.Message;
import net.tinyos.message.MessageListener;
import net.tinyos.message.MoteIF;
import net.tinyos.packet.BuildSource;
import net.tinyos.packet.PhoenixSource;
import net.tinyos.util.PrintStreamMessenger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Listen implements MessageListener{

	private final List<LightIntensityMeasurement> values = new ArrayList<>();


	public Listen() {
		try {
			PhoenixSource reader = BuildSource.makePhoenix("serial@/dev/ttyUSB0:telos", PrintStreamMessenger.err);
			MoteIF mote = new MoteIF(reader);
			mote.registerListener(new OscilloscopeMsg(), this);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void messageReceived(int i, Message message) {
		if(message instanceof OscilloscopeMsg) {
			OscilloscopeMsg oMessage = (OscilloscopeMsg)message;
			values.add(oMessage.toLightIntensityResponse());
		}
	}
	public List<LightIntensityMeasurement> getValues() {
		return values;
	}

	public LightIntensityResponse getLightIntesityResponse() {
		return new LightIntensityResponse(values);
	}

	public void resetValues() {
		values.clear();
	}
}

