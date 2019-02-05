package game;

import ui.TimeLabel;

public class Timer extends Thread {

	private int time;
	private TimeLabel labelObserver;
	private volatile boolean running = true;

	public Timer() {
		time = 0;
	}

	private void incTime() {
		++time;
		labelObserver.update(this);
	}

	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(1000);
				if (running)
					incTime();
			} catch (InterruptedException e) {
				running = false;
				e.printStackTrace();
			}
		}
	}

	public int getTime() {
		return time;
	}

	public void setLabelObserver(TimeLabel labelObserver) {
		this.labelObserver = labelObserver;
	}

	public void terminate() {
		running = false;
	}

}
