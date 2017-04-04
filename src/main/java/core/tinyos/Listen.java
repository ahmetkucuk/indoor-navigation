package core.tinyos;

import core.model.LightIntensityResponse;
import net.tinyos.message.Message;
import net.tinyos.message.MessageListener;
import net.tinyos.message.MoteIF;
import net.tinyos.packet.BuildSource;
import net.tinyos.packet.PacketSource;
import net.tinyos.packet.PhoenixSource;
import net.tinyos.util.Dump;
import net.tinyos.util.Messenger;
import net.tinyos.util.PrintStreamMessenger;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class Listen implements MessageListener{

	private final CopyOnWriteArrayList<LightIntensityResponse> values = new CopyOnWriteArrayList<>();


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
			System.out.println(oMessage);
		}
	}
/*
	public void startInBackground() {
		if(!isStarted) {
			Runnable task = new Runnable() {
				@Override
				public void run() {
					try {
						start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			isStarted = true;

			new Thread(task).start();
		}
	}

	private void start() throws IOException {

		PacketSource reader = BuildSource.makePacketSource("serial@/dev/ttyUSB0:telos");

		if (reader == null) {
			System.err.println("Invalid packet source (check your MOTECOM environment variable)");
			System.exit(2);
		}

		try {
			reader.open(PrintStreamMessenger.err);
			for (;;) {
				byte[] packet = reader.readPacket();
				Dump.printPacket(System.out, packet);
				System.out.println();

				int light = (packet[packet.length - 1] & 0xff) - 50;

				LightIntensityResponse lightIntensity = new LightIntensityResponse();
				lightIntensity.setValues(new int[]{light});
				lightIntensity.setId("Unknown");

				values.add(lightIntensity);
			}
		}
		catch (IOException e) {
			System.err.println("Error on " + reader.getName() + ": " + e);
		}
	}
*/
	public CopyOnWriteArrayList<LightIntensityResponse> getValues() {
		return values;
	}
}

