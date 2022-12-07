package tASK2;

public class Model {
	private String msg;
	private String[] users;
	private String current_user;
	private ClientChatGUI c1;
	private ServerChat s1;
	
	public Model() {
		this.msg = "new chat started!";
		String[] userlist = {
				"client_A",
				"client_B",
				"client_C",
				"client_D",
				"client_E"
				};
		this.users = userlist;
		this.current_user = this.users[0];
	}
	
	String get_msg(){
		return this.msg;
	}
	
	String[] get_users() {
		return this.users;
	}
	
	String get_current_user() {
		return this.current_user;
	}
	
	void set_msg(String new_msg) {
		this.msg = new_msg;
	}
	
	void append_msg(String new_msg, String flag) {
		if(flag.equals("client_button")) {
			this.msg = this.msg + "\n" + this.current_user+ ": " + new_msg;
		}
		
		if(flag.equals("server_button")) {
			this.msg = this.msg + "\nServer: " + new_msg;
		}
	}
	
	void set_current_user(String user) {
		this.current_user = user;
	}
	
	// mini controller in Model
	void register(ClientChatGUI c1) {
		this.c1 = c1;
	}
	
	void register(ServerChat s1) {
		this.s1 = s1;
	}
	
	void update_views() {
		//display msg to view
		this.c1.get_ClientChatMes03().setText(this.msg);
		this.s1.get_ChatserverTextField01().setText(this.msg);
	}
	
}
