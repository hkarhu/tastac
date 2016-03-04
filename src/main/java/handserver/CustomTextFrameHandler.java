package handserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class CustomTextFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	
	private DirectionListener dl;
	private final ChannelGroup channels;
	
    public CustomTextFrameHandler(DirectionListener dl, ChannelGroup channels) {
    	this.channels = channels;
    	this.dl = dl;
	}
    
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		channels.add(ctx.channel());
	}
    
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		channels.remove(ctx.channel());
	}

	@Override
    protected void messageReceived(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {
        String request = frame.text();
        String returnMsg = "";
        switch (request.toUpperCase()) {
		case "W": dl.valuesChanged(1, 0, 0, 0, 0); returnMsg = "UP!"; break;
		case "S": dl.valuesChanged(0, 1, 0, 0, 0); returnMsg = "DOWN!"; break;
		case "D": dl.valuesChanged(0, 0, 1, 0, 0); returnMsg = "LEFT!"; break;
		case "A": dl.valuesChanged(0, 0, 0, 1, 0); returnMsg = "RIGHT!"; break;
		case "L": dl.valuesChanged(0, 0, 0, 0, 1); returnMsg = "FIRE!"; break;
		default:
			System.out.println(request);	
			break;
		}
        
        ctx.channel().writeAndFlush(new TextWebSocketFrame(dl.getValues() + ", \"echo\": \"" + returnMsg + "\", \"cons\": \"" + channels.size() + "\"}"));
        
        System.out.println("cons: " + channels.size());
        
    }
}
