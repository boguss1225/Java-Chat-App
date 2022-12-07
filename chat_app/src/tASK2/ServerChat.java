package tASK2;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerChat{
	
	private JFrame ChatServerFrame01 = new JFrame();
	private JTextArea ChatserverTextField01;
	private JTextField ChatserverTextField02;
	
	public ServerChat(Model model) {
	
		this.ChatServerFrame01.setTitle("Chat Server");
		this.ChatServerFrame01.setLocation(1000, 250);
		this.ChatServerFrame01.setSize(600,500);
		this.ChatServerFrame01.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ChatServerFrame01.setLayout(null);
		
		//Chat server Label
		JLabel ChatserverLabel01 = new JLabel("Messages");
		ChatserverLabel01.setLocation(30,10 );
		ChatserverLabel01.setSize(150,30);
		this.ChatServerFrame01.add(ChatserverLabel01);
		
		//Message to send : Label
		JLabel ChatserverLarbel02 = new JLabel("Message to send");	
		ChatserverLarbel02.setLocation(30,280 );
		ChatserverLarbel02.setSize(150,30);
		this.ChatServerFrame01.add(ChatserverLarbel02);
		
		//Chat log field
		this.ChatserverTextField01 = new JTextArea();
		this.ChatserverTextField01.setLocation(30,40 );
		this.ChatserverTextField01.setSize(500,230);
		this.ChatserverTextField01.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.ChatserverTextField01.setEditable(false);
		this.ChatServerFrame01.add(ChatserverTextField01);
		
		//Message field to be send
		this.ChatserverTextField02 = new JTextField();
		this.ChatserverTextField02.setLocation(30,310 );
		this.ChatserverTextField02.setSize(380,80);
		this.ChatServerFrame01.add(ChatserverTextField02);
		
		//Send Button
		JButton ChatserverButton01 = new JButton("Send");
		ChatserverButton01.setLocation(430,310 );
		ChatserverButton01.setSize(100,20);
		ChatserverButton01.addActionListener(new send_msg(model,this));
		this.ChatServerFrame01.add(ChatserverButton01);
		
		this.ChatServerFrame01.setVisible(true);
	
		this.ChatserverTextField02.addActionListener(new send_msg(model,this));
		
		//register view to model
		model.register(this);
	}
	
	JTextArea get_ChatserverTextField01() {
		return this.ChatserverTextField01;
	}
	
	JTextField get_ChatserverTextField02() {
		return this.ChatserverTextField02;
	}
}

