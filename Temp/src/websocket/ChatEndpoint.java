package websocket;

import java.io.IOException;
import java.util.HashMap;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import model.Message;

@ServerEndpoint(value = "/user/chat/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatEndpoint {
	private Session session;
	// private static final Set<ChatEndpoint> chatEndpoints = new
	// CopyOnWriteArraySet<>();
	// private static HashMap<String, ChatEndpoint> adminEndpoints = new
	// HashMap<>();

	private static HashMap<String, ChatEndpoint> userToAdminMap = new HashMap<>();
	private static HashMap<String, ChatEndpoint> adminToUserMap = new HashMap<>();
	private static HashMap<ChatEndpoint, String> adminIdMap = new HashMap<>();
	//private static Vector<ChatEndpoint> availableAdmins = new Vector<>();

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException {

		System.out.println("Socket aperto");
		this.session = session;

		// Se sono un admin, mi metto in attesa
		if (username.contains("admin")) {
			//System.out.println("Sono un admin (id " + session.getId() + ") che si mette a disposizione");
			EndpointBroker.getInstance().addAdmin(this);
			//availableAdmins.add(this);
			System.out.println("Ho aggiunto " + this + " alla queue di disponibili");
			adminIdMap.put(this, session.getId());
			adminToUserMap.put(session.getId(), this);
		} else {
			// Se sono un customer, chiedo aiuto al primo admin che trovo
			// Assumo che troverò sempre un admin a disposizione
			System.out.println("Sono un cliente (id " + session.getId() + ") che chiede aiuto");
			ChatEndpoint adminEndpoint = EndpointBroker.getInstance().takeAdmin();
			System.out.println("Ho rimosso " + adminEndpoint + " dalla queue di disponibili");
			userToAdminMap.put(session.getId(), adminEndpoint);
			adminToUserMap.put(adminIdMap.get(adminEndpoint), this);

			Message message = new Message();
			message.setFrom(username);
			message.setContent("Ho bisogno di aiuto");
			adminEndpoint.session.getBasicRemote().sendObject(message);
		}
	}

	@OnMessage
	public void onMessage(Session session, Message message) throws IOException, EncodeException {
		System.out.print("Sono l'id " + session.getId() + " e mando un messaggio ad un ");
		ChatEndpoint e = userToAdminMap.get(session.getId());

		if (e == null) {
			System.out.println("utente");
			e = adminToUserMap.get(session.getId());
		} else
			System.out.println("admin");

		e.session.getBasicRemote().sendObject(message);
	}

	@OnClose
	public void onClose(Session session) throws IOException, EncodeException {
		// Qui sto assumendo che a chiudere siano solo gli user, e mai gli admin.
		ChatEndpoint adminLiberato = userToAdminMap.get(session.getId());
		if (adminLiberato != null) {
			System.out.println("Uno user sta chiudendo la connessione");
			EndpointBroker.getInstance().addAdmin(adminLiberato);
			System.out.println("Ho riaggiunto " + adminLiberato + " alla queue di disponibili");
			userToAdminMap.remove(session.getId());
			Message message = new Message();
			message.setFrom(session.getId());
			message.setContent("Disconnesso.");
			adminLiberato.session.getBasicRemote().sendObject(message);
		}
			
		System.out.println("Socket chiuso");
		
		
		
//		chatEndpoints.remove(this);
//		broadcast(message);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		// Do error handling here
	}

//	private static void broadcast(Message message) throws IOException, EncodeException {
//		chatEndpoints.forEach(endpoint -> {
//			synchronized (endpoint) {
//				try {
//					endpoint.session.getBasicRemote().sendObject(message);
//				} catch (IOException | EncodeException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

}