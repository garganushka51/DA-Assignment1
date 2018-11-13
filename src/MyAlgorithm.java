
import teachnet.algorithm.BasicAlgorithm;

import java.awt.Color;
import java.util.Random;

public class MyAlgorithm extends BasicAlgorithm {

	Color color = null;
	int markInterface = -1;
	String caption;
	Random rand = null;

	private boolean active = true;
	private int receivedCount = 0;
	private int nodeId = -1;

	private java.util.Map<Integer, Integer> messagesPerRound = new java.util.HashMap<Integer, Integer>();
	
	public void setup(java.util.Map<String, Object> config) {
		nodeId = (Integer) config.get("node.id");
		caption = "" + nodeId + " - " + active;
	}

	public void initiate() {
		int i;
		messagesPerRound.put(Integer.valueOf(0),0);
		caption = "" + nodeId + " - " + active + " - " + messagesPerRound.get(0);
		color = Color.RED;
		for (i = 0; i < checkInterfaces(); ++i) {
			send(i, new MyMsg(nodeId, 0, 0));
		}
	}

	public void receive(int interf, Object message) {
		MyMsg msg = (MyMsg) message;

		if (active) {
			// Update the received message count based on round
			messagesPerRound.put(
					Integer.valueOf(msg.getRoundNumber()),
					messagesPerRound.get(Integer.valueOf(msg.getRoundNumber()))+1);
			caption = "" + nodeId + " - " + active + " - " + " message round:" + msg.getRoundNumber();

			if (nodeId > msg.getId()) {
				// Eat the message and start next round in this direction
				if (messagesPerRound.get(msg.getRoundNumber()) == 2) {
					messagesPerRound.put(Integer.valueOf(msg.getRoundNumber() + 1),0);
					for (int i = 0; i < checkInterfaces(); ++i) {
						send(i, new MyMsg(nodeId, 0, msg.getRoundNumber()+1));
					}
				} 

			} else if (nodeId < msg.getId()) {
				// Kill ourselves and send back our id - it doesnt matter what we send
				if (messagesPerRound.get(msg.getRoundNumber()) == 2) {
					active = false;
					caption = "" + nodeId + " - " + active;
					color = Color.GRAY;
					for (int i = 0; i < checkInterfaces(); ++i) {
						send(i, new MyMsg(nodeId, 1, msg.getRoundNumber()));
					}

				}

				

			} else if (nodeId == msg.getId()) {
				// This was the last round, end algorithm.
				// Send our own again as a winner notification

			}
		} else {
			// We are disabled we will just forward
			for (int i = 0; i < checkInterfaces(); ++i) {
				if (i != interf) {
					send(i, msg);
				}
			}

		}
		/*
		 * if (message instanceof Boolean) { send(interf, !(Boolean)message); return; }
		 * else if (message instanceof MyMsg) { MyMsg msg = (MyMsg) message; // color =
		 * msg.getColor(); caption = "Got " + message; markInterface = interf; //
		 * send((interf + 1) % checkInterfaces(), new MyMsg(1 + msg.getInt())); }
		 */
	}
}
