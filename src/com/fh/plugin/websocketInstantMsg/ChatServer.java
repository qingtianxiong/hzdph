package com.fh.plugin.websocketInstantMsg;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Date;

import net.sf.json.JSONObject;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;


/**
 * 鍗虫椂閫氳
 * @author FH
 * QQ 313596790
 * 2015-5-16
 */
public class ChatServer extends WebSocketServer{

	public ChatServer(int port) throws UnknownHostException {
		super(new InetSocketAddress(port));
	}

	public ChatServer(InetSocketAddress address) {
		super(address);
	}

	/**
	 * 瑙﹀彂杩炴帴浜嬩欢
	 */
	@Override
	public void onOpen( WebSocket conn, ClientHandshake handshake ) {
		//this.sendToAll( "new connection: " + handshake.getResourceDescriptor() );
		//System.out.println("===" + conn.getRemoteSocketAddress().getAddress().getHostAddress());
	}

	/**
	 * 瑙﹀彂鍏抽棴浜嬩欢
	 */
	@Override
	public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
		userLeave(conn);
	}

	/**
	 * 瀹㈡埛绔彂閫佹秷鎭埌鏈嶅姟鍣ㄦ椂瑙﹀彂浜嬩欢
	 */
	@Override
	public void onMessage(WebSocket conn, String message){
		message = message.toString();
		if(null != message && message.startsWith("FHadminqq313596790")){
			this.userjoin(message.replaceFirst("FHadminqq313596790", ""),conn);
		}if(null != message && message.startsWith("LeaveFHadminqq313596790")){
			this.userLeave(conn);
		}if(null != message && message.contains("fhadmin886")){
			String toUser = message.substring(message.indexOf("fhadmin886")+10, message.indexOf("fhfhadmin888"));
			message = message.substring(0, message.indexOf("fhadmin886")) +"[绉佷俊]  "+ message.substring(message.indexOf("fhfhadmin888")+12, message.length());
			ChatServerPool.sendMessageToUser(ChatServerPool.getWebSocketByUser(toUser),message);//鍚戞墍鏌愮敤鎴峰彂閫佹秷鎭�
			ChatServerPool.sendMessageToUser(conn, message);//鍚屾椂鍚戞湰浜哄彂閫佹秷鎭�
		}else{
			ChatServerPool.sendMessage(message.toString());//鍚戞墍鏈夊湪绾跨敤鎴峰彂閫佹秷鎭�
		}
	}

	public void onFragment( WebSocket conn, Framedata fragment ) {
	}

	/**
	 * 瑙﹀彂寮傚父浜嬩欢
	 */
	@Override
	public void onError( WebSocket conn, Exception ex ) {
		ex.printStackTrace();
		if( conn != null ) {
			//some errors like port binding failed may not be assignable to a specific websocket
		}
	}

	
	/**
	 * 鐢ㄦ埛鍔犲叆澶勭悊
	 * @param user
	 */
	public void userjoin(String user, WebSocket conn){
		JSONObject result = new JSONObject();
		result.element("type", "user_join");
		result.element("user", "<a onclick=\"toUserMsg('"+user+"');\">"+user+"</a>");
		ChatServerPool.sendMessage(result.toString());				//鎶婂綋鍓嶇敤鎴峰姞鍏ュ埌鎵�鏈夊湪绾跨敤鎴峰垪琛ㄤ腑
		String joinMsg = "{\"from\":\"[绯荤粺]\",\"content\":\""+user+"涓婄嚎浜哱,\"timestamp\":"+new Date().getTime()+",\"type\":\"message\"}";
		ChatServerPool.sendMessage(joinMsg);						//鍚戞墍鏈夊湪绾跨敤鎴锋帹閫佸綋鍓嶇敤鎴蜂笂绾跨殑娑堟伅
		result = new JSONObject();
		result.element("type", "get_online_user");
		ChatServerPool.addUser(user,conn);							//鍚戣繛鎺ユ睜娣诲姞褰撳墠鐨勮繛鎺ュ璞�
		result.element("list", ChatServerPool.getOnlineUser());
		ChatServerPool.sendMessageToUser(conn, result.toString());	//鍚戝綋鍓嶈繛鎺ュ彂閫佸綋鍓嶅湪绾跨敤鎴风殑鍒楄〃
	}
	
	/**
	 * 鐢ㄦ埛涓嬬嚎澶勭悊
	 * @param user
	 */
	public void userLeave(WebSocket conn){
		String user = ChatServerPool.getUserByKey(conn);
		boolean b = ChatServerPool.removeUser(conn);				//鍦ㄨ繛鎺ユ睜涓Щ闄よ繛鎺�
		if(b){
			JSONObject result = new JSONObject();
			result.element("type", "user_leave");
			result.element("user", "<a onclick=\"toUserMsg('"+user+"');\">"+user+"</a>");
			ChatServerPool.sendMessage(result.toString());			//鎶婂綋鍓嶇敤鎴蜂粠鎵�鏈夊湪绾跨敤鎴峰垪琛ㄤ腑鍒犻櫎
			String joinMsg = "{\"from\":\"[绯荤粺]\",\"content\":\""+user+"涓嬬嚎浜哱,\"timestamp\":"+new Date().getTime()+",\"type\":\"message\"}";
			ChatServerPool.sendMessage(joinMsg);					//鍚戝湪绾跨敤鎴峰彂閫佸綋鍓嶇敤鎴烽��鍑虹殑娑堟伅
		}
	}
	public static void main( String[] args ) throws InterruptedException , IOException {
		WebSocketImpl.DEBUG = false;
		int port = 8887; //绔彛
		ChatServer s = new ChatServer(port);
		s.start();
		//System.out.println( "鏈嶅姟鍣ㄧ殑绔彛" + s.getPort() );
	}

}

