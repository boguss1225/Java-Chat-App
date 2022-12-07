package tASK2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ClientChat{
	public static void main(String[] args) {
		//model
		Model model = new Model();
		// views
		ClientChatGUI c1 = new ClientChatGUI(model);
		ServerChat s1 = new ServerChat(model);
	}
}

class ClientChatGUI{
	
	private JFrame ClientChatFrame01 = new JFrame();
	
	private JList ClientChatAvuText01;
	private JTextField ClientChatSeuText01;
	private JTextArea ClientChatMes03;
	private JTextField ClientChatMto;
	
	private JButton ClientChatButton01;
	private JButton ClientChatButton02;
	
	public ClientChatGUI(Model model) {
		this.ClientChatFrame01.setTitle("Client Chat");
		this.ClientChatFrame01.setLocation(250, 250);
		this.ClientChatFrame01.setSize(600,500);
		this.ClientChatFrame01.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ClientChatFrame01.setLayout(null);
		
		//Chat Label Creation
		//Available users
		JLabel  ClientChatLabel01 = new JLabel("Available users:");
		ClientChatLabel01.setSize(150, 30);
		ClientChatLabel01.setLocation(30, 10);
		ClientChatLabel01.setHorizontalAlignment(JLabel.LEFT);
		this.ClientChatFrame01.add(ClientChatLabel01);		
		
		//Selected user
		JLabel  ClientChatLabel02 = new JLabel("Selected users:");
		ClientChatLabel02.setSize(150, 30);
		ClientChatLabel02.setLocation(200, 10);
		ClientChatLabel02.setHorizontalAlignment(JLabel.LEFT);
		this.ClientChatFrame01.add(ClientChatLabel02);
		
		//Messages
		JLabel ClientChatLabel03  = new JLabel("Messages");
		ClientChatLabel03.setLocation(30, 110);
		ClientChatLabel03.setSize(150,30);
		this.ClientChatFrame01.add(ClientChatLabel03);
		
		//Messages to send
		JLabel ClientChatLabel04 = new JLabel("Message to send");
		ClientChatLabel04.setLocation(30, 350);
		ClientChatLabel04.setSize(150,30);
		this.ClientChatFrame01.add(ClientChatLabel04);
		
		//Chat TextField Creation
		//Available users
		this.ClientChatAvuText01 = new JList(model.get_users());
		this.ClientChatAvuText01.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.ClientChatAvuText01.setSelectedIndex(0);
		JScrollPane listScroller = new JScrollPane(ClientChatAvuText01);
		listScroller.setPreferredSize(new Dimension(140, 60));
		listScroller.setBounds(30, 40, 140, 60);
		this.ClientChatFrame01.add(listScroller);
		
		//Selected users
		this.ClientChatSeuText01 = new JTextField();
		this.ClientChatSeuText01.setBounds(200, 40, 210, 25);
		this.ClientChatSeuText01.setText(model.get_current_user());
		this.ClientChatSeuText01.setEditable(false);
		this.ClientChatFrame01.add(this.ClientChatSeuText01);	
		
		//Messages
		this.ClientChatMes03 = new JTextArea();
		this.ClientChatMes03.setLocation(30,140);
		this.ClientChatMes03.setSize(500,210);
		this.ClientChatMes03.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.ClientChatMes03.setEditable(false);
		this.ClientChatFrame01.add(ClientChatMes03);
		
		//Message to send
		this.ClientChatMto = new JTextField();
		this.ClientChatMto.setLocation(30,380 );
		this.ClientChatMto.setSize(380,50);
		this.ClientChatMto.addActionListener(new send_msg(model,this));
		this.ClientChatFrame01.add(ClientChatMto);
		
		//Chat Button Creation
		//Connect Button
		this.ClientChatButton01 = new JButton("Connect");
		this.ClientChatButton01.setLocation(430, 40);
		this.ClientChatButton01.setSize(100,20);
		this.ClientChatButton01.addActionListener(new connect_button(model, this));
		this.ClientChatFrame01.add(ClientChatButton01);
		
		//Send Button
		this.ClientChatButton02 = new JButton("Send");
		this.ClientChatButton02.setLocation(430, 380);
		this.ClientChatButton02.setSize(100,20);
		this.ClientChatButton02.addActionListener(new send_msg(model,this));
		this.ClientChatFrame01.add(ClientChatButton02);
		this.ClientChatFrame01.setVisible(true);
		
		//register view to model
		model.register(this);
	
	}
	
	public void appendMsg(String msg) {
		this.ClientChatMes03.append(msg);
	}
	
	public JList get_ClientChatAvuText01() {
		return this.ClientChatAvuText01;
	}
	
	public JTextField get_ClientChatSeuText01() {
		return this.ClientChatSeuText01;
	}
	
	public JTextArea get_ClientChatMes03() {
		return this.ClientChatMes03;
	}
	
	public JTextField get_ClientChatMto() {
		return this.ClientChatMto;
	}

}

class connect_button implements ActionListener{
	String selected_user;
	JList list;
	Model model;
	ClientChatGUI c1;

	public connect_button (Model model, ClientChatGUI c1) {
		this.list = c1.get_ClientChatAvuText01();
		this.model = model;
		this.c1 = c1;
	}
	@Override
	public void actionPerformed(ActionEvent ae){
		this.selected_user = (String) this.list.getSelectedValue();
		this.model.set_current_user(this.selected_user);
		this.c1.get_ClientChatSeuText01().setText(this.selected_user);
	}
}

class send_msg implements ActionListener{
	String flag; // either client_button or server_button
	Model model;
	ClientChatGUI c1;
	ServerChat s1;
	String msg;
	
	public send_msg (Model model, ClientChatGUI c1) {
		this.flag = "client_button";
		this.c1 = c1;
		this.model = model;
	}
	
	public send_msg(Model model, ServerChat s1) {
		this.flag = "server_button";
		this.s1 = s1;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent ae){
		if(flag.equals("client_button")) {
			//get msg from textfiled
			this.msg = this.c1.get_ClientChatMto().getText();
			// init chat box
			this.c1.get_ClientChatMto().setText("");
		}
		
		if(flag.equals("server_button")) {
			//get msg from textfiled
			this.msg = this.s1.get_ChatserverTextField02().getText();
			// init chat box
			this.s1.get_ChatserverTextField02().setText("");
		}
		
		if(!this.msg.isEmpty()) {
			this.model.append_msg(this.msg, this.flag);
			this.model.update_views();
		}
	}
}
