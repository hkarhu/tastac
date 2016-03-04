package handserver;

import java.io.IOException;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

public class TASTAC implements DirectionListener {

	SerialInterface ser;
	
	private volatile float up = 0, down = 0, left = 0, right = 0, fire = 0;
	private boolean changed = true;
	
	public TASTAC() {
		
		try {
			
			ser = new SerialInterface("/dev/ttyUSB2", 115200);
			
			Thread serthread = new Thread(new Runnable() {
				@Override
				public void run() {
					float i=0;
					while(true){
						if(changed){
							System.out.printf("%s %s %s %s %s\n", up, down, left, right, fire);
							ser.write(getOutString().getBytes());
							changed = false;
						}
						try {
							Thread.sleep(45);
							if(up > 0) up -= 0.2f;
							if(down > 0) down -= 0.2f;
							if(left > 0) left -= 0.2f;
							if(right > 0) right -= 0.2f;
							if(fire > 0) fire -= 0.2f;
							
							if(up < 0) up = 0;
							if(down < 0) down = 0;
							if(left < 0) left = 0;
							if(right < 0) right = 0;
							if(fire < 0) fire = 0;
							
							ser.write(getOutString().getBytes());
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			
			serthread.start();
			
			WebSocketServer websocket = new WebSocketServer(14444);
			websocket.addDirectionListener(this);
			websocket.run();
			
		} catch (NoSuchPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedCommOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String getOutString() {
		String out = "";
		if (up > 0.5f) {out += "1";} else {out += "0";}
		if (down > 0.5f) {out += "1";} else {out += "0";}
		if (right > 0.5f) {out += "1";} else {out += "0";}
		if (left > 0.5f) {out += "1";} else {out += "0";}
		if (fire > 0.5f) {out += "1";} else {out += "0";}
		out += '\n';
		return out;
	}

	public static void main(String[] args) {
		new TASTAC();
	}
	
	@Override
	public void valuesChanged(float a, float b, float c, float d, float e) {
		up += a;
		down += b;
		left += c;
		right += d;
		fire += e;
		
		if(up > 5f) up = 5f;
		if(down > 5f) down = 5f;
		if(left > 5f) left = 5f;
		if(right > 5f) right = 5f;
		if(fire > 5f) fire = 5f;
		
		changed = true;
		
	}

	@Override
	public String getValues() {
		return "{ \"up\":" + up + ", \"down\":" + down + ", \"left\":" + left + ", \"right\":" + right + ", \"fire\":" + fire;
	}

	@Override
	public float getMultiplier() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
